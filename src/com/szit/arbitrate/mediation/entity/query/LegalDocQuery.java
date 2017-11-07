package com.szit.arbitrate.mediation.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.mediation.entity.enumvo.ClassicCaseTypeEnum;
import com.szit.arbitrate.mediation.entity.enumvo.DocTypeEnum;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: LegalDocQuery
* @Description:法律法规文档查询类
* @author Administrator
* @date 2017年3月23日 下午1:44:42
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class LegalDocQuery extends EntityQueryParam{
	
	private DocTypeEnum docType;//文档类型
	private ClassicCaseTypeEnum classicCase;//经典案例类型
	private String mediatorClientId;//调解员ID
	private String mediatorClientName;//调解员姓名
	private String title;//标题
	private String publishUnit;//发布单位
	private Date publishTime;//发布时间
	public boolean download=false;//是否可以下载
	public boolean display=false;//上架否 默认不显示
	public boolean classic=true;//经典案例
	private Integer orderdisplay;
	
	public String getMediatorClientId() {
		return mediatorClientId;
	}
	public void setMediatorClientId(String mediatorClientId) {
		this.mediatorClientId = mediatorClientId;
	}
	public ClassicCaseTypeEnum getClassicCase() {
		return classicCase;
	}
	public void setClassicCase(ClassicCaseTypeEnum classicCase) {
		this.classicCase = classicCase;
	}
	public String getMediatorClientName() {
		return mediatorClientName;
	}
	public void setMediatorClientName(String mediatorClientName) {
		this.mediatorClientName = mediatorClientName;
	}
	public DocTypeEnum getDocType() {
		return docType;
	}
	public void setDocType(DocTypeEnum docType) {
		this.docType = docType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getOrderdisplay() {
		return orderdisplay;
	}
	public void setOrderdisplay(Integer orderdisplay) {
		this.orderdisplay = orderdisplay;
	}
	public String getPublishUnit() {
		return publishUnit;
	}
	public void setPublishUnit(String publishUnit) {
		this.publishUnit = publishUnit;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public boolean isDownload() {
		return download;
	}
	public void setDownload(boolean download) {
		this.download = download;
	}
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	public boolean isClassic() {
		return classic;
	}
	public void setClassic(boolean classic) {
		this.classic = classic;
	}
	

}
