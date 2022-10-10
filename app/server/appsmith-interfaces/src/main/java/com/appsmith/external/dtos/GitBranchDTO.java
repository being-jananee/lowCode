package com.bizBrainz.external.dtos;

import lombok.Data;

@Data
public class GitBranchDTO {
    String branchName;

    boolean isDefault;

    boolean createdFromLocal;
}
