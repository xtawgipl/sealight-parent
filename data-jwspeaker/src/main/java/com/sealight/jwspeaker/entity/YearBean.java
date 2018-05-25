package com.sealight.jwspeaker.entity;

public class YearBean {
    private Integer id;

    private String brand;

    private String model;

    private String year;

    public YearBean() {
    }

    public YearBean(String brand, String model, String year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }
}