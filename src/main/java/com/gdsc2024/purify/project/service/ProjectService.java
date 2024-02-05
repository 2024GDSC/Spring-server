package com.gdsc2024.purify.project.service;

import com.gdsc2024.purify.project.dto.ReqProjectCreateDto;
import com.gdsc2024.purify.security.dto.AuthorizerDto;

public interface ProjectService {
    void createProject(ReqProjectCreateDto reqProjectCreateDto, AuthorizerDto authorizerDto);
}
