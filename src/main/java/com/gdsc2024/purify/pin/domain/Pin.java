package com.gdsc2024.purify.pin.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gdsc2024.purify.baseTime.BaseTimeEntity;
import com.gdsc2024.purify.member.enums.MemberRole;
import com.gdsc2024.purify.pin.enums.PinTypes;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 접근 x
@Table(name = "Pins")
public class Pin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pin_id", nullable = false)
    private Long pinId;

    @Enumerated(EnumType.STRING)
    @Column(name = "pin_type")
    private PinTypes pinType;

    @Column(name = "x_location")
    private String x_location;

    @Column(name = "y_location")
    private String y_location;

    @ManyToOne
    @JsonBackReference
    private PinMap pinMap;


    @Builder
    public Pin(Long pinId, PinTypes pinType, String x_location, String y_location, PinMap pinMap) {
        this.pinId = pinId;
        this.pinType = pinType;
        this.x_location = x_location;
        this.y_location = y_location;
        this.pinMap = pinMap;
    }
}
