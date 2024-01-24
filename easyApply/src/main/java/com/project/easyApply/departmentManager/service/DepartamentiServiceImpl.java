package com.project.easyApply.departmentManager.service;

import com.project.easyApply.departmentManager.model.Departamenti;
import com.project.easyApply.departmentManager.repository.DepartamentiRepository;
import com.project.easyApply.userManager.config.CustomUserDetails;
import com.project.easyApply.userManager.service.UserService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartamentiServiceImpl implements DepartamentiService{

    @Autowired
    private DepartamentiRepository departamentiRepository;

    @Autowired
    private UserService userService;

    @Override
    public Departamenti createDepartamenti(Departamenti departamenti){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getPrincipal() instanceof CustomUserDetails){
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            int userId = userService.findUserIdByEmail(userDetails.getUsername());
            departamenti.setKompania(userId);
            //Check departamenti for the given company
            Optional<Departamenti> existingDepartamenti = departamentiRepository.findByDepartamentiAndKompania(departamenti.getDepartamenti(),userId);

            if(existingDepartamenti.isPresent()){
                throw new EntityExistsException("Departametni me kete emer ekziston!");
            }else{
                return departamentiRepository.save(departamenti);
            }
        }
        return new Departamenti();
    }

    @Override
    public boolean checkDepartamenti(String departamenti) {
        return departamentiRepository.existsByDepartamenti(departamenti);
    }


    }

