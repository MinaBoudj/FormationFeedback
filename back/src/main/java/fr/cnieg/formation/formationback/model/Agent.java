package fr.cnieg.formation.formationback.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "AGENT")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nom;
    private String prenom;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
