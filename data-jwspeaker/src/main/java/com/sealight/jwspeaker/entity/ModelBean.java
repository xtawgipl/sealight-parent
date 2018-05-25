package com.sealight.jwspeaker.entity;

public class ModelBean {
    private Integer id;

    private String brand;

    private String model;

    public ModelBean() {
    }

    public ModelBean(String brand, String model) {
        this.brand = brand;
        this.model = model;
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
}