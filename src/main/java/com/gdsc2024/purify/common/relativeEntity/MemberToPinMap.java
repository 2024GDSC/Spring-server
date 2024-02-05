package com.gdsc2024.purify.common.relativeEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gdsc2024.purify.member.domain.Member;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 접근 x
@Table(name = "memberToPinMaps")
public class MemberToPinMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_to_pin_map_id", nullable = false)
    private Long memberToPinMapId;

    @ManyToOne
    @JsonBackReference
    private Member member;

    @ManyToOne
    @JsonBackReference
    private PinMap pinMap;

}
