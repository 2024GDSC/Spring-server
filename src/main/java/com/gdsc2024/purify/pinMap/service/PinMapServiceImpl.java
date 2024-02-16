package com.gdsc2024.purify.pinMap.service;

import com.gdsc2024.purify.common.relativeEntity.MemberToPinMap;
import com.gdsc2024.purify.common.relativeEntity.repository.MemberToPinMapRepository;
import com.gdsc2024.purify.handler.CustomException;
import com.gdsc2024.purify.handler.StatusCode;
import com.gdsc2024.purify.member.domain.Member;
import com.gdsc2024.purify.member.enums.MemberRole;
import com.gdsc2024.purify.member.repository.MemberRepository;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import com.gdsc2024.purify.pinMap.dto.ReqPinMapCreateDto;
import com.gdsc2024.purify.pinMap.dto.ResPinMapDto;
import com.gdsc2024.purify.pinMap.repository.PinMapRepository;
import com.gdsc2024.purify.project.domain.Project;
import com.gdsc2024.purify.project.enums.ProjectStatus;
import com.gdsc2024.purify.project.repository.ProjectRepository;
import com.gdsc2024.purify.security.dto.AuthorizerDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PinMapServiceImpl implements PinMapService{
    private final PinMapRepository pinMapRepository;
    private final ProjectRepository projectRepository;
    private final MemberToPinMapRepository memberToPinMapRepository;
    private final MemberRepository memberRepository;


    @Override
    public void createPinMap(ReqPinMapCreateDto reqPinMapCreateDto, AuthorizerDto authorizerDto) {
        if (!authorizerDto.getRole().equals(MemberRole.SIMULATOR.name())) {throw new CustomException(StatusCode.FORBIDDEN);}
        Member member = memberRepository.findById(authorizerDto.getMemberId()).orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND));
        if (!member.getRole().name().equals(authorizerDto.getRole())) {throw new CustomException(StatusCode.DISABLED_AUTH_TOKEN);}
        PinMap pinMap = reqPinMapCreateDto.toEntity();
        pinMapRepository.save(pinMap);

        MemberToPinMap memberToPinMap = MemberToPinMap
                .builder()
                .member(member)
                .pinMap(pinMap)
                .build();

        reqPinMapCreateDto.getSharedMemberList().stream()
                .filter(element -> element != null)
                .forEach(
                        (memberEmail) -> {
                            if (memberEmail.equals(member.getEmail())) return;
                            Member emailedMember = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND));
                            MemberToPinMap sharedMemberToPinMap = MemberToPinMap
                                    .builder()
                                    .member(emailedMember)
                                    .pinMap(pinMap)
                                    .build();
                            memberToPinMapRepository.save(sharedMemberToPinMap);
                        }
                );
        memberToPinMapRepository.save(memberToPinMap);
    }

    @Override
    public void simulatePinMap(Long pinMapIdHash) {

    }

    @Override
    public PinMap getSimulation(Long pinMapId) {
        return null;
    }

    @Override
    @Transactional
    public void transferPinMap(Long pinMapId, Long projectId) {
        PinMap pinMap = pinMapRepository.findById(pinMapId).orElseThrow(() -> {throw new CustomException(StatusCode.NOT_FOUND);});
        Project project = projectRepository.findById(projectId).orElseThrow(() -> {throw new CustomException(StatusCode.NOT_FOUND);});

        if (!project.getStatus().equals(ProjectStatus.OPEN) || !project.getStatus().equals(ProjectStatus.PRIVATE)) {
            throw new CustomException(StatusCode.FAILED_REQUEST);
        }

        PinMap updatePinMap = PinMap.builder()
                .pinMapId(pinMap.getPinMapId())
                .project(project)
                .location(pinMap.getLocation())
                .build();

        pinMapRepository.save(updatePinMap);
    }

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
