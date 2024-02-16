package com.gdsc2024.purify.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ProjectStatus {
    OPEN, // 모두가 열람 가능
    PRIVATE, // 공유 리스트만 열람 가능
    PROHIBITION, // 관리자가 블락을 건 상태
    DELETE_REQUEST, // 삭제 요청
    UPDATE_REQUEST, // 수정 요청
    TERMINATION_REQUEST, // 종료 요청
    TERMINATED; // 종료된 프로젝트
}
