package com.flaneur.blog.queryvo;

public class FirstPageType {

    //分类名称
    private String id;
    private String typeName;
    private Long typeTotal;

    public FirstPageType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeTotal() {
        return typeTotal;
    }

    public void setTypeTotal(Long typeTotal) {
        this.typeTotal = typeTotal;
    }

    @Override
    public String toString() {
        return "FirstPageType{" +
                "typeName='" + typeName + '\'' +
                ", total='" + typeTotal + '\'' +
                '}';
    }
}
