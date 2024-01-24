package com.project.easyApply.competitionManager.repository;

import com.project.easyApply.competitionManager.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CompetitionRespository extends JpaRepository<Competition, Integer> {

    List<Competition> findByKompaniaId(int kompaniaId);
    List<Competition> findByDataBefore(Date data);

    void deleteById(int konkursiId);


}
