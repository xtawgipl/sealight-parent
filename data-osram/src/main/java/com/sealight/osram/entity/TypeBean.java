package com.osram.osram.entity;

public class TypeBean {
    private Integer typeId;

    private Integer modelId;

    private String typeFrom;

    private String typeFromYear;

    private String typeFromMonth;

    private String typeTo;

    private String typeToYear;

    private String typeToMonth;

    private String typeKw;

    private String typeAxles;

    private String typeTonnage;

    private Integer variantId;

    private String typeName;

    private String kba;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getTypeFrom() {
        return typeFrom;
    }

    public void setTypeFrom(String typeFrom) {
        this.typeFrom = typeFrom == null ? null : typeFrom.trim();
    }

    public String getTypeFromYear() {
        return typeFromYear;
    }

    public void setTypeFromYear(String typeFromYear) {
        this.typeFromYear = typeFromYear == null ? null : typeFromYear.trim();
    }

    public String getTypeFromMonth() {
        return typeFromMonth;
    }

    public void setTypeFromMonth(String typeFromMonth) {
        this.typeFromMonth = typeFromMonth == null ? null : typeFromMonth.trim();
    }

    public String getTypeTo() {
        return typeTo;
    }

    public void setTypeTo(String typeTo) {
        this.typeTo = typeTo == null ? null : typeTo.trim();
    }

    public String getTypeToYear() {
        return typeToYear;
    }

    public void setTypeToYear(String typeToYear) {
        this.typeToYear = typeToYear == null ? null : typeToYear.trim();
    }

    public String getTypeToMonth() {
        return typeToMonth;
    }

    public void setTypeToMonth(String typeToMonth) {
        this.typeToMonth = typeToMonth == null ? null : typeToMonth.trim();
    }

    public String getTypeKw() {
        return typeKw;
    }

    public void setTypeKw(String typeKw) {
        this.typeKw = typeKw == null ? null : typeKw.trim();
    }

    public String getTypeAxles() {
        return typeAxles;
    }

    public void setTypeAxles(String typeAxles) {
        this.typeAxles = typeAxles == null ? null : typeAxles.trim();
    }

    public String getTypeTonnage() {
        return typeTonnage;
    }

    public void setTypeTonnage(String typeTonnage) {
        this.typeTonnage = typeTonnage == null ? null : typeTonnage.trim();
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getKba() {
        return kba;
    }

    public void setKba(String kba) {
        this.kba = kba == null ? null : kba.trim();
    }
}