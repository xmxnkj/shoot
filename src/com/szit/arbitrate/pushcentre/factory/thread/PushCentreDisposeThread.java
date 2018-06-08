package com.szit.arbitrate.pushcentre.factory.thread;

import java.util.Map;

import com.szit.arbitrate.pushcentre.factory.PushExecuteReflectFactory;
import com.szit.arbitrate.pushcentre.factory.product.impl.BuildCertificateProduct;
import com.szit.arbitrate.pushcentre.factory.product.impl.BuildChatRoomProduct;
import com.szit.arbitrate.pushcentre.factory.product.impl.BuildMediateStateProduct;
import com.szit.arbitrate.pushcentre.factory.product.impl.BuildMediationProtocolProduct;
import com.szit.arbitrate.pushcentre.factory.product.impl.BuildNeteaseChatRoomProduct;
import com.szit.arbitrate.pushcentre.factory.product.impl.BuildNotifyToSignProtocolProduct;
import com.szit.arbitrate.pushcentre.factory.product.impl.BuildSendChatMessageProduct;
import com.szit.arbitrate.pushcentre.factory.product.impl.BuildSystemNotifyProduct;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * 
* @ClassName: PushCentreDisposeThread
* @author Administrator
* @date 2017年4月25日 下午4:01:09
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class PushCentreDisposeThread implements Runnable{

	
	private PushTypeEnum pushType;
	private Map<String, Object> params;
	private PushExecuteReflectFactory fac;

	
	public PushCentreDisposeThread(PushTypeEnum pushTypeEnum,Map<String, Object> paramsval){
		this.pushType = pushTypeEnum;
		this.params = paramsval;
		this.fac = new PushExecuteReflectFactory();
	}
	
	@Override
	public void run() {
	     switch (pushType) {
	        case NotifyToSignProtocol:
	        	fac.createProduct(BuildNotifyToSignProtocolProduct.class).buildPushDispose(params,PushTypeEnum.NotifyToSignProtocol);
	        	 break;
	        case OpenChatRoom:
	        	fac.createProduct(BuildChatRoomProduct.class).buildPushDispose(params,PushTypeEnum.OpenChatRoom);
	        	break;
	        case PauseChatRoom:
	        	fac.createProduct(BuildChatRoomProduct.class).buildPushDispose(params,PushTypeEnum.PauseChatRoom);
	            break;
	        case OpenNeteaseChat:
	        	fac.createProduct(BuildNeteaseChatRoomProduct.class).buildPushDispose(params,PushTypeEnum.OpenNeteaseChat);
	        	break;
	        case CloseNeteaseChat:
	        	fac.createProduct(BuildNeteaseChatRoomProduct.class).buildPushDispose(params,PushTypeEnum.CloseNeteaseChat);
	        	break;
	        case MediateProtocol:
	        	fac.createProduct(BuildMediationProtocolProduct.class).buildPushDispose(params,PushTypeEnum.MediateProtocol);
	        	break;
	        case System:
	        	fac.createProduct(BuildSystemNotifyProduct.class).buildPushDispose(params,PushTypeEnum.System);
	        	break;
	        case MediationApply://申请
	        	fac.createProduct(BuildSystemNotifyProduct.class).buildPushDispose(params,PushTypeEnum.MediationApply);
	        	break;
	        case RefuseMediation://拒绝
	        	fac.createProduct(BuildSystemNotifyProduct.class).buildPushDispose(params,PushTypeEnum.RefuseMediation);
	        	break;
	        case NotifyMediatorProtocol:
	        	fac.createProduct(BuildSystemNotifyProduct.class).buildPushDispose(params,PushTypeEnum.NotifyMediatorProtocol);
	        	break;
	        case Certificate://实名认证
	        	fac.createProduct(BuildCertificateProduct.class).buildPushDispose(params,PushTypeEnum.Certificate);
	        	break;
	        case MediateState://
	        	fac.createProduct(BuildMediateStateProduct.class).buildPushDispose(params,PushTypeEnum.MediateState);
	        	break;
	        case OneChat:
	        	fac.createProduct(BuildSendChatMessageProduct.class).buildPushDispose(params,PushTypeEnum.OneChat);
	        	break;
	        case RoomChat:
	        	fac.createProduct(BuildSendChatMessageProduct.class).buildPushDispose(params,PushTypeEnum.RoomChat);
	        	break;
	        case AdminChat:
	        	fac.createProduct(BuildSendChatMessageProduct.class).buildPushDispose(params,PushTypeEnum.AdminChat);
	        	break;
	        case RevocateMessage:
	        	fac.createProduct(BuildSendChatMessageProduct.class).buildPushDispose(params,PushTypeEnum.RevocateMessage);
	        	break;
	        default:
	            break;
	      }
	}

}
