package com.CvExt.CvExtIsetKeyrus.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "COMPETENCE")
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competence")
    private Integer idCompetence;

    @Column(name = "nom_competence")
    private String nomCompetence;

    @Column(name = "type_competence")
    private String typeCompetence;

    @Column(name = "niveau")
    private String niveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cv", nullable = false)
    private CV cv;

    // Constructeurs
    public Competence() {
    }

    public Competence(String nomCompetence, String typeCompetence, String niveau, CV cv) {
        this.nomCompetence = nomCompetence;
        this.typeCompetence = typeCompetence;
        this.niveau = niveau;
        this.cv = cv;
    }

    // Getters et Setters
    public Integer getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(Integer idCompetence) {
        this.idCompetence = idCompetence;
    }

    public String getNomCompetence() {
        return nomCompetence;
    }

    public void setNomCompetence(String nomCompetence) {
        this.nomCompetence = nomCompetence;
    }

    public String getTypeCompetence() {
        return typeCompetence;
    }

    public void setTypeCompetence(String typeCompetence) {
        this.typeCompetence = typeCompetence;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }
}
