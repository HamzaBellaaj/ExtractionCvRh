package com.CvExt.CvExtIsetKeyrus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CvExt.CvExtIsetKeyrus.entities.Analyse;

@Repository
public interface AnalyseRepository extends JpaRepository<Analyse, Integer> {
    List<Analyse> findByIdCv(Integer idCv);

    @Query("SELECT a FROM Analyse a WHERE LOWER(a.motsClesPrincipaux) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY a.scoreGlobal DESC")
    List<Analyse> findByKeywordOrderByScoreDesc(@Param("keyword") String keyword);
}
