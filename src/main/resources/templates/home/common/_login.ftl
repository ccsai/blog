<#-- 登录模态框 -->
<div class="modal fade" id="login">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">登录</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
            <#-- 登录表单 -->
                <form id="login-form">
                    <div class="form-group">
                        <label for="l-login-name">用户名</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-person" title="用户" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="l-login-name" class="form-control" name="loginName" placeholder="用户名"
                                   type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="l-pwd">密码</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-lock-locked" title="密码" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="l-pwd" class="form-control" name="password" placeholder="密码" type="password">
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary btn-block">登录</button>
                    </div>
                </form>

            <#-- 登录异常 -->
                <div class="d-sm-flex justify-content-sm-between">
                    <div>
                        <span>没有账号？</span><a class="register-href" href="javascript:void(0)">注册</a>
                    </div>
                    <div>
                        <a class="forget-pwd-href" href="javascript:void(0)">忘记密码？</a>
                    </div>
                </div>

                <hr>
                <div>
                    <div>
                        <button class="btn p-0 qq-login-btn">
                            <img src="${basePath!}/img/home/icon/qqLoginButton.png">
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<#-- 注册模态框 -->
<div class="modal fade" id="register">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">注册</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
            <#-- 注册表单 -->
                <form id="register-form" action="">
                    <div class="form-group">
                        <label for="r-login-name">用户名</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-person" title="用户" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="r-login-name" class="form-control" name="loginName" placeholder="用户名"
                                   type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="r-real-name">姓名</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-person" title="姓名" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="r-real-name" class="form-control" name="realName" placeholder="姓名"
                                   type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="r-pwd">密码</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-lock-locked" title="密码" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="r-pwd" class="form-control" name="password" placeholder="密码" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="r-twice-pwd">确认密码</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-lock-locked" title="密码" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="r-twice-pwd" class="form-control" name="twicePassword" placeholder="确认密码"
                                   type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="r-phone">手机</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-phone" title="手机" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="r-phone" class="form-control" name="phone" placeholder="手机" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="r-email">邮箱</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-envelope-closed" title="邮箱" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="r-email" class="form-control" name="email" placeholder="邮箱" type="text">
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary btn-block">注册</button>
                    </div>
                </form>

            <#-- 已有帐号 -->
                <div class="d-sm-flex justify-content-sm-between">
                    <div>
                        <span>已有帐号？</span><a class="login-href" href="javascript:void(0)">登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<#-- 忘记密码模态框 -->
<div class="modal fade" id="forget-pwd">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">忘记密码</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
            <#-- 找回用户 -->
                <div id="forget-pwd-user">
                    <form id="forget-pwd-user-form">
                        <div class="form-group">
                            <label for="fpu-phone">手机</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-phone" title="手机" aria-hidden="true"></i>
                                </span>
                                </div>
                                <input id="fpu-phone" class="form-control" name="phone" placeholder="手机" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fpu-email">邮箱</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-envelope-closed" title="邮箱" aria-hidden="true"></i>
                                </span>
                                </div>
                                <input id="fpu-email" class="form-control" name="email" placeholder="邮箱" type="text">
                            </div>
                        </div>
                        <div>
                            <button type="submit" class="btn btn-primary btn-block">下一步</button>
                        </div>
                    </form>
                </div>

            <#-- 设置新密码 -->
                <div id="forget-pwd-reset">
                    <form id="forget-pwd-reset-form">
                    <#-- 用户编号 -->
                        <input type="hidden" name="userId" value="">
                        <div class="form-group">
                            <label for="fpr-name">用户名</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-person" title="用户" aria-hidden="true"></i>
                                </span>
                                </div>
                                <input id="fpr-name" class="form-control" name="loginName" placeholder="用户名"
                                       type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fpr-pwd">密码</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-lock-locked" title="密码" aria-hidden="true"></i>
                                </span>
                                </div>
                                <input id="fpr-pwd" class="form-control" name="password" placeholder="密码"
                                       type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fpr-twice-pwd">确认密码</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-lock-locked" title="密码" aria-hidden="true"></i>
                                </span>
                                </div>
                                <input id="fpr-twice-pwd" class="form-control" name="twicePassword" placeholder="确认密码"
                                       type="password">
                            </div>
                        </div>
                        <div>
                            <button type="submit" class="btn btn-primary btn-block">设置</button>
                        </div>
                    </form>
                </div>

            <#-- 已有帐号 -->
                <div class="d-sm-flex justify-content-sm-between">
                    <div>
                        <span>已有帐号？</span><a class="login-href" href="javascript:void(0)">登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<#-- 修改用户信息模态框 -->
<div class="modal fade" id="modify-user-info">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">修改信息</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
            <#-- 修改信息表单 -->
                <form id="modify-user-info-form">
                    <div class="form-group">
                        <label for="nui-login-name">用户名<span class="text-warning">（三方登录的用户名修改后下次登录会失效）</span></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-person" title="用户" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="nui-login-name" class="form-control" name="loginName" placeholder="用户名"
                                   type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mui-real-name">姓名</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-person" title="姓名" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="mui-real-name" class="form-control" name="realName" placeholder="姓名"
                                   type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mui-pwd">新密码<span class="text-warning">（如密码不修改，此选项不填）</span></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-lock-locked" title="新密码" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="mui-pwd" class="form-control" name="password" placeholder="新密码"
                                   type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mui-twice-pwd">确认新密码<span class="text-warning">（如密码不修改，此选项不填）</span></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-lock-locked" title="确认新密码" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="mui-twice-pwd" class="form-control" name="twicePassword" placeholder="确认新密码"
                                   type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mui-phone">手机</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-phone" title="手机" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="mui-phone" class="form-control" name="phone" placeholder="手机" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mui-email">邮箱</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <i class="oi oi-envelope-closed" title="邮箱" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input id="mui-email" class="form-control" name="email" placeholder="邮箱" type="text">
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary btn-block">修改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>