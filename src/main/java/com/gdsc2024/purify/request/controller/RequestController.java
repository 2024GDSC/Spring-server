package com.gdsc2024.purify.request.controller;

import com.gdsc2024.purify.common.dto.Message;
import com.gdsc2024.purify.handler.StatusCode;
import com.gdsc2024.purify.project.dto.ReqProjectCreateDto;
import com.gdsc2024.purify.request.dto.ReqRequestCreateDto;
import com.gdsc2024.purify.request.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.gdsc2024.purify.security.JwtInfoExtractor.getAuthorizer;

@RequiredArgsConstructor
@RestController
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

//    @GetMapping("/")
//    public ResponseEntity<Message> listUpProject() {
//        return ResponseEntity.ok(new Message(StatusCode.OK, projectService.getProjects(getAuthorizer())));
//    }
    @PostMapping("/create")
    public ResponseEntity<Message> generateRequest(@RequestBody ReqRequestCreateDto reqRequestCreateDto) {
        requestService.createRequest(reqRequestCreateDto, getAuthorizer());
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }
}
