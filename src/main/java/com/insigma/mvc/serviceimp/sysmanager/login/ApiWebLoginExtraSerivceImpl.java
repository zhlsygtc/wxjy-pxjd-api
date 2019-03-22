package com.insigma.mvc.serviceimp.sysmanager.login;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.login.ApiWebLoginExtraMapper;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.service.sysmanager.login.ApiWebLoginExtraService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ApiWebLoginExtraSerivceImpl extends MvcHelper implements ApiWebLoginExtraService {

	@Resource
	private ApiWebLoginExtraMapper apiWebLoginExtraMapper;

	@Override
	public AjaxReturnMsg updPassword(SUser suser) {
		int num = apiWebLoginExtraMapper.updPassword(suser);
        if(num > 0) {
            return this.success("密码修改成功");
        }else {
            return this.error("密码修改失败");
        }
	}
}
