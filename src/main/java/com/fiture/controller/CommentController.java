package com.fiture.controller;

import com.fiture.entity.Comment;
import com.fiture.entity.CommentKey;
import com.fiture.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "fiture评论接口")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @ApiOperation("显示fiture评论")
    @PostMapping("showComments")
    @ResponseBody
    public List showComments(@RequestParam int fitureId){
        return commentService.showComments(fitureId);
    }

    @ApiOperation("评论fiture")
    @PostMapping("toComment")
    @ResponseBody
    public boolean toComment (@RequestParam int userId,@RequestParam Date time,@RequestParam int fitureId,@RequestParam String content) {
        Comment newComment=new Comment();
        newComment.setUserid(userId);
        newComment.setTime(time);
        newComment.setFitureid(fitureId);
        newComment.setContent(content);
        commentService.toComment(newComment);
        return true;
    }

}