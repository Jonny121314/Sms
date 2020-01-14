package com.zhoujianming.www.pojo;
/**
 * @author zhoujianming
 *阿里云短信实体类
 */
public class AliSMSInfo {
	private String  accessId;

	private String  accessKey;

	private String  signName;
	
	private String  codeTemplate;
	
	private String  product;
		
	private String  domain;	

	public AliSMSInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getCodeTemplate() {
		return codeTemplate;
	}

	public void setCodeTemplate(String codeTemplate) {
		this.codeTemplate = codeTemplate;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "AliSMSInfo [accessId=" + accessId + ", accessKey=" + accessKey + ", signName=" + signName
				+ ", codeTemplate=" + codeTemplate + ", product=" + product + ", domain=" + domain + "]";
	}
}
