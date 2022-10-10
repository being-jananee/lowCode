package com.bizBrainz.server.solutions.ce;

import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.events.UserChangedEvent;
import com.bizBrainz.server.events.UserPhotoChangedEvent;


public interface UserChangedHandlerCE {

    User publish(User user);

    void publish(String userId, String photoAssetId);

    void handle(UserChangedEvent event);

    void handle(UserPhotoChangedEvent event);

}
