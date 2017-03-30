/**   
* @Title: PagingBean.java
* @Package com.hsit.utils
* @Description: TODO
* @author XUJC 
* @date 2017年6月23日 上午11:40:58
* @version V1.0   
*/


package com.hsit.common.utils;

/**
 * @ProjectName:sizt-coupons
 * @ClassName: PagingBean
 * @Description:分页类
 * @author XUJC
 * @date 2017年
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class PagingBean {
	/**
	 * 默认分页每页25条
	 */
    public static final int DEFAULT_PAGE_SIZE = 10;
    
    /**
     *  页码大小
     */
    private int pageSize;
    
    /**
     * 当前页
     */
    private int pageIndex;
    
    /**
     * 总页数
     */
    private int totalPages;
    
    /**
     * 总记录数
     */
    private int totalItems;
	
    /**
     * 起始数
     */
    private int startIndex;
    
    /**
     * 结束数
     */
    private int lastIndex;
    
    /**
     * 是否分页
     */
    private boolean isPaging;

    /**
     * 构造函数
     * @param _pageIndex
     * @param _pageSize
     */
    public PagingBean(int _pageIndex, int _pageSize)
    {
        this.pageIndex = _pageIndex;
        this.pageSize = _pageSize;
    }

    /**
     * 
    * @Title: setTotalPages 
    * @Description:计算总页数
    * @param 
    * @return void 
    * @throws
     */
    public void setTotalPages()
    {
        if (totalItems % pageSize == 0)
        {
            this.totalPages = (int) (totalItems / pageSize);
        }
        else
        {
            this.totalPages = (int) ((totalItems / pageSize) + 1);
        }
    }   
    
    /**
     * 
    * @Title: setStartIndex 
    * @Description:计算开始时候的索引
    * @param 
    * @return void 
    * @throws
     */
    public void setStartIndex()
    {
        this.startIndex = (pageIndex - 1) * pageSize;
    }
    
    
    /**
     * 
    * @Title: setLastIndex 
    * @Description: 计算结束时候的索引
    * @param 
    * @return void 
    * @throws
     */
    public void setLastIndex()
    {
        if (totalItems < pageSize)
        {
            this.lastIndex = totalItems;
        }
        else if ((totalItems % pageSize == 0) || (totalItems % pageSize != 0 && pageIndex < totalPages))
        {
            this.lastIndex = pageIndex * pageSize;
        }
        else if (totalItems % pageSize != 0 && pageIndex == totalPages)
        {
            //最后一页
            this.lastIndex = totalItems;
        }
    }
    

    
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public boolean isPaging() {
		return isPaging;
	}

	public void setPaging(boolean isPaging) {
		this.isPaging = isPaging;
	}

	public static int getDefaultPageSize() {
		return DEFAULT_PAGE_SIZE;
	}
}
