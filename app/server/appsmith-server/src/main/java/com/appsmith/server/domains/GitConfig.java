package com.bizBrainz.server.domains;

import com.bizBrainz.external.models.BizbrainzDomain;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class GitConfig implements BizbrainzDomain {

    String authorName;

    String authorEmail;

}
