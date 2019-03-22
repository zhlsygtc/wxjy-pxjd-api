package com.insigma.mvc.serviceimp.monitor.PXJG_001_005;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.insigma.mvc.dao.train.PXYW_001_013.ApiHeadTeacherMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.listener.AppConfig;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.monitor.PXJG_001_001.CollectMapper;
import com.insigma.mvc.dao.monitor.PXJG_001_005.AttendanceByCourseMapper;
import com.insigma.mvc.model.train.*;
import com.insigma.mvc.service.common.fileupload.ApiFileUploadService;
import com.insigma.mvc.service.monitor.PXJG_001_005.AttendanceByCourseService;

/**
 * �ɼ�
 * 
 * @author jewel 2018-01-10
 */
@Service
public class AttendanceByCourseServiceImpl extends MvcHelper implements AttendanceByCourseService {

	@Resource
	private AttendanceByCourseMapper attendanceByCourseMapper;
	@Resource
	private ApiFileUploadService fileLoadService;
	@Resource
	private CollectMapper collectMapper;
    @Resource
    private ApiHeadTeacherMapper apiHeadTeacherMapper;


	/**
	 * ��ѯ��ѵ�����б�
	 */
	@Override
	public PageInfo<Hb69DTO> getInfoList(Hb69DTO param) throws Exception {
		Hb57 hb57 = apiHeadTeacherMapper.getHeadTeacherByUserId(param.getAae011());
		if(hb57 != null && "02".equals(hb57.getChb299())) {
			param.setChb057(hb57.getChb057());
		}
		PageHelper.offsetPage(param.getOffset(), param.getLimit());
		if (StringUtils.isNotEmpty(param.getChb103())) {
			param.setChb103s(param.getChb103().split(","));
		}
		if (StringUtils.isNotEmpty(param.getChb315())) {
			param.setChb315s(param.getChb315().split(","));
		}
		List<Hb69DTO> list = attendanceByCourseMapper.getInfoList(param);
		PageInfo<Hb69DTO> pageinfo = new PageInfo<Hb69DTO>(list);
		return pageinfo;
	}

	/**
	 * ���ݰ༶�����ѯ��ѵ����ѧ���б�
	 */
	@Override
	public PageInfo<Hc60> getHc60ById(Hc60 hc60) throws Exception {
		PageHelper.offsetPage(hc60.getOffset(), hc60.getLimit());
		List<Hc60> list = attendanceByCourseMapper.getHc60ById(hc60);
		String fileModule = AppConfig.getProperties("fileModule");
		for(Hc60 hc601:list) {
			hc601.setBus_uuid(fileModule + hc601.getBus_uuid());
		}
		PageInfo<Hc60> pageinfo = new PageInfo<Hc60>(list);
		return pageinfo;
	}

	/**
	 * ���ݰ༶�����ѯ��ѵ����Ϣ
	 */
	@Override
	public Hb69 getHb69ById(Hb69 hb69) throws Exception {
		return attendanceByCourseMapper.getHb69ById(hb69);
	}

	// ������Ϣ
	@Override
	public AjaxReturnMsg saveData(Zc10 zc10) throws Exception {
		zc10.setAae036(new Date());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		Date date = new Date();
		// �жϴ����֤�Ƿ��Ѿ��ɼ�
		/*List<Hc60DTO> list00 = collectMapper.checkAac002(zc10.getAac002());
		if (list00.size() == 0) {
			return this.error("�����ָ����Ϣδ�ɼ������Ƚ���ָ�Ʋɼ�");
		}*/
		
		// �ж�ѧԱ
		List<Hc60> list0 = attendanceByCourseMapper.getHc60ByAac002(zc10);
		if (list0.size() > 0) {
			zc10.setChc001(list0.get(0).getChc001());
			zc10.setAac001(list0.get(0).getAac001());
			// �жϴ����֤ �γ��Ƿ��Ѿ�����
			List<Zc10> list = attendanceByCourseMapper.checkZc10(zc10);
			if (list.size() > 0) {
				zc10.setCzc010(list.get(0).getCzc010());
				if ("1".equals(zc10.getType())) {
					// zc10.setEdc362(date);
					return this.error(zc10.getAac003() + "�Ѵ򿨳ɹ����벻Ҫ�ظ��򿨣�");

				} else if ("2".equals(zc10.getType())) {
					zc10.setEdc363(date);
				}
				int num = attendanceByCourseMapper.updateAttendance(zc10);
				if (num == 1) {
					return this.success(zc10.getAac003() + "�򿨳ɹ���", zc10.getAac002());
				} else {
					return this.error(zc10.getAac003() + "��ʧ�ܣ�");
				}
			} else {
				if ("1".equals(zc10.getType())) {
					zc10.setEdc362(date);
				} else if ("2".equals(zc10.getType())) {
					zc10.setEdc363(date);
				}
				int num = attendanceByCourseMapper.addAttendance(zc10);
				if (num == 1) {
					return this.success(zc10.getAac003() + "�򿨳ɹ���", zc10.getAac002());

				} else {
					return this.error(zc10.getAac003() + "��ʧ�ܣ�");
				}
			}
		} else {
			// �жϽ�ʦ
			List<Hb61> list1 = attendanceByCourseMapper.getHb61ByAac002(zc10);
			if (list1.size() > 0) {
				zc10.setChb061(list1.get(0).getChb061());

				// �жϴ����֤ �γ��Ƿ��Ѿ�����
				List<Zc10> list = attendanceByCourseMapper.checkZc11(zc10);
				if (list.size() > 0) {
					zc10.setCzc011(list.get(0).getCzc011());
					if ("1".equals(zc10.getType())) {
						// zc10.setEdc362(date);
						return this.error(zc10.getAac003() + "�Ѵ򿨳ɹ����벻Ҫ�ظ��򿨣�");

					} else if ("2".equals(zc10.getType())) {
						zc10.setEdc363(date);
					}
					int num = attendanceByCourseMapper.updateTeacherAttendance(zc10);
					if (num == 1) {
						return this.success(zc10.getAac003() + "�򿨳ɹ���", zc10.getAac002());
					} else {
						return this.error(zc10.getAac003() + "��ʧ�ܣ�");
					}
				} else {
					if ("1".equals(zc10.getType())) {
						zc10.setEdc362(date);
					} else if ("2".equals(zc10.getType())) {
						zc10.setEdc363(date);
					}
					int num = attendanceByCourseMapper.addTeacherAttendance(zc10);
					if (num == 1) {
						return this.success(zc10.getAac003() + "�򿨳ɹ���", zc10.getAac002());
					} else {
						return this.error(zc10.getAac003() + "��ʧ�ܣ�");
					}
				}
			} else {
				return this.error("�������Ϣ�����ڵ�ǰ�༶�����ʵ��");
			}
		}

	}

	public boolean checkTime(String date, int time) {
		if (Integer.parseInt(date.substring(11, 13)) < time) {
			return true;
		}
		return false;
	}

   

    @Override
    public AjaxReturnMsg getHc60ById1(Hc60 hc60) throws Exception {
        HashMap map = new HashMap();
        List<Hc60> list = attendanceByCourseMapper.getHc60ById1(hc60);
        String fileModule = AppConfig.getProperties("fileModule");
		for(Hc60 hc601:list) {
			hc601.setBus_uuid(fileModule + hc601.getBus_uuid());
		}
        map.put("hc60list", list);
        return this.success(map);
    }

}
