package com.gdsc2024.purify.pin.Repository;

import com.gdsc2024.purify.pin.domain.Pin;
import com.gdsc2024.purify.pinMap.domain.PinMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {
    List<Pin> findPinByPinMap_PinMapId(Long pinMapId);
}
