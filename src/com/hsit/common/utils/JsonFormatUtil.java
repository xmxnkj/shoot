/**   
* @Title: JsonFormatUtil.java
* @Package com.hsit.utils
* @Description: TODO
* @author XUJC 
* @date 2017年8月11日 下午12:34:05
* @version V1.0   
*/


package com.hsit.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ProjectName:sizt-coupons
 * @ClassName: JsonFormatUtil
 * @Description:格式化输入工具类
 * @author XUJC
 * @date 2017年8月11日 下午12:34:05
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class JsonFormatUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonFormatUtil.class);
	
	/**
	 * 
	* @Title: printJson 
	* @Description: 打印输入到控制台
	* @param @param jsonStr
	* @return void 
	* @throws
	 */
	public static void printJson(String desc,String jsonStr){
		logger.debug(desc+":{}",formatJson(jsonStr));
	}
   
	public static  void printJson(String desc,Object obj){
		logger.debug(desc+":{}",formatJson(JsonMapper.getInstance().toJson(obj)));
	}

	/**
	 * 
	* @Title: formatJson 
	* @Description:格式化
	* @param @param jsonStr
	* @param @return
	* @return String 
	* @throws
	 */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    

    /**
     * 
    * @Title: addIndentBlank 
    * @Description: 添加space
    * @param @param sb
    * @param @param indent
    * @return void 
    * @throws
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
