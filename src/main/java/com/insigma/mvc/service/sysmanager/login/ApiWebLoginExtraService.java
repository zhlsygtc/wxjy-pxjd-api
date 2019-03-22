package com.insigma.mvc.service.sysmanager.login;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.train.Ad01;
import com.insigma.mvc.model.train.Hb63;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApiWebLoginExtraService {

	/**
	 * ÐÞ¸ÄÃÜÂë
	 */
	public AjaxReturnMsg updPassword(SUser suser);
}
