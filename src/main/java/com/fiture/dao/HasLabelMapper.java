package com.fiture.dao;

import com.fiture.entity.HasLabelKey;

public interface HasLabelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table haslabel
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(HasLabelKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table haslabel
     *
     * @mbggenerated
     */
    int insert(HasLabelKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table haslabel
     *
     * @mbggenerated
     */
    int insertSelective(HasLabelKey record);

    void deleteByFitureId(int fitureId);
}