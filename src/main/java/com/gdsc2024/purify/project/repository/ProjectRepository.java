package com.gdsc2024.purify.project.repository;

import com.gdsc2024.purify.project.domain.Project;
import com.gdsc2024.purify.project.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByTitle(String title);
    List<Project> findProjectByStatus(ProjectStatus projectStatus);
}
