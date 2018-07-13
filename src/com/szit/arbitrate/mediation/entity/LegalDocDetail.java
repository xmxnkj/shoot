package com.szit.arbitrate.mediation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;

/**
 * 
* @ProjectName:
* @ClassName: LegalDocMoments
* @Description:法律法规文档评论实体类
* @author Administrator
* @date 2017年3月28日 下午3:35:30
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class LegalDocDetail extends DomainEntity{
	
	private static final long serialVersionUID = 6530229195140822916L;
	
	private String legalDocId;//法规ID 
	@JsonIgnore
	private LegalDoc legalDoc;//法规对象
	private String content;//内容
	private Integer seq;//内容排序
	
	public String getLegalDocId() {
		return legalDocId;
	}
	public void setLegalDocId(String legalDocId) {
		this.legalDocId = legalDocId;
	}
	public LegalDoc getLegalDoc() {
		return legalDoc;
	}
	public void setLegalDoc(LegalDoc legalDoc) {
		this.legalDoc = legalDoc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

}
