package org.example.ws.repository;

import org.example.ws.model.RoundRobin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRobinRepository extends JpaRepository<RoundRobin, Long> {

}
