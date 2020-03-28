package com.fiture.entity;

import java.util.Date;

public class CommentKey {
    private Integer userid;

    private Date time;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}