/*

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.mediation.entity.DifferentSubjects;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseExcelDto;



public class ExportExcelUtil {
	
	*//**
	 * 所有案件分类
	 * @param ma
	 * @param pattern
	 * @param typeBranch
	 * @return
	 * @throws Exception
	 *//*
	public static HSSFWorkbook  exportExcel(HashMap<String,HashMap<String,Object>> ma, String pattern,List<String> typeBranch)throws Exception{  
		return exportExcel("海沧区矛盾纠纷排查调处情况表", ma, pattern,typeBranch);  
	}
	
	private static String getString(String sourcestring){
		StringBuilder sb = new StringBuilder();
		for (String s : sourcestring.split("")) {
			sb.append(s);
			sb.append("\r\n");
		}
		return sb.toString();
	}

	*//**
	 * 
	 * @param title
	 * @param ma
	 * @param pattern
	 * @param typeBranch 所有案件分类
	 * @return
	 * @throws Exception
	 *//*
	@SuppressWarnings({ "unchecked", "deprecation" })  
	public static HSSFWorkbook  exportExcel(String title,HashMap<String,HashMap<String,Object>> ma, String pattern,List<String> typeBranch)throws Exception 
	{  
		List<HashMap<String,Object>> datas = new ArrayList<HashMap<String,Object>>();
		
		for (Entry<String, HashMap<String, Object>> map : ma.entrySet()) {
			//初始化为空的街道
			map.getValue().put("jiedao", map.getKey());
			datas.add(map.getValue());
		}
		
		// 声明一个工作薄  
		HSSFWorkbook workbook = new HSSFWorkbook();  
		// 生成一个表格  
		HSSFSheet sheet = workbook.createSheet(title);
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 1000);
		sheet.setColumnWidth(2, 1000);
		sheet.setColumnWidth(3, 1000);
		sheet.setColumnWidth(4, 1000);
		sheet.setColumnWidth(5, 3000);
		
		// 生成一个样式 01
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		// 设置这些样式  
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		// 生成一个字体01  
		HSSFFont font = workbook.createFont(); 
		font.setFontName("宋体");
		font.setColor(HSSFColor.BLACK.index);  
		font.setFontHeightInPoints((short) 10);  
		font.setBold(true);
		// 把字体应用到当前的样式  
		style.setFont(font);  
		
		
		// 生成并设置另一个样式02  
		HSSFCellStyle style2 = workbook.createCellStyle();  
		// 设置这些样式  
		style2.setVerticalAlignment(VerticalAlignment.CENTER);
		style2.setAlignment(HorizontalAlignment.CENTER); 
		// 生成另一个字体 02
		HSSFFont font2 = workbook.createFont();  
		font2.setFontName("宋体");
		font2.setColor(HSSFColor.BLACK.index);  
		font2.setFontHeightInPoints((short) 16);  
		font2.setBold(true);
		// 把字体应用到当前的样式  
		style2.setFont(font2);  

		
		在sheet里创建第0行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
		HSSFRow row0=sheet.createRow(0);
		row0.setHeight((short)800);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
		HSSFCell cell00=row0.createCell(0);
		//设置单元格内容  
		cell00.setCellValue(pattern+"海沧区矛盾纠纷排查调处情况表"); 
		cell00.setCellStyle(style2);
		
		
		int rowIndex = 1;//行数计算
		//第1行
		HSSFRow row1=sheet.createRow(1);
		HSSFCell cell10=row1.createCell(0);
		cell10.setCellValue("项目");
		cell10.setCellStyle(style);
		HSSFCell cell11=row1.createCell(1);
		cell11.setCellValue("调 解 情 况");
		cell11.setCellStyle(style);
		
		rowIndex += 1;
		//第2行
		HSSFRow row2=sheet.createRow(2);
		row2.setHeight((short)1300);
		HSSFCell cell20=row2.createCell(0);
		cell20.setCellValue("1");
		rowIndex += 1;
		//第3行
		HSSFRow row3=sheet.createRow(3);
		row3.setHeight((short)5000);
		rowIndex += 1;
		//第4行
		HSSFRow row4=sheet.createRow(4);
		row4.setHeight((short)1000);
		rowIndex += 1;
		
		int columnIndex = 0;//列数计算
		HSSFCell cell30=row3.createCell(columnIndex);
		cell30.setCellValue("项目");
		cell30.setCellStyle(style);
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(1,3,0,0));
		HSSFCell cell40=row4.createCell(columnIndex);
		cell40.setCellValue("单位");
		cell40.setCellStyle(style);
		columnIndex += 1;
		
		HSSFCell cell21=row2.createCell(columnIndex);
		cell21.setCellValue("调\r\n解\r\n案\r\n件\r\n总\r\n数");
		cell21.setCellStyle(style);
		
		HSSFCell cell31=row3.createCell(columnIndex);
		cell31.setCellValue("");
		cell31.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,1,1));
		
		HSSFCell cell41=row4.createCell(columnIndex);
		cell41.setCellValue("件");
		cell41.setCellStyle(style);
		columnIndex += 1;
		
		HSSFCell cell12=row1.createCell(columnIndex);
		cell12.setCellValue("");
		cell12.setCellStyle(style);
		HSSFCell cell22=row2.createCell(columnIndex);
		cell22.setCellValue("涉\r\n及\r\n当\r\n事\r\n人\r\n数");
		cell22.setCellStyle(style);
		HSSFCell cell32=row3.createCell(columnIndex);
		cell32.setCellValue("");
		cell32.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,2,2));
		
		HSSFCell cell42=row4.createCell(columnIndex);
		cell42.setCellValue("人");
		cell42.setCellStyle(style);
		columnIndex += 1;
		
		HSSFCell cell13=row1.createCell(columnIndex);
		cell13.setCellValue("");
		cell13.setCellStyle(style);
		HSSFCell cell23=row2.createCell(columnIndex);
		cell23.setCellValue("调\r\n解\r\n成\r\n功");
		cell23.setCellStyle(style);
		HSSFCell cell33=row3.createCell(columnIndex);
		cell33.setCellValue("");
		cell33.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,3,3));
		
		HSSFCell cell43=row4.createCell(columnIndex);
		cell43.setCellValue("件");
		cell43.setCellStyle(style);
		columnIndex += 1;
		
		HSSFCell cell14=row1.createCell(columnIndex);
		cell14.setCellValue("");
		cell14.setCellStyle(style);
		HSSFCell cell24=row2.createCell(columnIndex);
		cell24.setCellValue("疑\r\n难\r\n复\r\n杂\r\n案\r\n件");
		cell24.setCellStyle(style);
		HSSFCell cell34=row3.createCell(columnIndex);
		cell34.setCellValue("疑\r\n难\r\n复\r\n杂\r\n案\r\n件");
		cell34.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,4,4));
				
		HSSFCell cell44=row4.createCell(columnIndex);
		cell44.setCellValue("件");
		cell44.setCellStyle(style);
		columnIndex += 1;
		
		HSSFCell cell15=row1.createCell(columnIndex);
		cell15.setCellValue("");
		cell15.setCellStyle(style);
		HSSFCell cell25=row2.createCell(columnIndex);
		cell25.setCellValue("协议\r\n涉及\r\n金额");
		cell25.setCellStyle(style);
		HSSFCell cell35=row3.createCell(columnIndex);
		cell35.setCellValue("协议\r\n涉及\r\n金额");
		cell35.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,5,5));
		
		HSSFCell cell45=row4.createCell(columnIndex);
		cell45.setCellValue("万元");
		cell45.setCellStyle(style);
		columnIndex += 1;
		
		if(datas != null && datas.size() > 0){
//			HashMap<String,Object> dto = datas.get(0);
			int index = 0;
			int index2 = 0;
			int index3 = 0;
			int index4 = 0;
			
			//1.循环不同主题数据 
			//列写死2列
			for(int i = 0; i < 2; i ++){
//				Map<String, Object> data = datas.get(i);
//				JSONObject jsonObject = JSONObject.fromObject(JsonMapper.getInstance().toJson(data));
//				String classfy = jsonObject.getString("classfy");
//				classfy = getString(classfy);
				
				HSSFCell cell1x=row1.createCell(columnIndex);
				cell1x.setCellValue("");
				cell1x.setCellStyle(style);
				HSSFCell cell2x=row2.createCell(columnIndex);
				cell2x.setCellValue("不同主体调解情况");
				cell2x.setCellStyle(style);
				
				HSSFCell cell3x=row3.createCell(columnIndex);
				cell3x.setCellValue(DifferentSubjects.values()[i].getName());
				cell3x.setCellStyle(style);
				HSSFCell cell4x=row4.createCell(columnIndex);
				cell4x.setCellValue("件");
				cell4x.setCellStyle(style);
				
				columnIndex += 1;
			}
			index = columnIndex;
			//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
			sheet.addMergedRegion(new CellRangeAddress(2,2,6,index-1));
			
			//2.循环案件来源数据   写死长度8
			int sourceNumList = 8;
//			List<Map<String, Object>> sourceNumList = dto.getSourceNumList();
			for(int i = 0; i < sourceNumList; i ++){
//				Map<String, Object> data = sourceNumList.get(i);
//				JSONObject jsonObject = JSONObject.fromObject(JsonMapper.getInstance().toJson(data));
//				String source = jsonObject.getString("source");
//				source = getString(source);
				HSSFCell cell2x=row2.createCell(columnIndex);
				cell2x.setCellValue("案件来源");
				cell2x.setCellStyle(style);
				
				HSSFCell cell3x=row3.createCell(columnIndex);
				if(i==0)
					cell3x.setCellValue("信访部门委托移送");
				if(i==1)
					cell3x.setCellValue("法院委托移送");
				if(i==2)
					cell3x.setCellValue("主动调解");
				if(i==3)
					cell3x.setCellValue("公安机关委托移送");
				if(i==4)
					cell3x.setCellValue("其他部门委托移送");
				if(i==5)
					cell3x.setCellValue("依申请调解");
				if(i==6)
					cell3x.setCellValue("检察院委托移送");
				if(i==7)
					cell3x.setCellValue("接受委托移送调解");
				cell3x.setCellStyle(style);
				HSSFCell cell4x=row4.createCell(columnIndex);
				cell4x.setCellValue("件");
				cell4x.setCellStyle(style);
				columnIndex += 1;
			}
			index2 = columnIndex;
			//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
			sheet.addMergedRegion(new CellRangeAddress(2,2,index2-sourceNumList,index2-1));
			
			//3.循环案件分类情况数据
//			List<Map<String, Object>> typeNumList = dto.getTypeNumList();
			for(int i = 0; i < typeBranch.size(); i ++){
				String data = typeBranch.get(i);
//				JSONObject jsonObject = JSONObject.fromObject(JsonMapper.getInstance().toJson(data));
//				String type = jsonObject.getString("type");
//				type = getString(type);
				HSSFCell cell2x=row2.createCell(columnIndex);
				cell2x.setCellValue("案件分类情况");
				cell2x.setCellStyle(style);
				
				HSSFCell cell3x=row3.createCell(columnIndex);
				cell3x.setCellValue(data);
				cell3x.setCellStyle(style);
				HSSFCell cell4x=row4.createCell(columnIndex);
				cell4x.setCellValue("件");
				cell4x.setCellStyle(style);
				
				columnIndex += 1;
			}
			index3 = columnIndex;
			
			//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
			sheet.addMergedRegion(new CellRangeAddress(2,2,index3-typeBranch.size(),index3-1));
			
			//4.循环协议形式数据 写死了
			int protocolNumList = 2;
//			List<Map<String, Object>> protocolNumList = dto.getProtocolNumList();
			for(int i = 0; i < protocolNumList; i ++){
				HSSFCell cell2x=row2.createCell(columnIndex);
				cell2x.setCellValue("协议形式");
				cell2x.setCellStyle(style);
				
				HSSFCell cell3x=row3.createCell(columnIndex);
				if(i==0)
					cell3x.setCellValue("口头协议");
				if(i==1)
					cell3x.setCellValue("书面协议");
				cell3x.setCellStyle(style);
				HSSFCell cell4x=row4.createCell(columnIndex);
				cell4x.setCellValue("件");
				cell4x.setCellStyle(style);
				
				
				columnIndex += 1;
			}
			index4 = columnIndex;
			//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
			sheet.addMergedRegion(new CellRangeAddress(1,1,1,index4-1));
			cell11.setCellStyle(style);
			//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
			sheet.addMergedRegion(new CellRangeAddress(2,2,index4-protocolNumList,index4-1));
			
		}
		
		HSSFCell cell17=row1.createCell(columnIndex);
		cell17.setCellValue("排 查 预 防 纠 纷");
		cell17.setCellStyle(style);
		HSSFCell cell27=row2.createCell(columnIndex);
		cell27.setCellValue("排\r\n查\r\n纠\r\n纷");
		cell27.setCellStyle(style);
		HSSFCell cell37=row3.createCell(columnIndex);
		cell37.setCellValue("排\r\n查\r\n纠\r\n纷");
		cell37.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,columnIndex,columnIndex));
		
		HSSFCell cell47=row4.createCell(columnIndex);
		cell47.setCellValue("次");
		cell47.setCellStyle(style);
		columnIndex += 1;
		
		HSSFCell cell28=row2.createCell(columnIndex);
		cell28.setCellValue("预\r\n防\r\n纠\r\n纷");
		cell28.setCellStyle(style);
		HSSFCell cell38=row3.createCell(columnIndex);
		cell38.setCellValue("预\r\n防\r\n纠\r\n纷");
		cell38.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,columnIndex,columnIndex));
		
		HSSFCell cell48=row4.createCell(columnIndex);
		cell48.setCellValue("件");
		cell48.setCellStyle(style);
		columnIndex += 1;
		
		HSSFCell cell29=row2.createCell(columnIndex);
		cell29.setCellValue("防止民\r\n间纠纷\r\n引起自杀");
		cell29.setCellStyle(style);
		HSSFCell cell39=row3.createCell(columnIndex);
		cell39.setCellValue("防止民\r\n间纠纷\r\n引起自杀");
		cell39.setCellStyle(style);
		HSSFCell cell49=row4.createCell(columnIndex);
		cell49.setCellValue("件");
		cell49.setCellStyle(style);
		 
		columnIndex += 1;
		
		HSSFCell cell210=row2.createCell(columnIndex);
		cell210.setCellValue("");
		cell210.setCellStyle(style);
		HSSFCell cell310=row3.createCell(columnIndex);
		cell310.setCellValue("");
		cell310.setCellStyle(style);
		HSSFCell cell410=row4.createCell(columnIndex);
		cell410.setCellValue("人");
		cell410.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,columnIndex-1,columnIndex));
		
		columnIndex += 1;
		
		HSSFCell cell211=row2.createCell(columnIndex);
		cell211.setCellValue("防止民\r\n间纠纷\r\n转化为\r\n刑事案件");
		cell211.setCellStyle(style);
		HSSFCell cell311=row3.createCell(columnIndex);
		cell311.setCellValue("防止民\r\n间纠纷\r\n转化为\r\n刑事案件");
		cell311.setCellStyle(style);
		HSSFCell cell411=row4.createCell(columnIndex);
		cell411.setCellValue("件");
		cell411.setCellStyle(style);
		columnIndex += 1;
		
		HSSFCell cell212=row2.createCell(columnIndex);
		cell212.setCellValue("");
		cell212.setCellStyle(style);
		HSSFCell cell312=row3.createCell(columnIndex);
		cell312.setCellValue("");
		cell312.setCellStyle(style);
		HSSFCell cell412=row4.createCell(columnIndex);
		cell412.setCellValue("人");
		cell412.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,columnIndex-1,columnIndex));
		
		columnIndex += 1;
		
		HSSFCell cell213=row2.createCell(columnIndex);
		cell213.setCellValue("防止\r\n群体性\r\n上访");
		cell213.setCellStyle(style);
		HSSFCell cell313=row3.createCell(columnIndex);
		cell313.setCellValue("防止\r\n群体性\r\n上访");
		cell313.setCellStyle(style);
		HSSFCell cell413=row4.createCell(columnIndex);
		cell413.setCellValue("件");
		cell413.setCellStyle(style);
		
		columnIndex += 1;
		
		HSSFCell cell214=row2.createCell(columnIndex);
		cell214.setCellValue("");
		cell214.setCellStyle(style);
		HSSFCell cell314=row3.createCell(columnIndex);
		cell314.setCellValue("");
		cell314.setCellStyle(style);
		HSSFCell cell414=row4.createCell(columnIndex);
		cell414.setCellValue("人");
		cell414.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,columnIndex-1,columnIndex));
		
		columnIndex += 1;
		
		HSSFCell cell215=row2.createCell(columnIndex);
		cell215.setCellValue("防止\r\n群体性\r\n械斗");
		cell215.setCellStyle(style);
		HSSFCell cell315=row3.createCell(columnIndex);
		cell315.setCellValue("防止\r\n群体性\r\n械斗");
		cell315.setCellStyle(style);
		HSSFCell cell415=row4.createCell(columnIndex);
		cell415.setCellValue("件");
		cell415.setCellStyle(style);
		
		columnIndex += 1;
		
		HSSFCell cell216=row2.createCell(columnIndex);
		cell216.setCellValue("");
		cell216.setCellStyle(style);
		HSSFCell cell316=row3.createCell(columnIndex);
		cell316.setCellValue("");
		cell316.setCellStyle(style);
		HSSFCell cell416=row4.createCell(columnIndex);
		cell416.setCellValue("人");
		cell416.setCellStyle(style);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(2,3,columnIndex-1,columnIndex));
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(1,1,columnIndex-9,columnIndex));
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,columnIndex));  
		
		for(int i = 0; i < columnIndex; i ++){
			sheet.setColumnWidth(i+5+1, 1000);
		}
		
		*//** 此处循环统计数据 *//*
		for(int i = 0; i < datas.size(); i ++){
			HashMap<String,Object> dto = datas.get(i);
			if(dto == null)
				dto = new HashMap<String,Object>();
			//第4+i+1行
			HSSFRow rowx=sheet.createRow(4+i+1);
			rowx.setHeight((short)500);
			
			int columnIndex2 = 0;
			//第1列  街道
			HSSFCell cellx0=rowx.createCell(columnIndex2);
			cellx0.setCellValue(getValue(dto.get("jiedao")));
			cellx0.setCellStyle(style);
			columnIndex2 += 1;
			//第2列 案件总数
			HSSFCell cellx1=rowx.createCell(columnIndex2);
			cellx1.setCellValue(getValue(dto.get("tiaojzs")));
			cellx1.setCellStyle(style);
			columnIndex2 += 1;
			//第3列  涉案人数
			HSSFCell cellx2=rowx.createCell(columnIndex2);
			cellx2.setCellValue(getValue(dto.get("ajsjrs")));
			cellx2.setCellStyle(style);
			columnIndex2 += 1;
			//第4列  调节成功
			HSSFCell cellx3=rowx.createCell(columnIndex2);
			cellx3.setCellValue(getValue(dto.get("cgla")));
			cellx3.setCellStyle(style);
			columnIndex2 += 1;
			//第5列 疑难复杂案件
			HSSFCell cellx4=rowx.createCell(columnIndex2);
			cellx4.setCellValue(getValue(dto.get("ynfzaj")));
			cellx4.setCellStyle(style);
			columnIndex2 += 1;
			//第6列 涉案金额
			HSSFCell cellx5=rowx.createCell(columnIndex2);
			cellx5.setCellValue(getValue(dto.get("ajsjje")));
			cellx5.setCellStyle(style);
			columnIndex2 += 1;
			
			//1.循环不同主题数据 写死2
//			List<Map<String, Object>> hostNumList = dto.getHostNumList();
			int hostNumList =2;
			for(int j = 0; j < DifferentSubjects.values().length; j ++){
//				Map<String, Object> data = hostNumList.get(j);
//				JSONObject jsonObject = JSONObject.fromObject(JsonMapper.getInstance().toJson(data));
//				Integer count = jsonObject.getInt("count");
				HSSFCell cellxx=rowx.createCell(columnIndex2);
				cellxx.setCellValue(getValue(dto.get(DifferentSubjects.values()[j].getKey())));
				cellxx.setCellStyle(style);
				columnIndex2 += 1;
			}
			
			//2.循环案件来源数据
			int sourceNumList = 8;
//			List<Map<String, Object>> sourceNumList = dto.getSourceNumList();
			for(int j = 0; j < sourceNumList; j ++){
//				Map<String, Object> data = sourceNumList.get(j);
//				JSONObject jsonObject = JSONObject.fromObject(JsonMapper.getInstance().toJson(data));
//				Integer count = jsonObject.getInt("count");
				HSSFCell cellxx=rowx.createCell(columnIndex2);
				if(j==0)//信访
					cellxx.setCellValue(getValue(dto.get("xinfan")));
				else if(j==1)//法院
					cellxx.setCellValue(getValue(dto.get("fayuan")));
				else if(j==2)//主动调解
					cellxx.setCellValue(getValue(dto.get("zhudong")));
				else if(j==3)//公安
					cellxx.setCellValue(getValue(dto.get("gongan")));
				else if(j==4)//其他
					cellxx.setCellValue(getValue(dto.get("qita")));
				else if(j==5)//依申请调解
					cellxx.setCellValue(getValue(dto.get("yishen")));
				else if(j==6)//检察院
					cellxx.setCellValue(getValue(dto.get("jiancha")));
				else if(j==7)//接受委托
					cellxx.setCellValue(getValue(dto.get("jiesou")));	
				cellxx.setCellStyle(style);
				columnIndex2 += 1;
			}
			
			//3.循环案件分类情况数据
//			List<Map<String, Object>> typeNumList = dto.getTypeNumList();
			for(int j = 0; j < typeBranch.size(); j ++){
//				Map<String, Object> data = typeNumList.get(j);
//				JSONObject jsonObject = JSONObject.fromObject(JsonMapper.getInstance().toJson(data));
//				Integer count = jsonObject.getInt("count");
				HSSFCell cellxx=rowx.createCell(columnIndex2);
				if(dto.get(typeBranch.get(j))!=null )
					cellxx.setCellValue(getValue(dto.get(typeBranch.get(j).toString()).toString()));
				else
					cellxx.setCellValue(getValue(null));
				cellxx.setCellStyle(style);
				
				columnIndex2 += 1;
			}
			
			//4.循环协议形式数据 写死
			int  protocolNumList=2;
//			List<Map<String, Object>> protocolNumList = dto.getProtocolNumList();
			for(int j = 0; j < protocolNumList; j ++){
				HSSFCell cellxx=rowx.createCell(columnIndex2);
				if(j==0)
					cellxx.setCellValue(getValue(dto.get("ktxy")));
				else if(j==1)
					cellxx.setCellValue(getValue(dto.get("smxy")));
				cellxx.setCellStyle(style);
				
				columnIndex2 += 1;
			}
			
			rowIndex += 1;
		}

		//第rowIndex行
		HSSFRow row6=sheet.createRow(rowIndex);
		HSSFCell cell60=row6.createCell(0);
		cell60.setCellValue("下级上报总计");
		cell60.setCellStyle(style);
		
		rowIndex += 1;
		
		//第rowIndex行
		HSSFRow row7=sheet.createRow(rowIndex);
		HSSFCell cell70=row7.createCell(0);
		cell70.setCellValue("本级上报总计");
		cell70.setCellStyle(style);

		return workbook;
	} 
	
	*//**
	 * 获取string 值
	 * @param str
	 * @return
	 *//*
	public static String getValue(Object str){
		if(str == null)
			return String.valueOf(0);
		else
			return str.toString();
	}
}
*/