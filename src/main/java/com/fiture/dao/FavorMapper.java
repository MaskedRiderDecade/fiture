package com.fiture.dao;

import com.fiture.entity.Favor;
import com.fiture.entity.FavorKey;

public interface FavorMapper {
    int deleteByPrimaryKey(FavorKey key);

    int insert(Favor record);

    int insertSelective(Favor record);

    Favor selectByPrimaryKey(FavorKey key);

    int updateByPrimaryKeySelective(Favor record);

    int updateByPrimaryKey(Favor record);
}