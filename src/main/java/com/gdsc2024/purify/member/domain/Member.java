package com.gdsc2024.purify.member.domain;

import com.gdsc2024.purify.baseTime.BaseTimeEntity;
import com.gdsc2024.purify.member.enums.MemberRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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


    @Builder
    public Member(Long memberId, String email, String name, String password, MemberRole role) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
