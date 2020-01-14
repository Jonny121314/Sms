package com.zhoujianming.www.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhoujianming.www.common.dataSource.DataSourceKeyEnum;
import com.zhoujianming.www.common.dataSource.utils.DataSourceContextHolder;
import com.zhoujianming.www.mapper.SendEmailMapper;
import com.zhoujianming.www.service.SendEmailService;
import com.zhoujianming.www.utils.JsonResult;

@Service
public class SendEmailServiceImpl implements SendEmailService {

	@Autowired
	private SendEmailMapper sendEmailMapper;

	@Override
	public JsonResult<Object> sendEmail(Map<String, Object> params) {
		try {
			String emailReceiver = MapUtils.getString(params, "mail");//用户输入的邮箱
			// 邮箱格式校验
			if (!formatValidate(emailReceiver)) {
				return new JsonResult<>("-1", "邮箱格式不正确");
			}
			//设置邮箱发送人信息和邮箱服务器信息
			setSendEmailInfo(params);

			String hostName = MapUtils.getString(params, "hostName");
			String hostPort = MapUtils.getString(params, "hostPort");
			String sender = MapUtils.getString(params, "sender");
			String authCode = MapUtils.getString(params, "authCode");
			String senderName = MapUtils.getString(params, "senderName");


			MultiPartEmail mail = new MultiPartEmail();
			mail.setSSLOnConnect(true);
			// 设置邮箱服务器信息
			mail.setHostName(hostName);
			if (!StringUtils.isEmpty(hostPort)) {
				mail.setSslSmtpPort(hostPort);
			}
			// 设置密码验证器
			mail.setAuthentication(sender, authCode);
			// 设置邮件发送者
			if (StringUtils.isEmpty(senderName)) {
				mail.setFrom(sender);
			} else {
				mail.setFrom(sender, senderName, "UTF-8");
			}
			// 设置邮件接收者
			mail.addTo(emailReceiver);
			// 设置邮件编码
			mail.setCharset("UTF-8");
			// 设置邮件标题
			mail.setSubject("测试标题");
			mail.setMsg("测试内容");//设置内容
			// 创建附件
			EmailAttachment attachment = new EmailAttachment();
			String testPath = "C:\\Users\\xiaomi\\Desktop\\test.docx";//附件路径
			attachment.setPath(testPath);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			mail.attach(attachment);
			// 设置邮件发送时间
			mail.setSentDate(new Date());
			// 发送邮件
			mail.send();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return new JsonResult<Object>("0", "邮箱发送成功");
	}

	/**
	 * 查询数据库配置表获取邮件配置信息
	 * @param params
	 */
	private void setSendEmailInfo(Map<String, Object> params) {
		DataSourceContextHolder.set(DataSourceKeyEnum.DB_COMMON);//切换好DB_COMMON数据库
		List<Map<String, Object>> systemParams = sendEmailMapper.getSystemParams();
		for (Map<String, Object> map : systemParams) {
			if("email_hostName".equals(MapUtils.getString(map, "paramName"))) {
				params.put("hostName", MapUtils.getString(map, "paramValue"));
				continue;
			}
			if("email_hostPort".equals(MapUtils.getString(map, "paramName"))) {
				params.put("hostPort", MapUtils.getString(map, "paramValue"));
				continue;
			}
			if("email_sender".equals(MapUtils.getString(map, "paramName"))) {
				params.put("sender", MapUtils.getString(map, "paramValue"));
				continue;
			}
			if("email_authCode".equals(MapUtils.getString(map, "paramName"))) {
				params.put("authCode", MapUtils.getString(map, "paramValue"));
				continue;
			}
			if("email_senderName".equals(MapUtils.getString(map, "paramName"))) {
				params.put("senderName", MapUtils.getString(map, "paramValue"));
			}
		}		
		DataSourceContextHolder.clear();//清除数据源
	}

	/**
	 * 邮箱校验
	 * @param emailAddress
	 * @return
	 */
	private boolean formatValidate(String emailAddress) {
		Pattern pattern = null;
		Matcher matcher = null;
		String mailRegex, mailName, mailDomain;
		// ^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
		mailName = "^[0-9a-z]+\\w*";
		// ***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
		mailDomain = "([0-9a-z]+\\.)+[0-9a-z]+$";
		// 邮箱正则表达式 ^[0-9a-z]+\w*@([0-9a-z]+\.)+[0-9a-z]+$
		mailRegex = mailName + "@" + mailDomain;
		pattern = Pattern.compile(mailRegex);
		matcher = pattern.matcher(emailAddress);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
