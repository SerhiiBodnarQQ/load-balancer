package org.example.ws.repository;

import org.example.ws.model.WeightedRoundRobin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightedRoundRobinRepository extends JpaRepository<WeightedRoundRobin, Long> {

}
