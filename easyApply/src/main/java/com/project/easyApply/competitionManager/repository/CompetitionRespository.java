package com.project.easyApply.competitionManager.repository;

import com.project.easyApply.competitionManager.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.*;

public interface CompetitionRespository extends JpaRepository<Competition, Integer> {

    List<Competition> findByKompaniaId(int kompaniaId);
    List<Competition> findByDataBefore(LocalDate data);

    void deleteById(int konkursiId);

    Optional<Competition> findByKonkursiId(int konkursiId);


}
