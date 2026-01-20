package com.CvExt.CvExtIsetKeyrus.Service;

import com.CvExt.CvExtIsetKeyrus.Repository.LangueRepository;
import com.CvExt.CvExtIsetKeyrus.entities.Langue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LangueService {

    private final LangueRepository langueRepository;

    public LangueService(LangueRepository langueRepository) {
        this.langueRepository = langueRepository;
    }

    // CREATE - Créer une nouvelle langue
    public Langue creer(Langue langue) {
        return this.langueRepository.save(langue);
    }

    // READ - Récupérer toutes les langues
    public List<Langue> rechercher() {
        return this.langueRepository.findAll();
    }

    // READ - Récupérer une langue par ID
    public Langue lire(Integer id) {
        Optional<Langue> langue = this.langueRepository.findById(id);
        return langue.orElse(null);
    }

    // READ - Récupérer toutes les langues d'un CV
    public List<Langue> rechercherParCV(Integer idCv) {
        return this.langueRepository.findByIdCv(idCv);
    }

    // UPDATE - Mettre à jour une langue
    public Langue mettre_a_jour(Langue langue) {
        if (langue.getIdLangue() != null && this.langueRepository.existsById(langue.getIdLangue())) {
            return this.langueRepository.save(langue);
        }
        return null;
    }

    // DELETE - Supprimer une langue par ID
    public boolean supprimer(Integer id) {
        if (this.langueRepository.existsById(id)) {
            this.langueRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
