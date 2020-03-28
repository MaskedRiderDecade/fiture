package com.fiture.service;

import com.fiture.entity.Fiture;
import com.fiture.entity.HasLabelKey;
import com.fiture.entity.HasPictureKey;
import com.fiture.entity.Picture;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(value = "MQConsumerService")
public class MQConsumerService {

    @Autowired
    private FitureService fitureService;

    @Autowired LabelService labelService;

    @JmsListener(destination = "FitureQueue")
    public void insertPicture(String info) {
        System.out.println("FitureQueue:" + info);
        String[] resource=info.split("\\|");

        String fileName=resource[0];
        String fileServerPath=resource[1];
        int authorId= Integer.parseInt(resource[2]);
        String content=resource[3];
        System.out.println(content);

        //插入图片记录
        Picture picture=new Picture();
        picture.setName(fileName);
        picture.setUrl(fileServerPath);
        int pictureId=fitureService.insertPicture(picture);

        //插入fiture记录
        Date now = new Date();
        Fiture fiture=new Fiture();
        fiture.setAuthorid(authorId);
        fiture.setContent(content);
        fiture.setDate(now);
        int fitureId=fitureService.insertFiture(fiture);

        //插入hasPicture联系集记录
        HasPictureKey hasPictureKey=new HasPictureKey(authorId,pictureId,fitureId);
        fitureService.insertHasPictureKey(hasPictureKey);
        System.out.println("FitureQueue:" + info);
    }

    @JmsListener(destination = "LabelQueue")
    public void updateLabel(String info) {
        System.out.println("LabelQueue:" + info);
        String[] resource = info.split("\\|");
        Integer labelId=Integer.parseInt(resource[0]);
        Integer fitureId=Integer.parseInt(resource[1]);
        labelService.deleteByFitureId(fitureId);
        HasLabelKey hasLabelKey=new HasLabelKey(labelId,fitureId);
        labelService.insertHasLabelKey(hasLabelKey);
    }

    @JmsListener(destination = "DeleteQueue")
    public void deleteFiture(String info) {
        System.out.println("DeleteQueue:" + info);
        Integer fitureId=Integer.parseInt(info);
        labelService.deleteByFitureId(fitureId);
        fitureService.deletePictureByFitureId(fitureId);
    }

    @JmsListener(destination = "ModifyContentQueue")
    public void modifyContent(String info) {
        System.out.println("ModifyContentQueue:" + info);
        String[] resource = info.split("\\|");
        String content=resource[1];
        Integer fitureId=Integer.parseInt(resource[0]);
        fitureService.modifyFitureContent(fitureId,content);
    }
}
