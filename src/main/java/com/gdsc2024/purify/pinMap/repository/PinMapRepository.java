package com.gdsc2024.purify.pinMap.repository;

import com.gdsc2024.purify.pinMap.domain.PinMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PinMapRepository extends JpaRepository<PinMap, Long> {
    List<PinMap> findPinMapByProject_ProjectId(Long projectId);

    List<PinMap> findPinMapByPinMapId(Long pinMapId);

}
