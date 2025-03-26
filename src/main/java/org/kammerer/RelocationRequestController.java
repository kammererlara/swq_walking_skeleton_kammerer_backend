package org.kammerer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requestForRelocationSupport")
public class RelocationRequestController {

    @Autowired
    private RelocationRequestService relocationRequestService;

    @PostMapping
    public ResponseEntity<?> createRelocationRequest(@RequestBody RelocationRequest request) {
        relocationRequestService.createRelocationRequest(request);
        return ResponseEntity.ok().body("{\"message\": \"Relocation request successfully created\"}");
    }
}