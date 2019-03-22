package com.insigma.mvc.dao.person;

import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.SUserGroupRef;
import com.insigma.mvc.model.SUserRole;

import java.util.HashMap;

public interface ApiRegistMapper {
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	SUser getSUserByUsername(String username);

	/**
	 * 根据用户ID查询用户
	 * @param userid
	 * @return
	 */
	SUser getSUserByUserId(String userid);

	/**
	 * 新增账号信息
	 * @param suser
	 * @return
	 */
	int addSUser(SUser suser);

	/**
	 * 新增账号机构关联信息
	 * @param sUserGroupRef
	 * @return
	 */
	int addSUserGroup(SUserGroupRef sUserGroupRef);

	/**
	 * 根据角色编码获取角色信息
	 * @param code
	 * @return
	 */
	SRole getSRoleByCode(String code);

	/**
	 * 给用户添加角色
	 */
	int addSUserRole(SUserRole suserrole);

    /**
     * 通过用户id与角色代码删除用户与角色关联信息
     */
	int deleteSUserRoleByUserIdAndRolecode(HashMap map);

    /**
     * 通过用户id与角色代码批量删除用户与角色关联信息
     */
	int deleteSUserRoleByUserIdsAndRolecode(HashMap map);

    /**
     * 通过用户id删除用户
     */
    int deleteSUserByUserId(String userid);

	/**
	 * 通过用户id批量删除用户
	 */
	int deleteSUserByUserIds(String[] ids);

}
