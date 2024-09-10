package fr.cnieg.formation.formationback.DTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record AvisDTO (UUID id, AgentDTO agent, String formation,
                       String organisme, boolean anonyme, Date datePublication,
                       String avisGlobal, int noteGlobale, List<ReponseDTO> reponses){

}
