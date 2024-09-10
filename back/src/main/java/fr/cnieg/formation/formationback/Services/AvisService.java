package fr.cnieg.formation.formationback.Services;

import fr.cnieg.formation.formationback.DTO.AvisDTO;
import fr.cnieg.formation.formationback.DTO.QuestionDTO;
import fr.cnieg.formation.formationback.DTO.ReponseDTO;
import fr.cnieg.formation.formationback.model.Agent;
import fr.cnieg.formation.formationback.model.Avis;
import fr.cnieg.formation.formationback.model.Question;
import fr.cnieg.formation.formationback.model.Reponse;
import fr.cnieg.formation.formationback.repository.AgentRepository;
import fr.cnieg.formation.formationback.repository.AvisRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AvisService {

    @Autowired
    private AvisRepository avisRepository;
    private AgentService agentService = new AgentService();

    @Autowired
    private AgentRepository agentRepository;

    public List<AvisDTO> recupererTousLesAvisDTO() {
        List<Avis> all = avisRepository.findAll();
        return all.stream().map(this::mapAvisToAvisDTO).toList();
    }

    public Avis creerAvis(AvisDTO avisDTO) {
        Avis avis = mapAvisDTOToAvis(avisDTO);
        Agent agent = agentService.mapAgentDTOToAgent(avisDTO.agent());

        Optional<Agent> agentExiste = agentRepository.findById(agent.getId());
        if (agentExiste.isPresent()) {
            avis.setAgent(agentExiste.get());
        } else {
            Agent savedAgent = agentRepository.save(agent);
            avis.setAgent(savedAgent);
        }
        return avisRepository.save(avis);
    }

    public List<AvisDTO> chercherAvisDTOByFormation(String formation) {
        List<Avis> all = avisRepository.findAvisByFormationContainingIgnoreCase(formation);
        return all.stream().map(this::mapAvisToAvisDTO).toList();
    }

    public List<AvisDTO> chercherAvisDTOByOrganisme(String organisme) {
        List<Avis> all = avisRepository.findAvisByOrganismeContainingIgnoreCase(organisme);
        return all.stream().map(this::mapAvisToAvisDTO).toList();
    }

    //transforme un Avis en AvisDTO
    public AvisDTO mapAvisToAvisDTO(@NotNull Avis avis) {
        return new AvisDTO(avis.getId(),agentService.mapAgentToAgentDTO(avis.getAgent()), avis.getFormation(), avis.getOrganisme(), avis.isAnonyme(),
                avis.getDatePublication(), avis.getAvisGlobal(), avis.getNoteGlobale(), avis.getReponses().stream().map(this::mapReponsesToReponseDTO).toList());
    }

    //transforme un AvisDTO en Avis
    public Avis mapAvisDTOToAvis(@NotNull AvisDTO avisDTO) {
        Avis avis = new Avis();
        avis.setId(avisDTO.id());
        avis.setAgent(agentService.mapAgentDTOToAgent(avisDTO.agent()));
        avis.setFormation(avisDTO.formation());
        avis.setOrganisme(avisDTO.organisme());
        avis.setAnonyme(avisDTO.anonyme());
        avis.setDatePublication(avisDTO.datePublication());
        avis.setAvisGlobal(avisDTO.avisGlobal());
        avis.setNoteGlobale(avisDTO.noteGlobale());
        List<Reponse> reponses = avisDTO.reponses().stream().map(this::mapReponsesDTOToReponse).toList();
        for (Reponse reponse : reponses) {
            reponse.setAvis(avis);
        }
        avis.setReponses(reponses);
        return avis;
    }

    private ReponseDTO mapReponsesToReponseDTO(@NotNull Reponse reponse) {
        return new ReponseDTO(new QuestionDTO(reponse.getQuestion().getId(), reponse.getQuestion().getTexte()), reponse.getCommentaire(), reponse.getNote());
    }

    private Reponse mapReponsesDTOToReponse(@NotNull ReponseDTO reponseDTO) {
        Reponse reponse = new Reponse();
        Question question = new Question();
        question.setId(reponseDTO.question().id());
        question.setTexte(reponseDTO.question().texte());
        reponse.setQuestion(question);
        reponse.setCommentaire(reponseDTO.commentaire());
        reponse.setNote(reponseDTO.note());
        return reponse;
    }


}
