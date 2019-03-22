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
 * ���ڲɼ�
 * 
 * @author jewel
 */
@RestController
@Api(description = "���ڲɼ�������")
@RequestMapping("/api/collect")
public class ApiCollectController extends MvcHelper {
	@Resource
	private CollectService collectService;

	/**
	 * ��ʼ��ѧԱ��Ϣ�б�
	 */
	@ApiOperation(value = "��ȡѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getInfoList", method = RequestMethod.POST)
	public AjaxReturnMsg getHc60List(Hc60DTO hc60dto) throws Exception {
		PageInfo<Hc60DTO> pageInfo = collectService.getInfoList(hc60dto);
		return this.success(pageInfo);

	}

	/**
	 * ��ʼ����ʦ��Ϣ�б�
	 */
	@ApiOperation(value = "��ȡ��ʦ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getTeacherInfoList", method = RequestMethod.POST)
	public AjaxReturnMsg getTeacherInfoList(Hb69DTO hb69dto) throws Exception {
		PageInfo<Hb69DTO> pageInfo = collectService.getTeacherInfoList(hb69dto);
		return this.success(pageInfo);

	}

	/**
	 * ����id��ȡѧԱ��Ϣ
	 * 
	 * @param chc001ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "����id��ȡѧԱ��Ϣ", notes = "����id��ȡѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "����", required = true, paramType = "path") })
	@RequestMapping(value = "/getInfo/{id}/{type}", method = RequestMethod.GET)
	public AjaxReturnMsg getStuInfo(@PathVariable String id, @PathVariable String type) throws Exception {
		if ("1".equals(type)) {
			return collectService.getStuById(id);
		} else if ("2".equals(type)) {
			return collectService.getTeacherById(id);
		}
		return this.error("��ѯʧ��");
	}

	/**
	 * ����ɼ�������Ϣ
	 *
	 * @param hb61
	 *            ʦ�ʶ���
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "����ɼ�������Ϣ", notes = "����ɼ�������Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/savedata", method = RequestMethod.POST)
	public AjaxReturnMsg saveData(@ModelAttribute @Valid Hc60DTO hc60dto, BindingResult result) throws Exception {
		// ��������
		if (result.hasErrors()) {
			return validate(result);
		}
		return collectService.saveData(hc60dto);
	}

	/**
	 * ͷ���ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "ͷ���ϴ�", notes = "ͷ���ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "file_bus_id", value = "���֤", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query") })
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name, String userid,
			@RequestParam("uploadFile") MultipartFile multipartFile) {
		return collectService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}

	/**
	 * ͷ���ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "ͷ���ϴ�", notes = "ͷ���ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "file_bus_id", value = "���֤", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query") })
	@RequestMapping(value = "/uploadcard", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadcard(String file_bus_id, String file_name, String userid,
			@RequestParam("uploadFile") MultipartFile multipartFile) {
		return collectService.uploadCard(userid, file_bus_id, file_name, multipartFile);
	}

}