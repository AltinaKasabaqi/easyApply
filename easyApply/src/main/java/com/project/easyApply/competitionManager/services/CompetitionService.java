package com.project.easyApply.competitionManager.services;

import com.project.easyApply.competitionManager.model.Competition;
import com.project.easyApply.userManager.model.User;

import java.util.*;

public interface CompetitionService {
    public List<Competition> getCompetitionsByCompanyId();

    public Competition saveCompetition(Competition competition);

    public Competition createCompetition(Competition competition);

    void mbylleKonkursin();

    public void fshijKonkursin(int konkursiId);

    public Optional<Competition> getCompetitionById(int konkursiId);


}
