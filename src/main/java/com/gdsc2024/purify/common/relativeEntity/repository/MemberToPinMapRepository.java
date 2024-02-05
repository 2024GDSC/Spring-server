package com.gdsc2024.purify.common.relativeEntity.repository;

import com.gdsc2024.purify.common.relativeEntity.MemberToPinMap;
import com.gdsc2024.purify.common.relativeEntity.MemberToProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberToPinMapRepository extends JpaRepository<MemberToProject, Long> {
    List<MemberToPinMap> findMemberToProjectByMember_MemberIdAndAndProject_ProjectId(Long memberId, Long projectId);
}
