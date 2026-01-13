package com.CvExt.CvExtIsetKeyrus.Service;

import com.CvExt.CvExtIsetKeyrus.Repository.FormationRepository;
import com.CvExt.CvExtIsetKeyrus.entities.Formation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    private FormationRepository formationRepository;

    public FormationService(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    // CREATE - Créer une nouvelle formation
    public Formation creer(Formation formation) {
        return this.formationRepository.save(formation);
    }

    // READ - Récupérer toutes les formations
    public List<Formation> rechercher() {
        return this.formationRepository.findAll();
    }

    // READ - Récupérer une formation par ID
    public Formation lire(Integer id) {
        Optional<Formation> formation = this.formationRepository.findById(id);
        return formation.orElse(null);
    }

    // READ - Récupérer toutes les formations d'un CV
    public List<Formation> rechercherParCV(Integer idCv) {
        return this.formationRepository.findByCvIdCv(idCv);
    }

    // UPDATE - Mettre à jour une formation
    public Formation mettre_a_jour(Formation formation) {
        if (formation.getIdFormation() != null && this.formationRepository.existsById(formation.getIdFormation())) {
            return this.formationRepository.save(formation);
        }
        return null;
    }

    // DELETE - Supprimer une formation par ID
    public boolean supprimer(Integer id) {
        if (this.formationRepository.existsById(id)) {
            this.formationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
