package com.zhoujianming.www.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhoujianming.www.service.SendEmailService;
import com.zhoujianming.www.utils.JsonResult;
import com.zhoujianming.www.utils.ParameterUtils;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600)
@RequestMapping("/client")
public class SendEmailController {

	@Autowired
	private SendEmailService sendEmailService;
	
	/**
	 * 发邮件
	 * @return
	 */
	@RequestMapping("/sendEmail")
	public JsonResult<Object> sendEmail() {
		Map<String, Object> params = ParameterUtils.getParameterMap();
		return sendEmailService.sendEmail(params);
	}
}
