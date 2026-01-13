package com.CvExt.CvExtIsetKeyrus.Service;

import com.CvExt.CvExtIsetKeyrus.Repository.AnalyseRepository;
import com.CvExt.CvExtIsetKeyrus.entities.Analyse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyseService {

    private AnalyseRepository analyseRepository;

    public AnalyseService(AnalyseRepository analyseRepository) {
        this.analyseRepository = analyseRepository;
    }

    // CREATE - Créer une nouvelle analyse
    public Analyse creer(Analyse analyse) {
        return this.analyseRepository.save(analyse);
    }

    // READ - Récupérer toutes les analyses
    public List<Analyse> rechercher() {
        return this.analyseRepository.findAll();
    }

    // READ - Récupérer une analyse par ID
    public Analyse lire(Integer id) {
        Optional<Analyse> analyse = this.analyseRepository.findById(id);
        return analyse.orElse(null);
    }

    // READ - Récupérer toutes les analyses d'un CV
    public List<Analyse> rechercherParCV(Integer idCv) {
        return this.analyseRepository.findByCvIdCv(idCv);
    }

    // UPDATE - Mettre à jour une analyse
    public Analyse mettre_a_jour(Analyse analyse) {
        if (analyse.getIdAnalyse() != null && this.analyseRepository.existsById(analyse.getIdAnalyse())) {
            return this.analyseRepository.save(analyse);
        }
        return null;
    }

    // DELETE - Supprimer une analyse par ID
    public boolean supprimer(Integer id) {
        if (this.analyseRepository.existsById(id)) {
            this.analyseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
