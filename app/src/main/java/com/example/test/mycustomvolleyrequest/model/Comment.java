package com.example.test.mycustomvolleyrequest.model;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      简单数据
 */
public class Comment {
    private String coment;
    private String time;
    private String user;

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "coment='" + coment + '\'' +
                ", time='" + time + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
