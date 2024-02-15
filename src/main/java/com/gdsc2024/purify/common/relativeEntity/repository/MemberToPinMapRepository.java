package com.gdsc2024.purify.common.relativeEntity.repository;

import com.gdsc2024.purify.common.relativeEntity.MemberToPinMap;
import com.gdsc2024.purify.common.relativeEntity.MemberToProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberToPinMapRepository extends JpaRepository<MemberToPinMap, Long> {
    List<MemberToPinMap> findByMember_MemberId(Long memberId);

    List<MemberToPinMap> findByMember_MemberIdAndPinMap_PinMapId(Long memberId, Long projectId);
}
