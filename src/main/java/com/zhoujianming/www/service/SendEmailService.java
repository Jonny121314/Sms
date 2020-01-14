package com.zhoujianming.www.service;

import java.util.Map;

import com.zhoujianming.www.utils.JsonResult;

public interface SendEmailService {

	JsonResult<Object> sendEmail(Map<String, Object> params);

}
