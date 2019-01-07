package com.chenchuan.admin.blog.service.impl;

import com.chenchuan.admin.blog.dao.FriendlyLinkDao;
import com.chenchuan.admin.blog.po.FriendlyLinkPo;
import com.chenchuan.admin.blog.service.FriendlyLinkServie;
import com.chenchuan.admin.blog.vo.FriendlyLinkVo;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 友情链接service实现
 */
@Service
public class FriendlyLinkServieImpl implements FriendlyLinkServie {

    @Autowired
    private FriendlyLinkDao friendlyLinkDao;

    @Autowired
    private UserService userService;


    @Override
    public List<FriendlyLinkPo> findFriendlyLinkList(FriendlyLinkVo friendlyLinkVo) {
        return friendlyLinkDao.findFriendlyLinkList(friendlyLinkVo);
    }

    @Override
    @Transactional
    public int addFriendlyLink(FriendlyLinkVo friendlyLinkVo) {
        //当前登录用户
        String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
        friendlyLinkVo.setCreateUser(userId);
        friendlyLinkVo.setModifyUser(userId);
        //主键
        friendlyLinkVo.setFriendlyLinkId(UuidUtil.getUuid());
        //添加
        int affectRows = friendlyLinkDao.addFriendlyLink(friendlyLinkVo);
        if (affectRows <= 0) {
            throw new BaseException("添加友情链接失败！");
        }
        return 1;
    }

    @Override
    public FriendlyLinkPo findFriendlyLinkByFriendlyLinkId(String friendlyLinkId) {
        //查询友链详情
        FriendlyLinkPo friendlyLinkPo = friendlyLinkDao.findFriendlyLinkByFriendlyLinkId(friendlyLinkId);
        if (friendlyLinkId == null) {
            throw new BaseException("没有本条友情链接数据！");
        }
        return friendlyLinkPo;
    }

    @Override
    @Transactional
    public int editFriendlyLinkByFriendlyLinkId(FriendlyLinkVo friendlyLinkVo) {
        //当前用户
        //friendlyLinkVo.setModifyUser(userService.getCurrentLoginUserBaseInfo().getUserId());
        //修改友链
        int affectRows = friendlyLinkDao.editFriendlyLinkByFriendlyLinkId(friendlyLinkVo);
        if (affectRows <= 0) {
            throw new BaseException("没有友情链接被修改！");
        }
        return 1;
    }

    @Override
    @Transactional
    public int removeFriendlyLinkByFriendlyLinkId(String friendlyLinkId) {
        //删除友链
        int affectRows = friendlyLinkDao.removeFriendlyLinkByFriendlyLinkId(friendlyLinkId);
        if (affectRows <= 0) {
            throw new BaseException("没有友情链接被删除！");
        }
        return 1;
    }
}
