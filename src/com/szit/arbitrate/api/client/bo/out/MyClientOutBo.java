/**   
* @Title: MyClientOutBo.java
* @Package com.szit.cowell.api.client.bo.out
* @Description: TODO
* @author XUJC
* @date 2017年10月29日 上午10:25:31
* @version V1.0   
*/


package com.szit.arbitrate.api.client.bo.out;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.utils.CustomDateSerializerYm;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;

/**
 * 
* @ClassName: MyClientOutBo
* @Description:我的会员信息输入业务对象
* @author Administrator
* @date 2017年3月20日 下午2:46:48
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class MyClientOutBo {
	//会员id
	private String id;
	//昵称
	private String nickName;
	//账号
	private String account;
	//头像路径
	private String headImgFile;
	//性别
	private Boolean gender;
	//生日
	private Date bornDate;
	//身高
	private Integer height;
	//体重
	private Integer weight;
	//会员类型
	private ClientTypeEnum clientType;
	//价格
	private Float price;
	//IP地址
	private String city;
	//地址
	private String address;
	
	//做为教练的星级
	private Integer stars;
	//作为用户的等级
	private Integer grade;
	//关注人数
	private Integer noticeCount;
	//做为教练的订购人数
	private Integer clientCount;
	
	//经验值
	private Integer experience;
	//健康币
	private Integer healthyMoney;
	
	//PK次数
	private Integer pkCount;
	//胜利次数
	private Integer victorCount;
	//失败次数
	private Integer loseCount;
	
	//列表图片
	private String bannerImgFile;
	
	//是否置顶
	private Boolean isTop;
	
	public Boolean getIsTop() {
		return isTop;
	}
	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}
	public String getBannerImgFile() {
		return bannerImgFile;
	}
	public void setBannerImgFile(String bannerImgFile) {
		this.bannerImgFile = bannerImgFile;
	}
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getNoticeCount() {
		return noticeCount;
	}
	public void setNoticeCount(Integer noticeCount) {
		this.noticeCount = noticeCount;
	}
	public Integer getClientCount() {
		return clientCount;
	}
	public void setClientCount(Integer clientCount) {
		this.clientCount = clientCount;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public Integer getHealthyMoney() {
		return healthyMoney;
	}
	public void setHealthyMoney(Integer healthyMoney) {
		this.healthyMoney = healthyMoney;
	}
	public Integer getPkCount() {
		return pkCount;
	}
	public void setPkCount(Integer pkCount) {
		this.pkCount = pkCount;
	}
	public Integer getVictorCount() {
		return victorCount;
	}
	public void setVictorCount(Integer victorCount) {
		this.victorCount = victorCount;
	}
	public Integer getLoseCount() {
		return loseCount;
	}
	public void setLoseCount(Integer loseCount) {
		this.loseCount = loseCount;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getHeadImgFile() {
		return headImgFile;
	}
	public void setHeadImgFile(String headImgFile) {
		this.headImgFile = headImgFile;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	@JsonSerialize(using = CustomDateSerializerYm.class)
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public ClientTypeEnum getClientType() {
		return clientType;
	}
	public void setClientType(ClientTypeEnum clientType) {
		this.clientType = clientType;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
