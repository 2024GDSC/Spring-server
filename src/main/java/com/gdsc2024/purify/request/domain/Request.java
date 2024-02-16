package com.gdsc2024.purify.request.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gdsc2024.purify.baseTime.BaseTimeEntity;
import com.gdsc2024.purify.member.domain.Member;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import com.gdsc2024.purify.project.domain.Project;
import com.gdsc2024.purify.request.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 접근 x
@Table(name = "requests")
public class Request extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false)
    private Long requestId;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestStatus status;

    @ManyToOne
    @JsonBackReference
    private PinMap pinMap;

    @ManyToOne
    @JsonBackReference
    private Project project;

    @ManyToOne
    @JsonBackReference
    private Member member;


    @Builder
    public Request(Long requestId, String description, RequestStatus status) {
        this.requestId = requestId;
        this.description = description;
        this.status = status;
    }

    public void addProject(Project project) {
        this.project = project;
    }
    public void addPinMap(PinMap pinMap) {
        this.pinMap = pinMap;
    }
    public void addMember(Member member) {
        this.member = member;
    }

}

