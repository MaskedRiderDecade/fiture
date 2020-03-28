package com.fiture.dao;

import com.fiture.entity.Comment;
import com.fiture.entity.CommentKey;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(CommentKey key);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(CommentKey key);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List findAllComments(int fitureId);
}