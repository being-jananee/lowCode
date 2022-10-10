package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.dtos.InviteUsersDTO;
import com.bizBrainz.server.dtos.ResetUserPasswordDTO;
import com.bizBrainz.server.dtos.UserProfileDTO;
import com.bizBrainz.server.dtos.UserSignupDTO;
import com.bizBrainz.server.dtos.UserUpdateDTO;
import com.bizBrainz.server.services.CrudService;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserServiceCE extends CrudService<User, String> {

    Mono<User> findByEmail(String email);

    Mono<User> findByEmailAndTenantId(String email, String tenantId);

    Mono<User> switchCurrentWorkspace(String workspaceId);

    Mono<Boolean> forgotPasswordTokenGenerate(ResetUserPasswordDTO resetUserPasswordDTO);

    Mono<Boolean> verifyPasswordResetToken(String token);

    Mono<Boolean> resetPasswordAfterForgotPassword(String token, User user);

    Mono<UserSignupDTO> createUserAndSendEmail(User user, String originHeader);

    Mono<User> userCreate(User user, boolean isAdminUser);

    Mono<List<User>> inviteUsers(InviteUsersDTO inviteUsersDTO, String originHeader);

    Mono<User> updateCurrentUser(UserUpdateDTO updates, ServerWebExchange exchange);

    Map<String, String> getEmailParams(Workspace workspace, User inviterUser, String inviteUrl, boolean isNewUser);

    Mono<Boolean> isUsersEmpty();

    Mono<UserProfileDTO> buildUserProfileDTO(User user);

    Flux<User> getAllByEmails(Set<String> emails, AclPermission permission);
}
