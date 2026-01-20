package com.CvExt.CvExtIsetKeyrus.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    @Column(name = "id_cv")
    private Integer idCv;

    // Constructeurs
    public Competence() {
    }

    public Competence(String nomCompetence, String typeCompetence, String niveau, Integer idCv) {
        this.nomCompetence = nomCompetence;
        this.typeCompetence = typeCompetence;
        this.niveau = niveau;
        this.idCv = idCv;
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

    public Integer getIdCv() {
        return idCv;
    }

    public void setIdCv(Integer idCv) {
        this.idCv = idCv;
    }
}
