package com.project.easyApply.departmentManager.repository;

import com.project.easyApply.departmentManager.model.Departamenti;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface DepartamentiRepository extends JpaRepository<Departamenti,Integer> {

        boolean existsByDepartamenti (String departamenti);

        Departamenti findByDepartamenti(String departamenti);

    }
