package com.example.maomao.myapplication.recycler;


/**
 * Created by maomao on 2017/3/11.
 */

public class Discuss extends Dto {
    private int id;
    private int to_user_id;
    private int user_id;
    private long time;
    private String header_url;
    private String chat_comment;
    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getHeader_url() {
        return header_url;
    }

    public void setHeader_url(String header_url) {
        this.header_url = header_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getChat_comment() {
        return chat_comment;
    }

    public void setChat_comment(String chat_comment) {
        this.chat_comment = chat_comment;
    }
}
