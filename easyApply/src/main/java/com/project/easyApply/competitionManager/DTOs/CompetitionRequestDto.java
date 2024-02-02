package com.project.easyApply.competitionManager.DTOs;

import java.time.LocalDate;

import java.time.LocalDate;

public class CompetitionRequestDto {
    private int departamentiId;
    private String pershkrimi;
    private String teDhenaShtese;
    private LocalDate data;


    public CompetitionRequestDto() {}

    public int getDepartamentiId() {
        return departamentiId;
    }

    public void setDepartamentiId(int departamentiId) {
        this.departamentiId = departamentiId;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public String getTeDhenaShtese() {
        return teDhenaShtese;
    }

    public void setTeDhenaShtese(String teDhenaShtese) {
        this.teDhenaShtese = teDhenaShtese;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
