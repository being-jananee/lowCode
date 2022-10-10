package com.bizBrainz.server.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefactorNameDTO {
    String pageId;
    String layoutId;
    String oldName;
    String newName;
}
