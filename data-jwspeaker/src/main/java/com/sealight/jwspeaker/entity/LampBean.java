package com.sealight.jwspeaker.entity;

public class LampBean implements Comparable<LampBean>{
    private Integer id;

    private String brand;

    private String model;

    private String year;

    private String lampName;

    private String lampDesc;

    public LampBean(){

    }

    public LampBean(String brand, String model, String year, String lampName, String lampDesc) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.lampName = lampName;
        this.lampDesc = lampDesc;
    }

    @Override
    public int compareTo(LampBean lamp) {
        if(this.brand.compareTo(lamp.getBrand()) != 0){
            return brand.compareTo(lamp.getBrand());
        }else if(this.model.compareTo(lamp.getModel()) != 0){
            return this.model.compareTo(lamp.getModel());
        }else if(this.year.compareTo(lamp.getYear()) != 0){
            return this.year.compareTo(lamp.getYear());
        }else if(this.lampName.compareTo(lamp.getLampName()) != 0){
            return this.lampName.compareTo(lamp.getLampName());
        }else {
            return this.lampDesc.compareTo(lamp.getLampDesc());
        }
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

    public String getLampName() {
        return lampName;
    }

    public void setLampName(String lampName) {
        this.lampName = lampName == null ? null : lampName.trim();
    }

    public String getLampDesc() {
        return lampDesc;
    }

    public void setLampDesc(String lampDesc) {
        this.lampDesc = lampDesc == null ? null : lampDesc.trim();
    }
}