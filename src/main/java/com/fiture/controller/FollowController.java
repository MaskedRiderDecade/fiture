package com.fiture.controller;

import com.fiture.entity.Follow;
import com.fiture.entity.FollowKey;
import com.fiture.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(tags = "用户关注接口")
public class FollowController {
    @Autowired
    private FollowService followService;

    @ApiOperation("获取是否关注")
    @GetMapping("getFollow")
    @ResponseBody
    public boolean getFollowState(int userId,int isFollowedId){
        FollowKey key=new FollowKey();
        key.setIsfollowedid(isFollowedId);
        key.setUserid(userId);
        Follow follow=followService.getFollow(key);
        return follow != null;
    }

    @ApiOperation("改变关注状态")
    @PostMapping("toOrCancelFollow")
    @ResponseBody
    public int toOrCancelFollow (int userId,int isFollowedId) {
        FollowKey key = new FollowKey();
        key.setIsfollowedid(isFollowedId);
        key.setUserid(userId);
        Follow follow = followService.getFollow(key);
        if (follow == null) {
            Follow toFollow = new Follow();
            toFollow.setUserid(userId);
            toFollow.setIsfollowedid(isFollowedId);
            toFollow.setIsfollowed(true);
            followService.toFollow(toFollow);
            return 1;
        } else {
            followService.notFollow(follow);
            return 0;
        }
    }

    @ApiOperation("显示关注列表")
    @PostMapping("showFollows")
    @ResponseBody
    public List showFollows(int userId){
        return followService.findAllFollows(userId);
    }
}