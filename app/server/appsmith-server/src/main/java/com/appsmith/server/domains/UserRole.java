package com.bizBrainz.server.domains;

import com.bizBrainz.server.acl.BizbrainzRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Deprecated
public class UserRole {
    @JsonIgnore
    String userId;

    String username;

    String name;

    String roleName;

    @JsonIgnore
    BizbrainzRole role;
}
