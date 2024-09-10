package fr.cnieg.formation.formationback.Services;

import fr.cnieg.formation.formationback.DTO.AgentDTO;
import fr.cnieg.formation.formationback.model.Agent;
import fr.cnieg.formation.formationback.repository.AgentRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    public Agent recupererAgentById(UUID id) {
        if (id == null)
            return null;
        return agentRepository.findAgentById(id);
    }

    public Agent CreerAgent(AgentDTO agent) {
        return agentRepository.save(mapAgentDTOToAgent(agent));
    }

    public Agent mapAgentDTOToAgent(@NotNull AgentDTO agentDTO) {
        Agent agent = new Agent();
        agent.setId(agentDTO.id());
        agent.setNom(agentDTO.nom());
        agent.setPrenom(agentDTO.prenom());
        return agent;
    }

    public AgentDTO mapAgentToAgentDTO(@NotNull Agent agent) {
        return new AgentDTO(agent.getId(), agent.getNom(), agent.getPrenom());
    }
}
