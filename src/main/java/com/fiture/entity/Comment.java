package com.fiture.entity;

public class Comment extends CommentKey {
    private Integer fitureid;

    private String content;

    public Integer getFitureid() {
        return fitureid;
    }

    public void setFitureid(Integer fitureid) {
        this.fitureid = fitureid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}