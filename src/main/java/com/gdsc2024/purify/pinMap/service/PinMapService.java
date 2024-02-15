package com.gdsc2024.purify.pinMap.service;

import com.gdsc2024.purify.pinMap.domain.PinMap;
import com.gdsc2024.purify.pinMap.dto.ReqPinMapCreateDto;
import com.gdsc2024.purify.pinMap.dto.ResPinMapDto;
import com.gdsc2024.purify.security.dto.AuthorizerDto;

import java.util.List;

public interface PinMapService {
    List<ResPinMapDto> listUpPinMaps(Long projectId);
    void createPinMap(ReqPinMapCreateDto reqPinMapCreateDto, AuthorizerDto authorizerDto);

    void transferPinMap(Long pinMapId, Long projectId);

    void simulatePinMap(Long pinMapId);

    PinMap getSimulation(Long pinMapId);
}
