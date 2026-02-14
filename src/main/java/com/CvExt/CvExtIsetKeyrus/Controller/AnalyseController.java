package com.CvExt.CvExtIsetKeyrus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CvExt.CvExtIsetKeyrus.Service.AnalyseService;
import com.CvExt.CvExtIsetKeyrus.entities.Analyse;
import com.CvExt.CvExtIsetKeyrus.entities.CV;

@RestController
@RequestMapping("/analyse")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;

    // Créer une analyse
    @PostMapping
    public Analyse creer(@RequestBody Analyse analyse) {
        return analyseService.creer(analyse);
    }

    // Récupérer toutes les analyses
    @GetMapping
    public List<Analyse> rechercher() {
        return analyseService.rechercher();
    }

    // Récupérer une analyse par ID
    @GetMapping("/{id}")
    public Analyse lire(@PathVariable Integer id) {
        return analyseService.lire(id);
    }

    // Récupérer les analyses d'un CV
    @GetMapping("/cv/{idCv}")
    public List<Analyse> rechercherParCV(@PathVariable Integer idCv) {
        return analyseService.rechercherParCV(idCv);
    }

    // Modifier une analyse
    @PutMapping("/{id}")
    public Analyse modifier(@PathVariable Integer id, @RequestBody Analyse analyse) {
        analyse.setIdAnalyse(id);
        return analyseService.mettre_a_jour(analyse);
    }

    // Supprimer une analyse
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        analyseService.supprimer(id);
    }

    // Rechercher par mots-clés
    @GetMapping("/search")
    public List<Analyse> rechercherParMotsCles(@RequestParam List<String> keywords) {
        return analyseService.rechercherParMotsCles(keywords);
    }

    // Rechercher CVs par mots-clés
    @GetMapping("/search/cvs")
    public List<CV> rechercherCvsParMotsCles(@RequestParam List<String> keywords) {
        return analyseService.rechercherCvsParMotsCles(keywords);
    }
}
