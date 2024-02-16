package com.gdsc2024.purify.project.dto;

import com.gdsc2024.purify.project.domain.Project;
import com.gdsc2024.purify.project.enums.ProjectStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReqProjectCreateDto {
    private String title;
    private String description;
    private Boolean isOpen;
    private List<String> sharedMemberList; // email 형식을 받아옵니다.

    @Builder
    public ReqProjectCreateDto(String title, String description, Boolean isOpen, List<String> sharedMemberList) {
        this.title = title;
        this.description = description;
        this.isOpen = isOpen;
        this.sharedMemberList = sharedMemberList;
    }

    public Project toEntity() {
        Project.ProjectBuilder projectBuilder = Project.builder()
                .title(this.title)
                .description(this.description);

        if (this.isOpen) {
            projectBuilder.status(ProjectStatus.OPEN);
        } else {
            projectBuilder.status(ProjectStatus.PRIVATE);
        }

        return projectBuilder.build();
    }
}
