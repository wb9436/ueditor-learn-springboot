package com.sunyoo.article.dto;

import java.io.Serializable;

public class ArticleInfoDto implements Serializable {
    private static final long serialVersionUID = -2259096600754366465L;

    private Integer id;
    private String title;
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
