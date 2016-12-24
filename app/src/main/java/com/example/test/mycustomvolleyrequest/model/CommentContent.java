package com.example.test.mycustomvolleyrequest.model;

import java.util.List;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      标题
 *      描述
 *      评论列表
 *      稍微有点混合的数据
 */
public class CommentContent {


    private String title;
    private String description;
    /**
     * coment : 伤心，勇士既然输了
     * time : 1467264077316
     * user : Curry逃课
     */

    private List<Comment> comentList;//评论列表

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComentList() {
        return comentList;
    }

    public void setComentList(List<Comment> comentList) {
        this.comentList = comentList;
    }

    @Override
    public String toString() {
        return "CommentContent{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", comentList=" + comentList +
                '}';
    }
}
