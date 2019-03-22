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
 * ���
 *
 * @author jewel
 */
@RestController
@Api(description = "��ٹ��������")
@RequestMapping("/api/leave")
public class ApiLeaveController extends MvcHelper {
    @Resource
    private LeaveService leaveService;

    /**
     * ��ʼ���༶��Ϣ�б�
     */
    @ApiOperation(value = "��ȡ�༶��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList", method = RequestMethod.POST)
    public AjaxReturnMsg getInfoList(Zc07DTO zc07DTO) throws Exception {
        PageInfo<Zc07DTO> pageInfo = leaveService.getInfoList(zc07DTO);
        return this.success(pageInfo);

    }

    /**
     * ����id��ȡѧԱ�����Ϣ
     */
    @ApiOperation(value = "����id��ȡѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chc001", value = "ѧԱ����", required = true, paramType = "path")
    })
    @RequestMapping(value = "/getStuList", method = RequestMethod.POST)
    public AjaxReturnMsg getStuList(Zc07 zc07) throws Exception {
        PageInfo<Zc07> pageInfo = leaveService.getStuList(zc07);
        return this.success(pageInfo);

    }
    /**
     * ɾ��ѧԱ�����Ϣ
     *
     * @param zc07
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "ɾ��ѧԱ�����Ϣ", notes = "ɾ��ѧԱ�����Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public AjaxReturnMsg deleteData(@ModelAttribute @Valid Zc07 zc07, BindingResult result) throws Exception {
        //��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return leaveService.deleteData(zc07.getCzc007());
    }
    /**
     * ����id��ȡѧԱ��Ϣ
     * @param chc001ѧԱ����
     * @return
     */
    @ApiOperation(value = "����id��ȡѧԱ��Ϣ", notes = "����id��ȡѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chc001", value = "ѧԱ����", required = true, paramType = "path")
    })
    @RequestMapping(value = "/getInfo/{chc001}", method = RequestMethod.GET)
    public AjaxReturnMsg getStuInfo(@PathVariable String chc001) throws Exception {
        return leaveService.getStuById(chc001);
    }

    /**
     * �������ٻ�����Ϣ
     *
     * @param zc07
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ɼ�������Ϣ", notes = "����ɼ�������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public AjaxReturnMsg saveData(@ModelAttribute @Valid Zc07 zc07, BindingResult result) throws Exception {
        //��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return leaveService.saveData(zc07);
    }

}