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

import com.CvExt.CvExtIsetKeyrus.Service.LangueService;
import com.CvExt.CvExtIsetKeyrus.entities.Langue;

@RestController
@RequestMapping("/langue")
public class LangueController {

    @Autowired
    private LangueService langueService;

    // Créer une langue
    @PostMapping
    public Langue creer(@RequestBody Langue langue) {
        return langueService.creer(langue);
    }

    // Récupérer toutes les langues
    @GetMapping
    public List<Langue> rechercher() {
        return langueService.rechercher();
    }

    // Récupérer une langue par ID
    @GetMapping("/{id}")
    public Langue lire(@PathVariable Integer id) {
        return langueService.lire(id);
    }

    // Récupérer les langues d'un CV
    @GetMapping("/cv/{idCv}")
    public List<Langue> rechercherParCV(@PathVariable Integer idCv) {
        return langueService.rechercherParCV(idCv);
    }

    // Modifier une langue
    @PutMapping("/{id}")
    public Langue modifier(@PathVariable Integer id, @RequestBody Langue langue) {
        langue.setIdLangue(id);
        return langueService.mettre_a_jour(langue);
    }

    // Supprimer une langue
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        langueService.supprimer(id);
    }
}
