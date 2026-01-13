package com.CvExt.CvExtIsetKeyrus.Repository;

import com.CvExt.CvExtIsetKeyrus.entities.Langue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LangueRepository extends JpaRepository<Langue, Integer> {
    List<Langue> findByCvIdCv(Integer idCv);
}
