package com.gdsc2024.purify.pin.dto;

import com.gdsc2024.purify.pin.domain.Pin;
import com.gdsc2024.purify.pin.enums.PinTypes;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReqPinCreateDto {

    private String x_location;
    private String y_location;
    private PinTypes pinType;
    private PinMap pinMap;

    @Builder
    public ReqPinCreateDto(String x_location, String y_location, PinTypes pinType, PinMap pinMap) {
        this.x_location = x_location;
        this.y_location = y_location;
        this.pinType = pinType;
        this.pinMap = pinMap;
    }

    public Pin toEntity() {
        Pin.PinBuilder pinBuilder = Pin.builder()
                .x_location(this.x_location)
                .y_location(this.y_location)
                .pinType(this.pinType);

        return pinBuilder.build();
    }

}
