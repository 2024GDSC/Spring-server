package com.gdsc2024.purify.pinMap.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdsc2024.purify.baseTime.BaseTimeEntity;
import com.gdsc2024.purify.common.relativeEntity.MemberToPinMap;
import com.gdsc2024.purify.member.enums.MemberRole;
import com.gdsc2024.purify.pin.domain.Pin;
import com.gdsc2024.purify.pinMap.dto.ResPinMapDto;
import com.gdsc2024.purify.project.domain.Project;
import com.gdsc2024.purify.project.dto.ResProjectDto;
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
@Table(name = "pinMaps")
public class PinMap extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pin_map_id", nullable = false)
    private Long pinMapId;

    @Column(name = "title")
    private String title;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "pinMap", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pin> pins = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private Project project;

    @OneToMany(mappedBy = "pinMap")
    @JsonManagedReference
    private List<MemberToPinMap> memberToPinMaps = new ArrayList<>();

    @OneToMany(mappedBy = "pinMap")
    @JsonManagedReference
    private List<Request> requests = new ArrayList<>();

    @Builder
    public PinMap(Long pinMapId, String title, String location, Project project) {
        this.pinMapId = pinMapId;
        this.title = title;
        this.location = location;
        this.project = project;
    }

    public ResPinMapDto toDto() {
        return ResPinMapDto.builder()
                .pinMapId(this.pinMapId)
                .title(this.title)
                .location(this.location)
                .project(this.project)
                .build();
    }
}
