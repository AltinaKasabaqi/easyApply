package com.project.easyApply.userManager.repository;

import com.project.easyApply.userManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findByEmail(String email);
    @Query("SELECT u.id FROM User u WHERE u.email = ?1")
    int findUserIdByEmail(String email);
}

