package com.wsu.ordermasterservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wsu.ordermasterservice.dto.ServerDTO;
import com.wsu.ordermasterservice.service.ServerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servers")
public class ServerController {

    @Autowired
    private ServerService serverService;

    // GET: Retrieve all servers or by availability status
    @GetMapping
    public ResponseEntity<List<ServerDTO>> getAllServers(@RequestParam(required = false) String availability) {
        return ResponseEntity.ok(serverService.getAllServers(availability));
    }

    // POST: Create a new server
    @PostMapping
    public ResponseEntity<ServerDTO> createServer(@Valid @RequestBody ServerDTO serverDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serverService.createServer(serverDTO));
    }

    // PUT: Update an existing server by ID
    @PutMapping("/{id}")
    public ResponseEntity<ServerDTO> updateServer(@PathVariable Integer id, @Valid @RequestBody ServerDTO serverDTO) {
        return ResponseEntity.ok(serverService.updateServer(id, serverDTO));
    }

    // DELETE: Remove a server by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable Integer id) {
        serverService.deleteServer(id);
        return ResponseEntity.noContent().build();
    }
}
