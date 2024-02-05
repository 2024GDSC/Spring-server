package com.gdsc2024.purify.pinMap.service;

import com.gdsc2024.purify.common.relativeEntity.MemberToPinMap;
import com.gdsc2024.purify.common.relativeEntity.repository.MemberToPinMapRepository;
import com.gdsc2024.purify.pinMap.dto.ResPinMapDto;
import com.gdsc2024.purify.pinMap.repository.PinMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PinMapServiceImpl implements PinMapService{
    private final PinMapRepository pinMapRepository;
    private final MemberToPinMapRepository memberToPinMapRepository;

    @Override
    public List<ResPinMapDto> listUpPinMaps(Long projectId) {
        List<ResPinMapDto> resPinMapDtos = new ArrayList<>();
        pinMapRepository.findPinMapByProject_ProjectId(projectId)
            .stream()
                .forEach((pinMap -> {
                    resPinMapDtos.add(
                        ResPinMapDto.builder()
                            .pinMapId(pinMap.getPinMapId())
                            .title(pinMap.getTitle())
                            .location(pinMap.getLocation())
                            .build()
                    );
                }));

        return resPinMapDtos;
    }
}
