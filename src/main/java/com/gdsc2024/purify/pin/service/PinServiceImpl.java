package com.gdsc2024.purify.pin.service;

import com.gdsc2024.purify.handler.CustomException;
import com.gdsc2024.purify.handler.StatusCode;
import com.gdsc2024.purify.member.enums.MemberRole;
import com.gdsc2024.purify.pin.Repository.PinRepository;
import com.gdsc2024.purify.pin.domain.Pin;
import com.gdsc2024.purify.pin.dto.ReqPinCreateDto;
import com.gdsc2024.purify.pin.dto.ResPinDto;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import com.gdsc2024.purify.pinMap.dto.ReqPinMapCreateDto;
import com.gdsc2024.purify.pinMap.repository.PinMapRepository;
import com.gdsc2024.purify.security.dto.AuthorizerDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PinServiceImpl implements PinService {
    private final PinRepository pinRepository;
    private final PinMapRepository pinMapRepository;
    @Override
    @Transactional
    public Pin createPin(ReqPinCreateDto reqPinCreateDto, AuthorizerDto authorizerDto) {
        if (!authorizerDto.getRole().equals(MemberRole.SIMULATOR.name())) throw new CustomException(StatusCode.FORBIDDEN);

        Pin pin = reqPinCreateDto.toEntity();
        return pinRepository.save(pin);
    }

    @Override
    public Pin updatePin(Long pinId, ReqPinCreateDto reqPinCreateDto, AuthorizerDto authorizerDto) {
        if (!authorizerDto.getRole().equals(MemberRole.SIMULATOR.name())) throw new CustomException(StatusCode.FORBIDDEN);

        Pin existingPin = pinRepository.findById(pinId)
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND));
        Pin updatedPin = Pin.builder()
                .pinId(existingPin.getPinId())
                .pinType(reqPinCreateDto.getPinType())
                .x_location(reqPinCreateDto.getX_location())
                .y_location(reqPinCreateDto.getY_location())
                .pinMap(existingPin.getPinMap())
                .build();
        return pinRepository.save(updatedPin);
    }

    @Override
    public List<ResPinDto> listUpPins(Long pinMapId) {
        List<ResPinDto> resPinDtos = new ArrayList<>();
        pinRepository.findPinByPinMap_PinMapId(pinMapId)
                .stream()
                .forEach((pin -> {
                    resPinDtos.add(
                            ResPinDto.builder()
                                    .pinId(pin.getPinId())
                                    .x_location(pin.getX_location())
                                    .y_location(pin.getY_location())
                                    .pinType(pin.getPinType())
                                    .build()
                    );
                }));

        return resPinDtos;
    }


}
