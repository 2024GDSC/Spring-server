package com.gdsc2024.purify.request.service;

import com.gdsc2024.purify.handler.CustomException;
import com.gdsc2024.purify.handler.StatusCode;
import com.gdsc2024.purify.member.domain.Member;
import com.gdsc2024.purify.member.enums.MemberRole;
import com.gdsc2024.purify.member.repository.MemberRepository;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import com.gdsc2024.purify.pinMap.repository.PinMapRepository;
import com.gdsc2024.purify.project.domain.Project;
import com.gdsc2024.purify.project.repository.ProjectRepository;
import com.gdsc2024.purify.request.domain.Request;
import com.gdsc2024.purify.request.dto.ReqRequestCreateDto;
import com.gdsc2024.purify.request.enums.RequestStatus;
import com.gdsc2024.purify.request.repository.RequestRepository;
import com.gdsc2024.purify.security.dto.AuthorizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService{
    private final RequestRepository requestRepository;
    private final PinMapRepository pinMapRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    @Override
    public void createRequest(ReqRequestCreateDto reqRequestCreateDto, AuthorizerDto authorizerDto) {
        if (!authorizerDto.getRole().equals(MemberRole.SIMULATOR.name())) throw new CustomException(StatusCode.FORBIDDEN);
        Member member = memberRepository.findById(authorizerDto.getMemberId()).orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND));

        if (!member.getRole().name().equals(authorizerDto.getRole())) throw new CustomException(StatusCode.DISABLED_AUTH_TOKEN);

        RequestStatus requestStatus = RequestStatus.valueOf(reqRequestCreateDto.getRequestStatus().toString().toUpperCase());
        Request request = reqRequestCreateDto.toEntity(requestStatus);
        request.addMember(member);

        Project project = projectRepository.findProjectByProjectId(reqRequestCreateDto.getProjectId()).orElseThrow(() -> {throw new CustomException(StatusCode.NOT_FOUND);});

        if(reqRequestCreateDto.getPinMapId() == -1) request.addProject(project);
        if(reqRequestCreateDto.getPinMapId() != -1) {
            PinMap pinMap = pinMapRepository.findPinMapByPinMapId(reqRequestCreateDto.getPinMapId()).orElseThrow(() -> {throw new CustomException(StatusCode.NOT_FOUND);});
            request.addPinMap(pinMap);
        }
        System.out.println(request.getStatus().getClass());
        requestRepository.save(request);
    }
}
