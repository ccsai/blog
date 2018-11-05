package com.chenchuan.admin.test.vo;

import com.chenchuan.admin.test.po.VisitedPo;

public class VisitedVo extends VisitedPo {

    //访问成功次数
    private  static Integer VisitedSuccessNum = 0;

    public static Integer getVisitedSuccess() {
        return VisitedSuccessNum += 1;
    }

    public static void setVisitedSuccess(Integer VisitedSuccessNum) {
        VisitedSuccessNum = VisitedSuccessNum;
    }
}
