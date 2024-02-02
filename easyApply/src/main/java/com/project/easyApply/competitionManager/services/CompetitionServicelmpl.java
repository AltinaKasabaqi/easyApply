package com.project.easyApply.competitionManager.services;

import com.project.easyApply.competitionManager.model.Competition;
import com.project.easyApply.competitionManager.repository.CompetitionRespository;
import com.project.easyApply.userManager.config.CustomUserDetails;
import com.project.easyApply.userManager.service.KompaniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.*;
import java.util.Collections;
import java.util.*;


@Service
public class CompetitionServicelmpl implements CompetitionService {

    @Autowired
    private CompetitionRespository compRepo;

    @Autowired
    private KompaniaService userService;




    @Override
    public List<Competition> getCompetitionsByCompanyId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId;

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            userId = userService.findKompaniaIdByEmail(userDetails.getUsername());
        } else {

            userId = -1;
        }
        if (userId != -1) {
            return compRepo.findByKompaniaId(userId);
        } else {

            return Collections.emptyList();
        }
    }

    @Override
    public Competition saveCompetition(Competition competition) {
        return compRepo.save(competition);
    }


public void mbylleKonkursin() {

    LocalDate currentDate = LocalDate.now();
    List<Competition> konkursatPerMbyllje = compRepo.findByDataBefore(currentDate);
    for (Competition konkursi : konkursatPerMbyllje) {
        konkursi.setStatusi("i mbyllur");
    }

    compRepo.saveAll(konkursatPerMbyllje);
}
    @Override
    public Competition createCompetition(Competition competition) {
            return compRepo.save(competition);
        }

    @Override
    public void fshijKonkursin(int konkursiId) {
        compRepo.deleteById(konkursiId);
    }

    @Override
    public Optional<Competition> getCompetitionById(int konkursiId) {
        return compRepo.findByKonkursiId(konkursiId);
    }





}





