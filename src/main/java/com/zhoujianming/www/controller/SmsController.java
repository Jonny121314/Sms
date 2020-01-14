package com.zhoujianming.www.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhoujianming.www.service.SmsService;
import com.zhoujianming.www.utils.JsonResult;
import com.zhoujianming.www.utils.ParameterUtils;


@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600)
@RequestMapping("/client")
public class SmsController {

	@Autowired
	private SmsService smsService;
	
	@RequestMapping("/smscode")
	public JsonResult<Object> smscode() {
		Map<String, Object> params = ParameterUtils.getParameterMap();
		return smsService.smsCode(params);
	}
}
