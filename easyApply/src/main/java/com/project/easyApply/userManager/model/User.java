package com.project.easyApply.userManager.model;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name="kompania")
@Data


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kompania_id;
    private String emri;
    private String mbiemri;
    private String email;
    private String fjalekalimi;
    private String iVerifikuar;
    private String lokacioni;
    private String industria;
    private String emriKompanise;

}


