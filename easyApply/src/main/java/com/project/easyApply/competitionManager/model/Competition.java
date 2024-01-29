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
    private LocalDate data;
    private String teDhenaShtese;
    private String statusi;


    // Factory metoda per me kriju kompetition
    public static Competition CreateCompetition(int departamentiId, int kompaniaId, String pershkrimi, LocalDate data, String teDhenatShtese, String statusi ){
        var competition = new Competition();

        competition.setKompaniaId(kompaniaId);
        competition.setDepartamentiId(departamentiId);
        competition.setPershkrimi(pershkrimi);
        competition.setData(data);
        competition.setTeDhenaShtese(teDhenatShtese);
        competition.setStatusi(statusi);

        return competition;
    }
}
