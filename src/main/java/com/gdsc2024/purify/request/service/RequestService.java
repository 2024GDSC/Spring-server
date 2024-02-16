package com.gdsc2024.purify.request.service;

import com.gdsc2024.purify.request.dto.ReqRequestCreateDto;
import com.gdsc2024.purify.request.enums.RequestStatus;
import com.gdsc2024.purify.security.dto.AuthorizerDto;

public interface RequestService {
    void createRequest(ReqRequestCreateDto reqRequestCreateDto, AuthorizerDto authorizerDto);
}
