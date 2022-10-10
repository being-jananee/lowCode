package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Theme;
import com.bizBrainz.server.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepositoryCE extends BaseRepository<Theme, String>, CustomThemeRepositoryCE {

}
