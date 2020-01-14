package com.zhoujianming.www.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Page<T> {
	//默认每页5条记�?
	private static final int DEFAULT_PAGE_SIZE = 5;
	// 当前�?, 默认为第1�?
	private int page = 1;
	// 每页记录�?
	private int limit = DEFAULT_PAGE_SIZE;
	// 总记录数, 默认�?-1, 表示�?要查�?
	private long total = -1;
	// 总页�?, 默认�?-1, 表示�?要计�?
	private int totalPage = -1;
	// 当前页记录List形式
	private List<T> items;
	//存一个需要带回的属�??
	private Map<String,Object> attrs = null;
	//默认：带宽利用率峰�?�排�?
	private String sort = "peak";
	//环网层次默认�?
	private String level = "SH, ZH, XH";
	//折点默认�?1
	private int convergeListPot = 1;
	

	public Map<String, Object> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, Object> attrs) {
		this.attrs = attrs;
	}

	private Map<String, Object> params = new HashMap<String, Object>();// 设置页面传�?�的查询参数
	
	public Page(Map<String,Object> params) {
		setPage(MapUtils.getIntValue(params, "page"));
		setLimit(MapUtils.getIntValue(params, "limit"));
		params.put("limit", getLimit());
		params.put("offset", getOffset());
		setParams(params);
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return (page - 1) * limit;
	}
	
	public long getTotal() {
		return total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotal(long total) {
		this.total = total;
		computeTotalPage();
	}

	protected void computeTotalPage() {
		if (getLimit() > 0 && getTotal() > -1) {
			this.totalPage = (int) (getTotal() % getLimit() == 0 ? getTotal()
					/ getLimit() : getTotal() / getLimit() + 1);
		}
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getConvergeListPot() {
		return convergeListPot;
	}

	public void setConvergeListPot(int convergeListPot) {
		this.convergeListPot = convergeListPot;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append("Page [pageNo=")
				.append(page).append(", pageSize=").append(limit)
				.append(", totalRecord=").append(total < 0 ? "null" : total)
				.append(", totalPage=")
				.append(totalPage < 0 ? "null" : totalPage)
				.append(", curPageObjects=")
				.append(items == null ? "null" : items.size()).append("]");
		return builder.toString();
	}
	
	public String toJSONString() {
		this.params = null;
		return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
	}
	
}