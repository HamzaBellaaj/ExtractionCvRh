package com.CvExt.CvExtIsetKeyrus.Controller;

import com.CvExt.CvExtIsetKeyrus.Service.ExperienceService;
import com.CvExt.CvExtIsetKeyrus.entities.Experience;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    private ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    // CREATE - Créer une nouvelle expérience
    @PostMapping
    public ResponseEntity<Experience> creer(@RequestBody Experience experience) {
        Experience experienceCreated = this.experienceService.creer(experience);
        return new ResponseEntity<>(experienceCreated, HttpStatus.CREATED);
    }

    // READ - Récupérer toutes les expériences
    @GetMapping
    public ResponseEntity<List<Experience>> rechercher() {
        List<Experience> experiences = this.experienceService.rechercher();
        return new ResponseEntity<>(experiences, HttpStatus.OK);
    }

    // READ - Récupérer une expérience par ID
    @GetMapping("/{id}")
    public ResponseEntity<Experience> lire(@PathVariable Integer id) {
        Experience experience = this.experienceService.lire(id);
        if (experience != null) {
            return new ResponseEntity<>(experience, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // READ - Récupérer toutes les expériences d'un CV
    @GetMapping("/cv/{idCv}")
    public ResponseEntity<List<Experience>> rechercherParCV(@PathVariable Integer idCv) {
        List<Experience> experiences = this.experienceService.rechercherParCV(idCv);
        return new ResponseEntity<>(experiences, HttpStatus.OK);
    }

    // UPDATE - Mettre à jour une expérience
    @PutMapping("/{id}")
    public ResponseEntity<Experience> mettre_a_jour(@PathVariable Integer id, @RequestBody Experience experience) {
        experience.setIdExperience(id);
        Experience experienceUpdated = this.experienceService.mettre_a_jour(experience);
        if (experienceUpdated != null) {
            return new ResponseEntity<>(experienceUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE - Supprimer une expérience
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Integer id) {
        boolean isDeleted = this.experienceService.supprimer(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
