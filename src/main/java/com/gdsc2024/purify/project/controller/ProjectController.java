package com.gdsc2024.purify.project.controller;

import com.gdsc2024.purify.common.dto.Message;
import com.gdsc2024.purify.handler.StatusCode;
import com.gdsc2024.purify.project.dto.ReqProjectCreateDto;
import com.gdsc2024.purify.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.gdsc2024.purify.security.JwtInfoExtractor.getAuthorizer;

@RequiredArgsConstructor
@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<Message> generateProject(@RequestBody ReqProjectCreateDto reqProjectCreateDto) {
        projectService.createProject(reqProjectCreateDto, getAuthorizer());
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

//    @GetMapping("/mark/suiteroom")
//    public ResponseEntity<Message> listUpMarkOfSuiteRoom() {
//        return ResponseEntity.ok(new Message(StatusCode.OK, markService.getMarkOfSuiteRoom(getSuiteAuthorizer().getMemberId())));
//    }
}
