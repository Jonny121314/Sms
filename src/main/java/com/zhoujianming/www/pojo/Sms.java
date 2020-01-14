package com.zhoujianming.www.pojo;

import java.io.Serializable;

/**
 * 
 * @author zhoujianming
 *用户登录手机，验证码
 */
public class Sms implements Serializable{
	private static final long serialVersionUID = 1L;
	private String phone;   //电话号码
    private String code;    //短信验证码
    private Long time;      //短信验证码生成时间
  
    public Sms() {
    }
  
    public Sms(String phone, String code, Long time) {
        this.phone = phone;
        this.code = code;
        this.time = time;
    }
  
    public String getPhone() {
        return phone;
    }
  
    public void setPhone(String phone) {
        this.phone = phone;
    }
  
    public String getCode() {
        return code;
    }
  
    public void setCode(String code) {
        this.code = code;
    }
  
    public Long getTime() {
        return time;
    }
  
    public void setTime(Long time) {
        this.time = time;
    }
	
}
