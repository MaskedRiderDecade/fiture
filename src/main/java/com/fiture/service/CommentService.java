package com.fiture.service;


import com.fiture.entity.Comment;
import com.fiture.entity.CommentKey;
import com.fiture.dao.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "commetService")
public class CommentService {
    @Autowired
    private CommentMapper commentDao;


    public void toComment(Comment comment){commentDao.insert(comment);};
    public List showComments(int fitureId){return commentDao.findAllComments(fitureId);};


}
