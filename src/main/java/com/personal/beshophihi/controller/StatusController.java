package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.dto.StatusDTO;
import com.personal.beshophihi.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/status-order")
public class StatusController {
    private final StatusService statusService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllStatuses() {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all statuses successfully.")
                        .data(statusService.getAllStatus())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetStatusById(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched status with ID: " + id + " successfully.")
                        .data(statusService.getStatusById(id))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateStatus(@RequestBody StatusDTO statusDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Status created successfully.")
                        .data(statusService.addStatus(statusDTO))
                        .build(),
                HttpStatus.CREATED);
    }

//  We don't need these APIs at the moment.
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> doUpdateStatus(@PathVariable Long id, @RequestBody StatusDTO statusDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Status with ID: " + id + " updated successfully.")
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> doDeleteStatus(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Status with ID: " + id + " deleted successfully.")
                        .build(),
                HttpStatus.OK);
    }





}
