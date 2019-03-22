package com.insigma.mvc.controller.monitor.PXJG_001_001;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb61;
import com.insigma.mvc.model.train.Hb69DTO;
import com.insigma.mvc.model.train.Hc60DTO;
import com.insigma.mvc.service.monitor.PXJG_001_001.CollectService;

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
 * 考勤采集
 * 
 * @author jewel
 */
@RestController
@Api(description = "考勤采集控制器")
@RequestMapping("/api/collect")
public class ApiCollectController extends MvcHelper {
	@Resource
	private CollectService collectService;

	/**
	 * 初始化学员信息列表
	 */
	@ApiOperation(value = "获取学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getInfoList", method = RequestMethod.POST)
	public AjaxReturnMsg getHc60List(Hc60DTO hc60dto) throws Exception {
		PageInfo<Hc60DTO> pageInfo = collectService.getInfoList(hc60dto);
		return this.success(pageInfo);

	}

	/**
	 * 初始化教师信息列表
	 */
	@ApiOperation(value = "获取教师信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getTeacherInfoList", method = RequestMethod.POST)
	public AjaxReturnMsg getTeacherInfoList(Hb69DTO hb69dto) throws Exception {
		PageInfo<Hb69DTO> pageInfo = collectService.getTeacherInfoList(hb69dto);
		return this.success(pageInfo);

	}

	/**
	 * 根据id获取学员信息
	 * 
	 * @param chc001学员内码
	 * @return
	 */
	@ApiOperation(value = "根据id获取学员信息", notes = "根据id获取学员信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "内码", required = true, paramType = "path") })
	@RequestMapping(value = "/getInfo/{id}/{type}", method = RequestMethod.GET)
	public AjaxReturnMsg getStuInfo(@PathVariable String id, @PathVariable String type) throws Exception {
		if ("1".equals(type)) {
			return collectService.getStuById(id);
		} else if ("2".equals(type)) {
			return collectService.getTeacherById(id);
		}
		return this.error("查询失败");
	}

	/**
	 * 保存采集基本信息
	 *
	 * @param hb61
	 *            师资对象
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "保存采集基本信息", notes = "保存采集基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/savedata", method = RequestMethod.POST)
	public AjaxReturnMsg saveData(@ModelAttribute @Valid Hc60DTO hc60dto, BindingResult result) throws Exception {
		// 检验输入
		if (result.hasErrors()) {
			return validate(result);
		}
		return collectService.saveData(hc60dto);
	}

	/**
	 * 头像上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "头像上传", notes = "头像上传", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "file_bus_id", value = "身份证", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query") })
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name, String userid,
			@RequestParam("uploadFile") MultipartFile multipartFile) {
		return collectService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}

	/**
	 * 头像上传
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "头像上传", notes = "头像上传", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "file_bus_id", value = "身份证", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "文件名称，没有后缀", required = true, paramType = "query") })
	@RequestMapping(value = "/uploadcard", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadcard(String file_bus_id, String file_name, String userid,
			@RequestParam("uploadFile") MultipartFile multipartFile) {
		return collectService.uploadCard(userid, file_bus_id, file_name, multipartFile);
	}

}