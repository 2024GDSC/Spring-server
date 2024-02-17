package com.gdsc2024.purify.pinMap.controller;

import com.gdsc2024.purify.common.dto.Message;
import com.gdsc2024.purify.handler.StatusCode;
import com.gdsc2024.purify.pin.domain.Pin;
import com.gdsc2024.purify.pin.dto.ReqPinCreateDto;
import com.gdsc2024.purify.pin.service.PinService;
import com.gdsc2024.purify.pinMap.dto.ReqPinMapCreateDto;
import com.gdsc2024.purify.pinMap.service.PinMapService;
import com.gdsc2024.purify.project.service.ProjectService;
import com.gdsc2024.purify.security.dto.AuthorizerDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.gdsc2024.purify.security.JwtInfoExtractor.getAuthorizer;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/pinMap")
public class PinMapController {
    private final PinService pinService;
    private final PinMapService pinMapService;
    private final ProjectService projectService;

    @PostMapping("/")
    public ResponseEntity<Message> listUpPinMap(@RequestBody HashMap<String, Long> projectIdHash) {
        return ResponseEntity.ok(new Message(StatusCode.OK, pinMapService.listUpPinMaps(projectIdHash.get("projectId"))));
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createPinMap(@RequestBody ReqPinMapCreateDto reqPinMapCreateDto) {
        pinMapService.createPinMap(reqPinMapCreateDto, (AuthorizerDto) projectService.getProjects(getAuthorizer()));
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @GetMapping("/view")
    public ResponseEntity<Message> viewPinMap() {
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @PostMapping("/{pinMapId}")
    public ResponseEntity<Message> createPin(@PathVariable String pinMapId, @RequestBody ReqPinCreateDto reqPinCreateDto) {
        pinService.createPin(reqPinCreateDto, getAuthorizer());
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @PatchMapping("/{pinId}")
    public ResponseEntity<Message> updatePin(@PathVariable Long pinId, @RequestBody ReqPinCreateDto reqPinCreateDto) {
        pinService.updatePin(pinId, reqPinCreateDto, getAuthorizer());
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @GetMapping("/read/{pinMapId}")
    public ResponseEntity<Message> readSimulation(@PathVariable Long pinMapId) {
        return ResponseEntity.ok(new Message(StatusCode.OK, pinMapService.readPinMap(pinMapId, getAuthorizer())));
    }

    @PostMapping("/save/{pinMapId}")
    public ResponseEntity<Message> savePinMap(@RequestBody ReqPinMapCreateDto reqPinMapCreateDto, @PathVariable Long pinMapId) {
        pinMapService.savePinMap(pinMapId,  reqPinMapCreateDto, getAuthorizer());
        return ResponseEntity.ok(new Message(StatusCode.OK));
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
