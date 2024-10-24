package com.wsu.ordermasterservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

//import com.wsu.ordermasterservice.dto.ServerDTO;
import com.wsu.ordermasterservice.model.Server;

public interface ServerRepository extends JpaRepository<Server, Integer> {
    
    List<Server> findByAvailability(String availability); // Ensure this returns List<Server>
}
