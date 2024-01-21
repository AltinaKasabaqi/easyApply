package com.project.easyApply.departmentManager.service;

import com.project.easyApply.departmentManager.model.Departamenti;
import com.project.easyApply.departmentManager.repository.DepartamentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentiServiceImpl implements DepartamentiService{

    @Autowired
    private DepartamentiRepository departamentiRepository;

    @Override
    public Departamenti createDepartamenti(Departamenti departamenti){
        return departamentiRepository.save(departamenti);
    }

    @Override
    public boolean checkDepartamenti(String departamenti) {
        return departamentiRepository.existsByDepartamenti(departamenti);
    }
}
