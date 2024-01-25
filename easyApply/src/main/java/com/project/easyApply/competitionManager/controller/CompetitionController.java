package com.project.easyApply.competitionManager.controller;
import com.project.easyApply.userManager.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.project.easyApply.competitionManager.model.Competition;
import com.project.easyApply.competitionManager.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("user/dashboard")
    public String getCompetitionsByLoggedInUser(Model model) {
        mbylleKonkursin();
        List<Competition> competitions = competitionService.getCompetitionsByCompanyId();

//        if (competitions != null && !competitions.isEmpty()) {
            model.addAttribute("competitions", competitions);
            System.out.println("Competitions found: " + competitions.size());
//        } else {
//            System.out.println("No competitions found");
//            // Vendosni një mesazh nëse nuk ka konkurse
//            model.addAttribute("noCompetitions", "No competitions found");
//        }

        return "user/dashboard";
    }

    @PostMapping("/user/createCompetition")
    public String createCompetition(@ModelAttribute Competition competition, RedirectAttributes redirectAttributes) {
         competitionService.createCompetition(competition);
        return "redirect:/signup";
    }

//    @PostMapping("/update")
//    public void saveCompetition(@RequestBody Competition competition) {
//        competitionService.saveCompetition(competition);
//    }


    @GetMapping("/mbylle")
    public String mbylleKonkursin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            competitionService.mbylleKonkursin();

            return "Konkursi është mbyllur.";
        } else {
            return "Ju nuk jeni kyçur. Nuk keni leje për të mbyllur konkursin.";
        }
    }

    @DeleteMapping("user/dashboard/{id}")
    public ResponseEntity<String> fshijKonkursin(@PathVariable int id) {
        try {
            competitionService.fshijKonkursin(id);
            return new ResponseEntity<>("Konkursi u fshi me sukses.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Gabim gjatë fshirjes së konkursit.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
