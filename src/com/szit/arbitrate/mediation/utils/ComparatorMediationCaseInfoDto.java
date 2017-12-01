package com.szit.arbitrate.mediation.utils;

import java.util.Comparator;
import java.util.Date;

import com.hsit.common.utils.DateUtils;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseInfoDto;

public class ComparatorMediationCaseInfoDto implements Comparator<MediationCaseInfoDto>{

	@Override
	public int compare(MediationCaseInfoDto o1, MediationCaseInfoDto o2) {
		int result = 0;
		Date applyTime1 = o1.getApplyDate();
		String date1 = DateUtils.parseToString(applyTime1, DateUtils.DATE_YMDHMS_STR);
		Date applyTime2 = o2.getApplyDate();
		String date2 = DateUtils.parseToString(applyTime2, DateUtils.DATE_YMDHMS_STR);
		try {
			result = DateUtils.compareDate(date2, date1, DateUtils.DATE_YMDHMS_STR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
