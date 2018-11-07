/**   
* @Title: PageDataOutBoVm.java
* @Package com.szit.cowell.api.common.vm
* @Description: TODO
* @author XUJC
* @date 2017年10月30日 下午5:21:04
* @version V1.0   
*/


package com.szit.arbitrate.api.common.vm;

import java.util.List;

import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.PagingBean;

/**
 * 
* @ProjectName:
* @ClassName: PageDataOutBoVm
* @Description:分页数据输出业务对象
* @author Administrator
* @date 2017年3月20日 下午2:53:59
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
* @param <T>
 */

public class PageDataOutBoVm<T> {

	public PageDataOutBoVm(long total,int pageCount,List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
		this.pageCount = pageCount;
	}
	
	public PageDataOutBoVm(Paging paging, List<T> rows){
		super();
		this.total = paging.getRecordCount();
		this.rows = rows;
		this.pageCount = paging.getPageCount();
	}
	
	public PageDataOutBoVm(PagingBean pagingBean, List<T> rows){
		super();
		this.total = pagingBean.getTotalItems();
		this.rows = rows;
		this.pageCount = pagingBean.getTotalPages();
	}
	
	public PageDataOutBoVm(PagingBean pagingBean, List<T> rows,Object customdata){
		super();
		this.total = pagingBean.getTotalItems();
		this.rows = rows;
		this.pageCount = pagingBean.getTotalPages();
		this.customdata = customdata;
	}
	
	private long total;
	private List<T>  rows;
	private int pageCount;
	//自定义数据
	private Object customdata;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Object getCustomdata() {
		return customdata;
	}

	public void setCustomdata(Object customdata) {
		this.customdata = customdata;
	}

	
}
