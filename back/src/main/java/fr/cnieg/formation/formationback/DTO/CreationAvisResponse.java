package fr.cnieg.formation.formationback.DTO;

import java.util.UUID;

public record CreationAvisResponse (
        String message,
        String status,
        UUID idAvis){
}
