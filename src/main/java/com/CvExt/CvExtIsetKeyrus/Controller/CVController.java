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

import com.CvExt.CvExtIsetKeyrus.Service.CVService;
import com.CvExt.CvExtIsetKeyrus.entities.CV;

@RestController
@RequestMapping("/cv")
public class CVController {

    @Autowired
    private CVService cvService;

    // Créer un CV
    @PostMapping
    public CV creer(@RequestBody CV cv) {
        return cvService.creer(cv);
    }

    // Récupérer tous les CVs
    @GetMapping
    public List<CV> rechercher() {
        return cvService.rechercher();
    }

    // Récupérer un CV par ID
    @GetMapping("/{id}")
    public CV lire(@PathVariable Integer id) {
        return cvService.lire(id);
    }

    // Modifier un CV
    @PutMapping("/{id}")
    public CV modifier(@PathVariable Integer id, @RequestBody CV cv) {
        cv.setIdCv(id);
        return cvService.mettre_a_jour(cv);
    }

    // Supprimer un CV
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        cvService.supprimer(id);
    }

    // Récupérer plusieurs CVs par liste d'IDs
    @GetMapping("/batch")
    public List<CV> rechercherParIds(@RequestParam List<Integer> ids) {
        return cvService.rechercherParIds(ids);
    }
}
