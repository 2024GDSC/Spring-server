package com.gdsc2024.purify.pin.dto;

import com.gdsc2024.purify.pin.enums.PinTypes;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResPinDto {
    private Long pinId;
    private String x_location;
    private String y_location;
    private PinTypes pinType;
    private PinMap pinMap;

    @Builder
    public ResPinDto(Long pinId, String x_location, String y_location, PinTypes pinType, PinMap pinMap) {
        this.pinId = pinId;
        this.x_location = x_location;
        this.y_location = y_location;
        this.pinType = pinType;
        this.pinMap = pinMap;
    }
}
