package com.admin.jdbc;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class QueryInfo {

	private Integer begin;
	
	private Integer end;//页面大小
	
	private ArrayList<String> cancel;//多用于取消查找
	
	private String meSelect;//自身只查某个属性 只查某些字段已经打开了关联 使用该字段大部分情况下要配合cancelConnection字段 取消关联
	
	private String sort;//排序的列名
	
	private String order;//升降序
	
	private ArrayList<Order> or ;
	
//	private String menu_id; //当前访问的菜单
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private HashMap<String,Object> search;//查询条件
	
	/**
	 * ServicePublicimpl  createSQLDebris
	 */
	private boolean cancelConnection;// true取消所有关联 默认false或null 包含对象和list
	
	/**
	 * 在业务层控制
	 * 格式 cancelNotSelect="name,";cancelNotSelect="name,user,";无论多个还是一个都加逗号
	 */
	private String cancelNotSelect;//取消NotSelect 用逗号分割多个字段 在业务层控制
	
	
	private HashMap<String,String> connection;//重新指定内联外联 
	
	public Integer getBegin() {
		if(begin == null)
			this.begin = 0;
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		if(end == null)
			this.end = 999999;
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}

	public QueryInfo(Integer begin, Integer end,String meSelect) {
		super();
		this.begin = begin;
		this.end = end;
		this.meSelect = meSelect;
	}
	
	public QueryInfo(Integer begin, Integer end) {
		super();
		this.begin = begin;
		this.end = end;
	}
	
	public QueryInfo(HashMap<String, Object> where) {
		super();
		this.search = where;
	}
	
	public QueryInfo() {
		super();
	}
	
	public QueryInfo(HttpServletRequest request) {
		super();
		this.request = request;
	}
	public ArrayList<String> getCancel() {
		return cancel;
	}
	
	public void addCancel(String Str) {
		if(this.cancel==null)
			this.cancel = new ArrayList<String>();
		this.cancel.add(Str);
	}
	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String getSort() {
		//这里要不要验证下去掉 select update insert 等关键字出现返回xxxx
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getMeSelect() {
		return meSelect;
	}
	public void setMeSelect(String meSelect) {
		this.meSelect = meSelect;
	}
	
	public HashMap<String,String> getConnection() {
		return connection;
	}
	public void setConnection(HashMap<String,String> connection) {
		this.connection = connection;
	}
	
	public boolean isCancelConnection() {
		return cancelConnection;
	}
	/**
	 * true 取消所有关联查询
	 * @param cancelConnection
	 */
	public void setCancelConnection(boolean cancelConnection) {
		this.cancelConnection = cancelConnection;
	}
	
	public String getCancelNotSelect() {
		return cancelNotSelect;
	}
	public void setCancelNotSelect(String cancelNotSelect) {
		this.cancelNotSelect = cancelNotSelect;
	}

	public HashMap<String, Object> getSearch() {
		if(this.search == null)
			this.search = new HashMap<String, Object>();
		return search;
	}
	public void setSearch(HashMap<String, Object> search) {
		this.search = search;
	}
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	public ArrayList<Order> getOr() {
		return or;
	}
	public void setOr(ArrayList<Order> or) {
		this.or = or;
	}
	
	
//	public SellPermissions getPermissions(){
//		SellPermissions permissions = (SellPermissions) this.request.getSession().getAttribute("sellPermissions");
//		return permissions;
//	}
}
