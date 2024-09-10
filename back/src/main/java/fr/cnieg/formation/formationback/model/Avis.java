package fr.cnieg.formation.formationback.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "AVIS")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Agent agent;
    private String formation;
    private String organisme;
    private boolean anonyme;
    private Date datePublication;
    private int noteGlobale;
    private String avisGlobal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avis")
    private List<Reponse> reponses;

    public Avis() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public boolean isAnonyme() {
        return anonyme;
    }

    public void setAnonyme(boolean anonyme) {
        this.anonyme = anonyme;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public int getNoteGlobale() {
        return noteGlobale;
    }

    public void setNoteGlobale(int noteGlobale) {
        this.noteGlobale = noteGlobale;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public String getAvisGlobal() {
        return avisGlobal;
    }

    public void setAvisGlobal(String avisGlobal) {
        this.avisGlobal = avisGlobal;
    }
}