package com.project.easyApply.competitionManager.controller;

import com.project.easyApply.competitionManager.DTOs.CompetitionRequestDto;
import com.project.easyApply.departmentManager.model.Departamenti;
import com.project.easyApply.departmentManager.service.DepartamentiService;
import com.project.easyApply.userManager.config.CustomUserDetails;
import com.project.easyApply.userManager.service.KompaniaService;
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
import java.util.Optional;

@Controller
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private KompaniaService userService;

    @Autowired
    private DepartamentiService departamentiService;


    @GetMapping("/competiton")
    public String shtoCompetition() {
        return "user/competition";
    }


    @GetMapping("/updateCompetition/{konkursiId}")
    public String updateCompetition(@PathVariable int konkursiId, Model model) {

        List<Departamenti> departamentes = departamentiService.getDepartamentByCompanyId();
        model.addAttribute("departamentes", departamentes);
        System.out.println("Departments found found: " + departamentes.size());
        System.out.println(departamentes);

        Optional<Competition> competition = competitionService.getCompetitionById(konkursiId);
        model.addAttribute("competition", competition.orElse(null)); // Ose null nëse nuk gjen konkursin
        System.out.println(competition);

        // Merr listën e konkurrencave dhe shto ato në model
        List<Competition> competitions = competitionService.getCompetitionsByCompanyId();
        model.addAttribute("competitions", competitions);

        return "user/updateCompetition";
    }


    @GetMapping("user/dashboard")
    public String getCompetitionsByLoggedInUser(Model model) {
        mbylleKonkursin();
        List<Competition> competitions = competitionService.getCompetitionsByCompanyId();

        model.addAttribute("competitions", competitions);
        System.out.println("Competitions found: " + competitions.size());

        return "user/dashboard";
    }

    @PostMapping("/user/createCompetition")
    public String createCompetition(@ModelAttribute CompetitionRequestDto competitionRequestDto, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            int userId = userService.findKompaniaIdByEmail(userDetails.getUsername());

            var competition = Competition.CreateCompetition(competitionRequestDto.getDepartamentiId(),
                    userId,
                    competitionRequestDto.getPershkrimi(),
                    competitionRequestDto.getData(),
                    competitionRequestDto.getTeDhenaShtese(),
                    "I hapur");

            competitionService.createCompetition(competition);

        }
        return "redirect:/user/competition";
    }


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

    @PostMapping("/updateCompetition/{konkursiId}")
    public String editCompetition(@PathVariable int konkursiId, @ModelAttribute Competition editedCompetition) {
        System.out.println("Received request to update competition with ID: " + konkursiId);

        Optional<Competition> existingCompetition = competitionService.getCompetitionById(konkursiId);

        if (existingCompetition.isPresent()) {
            System.out.println("Existing competition found for ID: " + konkursiId);
            Competition updatedCompetition = existingCompetition.get();

            updatedCompetition.setDepartamentiId(editedCompetition.getDepartamentiId());
            updatedCompetition.setPershkrimi(editedCompetition.getPershkrimi());
            updatedCompetition.setData(editedCompetition.getData());
            updatedCompetition.setTeDhenaShtese(editedCompetition.getTeDhenaShtese());

            // Save the updated competition
            competitionService.saveCompetition(updatedCompetition);
            System.out.println("Competition with ID " + konkursiId + " updated successfully.");

            return "redirect:/user/dashboard";
        } else {
            System.out.println("Competition with ID " + konkursiId + " not found.");
            return "redirect:/user/updateCompetition";
        }
    }

}
