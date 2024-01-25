package com.project.easyApply.departmentManager.repository;

import com.project.easyApply.departmentManager.model.Departamenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface DepartamentiRepository extends JpaRepository<Departamenti,Integer> {

        boolean existsByDepartamenti (String departamenti);

        Optional<Departamenti> findByDepartamentiAndKompania(String departamenti,int kompania);

    List<Departamenti> findByKompania(int kompania);


    void deleteById(int departamentiId);
}
