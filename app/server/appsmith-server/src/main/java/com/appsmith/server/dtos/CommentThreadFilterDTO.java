package com.bizBrainz.server.dtos;

import com.bizBrainz.server.domains.ApplicationMode;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentThreadFilterDTO {
    @NotNull
    private String applicationId;
    private Boolean resolved;
    private ApplicationMode mode;
}
