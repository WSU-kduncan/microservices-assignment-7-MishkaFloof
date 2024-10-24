package com.wsu.ordermasterservice.service;

import com.wsu.ordermasterservice.dto.ServerDTO;
import com.wsu.ordermasterservice.exception.ServerNotFoundException;
import com.wsu.ordermasterservice.model.Server;
import com.wsu.ordermasterservice.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServerService {


    @Autowired
    private ServerRepository serverRepository;

    // Retrieve all servers or by availability status
    public List<ServerDTO> getAllServers(String availability) {
        if (availability != null) {
            return serverRepository.findByAvailability(availability).stream()
                    .map(this::entityToDTO)
                    .toList();
        }
        return serverRepository.findAll().stream()
                .map(this::entityToDTO)
                .toList();
    }

    // Create a new server
    public ServerDTO createServer(ServerDTO serverDTO) {
        Server server = dtoToEntity(serverDTO);
        server = serverRepository.save(server);
        return entityToDTO(server);
    }

    // Update an existing server by ID
    public ServerDTO updateServer(Integer id, ServerDTO serverDTO) {
        Server server = serverRepository.findById(id)
            .orElseThrow(() -> new ServerNotFoundException("Server not found"));
        server.updateFromDTO(serverDTO);
        return entityToDTO(serverRepository.save(server));
    }

    // Delete a server by ID
    public void deleteServer(Integer id) {
        if (!serverRepository.existsById(id)) {
            throw new ServerNotFoundException("Server not found");
        }
        serverRepository.deleteById(id);
    }


    // Helper methods: dtoToEntity, entityToDTO
    private Server dtoToEntity(ServerDTO serverDTO) {
        // Return a new Server entity, ensuring fields are correctly mapped from the DTO
        Server server = new Server();
        server.setServerId(serverDTO.getServerId());
        server.setFirstName(serverDTO.getFirstName());
        server.setLastName(serverDTO.getLastName());
        server.setAvailability(serverDTO.getAvailability());
        return server;
    }
    
    private ServerDTO entityToDTO(Server server) {
        // Return a new ServerDTO, ensuring fields are correctly mapped from the entity
        ServerDTO serverDTO = new ServerDTO();
        serverDTO.setServerId(server.getServerId());
        serverDTO.setFirstName(server.getFirstName());
        serverDTO.setLastName(server.getLastName());
        serverDTO.setAvailability(server.getAvailability());
        return serverDTO;
    }
}