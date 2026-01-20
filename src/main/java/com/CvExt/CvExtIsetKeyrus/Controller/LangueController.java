package com.CvExt.CvExtIsetKeyrus.Controller;

import com.CvExt.CvExtIsetKeyrus.Service.LangueService;
import com.CvExt.CvExtIsetKeyrus.entities.Langue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/langue")
public class LangueController {

    private final LangueService langueService;

    public LangueController(LangueService langueService) {
        this.langueService = langueService;
    }

    // CREATE - Créer une nouvelle langue
    @PostMapping
    public ResponseEntity<Langue> creer(@RequestBody Langue langue) {
        Langue langueCreated = this.langueService.creer(langue);
        return new ResponseEntity<>(langueCreated, HttpStatus.CREATED);
    }

    // READ - Récupérer toutes les langues
    @GetMapping
    public ResponseEntity<List<Langue>> rechercher() {
        List<Langue> langues = this.langueService.rechercher();
        return new ResponseEntity<>(langues, HttpStatus.OK);
    }

    // READ - Récupérer une langue par ID
    @GetMapping("/{id}")
    public ResponseEntity<Langue> lire(@PathVariable Integer id) {
        Langue langue = this.langueService.lire(id);
        if (langue != null) {
            return new ResponseEntity<>(langue, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // READ - Récupérer toutes les langues d'un CV
    @GetMapping("/cv/{idCv}")
    public ResponseEntity<List<Langue>> rechercherParCV(@PathVariable Integer idCv) {
        List<Langue> langues = this.langueService.rechercherParCV(idCv);
        return new ResponseEntity<>(langues, HttpStatus.OK);
    }

    // UPDATE - Mettre à jour une langue
    @PutMapping("/{id}")
    public ResponseEntity<Langue> mettre_a_jour(@PathVariable Integer id, @RequestBody Langue langue) {
        langue.setIdLangue(id);
        Langue langueUpdated = this.langueService.mettre_a_jour(langue);
        if (langueUpdated != null) {
            return new ResponseEntity<>(langueUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE - Supprimer une langue
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Integer id) {
        boolean isDeleted = this.langueService.supprimer(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
