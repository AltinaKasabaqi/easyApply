package com.project.easyApply.departmentManager.model;

import com.project.easyApply.userManager.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "departamenti")
@Data

public class Departamenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departamenti_id")
    private int departamentiId;

    @ManyToOne
    @JoinColumn(name = "kompania_id",nullable = true)
    private User kompania;

    @Column(name = "departamenti")
    private String departamenti;

    @Column(name = "pershkrimi")
    private String pershkrimi;

    public Departamenti() {
    }

    public Departamenti(int departamentiId, User kompania, String departamenti, String pershkrimi) {
        this.departamentiId = departamentiId;
        this.kompania = kompania;
        this.departamenti = departamenti;
        this.pershkrimi = pershkrimi;
    }

    public int getDepartamentiId() {
        return departamentiId;
    }

    public void setDepartamentiId(int departamentiId) {
        this.departamentiId = departamentiId;
    }

    public User getKompania() {
        return kompania;
    }

    public void setKompania(User kompania) {
        this.kompania = kompania;
    }

    public String getDepartamenti() {
        return departamenti;
    }

    public void setDepartamenti(String departamenti) {
        this.departamenti = departamenti;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }
}
