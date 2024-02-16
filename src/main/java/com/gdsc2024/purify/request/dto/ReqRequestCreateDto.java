package com.gdsc2024.purify.request.dto;

import com.gdsc2024.purify.request.domain.Request;
import com.gdsc2024.purify.request.enums.RequestStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReqRequestCreateDto {
    private Long projectId;
    private Long pinMapId;
    private String description;
    private RequestStatus requestStatus;

    @Builder
    public ReqRequestCreateDto(Long projectId, Long pinMapId, String description, RequestStatus requestStatus) {
        this.projectId = projectId;
        this.pinMapId = pinMapId;
        this.description = description;
        this.requestStatus = requestStatus;
    }

    public Request toEntity(RequestStatus requestStatus) {
        return Request.builder()
                .description(this.description)
                .status(requestStatus)
                .build();
    }
}
