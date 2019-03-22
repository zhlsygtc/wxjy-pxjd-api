package com.insigma.mvc.serviceimp.appraisal.JDYW_002_001;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.enums.COMAAE100Enum;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.appraisal.JDYW_002_001.ApiAppraisalCompanyMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.appraisal.Hb76;
import com.insigma.mvc.model.appraisal.Hb78;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
import com.insigma.mvc.service.appraisal.JDYW_002_001.ApiAppraisalCompanyService;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;

/**
 * ����������Ϣά��
 * 2018-12-17
 */
@Service
@Transactional
public class ApiAppraisalCompanySerivceImpl extends MvcHelper implements ApiAppraisalCompanyService {

	private  static  String localdir = AppConfig.getProperties("localdir");
	@Resource
	private ApiAppraisalCompanyMapper apiAppraisalCompanyMapper;
	
	@Resource
	private ApiFileUploadService fileLoadService;
	/**
	 * ��ȡ������վ������Ϣ
	 */
	@Override
	public PageInfo<Hb76> getGroundInfo(Hb76 hb76) throws Exception {
		PageHelper.offsetPage(0, 1000);//��ҳ������
//		PageHelper.offsetPage(hb63.getOffset(), hb63.getLimit());
		hb76.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb76> list=apiAppraisalCompanyMapper.getGroundInfo(hb76);
		PageInfo<Hb76> pageinfo = new PageInfo<Hb76>(list);
		return pageinfo;
	}
	/**
	 * ��ȡcode_value����--chb065
	 */
	@Override
	public List<Hb64> getCodeValueChb065() throws Exception {
		List<Hb64> hb64List=apiAppraisalCompanyMapper.getCodeValueChb065();
		return hb64List;
	}
	/**
	 * ��ȡcode_value����--chb163
	 */
	@Override
	public List<Hb64> getCodeValueChb163() throws Exception {
		List<Hb64> hb64List=apiAppraisalCompanyMapper.getCodeValueChb163();
		return hb64List;
	}
	/**
	 * ��ȡcode_value����--chb070
	 */
	@Override
	public List<Hb64> getCodeValueChb070() throws Exception {
		List<Hb64> hb64List=apiAppraisalCompanyMapper.getCodeValueChb070();
		return hb64List;
	}
	/**
	 * ��ȡ����������Ϣ
	 */
	@Override
	public PageInfo<Hb78> getClassroomInfo(Hb78 hb78) throws Exception {
		PageHelper.offsetPage(0, 1000);//��ҳ������
//		PageHelper.offsetPage(hb63.getOffset(), hb63.getLimit());
		hb78.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
		List<Hb78> list=apiAppraisalCompanyMapper.getClassroomInfo(hb78);
		PageInfo<Hb78> pageinfo = new PageInfo<Hb78>(list);
		return pageinfo;
	}
	
	/**
	 * ��ȡ����������Ϣ
	 */
	@Override
	public PageInfo<Hb62> getQualityInfo(Hb62 hb62) throws Exception{
		PageHelper.offsetPage(hb62.getOffset(), hb62.getLimit());
		
		String aaa005 = apiAppraisalCompanyMapper.getAa01ByAaa001("AREA_ID");
		List<Hb62> list = new ArrayList<>();
		if("320904".equals(aaa005)) {
			list=apiAppraisalCompanyMapper.getQualityInfo_df(hb62);
		}else if("610000".equals(aaa005)) {
			list=apiAppraisalCompanyMapper.getQualityInfo_sx(hb62);
		}else{
			list=apiAppraisalCompanyMapper.getQualityInfo(hb62);
		}
		PageInfo<Hb62> pageinfo = new PageInfo<Hb62>(list);
		return pageinfo;
	}

	/**
	 * ���濼����Ϣ
	 */
	@Override
	public AjaxReturnMsg saveGroundInfo(Hb63 hb63) throws Exception{
		try{
			String[] chb060Datas = hb63.getChb060Data().split(";");
			// ���㼯��
			List<Hb76> hb76list = new ArrayList();
			String chb076String = "";
			Hb76 hb76 = null;
			for(int i=0;i<chb060Datas.length;i++) {
				hb76 = new Hb76();
				chb076String += "'" + chb060Datas[i].split(",")[0] + "',";
				hb76.setChb076(chb060Datas[i].split(",")[0]);
				hb76.setChb178(chb060Datas[i].split(",")[1]);
				hb76.setAae013(chb060Datas[i].split(",")[2]);
				hb76.setAae011(hb63.getAae011());
				hb76.setAab001(hb63.getAab001());
				hb76.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				hb76list.add(hb76);
			}
			// ���¿�����Ϣ
//			apiAppraisalCompanyMapper.delHb76Hb78(chb076String.substring(1, chb076String.length() - 2), hb63.getAab001(), COMAAE100Enum.DATA_INVALID.getCode());
			// ��Ч��ԭ�п�����Ϣ
			apiAppraisalCompanyMapper.delHb76(hb63.getAab001(), COMAAE100Enum.DATA_INVALID.getCode());
			// ���¿�����Ϣ
			apiAppraisalCompanyMapper.saveHb76(hb76list);
			return this.success("���濼����Ϣ�ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("���濼����Ϣʧ�ܣ�����ԭ��:"+e);
	    }
	}
	/**
	 * ���濼����Ϣ
	 */
	@Override
	public AjaxReturnMsg saveClassroomInfo(Hb63 hb63) throws Exception{
		try{
			String[] chb064Datas = hb63.getChb064Data().split(";");
			// ���㼯��
			List<Hb78> hb78list = new ArrayList();
			Hb78 hb78 = null;
			String chb076 = "";
			for(int i=0;i<chb064Datas.length;i++) {
				hb78 = new Hb78();
				chb076 = chb064Datas[i].split(",")[1];
				hb78.setChb078(chb064Datas[i].split(",")[0]);
				hb78.setChb076(chb064Datas[i].split(",")[1]);
				hb78.setChb178(chb064Datas[i].split(",")[2]);
				hb78.setChb166(Short.parseShort(chb064Datas[i].split(",")[3]));
				hb78.setAae013(chb064Datas[i].split(",")[4]);
				hb78.setChb179(chb064Datas[i].split(",")[5]);
				hb78.setAae011(hb63.getAae011());
				hb78.setAae100(COMAAE100Enum.DATA_EFFECTIVE.getCode());
				hb78list.add(hb78);
			}
			//��Ч��֮ǰ�Ŀ���������Ϣ
			apiAppraisalCompanyMapper.delHb78(chb076, COMAAE100Enum.DATA_INVALID.getCode());
			// ��������
			apiAppraisalCompanyMapper.saveHb78(hb78list);
			return this.success("���濼�㼰������Ϣ�ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("���濼�㼰������Ϣʧ�ܣ�����ԭ��:"+e);
	    }
	}
	
	
	/**
	 * �������������Ϣ
	 */
	public AjaxReturnMsg saveQualityInfo(Hb62 hb62) throws Exception{
		try{
			String[] chb062Datas = hb62.getChb062Data().split(";");
			String chb062 = "";
			String aca111 = "";
			String aca11a = "";
			String chb624 = "";
			String chb625 = "";
			String chb062s = "";
			CodeValue codevalue= new CodeValue();
			for(int i=0;i<chb062Datas.length;i++) {
				chb062s+=chb062Datas[i].split(",")[0]+",";
				chb062 = chb062Datas[i].split(",")[0];
				aca111 = chb062Datas[i].split(",")[1];
				aca11a = chb062Datas[i].split(",")[2];
				chb624 = chb062Datas[i].split(",")[3];
				chb625 = chb062Datas[i].split(",")[4];
				hb62.setChb062(chb062);
				
				String aaa005 = apiAppraisalCompanyMapper.getAa01ByAaa001("AREA_ID");
				if("320904".equals(aaa005)) {
					codevalue.setCode_type("ACA109");
					codevalue.setCode_name(aca111);
					codevalue.setCode_level("3");
					hb62.setAca111(apiAppraisalCompanyMapper.getCodeValueByName(codevalue).get(0).getCode_seq());
				}else if("610000".equals(aaa005)){
					hb62.setAca111(apiAppraisalCompanyMapper.getCa11ByAca112(aca111));
					codevalue.setCode_type("ACA11A");
					codevalue.setCode_name(aca11a);
					hb62.setAca11a(apiAppraisalCompanyMapper.getCodeValueByName(codevalue).get(0).getCode_value());
					hb62.setChb624(chb624);
					hb62.setChb625(chb625);
			    }else{
					codevalue.setCode_type("ACA109");
					codevalue.setCode_name(aca111.split(" ")[0]);
					hb62.setAca111(apiAppraisalCompanyMapper.getCodeValueByName(codevalue).get(0).getCode_seq());
					codevalue.setCode_type("CHB210");
					codevalue.setCode_name(aca111.split(" ")[1]);
					hb62.setChb210(apiAppraisalCompanyMapper.getCodeValueByName(codevalue).get(0).getCode_value());
				}
				
				//����Ǹ��²���������������
				String count_str = apiAppraisalCompanyMapper.checkQualityInfo(chb062);
				if("0".equals(count_str)) {
					//����
					int count = apiAppraisalCompanyMapper.insertHb62(hb62);
					if(count==0) {
						return this.error("����������Ϣʧ�ܣ�����ԭ��:����������Ϣ����");
					}
				}else {
					//����
					int count = apiAppraisalCompanyMapper.updateHb62(hb62);
					if(count==0) {
						return this.error("����������Ϣʧ�ܣ�����ԭ��:����������Ϣ����");
					}
				}
			}
			hb62.setChb062s(chb062s.split(","));
			//ɾ�����ѱ��Ƴ���������Ϣ
			apiAppraisalCompanyMapper.delHb62(hb62);
			return this.success("����������Ϣ�ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("����������Ϣʧ�ܣ�����ԭ��:"+e);
	    }
	}
	
	/**
     * ������֯id��ȡ������Ϣ
     */
	@Override
	public Ad01 getAD01ById(String groupid) throws Exception {

		Ad01 ad01 = apiAppraisalCompanyMapper.getAD01ById(groupid);
		if (ad01.getBus_uuid() != null) {
			Ad01 newAd01 = apiAppraisalCompanyMapper.getAd01ByBusUuid(ad01.getBus_uuid());
//            File file=new File(localdir+newAd01.getFile_rel_path());
//            if(file.exists()){
			//��ͼƬ����ƴ����ȥ
				String fileModule = AppConfig.getProperties("fileModule");
				ad01.setFile_rel_path(fileModule+newAd01.getFile_rel_path());
//			}
		}
		return ad01;
	}
	
	/**
     * �ϴ�ͼƬ�����޸���֯����bus_uuid
     */
	@Override
	public AjaxReturnMsg uploadLogo(String userid, String file_bus_id, String file_name, MultipartFile multipartFile) {
		try {
			//����ͼƬ���ļ���������ͬʱ����ͼƬ��¼
			SFileRecord sFileRecord = fileLoadService.uploadFile(multipartFile, Param.TRAIN_GROUP_LOGO, file_name,
					file_bus_id);
			Ad01 ad01 = new Ad01();
			ad01.setFile_bus_id(sFileRecord.getFile_bus_id());
			ad01.setBus_uuid(sFileRecord.getBus_uuid());
			apiAppraisalCompanyMapper.updateAd01ByBusUuid(ad01);
			return this.success(sFileRecord);
			
		} catch (Exception e) {
			return this.error(e.getMessage());
		}
	}

	/**
	 * ������Ϣ�޸�
	 */
	@Override
	public AjaxReturnMsg saveData(Ad01 ad01) {
		int num = apiAppraisalCompanyMapper.saveAd01ByGroupid(ad01);
		int num2 = apiAppraisalCompanyMapper.saveSmtgroupByGroupid(ad01);
		if(num ==1 && num2 ==1) {
			return this.success("������Ϣ�޸ĳɹ�");
		}else {
			return this.success("������Ϣ�޸�ʧ��");
		}
	}
	
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	public CodeValue getAca112(String codename) throws Exception{
		
		CodeValue codevalue = new CodeValue();
		String aaa005 = apiAppraisalCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("320904".equals(aaa005)) {
			codevalue =(CodeValue) apiAppraisalCompanyMapper.getAca112_df(codename);
		}else if("610000".equals(aaa005)){
			codevalue =(CodeValue) apiAppraisalCompanyMapper.getAca112_sx(codename);
		}else{
			codevalue =(CodeValue) apiAppraisalCompanyMapper.getAca112(codename);
		}
		return codevalue;
	}

}
