package com.szit.arbitrate.mediation.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.mediation.entity.enumvo.ClassicCaseTypeEnum;
import com.szit.arbitrate.mediation.entity.enumvo.DocTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: LegalDoc
* @Description:法律法规文档实体类
* @author yuyb
* @date 2017年3月23日 上午11:50:27
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class LegalDoc extends DomainEntity{

	private static final long serialVersionUID = 6305263038773284630L;
	
	private DocTypeEnum docType;//文档类型
	private ClassicCaseTypeEnum classicCase;//经典案例类型
	private String mediatorClientId;//员ID
	private String image;//配图
	private String title;//标题
	private String publishUnit;//发布单位
	private Date publishTime;//发布时间
	private String docDescription;//案例简介
	private String content;//文档内容
	private Integer commentCounts;//评论数
	private Integer likeCounts;//点赞数
	public boolean download=true;//是否可以下载
	public boolean display=false;//上架否 默认不显示
	private String filePath;	//附件路径
	private Client mediatorClient;
	private Integer orderdisplay;
	
	
	public String getDocDescription() {
		return docDescription;
	}
	public Integer getOrderdisplay() {
		return orderdisplay;
	}
	public void setOrderdisplay(Integer orderdisplay) {
		this.orderdisplay = orderdisplay;
	}
	public void setDocDescription(String docDescription) {
		this.docDescription = docDescription;
	}
	public Client getMediatorClient() {
		return mediatorClient;
	}
	public void setMediatorClient(Client mediatorClient) {
		this.mediatorClient = mediatorClient;
	}
	public String getMediatorClientId() {
		return mediatorClientId;
	}
	public void setMediatorClientId(String mediatorClientId) {
		this.mediatorClientId = mediatorClientId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public DocTypeEnum getDocType() {
		return docType;
	}
	public void setDocType(DocTypeEnum docType) {
		this.docType = docType;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublishUnit() {
		return publishUnit;
	}
	public void setPublishUnit(String publishUnit) {
		this.publishUnit = publishUnit;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ClassicCaseTypeEnum getClassicCase() {
		return classicCase;
	}
	public void setClassicCase(ClassicCaseTypeEnum classicCase) {
		this.classicCase = classicCase;
	}
	public Integer getCommentCounts() {
		return commentCounts;
	}
	public void setCommentCounts(Integer commentCounts) {
		this.commentCounts = commentCounts;
	}
	public Integer getLikeCounts() {
		return likeCounts;
	}
	public void setLikeCounts(Integer likeCounts) {
		this.likeCounts = likeCounts;
	}
	
}
