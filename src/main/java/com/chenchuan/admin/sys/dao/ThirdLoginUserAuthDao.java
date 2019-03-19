package com.chenchuan.admin.sys.dao;

import com.chenchuan.admin.sys.vo.ThirdLoginUserAuthVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ThirdLoginUserAuthDao {

    /**
     * 插入第三方登录信息
     *
     * @param thirdLoginUserAuthVo
     * @return 影响行数
     */
    int addThirdLoginUserInfo(ThirdLoginUserAuthVo thirdLoginUserAuthVo);
}
