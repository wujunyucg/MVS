package edu.swjtu.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import edu.swjtu.model.Line;
import edu.swjtu.model.LineRecord;
import edu.swjtu.model.Lines;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

public class StatisticLineServlet extends HttpServlet {
	DBUtil db = new DBUtil();

	public StatisticLineServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");  
		Connection con = null;
		PrintWriter pw = response.getWriter();
		
		try {
			con = db.getCon();
			String type = request.getParameter("type");
			
			if(type.equals("1")){
				String s_method = request.getParameter("s_method");
				String s_date = request.getParameter("s_date");
				/*删除非法路线*/
				ArrayList<Line> linesl = null;
				linesl = new LineDaoImpl().getAllLine(con);
				new Lines().deleteIllegalLine(linesl,con);
				if(s_method.equals("1")){
					
					ArrayList<LineRecord> linereclist = null;
					linereclist = new LineRecordDaoImpl().getLineRecordByDayDate(con, s_date);
					ArrayList<JSONObject> json_linereclist = new ArrayList<JSONObject>();
					ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();
					LineDaoImpl ldi = new LineDaoImpl();
					LineRecordDaoImpl lrdi = new LineRecordDaoImpl();
					Line line = null;
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					int j=0;
					if(linereclist.size() > 0){
						for(int i=0;i<linereclist.size();i++){
							line = null;
							line = ldi.getLineById(con, linereclist.get(i).getLineId());
							if(line != null){
								linereclist.get(i).setRate(Double.valueOf(df.format(linereclist.get(i).getRate())).doubleValue());
								line.setRate(Double.valueOf(df.format(line.getRate())).doubleValue());
								/*所经站点名称*/
								String temp1 = new String();
								temp1 = new Lines().getSitesNameByIds(line.getSiteId(), con);
								line.setSiteId(temp1);
								/*数据存放至json*/
								json_linelist.add(new JSONObject());
								json_linelist.get(j).put("linelist", line);  
								json_linereclist.add(new JSONObject());
								json_linereclist.get(j).put("linereclist", linereclist.get(i)); 
								j++;
							}else{
								lrdi.deleteLineRecord(linereclist.get(i).getLinerecordId(), con);
							}
						}
						String json_s  = json_linelist.toString() + "&" + json_linereclist.toString();
						System.out.println("$"+json_s);
						pw.write(json_s);
					}else{
						pw.write("no");
					}
				}else if(s_method.equals("2")){
					ArrayList<LineRecord> linereclist = null;
					linereclist = new LineRecordDaoImpl().getLineRecordByWeekDate(con, s_date);
					ArrayList<JSONObject> json_linereclist = new ArrayList<JSONObject>();
					ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();
					LineDaoImpl ldi = new LineDaoImpl();
					LineRecordDaoImpl lrdi = new LineRecordDaoImpl();
					Line line = null;
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					int j=0, lrId;
					double rate_sum = 0.0;
					double rate_num = 0.0;
					if(linereclist.size() > 0){
						for(int i=0;i<linereclist.size();i++){
							lrId = linereclist.get(i).getLineId();
							line = null;
							line = ldi.getLineById(con, linereclist.get(i).getLineId());
							rate_sum = linereclist.get(i).getRate();
							rate_num = 1.0;
							if(line != null){
								for(int k=i+1;k<linereclist.size();k++){
									if(linereclist.get(k)!=null&&linereclist.get(i).getLineId() == linereclist.get(k).getLineId()){
										rate_sum += linereclist.get(k).getRate();
										rate_num += 1.0;
										linereclist.remove(k);
									}
								}
								linereclist.get(i).setRate(Double.valueOf(df.format(rate_sum / (rate_num+0.0)*1.0)).doubleValue());
								/*所经站点名称*/
								String temp1 = new String();
								temp1 = new Lines().getSitesNameByIds(line.getSiteId(), con);
								line.setSiteId(temp1);
								/*数据存放至json*/
								json_linelist.add(new JSONObject());
								json_linelist.get(j).put("linelist", line);  
								json_linereclist.add(new JSONObject());
								json_linereclist.get(j).put("linereclist", linereclist.get(i)); 
								j++;
							}else{
								lrdi.deleteLineRecord(linereclist.get(i).getLinerecordId(), con);
							}
						}
						String json_s  = json_linelist.toString() + "&" + json_linereclist.toString();
						System.out.println("$"+json_s);
						pw.write(json_s);
					}else{
						pw.write("no");
					}
				}else if(s_method.equals("3")){
					ArrayList<LineRecord> linereclist = null;
					linereclist = new LineRecordDaoImpl().getLineRecordByMonthDate(con, s_date);
					ArrayList<JSONObject> json_linereclist = new ArrayList<JSONObject>();
					ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();
					LineDaoImpl ldi = new LineDaoImpl();
					LineRecordDaoImpl lrdi = new LineRecordDaoImpl();
					Line line = null;
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					int j=0, lrId;
					double rate_sum = 0.0;
					double rate_num = 0.0;
					if(linereclist.size() > 0){
						for(int i=0;i<linereclist.size();i++){
							lrId = linereclist.get(i).getLineId();
							line = null;
							line = ldi.getLineById(con, linereclist.get(i).getLineId());
							rate_sum = linereclist.get(i).getRate();
							rate_num = 1.0;
							if(line != null){
								for(int k=i+1;k<linereclist.size();k++){
									if(linereclist.get(k)!=null&&linereclist.get(i).getLineId() == linereclist.get(k).getLineId()){
										rate_sum += linereclist.get(k).getRate();
										rate_num += 1.0;
										linereclist.remove(k);
									}
								}
								linereclist.get(i).setRate(Double.valueOf(df.format(rate_sum / (rate_num+0.0)*1.0)).doubleValue());
								/*所经站点名称*/
								String temp1 = new String();
								temp1 = new Lines().getSitesNameByIds(line.getSiteId(), con);
								line.setSiteId(temp1);
								/*数据存放至json*/
								json_linelist.add(new JSONObject());
								json_linelist.get(j).put("linelist", line);  
								json_linereclist.add(new JSONObject());
								json_linereclist.get(j).put("linereclist", linereclist.get(i)); 
								j++;
							}else{
								lrdi.deleteLineRecord(linereclist.get(i).getLinerecordId(), con);
							}
						}
						String json_s  = json_linelist.toString() + "&" + json_linereclist.toString();
						System.out.println("$"+json_s);
						pw.write(json_s);
					}else{
						pw.write("no");
					}
				}
				
				
			}else if(type.equals("2")){
				String linerecId = request.getParameter("linerecId");
				LineRecord lr = null;
				lr = new LineRecordDaoImpl().getLineRecordById(Integer.valueOf(linerecId).intValue(), con);
				String[] staff_ids = lr.getStaffIds().split(",");
				System.out.println("---------------"+lr.getStaffIds());
				ArrayList<JSONObject> json_stafflist = new ArrayList<JSONObject>();
				Staff staff = null;
				int j = 0;
				if(staff_ids.length > 0){
					for(int i=0;i<staff_ids.length;i++){
						staff = null;
						staff = new StaffDaoImpl().getStaffByStaffId(Integer.valueOf(staff_ids[i]).intValue(), con);
						if(staff != null){
							json_stafflist.add(new JSONObject());
							json_stafflist.get(j).put("stafflist", staff);  
							j++;
						}
					}
					String json_s  = json_stafflist.toString();
					System.out.println("#"+json_s);
					pw.write(json_s);
				}else{
					pw.write("no");
				}
				
				
			}else if(type.equals("3")){
				
			}else{
				
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				if(null!=con){
					try {
						db.closeCon(con);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
