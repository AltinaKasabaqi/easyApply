package com.project.easyApply.competitionManager.services;

import com.project.easyApply.competitionManager.model.Competition;
import com.project.easyApply.userManager.model.User;

import java.util.*;

public interface CompetitionService {
    public List<Competition> getCompetitionsByCompanyId();

    

    public Competition createCompetition(Competition competition);

    void mbylleKonkursin();

    public void fshijKonkursin(int konkursiId);
}
