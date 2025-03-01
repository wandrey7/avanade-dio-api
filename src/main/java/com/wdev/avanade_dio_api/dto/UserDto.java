package com.wdev.avanade_dio_api.dto;

import com.wdev.avanade_dio_api.model.User;

import java.util.List;
import java.util.stream.Collectors;

public record UserDto(
        Long id,
        String name,
        AccountDto account,
        CardDto card,
        List<FeatureDto> features,
        List<NewsDto> news
) {
    public static UserDto fromEntity(User user) {
        if (user == null) return null;

        return new UserDto(
                user.getId(),
                user.getName(),
                AccountDto.fromEntity(user.getAccount()),
                CardDto.fromEntity(user.getCard()),
                user.getFeatures() != null ? user.getFeatures().stream()
                        .map(FeatureDto::fromEntity)
                        .toList() : null,
                user.getNews() != null ? user.getNews().stream()
                        .map(NewsDto::fromEntity)
                        .toList() : null
        );
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);

        if (this.account != null) {
            user.setAccount(this.account.toEntity());
        }

        if (this.card != null) {
            user.setCard(this.card.toEntity());
        }

        if (this.features != null) {
            user.setFeatures(this.features.stream()
                    .map(FeatureDto::toEntity)
                    .collect(Collectors.toList()));
        }

        if (this.news != null) {
            user.setNews(this.news.stream()
                    .map(NewsDto::toEntity)
                    .collect(Collectors.toList()));
        }

        return user;
    }
}