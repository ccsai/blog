package com.chenchuan.admin.blog.service.impl;

import com.chenchuan.admin.blog.dao.LabelDao;
import com.chenchuan.admin.blog.po.LabelPo;
import com.chenchuan.admin.blog.service.LabelService;
import com.chenchuan.admin.blog.vo.LabelVo;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标签service实现
 */
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private UserService userService;


    @Override
    public List<LabelPo> findLabelList(LabelVo labelVo) {
        return labelDao.findLabelList(labelVo);
    }

    @Override
    @Transactional
    public int addLabel(LabelVo labelVo) {
        //标签编号
        labelVo.setLabelId(UuidUtil.getUuid());
        //登录用户
        String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
        labelVo.setCreateUser(userId);
        labelVo.setModifyUser(userId);
        //添加标签
        int affectRows = labelDao.addLabel(labelVo);
        if (affectRows <= 0) {
            throw new BaseException("添加标签失败！");
        }
        return 1;
    }

    @Override
    public LabelPo findLabelByLabelId(String labelId) {
        //查询标签详情
        LabelPo labelPo = labelDao.findLabelByLabelId(labelId);
        if (labelPo == null) {
            throw new BaseException("没有找到该标签！");
        }
        return labelPo;
    }

    @Override
    @Transactional
    public int editLabelByLabelId(LabelVo labelVo) {
        //当前登录用户
        labelVo.setModifyUser(userService.getCurrentLoginUserBaseInfo().getUserId());
        //修改标签
        int affectRows = labelDao.editLabelByLabelId(labelVo);
        if (affectRows <= 0) {
            throw new BaseException("没有标签被修改！");
        }
        return 1;
    }

    @Override
    @Transactional
    public int removeLabelByLabelId(String labelId) {
        //删除标签
        int affectRows = labelDao.removeLabelByLabelId(labelId);
        if (affectRows <= 0) {
            throw new BaseException("没有标签被删除");
        }
        //删除文章标签关联
        labelDao.removeArticleLabelAuthByLabelId(labelId);
        return 1;
    }
}
