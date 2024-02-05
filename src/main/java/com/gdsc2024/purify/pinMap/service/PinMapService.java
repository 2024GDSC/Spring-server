package com.gdsc2024.purify.pinMap.service;

import com.gdsc2024.purify.pinMap.dto.ResPinMapDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PinMapService {
    List<ResPinMapDto> listUpPinMaps(Long projectId);
}
