package com.zhoujianming.www.service;

import java.util.Map;

import com.zhoujianming.www.utils.JsonResult;

public interface SmsService {

	JsonResult<Object> smsCode(Map<String, Object> params);

}
