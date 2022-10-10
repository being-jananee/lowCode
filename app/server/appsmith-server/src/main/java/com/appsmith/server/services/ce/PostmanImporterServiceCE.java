package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.models.TemplateCollection;

import java.util.List;

public interface PostmanImporterServiceCE extends ApiImporterCE {

    TemplateCollection importPostmanCollection(Object input);

    List<TemplateCollection> fetchPostmanCollections();

    TemplateCollection deletePostmanCollection(String id);
}
