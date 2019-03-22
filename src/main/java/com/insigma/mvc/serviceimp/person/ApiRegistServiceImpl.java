package com.insigma.mvc.serviceimp.person;

import javax.annotation.Resource;

import com.insigma.common.jwt.JWT;
import com.insigma.mvc.dao.person.ApiRegistMapper;
import com.insigma.mvc.model.SUserGroupRef;
import com.insigma.mvc.service.person.ApiRegistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.SUserRole;

/**
 * ע��serverʵ����
 *
 * @author zhongr
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApiRegistServiceImpl extends MvcHelper implements ApiRegistService {

    @Resource
    private ApiRegistMapper apiRegistMapper;

    /**
     * �ж��û����Ƿ��ѱ�ռ��
     *
     * @param username
     * @return
     */
    @Override
    public AjaxReturnMsg checkUsername(String username) {
        SUser suser = apiRegistMapper.getSUserByUsername(username);
        if(suser != null) {
            return this.error("���û����Ѿ���ע��");
        }
        return this.success("");
    }

    /**
     * ��������˺�
     *
     * @param suser
     * @throws Exception
     */
    @Override
    public AjaxReturnMsg regist(SUser suser) throws Exception {
        //���������Ϣ
        suser.setUsertype("1");
        suser.setLogintimes(0);
        suser.setEnabled("1");
        suser.setIsblacklist("0");
        suser.setIsmainaccount("1");
        int num = apiRegistMapper.addSUser(suser);
        if(num < 1) {
            return this.error("ע��ʧ��");
        }
        SUserGroupRef sUserGroupRef = new SUserGroupRef();
        sUserGroupRef.setUserid(suser.getUserid());
        sUserGroupRef.setGroupid(suser.getBaseinfoid());
        apiRegistMapper.addSUserGroup(sUserGroupRef);
        //���û�jwt��������token
        String token = JWT.sign(suser);
        System.out.println("jwt token=" + token);
        suser.setToken(token);
        return this.success("ע��ɹ�", suser);
    }

    /**
     * ���û���ӽ�ɫ
     *
     * @param userid   �û�����
     * @param rolecode ��ɫ����
     * @throws Exception
     */
    @Override
    public AjaxReturnMsg addSUserRole(String userid, String rolecode) throws Exception {
        SRole srole = apiRegistMapper.getSRoleByCode(rolecode);
        if (srole == null) {
            throw new Exception("��ɫ��Ų����ڣ��������ݿ�");
        }
        SUserRole suserrole = new SUserRole();
        suserrole.setUserid(userid);
        suserrole.setRoleid(srole.getRoleid());
        int num = apiRegistMapper.addSUserRole(suserrole);
        if(num > 0) {
            return this.success("��ɫ��ӳɹ�");
        }else {
            return this.error("��ɫ���ʧ��");
        }
    }

    /**
     * �����û��˺������ȡ�û���Ϣ
     * @param userid
     * @return
     */
    @Override
    public SUser getSUserByUserId(String userid) {
        return apiRegistMapper.getSUserByUserId(userid);
    }

}
