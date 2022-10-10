package com.bizBrainz.server.services;

import com.bizBrainz.server.configurations.WithMockBizbrainzUser;
import com.bizBrainz.server.domains.LoginSource;
import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.exceptions.BizbrainzError;
import com.bizBrainz.server.exceptions.BizbrainzException;
import com.bizBrainz.server.repositories.PermissionGroupRepository;
import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;

import static com.bizBrainz.server.constants.FieldName.ADMINISTRATOR;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"signup.disabled = true", "admin.emails = dummy_admin@bizBrainz.com,dummy2@bizBrainz.com"})
@DirtiesContext
public class UserServiceWithDisabledSignupTest {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    PermissionGroupRepository permissionGroupRepository;

    Mono<User> userMono;

    @BeforeEach
    public void setup() {
        userMono = userService.findByEmail("usertest@usertest.com");
    }

    @Test
    @WithMockBizbrainzUser
    public void createNewUserValidWhenDisabled() {
        User newUser = new User();
        newUser.setEmail("new-user-email@email.com");
        newUser.setPassword("new-user-test-password");

        Mono<User> userMono = userService.create(newUser);

        StepVerifier.create(userMono)
                .expectErrorMatches(error -> {
                    assertThat(error).isInstanceOf(BizbrainzException.class);
                    assertThat(((BizbrainzException) error).getError()).isEqualTo(BizbrainzError.SIGNUP_DISABLED);
                    return true;
                })
                .verify();
    }

    @Test
    @WithMockBizbrainzUser
    public void createNewAdminValidWhenDisabled() {
        User newUser = new User();
        newUser.setEmail("dummy_admin@bizBrainz.com");
        newUser.setPassword("admin-password");

        Mono<User> userMono = userService.create(newUser).cache();

        Mono<Set<String>> assignedToUsersMono = userMono
                .flatMap(user -> {
                    String workspaceName = user.computeFirstName() + "'s apps";
                    return workspaceRepository.findByName(workspaceName);
                })
                .flatMapMany(workspace -> permissionGroupRepository.findAllById(workspace.getDefaultPermissionGroups()))
                .filter(permissionGroup -> permissionGroup.getName().startsWith(ADMINISTRATOR))
                .single()
                .map(permissionGroup -> permissionGroup.getAssignedToUserIds());

        StepVerifier.create(Mono.zip(userMono, assignedToUsersMono))
                .assertNext(tuple -> {
                    User user = tuple.getT1();
                    Set<String> workspaceAssignedToUsers = tuple.getT2();
                    assertThat(user).isNotNull();
                    assertThat(user.getId()).isNotNull();
                    assertThat(user.getEmail()).isEqualTo("dummy_admin@bizBrainz.com");
                    assertThat(user.getName()).isNullOrEmpty();
                    assertThat(user.getPolicies()).isNotEmpty();
                    assertThat(workspaceAssignedToUsers).contains(user.getId());
                })
                .verifyComplete();
    }

    @Test
    @WithMockBizbrainzUser
    public void createNewAdminValidWhenDisabled2() {
        User newUser = new User();
        newUser.setEmail("dummy2@bizBrainz.com");
        newUser.setPassword("admin-password");

        Mono<User> userMono = userService.create(newUser).cache();

        Mono<Set<String>> assignedToUsersMono = userMono
                .flatMap(user -> {
                    String workspaceName = user.computeFirstName() + "'s apps";
                    return workspaceRepository.findByName(workspaceName);
                })
                .flatMapMany(workspace -> permissionGroupRepository.findAllById(workspace.getDefaultPermissionGroups()))
                .filter(permissionGroup -> permissionGroup.getName().startsWith(ADMINISTRATOR))
                .single()
                .map(permissionGroup -> permissionGroup.getAssignedToUserIds());

        StepVerifier.create(Mono.zip(userMono, assignedToUsersMono))
                .assertNext(tuple -> {
                    User user = tuple.getT1();
                    Set<String> workspaceAssignedToUsers = tuple.getT2();
                    assertThat(user).isNotNull();
                    assertThat(user.getId()).isNotNull();
                    assertThat(user.getEmail()).isEqualTo("dummy2@bizBrainz.com");
                    assertThat(user.getName()).isNullOrEmpty();
                    assertThat(user.getPolicies()).isNotEmpty();

                    assertThat(workspaceAssignedToUsers).contains(user.getId());

                })
                .verifyComplete();
    }

    @Test
    @WithMockBizbrainzUser
    public void signUpViaFormLoginIfAlreadyInvited() {
        User newUser = new User();
        newUser.setEmail("alreadyInvited@alreadyInvited.com");
        newUser.setIsEnabled(false);

        userRepository.save(newUser).block();

        User signupUser = new User();
        signupUser.setEmail(newUser.getEmail());
        signupUser.setPassword("password");
        signupUser.setSource(LoginSource.FORM);

        Mono<User> userMono = userService.create(signupUser);

        StepVerifier.create(userMono)
                .assertNext(user -> {
                    assertThat(user.getEmail()).isEqualTo(newUser.getEmail());
                    assertThat(user.getSource()).isEqualTo(LoginSource.FORM);
                    assertThat(user.getIsEnabled()).isTrue();
                })
                .verifyComplete();
    }
}
