package com.flaneur.blog.queryvo;

public class SearchBlog {
    private String title;
    private Long typeId;
    private Boolean recommend;

    public SearchBlog() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "SearchBlog{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                ", recommend=" + recommend +
                '}';
    }
}
