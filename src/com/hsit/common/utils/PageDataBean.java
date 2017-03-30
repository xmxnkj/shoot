package com.hsit.common.utils;

import java.util.List;

import com.hsit.common.kfbase.entity.Paging;

/**
 * @ProjectName:sizt-coupons
 * @ClassName: PageDataListBean
 * @Description:
 * @author XUJC
 * @date 2017年
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class PageDataBean<T> {
   
	public PageDataBean() {
	
	}
	
	public PageDataBean(long total,int pageCount,List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	public PageDataBean(long total,List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	public PageDataBean(PagingBean pagingBean, List<T> rows){
		super();
		this.total = pagingBean.getTotalItems();
		this.rows = rows;
	}
	
	public PageDataBean(Paging paging, List<T> rows){
		super();
		this.total = paging.getRecordCount();
		this.rows = rows;
	}
	
	private long total;
	private List<T> rows;

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

}
