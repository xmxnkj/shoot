package com.szit.comment.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.ss.formula.functions.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.kfbase.entity.FileType;
import com.hsit.common.kfbase.service.ObjectFileService;
import com.hsit.common.utils.net.HttpUtilityInstance;
import com.szit.comment.entity.Report;
import com.szit.comment.entity.query.ReportQuery;
import com.szit.comment.service.ReportService;

@Controller("importDataAction")
@Scope("prototype")
public class ImportData extends BaseAction<Report, ReportQuery>{
	
	@Autowired
	private ReportService service;
	
	@Override
	public ReportService getService() {
		return service;
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ObjectFileService objectFileService;
	@Autowired
	private ReportService reportService;
	public void importData(){
		int count = getCount();
		int pageSize = 100;
		int pageCount = (int)Math.ceil((count*1.0)/pageSize);
		//pageCount=1;
		for(int i=0; i<pageCount; i++){
			int start = i*pageSize;
			String sql = "SELECT * FROM pre_forum_thread WHERE fid=51 ORDER BY dateline DESC LIMIT " + start + "," + pageSize;
			List<Report> reports = jdbcTemplate.query(sql, new RowMapper<Report>(){

				@Override
				public Report mapRow(ResultSet row, int arg1) throws SQLException {
					Report report = new Report();
					report.setReportUserName(row.getString("author"));
					report.setReportUserId(String.valueOf(row.getInt("authorid")));
					long second = row.getLong("dateline");
					Date dt = new Date(second*1000);
					report.setReportTime(dt);
					//report.setReportTime(row.getDate("dateline"));
					//System.out.println("dateline:" + row.getInt("dateline"));
					//Calendar cal = Calendar.getInstance();
					//cal.set(2017, 04, 19, 0, 0, 0);
					//System.out.println("time:" + cal.getTime().getTime());
					//System.out.println("dateline getDate:" + row.getDate("dateline"));
					report.setId(String.valueOf(row.getInt("tid")));
					return report;
				}
				
			});
			
			
			for(int j=0; j<reports.size(); j++){
				sql = "SELECT message FROM pre_forum_post WHERE fid=51 AND tid=" + reports.get(j).getId();
				
				List<Report> messageReports = jdbcTemplate.query(sql, new RowMapper<Report>(){

					@Override
					public Report mapRow(ResultSet row, int arg1) throws SQLException {
						Report report = new Report();
						report.setContent(row.getString("message"));
						return report;
					}
					
				});
				Report report = null;
				if (messageReports!=null && messageReports.size()>0) {
					report = messageReports.get(0);
				}
				
				if (report!=null) {
					reports.get(j).setContent(report.getContent());
				}
				
				sql = "SELECT tableid FROM pre_forum_attachment WHERE tid=" + reports.get(j).getId();
//				Integer tableId = jdbcTemplate.queryForObject(sql, new RowMapper<Integer>(){
//					@Override
//					public Integer mapRow(ResultSet row, int arg1) throws SQLException {
//						return row.getInt("tableid");
//					}
//				});
				
				List<Integer> tableIds = jdbcTemplate.query(sql, new RowMapper<Integer>(){
					@Override
					public Integer mapRow(ResultSet row, int arg1) throws SQLException {
						return row.getInt("tableid");
					}
					
				});
				
				List<File> imageFiles = new ArrayList<>();
				List<String> fileNames = new ArrayList<>();
				List<FileType> fileTypes = new ArrayList<>();
				
				if(tableIds!=null && tableIds.size()>0){
					int tableId = tableIds.get(0);
					sql = "SELECT * FROM pre_forum_attachment_" + tableId + " WHERE isimage=1 AND tid=" + reports.get(j).getId();
					List<TmpFile> files = jdbcTemplate.query(sql, new RowMapper<TmpFile>(){
						@Override
						public TmpFile mapRow(ResultSet row, int arg1) throws SQLException {
							
							TmpFile file = new TmpFile();
							file.setFileName(row.getString("filename"));
							file.setPath(row.getString("attachment"));
	
							return file;
						}
					});
					
					for(int k=0; k<files.size(); k++){
						String urlStr = "http://yao.cutv.com/data/attachment/forum/" + files.get(k).getPath();
						InputStream stream = HttpUtilityInstance.getUtility().getInputStreamFromURL(urlStr);
						String path = "/data/web/baoliao/webapps/comment/uploads/tmp/" + UUID.randomUUID().toString();
						try {
							FileOutputStream out = new FileOutputStream(path);
							byte[] buffer = new byte[1024];
							int c=0;
							while ((c=stream.read(buffer))!=-1) {
								out.write(buffer, 0, c);
							}
							out.close();
							stream.close();
							imageFiles.add(new File(path));
							fileNames.add(files.get(k).getFileName());
							fileTypes.add(FileType.Image);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				try {
					reports.get(j).setUserImgUrl(HttpUtilityInstance.getUtility().doGet("http://yao.cutv.com/plugin.php?id=cutv_shake:api_get_user_avatar&uid=" + report.getReportUserId()));
				} catch (ClientProtocolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				reports.get(j).setId(null);
				
				try {
					reports.get(j).setIsPublic(true);
					reportService.save(reports.get(j), imageFiles, fileNames, fileTypes);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		outSuccessJson();
	}
	
	private class TmpFile{
		private String path;
		private String fileName;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	}
	
	private int getCount(){
		String sql = "SELECT COUNT(*) FROM pre_forum_thread WHERE fid=51";
		
		return jdbcTemplate.queryForInt(sql);
		
	}
	
}

