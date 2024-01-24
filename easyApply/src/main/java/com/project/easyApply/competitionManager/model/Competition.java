package com.project.easyApply.competitionManager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "konkursi")
@Data
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int konkursiId;
    private int departamentiId;
    private int kompaniaId;
    private String pershkrimi;
    private Date data;
    private String teDhenaShtese;
    private String statusi;
}
