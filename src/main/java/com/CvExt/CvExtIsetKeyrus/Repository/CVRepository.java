package com.CvExt.CvExtIsetKeyrus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CvExt.CvExtIsetKeyrus.entities.CV;

@Repository
public interface CVRepository extends JpaRepository<CV, Integer> {
    List<CV> findByIdCvIn(List<Integer> ids);
}
