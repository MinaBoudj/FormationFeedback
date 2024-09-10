package fr.cnieg.formation.formationback.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String texte;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
