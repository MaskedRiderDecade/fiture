package com.fiture.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fiture.entity.Fiture;
import com.fiture.entity.HasLabelKey;
import com.fiture.entity.HasPictureKey;
import com.fiture.entity.Picture;
import com.fiture.service.FitureService;
import com.fiture.service.LabelService;
import com.fiture.service.MQProducerService;
import com.fiture.util.FitureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = "fiture内容接口")
@RestController
public class FitureController {

    @Value("${uploadFile.resourceHandler}")
    private String resourceHandler;//请求 url 中的资源映射

    @Value("${uploadFile.location}")
    private String uploadFileLocation;//上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值

    @Autowired
    private FitureService fitureService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private MQProducerService mqProducerService;

    @ApiOperation("发布fiture")
    @PostMapping("v1/fitures/release")
    @ResponseBody
    public String releaseFiture(MultipartFile multipartFile, HttpServletRequest request,String content,int authorId) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return "上传图片为空...";
        }
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        String fileName="Picture"+(fitureService.countPicture()+1)+".jpg";
        String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/") + 1) + fileName;
        System.out.println("文件访问路径：" + fileServerPath);

        File saveFile = new File(uploadFileLocation, fileName);
        multipartFile.transferTo(saveFile);//文件保存
        //传入队列以及值

        mqProducerService.send("FitureQueue", fileName+"|"+fileServerPath+"|"+authorId+"|"+content);
        System.out.println("文件保存路径：" + saveFile.getPath());
        return "<a href='" + fileServerPath + "'>" + fileServerPath + "</a>";
    }

    @ApiOperation("设置fiture标签")
    @PostMapping(value = "v1/fitures/setLabel",produces = {"application/json"})
    @ResponseBody
    public void setFitureLabel(Integer labelId,Integer fitureId) throws IOException {
        mqProducerService.send("LabelQueue", labelId+"|"+fitureId);
    }

    @ApiOperation("删除fiture")
    @PostMapping("v1/fitures/delete")
    @ResponseBody
    public void deleteFiture(Integer fitureId) throws IOException {
        mqProducerService.send("DeleteQueue", fitureId.toString());
    }

    @ApiOperation("修改fiture内容")
    @PostMapping("v1/fitures/modifyContent")
    @ResponseBody
    public void modifyContent(Integer fitureId,String content) throws IOException {
        mqProducerService.send("ModifyContentQueue", fitureId+"|"+content);
    }

    @ApiOperation("展示fiture")
    @GetMapping("v1/fitures/show")
    @ResponseBody
    public List showFiture(Integer authorId){
        List result=fitureService.selectByAuthorId(authorId);
        return result;
    }
}