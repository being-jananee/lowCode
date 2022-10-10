package com.bizBrainz.server.domains;

import com.bizBrainz.external.models.BaseDomain;
import com.bizBrainz.server.constants.Url;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Document
public class Workspace extends BaseDomain {

    private String domain;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String website;

    private String email;

    private Set<WorkspacePlugin> plugins;

    private String slug;

    //Organizations migrated to workspaces, kept the field as deprecated to support the old migration
    @Deprecated
    private Boolean isAutoGeneratedOrganization;
    
    private Boolean isAutoGeneratedWorkspace;

    @JsonIgnore
    @Deprecated
    private List<UserRole> userRoles;

    @JsonIgnore
    private String logoAssetId;

    private String tenantId;

    @JsonIgnore
    private Set<String> defaultPermissionGroups;

    public String makeSlug() {
        return toSlug(name);
    }

    public static String toSlug(String text) {
        return text == null ? null : text.replaceAll("[^\\w\\d]+", "-").toLowerCase();
    }

    public String getLogoUrl() {
        return Url.ASSET_URL + "/" + logoAssetId;
    }

}
