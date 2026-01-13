package com.CvExt.CvExtIsetKeyrus.Repository;

import com.CvExt.CvExtIsetKeyrus.entities.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
    List<Competence> findByCvIdCv(Integer idCv);
}
