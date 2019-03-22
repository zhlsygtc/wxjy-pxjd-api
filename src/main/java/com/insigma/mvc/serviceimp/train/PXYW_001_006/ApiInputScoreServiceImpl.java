package com.insigma.mvc.serviceimp.train.PXYW_001_006;

import java.util.List;
import javax.annotation.Resource;
import com.insigma.mvc.dao.train.PXYW_001_013.ApiHeadTeacherMapper;
import com.insigma.mvc.model.train.Hb57;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.train.PXYW_001_001.ApiCompanyMapper;
import com.insigma.mvc.dao.train.PXYW_001_006.ApiInputScoreMapper;
import com.insigma.mvc.model.train.Hb68;
import com.insigma.mvc.model.train.Student;
import com.insigma.mvc.model.train.Zc13;
import com.insigma.mvc.service.train.PXYW_001_006.ApiInputScoreService;
/**
 * ��������
 * @author zhanghl
 * 2018-01-10
 */
@Service
public class ApiInputScoreServiceImpl extends MvcHelper implements ApiInputScoreService {
	
	@Resource
	private ApiInputScoreMapper inputScoreMapper;
	@Resource
	private ApiCompanyMapper apiCompanyMapper;
    @Resource
    private ApiHeadTeacherMapper apiHeadTeacherMapper;

	/**
	 * ��ѯ��ѵ�����б�
	 */
	@Override
	public PageInfo<Hb68> getInfoList(Hb68 hb68) throws Exception {
		PageHelper.offsetPage(hb68.getOffset(), hb68.getLimit());
		if(StringUtils.isNotEmpty(hb68.getChb103())) {
			hb68.setChb103s(hb68.getChb103().split(","));
		}
		if(StringUtils.isNotEmpty(hb68.getChn198())) {
			hb68.setChn198s(hb68.getChn198().split(","));
		}
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherByUserId(hb68.getAae011());
		if(hb57 != null && "02".equals(hb57.getChb299())) {
		    hb68.setChb057(hb57.getChb057());
        }
		List<Hb68> list=inputScoreMapper.getInfoList(hb68);
		PageInfo<Hb68> pageinfo = new PageInfo<Hb68>(list);
		return pageinfo;
	}
	/**
	 * ����ID��ѯ�༶��Ϣ
	 */
	@Override
	public Hb68 getById(Hb68 hb68) throws Exception {
		String aaa005 = apiCompanyMapper.getAa01ByAaa001("AREA_ID");
		if("320904".equals(aaa005)) {
			hb68=inputScoreMapper.getById_df(hb68.getChb100());
		}else if("610000".equals(aaa005)){
			hb68=inputScoreMapper.getById_sx(hb68.getChb100());
	    }else{
			hb68=inputScoreMapper.getById(hb68.getChb100());
		}
		return hb68;
	}
	/**
	 * ��ѯѧԱ�ɼ�
	 */
	@Override
	public PageInfo<Student> getStuListForLook(Student stu) throws Exception {
		PageHelper.offsetPage(0, 1000);
		List<Student> list=inputScoreMapper.getStuListForLook(stu.getChb100());
		PageInfo<Student> pageinfo = new PageInfo<Student>(list);
		return pageinfo;
	}
	/**
	 * ����רҵ���Ʋ�ѯ��ѵ�������Ƽ�רҵ���
	 */
	public Hb68 getAca112(String aca110) throws Exception{
		Hb68 hb68 =(Hb68) inputScoreMapper.getAca112(aca110);
		return hb68;
	}
	/**
	 * ����ѧԱ��ѧԱ��hc60������ѡ�ϸ��벻�ϸ�
	 */
	public AjaxReturnMsg doQualified(Student stu) throws Exception{
		try{
			//flagΪtrue���ʾҳ���ϴ��ڳɼ����������ݿ����޳ɼ�����ִ��һ����ճɼ�����
			if("true".equals(stu.getFlag())) {
				//������۲����ۺϳɼ�
				inputScoreMapper.clearHc66(stu.getChb100());
			}
			//����ѧԱ�Ƿ���66����   ����������66��
			for(String chc001:stu.getChc001s()) {
				stu.setChc001(chc001);
				String count_hc66 = inputScoreMapper.checkHc66(stu.getChc001());
				if("0".equals(count_hc66)) {//��ѧԱû����66���У���Ҫ����
					inputScoreMapper.createHc66(stu);
				}
				//¼��ѧԱ�ɼ�
				inputScoreMapper.updateHc66(stu);
			}
			//¼��ѧԱ�ɼ����Ϊ����¼��
			inputScoreMapper.updateHb68PartStatus(stu);
			//����Ƿ�ѧԱ����¼���˳ɼ�������¼�������޸İ༶¼��״̬
			inputScoreMapper.updateHb68Status(stu);
			return this.success("¼��ɹ��ҳɼ���Ϣ�ѱ���");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("¼��ʧ�ܣ�����ԭ��:"+e);
	    }
	}
	/**
	 * ����ѧԱ��ѧԱ��hb60�����ֶ�¼��ɼ�
	 */
	public AjaxReturnMsg saveScore(Student stu) throws Exception{
		try{
			String contentValue = stu.getContentVal();
			String[] contents = contentValue.split(";");
			String aac002;
			String chc014;
			String chc019;
			String chc016;
			int count;
			int count_check = 0;//У��ҳ����¼��ĳɼ������Ƿ�һ��
			Zc13 zc13 = getZc13(stu.getAab001());
			Double czc910 = zc13.getCzc910();//���ۺϸ�ɼ�
			Double czc912 = zc13.getCzc912();//ʵ�ٺϸ�ɼ�
			Double czc914 = zc13.getCzc914();//�ۺϺϸ�ɼ�
			//����ѧԱ�Ƿ���66����   ����������66��
			for(int i=0;i<contents.length;i++) {
				String aac002Score = contents[i];
				count = 0;
				aac002 = aac002Score.split(",")[0];
				chc014 = aac002Score.split(",")[1];
				chc019 = aac002Score.split(",")[2];
				chc016 = aac002Score.split(",")[3];
				stu.setAac002(aac002);
				//��������ɼ���Ϊ����ϸ��־��¼��
				if(("".equals(chc014) || "-".equals(chc014))
						&& ("".equals(chc016) || "-".equals(chc016))
						&& ("".equals(chc019) || "-".equals(chc019))) {
				}else {
					stu.setChc018("1");//������һ�ųɼ����ʼֵ��Ϊ�ϸ�
				}
				if(!"".equals(chc014) && !"-".equals(chc014)) {
					stu.setChc014(chc014);//����
					if(Double.parseDouble(chc014) < czc910) {
						stu.setChc018("0");//���ϸ�
					}
					count+=1;
				}
				if(!"".equals(chc019) && !"-".equals(chc019)) {
					stu.setChc019(chc019);//ʵ��
					if(Double.parseDouble(chc019) < czc912) {
						stu.setChc018("0");//���ϸ�
					}
					count+=2;
				}
				if(!"".equals(chc016) && !"-".equals(chc016)) {
					stu.setChc016(chc016);//�ۺ�
					if(Double.parseDouble(chc016) < czc914) {
						stu.setChc018("0");//���ϸ�
					}
					count+=3;
				}
				if(i==0) {//��һ��ѧԱ�ĳɼ�������һ����¼���ڱȽ�
					count_check = count;
				}
				if(count_check != count) {//������� ���ʾ�ɼ�������һ�� 
					i++;
					return this.error("���ã��� <span style = \"color:orange\">"
										+i+"</span> ��ѧԱ�ɼ�ͬ����ѧԱ�ɼ���Ŀ����һ�£����޸�");
				}
				String count_hc66 = inputScoreMapper.checkByAac002AndChb100(stu);
				if("0".equals(count_hc66)) {//��ѧԱû����66���У���Ҫ����
					inputScoreMapper.createHc66ByAac002AndChb100(stu);
				}
				//¼��ѧԱ�ɼ�
				inputScoreMapper.updateHc66ByAac002AndChb100(stu);
				//����ѧԱ�ɷ�֤
				inputScoreMapper.updateHc60Chc029(stu);
				//����student
				stu.setChc014("");
				stu.setChc019("");
				stu.setChc016("");
				stu.setChc018("");
			}
			//¼��ѧԱ�ɼ����Ϊ����¼��
			inputScoreMapper.updateHb68PartStatus(stu);
			//����Ƿ�ѧԱ����¼���˳ɼ�������¼�������޸İ༶¼��״̬
			inputScoreMapper.updateHb68Status(stu);
			return this.success("�ֶ�¼��ɼ��ѱ���ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			return this.error("�ֶ�¼��ɼ�����ʧ�ܣ�����ԭ��:"+e);
	    }
	}
	/**
	 * �ύ������Ϣ
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> submitScore(Hb68 hb68) {
		try {
			inputScoreMapper.submitScore(hb68);
			return this.success("�ύ�ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("�ύʧ��,��ȷ���ð༶�ɼ���Ϣ���ύ�ɹ�");
		}
	}
	/**
	 * �����ɼ���Ϣ
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> revokeScore(Hb68 hb68) {
		try {
			int count =inputScoreMapper.revokeScore(hb68);
			if(count==1){
				return this.success("�����ɹ�");
			}else{
				return this.error("����ʧ��,��ȷ�������Ѿ���������");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return this.error("����ʧ��,��ȷ���ð༶�ѳ����ɹ�");
		}
	}
	/**
	 * ��ѯ�ɼ���׼��Ϣ
	 */
	@Override
	public Zc13 getZc13(String aab001) throws Exception {
		//�Դ˻���id��ѯgroupid ��groupid��ѯ�ɼ���׼ һֱ�鵽Ϊֹ   �鲻����Ĭ�ϸ�0
		String groupid ;
		String areaId = inputScoreMapper.getAreaId();
		Zc13 zc13 = null;
		int count = 0;
		do {
			if(count > 20 || "G001".equals(aab001)) {//��ֹͻ�����������ѭ��
				//��������û�б�׼��д0
				zc13 = new Zc13();
				zc13.setCzc909(0.00);
				zc13.setCzc910(0.00);
				zc13.setCzc911(0.00);
				zc13.setCzc912(0.00);
				zc13.setCzc913(0.00);
				zc13.setCzc914(0.00);
				return zc13;
			}
			if(areaId.equals(aab001)) {
				//��������û�б�׼��д0
				zc13 = new Zc13();
				zc13.setCzc909(0.00);
				zc13.setCzc910(0.00);
				zc13.setCzc911(0.00);
				zc13.setCzc912(0.00);
				zc13.setCzc913(0.00);
				zc13.setCzc914(0.00);
				return zc13;
			}
			groupid = inputScoreMapper.getGroupId(aab001);
			zc13=inputScoreMapper.getZc13(groupid);
			aab001 = groupid;
			count++;
		}while(zc13 == null);
		return zc13;
	}
}
