package com.bizBrainz.server.domains;

import com.bizBrainz.external.models.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Asset extends BaseDomain {

    public Asset(MediaType mediaType, byte[] data) {
        this(mediaType == null ? null : mediaType.toString(), data);
    }

    String contentType;

    byte[] data;

}
