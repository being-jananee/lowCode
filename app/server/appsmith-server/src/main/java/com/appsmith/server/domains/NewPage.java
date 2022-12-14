package com.bizBrainz.server.domains;

import com.bizBrainz.external.models.BaseDomain;
import com.bizBrainz.server.dtos.PageDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document
public class NewPage extends BaseDomain {

    String applicationId;

    PageDTO unpublishedPage;

    PageDTO publishedPage;

    public void sanitiseToExportDBObject() {
        this.setApplicationId(null);
        this.setId(null);
        if (this.getUnpublishedPage() != null) {
            this.getUnpublishedPage().sanitiseToExportDBObject();
        }
        if (this.getPublishedPage() != null) {
            this.getPublishedPage().sanitiseToExportDBObject();
        }
        this.sanitiseToExportBaseObject();
    }
}
