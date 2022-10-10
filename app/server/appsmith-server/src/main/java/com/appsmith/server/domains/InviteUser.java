package com.bizBrainz.server.domains;

import com.bizBrainz.server.acl.BizbrainzRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class InviteUser extends User {

    String inviterUserId;

    String token;

    BizbrainzRole role;
}
