package com.gdsc2024.purify.pinMap.controller;

import com.gdsc2024.purify.common.dto.Message;
import com.gdsc2024.purify.handler.StatusCode;
import com.gdsc2024.purify.pinMap.dto.ReqPinMapCreateDto;
import com.gdsc2024.purify.pinMap.service.PinMapService;
import com.gdsc2024.purify.project.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.gdsc2024.purify.security.JwtInfoExtractor.getAuthorizer;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/pinMap")
public class PinMapController {
    private final PinMapService pinMapService;
    private final ProjectService projectService;

    @PostMapping("/")
    public ResponseEntity<Message> listUpPinMap(@RequestBody HashMap<String, Long> projectIdHash) {
        return ResponseEntity.ok(new Message(StatusCode.OK, pinMapService.listUpPinMaps(projectIdHash.get("projectId"))));
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createPinMap(@RequestBody ReqPinMapCreateDto reqPinMapCreateDto) {
        pinMapService.createPinMap(reqPinMapCreateDto, getAuthorizer());
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @GetMapping("/view")
    public ResponseEntity<Message> viewPinMap() {
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @PostMapping("/simulate")
    public ResponseEntity<Message> simulatePinMap(@RequestBody HashMap<String, Long> pinMapIdHash) {
        pinMapService.simulatePinMap(pinMapIdHash.get("pinMapId"));
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @GetMapping("/simulate/read")
    public ResponseEntity<Message> readSimulation(@RequestBody HashMap<String, Long> pinMapIdHash) {
        return ResponseEntity.ok(new Message(StatusCode.OK, pinMapService.getSimulation(pinMapIdHash.get("pinMapId"))));
    }

    @GetMapping("/transfer")
    public ResponseEntity<Message> transferPinMap() {
        return ResponseEntity.ok(new Message(StatusCode.OK, projectService.getProjects(getAuthorizer())));
    }

    @PostMapping("/transfer/select")
    public ResponseEntity<Message> transferProjectId(@RequestBody HashMap<String, Long> pinMapIdHash, @RequestBody HashMap<String, Long> projectIdHash) {
        pinMapService.transferPinMap(pinMapIdHash.get("pinMapId"), projectIdHash.get("projectId"));
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }
}
