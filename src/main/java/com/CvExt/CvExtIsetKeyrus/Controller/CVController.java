package com.CvExt.CvExtIsetKeyrus.Controller;

import com.CvExt.CvExtIsetKeyrus.Service.CVService;
import com.CvExt.CvExtIsetKeyrus.entities.CV;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv")
public class CVController {

    private CVService cvService;

    public CVController(CVService cvService) {
        this.cvService = cvService;
    }

    @PostMapping
    public ResponseEntity<CV> creer(@RequestBody CV cv) {
        CV cvCreated = this.cvService.creer(cv);
        return new ResponseEntity<>(cvCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CV>> rechercher() {
        List<CV> cvs = this.cvService.rechercher();
        return new ResponseEntity<>(cvs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CV> lire(@PathVariable Integer id) {
        CV cv = this.cvService.lire(id);
        if (cv != null) {
            return new ResponseEntity<>(cv, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CV> mettre_a_jour(@PathVariable Integer id, @RequestBody CV cv) {
        cv.setIdCv(id);
        CV cvUpdated = this.cvService.mettre_a_jour(cv);
        if (cvUpdated != null) {
            return new ResponseEntity<>(cvUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Integer id) {
        boolean isDeleted = this.cvService.supprimer(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
