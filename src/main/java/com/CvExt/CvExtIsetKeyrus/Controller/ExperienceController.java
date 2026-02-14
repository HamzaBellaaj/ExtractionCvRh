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
import org.springframework.web.bind.annotation.RestController;

import com.CvExt.CvExtIsetKeyrus.Service.ExperienceService;
import com.CvExt.CvExtIsetKeyrus.entities.Experience;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    // Créer une expérience
    @PostMapping
    public Experience creer(@RequestBody Experience experience) {
        return experienceService.creer(experience);
    }

    // Récupérer toutes les expériences
    @GetMapping
    public List<Experience> rechercher() {
        return experienceService.rechercher();
    }

    // Récupérer une expérience par ID
    @GetMapping("/{id}")
    public Experience lire(@PathVariable Integer id) {
        return experienceService.lire(id);
    }

    // Récupérer les expériences d'un CV
    @GetMapping("/cv/{idCv}")
    public List<Experience> rechercherParCV(@PathVariable Integer idCv) {
        return experienceService.rechercherParCV(idCv);
    }

    // Modifier une expérience
    @PutMapping("/{id}")
    public Experience modifier(@PathVariable Integer id, @RequestBody Experience experience) {
        experience.setIdExperience(id);
        return experienceService.mettre_a_jour(experience);
    }

    // Supprimer une expérience
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        experienceService.supprimer(id);
    }
}
