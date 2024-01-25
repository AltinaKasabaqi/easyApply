package com.project.easyApply.departmentManager.controller;

//import ch.qos.logback.core.model.Model;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.List;


@Controller
public class DepartamentiController {

    @Autowired
    private DepartamentiRepository departamentiRepository;

    @Autowired
    private DepartamentiService departamentiService;

        //Listimi i departamenteve te regjistruara ne baze te id se userit
        @GetMapping("/user/profile")
        public String getDepartamentetByLoggedInUser(Model model){
            List<Departamenti> departamentet = departamentiService.getDepartamentetByCompanyId();

            model.addAttribute("departamentet",departamentet);
            System.out.println("Departamentet found:" + departamentet.size());

            return "user/profile";
        }

    @Autowired
    private UserService userService;


    @GetMapping("/user/departamentiForm")
    public String shtoDepartamentin() {
        return "user/departamentiForm";
    }


    //Shtimi i departamentit ne baze te id se userit permes formes
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
    @DeleteMapping("user/profile/{id}")
    public ResponseEntity<String> fshijDepartamentin(@PathVariable int id){
        try{
            departamentiService.fshijDepartamentin(id);
            return new ResponseEntity<>("Konkursi u fshi me sukses!",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Gabim gjate fshirjes se departamentit!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
