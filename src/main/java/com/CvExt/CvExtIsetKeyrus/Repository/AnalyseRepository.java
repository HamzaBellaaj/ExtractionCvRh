package com.CvExt.CvExtIsetKeyrus.Repository;

import com.CvExt.CvExtIsetKeyrus.entities.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyseRepository extends JpaRepository<Analyse, Integer> {
    List<Analyse> findByCvIdCv(Integer idCv);
}
