package com.gdsc2024.purify.project.service;

import com.gdsc2024.purify.common.relativeEntity.MemberToProject;
import com.gdsc2024.purify.common.relativeEntity.repository.MemberToProjectRepository;
import com.gdsc2024.purify.handler.CustomException;
import com.gdsc2024.purify.handler.StatusCode;
import com.gdsc2024.purify.member.domain.Member;
import com.gdsc2024.purify.member.enums.MemberRole;
import com.gdsc2024.purify.member.repository.MemberRepository;
import com.gdsc2024.purify.project.domain.Project;
import com.gdsc2024.purify.project.dto.ReqProjectCreateDto;
import com.gdsc2024.purify.project.dto.ResProjectDto;
import com.gdsc2024.purify.project.enums.ProjectStatus;
import com.gdsc2024.purify.project.repository.ProjectRepository;
import com.gdsc2024.purify.security.dto.AuthorizerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private final MemberToProjectRepository memberToProjectRepository;
    private final MemberRepository memberRepository;
    @Override
    public void createProject(ReqProjectCreateDto reqProjectCreateDto, AuthorizerDto authorizerDto) {
        if (!authorizerDto.getRole().equals(MemberRole.SIMULATOR.name())) throw new CustomException(StatusCode.FORBIDDEN);
        projectRepository.findProjectByTitle(reqProjectCreateDto.getTitle()).ifPresent((p)->{throw new CustomException(StatusCode.FORBIDDEN_CREATING);});
        Member member = memberRepository.findById(authorizerDto.getMemberId()).orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND));

        if (!member.getRole().name().equals(authorizerDto.getRole())) throw new CustomException(StatusCode.DISABLED_AUTH_TOKEN);
        Project project = reqProjectCreateDto.toEntity();
        projectRepository.save(project);

        MemberToProject memberToProject = MemberToProject
                .builder()
                .member(member)
                .project(project)
                .build();

        reqProjectCreateDto.getSharedMemberList().stream()
                .filter(element -> element != null)
                .forEach(
                (memberEmail) -> {
                    if (memberEmail.equals(member.getEmail())) return;
                    Member emailedMember = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND));
                    MemberToProject sharedMemberToProject = MemberToProject
                            .builder()
                            .member(emailedMember)
                            .project(project)
                            .build();
                    memberToProjectRepository.save(sharedMemberToProject);
                }
        );
        memberToProjectRepository.save(memberToProject);
    }

    @Override
    public List<ResProjectDto> getProjects(AuthorizerDto authorizerDto) {
        Member member = memberRepository.findById(authorizerDto.getMemberId()).orElseThrow(() -> {throw new CustomException(StatusCode.NOT_FOUND);});
        if (!member.getRole().name().equals(authorizerDto.getRole())) throw new CustomException(StatusCode.DISABLED_AUTH_TOKEN);
        return getAuthorizedProjects(authorizerDto);
    }

    private List<ResProjectDto> getAuthorizedProjects(AuthorizerDto authorizerDto) {
        List<ResProjectDto> resProjectDtos = new ArrayList<>();
        if (authorizerDto.getRole().equals(MemberRole.ADMIN.name())) {
            projectRepository.findAll().stream()
                    .forEach(p -> resProjectDtos.add(p.toDto()));
        }
        if (authorizerDto.getRole().equals(MemberRole.VIEWER.name()) ||
                authorizerDto.getRole().equals(MemberRole.SIMULATOR.name())) {
            memberToProjectRepository.findByMember_MemberId(authorizerDto.getMemberId()).stream()
                    .filter(mtp -> mtp.getProject().getStatus() == ProjectStatus.PRIVATE)
                    .forEach(mtp -> resProjectDtos.add(mtp.getProject().toDto()));
        }
        if (authorizerDto.getRole().equals(MemberRole.SIMULATOR.name())) {
            projectRepository.findProjectByStatus(ProjectStatus.OPEN).stream()
                    .forEach(p -> resProjectDtos.add(p.toDto()));
        }
        return resProjectDtos;
    }
}
