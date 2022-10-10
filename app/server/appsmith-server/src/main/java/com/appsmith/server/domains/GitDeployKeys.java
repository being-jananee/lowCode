package com.bizBrainz.server.domains;

import com.bizBrainz.external.models.BizbrainzDomain;
import com.bizBrainz.external.models.BaseDomain;
import lombok.Data;

@Data
public class GitDeployKeys extends BaseDomain {
    String email;

    GitAuth gitAuth;
}
