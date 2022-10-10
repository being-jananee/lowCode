package com.bizBrainz.server.dtos;

import com.bizBrainz.external.dtos.MergeStatusDTO;
import com.bizBrainz.server.domains.Application;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitPullDTO {

    Application application;

    MergeStatusDTO mergeStatus;
}
