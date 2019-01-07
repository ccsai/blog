package com.chenchuan.admin.blog.dao;

import com.chenchuan.admin.blog.po.LabelPo;
import com.chenchuan.admin.blog.vo.LabelVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 标签dao
 */
@Mapper
@Repository
public interface LabelDao {

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
     * @return 影响行数
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
     * @return 影响行数
     */
    int editLabelByLabelId(LabelVo labelVo);

    /**
     * 根据标签编号删除文章标签关联
     *
     * @param labelId 标签编号
     * @return 影响行数
     */
    int removeArticleLabelAuthByLabelId(String labelId);

    /**
     * 根据编号删除标签
     *
     * @param labelId 标签编号
     * @return 影响行数
     */
    int removeLabelByLabelId(String labelId);
}
