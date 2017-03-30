package com.hsit.common.utils;

/**
 * 
* @ProjectName:sizt-coupons
* @ClassName: Lonlat
* @author XUJC
* @date 2017年10月12日 上午10:35:14
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司.
* @versions:1.0
 */
public class Lonlat {
	private static long EARTH_RADIUS = 6378137;
	
	/**
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double calcDistance(float lng1, float lat1, float lng2, float lat2){
		float radLat1 = rad(lat1);
		float radLat2 = rad(lat2);
		float a = radLat1 - radLat2;
		float b = rad(lng1) - rad(lng2);
		
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
				 Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s/1000;
	}

	private static float rad(float d){
		return (float) (d*Math.PI/180.0);
	}
}
