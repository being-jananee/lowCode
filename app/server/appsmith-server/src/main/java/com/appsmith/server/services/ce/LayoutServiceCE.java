package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.domains.Layout;
import reactor.core.publisher.Mono;

public interface LayoutServiceCE {

    Mono<Layout> createLayout(String pageId, Layout layout);

    Mono<Layout> createLayout(String defaultPageId, Layout layout, String branchName);

    Mono<Layout> getLayout(String pageId, String layoutId, Boolean viewMode);

    Mono<Layout> getLayout(String defaultPageId, String layoutId, Boolean viewMode, String branchName);

}
