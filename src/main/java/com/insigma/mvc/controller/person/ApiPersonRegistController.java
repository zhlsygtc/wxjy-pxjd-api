package com.insigma.mvc.controller.person;


import com.insigma.common.util.StringUtil;
import com.insigma.mvc.model.train.Hb57;
import com.insigma.mvc.service.person.ApiRegistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SUser;

/**
 * 个人注册
 * @author zhongr
 * 2018-06-12
 */
@RestController
@Api(description = "个人注册")
@RequestMapping("/api/person/regist")
public class ApiPersonRegistController extends MvcHelper {

    @Resource
    private ApiRegistService apiRegistService;

    /**
     * 用户名验证重复
     *
     * @param hb57
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户名验证重复", notes = "用户名验证重复", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
    public AjaxReturnMsg checkUsername(@ModelAttribute Hb57 hb57) throws Exception {
        return apiRegistService.checkUsername(hb57.getUsername());
    }

    /**
     * 个人注册
     *
     * @return
     */
    @ApiOperation(value = "个人注册", notes = "个人注册", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public AjaxReturnMsg regist(@ModelAttribute @Valid SUser suser, BindingResult result) throws Exception {
        //检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        if(StringUtil.isEmpty(suser.getFromaddr())){
            return this.error("数据来源不能为空");
        }
        return apiRegistService.regist(suser);
    }

    /**
     * 添加角色
     *
     * @return
     */
    @ApiOperation(value = "添加角色", notes = "添加角色", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public AjaxReturnMsg addSUserRole(String user_id, String rolecode) throws Exception {
        return apiRegistService.addSUserRole(user_id, rolecode);
    }

}
