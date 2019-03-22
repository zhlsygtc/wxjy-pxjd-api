package com.insigma.mvc.dao.person;

import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.SUserGroupRef;
import com.insigma.mvc.model.SUserRole;

import java.util.HashMap;

public interface ApiRegistMapper {
	/**
	 * �����û�����ѯ�û�
	 * @param username
	 * @return
	 */
	SUser getSUserByUsername(String username);

	/**
	 * �����û�ID��ѯ�û�
	 * @param userid
	 * @return
	 */
	SUser getSUserByUserId(String userid);

	/**
	 * �����˺���Ϣ
	 * @param suser
	 * @return
	 */
	int addSUser(SUser suser);

	/**
	 * �����˺Ż���������Ϣ
	 * @param sUserGroupRef
	 * @return
	 */
	int addSUserGroup(SUserGroupRef sUserGroupRef);

	/**
	 * ���ݽ�ɫ�����ȡ��ɫ��Ϣ
	 * @param code
	 * @return
	 */
	SRole getSRoleByCode(String code);

	/**
	 * ���û���ӽ�ɫ
	 */
	int addSUserRole(SUserRole suserrole);

    /**
     * ͨ���û�id���ɫ����ɾ���û����ɫ������Ϣ
     */
	int deleteSUserRoleByUserIdAndRolecode(HashMap map);

    /**
     * ͨ���û�id���ɫ��������ɾ���û����ɫ������Ϣ
     */
	int deleteSUserRoleByUserIdsAndRolecode(HashMap map);

    /**
     * ͨ���û�idɾ���û�
     */
    int deleteSUserByUserId(String userid);

	/**
	 * ͨ���û�id����ɾ���û�
	 */
	int deleteSUserByUserIds(String[] ids);

}
