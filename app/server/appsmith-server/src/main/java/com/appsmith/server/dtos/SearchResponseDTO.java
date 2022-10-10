package com.bizBrainz.server.dtos;

import com.bizBrainz.external.models.ApiTemplate;
import com.bizBrainz.external.models.Provider;
import com.bizBrainz.server.domains.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponseDTO {
    List<Provider> providers;
    List<ApiTemplate> apiTemplates;
    List<Action> actions;
}
