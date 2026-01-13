package com.CvExt.CvExtIsetKeyrus.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "LANGUE")
public class Langue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_langue")
    private Integer idLangue;

    @Column(name = "nom_langue")
    private String nomLangue;

    @Column(name = "niveau_langue")
    private String niveauLangue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cv", nullable = false)
    private CV cv;

    // Constructeurs
    public Langue() {
    }

    public Langue(String nomLangue, String niveauLangue, CV cv) {
        this.nomLangue = nomLangue;
        this.niveauLangue = niveauLangue;
        this.cv = cv;
    }

    // Getters et Setters
    public Integer getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(Integer idLangue) {
        this.idLangue = idLangue;
    }

    public String getNomLangue() {
        return nomLangue;
    }

    public void setNomLangue(String nomLangue) {
        this.nomLangue = nomLangue;
    }

    public String getNiveauLangue() {
        return niveauLangue;
    }

    public void setNiveauLangue(String niveauLangue) {
        this.niveauLangue = niveauLangue;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }
}
