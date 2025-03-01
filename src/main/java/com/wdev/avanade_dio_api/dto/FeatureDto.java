package com.wdev.avanade_dio_api.dto;

import com.wdev.avanade_dio_api.model.Feature;

public record FeatureDto(
        Long id,
        String icon,
        String description
) {
    public static FeatureDto fromEntity(Feature feature) {
        if (feature == null) return null;

        return new FeatureDto(
                feature.getId(),
                feature.getIcon(),
                feature.getDescription()
        );
    }

    public Feature toEntity() {
        Feature feature = new Feature();
        feature.setId(this.id);
        feature.setIcon(this.icon);
        feature.setDescription(this.description);
        return feature;
    }
}