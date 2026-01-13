package com.CvExt.CvExtIsetKeyrus.Repository;

import com.CvExt.CvExtIsetKeyrus.entities.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CVRepository extends JpaRepository<CV, Integer> {
}
