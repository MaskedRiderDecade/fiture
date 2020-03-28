package com.fiture.util;

public class FitureUtil {
    String content;
    String pictureUrl;
    int fitureId;

    public FitureUtil(String content,String pictureUrl,int fitureId){
        this.content=content;
        this.pictureUrl=pictureUrl;
        this.fitureId=fitureId;
    }

    public String getContent(){
        return content;
    }

    public String getPictureUrl(){
        return pictureUrl;
    }

    public int getFitureId(){
        return fitureId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPictureUrl(String pictureUrl){
        this.pictureUrl=pictureUrl;
    }
}
