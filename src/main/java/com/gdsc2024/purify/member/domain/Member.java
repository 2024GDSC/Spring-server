package com.gdsc2024.purify.member.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdsc2024.purify.baseTime.BaseTimeEntity;
import com.gdsc2024.purify.common.relativeEntity.MemberToPinMap;
import com.gdsc2024.purify.common.relativeEntity.MemberToProject;
import com.gdsc2024.purify.member.enums.MemberRole;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import com.gdsc2024.purify.project.domain.Project;
import com.gdsc2024.purify.request.domain.Request;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 접근 x
@Table(name = "members")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private MemberRole role;

//    @ManyToOne
//    @JsonBackReference
//    private Project project;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<MemberToPinMap> memberToPinMaps = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<MemberToProject> memberToProjects = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Request> requests = new ArrayList<>();

    @Builder
    public Member(Long memberId, String email, String name, String password, MemberRole role) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
