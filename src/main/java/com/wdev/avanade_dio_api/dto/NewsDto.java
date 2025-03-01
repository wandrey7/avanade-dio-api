package com.wdev.avanade_dio_api.dto;

import com.wdev.avanade_dio_api.model.News;

public record NewsDto(
        Long id,
        String icon,
        String description
) {
    public static NewsDto fromEntity(News news) {
        if (news == null) return null;

        return new NewsDto(
                news.getId(),
                news.getIcon(),
                news.getDescription()
        );
    }

    public News toEntity() {
        News news = new News();
        news.setId(this.id);
        news.setIcon(this.icon);
        news.setDescription(this.description);
        return news;
    }
}