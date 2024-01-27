package com.project.easyApply.departmentManager.service;

import com.project.easyApply.departmentManager.model.Departamenti;

import java.util.List;

public interface DepartamentiService {

    public Departamenti createDepartamenti (Departamenti departamenti);

    public boolean checkDepartamenti (String departamenti);

    List<Departamenti> getDepartamentetByCompanyId();

    public void fshijDepartamentin(int departamentiId);

    List<Departamenti> getDepartamentByCompanyId();
}
