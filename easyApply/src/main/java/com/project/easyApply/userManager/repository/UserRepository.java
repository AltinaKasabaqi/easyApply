package com.project.easyApply.userManager.repository;

import com.project.easyApply.userManager.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {


    boolean existsByEmail(String email);


}

