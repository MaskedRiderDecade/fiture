package com.fiture.service;


import com.fiture.entity.Favor;
import com.fiture.entity.FavorKey;
import com.fiture.dao.FavorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "favorService")
public class FavorService {
    @Autowired
    private FavorMapper favorDao;

    public Favor getFavor(FavorKey favorKey){return favorDao.selectByPrimaryKey(favorKey);}
    public void toFavor(Favor favor){favorDao.insert(favor);};
    public void notFavor(FavorKey favorKey){favorDao.deleteByPrimaryKey(favorKey);};

}
