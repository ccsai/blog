package com.chenchuan.admin.sys.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.chenchuan.common.po.BasePo;

import java.util.Date;
import java.util.List;

/**
 * 角色po
 */
public class RolePo extends BasePo {

    private String roleId;

    private String roleName;

    private String roleDes;

    private String createUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String modifyUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private List<PermissionPo> permissionList;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDes() {
        return roleDes;
    }

    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<PermissionPo> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionPo> permissionList) {
        this.permissionList = permissionList;
    }
}
