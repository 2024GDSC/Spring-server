package com.gdsc2024.purify.project.dto;

import com.gdsc2024.purify.project.enums.ProjectStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class ResProjectDto {
    private Long projectId;
    private String title;
    private String description;
    private ProjectStatus projectStatus;
    private Timestamp createdTime;
    private Boolean isMine;

    @Builder
    public ResProjectDto(Long projectId, String title, String description, ProjectStatus projectStatus, Timestamp createdTime, Boolean isMine) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.projectStatus = projectStatus;
        this.createdTime = createdTime;
        this.isMine = isMine;
    }
}
