package fr.cnieg.formation.formationback;

import fr.cnieg.formation.formationback.DTO.AgentDTO;
import fr.cnieg.formation.formationback.Services.AgentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fr.cnieg.formation.formationback.model.Agent;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AgentDTOTest {

    @Autowired
    private AgentService agentService;
    private Agent agent;
    private AgentDTO agentDTO;

    @BeforeEach
    public void setUpAgent() {
        agent = new Agent();
        agent.setId(UUID.randomUUID());
        agent.setNom("John Doe");
        agent.setPrenom("Doe");

        agentDTO = new AgentDTO(UUID.randomUUID(), "Thomas", "H");
    }

    @Test
    public void testAgentToAgentDTO() {
        AgentDTO agentDTO = agentService.mapAgentToAgentDTO(agent);

        assertNotNull(agentDTO);
        assertEquals(agent.getId(), agentDTO.id());
        assertEquals(agent.getNom(), agentDTO.nom());
        assertEquals(agent.getPrenom(), agentDTO.prenom());
    }

    @Test
    public void testAgentDTOToAgent() {
        Agent agent = agentService.mapAgentDTOToAgent(agentDTO);

        assertNotNull(agentDTO);
        assertEquals(agent.getId(), agentDTO.id());
        assertEquals(agent.getNom(), agentDTO.nom());
        assertEquals(agent.getPrenom(), agentDTO.prenom());
    }

}
