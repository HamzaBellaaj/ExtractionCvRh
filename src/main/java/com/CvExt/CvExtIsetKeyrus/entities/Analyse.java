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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cv", nullable = false)
    private CV cv;

    // Constructeurs
    public Analyse() {
    }

    public Analyse(String motsClesPrincipaux, Double scoreGlobal, Double scoreTechnique, Double scoreSoftSkills, CV cv) {
        this.motsClesPrincipaux = motsClesPrincipaux;
        this.scoreGlobal = scoreGlobal;
        this.scoreTechnique = scoreTechnique;
        this.scoreSoftSkills = scoreSoftSkills;
        this.cv = cv;
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

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }
}
