package com.sealight.osram.entity;

public class LightBean {
    private Integer id;

    private Integer typeId;

    private Integer useId;

    private Integer posId;

    private String useName;

    public LightBean(){

    }

    public LightBean(Integer typeId, Integer useId, Integer posId, String useName) {
        this.typeId = typeId;
        this.useId = useId;
        this.posId = posId;
        this.useName = useName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName == null ? null : useName.trim();
    }
}