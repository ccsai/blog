package com.chenchuan.admin.blog.service;

import com.chenchuan.admin.blog.po.FriendlyLinkPo;
import com.chenchuan.admin.blog.vo.FriendlyLinkVo;

import java.util.List;

/**
 * 友情链接service
 */
public interface FriendlyLinkServie {

    /**
     * 查询友链列表
     *
     * @param friendlyLinkVo
     * @return 友链列表
     */
    List<FriendlyLinkPo> findFriendlyLinkList(FriendlyLinkVo friendlyLinkVo);

    /**
     * 添加友链
     *
     * @param friendlyLinkVo
     * @return 添加状态
     */
    int addFriendlyLink(FriendlyLinkVo friendlyLinkVo);

    /**
     * 根据编号查询友链详情
     *
     * @param friendlyLinkId 友链编号
     * @return 友链详情
     */
    FriendlyLinkPo findFriendlyLinkByFriendlyLinkId(String friendlyLinkId);

    /**
     * 根据编号修改友链
     *
     * @param friendlyLinkVo
     * @return 修改状态
     */
    int editFriendlyLinkByFriendlyLinkId(FriendlyLinkVo friendlyLinkVo);

    /**
     * 根据编号删除友链
     *
     * @param friendlyLinkId 友链编号
     * @return 删除状态
     */
    int removeFriendlyLinkByFriendlyLinkId(String friendlyLinkId);
}
