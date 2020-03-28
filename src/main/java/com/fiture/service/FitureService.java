package com.fiture.service;

import com.fiture.dao.FitureMapper;
import com.fiture.dao.HasLabelMapper;
import com.fiture.dao.HasPictureMapper;
import com.fiture.dao.PictureMapper;
import com.fiture.entity.Fiture;
import com.fiture.entity.HasLabelKey;
import com.fiture.entity.HasPictureKey;
import com.fiture.entity.Picture;
import com.fiture.util.FitureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service(value = "FitureService")
public class FitureService {

    @Autowired
    private PictureMapper pictureDao;

    @Autowired
    private FitureMapper fitureDao;

    @Autowired
    private HasPictureMapper hasPictureKeyDao;

    @Autowired
    private HasLabelMapper hasLabelKeyDao;

    String location="E:/wmx/uploadFiles/";

    public int insertFiture(Fiture fiture){
        fitureDao.insert(fiture);
        return fiture.getId();
    }

    public int insertPicture(Picture picture){
        pictureDao.insert(picture);
        return picture.getId();
    }

    public void insertHasPictureKey(HasPictureKey hasPictureKey){
        hasPictureKeyDao.insert(hasPictureKey);
    }

    public boolean deletePictureByFitureId(int fitureId){
        boolean deleteFlag;
        int pictureId=hasPictureKeyDao.selectPictureIdsByFitureId(fitureId);
        hasPictureKeyDao.deleteByFitureId(fitureId);
        Picture picture=pictureDao.selectByPrimaryKey(pictureId);
        String pictureName=picture.getName();
        File file=new File(location+pictureName);
        if (file.exists() && file.isFile() && file.delete())
            deleteFlag = true;
        else
            deleteFlag = false;
        pictureDao.deleteByPrimaryKey(pictureId);
        fitureDao.deleteByPrimaryKey(fitureId);
        return deleteFlag;
    }

    //当前图片记录条数
    public int countPicture(){
        return pictureDao.count();
    }

    public void modifyFitureContent(int fitureId,String content){
        fitureDao.updateContentByPrimaryKey(fitureId,content);
    }

    public List<FitureUtil> selectByAuthorId(int authorId){
        List<Fiture>fitures=fitureDao.selectByAuthorId(authorId);
        List<FitureUtil> resultUtil=new ArrayList<FitureUtil>();
        for(Fiture fiture:fitures){
            int pictureId=hasPictureKeyDao.selectPictureIdsByFitureId(fiture.getId());
            String pictureUrl=pictureDao.selectByPrimaryKey(pictureId).getUrl();
            FitureUtil fitureUtil=new FitureUtil(fiture.getContent(),pictureUrl,fiture.getId());
            resultUtil.add(fitureUtil);
        }
        return resultUtil;
    }
}
