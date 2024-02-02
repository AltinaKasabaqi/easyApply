package com.project.easyApply.userManager.service;

import com.project.easyApply.userManager.model.Kompania;
import com.project.easyApply.userManager.repository.KompaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class KompaniaServiceImpl implements KompaniaService{
    @Autowired
    private KompaniaRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncode;

    @Override
    public Kompania createUser(Kompania user) {
        user.setFjalekalimi(passwordEncode.encode(user.getFjalekalimi()));
        return userRepo.save(user);
    }
    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public int findKompaniaIdByEmail(String email) {
        return userRepo.findKompaniaIdByEmail((email));
    }
}
