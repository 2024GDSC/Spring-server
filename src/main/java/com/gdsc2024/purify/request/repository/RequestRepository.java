package com.gdsc2024.purify.request.repository;

import com.gdsc2024.purify.request.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
