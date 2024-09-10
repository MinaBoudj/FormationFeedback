package fr.cnieg.formation.formationback.controller;

import fr.cnieg.formation.formationback.DTO.AvisDTO;
import fr.cnieg.formation.formationback.DTO.CreationAvisResponse;
import fr.cnieg.formation.formationback.Services.AgentService;
import fr.cnieg.formation.formationback.Services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;
    @Autowired
    private AgentService agentService;

    @GetMapping(path = "/list")
    public List<AvisDTO> recupererTousLesAvis() {
        return avisService.recupererTousLesAvisDTO();
    }

    @PostMapping(path = "/add")
    public CreationAvisResponse creationAvis(@RequestBody AvisDTO avisDTO) {
        if(avisService.creerAvis(avisDTO) != null)
            return new CreationAvisResponse("Avis créé avec succès","Créé", avisDTO.id());
        else
            return new CreationAvisResponse("Echèc lors de la création de l'avis","Echec", null);
    }

    @GetMapping(path ="/chercherByformation")
    public List<AvisDTO> chercherAvisByFormation(@RequestParam("formation") String formation) {
        return avisService.chercherAvisDTOByFormation(formation);
    }

    @GetMapping(path ="/chercherByOrganisme")
    public List<AvisDTO> chercherAvisByOrganisme(@RequestParam("organisme") String organisme) {
        return avisService.chercherAvisDTOByOrganisme(organisme);
    }
}
