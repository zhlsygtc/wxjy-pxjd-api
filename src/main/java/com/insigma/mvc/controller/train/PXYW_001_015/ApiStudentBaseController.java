package com.insigma.mvc.controller.train.PXYW_001_015;


import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.service.train.PXYW_001_015.ApiStudentBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "ѧԱ������Ϣ������")
@RequestMapping("/api/studentBase")
public class ApiStudentBaseController extends MvcHelper{

	@Resource
	private ApiStudentBaseService apiStudentBaseService;
	
	
	/**
	 * ��ȡѧԱ��Ϣ�б�
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "ѧԱ��Ϣ��ʾ", notes = "ѧԱ��Ϣ��ʾ")
    @RequestMapping(value = "/getStudentList", method = RequestMethod.POST)
	public AjaxReturnMsg getStudentList(@ModelAttribute Student stu, BindingResult result ) throws Exception {
		PageInfo<Student> pageinfo = apiStudentBaseService.getStudentList(stu);
		return this.success(pageinfo);
	}
	
	/**
	 * ����id��ȡѧԱ��Ϣ
	 * @param stu ��Ա����
	 * @return
	 */
	@ApiOperation(value = "����id��ȡѧԱ��Ϣ", notes = "����id��ȡѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPersonInfo(@ModelAttribute Student stu) throws Exception {
		return apiStudentBaseService.getStudentById(stu.getChc111());
    }
	
	/**
     * ����ѧԱ��Ϣ
     *
     * @param temp ѧԱ����
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "����ѧԱ��Ϣ", notes = "����ѧԱ��Ϣ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateStu", method = RequestMethod.POST)
    public AjaxReturnMsg updateStu(@ModelAttribute @Valid Student stu, BindingResult result) throws Exception {
        //��������
        if (result.hasErrors()) {
            return validate(result);
        }
        return apiStudentBaseService.updateStu(stu);
    }
	
    /**
	 * ѧԱͷ���ϴ�
	 *
	 * @param file_bus_id
	 * @param file_name
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "ѧԱͷ���ϴ�", notes = "ѧԱͷ���ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file_bus_id", value = "ѧԱ����", required = true, paramType = "query"),
			@ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "����������", required = true, paramType = "query")
	})
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AjaxReturnMsg uploadFile(String file_bus_id, String file_name,String userid,
									@RequestParam("uploadFile") MultipartFile multipartFile) {
		return apiStudentBaseService.uploadLogo(userid, file_bus_id, file_name, multipartFile);
	}
    
	/**
	 * ����id��ȡѧԱ��Ϣ����
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "����id��ȡѧԱ��Ϣ����", notes = "����id��ȡѧԱ��Ϣ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getInfoGBK", method = RequestMethod.POST)
    public AjaxReturnMsg getStudentGBKById(@ModelAttribute Student stu) throws Exception {
        return apiStudentBaseService.getStudentGBKById(stu.getChc111());
    }
	
    /**
	 * ��ȡѧԱ��ѵ��Ϣ�б�
	 * @param stu ѧԱ����
	 * @return
	 */
	@ApiOperation(value = "��ѵ��Ϣ", notes = "��ѵ��Ϣ")
    @RequestMapping(value = "/getTrainClasses", method = RequestMethod.POST)
	public AjaxReturnMsg getTrainClasses(@ModelAttribute Student stu) throws Exception {
		stu.setAab001(stu.getBaseinfoid());
		PageInfo<Hb68> pageinfo = apiStudentBaseService.getTrainClasses(stu);
		return this.success(pageinfo);
	}
    
}
