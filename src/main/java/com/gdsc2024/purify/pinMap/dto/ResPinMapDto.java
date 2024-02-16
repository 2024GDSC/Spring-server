package com.gdsc2024.purify.pinMap.dto;

import com.gdsc2024.purify.project.domain.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResPinMapDto {
    private Long pinMapId;
    private String title;
    private String location;
    private Project project;

    @Builder
    public ResPinMapDto(Long pinMapId, String title, String location, Project project) {
        this.pinMapId = pinMapId;
        this.title = title;
        this.location = location;
        this.project = project;
    }
}
