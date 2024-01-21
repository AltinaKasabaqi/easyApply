package com.project.easyApply.departmentManager.controller;

import com.project.easyApply.departmentManager.model.Departamenti;
import com.project.easyApply.departmentManager.repository.DepartamentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartamentiController {

    @Autowired
    private DepartamentiRepository departamentiRepository;

    @GetMapping("/user/departamentiForm")
    public String shtoDepartamentin() {
        return "user/departamentiForm";
    }

    @PostMapping("/user/departamentiForm")
    public String saveDepartamenti(@ModelAttribute Departamenti departamenti) {
        try {
            departamentiRepository.save(departamenti);
            return "redirect:user/dashboard";
        } catch (DataAccessException e) {
            e.printStackTrace();
            return "redirect:/user/departamentiForm?error=saveError";
        }
    }
}
