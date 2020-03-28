package com.fiture.service;


import com.fiture.entity.Follow;
import com.fiture.entity.FollowKey;
import com.fiture.dao.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "followService")
public class FollowService {
    @Autowired
    private FollowMapper followDao;

    public Follow getFollow(FollowKey followKey){return followDao.selectByPrimaryKey(followKey);}
    public void toFollow(Follow follow){followDao.insert(follow);};
    public void notFollow(FollowKey followKey){followDao.deleteByPrimaryKey(followKey);};
    public List findAllFollows(int userId) {
        return followDao.findAllFollows(userId);
    }
}
