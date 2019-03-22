package com.insigma.mvc.controller.monitor.PXJG_001_003;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Zc07DTO;
import com.insigma.mvc.model.train.Zc07;
import com.insigma.mvc.service.monitor.PXJG_001_003.LeaveService;

import com.insigma.mvc.service.train.PXYW_001_002.ApiTeacherService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 请假
 *
 * @author jewel
 */
@RestController
@Api(description = "请假管理控制器")
@RequestMapping("/api/leave")
public class ApiLeaveController extends MvcHelper {
    @Resource
    private LeaveService leaveService;

    /**
     * 初始化班级信息列表
     */
    @ApiOperation(value = "获取班级信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList", method = RequestMethod.POST)
    public AjaxReturnMsg getInfoList(Zc07DTO zc07DTO) throws Exception {
        PageInfo<Zc07DTO> pageInfo = leaveService.getInfoList(zc07DTO);
        return this.success(pageInfo);

    }

    /**
     * 根据id获取学员请假信息
     */
    @ApiOperation(value = "根据id获取学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chc001", value = "学员内码", required = true, paramType = "path")
    })
    @RequestMapping(value = "/getStuList", method = RequestMethod.POST)
    public AjaxReturnMsg getStuList(Zc07 zc07) throws Exception {
        PageInfo<Zc07> pageInfo = leaveService.getStuList(zc07);
        return this.success(pageInfo);

    }
    /**
     * 删除学员请假信息
     *
     * @param zc07
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除学员请假信息", notes = "删除学员请假信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public AjaxReturnMsg deleteData(@ModelAttribute @Valid Zc07 zc07, BindingResult result) throws Exception {
        //检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return leaveService.deleteData(zc07.getCzc007());
    }
    /**
     * 根据id获取学员信息
     * @param chc001学员内码
     * @return
     */
    @ApiOperation(value = "根据id获取学员信息", notes = "根据id获取学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chc001", value = "学员内码", required = true, paramType = "path")
    })
    @RequestMapping(value = "/getInfo/{chc001}", method = RequestMethod.GET)
    public AjaxReturnMsg getStuInfo(@PathVariable String chc001) throws Exception {
        return leaveService.getStuById(chc001);
    }

    /**
     * 保存采请假基本信息
     *
     * @param zc07
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存采集基本信息", notes = "保存采集基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Zc07 zc07, BindingResult result) throws Exception {
        //检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
        return leaveService.saveData(zc07);
    }

}