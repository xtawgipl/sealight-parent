package com.sealight.jwspeaker.entity;

public class UrlanagerBean {
    private Integer id;

    private String typeName;

    private String url;

    public UrlanagerBean() {
    }

    public UrlanagerBean(String url, String typeName) {
        this.url = url;
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}