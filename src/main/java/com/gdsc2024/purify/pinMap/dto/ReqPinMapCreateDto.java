package com.gdsc2024.purify.pinMap.dto;

import com.gdsc2024.purify.pinMap.domain.PinMap;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReqPinMapCreateDto {
    private String title;
    private String location;
    private List<String> sharedMemberList; // email 형식을 받아옵니다.

    @Builder
    public ReqPinMapCreateDto(String title, String location, List<String> sharedMemberList) {
        this.title = title;
        this.location = location;
        this.sharedMemberList = sharedMemberList;
    }

    public PinMap toEntity() {
        PinMap.PinMapBuilder pinMapBuilder = PinMap.builder()
                .title(this.title)
                .location(this.location);

        return pinMapBuilder.build();
    }
}
