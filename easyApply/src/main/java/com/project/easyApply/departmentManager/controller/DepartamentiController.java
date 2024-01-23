package com.project.easyApply.departmentManager.controller;

import com.project.easyApply.departmentManager.model.Departamenti;
import com.project.easyApply.departmentManager.repository.DepartamentiRepository;
import com.project.easyApply.departmentManager.service.DepartamentiService;
import com.project.easyApply.userManager.model.User;
import com.project.easyApply.userManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

    @PostMapping("/user/create-department")

    public String createdepartment(
            @RequestParam("departamenti") String departamenti,
            @RequestParam("pershkrimi") String pershkrimi,
            RedirectAttributes redirectAttributes) {


        boolean exists = departamentiService.checkDepartamenti(departamenti);

        if (exists) {
            redirectAttributes.addFlashAttribute("msg", "Departamenti ekziston");
        } else {
            // Create a new Departamenti object
            Departamenti newDepartamenti = new Departamenti();
            newDepartamenti.setDepartamenti(departamenti);
            newDepartamenti.setPershkrimi(pershkrimi);

//            Optional<User> company = userService.getUser(9);
//
//            if(company == null){
//                return "Could not find the given company";
//            }
//
//            newDepartamenti.setKompania(company.get());

            // Save the newDepartamenti to the database
            Departamenti savedDepartamenti = departamentiService.createDepartamenti(newDepartamenti);

            if (savedDepartamenti != null) {
                redirectAttributes.addFlashAttribute("msg", "Registered");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Something went wrong while saving the department");
            }
        }
//        public String createdepartment(@ModelAttribute Departamenti departamenti,RedirectAttributes redirectAttributes) {
//        boolean exists = departamentiService.checkDepartamenti(departamenti.getDepartamenti());
//
//        if(exists){
//            redirectAttributes.addFlashAttribute("msg","Departamenti ekziston");
//        }
//        else{
//            Departamenti departamentiDetails = departamentiService.createDepartamenti(departamenti);
//
//            if(departamentiDetails != null){
//                redirectAttributes.addFlashAttribute("msg","Registered");
//            }
//            else{
//                redirectAttributes.addFlashAttribute("msg","Something is wrong!");
//            }
//        }


        return "redirect:/user/departamentiForm";
    }

}
