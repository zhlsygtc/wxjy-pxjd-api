package com.insigma.mvc.controller.train.PXYW_001_010;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.model.train.Hc61_temp;
import com.insigma.mvc.model.train.SmtGroup;
import com.insigma.mvc.model.train.Student;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.service.train.PXYW_001_010.ApiClassPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 开班计划contoller
 * @author zhanghl
 * 2018-04-25
 */
@RestController
@Api(description = "开班计划控制器")
@RequestMapping("/api/classPlan")
public class ApiClassPlanController extends MvcHelper{
	@Resource
	private ApiClassPlanService apiClassPlanService;
	/**
	 * 初始化开班计划
	 */
	@ApiOperation(value = "获取开班计划信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlanList",method = RequestMethod.POST)
	public AjaxReturnMsg getPlanList(Hb65 hb65) throws Exception {
		PageInfo<Hb65> pageInfo = apiClassPlanService.getPlanList(hb65);
		return this.success(pageInfo);
		
	}
    /**
	 * 保存培训开班计划信息
	 */
	@ApiOperation(value = "保存培训开班计划信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/addPlan",method = RequestMethod.POST)
	public AjaxReturnMsg addPlan(Hb65 hb65) throws Exception {
		return apiClassPlanService.addPlan(hb65);
	}
   /**
	 * 修改培训计划信息
	 */
	@ApiOperation(value = "修改培训计划信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlanById",method = RequestMethod.POST)
	public AjaxReturnMsg getPlanById(Hb65 hb65) throws Exception{
		hb65 = apiClassPlanService.getPlanById(hb65);
		return this.success(hb65);
	}
	/**
	 * 查询smt_group对象
	 */
	@ApiOperation(value = "查询smt_group对象", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSmtGroupById",method = RequestMethod.POST)
	public AjaxReturnMsg getSmtGroupById(SmtGroup smtGroup) throws Exception{
		smtGroup = apiClassPlanService.getSmtGroupById(smtGroup.getGroupid());
		return this.success(smtGroup);
	}
	/**
     * 获取机构培训资质
     * @param codevalue 资质编号
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取机构培训资质", notes = "资质编号", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca111List", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca111List(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiClassPlanService.getAca111List(codevalue);
    }
    /**
     * 获取机构培训资质等级
     * @param codevalue 资质编号
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取机构培训资质等级", notes = "资质编号", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca11aList", method = RequestMethod.POST)
    public AjaxReturnMsg<List<CodeValue>> getAca11aList(@ModelAttribute CodeValue codevalue) throws Exception {
        return apiClassPlanService.getAca11aList(codevalue.getId(), codevalue.getCode_type());
    }
    /**
     * 获取上级部门
     * @param stu 学员对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取上级部门", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAab301List", method = RequestMethod.GET)
    public AjaxReturnMsg<List<CodeValue>> getAab301List() throws Exception {
        return apiClassPlanService.getAab301List();
    }
    /**
	 * 查询该公司所属的行政区划
	 */
	@ApiOperation(value = "查询该公司所属的行政区划", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCompanyAab301",method = RequestMethod.POST)
	public AjaxReturnMsg getCompanyAab301(SmtGroup smtGroup) throws Exception{
		smtGroup = apiClassPlanService.getCompanyAab301(smtGroup.getGroupid());
		return this.success(smtGroup);
	}
    /**
   	 * 根据专业名称查询培训工种名称及专业类别
   	 */
	@ApiOperation(value = "根据专业名称查询培训工种名称及专业类别", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAca112",method = RequestMethod.POST)
   	public AjaxReturnMsg getAca112(Hb65 hb65) throws Exception{
		hb65 = apiClassPlanService.getAca112(hb65.getAca111());
   		return this.success(hb65);
   	}
	/**
	 * 根据id删除培训计划(改为无效)
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id删除培训计划", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/delPlan",method = RequestMethod.POST)
	public AjaxReturnMsg<String> delPlan(Hb65 hb65)throws Exception {
		return apiClassPlanService.delPlan(hb65);
	}
	/**
	 * 切换培训计划对外状态
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "切换培训计划对外状态", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/changePlan",method = RequestMethod.POST)
	public AjaxReturnMsg<String> changePlan(Hb65 hb65)throws Exception {
		return apiClassPlanService.changePlan(hb65);
	}
	/**
	 * 查看培训计划信息
	 * @param chb055
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看培训计划信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/lookPlan",method = RequestMethod.POST)
	public AjaxReturnMsg<String> lookPlan(Hb65 hb65)throws Exception {
		hb65 = apiClassPlanService.getPlanById(hb65);
		return this.success(hb65);
	}
	/**
	 * 查看已报名学员信息
	 * @param chb055
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看已报名学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/signedStuForLook",method = RequestMethod.POST)
	public AjaxReturnMsg<String> signedStuForLook(Student stu)throws Exception {
		PageInfo<Student> pageInfo = apiClassPlanService.signedStuForLook(stu);
		return this.success(pageInfo);
	}
    /**
	 * 报名
	 */
	@ApiOperation(value = "保存学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
	public AjaxReturnMsg signUp(Student stu) throws Exception {
		return apiClassPlanService.signUp(stu);
	}
	/**
	 * 根据身份证号获取学员基本信息
	 */
	@ApiOperation(value = "根据身份证号获取学员基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/findStuById",method = RequestMethod.POST)
	public AjaxReturnMsg findStuById(Student stu) throws Exception{
		Hc61 hc61 = apiClassPlanService.findStuById(stu);
		return this.success(hc61);
	}
	/**
	 * 根据身份证号获取学员附件信息
	 */
	@ApiOperation(value = "根据身份证号获取学员附件信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/findFileById",method = RequestMethod.POST)
	public AjaxReturnMsg findFileById(Student stu) throws Exception{
		Hc61 hc61 = apiClassPlanService.findFileById(stu);
		return this.success(hc61);
	}
    /**
	 * 学员头像上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "学员头像上传", notes = "学员头像上传", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "学员内码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "经办人内码", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadHc61Photo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadHc61Photo(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiClassPlanService.uploadHc61Photo(userid, file_bus_id, file_name, multipartFile);
	}
	/**
     *上传学员相关文件
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "上传申请相关文件", notes = "上传学员相关文件", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file_bus_id", value = "基本信息id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query")
    })
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxReturnMsg uploadFile(HttpServletRequest request,
                                    @RequestParam("uploadFile") MultipartFile multipartFile) throws Exception {
        return apiClassPlanService.fileUpload(request, multipartFile);
    }
    /**
	 * 根据id删除图片
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id删除图片", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/delPicById",method = RequestMethod.POST)
	public AjaxReturnMsg<String> delPicById(Student stu)throws Exception {
		return apiClassPlanService.delPicById(stu);
	}
	/**
	 * 批量导入报名保存
	 */
	@ApiOperation(value = "批量导入报名保存", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/signUpSave",method = RequestMethod.POST)
	public AjaxReturnMsg signUpSave(Hc61_temp hc61Temp) throws Exception {
		return apiClassPlanService.signUpSave(hc61Temp);
	}
	/**
	 * 获取导出word所需信息
	 * @param stu 学员对象
	 * @return
	 */
	@ApiOperation(value = "获取导出word所需信息", notes = "获取导出word所需信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/exportDJK", method = RequestMethod.POST)
    public AjaxReturnMsg exportDJK(@ModelAttribute Student stu) throws Exception {
        return apiClassPlanService.exportDJK(stu);
    }
}