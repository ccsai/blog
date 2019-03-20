package com.chenchuan.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenchuan.admin.sys.dao.ThirdLoginUserAuthDao;
import com.chenchuan.admin.sys.dao.UserDao;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.ThirdLoginUserAuthVo;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.service.LoginService;
import com.chenchuan.common.shiro.LoginType;
import com.chenchuan.common.shiro.MyUsernamePasswordToken;
import com.chenchuan.common.util.UuidUtil;
import com.chenchuan.common.util.httpclient.HttpClientResult;
import com.chenchuan.common.util.httpclient.HttpClientUtil;
import com.chenchuan.config.oauth.QqOAuthConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录登出相关service实现
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private QqOAuthConfig qqOAuthConfig;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ThirdLoginUserAuthDao thirdLoginUserAuthDao;


    @Override
    @Transactional
    public void login(UserVo userVo) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        //收集用户登录凭证
        MyUsernamePasswordToken usernamePasswordToken = new MyUsernamePasswordToken(userVo.getLoginName(), userVo.getPassword(), LoginType.NORMAL.getDesc());
        //认证
        subject.login(usernamePasswordToken);
        //当前登录用户,修改登录时间
        UserPo curLoginUser = userService.getCurrentLoginUserBaseInfo();
        UserVo userVo1 = new UserVo();
        userVo1.setUserId(curLoginUser.getUserId());
        userVo1.setLastLoginTime(new Date());
        userDao.editUserByUserId(userVo1);
    }

    @Override
    @Transactional
    public void qqLogin(String code) {
        //获取access_token的请求参数
        HashMap<String, String> getTokenParams = new HashMap<>();
        getTokenParams.put("grant_type", qqOAuthConfig.getTokenGrantType());
        getTokenParams.put("client_id", qqOAuthConfig.getAppId());
        getTokenParams.put("client_secret", qqOAuthConfig.getAppKey());
        getTokenParams.put("code", code);
        getTokenParams.put("redirect_uri", qqOAuthConfig.getRedirectUri());
        //获取access_token
        HttpClientResult resultOfGetAcccessToken = null;
        try {
            resultOfGetAcccessToken = HttpClientUtil.doGet(qqOAuthConfig.getGetAccessTokenUri(), getTokenParams);
        } catch (Exception e) {
            throw new BaseException("QQ登录异常");
        }
        //获取access_token返回参数
        String resultParamsOfGetToken = resultOfGetAcccessToken.getContent();
        if (resultOfGetAcccessToken.getCode() != 200 || (resultParamsOfGetToken == null || resultParamsOfGetToken.equals(""))) {
            throw new BaseException("QQ登录异常");
        }
        //处理获取access_token返回参数成json
        String[] tokenResultString = resultParamsOfGetToken.split("&");
        Map<String, String> tokenResultMap = new HashMap<>();
        for (String s : tokenResultString) {
            String keyValue[] = s.split("=");
            if (keyValue.length > 1) {
                tokenResultMap.put(keyValue[0], keyValue[1]);
            }
        }
        //access_token
        String accessToken = tokenResultMap.get("access_token");
        //获取openid
        HttpClientResult resultOfGetOpenid = null;
        try {
            resultOfGetOpenid = HttpClientUtil.doGet(qqOAuthConfig.getGetOpenidUri() + "?access_token=" + accessToken);
        } catch (Exception e) {
            throw new BaseException("QQ登录异常");
        }
        //处理获取openid结果
        String resultOfOpenidContent = resultOfGetOpenid.getContent();
        if (resultOfGetOpenid.getCode() != 200 || (resultOfOpenidContent == null || resultOfOpenidContent.equals(""))) {
            throw new BaseException("QQ登录异常");
        }
        JSONObject resultByGetOpenIdJson = JSON.parseObject(resultOfOpenidContent.replaceAll("callback\\( | \\);", "").trim());
        //openid
        String openid = (String) resultByGetOpenIdJson.get("openid");
        if (resultByGetOpenIdJson.get("client_id") == null || openid == null) {
            throw new BaseException("QQ登录异常");
        }
        //获取用户信息
        HttpClientResult resultByGetUserInfo = null;
        try {
            resultByGetUserInfo = HttpClientUtil.doGet(qqOAuthConfig.getGetUserInfoUri() +
                    "?access_token=" + accessToken +
                    "&oauth_consumer_key=" + qqOAuthConfig.getAppId() +
                    "&openid=" + openid);
        } catch (Exception e) {
            throw new BaseException("QQ登录异常");
        }
        //处理用户数据
        JSONObject qqUserInfoJson = JSON.parseObject(resultByGetUserInfo.getContent());
        //qq头像
        String chatHead = (String) qqUserInfoJson.get("figureurl_qq_1");
        //qq昵称
        String nickname = (String) qqUserInfoJson.get("nickname");
        //用户名
        String loginName = "QQ用户_" + nickname;
        //验证数据库是否存在该用户名
        UserVo userVo = new UserVo();
        userVo.setLoginName(loginName);
        //用户编号
        String userId = null;
        //qq用户关联处理条件
        ThirdLoginUserAuthVo thirdLoginUserAuthVo = new ThirdLoginUserAuthVo();
        thirdLoginUserAuthVo.setOpenid(openid);
        thirdLoginUserAuthVo.setType("qq");
        //登录时的密码
        String loginPassword = "";
        //根据openid判断该qq是否存在
        if (userDao.findQqUserNumberByOpenid(openid) == 0) {
            //验证数据库是否存在该用户名
            if (userDao.findLoginNameNumberByLoginName(userVo) > 0) {
                throw new BaseException("该QQ的昵称已经被占用，可以选择修改QQ昵称再登录！");
            }
            //添加用户
            userId = UuidUtil.getUuid();
            userVo.setUserId(userId);
            userVo.setPassword(loginPassword);
            userVo.setLoginType(0);
            userVo.setStatus(1);
            userVo.setCreateUser(userId);
            userVo.setModifyUser(userId);
            userDao.addUser(userVo);
            //添加qq登录关联信息
            thirdLoginUserAuthVo.setThridId(UuidUtil.getUuid());
            thirdLoginUserAuthVo.setUserId(userId);
            thirdLoginUserAuthVo.setAccessToken(accessToken);
            thirdLoginUserAuthVo.setChatHead(chatHead);
            thirdLoginUserAuthDao.addThirdLoginUserInfo(thirdLoginUserAuthVo);
        } else {
            //获取qq用户基本信息
            UserPo userBaseInfo = userDao.findThirdLoginUserByOpenId(thirdLoginUserAuthVo);
            if (!loginName.equals(userBaseInfo.getLoginName())) {
                //验证数据库是否存在该用户名
                if (userDao.findLoginNameNumberByLoginName(userVo) > 0) {
                    throw new BaseException("该QQ的昵称已经被占用，可以选择修改QQ昵称再登录！");
                }
                //修改用户名
                userVo.setUserId(userBaseInfo.getUserId());
                userDao.editUserByUserId(userVo);
                loginPassword = userBaseInfo.getPassword();
            }
        }
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        //收集用户登录凭证
        MyUsernamePasswordToken usernamePasswordToken = new MyUsernamePasswordToken(loginName, LoginType.FREE.getDesc());
        //认证
        subject.login(usernamePasswordToken);
        //当前登录用户,修改登录时间
        UserPo curLoginUser = userService.getCurrentLoginUserBaseInfo();
        UserVo userVo1 = new UserVo();
        userVo1.setUserId(curLoginUser.getUserId());
        userVo1.setLastLoginTime(new Date());
        userDao.editUserByUserId(userVo1);
    }

    @Override
    public void logOut() {
        Subject subject = SecurityUtils.getSubject();
        //登出
        subject.logout();
    }
}
