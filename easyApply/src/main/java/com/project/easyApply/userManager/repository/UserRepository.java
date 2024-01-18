package com.project.easyApply.userManager.repository;

import com.project.easyApply.userManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


    boolean existsByEmail(String email);

    User findByEmail(String email);


}

