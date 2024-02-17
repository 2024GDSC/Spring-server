package com.gdsc2024.purify.pin.service;

import com.gdsc2024.purify.pin.domain.Pin;
import com.gdsc2024.purify.pin.dto.ReqPinCreateDto;
import com.gdsc2024.purify.pin.dto.ResPinDto;
import com.gdsc2024.purify.security.dto.AuthorizerDto;

import java.util.List;

public interface PinService {
    Pin createPin(ReqPinCreateDto reqPinCreateDto, AuthorizerDto authorizerDto);
    Pin updatePin(Long pinId, ReqPinCreateDto reqPinCreateDto, AuthorizerDto authorizerDto);
    List<ResPinDto> listUpPins(Long pinMapId);
}
