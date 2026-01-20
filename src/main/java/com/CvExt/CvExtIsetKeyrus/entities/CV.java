package com.CvExt.CvExtIsetKeyrus.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CV")
public class CV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cv")
    private Integer idCv;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adresse", columnDefinition = "TEXT")
    private String adresse;

    @Column(name = "titre_profil")
    private String titreProfil;

    @Column(name = "resume_profil", columnDefinition = "TEXT")
    private String resumeProfil;

    @Column(name = "annees_experience")
    private Integer anneesExperience;

    @Column(name = "niveau_profil")
    private String niveauProfil;

    @Column(name = "domaine")
    private String domaine;

    // Constructeurs
    public CV() {
    }

    public CV(String nom, String prenom, String email, String telephone, String adresse, 
              String titreProfil, String resumeProfil, Integer anneesExperience, 
              String niveauProfil, String domaine) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.titreProfil = titreProfil;
        this.resumeProfil = resumeProfil;
        this.anneesExperience = anneesExperience;
        this.niveauProfil = niveauProfil;
        this.domaine = domaine;
    }

    // Getters et Setters
    public Integer getIdCv() {
        return idCv;
    }

    public void setIdCv(Integer idCv) {
        this.idCv = idCv;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTitreProfil() {
        return titreProfil;
    }

    public void setTitreProfil(String titreProfil) {
        this.titreProfil = titreProfil;
    }

    public String getResumeProfil() {
        return resumeProfil;
    }

    public void setResumeProfil(String resumeProfil) {
        this.resumeProfil = resumeProfil;
    }

    public Integer getAnneesExperience() {
        return anneesExperience;
    }

    public void setAnneesExperience(Integer anneesExperience) {
        this.anneesExperience = anneesExperience;
    }

    public String getNiveauProfil() {
        return niveauProfil;
    }

    public void setNiveauProfil(String niveauProfil) {
        this.niveauProfil = niveauProfil;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
}
