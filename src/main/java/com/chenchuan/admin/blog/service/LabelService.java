package com.chenchuan.admin.blog.service;

import com.chenchuan.admin.blog.po.LabelPo;
import com.chenchuan.admin.blog.vo.LabelVo;

import java.util.List;

/**
 * 标签service
 */
public interface LabelService {

    /**
     * 查询标签列表
     *
     * @return 标签列表
     */
    List<LabelPo> findLabelList(LabelVo labelVo);

    /**
     * 添加标签
     *
     * @param labelVo
     * @return 添加状态
     */
    int addLabel(LabelVo labelVo);

    /**
     * 根据编号查询标签
     *
     * @param labelId 标签编号
     * @return 标签详情
     */
    LabelPo findLabelByLabelId(String labelId);

    /**
     * 根据编号修改标签
     *
     * @param labelVo
     * @return 修改状态
     */
    int editLabelByLabelId(LabelVo labelVo);

    /**
     * 根据编号删除标签
     *
     * @param labelId 标签编号
     * @return 修改状态
     */
    int removeLabelByLabelId(String labelId);
}
