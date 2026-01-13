-- Table CV
CREATE TABLE CV (
    id_cv INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    email VARCHAR(150),
    telephone VARCHAR(20),
    adresse TEXT,
    titre_profil VARCHAR(200),
    resume_profil TEXT,
    annees_experience INT,
    niveau_profil VARCHAR(50),
    domaine VARCHAR(100)
);

-- Table COMPETENCE
CREATE TABLE COMPETENCE (
    id_competence INT AUTO_INCREMENT PRIMARY KEY,
    nom_competence VARCHAR(150),
    type_competence VARCHAR(50),
    niveau VARCHAR(50),
    id_cv INT,
    FOREIGN KEY (id_cv) REFERENCES CV(id_cv)
);

-- Table EXPERIENCE
CREATE TABLE EXPERIENCE (
    id_experience INT AUTO_INCREMENT PRIMARY KEY,
    poste VARCHAR(200),
    entreprise VARCHAR(200),
    date_debut DATE,
    date_fin DATE,
    description TEXT,
    id_cv INT,
    FOREIGN KEY (id_cv) REFERENCES CV(id_cv)
);

-- Table FORMATION
CREATE TABLE FORMATION (
    id_formation INT AUTO_INCREMENT PRIMARY KEY,
    diplome VARCHAR(200),
    etablissement VARCHAR(200),
    date_debut DATE,
    date_fin DATE,
    id_cv INT,
    FOREIGN KEY (id_cv) REFERENCES CV(id_cv)
);

-- Table LANGUE
CREATE TABLE LANGUE (
    id_langue INT AUTO_INCREMENT PRIMARY KEY,
    nom_langue VARCHAR(100),
    niveau_langue VARCHAR(50),
    id_cv INT,
    FOREIGN KEY (id_cv) REFERENCES CV(id_cv)
);

-- Table ANALYSE
CREATE TABLE ANALYSE (
    id_analyse INT AUTO_INCREMENT PRIMARY KEY,
    mots_cles_principaux TEXT,
    score_global DOUBLE,
    score_technique DOUBLE,
    score_soft_skills DOUBLE,
    id_cv INT,
    FOREIGN KEY (id_cv) REFERENCES CV(id_cv)
);