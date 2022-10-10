package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.NotificationRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends NotificationRepositoryCE, CustomNotificationRepository {

}
