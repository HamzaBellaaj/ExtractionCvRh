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

import com.CvExt.CvExtIsetKeyrus.Service.CompetenceService;
import com.CvExt.CvExtIsetKeyrus.entities.Competence;

@RestController
@RequestMapping("/competence")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    // Créer une compétence
    @PostMapping
    public Competence creer(@RequestBody Competence competence) {
        return competenceService.creer(competence);
    }

    // Récupérer toutes les compétences
    @GetMapping
    public List<Competence> rechercher() {
        return competenceService.rechercher();
    }

    // Récupérer une compétence par ID
    @GetMapping("/{id}")
    public Competence lire(@PathVariable Integer id) {
        return competenceService.lire(id);
    }

    // Récupérer les compétences d'un CV
    @GetMapping("/cv/{idCv}")
    public List<Competence> rechercherParCV(@PathVariable Integer idCv) {
        return competenceService.rechercherParCV(idCv);
    }

    // Modifier une compétence
    @PutMapping("/{id}")
    public Competence modifier(@PathVariable Integer id, @RequestBody Competence competence) {
        competence.setIdCompetence(id);
        return competenceService.mettre_a_jour(competence);
    }

    // Supprimer une compétence
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        competenceService.supprimer(id);
    }
}
