package com.gdsc2024.purify.common.relativeEntity.repository;

import com.gdsc2024.purify.common.relativeEntity.MemberToProject;
import com.gdsc2024.purify.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberToProjectRepository extends JpaRepository<MemberToProject, Long> {
}
