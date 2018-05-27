package com.sealight.osram.entity;

public class TechnologyBean {
    private Integer id;

    private Integer useId;

    private Integer typeId;

    private Integer technologyId;

    private String technologyName;

    public TechnologyBean(){

    }

    public TechnologyBean(Integer useId, Integer typeId, Integer technologyId, String technologyName) {
        this.useId = useId;
        this.typeId = typeId;
        this.technologyId = technologyId;
        this.technologyName = technologyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Integer technologyId) {
        this.technologyId = technologyId;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName == null ? null : technologyName.trim();
    }
}