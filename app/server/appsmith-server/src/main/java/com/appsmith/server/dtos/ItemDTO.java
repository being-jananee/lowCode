package com.bizBrainz.server.dtos;

import com.bizBrainz.external.models.ApiTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {
    ItemType type;
    ApiTemplate item;
}
