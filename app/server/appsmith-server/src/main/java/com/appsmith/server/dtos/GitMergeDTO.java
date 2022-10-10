package com.bizBrainz.server.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitMergeDTO {

    String sourceBranch;

    String destinationBranch;
}
