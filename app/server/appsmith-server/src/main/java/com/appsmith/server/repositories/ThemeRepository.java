package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.ThemeRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends ThemeRepositoryCE, CustomThemeRepository {
}
