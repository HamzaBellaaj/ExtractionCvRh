package com.CvExt.CvExtIsetKeyrus.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CvExt.CvExtIsetKeyrus.Repository.ExperienceRepository;
import com.CvExt.CvExtIsetKeyrus.entities.Experience;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    // CREATE - Créer une nouvelle expérience
    public Experience creer(Experience experience) {
        return this.experienceRepository.save(experience);
    }

    // READ - Récupérer toutes les expériences
    public List<Experience> rechercher() {
        return this.experienceRepository.findAll();
    }

    // READ - Récupérer une expérience par ID
    public Experience lire(Integer id) {
        Optional<Experience> experience = this.experienceRepository.findById(id);
        return experience.orElse(null);
    }

    // READ - Récupérer toutes les expériences d'un CV
    public List<Experience> rechercherParCV(Integer idCv) {
        return this.experienceRepository.findByIdCv(idCv);
    }

    // UPDATE - Mettre à jour une expérience
    public Experience mettre_a_jour(Experience experience) {
        if (experience.getIdExperience() != null && this.experienceRepository.existsById(experience.getIdExperience())) {
            return this.experienceRepository.save(experience);
        }
        return null;
    }

    // DELETE - Supprimer une expérience par ID
    public boolean supprimer(Integer id) {
        if (this.experienceRepository.existsById(id)) {
            this.experienceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
