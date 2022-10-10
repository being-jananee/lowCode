package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.external.models.ApiTemplate;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomApiTemplateRepository;

public interface ApiTemplateRepositoryCE extends BaseRepository<ApiTemplate, String>, CustomApiTemplateRepository {
}
