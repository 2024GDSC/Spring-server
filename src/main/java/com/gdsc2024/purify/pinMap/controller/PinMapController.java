package com.gdsc2024.purify.pinMap.controller;

import com.gdsc2024.purify.common.dto.Message;
import com.gdsc2024.purify.handler.StatusCode;
import com.gdsc2024.purify.pinMap.service.PinMapService;
import com.gdsc2024.purify.project.dto.ReqProjectCreateDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.gdsc2024.purify.security.JwtInfoExtractor.getAuthorizer;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/pinMap")
public class PinMapController {
    private final PinMapService pinMapService;

    @PostMapping("/")
    public ResponseEntity<Message> listUpPinMap(@RequestBody HashMap<String, Long> projectIdHash) {
        return ResponseEntity.ok(new Message(StatusCode.OK, pinMapService.listUpPinMaps(projectIdHash.get("projectId"))));
    }
}
