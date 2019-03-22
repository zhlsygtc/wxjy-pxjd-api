package com.insigma.mvc.controller.app.APP_001_005;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.train.Hb65;
import com.insigma.mvc.model.train.Hc61;
import com.insigma.mvc.service.app.APP_001_001.ApiCommonService;
import com.insigma.mvc.service.app.APP_001_005.ApiCollectionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/collection")
public class ApiCollectionController extends MvcHelper{
	
	@Resource
	ApiCollectionService apiCollectionService;
	@Resource
	ApiCommonService apiCommonService;

	/**
	 * ��ȡ��ѵ�ղ���Ϣ�б�
	 * @param hb65
	 * @return
	 */
	@ApiOperation(value = "��ȡ��ѵ�ղ���Ϣ�б�", notes = "��ȡ��ѵ�ղ���Ϣ�б�")
    @RequestMapping(value = "/getCollectionList", method = RequestMethod.POST)
	public AjaxReturnMsg getCollectionList(@ModelAttribute Hb65 hb65) throws Exception {
		Hc61 hc61 = apiCommonService.getStudentInfo(hb65);
		hb65.setChc111(hc61.getChc111());
		PageInfo<Hb65> pageinfo = apiCollectionService.getCollectionList(hb65);
		return this.success(pageinfo);
	}
	
	/**
	 * �ղؼƻ��������
	 * @param hb65 �ƻ�����
	 * @return
	 */
	@ApiOperation(value = "�ղؼƻ��������", notes = "�ղؼƻ��������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPlanInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getPlanInfo(@ModelAttribute Hb65 hb65) throws Exception {
        return apiCollectionService.getPlanInfo(hb65);
    }
	
	/**
     * �ղؼƻ�
     * @param hb65 �ƻ�����
     * @throws Exception
     */
    @ApiOperation(value = "�ղؼƻ�", notes = "�ղؼƻ�", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/doCollect", method = RequestMethod.POST)
    public AjaxReturnMsg doCollect(@ModelAttribute Hb65 hb65) throws Exception {
        return apiCollectionService.doCollect(hb65);
    }
	
    
}
