package com.bizBrainz.server.helpers;

import com.bizBrainz.external.models.BaseDomain;
import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.external.models.Policy;
import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.acl.PolicyGenerator;
import com.bizBrainz.server.domains.ActionCollection;
import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.domains.CommentThread;
import com.bizBrainz.server.domains.NewAction;
import com.bizBrainz.server.domains.NewPage;
import com.bizBrainz.server.domains.PermissionGroup;
import com.bizBrainz.server.domains.Theme;
import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.dtos.Permission;
import com.bizBrainz.server.repositories.ActionCollectionRepository;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.CommentThreadRepository;
import com.bizBrainz.server.repositories.DatasourceRepository;
import com.bizBrainz.server.repositories.NewActionRepository;
import com.bizBrainz.server.repositories.NewPageRepository;
import com.bizBrainz.server.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bizBrainz.server.acl.AclPermission.MANAGE_DATASOURCES;
import static com.bizBrainz.server.acl.AclPermission.READ_THEMES;

@Component
@AllArgsConstructor
@Slf4j
public class PolicyUtils {

    private final PolicyGenerator policyGenerator;
    private final ApplicationRepository applicationRepository;
    private final DatasourceRepository datasourceRepository;
    private final NewPageRepository newPageRepository;
    private final NewActionRepository newActionRepository;
    private final CommentThreadRepository commentThreadRepository;
    private final ActionCollectionRepository actionCollectionRepository;
    private final ThemeRepository themeRepository;

    public <T extends BaseDomain> T addPoliciesToExistingObject(Map<String, Policy> policyMap, T obj) {
        // Making a deep copy here so we don't modify the `policyMap` object.
        // TODO: Investigate a solution without using deep-copy.
        // TODO: Do we need to return the domain object?
        final Map<String, Policy> policyMap1 = new HashMap<>();
        for (Map.Entry<String, Policy> entry : policyMap.entrySet()) {
            Policy entryValue = entry.getValue();
            Policy policy = Policy.builder()
                    .permission(entryValue.getPermission())
                    .permissionGroups(new HashSet<>(entryValue.getPermissionGroups()))
                    .build();
            policyMap1.put(entry.getKey(), policy);
        }

        // Append the user to the existing permission policy if it already exists.
        for (Policy policy : obj.getPolicies()) {
            String permission = policy.getPermission();
            if (policyMap1.containsKey(permission)) {
                Set<String> permissionGroups = new HashSet<>();
                if(policy.getPermissionGroups() != null) {
                    permissionGroups.addAll(policy.getPermissionGroups());
                }
                if (policyMap1.get(permission).getPermissionGroups() != null) {
                    permissionGroups.addAll(policyMap1.get(permission).getPermissionGroups());
                }
                policy.setPermissionGroups(permissionGroups);
                // Remove this permission from the policyMap as this has been accounted for in the above code
                policyMap1.remove(permission);
            }
        }

        obj.getPolicies().addAll(policyMap1.values());
        return obj;
    }

    public <T extends BaseDomain> T removePoliciesFromExistingObject(Map<String, Policy> policyMap, T obj) {
        // Making a deep copy here so we don't modify the `policyMap` object.
        // TODO: Investigate a solution without using deep-copy.
        final Map<String, Policy> policyMap1 = new HashMap<>();
        for (Map.Entry<String, Policy> entry : policyMap.entrySet()) {
            policyMap1.put(entry.getKey(), entry.getValue());
        }

        // Remove the user from the existing permission policy if it exists.
        for (Policy policy : obj.getPolicies()) {
            String permission = policy.getPermission();
            if (policyMap1.containsKey(permission)) {
                if (policy.getPermissionGroups() == null) {
                    policy.setPermissionGroups(new HashSet<>());
                }
                if (policyMap1.get(permission).getPermissionGroups() != null) {
                    policy.getPermissionGroups().removeAll(policyMap1.get(permission).getPermissionGroups());
                }
                // Remove this permission from the policyMap as this has been accounted for in the above code
                policyMap1.remove(permission);
            }
        }

        return obj;
    }

    /**
     * Given a set of AclPermissions, generate all policies (including policies from lateral permissions) for the user.
     *
     * @param permissions
     * @param user
     * @return
     */
    public Map<String, Policy> generatePolicyFromPermission(Set<AclPermission> permissions, User user) {
        return generatePolicyFromPermission(permissions, user.getUsername());
    }

    public Map<String, Policy> generatePolicyFromPermission(Set<AclPermission> permissions, String username) {
        return permissions.stream()
                .map(perm -> {
                    // Create a policy for the invited user using the permission as per the role
                    Policy policyWithCurrentPermission = Policy.builder().permission(perm.getValue())
                            .users(Set.of(username)).build();
                    // Generate any and all lateral policies that might come with the current permission
                    Set<Policy> policiesForUser = policyGenerator.getLateralPolicies(perm, Set.of(username), null);
                    policiesForUser.add(policyWithCurrentPermission);
                    return policiesForUser;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Policy::getPermission, Function.identity()));
    }

    public Map<String, Policy> generatePolicyFromPermissionGroupForObject(PermissionGroup permissionGroup, String objectId) {
        Set<Permission> permissions = permissionGroup.getPermissions();
        return permissions.stream()
                .filter(perm -> perm.getDocumentId().equals(objectId))
                .map(perm -> {

                    Policy policyWithCurrentPermission = Policy.builder().permission(perm.getAclPermission().getValue())
                            .permissionGroups(Set.of(permissionGroup.getId()))
                            .build();
                    // Generate any and all lateral policies that might come with the current permission
                    Set<Policy> policiesForPermissionGroup = policyGenerator.getLateralPolicies(perm.getAclPermission(), Set.of(permissionGroup.getId()), null);
                    policiesForPermissionGroup.add(policyWithCurrentPermission);
                    return policiesForPermissionGroup;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Policy::getPermission, Function.identity(), (policy1, policy2) -> policy1));
    }

    public Map<String, Policy> generatePolicyFromPermissionWithPermissionGroup(AclPermission permission, String permissionGroupId) {

        Policy policyWithCurrentPermission = Policy.builder().permission(permission.getValue())
                .permissionGroups(Set.of(permissionGroupId))
                .build();
        // Generate any and all lateral policies that might come with the current permission
        Set<Policy> policiesForPermission = policyGenerator.getLateralPolicies(permission, Set.of(permissionGroupId), null);
        policiesForPermission.add(policyWithCurrentPermission);
        return policiesForPermission.stream()
                .collect(Collectors.toMap(Policy::getPermission, Function.identity()));
    }


    public Map<String, Policy> generatePolicyFromPermissionForMultipleUsers(Set<AclPermission> permissions, List<User> users) {
        Set<String> usernames = users.stream().map(user -> user.getUsername()).collect(Collectors.toSet());

        return permissions.stream()
                .map(perm -> {
                    // Create a policy for the invited user using the permission as per the role
                    Policy policyWithCurrentPermission = Policy.builder().permission(perm.getValue())
                            .users(usernames).build();
                    // Generate any and all lateral policies that might come with the current permission
                    Set<Policy> policiesForUser = policyGenerator.getLateralPolicies(perm, usernames, null);
                    policiesForUser.add(policyWithCurrentPermission);
                    return policiesForUser;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Policy::getPermission, Function.identity()));
    }

    public Flux<Datasource> updateWithNewPoliciesToDatasourcesByWorkspaceId(String workspaceId, Map<String, Policy> newPoliciesMap, boolean addPolicyToObject) {

        return datasourceRepository
                // fetch datasources with execute permissions so that app viewers can invite other app viewers
                .findAllByWorkspaceId(workspaceId, AclPermission.EXECUTE_DATASOURCES)
                // In case we have come across a datasource for this workspace that the current user is not allowed to manage, move on.
                .switchIfEmpty(Mono.empty())
                .map(datasource -> {
                    if (addPolicyToObject) {
                        return addPoliciesToExistingObject(newPoliciesMap, datasource);
                    } else {
                        return removePoliciesFromExistingObject(newPoliciesMap, datasource);
                    }
                })
                .collectList()
                .flatMapMany(updatedDatasources -> datasourceRepository.saveAll(updatedDatasources));
    }

    public Flux<Datasource> updateWithNewPoliciesToDatasourcesByDatasourceIds(Set<String> ids, Map<String, Policy> datasourcePolicyMap, boolean addPolicyToObject) {

        return datasourceRepository
                .findAllByIds(ids, MANAGE_DATASOURCES)
                // In case we have come across a datasource the current user is not allowed to manage, move on.
                .switchIfEmpty(Mono.empty())
                .flatMap(datasource -> {
                    Datasource updatedDatasource;
                    if (addPolicyToObject) {
                        updatedDatasource = addPoliciesToExistingObject(datasourcePolicyMap, datasource);
                    } else {
                        updatedDatasource = removePoliciesFromExistingObject(datasourcePolicyMap, datasource);
                    }

                    return Mono.just(updatedDatasource);
                })
                .collectList()
                .flatMapMany(datasources -> datasourceRepository.saveAll(datasources));
    }

    public Flux<Application> updateWithNewPoliciesToApplicationsByWorkspaceId(String workspaceId, Map<String, Policy> newAppPoliciesMap, boolean addPolicyToObject) {

        return applicationRepository
                // fetch applications with read permissions so that app viewers can invite other app viewers
                .findByWorkspaceId(workspaceId, AclPermission.READ_APPLICATIONS)
                // In case we have come across an application for this workspace that the current user is not allowed to manage, move on.
                .switchIfEmpty(Mono.empty())
                .map(application -> {
                    if (addPolicyToObject) {
                        return addPoliciesToExistingObject(newAppPoliciesMap, application);
                    } else {
                        return removePoliciesFromExistingObject(newAppPoliciesMap, application);
                    }
                })
                .collectList()
                .flatMapMany(updatedApplications -> applicationRepository.saveAll(updatedApplications));
    }

    public Flux<NewPage> updateWithApplicationPermissionsToAllItsPages(String applicationId, Map<String, Policy> newPagePoliciesMap, boolean addPolicyToObject) {

        // Instead of fetching pages from the application object, we fetch pages from the page repository. This ensures that all the published
        // AND the unpublished pages are updated with the new policy change [This covers the edge cases where a page may exist
        // in published app but has been deleted in the edit mode]. This means that we don't have to do any special treatment
        // during deployment of the application to handle edge cases.
        return newPageRepository
                // fetch pages with read permissions so that app viewers can invite other app viewers
                .findByApplicationId(applicationId, AclPermission.READ_PAGES)
                .switchIfEmpty(Mono.empty())
                .map(page -> {
                    if (addPolicyToObject) {
                        return addPoliciesToExistingObject(newPagePoliciesMap, page);
                    } else {
                        return removePoliciesFromExistingObject(newPagePoliciesMap, page);
                    }
                })
                .collectList()
                .flatMapMany(updatedPages -> newPageRepository
                        .saveAll(updatedPages));
    }

    public Flux<Theme> updateThemePolicies(Application application, Map<String, Policy> themePolicyMap, boolean addPolicyToObject) {
        Flux<Theme> applicationThemes = themeRepository.getApplicationThemes(application.getId(), READ_THEMES);
        if(StringUtils.hasLength(application.getEditModeThemeId())) {
            applicationThemes = applicationThemes.concatWith(
                    themeRepository.findById(application.getEditModeThemeId(), READ_THEMES)
            );
        }
        if(StringUtils.hasLength(application.getPublishedModeThemeId())) {
            applicationThemes = applicationThemes.concatWith(
                    themeRepository.findById(application.getPublishedModeThemeId(), READ_THEMES)
            );
        }
        return applicationThemes
                .filter(theme -> !theme.isSystemTheme()) // skip the system themes
                .map(theme -> {
                    if (addPolicyToObject) {
                        return addPoliciesToExistingObject(themePolicyMap, theme);
                    } else {
                        return removePoliciesFromExistingObject(themePolicyMap, theme);
                    }
                })
                .collectList()
                .flatMapMany(themeRepository::saveAll);
    }

    public Flux<CommentThread> updateCommentThreadPermissions(
            String applicationId, Map<String, Policy> commentThreadPolicyMap, String username, boolean addPolicyToObject) {

        return
                // fetch comment threads with read permissions
                commentThreadRepository.findByApplicationId(applicationId, AclPermission.READ_THREADS)
                .switchIfEmpty(Mono.empty())
                .map(thread -> {
                    if(!Boolean.TRUE.equals(thread.getIsPrivate())) {
                        if (addPolicyToObject) {
                            return addPoliciesToExistingObject(commentThreadPolicyMap, thread);
                        } else {
                            if(CollectionUtils.isNotEmpty(thread.getSubscribers())) {
                                thread.getSubscribers().remove(username);
                            }
                            return removePoliciesFromExistingObject(commentThreadPolicyMap, thread);
                        }
                    }
                    return thread;
                })
                .collectList()
                .flatMapMany(commentThreadRepository::saveAll);
    }

    /**
     * Instead of fetching actions by pageId, fetch actions by applicationId and then update the action policies
     * using the new ActionPoliciesMap. This ensures the following :
     * 1. Instead of bulk updating actions page wise, we do bulk update of actions in one go for the entire application.
     * 2. If the action is associated with different pages (in published/unpublished page due to movement of action), fetching
     * actions by applicationId ensures that we update ALL the actions and don't have to do special handling for the same.
     *
     * @param applicationId
     * @param newActionPoliciesMap
     * @param addPolicyToObject
     * @return
     */
    public Flux<NewAction> updateWithPagePermissionsToAllItsActions(String applicationId, Map<String, Policy> newActionPoliciesMap, boolean addPolicyToObject) {

        return newActionRepository
                .findByApplicationId(applicationId)
                .switchIfEmpty(Mono.empty())
                .map(action -> {
                    if (addPolicyToObject) {
                        return addPoliciesToExistingObject(newActionPoliciesMap, action);
                    } else {
                        return removePoliciesFromExistingObject(newActionPoliciesMap, action);
                    }
                })
                .collectList()
                .flatMapMany(newActionRepository::saveAll);
    }

    public Flux<ActionCollection> updateWithPagePermissionsToAllItsActionCollections(String applicationId, Map<String, Policy> newActionPoliciesMap, boolean addPolicyToObject) {

        return actionCollectionRepository
                .findByApplicationId(applicationId)
                .switchIfEmpty(Mono.empty())
                .map(action -> {
                    if (addPolicyToObject) {
                        return addPoliciesToExistingObject(newActionPoliciesMap, action);
                    } else {
                        return removePoliciesFromExistingObject(newActionPoliciesMap, action);
                    }
                })
                .collectList()
                .flatMapMany(actionCollectionRepository::saveAll);
    }

    public Map<String, Policy> generateInheritedPoliciesFromSourcePolicies(Map<String, Policy> sourcePolicyMap,
                                                                           Class<? extends BaseDomain> sourceEntity,
                                                                           Class<? extends BaseDomain> destinationEntity) {
        Set<Policy> extractedInterestingPolicySet = new HashSet<>(sourcePolicyMap.values());

        return policyGenerator.getAllChildPolicies(extractedInterestingPolicySet, sourceEntity, destinationEntity)
                .stream()
                .collect(Collectors.toMap(Policy::getPermission, Function.identity()));
    }

    public Boolean isPermissionPresentForUser(Set<Policy> policies, String permission, String username) {

        if (policies == null || policies.isEmpty()) {
            return false;
        }

        Optional<Policy> requestedPermissionPolicyOptional = policies.stream().filter(policy -> {
            if (policy.getPermission().equals(permission)) {
                Set<String> users = policy.getUsers();
                if (users.contains(username)) {
                    return true;
                }
            }
            return false;
        }).findFirst();

        if (requestedPermissionPolicyOptional.isPresent()) {
            return true;
        }

        return false;
    }

    public Set<String> findUsernamesWithPermission(Set<Policy> policies, AclPermission permission) {
        if (CollectionUtils.isNotEmpty(policies) && permission != null) {
            final String permissionString = permission.getValue();
            for (Policy policy : policies) {
                if (permissionString.equals(policy.getPermission())) {
                    return policy.getUsers();
                }
            }
        }

        return Collections.emptySet();
    }

}
