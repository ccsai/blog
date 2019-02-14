package com.chenchuan.home.sys;

import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.controller.BaseController;
import com.chenchuan.common.exception.BaseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户controller
 */
@Controller(value = "sysHomeUserController")
@RequestMapping("/s.u")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 当前用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/findcurUserInfo")
    @ResponseBody
    public Map<String, Object> findcurUserInfo() {
        Map<String, Object> map = new HashMap<>();
        //用户信息
        UserPo userInfo = userService.getCurrentLoginUserBaseInfo();
        if (userInfo == null) {
            throw new BaseException("用户登录异常");
        }
        userInfo.setPassword(null);
        map.put("userInfo", userInfo);
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 前台用户修改用户信息
     *
     * @return 修改状态
     */
    @PostMapping("/editCurUserInfo")
    @ResponseBody
    public Map<String, Object> editCurUserInfo(UserVo userVo) {
        Map<String, Object> map = new HashMap<>();
        //当前用户
        UserPo curUser = userService.getCurrentLoginUserBaseInfo();
        if (curUser == null) {
            throw new BaseException("修改信息异常");
        }
        //用户编号
        String userId = curUser.getUserId();
        userVo.setUserId(userId);
        //修改
        map.put("resultCode", userService.modifyUserInfo(userVo));
        //修改后的用户信息
        BeanUtils.copyProperties(userService.findUserByUserId(userId), curUser);
        curUser.setPassword(null);
        map.put("userInfo", curUser);
        return map;
    }
}
