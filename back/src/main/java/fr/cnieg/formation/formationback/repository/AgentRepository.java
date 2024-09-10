package fr.cnieg.formation.formationback.repository;

import fr.cnieg.formation.formationback.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgentRepository extends JpaRepository<Agent, UUID> {
    Agent findAgentById(UUID id);
}
