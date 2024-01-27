package com.project.easyApply.departmentManager.service;

import com.project.easyApply.competitionManager.model.Competition;
import com.project.easyApply.departmentManager.model.Departamenti;

import java.util.List;

public interface DepartamentiService {

    public Departamenti createDepartamenti (Departamenti departamenti);

    public boolean checkDepartamenti (String departamenti);

    public List<Departamenti> getDepartamentByCompanyId();
}
