package com.insigma.mvc.controller.train.PXYW_001_014;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb50;
import com.insigma.mvc.model.train.Hb50Dto;
import com.insigma.mvc.model.train.Hb67;
import com.insigma.mvc.model.train.Hb69;
import com.insigma.mvc.model.train.Hc60;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_014.ApiSubsidyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "补贴申报")
@RequestMapping("/api/subsidy")
public class ApiSubsidyController extends MvcHelper{
	
	@Resource
	private ApiSubsidyService apiSubsidyService;

	/**
	 * 班期信息列表
	 * @param hb67 班期对象
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/getHb67List")
	public AjaxReturnMsg getHb67List(Hb67 hb67 ) throws Exception {
		return this.success(apiSubsidyService.getHb67List(hb67));	
	}
	
    /**
     * 生成班期补贴信息
     *
     * @param hb67 班期对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "生成班期补贴信息", notes = "生成班期补贴信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public AjaxReturnMsg generate(@ModelAttribute Hb67 hb67) throws Exception {
    	
    	return apiSubsidyService.generate(hb67);
    }
    
    /**
	 * 查询班期信息 
	 */
    @RequestMapping(value = "/getHb67ById")
    public AjaxReturnMsg getHb67ById(Hb67 hb67) throws Exception{
		return this.success(apiSubsidyService.getHb67ById(hb67.getChb067()));
    }
    
    /**
	 * 查询合并班级中学员列表 
	 */
    @RequestMapping(value = "/getHc60StuList")
	public AjaxReturnMsg getHc60StuList(Hc60 hc60) throws Exception {
		return this.success(apiSubsidyService.getHc60StuList(hc60));	
	}
    
    /**
	 * 查询班期下学员补贴列表
	 */
    @RequestMapping(value = "/subsidyList")
	public AjaxReturnMsg subsidyList(Student stu) throws Exception {
		return this.success(apiSubsidyService.subsidyList(stu));	
	}
    
    /**
	 * 删除学员补贴信息
	 * @param request
	 * @param stu 学员内码
	 * @return
	 */
    @ApiOperation(value = "删除学员补贴信息", notes = "删除学员补贴信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxReturnMsg delete(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiSubsidyService.delete(stu);
    }
    
    /**
	 * 保存学员补贴信息
	 * @param request
	 * @param stu 学员内码
	 * @return
	 */
    @ApiOperation(value = "保存学员补贴信息", notes = "保存学员补贴信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxReturnMsg update(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
    	//检验输入
        if (result.hasErrors()) {
            return validate(result);
        }
    	return apiSubsidyService.update(stu);
    }
    
    /**
	 * 查询学员补贴信息 
	 */
    @RequestMapping(value = "/toedit")
    public AjaxReturnMsg getHc50ById(Student stu) throws Exception{
		return this.success(apiSubsidyService.getHc50ById(stu));
    }
    
    /**
     * 重新生成班期补贴信息
     *
     * @param Hb50 补贴对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "重新生成班期补贴信息", notes = "重新生成班期补贴信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/generateAgain", method = RequestMethod.POST)
    public AjaxReturnMsg generateAgain(@ModelAttribute Hb50 hb50) throws Exception {
    	
    	return apiSubsidyService.generateAgain(hb50);
    }
    
    /**
     * 提交班期补贴信息
     *
     * @param hb67 班期对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "提交班期补贴信息", notes = "提交班期补贴信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public AjaxReturnMsg submit(@ModelAttribute Hb67 hb67) throws Exception {
    	
    	return apiSubsidyService.submit(hb67);
    }
    /**
	 * 根据id查询补贴信息
	 * @param hb50 补贴信息对象
	 * @return
	 */
	@ApiOperation(value = "根据id查询补贴信息", notes = "根据id查询补贴信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getSubsidy", method = RequestMethod.POST)
    public AjaxReturnMsg getSubsidyById(@ModelAttribute Hb50Dto hb50) throws Exception {
        return apiSubsidyService.getSubsidyById(hb50.getChb050());
    }
	/**
	 * 获取导出培训学员补贴花名册
	 * @param chb068
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = " 获取导出培训学员补贴花名册", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/getexportStuBT",method = RequestMethod.POST)
	public AjaxReturnMsg getexportStuBT(Student stu)throws Exception {
		List<Student> list = apiSubsidyService.getexportStuBT(stu);
        return  this.success(list);
	}
}
