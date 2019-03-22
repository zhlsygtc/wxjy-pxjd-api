package com.insigma.mvc.serviceimp.train.PXYW_001_001;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.train.PXYW_001_001.ApiCompanyMapper;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.Param;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb62;
import com.insigma.mvc.model.train.Hb63;
import com.insigma.mvc.model.train.Hb64;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.train.PXYW_001_001.ApiCompanyService;

import java.util.ArrayList;
import java.util.List;

/**
 * ������Ϣά��
 * @author link
 * 2018-02-26
 */
@Service
public class ApiCompanySerivceImpl extends MvcHelper implements ApiCompanyService {

	private  static  String localdir = AppConfig.getProperties("localdir");
	@Resource
	private ApiCompanyMapper apiCompanyMapper;
	@Resource
	private ApiFileUploadService fileLoadService;
	/**
	 * ��ȡ����������Ϣ
	 */
	@Override
	public PageInfo<Hb63> getGroundInfo(Hb63 hb63) throws Exception {
		PageHelper.offsetPage(0, 1000);//��ҳ������
//		PageHelper.offsetPage(hb63.getOffset(), hb63.getLimit());
		List<Hb63> list=apiCompanyMapper.getGroundInfo(hb63);
		PageInfo<Hb63> pageinfo = new PageInfo<Hb63>(list);
		return pageinfo;
	}
	/**
	 * ��ȡcode_value����--chb065
	 */
	@Override
	public List<Hb64> getCodeValueChb065() throws Exception {
		List<Hb64> hb64List=apiCompanyMapper.getCodeValueChb065();
		return hb64List;
	}
	/**
	 * ��ȡcode_value����--chb163
	 */
	@Override
	public List<Hb64> getCodeValueChb163() throws Exception {
		List<Hb64> hb64List=apiCompanyMapper.getCodeValueChb163();
		return hb64List;
	}
	/**
	 * ��ȡcode_value����--chb070
	 */
	@Override
	public List<Hb64> getCodeValueChb070() throws Exception {
		List<Hb64> hb64List=apiCompanyMapper.getCodeValueChb070();
		return hb64List;
	}
	/**
	 * ��ȡ����������Ϣ
	 */
	@Override
	public PageInfo<Hb63> getClassroomInfo(Hb63 hb63) throws Exception {
		PageHelper.offsetPage(0, 1000);//��ҳ������
//		PageHelper.offsetPage(hb63.getOffset(), hb63.getLimit());
		List<Hb63> list=apiCompanyMapper.getClassroomInfo(hb63);
		PageInfo<Hb63> pageinfo = new PageInfo<Hb63>(list);
		return pageinfo;
	}
	
	/**
	 * ��ȡ����������Ϣ
	 */
	@Override
	public PageInfo<Hb62> getQualityInfo(Hb62 hb62) throws Exception{
		PageHelper.offsetPage(hb62.getOffset(), hb62.getLimit());
		
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		List<Hb62> list = new ArrayList<>();
		if("320904".equals(aaa005)) {
			list=apiCompanyMapper.getQualityInfo_df(hb62);
		}else if("610000".equals(aaa005)) {
			list=apiCompanyMapper.getQualityInfo_sx(hb62);
		}else{
			list=apiCompanyMapper.getQualityInfo(hb62);
		}
		PageInfo<Hb62> pageinfo = new PageInfo<Hb62>(list);
		return pageinfo;
	}

	/**
	 * �������������Ϣ
	 */
	public AjaxReturnMsg saveGroundInfo(Hb63 hb63) throws Exception{
		try{
			String[] chb060Datas = hb63.getChb060Data().split(";");
			String chb060 = "";
			String aae006 = "";
			String chb017 = "";
			String aae013 = "";
			String chb060s = "";
			for(int i=0;i<chb060Datas.length;i++) {
				chb060s+=chb060Datas[i].split(",")[0]+",";
				chb060 = chb060Datas[i].split(",")[0];
				aae006 = chb060Datas[i].split(",")[1];
				chb017 = chb060Datas[i].split(",")[2];
				aae013 = chb060Datas[i].split(",")[3];
				hb63.setChb060(chb060);
				hb63.setAae006(aae006);
				hb63.setChb017(new Double(chb017));
				hb63.setAae013(aae013);
				//����Ǹ��²���������������
				String count_str = apiCompanyMapper.checkGround(chb060);
				if("0".equals(count_str)) {
					//����
					int count = apiCompanyMapper.insertHb63(hb63);
					if(count==0) {
						return this.error("���泡����Ϣʧ�ܣ�����ԭ��:����������Ϣ����");
					}
				}else {
					//����
					int count = apiCompanyMapper.updateHb63(hb63);
					if(count==0) {
						return this.error("���泡����Ϣʧ�ܣ�����ԭ��:���³�����Ϣ����");
					}
				}
			}
			hb63.setChb060s(chb060s.split(","));
			//ɾ�����ѱ��Ƴ��ĳ�����Ϣͬʱɾ������󶨵Ľ�����Ϣ
			apiCompanyMapper.delHb63(hb63);
			apiCompanyMapper.delHb64(hb63);
			return this.success("����ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("���泡����Ϣʧ�ܣ�����ԭ��:"+e);
	    }
	}
	/**
	 * �������������Ϣ
	 */
	public AjaxReturnMsg saveClassroomInfo(Hb63 hb63) throws Exception{
		try{
			//���һ��������Ϣû�� ҲӦ������
			if("".equals(hb63.getChb064Data())) {
				//ɾ�����ѱ��Ƴ��Ľ�����Ϣ
				hb63.setChb064s(new String[]{" "});
				apiCompanyMapper.delSingleHb64(hb63);
				return this.success("����ɹ�");
			}
			
			String[] chb064Datas = hb63.getChb064Data().split(";");
			String chb060 = "";
			String chb064 = "";
			String chb065 = "";
			String chb063 = "";
			String chb070 = "";
			String aae013 = "";
			String chb163 = "";
			String chb064s = "";
			for(int i=0;i<chb064Datas.length;i++) {
				chb064s+=chb064Datas[i].split(",")[1]+",";
				chb060 = chb064Datas[i].split(",")[0];
				chb064 = chb064Datas[i].split(",")[1];
				chb065 = chb064Datas[i].split(",")[2];
				chb063 = chb064Datas[i].split(",")[3];
				chb070 = chb064Datas[i].split(",")[4];
				aae013 = chb064Datas[i].split(",")[5];
				chb163 = chb064Datas[i].split(",")[6];
				hb63.setChb060(chb060);
				hb63.setChb064(chb064);
				hb63.setChb065(chb065);
				hb63.setChb063(chb063);
				hb63.setChb070(chb070);
				hb63.setAae013(aae013);
				hb63.setChb163(chb163);
				//����Ǹ��²���������������
				String count_str = apiCompanyMapper.checkClassroom(chb064);
				if("0".equals(count_str)) {
					//����
					int count = apiCompanyMapper.insertHb64(hb63);
					if(count==0) {
						return this.error("���泡����Ϣʧ�ܣ�����ԭ��:����������Ϣ����");
					}
				}else {
					//����
					int count = apiCompanyMapper.updateHb64(hb63);
					if(count==0) {
						return this.error("���泡����Ϣʧ�ܣ�����ԭ��:���½�����Ϣ����");
					}
				}
			}
			hb63.setChb064s(chb064s.split(","));
			//ɾ�����ѱ��Ƴ��Ľ�����Ϣ
			apiCompanyMapper.delSingleHb64(hb63);
			return this.success("����ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("���������Ϣʧ�ܣ�����ԭ��:"+e);
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
			String chb062s = "";
			for(int i=0;i<chb062Datas.length;i++) {
				chb062s+=chb062Datas[i].split(",")[0]+",";
				chb062 = chb062Datas[i].split(",")[0];
				aca111 = chb062Datas[i].split(",")[1];
				hb62.setChb062(chb062);
				
				String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
				if("320904".equals(aaa005)) {
					CodeValue codevalue= new CodeValue();
					codevalue.setCode_type("ACA109");
					codevalue.setCode_name(aca111);
					codevalue.setCode_level("3");
					hb62.setAca111(apiCompanyMapper.getCodeValueByName(codevalue).get(0).getCode_seq());
				}else if("610000".equals(aaa005)){
					hb62.setAca111(apiCompanyMapper.getCa11ByAca112(aca111));
			    }else{
					CodeValue codevalue= new CodeValue();
					codevalue.setCode_type("ACA109");
					codevalue.setCode_name(aca111.split(" ")[0]);
					hb62.setAca111(apiCompanyMapper.getCodeValueByName(codevalue).get(0).getCode_seq());
					codevalue.setCode_type("CHB210");
					codevalue.setCode_name(aca111.split(" ")[1]);
					hb62.setChb210(apiCompanyMapper.getCodeValueByName(codevalue).get(0).getCode_value());
				}
				
				//����Ǹ��²���������������
				String count_str = apiCompanyMapper.checkQualityInfo(chb062);
				if("0".equals(count_str)) {
					//����
					int count = apiCompanyMapper.insertHb62(hb62);
					if(count==0) {
						return this.error("����������Ϣʧ�ܣ�����ԭ��:����������Ϣ����");
					}
				}else {
					//����
					int count = apiCompanyMapper.updateHb62(hb62);
					if(count==0) {
						return this.error("����������Ϣʧ�ܣ�����ԭ��:����������Ϣ����");
					}
				}
			}
			hb62.setChb062s(chb062s.split(","));
			//ɾ�����ѱ��Ƴ���������Ϣ
			apiCompanyMapper.delHb62(hb62);
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

		Ad01 ad01 = apiCompanyMapper.getAD01ById(groupid);
		if (ad01.getBus_uuid() != null) {
			Ad01 newAd01 = apiCompanyMapper.getAd01ByBusUuid(ad01.getBus_uuid());
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
			apiCompanyMapper.updateAd01ByBusUuid(ad01);
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
		int num = apiCompanyMapper.saveAd01ByGroupid(ad01);
		int num2 = apiCompanyMapper.saveSmtgroupByGroupid(ad01);
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
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("320904".equals(aaa005)) {
			codevalue =(CodeValue) apiCompanyMapper.getAca112_df(codename);
		}else if("610000".equals(aaa005)){
			codevalue =(CodeValue) apiCompanyMapper.getAca112_sx(codename);
		}else{
			codevalue =(CodeValue) apiCompanyMapper.getAca112(codename);
		}
		return codevalue;
	}

}
