package com.chenchuan.admin.blog.dao;

import com.chenchuan.admin.blog.vo.SupportVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 赞dao
 */
@Mapper
@Repository
public interface SupportDao {

    /**
     * 添加赞、踩记录
     *
     * @param supportVo
     * @return 影响行数
     */
    int addSupportData(SupportVo supportVo);

    /**
     * 删除一个月前的赞、踩数据
     * @return
     */
    int removeBeforeLastMonthSupportRecord();
}
