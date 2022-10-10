package com.bizBrainz.server.dtos;

import com.bizBrainz.external.models.DefaultResources;
import com.bizBrainz.external.models.JSValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ActionCollectionViewDTO {
    String id;
    String name;
    String pageId;
    String applicationId;
    List<JSValue> variables;
    List<ActionDTO> actions;
    String body;
    DefaultResources defaultResources;
}
