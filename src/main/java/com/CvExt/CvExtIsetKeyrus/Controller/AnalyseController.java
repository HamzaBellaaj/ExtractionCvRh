package com.CvExt.CvExtIsetKeyrus.Controller;

import com.CvExt.CvExtIsetKeyrus.Service.AnalyseService;
import com.CvExt.CvExtIsetKeyrus.entities.Analyse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analyse")
public class AnalyseController {

    private AnalyseService analyseService;

    public AnalyseController(AnalyseService analyseService) {
        this.analyseService = analyseService;
    }

    // CREATE - Créer une nouvelle analyse
    @PostMapping
    public ResponseEntity<Analyse> creer(@RequestBody Analyse analyse) {
        Analyse analyseCreated = this.analyseService.creer(analyse);
        return new ResponseEntity<>(analyseCreated, HttpStatus.CREATED);
    }

    // READ - Récupérer toutes les analyses
    @GetMapping
    public ResponseEntity<List<Analyse>> rechercher() {
        List<Analyse> analyses = this.analyseService.rechercher();
        return new ResponseEntity<>(analyses, HttpStatus.OK);
    }

    // READ - Récupérer une analyse par ID
    @GetMapping("/{id}")
    public ResponseEntity<Analyse> lire(@PathVariable Integer id) {
        Analyse analyse = this.analyseService.lire(id);
        if (analyse != null) {
            return new ResponseEntity<>(analyse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // READ - Récupérer toutes les analyses d'un CV
    @GetMapping("/cv/{idCv}")
    public ResponseEntity<List<Analyse>> rechercherParCV(@PathVariable Integer idCv) {
        List<Analyse> analyses = this.analyseService.rechercherParCV(idCv);
        return new ResponseEntity<>(analyses, HttpStatus.OK);
    }

    // UPDATE - Mettre à jour une analyse
    @PutMapping("/{id}")
    public ResponseEntity<Analyse> mettre_a_jour(@PathVariable Integer id, @RequestBody Analyse analyse) {
        analyse.setIdAnalyse(id);
        Analyse analyseUpdated = this.analyseService.mettre_a_jour(analyse);
        if (analyseUpdated != null) {
            return new ResponseEntity<>(analyseUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE - Supprimer une analyse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Integer id) {
        boolean isDeleted = this.analyseService.supprimer(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
