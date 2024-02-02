package com.project.easyApply.userManager.service;

import com.project.easyApply.userManager.model.Kompania;

public interface KompaniaService {

    public Kompania createUser(Kompania user);

    public boolean checkEmail (String email);

    public int findKompaniaIdByEmail(String email);


}
