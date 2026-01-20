package com.CvExt.CvExtIsetKeyrus.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ANALYSE")
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_analyse")
    private Integer idAnalyse;

    @Column(name = "mots_cles_principaux", columnDefinition = "TEXT")
    private String motsClesPrincipaux;

    @Column(name = "score_global")
    private Double scoreGlobal;

    @Column(name = "score_technique")
    private Double scoreTechnique;

    @Column(name = "score_soft_skills")
    private Double scoreSoftSkills;

    @Column(name = "id_cv")
    private Integer idCv;

    // Constructeurs
    public Analyse() {
    }

    public Analyse(String motsClesPrincipaux, Double scoreGlobal, Double scoreTechnique, Double scoreSoftSkills, Integer idCv) {
        this.motsClesPrincipaux = motsClesPrincipaux;
        this.scoreGlobal = scoreGlobal;
        this.scoreTechnique = scoreTechnique;
        this.scoreSoftSkills = scoreSoftSkills;
        this.idCv = idCv;
    }

    // Getters et Setters
    public Integer getIdAnalyse() {
        return idAnalyse;
    }

    public void setIdAnalyse(Integer idAnalyse) {
        this.idAnalyse = idAnalyse;
    }

    public String getMotsClesPrincipaux() {
        return motsClesPrincipaux;
    }

    public void setMotsClesPrincipaux(String motsClesPrincipaux) {
        this.motsClesPrincipaux = motsClesPrincipaux;
    }

    public Double getScoreGlobal() {
        return scoreGlobal;
    }

    public void setScoreGlobal(Double scoreGlobal) {
        this.scoreGlobal = scoreGlobal;
    }

    public Double getScoreTechnique() {
        return scoreTechnique;
    }

    public void setScoreTechnique(Double scoreTechnique) {
        this.scoreTechnique = scoreTechnique;
    }

    public Double getScoreSoftSkills() {
        return scoreSoftSkills;
    }

    public void setScoreSoftSkills(Double scoreSoftSkills) {
        this.scoreSoftSkills = scoreSoftSkills;
    }

    public Integer getIdCv() {
        return idCv;
    }

    public void setIdCv(Integer idCv) {
        this.idCv = idCv;
    }
}
