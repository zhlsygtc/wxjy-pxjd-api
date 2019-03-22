package com.insigma.mvc.service.person;


import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SUser;

/**
 * ע��server�ӿ���
 */
public interface ApiRegistService {
	/**
	 * �ж��û����Ƿ��ѱ�ռ��
	 * @param username
	 * @return
	 */
    AjaxReturnMsg checkUsername(String username);

	/**
	 * ����ע��
	 * @param suser
	 * @throws Exception
	 */
	AjaxReturnMsg regist(SUser suser) throws Exception;

    /**
     * ���û���ӽ�ɫ
     * @param userid
     * @param rolecode
     * @throws Exception
     */
    AjaxReturnMsg addSUserRole(String userid, String rolecode) throws Exception;

	/**
	 * �����û��˺������ȡ�û���Ϣ
	 * @param userid
	 * @return
	 */
	SUser getSUserByUserId(String userid);

}
