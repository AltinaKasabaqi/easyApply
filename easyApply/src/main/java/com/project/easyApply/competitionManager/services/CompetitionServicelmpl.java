package com.project.easyApply.competitionManager.services;

import com.project.easyApply.competitionManager.model.Competition;
import com.project.easyApply.competitionManager.repository.CompetitionRespository;
import com.project.easyApply.userManager.config.CustomUserDetails;
import com.project.easyApply.userManager.model.User;
import com.project.easyApply.userManager.service.UserService;
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
    private UserService userService;




    @Override
    public List<Competition> getCompetitionsByCompanyId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId;

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            userId = userService.findUserIdByEmail(userDetails.getUsername());
        } else {
            // Ju mund të vendosni një vlerë parazgjedhëse nëse përdoruesi nuk është i loguar
            userId = -1;
        }
        if (userId != -1) {
            return compRepo.findByKompaniaId(userId);
        } else {
            // Kthimi i një listë bosh ose një mesazh tjetër sipas rastit
            return Collections.emptyList(); // ose null, ose një listë bosh, ose një mesazh tjetër sipas rastit
        }
    }

    @Override
    public Competition saveCompetition(Competition competition) {
        return compRepo.save(competition);
    }

    //    public void saveCompetition() {
//        // Logjika ekzistuese për ruajtjen e konkursit
//
//        // Përdor logjikën për caktimin e statusit bazuar në datën e caktuar
//        if (competition.getData().isBefore(LocalDate.now())) {
//            competition.setStatusi("i mbyllur");
//        }
//
//        // Ruaj konkursin në bazën e të dhënave
//        compRepo.save(competition);
//    }
public void mbylleKonkursin() {
    // Merr datën e sotme
//    Date currentDate = new Date();
    LocalDate currentDate = LocalDate.now();


    // Merr konkursat që duhet të mbyllen bazuar në datën e sotme dhe datën e përfundimit
    List<Competition> konkursatPerMbyllje = compRepo.findByDataBefore(currentDate);

    // Ndrysho statusin e konkursit në "i mbyllur"
    for (Competition konkursi : konkursatPerMbyllje) {
        konkursi.setStatusi("i mbyllur");
    }

    // Ruaj ndryshimet në bazën e të dhënave
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





