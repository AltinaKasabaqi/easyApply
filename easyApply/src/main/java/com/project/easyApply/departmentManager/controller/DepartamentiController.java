package com.project.easyApply.departmentManager.controller;

//import ch.qos.logback.core.model.Model;
import com.project.easyApply.competitionManager.model.Competition;
import com.project.easyApply.departmentManager.model.Departamenti;
import com.project.easyApply.departmentManager.repository.DepartamentiRepository;
import com.project.easyApply.departmentManager.service.DepartamentiService;
import com.project.easyApply.userManager.service.UserService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class DepartamentiController {

    @Autowired
    private DepartamentiRepository departamentiRepository;

    @Autowired
    private DepartamentiService departamentiService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/departamentiForm")
    public String shtoDepartamentin() {
        return "user/departamentiForm";
    }

    //Listimi i departamenteve te regjistruara ne baze te id se userit
    @GetMapping("/user/home")
    public String getDepartamentetByLoggedInUser(Model model){
        List<Departamenti> departamentet = departamentiService.getDepartamentetByCompanyId();

        model.addAttribute("departamentet",departamentet);
        System.out.println("Departamentet found:" + departamentet.size());

        return "user/home";
    }


    @PostMapping("/user/create-department")
    public String createdepartment(
            @RequestParam("departamenti") String departamenti,
            @RequestParam("pershkrimi") String pershkrimi,
            RedirectAttributes redirectAttributes) {


     try {
         Departamenti newDepartamenti = new Departamenti();
         newDepartamenti.setDepartamenti(departamenti);
         newDepartamenti.setPershkrimi(pershkrimi);

         Departamenti savedDepartamenti = departamentiService.createDepartamenti(newDepartamenti);

         if (savedDepartamenti != null) {
             redirectAttributes.addFlashAttribute("msg", "Registered");
         } else {
             redirectAttributes.addFlashAttribute("msg", "Something went wrong while saving the department");
         }
     }catch(EntityExistsException e){
         //Kompania e ka departamentin me emer tnjejte
         redirectAttributes.addFlashAttribute("msg","Ky departament ekziston tashme");
        }catch (Exception e){
         redirectAttributes.addFlashAttribute(e);
     }

        return "redirect:/user/departamentiForm";
    }

//Fshirja e departamentit ne baze te id se userit
    @DeleteMapping("user/home/{id}")
    public ResponseEntity<String> fshijDepartamentin(@PathVariable int id){
        try{
            departamentiService.fshijDepartamentin(id);
            return new ResponseEntity<>("Konkursi u fshi me sukses!",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Gabim gjate fshirjes se departamentit!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/user/competition")
    public String getCompetitionsByLoggedInUser(@RequestParam(value = "action", required = false) String action, Model model) {

        List<Departamenti> departamentet = departamentiService.getDepartamentByCompanyId();
        model.addAttribute("departamentet", departamentet);
        System.out.println("Departments found found: " + departamentet.size());
        System.out.println(departamentet);


        return "user/competition"; // Kthehu në formën e konkurimit

    }


//    @GetMapping("user/updateCompetition/{konkursiId}")
//    public String getCompetitionsByLoggedInUser2(  Model model) {
//
//        List<Departamenti> departamentes = departamentiService.getDepartamentByCompanyId();
//        model.addAttribute("departamentes", departamentes);
//        System.out.println("Departments found found: " + departamentes.size());
//        System.out.println(departamentes);
//
//
//        return "user/updateCompetition/{konkursiId}"; // Kthehu në formën e konkurimit
//
//    }

}
