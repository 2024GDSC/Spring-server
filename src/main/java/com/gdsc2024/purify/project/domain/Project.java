package com.gdsc2024.purify.project.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdsc2024.purify.baseTime.BaseTimeEntity;
import com.gdsc2024.purify.common.relativeEntity.MemberToProject;
import com.gdsc2024.purify.member.domain.Member;
import com.gdsc2024.purify.member.enums.MemberRole;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import com.gdsc2024.purify.project.dto.ResProjectDto;
import com.gdsc2024.purify.project.enums.ProjectStatus;
import com.gdsc2024.purify.request.domain.Request;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 접근 x
@Table(name = "projects")
public class Project extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<Member> sharedMemberId = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PinMap> pinMaps = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<MemberToProject> memberToProjects = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private List<Request> requests = new ArrayList<>();

    @Builder
    public Project(Long projectId, String title, String description, ProjectStatus status) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public ResProjectDto toDto() {
        return ResProjectDto.builder()
                .projectId(this.projectId)
                .title(this.title)
                .description(this.description)
                .projectStatus(this.status)
                .createdTime(this.getCreateDate())
                .build();
    }

}
