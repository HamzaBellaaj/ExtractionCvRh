package com.CvExt.CvExtIsetKeyrus.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "FORMATION")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formation")
    private Integer idFormation;

    @Column(name = "diplome")
    private String diplome;

    @Column(name = "etablissement")
    private String etablissement;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "id_cv")
    private Integer idCv;

    // Constructeurs
    public Formation() {
    }

    public Formation(String diplome, String etablissement, LocalDate dateDebut, LocalDate dateFin, Integer idCv) {
        this.diplome = diplome;
        this.etablissement = etablissement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idCv = idCv;
    }

    // Getters et Setters
    public Integer getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Integer idFormation) {
        this.idFormation = idFormation;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getIdCv() {
        return idCv;
    }

    public void setIdCv(Integer idCv) {
        this.idCv = idCv;
    }
}
