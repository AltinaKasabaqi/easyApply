package com.project.easyApply.departmentManager.controller;

import com.project.easyApply.departmentManager.model.Departamenti;
import com.project.easyApply.departmentManager.repository.DepartamentiRepository;
import com.project.easyApply.departmentManager.service.DepartamentiService;
import com.project.easyApply.userManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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

            Departamenti newDepartamenti = new Departamenti();
            newDepartamenti.setDepartamenti(departamenti);
            newDepartamenti.setPershkrimi(pershkrimi);

            Departamenti savedDepartamenti = departamentiService.createDepartamenti(newDepartamenti);

            if (savedDepartamenti != null) {
                redirectAttributes.addFlashAttribute("msg", "Registered");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Something went wrong while saving the department");
            }
        }
        return "redirect:/user/departamentiForm";
    }
}
