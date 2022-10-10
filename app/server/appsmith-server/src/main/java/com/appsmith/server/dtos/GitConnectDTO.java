package com.bizBrainz.server.dtos;

import com.bizBrainz.server.domains.GitProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitConnectDTO {

    String remoteUrl;

    GitProfile gitProfile;
}
