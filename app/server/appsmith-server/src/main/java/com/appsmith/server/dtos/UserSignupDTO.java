package com.bizBrainz.server.dtos;

import com.bizBrainz.server.domains.User;
import lombok.Data;

@Data
public class UserSignupDTO {
    private User user;
    private String defaultWorkspaceId;
}
