package com.CvExt.CvExtIsetKeyrus.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CvExt.CvExtIsetKeyrus.Repository.CVRepository;
import com.CvExt.CvExtIsetKeyrus.entities.CV;

@Service
public class CVService {

    private final CVRepository cvRepository;

    public CVService(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    // CREATE - Créer un nouveau CV
    public CV creer(CV cv) {
        return this.cvRepository.save(cv);
    }

    // READ - Récupérer tous les CVs
    public List<CV> rechercher() {
        return this.cvRepository.findAll();
    }

    // READ - Récupérer un CV par ID
    public CV lire(Integer id) {
        Optional<CV> cv = this.cvRepository.findById(id);
        return cv.orElse(null);
    }

    // UPDATE - Mettre à jour un CV
    public CV mettre_a_jour(CV cv) {
        if (cv.getIdCv() != null && this.cvRepository.existsById(cv.getIdCv())) {
            return this.cvRepository.save(cv);
        }
        return null;
    }

    // DELETE - Supprimer un CV par ID
    public boolean supprimer(Integer id) {
        if (this.cvRepository.existsById(id)) {
            this.cvRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
