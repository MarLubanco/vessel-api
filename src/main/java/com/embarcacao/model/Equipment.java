package com.embarcacao.model;

import javax.persistence.*;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String code;
    private String location;
    private boolean status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vessel", nullable = false)
    private Vessel vessel;

    public Equipment() {
    }

    public Equipment(Integer id, String name, String code, String location, boolean status, Vessel vessel) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.location = location;
        this.status = status;
        this.vessel = vessel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }
}
