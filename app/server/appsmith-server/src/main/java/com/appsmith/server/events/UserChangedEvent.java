package com.bizBrainz.server.events;

import com.bizBrainz.server.domains.User;
import lombok.Data;

@Data
public class UserChangedEvent {

    private final User user;

}
