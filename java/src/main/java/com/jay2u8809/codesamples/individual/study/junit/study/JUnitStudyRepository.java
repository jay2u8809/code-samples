package com.jay2u8809.codesamples.individual.study.junit.study;

import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitStudy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JUnitStudyRepository extends JpaRepository<JUnitStudy, Long> {
}