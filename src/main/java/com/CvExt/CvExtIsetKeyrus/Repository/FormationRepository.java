package com.CvExt.CvExtIsetKeyrus.Repository;

import com.CvExt.CvExtIsetKeyrus.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Integer> {
    List<Formation> findByCvIdCv(Integer idCv);
}
