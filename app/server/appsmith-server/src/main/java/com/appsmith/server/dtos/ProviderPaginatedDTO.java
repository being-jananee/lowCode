package com.bizBrainz.server.dtos;

import com.bizBrainz.external.models.Provider;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProviderPaginatedDTO {
    List<Provider> providers;
    Long total;
}
