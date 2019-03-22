package com.insigma.mvc.controller.sysmanager.login;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.service.sysmanager.login.ApiWebLoginExtraService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 账户额外接口
 * @author zhongr
 * 2018-06-20
 */
@RestController
@RequestMapping("/api/webLoginExtra")
public class ApiWebLoginExtraController extends MvcHelper{
	@Resource
	private ApiWebLoginExtraService apiWebLoginExtraService;

	 /**
	 * 修改密码
	 */
	@ApiOperation(value = "修改密码", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updPassword", method = RequestMethod.POST)
	public AjaxReturnMsg updPassword(SUser suser) throws Exception {
		return apiWebLoginExtraService.updPassword(suser);
	}

}
