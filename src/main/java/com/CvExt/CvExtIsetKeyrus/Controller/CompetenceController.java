package com.CvExt.CvExtIsetKeyrus.Controller;

import com.CvExt.CvExtIsetKeyrus.Service.CompetenceService;
import com.CvExt.CvExtIsetKeyrus.entities.Competence;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competence")
public class CompetenceController {

    private final CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    // CREATE - Créer une nouvelle compétence
    @PostMapping
    public ResponseEntity<Competence> creer(@RequestBody Competence competence) {
        Competence competenceCreated = this.competenceService.creer(competence);
        return new ResponseEntity<>(competenceCreated, HttpStatus.CREATED);
    }

    // READ - Récupérer toutes les compétences
    @GetMapping
    public ResponseEntity<List<Competence>> rechercher() {
        List<Competence> competences = this.competenceService.rechercher();
        return new ResponseEntity<>(competences, HttpStatus.OK);
    }

    // READ - Récupérer une compétence par ID
    @GetMapping("/{id}")
    public ResponseEntity<Competence> lire(@PathVariable Integer id) {
        Competence competence = this.competenceService.lire(id);
        if (competence != null) {
            return new ResponseEntity<>(competence, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // READ - Récupérer toutes les compétences d'un CV
    @GetMapping("/cv/{idCv}")
    public ResponseEntity<List<Competence>> rechercherParCV(@PathVariable Integer idCv) {
        List<Competence> competences = this.competenceService.rechercherParCV(idCv);
        return new ResponseEntity<>(competences, HttpStatus.OK);
    }

    // UPDATE - Mettre à jour une compétence
    @PutMapping("/{id}")
    public ResponseEntity<Competence> mettre_a_jour(@PathVariable Integer id, @RequestBody Competence competence) {
        competence.setIdCompetence(id);
        Competence competenceUpdated = this.competenceService.mettre_a_jour(competence);
        if (competenceUpdated != null) {
            return new ResponseEntity<>(competenceUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE - Supprimer une compétence
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Integer id) {
        boolean isDeleted = this.competenceService.supprimer(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
