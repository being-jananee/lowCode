package com.bizBrainz.server.dtos;

import com.bizBrainz.server.domains.LoginSource;
import com.bizBrainz.server.domains.UserState;
import lombok.Data;

@Data
public class UserSignupRequestDTO {

    private String email;

    private String name;

    private LoginSource source = LoginSource.FORM;

    private UserState state = UserState.ACTIVATED;

    private boolean isEnabled = true;

    private String password;

    private String role;

    private String useCase;

    private boolean allowCollectingAnonymousData;

    private boolean signupForNewsletter;

}
