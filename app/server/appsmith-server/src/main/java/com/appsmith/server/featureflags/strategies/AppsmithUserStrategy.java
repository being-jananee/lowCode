package com.bizBrainz.server.featureflags.strategies;

import com.bizBrainz.server.constants.FieldName;
import com.bizBrainz.server.domains.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.ff4j.core.FeatureStore;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.strategy.AbstractFlipStrategy;

/**
 * This strategy enables a given feature for Bizbrainz users only. Useful when features are under development and not
 * ready for prime-time
 */
@Slf4j
public class BizbrainzUserStrategy extends AbstractFlipStrategy {

    @Override
    public boolean evaluate(String featureName, FeatureStore store, FlippingExecutionContext executionContext) {
        User user = (User) executionContext.getValue(FieldName.USER, true);

        return StringUtils.endsWith(user.getEmail(), "@bizBrainz.com");
    }
}