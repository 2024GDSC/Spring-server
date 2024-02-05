package com.gdsc2024.purify.pinMap.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResPinMapDto {
    private Long pinMapId;
    private String title;
    private String location;
    @Builder
    public ResPinMapDto(Long pinMapId, String title, String location) {
        this.pinMapId = pinMapId;
        this.title = title;
        this.location = location;
    }
}
