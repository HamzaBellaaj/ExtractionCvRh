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

import com.CvExt.CvExtIsetKeyrus.Service.FormationService;
import com.CvExt.CvExtIsetKeyrus.entities.Formation;

@RestController
@RequestMapping("/formation")
public class FormationController {

    @Autowired
    private FormationService formationService;

    // Créer une formation
    @PostMapping
    public Formation creer(@RequestBody Formation formation) {
        return formationService.creer(formation);
    }

    // Récupérer toutes les formations
    @GetMapping
    public List<Formation> rechercher() {
        return formationService.rechercher();
    }

    // Récupérer une formation par ID
    @GetMapping("/{id}")
    public Formation lire(@PathVariable Integer id) {
        return formationService.lire(id);
    }

    // Récupérer les formations d'un CV
    @GetMapping("/cv/{idCv}")
    public List<Formation> rechercherParCV(@PathVariable Integer idCv) {
        return formationService.rechercherParCV(idCv);
    }

    // Modifier une formation
    @PutMapping("/{id}")
    public Formation modifier(@PathVariable Integer id, @RequestBody Formation formation) {
        formation.setIdFormation(id);
        return formationService.mettre_a_jour(formation);
    }

    // Supprimer une formation
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        formationService.supprimer(id);
    }
}
