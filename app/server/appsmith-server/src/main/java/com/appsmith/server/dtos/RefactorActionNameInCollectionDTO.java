package com.bizBrainz.server.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefactorActionNameInCollectionDTO {
    RefactorActionNameDTO refactorAction;
    ActionCollectionDTO actionCollection;
}
