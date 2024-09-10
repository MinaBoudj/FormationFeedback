package fr.cnieg.formation.formationback;

import fr.cnieg.formation.formationback.DTO.AgentDTO;
import fr.cnieg.formation.formationback.DTO.AvisDTO;
import fr.cnieg.formation.formationback.DTO.QuestionDTO;
import fr.cnieg.formation.formationback.DTO.ReponseDTO;
import fr.cnieg.formation.formationback.Services.AvisService;
import fr.cnieg.formation.formationback.model.Agent;
import fr.cnieg.formation.formationback.model.Avis;
import fr.cnieg.formation.formationback.model.Question;
import fr.cnieg.formation.formationback.model.Reponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AvisDTOTests {

    @Autowired
    private AvisService avisService;
    private Avis avis;
    private Agent agent;
    private List<Reponse> reponses = new ArrayList<>();
    private Question question;
    private AvisDTO avisDTO_test;
    private AgentDTO agentDTO;

    @BeforeEach
    public void setUpAvis() {
        agent = new Agent();
        agent.setId(UUID.randomUUID());
        agent.setNom("Thoma");
        agent.setPrenom("H");

        avis = new Avis();
        avis.setId(UUID.randomUUID());
        avis.setAgent(agent);
        avis.setFormation("formation 1");
        avis.setOrganisme("organisme 1");
        avis.setAnonyme(true);
        avis.setDatePublication(new Date());
        avis.setAvisGlobal("Bien");
        avis.setNoteGlobale(3);

        question = new Question();
        question.setId(UUID.randomUUID());
        question.setTexte("Question1");

        Reponse reponse = new Reponse();
        reponse.setId(UUID.randomUUID());
        reponse.setQuestion(question);
        reponse.setAvis(avis);
        reponse.setNote(2);
        reponse.setCommentaire("com 1");

        reponses.add(reponse);
        avis.setReponses(reponses);
    }


    @BeforeEach
    public void setUpAvisDTO() {
        agentDTO = new AgentDTO(UUID.randomUUID(), "Tom", "H");
        QuestionDTO questionDTO = new QuestionDTO(UUID.randomUUID(), "Question 1");
        ReponseDTO reponseDTO = new ReponseDTO(questionDTO, "reponse 1", 3);
        List<ReponseDTO> reponsesDTO = new ArrayList<>();
        reponsesDTO.add(reponseDTO);

        avisDTO_test = new AvisDTO(UUID.randomUUID(), agentDTO, "formation 1", "organisme1",
                false, new Date(), "bien",3, reponsesDTO);
    }

    @Test
    public void tesAvisToAvisDTO(){
        AvisDTO avisDTO = avisService.mapAvisToAvisDTO(avis);

        assertNotNull(avisDTO);
        assertEquals(avis.getId(), avisDTO.id());
        assertEquals(avis.getDatePublication(), avisDTO.datePublication());
        assertEquals(avis.getAvisGlobal(), avisDTO.avisGlobal());
        assertEquals(avis.getAgent().getId(), avisDTO.agent().id());
        assertEquals(avis.getReponses().size(), avisDTO.reponses().size());
        assertEquals(avis.getFormation(), avisDTO.formation());
    }


    @Test
    public void testAvisDTOToAvis(){
        Avis avis_test = avisService.mapAvisDTOToAvis(avisDTO_test);

        assertNotNull(avis_test);
        assertEquals(avis_test.getId(), avisDTO_test.id());
        assertEquals(avis_test.getAgent().getId(), avisDTO_test.agent().id());
        assertEquals(avis_test.getFormation(), avisDTO_test.formation());
        assertEquals(avis_test.getDatePublication(), avisDTO_test.datePublication());
        assertEquals(avis_test.getAvisGlobal(), avisDTO_test.avisGlobal());
        assertEquals(avis_test.getNoteGlobale(), avisDTO_test.noteGlobale());
    }
}
