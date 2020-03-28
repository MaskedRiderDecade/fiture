package com.fiture.controller;

import com.fiture.entity.Favor;
import com.fiture.entity.FavorKey;
import com.fiture.service.FavorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "fiture收藏接口")
@RestController
public class FavorController {
    @Autowired
    private FavorService favorService;

    @ApiOperation("获取是否收藏")
    @GetMapping("getFavor")
    @ResponseBody
    public boolean getFavorState(int userId,int fitureId){
        FavorKey key=new FavorKey();
        key.setFitureid(fitureId);
        key.setUserid(userId);
        Favor follow=favorService.getFavor(key);
        return follow != null;
    }

    @ApiOperation("改变收藏状态")
    @PostMapping("toOrCancelFavor")
    @ResponseBody
    public int toOrCancelFollow (int userId,int fitureId) {
        FavorKey key = new FavorKey();
        key.setFitureid(fitureId);
        key.setUserid(userId);
        Favor follow = favorService.getFavor(key);
        if (follow == null) {
            Favor toFollow = new Favor();
            toFollow.setUserid(userId);
            toFollow.setFitureid(fitureId);
            toFollow.setIsfavored(true);
            favorService.toFavor(toFollow);
            return 1;
        } else {
            favorService.notFavor(follow);
            return 0;
        }
    }

}