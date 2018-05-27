package com.sealight.osram.entity;

public class ModelBean {
    private Integer modelId;

    private Integer manufacturerId;

    private String modelName;

    public ModelBean(){

    }

    public ModelBean(Integer manufacturerId, Integer modelId, String modelName) {
        this.modelId = modelId;
        this.manufacturerId = manufacturerId;
        this.modelName = modelName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }
}