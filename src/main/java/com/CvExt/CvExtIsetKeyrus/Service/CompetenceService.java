package com.CvExt.CvExtIsetKeyrus.Service;

import com.CvExt.CvExtIsetKeyrus.Repository.CompetenceRepository;
import com.CvExt.CvExtIsetKeyrus.entities.Competence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenceService {

    private CompetenceRepository competenceRepository;

    public CompetenceService(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    // CREATE - Créer une nouvelle compétence
    public Competence creer(Competence competence) {
        return this.competenceRepository.save(competence);
    }

    // READ - Récupérer toutes les compétences
    public List<Competence> rechercher() {
        return this.competenceRepository.findAll();
    }

    // READ - Récupérer une compétence par ID
    public Competence lire(Integer id) {
        Optional<Competence> competence = this.competenceRepository.findById(id);
        return competence.orElse(null);
    }

    // READ - Récupérer toutes les compétences d'un CV
    public List<Competence> rechercherParCV(Integer idCv) {
        return this.competenceRepository.findByCvIdCv(idCv);
    }

    // UPDATE - Mettre à jour une compétence
    public Competence mettre_a_jour(Competence competence) {
        if (competence.getIdCompetence() != null && this.competenceRepository.existsById(competence.getIdCompetence())) {
            return this.competenceRepository.save(competence);
        }
        return null;
    }

    // DELETE - Supprimer une compétence par ID
    public boolean supprimer(Integer id) {
        if (this.competenceRepository.existsById(id)) {
            this.competenceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
