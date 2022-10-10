package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Notification;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomNotificationRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface NotificationRepositoryCE extends BaseRepository<Notification, String>, CustomNotificationRepository {

    Flux<Notification> findByForUsernameAndCreatedAtBefore(String userId, Instant instant, Pageable pageable);

    Mono<Long> countByForUsername(String userId);

    Mono<Long> countByForUsernameAndIsReadIsFalse(String userId);
}
