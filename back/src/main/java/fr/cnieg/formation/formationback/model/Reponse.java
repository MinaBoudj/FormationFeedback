package fr.cnieg.formation.formationback.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "REPONSE")
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Question question;

    private String commentaire;
    private int note;

    @ManyToOne
    @JoinColumn(name="avis_id")
    private Avis avis;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question idQuestion) {
        this.question = idQuestion;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Avis getAvis() {
        return avis;
    }

    public void setAvis(Avis avis) {
        this.avis = avis;
    }

}
