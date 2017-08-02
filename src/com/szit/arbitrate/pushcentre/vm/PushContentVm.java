/**   
* @Title: PushChartContentVm.java
* @Package com.szit.cowell.pushcentre.vm
* @Description: TODO
* @author XUJC
* @date 2017年11月21日 下午4:28:32
* @version V1.0   
*/


package com.szit.arbitrate.pushcentre.vm;

/**
 * @ClassName: PushChartContentVm
 * @author XUJC
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */

public class PushContentVm {
    //推送者ID
	private String scid;
	//推送者名称
	private String scname;
	//推送者头像
	private String scheadimage;
	//推送类型
	private PushTypeEnum pushtype;
	//推送消息
	private Object message;
	
	
	public PushContentVm() {

	}

	public PushContentVm(String scid, String scname, String scheadimage,
			PushTypeEnum pushtype, Object message) {
		super();
		this.scid = scid;
		this.scname = scname;
		this.scheadimage = scheadimage;
		this.pushtype = pushtype;
		this.message = message;
	}
	
	public String getScid() {
		return scid;
	}
	public void setScid(String scid) {
		this.scid = scid;
	}
	public String getScname() {
		return scname;
	}
	public void setScname(String scname) {
		this.scname = scname;
	}
	public String getScheadimage() {
		return scheadimage;
	}
	public void setScheadimage(String scheadimage) {
		this.scheadimage = scheadimage;
	}
	public PushTypeEnum getPushtype() {
		return pushtype;
	}
	public void setPushtype(PushTypeEnum pushtype) {
		this.pushtype = pushtype;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	
}
