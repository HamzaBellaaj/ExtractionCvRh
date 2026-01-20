package com.CvExt.CvExtIsetKeyrus.Controller;

import com.CvExt.CvExtIsetKeyrus.Service.FormationService;
import com.CvExt.CvExtIsetKeyrus.entities.Formation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formation")
public class FormationController {

    private final FormationService formationService;

    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }

    // CREATE - Créer une nouvelle formation
    @PostMapping
    public ResponseEntity<Formation> creer(@RequestBody Formation formation) {
        Formation formationCreated = this.formationService.creer(formation);
        return new ResponseEntity<>(formationCreated, HttpStatus.CREATED);
    }

    // READ - Récupérer toutes les formations
    @GetMapping
    public ResponseEntity<List<Formation>> rechercher() {
        List<Formation> formations = this.formationService.rechercher();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    // READ - Récupérer une formation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Formation> lire(@PathVariable Integer id) {
        Formation formation = this.formationService.lire(id);
        if (formation != null) {
            return new ResponseEntity<>(formation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // READ - Récupérer toutes les formations d'un CV
    @GetMapping("/cv/{idCv}")
    public ResponseEntity<List<Formation>> rechercherParCV(@PathVariable Integer idCv) {
        List<Formation> formations = this.formationService.rechercherParCV(idCv);
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    // UPDATE - Mettre à jour une formation
    @PutMapping("/{id}")
    public ResponseEntity<Formation> mettre_a_jour(@PathVariable Integer id, @RequestBody Formation formation) {
        formation.setIdFormation(id);
        Formation formationUpdated = this.formationService.mettre_a_jour(formation);
        if (formationUpdated != null) {
            return new ResponseEntity<>(formationUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE - Supprimer une formation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Integer id) {
        boolean isDeleted = this.formationService.supprimer(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
