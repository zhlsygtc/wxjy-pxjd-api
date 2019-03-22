package com.insigma.mvc.controller.train.PXYW_001_006;

import javax.annotation.Resource;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.model.train.Zc13;
import com.insigma.mvc.service.train.PXYW_001_006.ApiInputScoreService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 成绩录入contoller
 * @author zhanghl
 */
@RestController
@Api(description = "成绩录入控制器")
@RequestMapping("/api/inputScore")
public class ApiInputScoreController extends MvcHelper{
	@Resource
	private ApiInputScoreService inputScoreService;
	/**
	 * 初始化开班基础信息列表 
	 */
	@ApiOperation(value = "获取班级基础信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoList",method = RequestMethod.POST)
	public AjaxReturnMsg getHb61List(Hb68 hb68) throws Exception {
		PageInfo<Hb68> pageInfo = inputScoreService.getInfoList(hb68);
		return this.success(pageInfo);
		
	}
    /**
	 * 保存学员成绩--勾选合格标志
	 */
	@ApiOperation(value = "保存学员成绩(勾选合格标志)", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/doQualified",method = RequestMethod.POST)
	public AjaxReturnMsg doQualified(Student stu) throws Exception {
    	String[] chc001s = stu.getSelectnodes().split(",");
    	stu.setChc001s(chc001s);
		return inputScoreService.doQualified(stu);
	}
	 /**
	 * 保存学员成绩--手工录入
	 */
	@ApiOperation(value = "保存学员成绩(手工录入)", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveScore",method = RequestMethod.POST)
	public AjaxReturnMsg saveScore(Student stu) throws Exception {
		return inputScoreService.saveScore(stu);
	}
	/**
	 * 提交成绩信息
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "提交成绩信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/submitScore",method = RequestMethod.POST)
	public AjaxReturnMsg<String> submitScore(Hb68 hb68)throws Exception {
		return inputScoreService.submitScore(hb68);
	}
	/**
	 * 撤销成绩信息
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "撤销成绩信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/revokeScore",method = RequestMethod.POST)
	public AjaxReturnMsg<String> revokeScore(Hb68 hb68)throws Exception {
		return inputScoreService.revokeScore(hb68);
	}
	/**
	 * 查看班级信息
	 * @param hb68
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看开班信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/doLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> doLook(@ModelAttribute Hb68 hb68)throws Exception {
		hb68 = inputScoreService.getById(hb68);
		return this.success(hb68);
	}
	/**
	 * 查询成绩标准
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询成绩标准", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getZc13",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getZc13(@ModelAttribute Zc13 zc13)throws Exception {
		if(zc13.getAab001() == null) {
			return this.error("登录信息验证失败，请重新登录");
		}
		zc13 = inputScoreService.getZc13(zc13.getAab001()); 
		return this.success(zc13);
	}
	/**
	 * 查看学员信息
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getStuListForLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> getStuListForLook(Student stu)throws Exception {
		PageInfo<Student> pageInfo = inputScoreService.getStuListForLook(stu);
		return this.success(pageInfo);
	}
    /**
   	 * 根据专业名称查询培训工种名称及专业类别
   	 */
	@ApiOperation(value = "根据专业名称查询培训工种名称及专业类别", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca112",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca112(Hb68 hb68) throws Exception{
   		hb68 = inputScoreService.getAca112(hb68.getAca111());
   		return this.success(hb68);
   	}
}