package com.fiture.entity;

import java.util.Date;

public class Fiture {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fiture.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fiture.authorId
     *
     * @mbggenerated
     */
    private Integer authorid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fiture.date
     *
     * @mbggenerated
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fiture.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fiture.id
     *
     * @return the value of fiture.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fiture.id
     *
     * @param id the value for fiture.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fiture.authorId
     *
     * @return the value of fiture.authorId
     *
     * @mbggenerated
     */
    public Integer getAuthorid() {
        return authorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fiture.authorId
     *
     * @param authorid the value for fiture.authorId
     *
     * @mbggenerated
     */
    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fiture.date
     *
     * @return the value of fiture.date
     *
     * @mbggenerated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fiture.date
     *
     * @param date the value for fiture.date
     *
     * @mbggenerated
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fiture.content
     *
     * @return the value of fiture.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fiture.content
     *
     * @param content the value for fiture.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}