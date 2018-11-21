package com.szit.arbitrate.api.mediation.junit;

import org.junit.Test;

import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

public class MediationReourceJunitTest extends BaseApiJunitTest{
	
	@Test
	public void saveMediationReource(){
		String inbo = this.getApiRecordLogForInBo("wapApiMediationResourceController_624294");
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiMediationResourceController","saveMediationReource", inbo);
		this.executeApiTest(apiInVm);
	}

}
