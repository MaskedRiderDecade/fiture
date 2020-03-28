package com.fiture.dao;

import com.fiture.entity.Follow;
import com.fiture.entity.FollowKey;

import java.util.List;

public interface FollowMapper {
    int deleteByPrimaryKey(FollowKey key);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(FollowKey key);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);

    List findAllFollows(int userId);
}