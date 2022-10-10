package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.Theme;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomThemeRepositoryCE extends BizbrainzRepository<Theme> {
    Flux<Theme> getApplicationThemes(String applicationId, AclPermission aclPermission);
    Flux<Theme> getSystemThemes();
    Mono<Theme> getSystemThemeByName(String themeName);
    Mono<Boolean> archiveByApplicationId(String applicationId);
    Mono<Boolean> archiveDraftThemesById(String editModeThemeId, String publishedModeThemeId);
}
