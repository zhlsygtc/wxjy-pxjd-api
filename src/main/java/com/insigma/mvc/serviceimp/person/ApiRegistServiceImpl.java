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
 * 注册server实现类
 *
 * @author zhongr
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApiRegistServiceImpl extends MvcHelper implements ApiRegistService {

    @Resource
    private ApiRegistMapper apiRegistMapper;

    /**
     * 判断用户名是否已被占用
     *
     * @param username
     * @return
     */
    @Override
    public AjaxReturnMsg checkUsername(String username) {
        SUser suser = apiRegistMapper.getSUserByUsername(username);
        if(suser != null) {
            return this.error("该用户名已经被注册");
        }
        return this.success("");
    }

    /**
     * 保存个人账号
     *
     * @param suser
     * @throws Exception
     */
    @Override
    public AjaxReturnMsg regist(SUser suser) throws Exception {
        //保存个人信息
        suser.setUsertype("1");
        suser.setLogintimes(0);
        suser.setEnabled("1");
        suser.setIsblacklist("0");
        suser.setIsmainaccount("1");
        int num = apiRegistMapper.addSUser(suser);
        if(num < 1) {
            return this.error("注册失败");
        }
        SUserGroupRef sUserGroupRef = new SUserGroupRef();
        sUserGroupRef.setUserid(suser.getUserid());
        sUserGroupRef.setGroupid(suser.getBaseinfoid());
        apiRegistMapper.addSUserGroup(sUserGroupRef);
        //给用户jwt加密生成token
        String token = JWT.sign(suser);
        System.out.println("jwt token=" + token);
        suser.setToken(token);
        return this.success("注册成功", suser);
    }

    /**
     * 给用户添加角色
     *
     * @param userid   用户内码
     * @param rolecode 角色名称
     * @throws Exception
     */
    @Override
    public AjaxReturnMsg addSUserRole(String userid, String rolecode) throws Exception {
        SRole srole = apiRegistMapper.getSRoleByCode(rolecode);
        if (srole == null) {
            throw new Exception("角色编号不存在，请检查数据库");
        }
        SUserRole suserrole = new SUserRole();
        suserrole.setUserid(userid);
        suserrole.setRoleid(srole.getRoleid());
        int num = apiRegistMapper.addSUserRole(suserrole);
        if(num > 0) {
            return this.success("角色添加成功");
        }else {
            return this.error("角色添加失败");
        }
    }

    /**
     * 根据用户账号内码获取用户信息
     * @param userid
     * @return
     */
    @Override
    public SUser getSUserByUserId(String userid) {
        return apiRegistMapper.getSUserByUserId(userid);
    }

}
