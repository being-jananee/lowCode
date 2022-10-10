package com.bizBrainz.server.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPhotoChangedEvent {
    private String userId;
    private String photoAssetId;
}
