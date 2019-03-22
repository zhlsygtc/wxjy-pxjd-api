package com.insigma.mvc.service.person;


import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SUser;

/**
 * 注册server接口类
 */
public interface ApiRegistService {
	/**
	 * 判断用户名是否已被占用
	 * @param username
	 * @return
	 */
    AjaxReturnMsg checkUsername(String username);

	/**
	 * 个人注册
	 * @param suser
	 * @throws Exception
	 */
	AjaxReturnMsg regist(SUser suser) throws Exception;

    /**
     * 给用户添加角色
     * @param userid
     * @param rolecode
     * @throws Exception
     */
    AjaxReturnMsg addSUserRole(String userid, String rolecode) throws Exception;

	/**
	 * 根据用户账号内码获取用户信息
	 * @param userid
	 * @return
	 */
	SUser getSUserByUserId(String userid);

}
