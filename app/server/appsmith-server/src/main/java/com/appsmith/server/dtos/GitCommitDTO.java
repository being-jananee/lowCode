package com.bizBrainz.server.dtos;

import lombok.Data;

@Data
public class GitCommitDTO {

    String commitMessage;

    String commitHeader;

    Boolean doPush;
}
