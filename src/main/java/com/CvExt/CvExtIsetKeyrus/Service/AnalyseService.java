package com.CvExt.CvExtIsetKeyrus.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CvExt.CvExtIsetKeyrus.Repository.AnalyseRepository;
import com.CvExt.CvExtIsetKeyrus.Repository.CVRepository;
import com.CvExt.CvExtIsetKeyrus.entities.Analyse;
import com.CvExt.CvExtIsetKeyrus.entities.CV;

@Service
public class AnalyseService {

    private final AnalyseRepository analyseRepository;
    private final CVRepository cvRepository;

    public AnalyseService(AnalyseRepository analyseRepository, CVRepository cvRepository) {
        this.analyseRepository = analyseRepository;
        this.cvRepository = cvRepository;
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
        return this.analyseRepository.findByIdCv(idCv);
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

    // SEARCH - Rechercher par mots-clés (recherche partielle, trié par score)
    public List<Analyse> rechercherParMotsCles(List<String> keywords) {
        return this.analyseRepository.findAll().stream()
                .filter(a -> a.getMotsClesPrincipaux() != null && keywords.stream()
                        .anyMatch(kw -> a.getMotsClesPrincipaux().toLowerCase().contains(kw.toLowerCase())))
                .sorted((a1, a2) -> {
                    Double s1 = a1.getScoreGlobal() != null ? a1.getScoreGlobal() : 0.0;
                    Double s2 = a2.getScoreGlobal() != null ? a2.getScoreGlobal() : 0.0;
                    return s2.compareTo(s1);
                })
                .toList();
    }

    // SEARCH - Rechercher CVs par mots-clés (optimisé, retourne CVs triés par score)
    public List<CV> rechercherCvsParMotsCles(List<String> keywords) {
        List<Analyse> analyses = rechercherParMotsCles(keywords);
        List<Integer> idCvs = analyses.stream()
                .map(Analyse::getIdCv)
                .distinct()
                .toList();
        
        if (idCvs.isEmpty()) {
            return List.of();
        }
        
        List<CV> cvs = cvRepository.findByIdCvIn(idCvs);
        
        // Trier les CVs selon l'ordre des analyses (par score)
        return idCvs.stream()
                .map(id -> cvs.stream().filter(cv -> cv.getIdCv().equals(id)).findFirst().orElse(null))
                .filter(cv -> cv != null)
                .toList();
    }
}
