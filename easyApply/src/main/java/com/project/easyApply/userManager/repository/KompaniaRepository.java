package com.project.easyApply.userManager.repository;

import com.project.easyApply.userManager.model.Kompania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KompaniaRepository extends JpaRepository<Kompania, Integer> {

    boolean existsByEmail(String email);

    Kompania findByEmail(String email);
    @Query("SELECT u.id FROM Kompania u WHERE u.email = ?1")
    int findKompaniaIdByEmail(String email);
}

