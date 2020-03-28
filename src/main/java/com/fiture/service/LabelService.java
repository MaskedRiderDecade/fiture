package com.fiture.service;

import com.fiture.dao.FitureMapper;
import com.fiture.dao.HasLabelMapper;
import com.fiture.dao.HasPictureMapper;
import com.fiture.dao.PictureMapper;
import com.fiture.entity.Fiture;
import com.fiture.entity.HasLabelKey;
import com.fiture.entity.HasPictureKey;
import com.fiture.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service(value = "LabelService")
public class LabelService {

    @Autowired
    private HasPictureMapper hasPictureKeyDao;

    @Autowired
    private HasLabelMapper hasLabelKeyDao;

    public void insertHasPictureKey(HasPictureKey hasPictureKey){
        hasPictureKeyDao.insert(hasPictureKey);
    }

    public void insertHasLabelKey(HasLabelKey hasLabelKey){
        hasLabelKeyDao.insert(hasLabelKey);
    }

    public void deleteByFitureId(int fitureId){
        hasLabelKeyDao.deleteByFitureId(fitureId);
    }
}
