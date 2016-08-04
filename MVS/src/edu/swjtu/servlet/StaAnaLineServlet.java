package edu.swjtu.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;

import net.sf.json.JSONObject;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.impl.LineRecordDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.intelligent.SearchRoute;
import edu.swjtu.model.Line;
import edu.swjtu.model.LineRecord;
import edu.swjtu.model.Lines;
import edu.swjtu.model.Site;
import edu.swjtu.model.SiteRecord;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

public class StaAnaLineServlet extends HttpServlet {
	DBUtil db = new DBUtil();


	public StaAnaLineServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
	
	@SuppressWarnings("deprecation")
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
					ArrayList<JSONObject> json_advice = new ArrayList<JSONObject>();
					
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					int j=0;
					if(linereclist.size() > 0){
						Queue<LineRecord> prioQue = new PriorityQueue<LineRecord>(linereclist.size(), new Lines().OrderIsdn1);
						for(int i=0;i<linereclist.size();i++){
							line = null;
							line = ldi.getLineById(con, linereclist.get(i).getLineId());
							if(line != null){
								linereclist.get(i).setRate(Double.valueOf(df.format(linereclist.get(i).getRate())).doubleValue());
								line.setRate(Double.valueOf(df.format(line.getRate())).doubleValue());
								prioQue.add(linereclist.get(i));
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
						Line line_name = null;;
						for(int i=0;i<linereclist.size()/6;i++){
							line_name = null;
							line_name = new LineDaoImpl().getLineById(con, prioQue.poll().getLineId());
							if(line_name != null){
								json_advice.add(new JSONObject());
								json_advice.get(i).put("name", line_name.getName());  
							}
						}

						String json_s  = json_linelist.toString() + "&" + json_linereclist.toString() + "&" + json_advice.toString();
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
					ArrayList<JSONObject> json_advice = new ArrayList<JSONObject>();
					
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					int j=0, lrId;
					double rate_sum = 0.0;
					double rate_num = 0.0;
					if(linereclist.size() > 0){
						Queue<LineRecord> prioQue = new PriorityQueue<LineRecord>(linereclist.size(), new Lines().OrderIsdn1);
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
								prioQue.add(linereclist.get(i));
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
						Line line_name = null;;
						for(int i=0;i<linereclist.size()/6;i++){
							line_name = null;
							line_name = new LineDaoImpl().getLineById(con, prioQue.poll().getLineId());
							json_advice.add(new JSONObject());
							json_advice.get(i).put("name", line_name.getName());  
						}

						String json_s  = json_linelist.toString() + "&" + json_linereclist.toString() + "&" + json_advice.toString();
						pw.write(json_s);
					}else{
						pw.write("no");
					}
				}else if(s_method.equals("3")){
					ArrayList<LineRecord> linereclist = null;
					String []s = s_date.split("-");
					String month_date = s[0] + "-" + s[1];
					linereclist = new LineRecordDaoImpl().getLineRecordByMonthDate(con, month_date);
					ArrayList<JSONObject> json_linereclist = new ArrayList<JSONObject>();
					ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();
					LineDaoImpl ldi = new LineDaoImpl();
					LineRecordDaoImpl lrdi = new LineRecordDaoImpl();
					Line line = null;
					ArrayList<JSONObject> json_advice = new ArrayList<JSONObject>();
					
					DecimalFormat df = new DecimalFormat( "0.00000 ");  
					int j=0, lrId;
					double rate_sum = 0.0;
					double rate_num = 0.0;
					if(linereclist.size() > 0){
						Queue<LineRecord> prioQue = new PriorityQueue<LineRecord>(linereclist.size(), new Lines().OrderIsdn1);
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
								prioQue.add(linereclist.get(i));
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
						Line line_name = null;;
						for(int i=0;i<linereclist.size()/6;i++){
							line_name = null;
							line_name = new LineDaoImpl().getLineById(con, prioQue.poll().getLineId());
							json_advice.add(new JSONObject());
							json_advice.get(i).put("name", line_name.getName());  
						}

						String json_s  = json_linelist.toString() + "&" + json_linereclist.toString() + "&" + json_advice.toString();
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
				ArrayList<LineRecord> linereclist = null;
				linereclist = new LineRecordDaoImpl().getAllLineRecord(con);
				ArrayList<Line> linelist = null;
				linelist = new LineDaoImpl().getAllLine(con);
				ArrayList<JSONObject> json_linereclist = new ArrayList<JSONObject>();
				ArrayList<JSONObject> json_linelist = new ArrayList<JSONObject>();
				LineDaoImpl ldi = new LineDaoImpl();
				LineRecordDaoImpl lrdi = new LineRecordDaoImpl();
				Line line = null;
				ArrayList<JSONObject> json_advice = new ArrayList<JSONObject>();
				ArrayList<JSONObject> json_advice1 = new ArrayList<JSONObject>();
				ArrayList<JSONObject> json_advice2 = new ArrayList<JSONObject>();
				
				DecimalFormat df = new DecimalFormat( "0.00000 ");  
				int j=0;
				double rate_num = 0.0, rate_sum = 0.0, sum1 = 0.0, sum2 = 0.0;;
				if(linereclist.size() > 0){
					Queue<LineRecord> prioQue1 = new PriorityQueue<LineRecord>(linereclist.size(), new Lines().OrderIsdn1);
					Queue<LineRecord> prioQue2 = new PriorityQueue<LineRecord>(linereclist.size(), new Lines().OrderIsdn2);
					Queue<LineRecord> prioQue3 = new PriorityQueue<LineRecord>(linereclist.size(), new Lines().OrderIsdn2);
					for(int i=0;i<linereclist.size();i++){
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
							linereclist.get(i).setDate(line.getName());
							linereclist.get(i).setRate(Double.valueOf(df.format(rate_sum / (rate_num+0.0)*1.0)).doubleValue());
							sum1 += linereclist.get(i).getRate();
							/*计算路线路程*/
							linereclist.get(i).setStaffIds(df.format(((new Lines().lineLength(linereclist.get(i).getLineId(),con))+0.0)/5000.0) + "");
							sum2 += Double.parseDouble(linereclist.get(i).getStaffIds());

							prioQue1.add(linereclist.get(i));
							prioQue2.add(linereclist.get(i));
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
					Line line_l = null;
					LineRecord lr = null;
					for(int i=0;i<linereclist.size()/5;i++){
						lr = prioQue1.poll();
						line_l = new LineDaoImpl().getLineById(con, lr.getLineId());
						line_l.setRate(lr.getRate());
						json_advice1.add(new JSONObject());
						json_advice1.get(i).put("linelist1", line_l);
						
						System.out.println(prioQue2.peek().getStaffIds()+"___");
						lr = prioQue2.poll();
						line_l = new LineDaoImpl().getLineById(con, lr.getLineId());
						line_l.setSiteId(lr.getStaffIds());
						json_advice2.add(new JSONObject());
						json_advice2.get(i).put("linelist2", line_l);
					}
					
					for(int i=0;i<linereclist.size();i++){
						linereclist.get(i).setStaffIds((linereclist.get(i).getRate()/sum1+Double.parseDouble(linereclist.get(i).getStaffIds())/sum2*(-1.0)) + "");
						prioQue3.add(linereclist.get(i));
					}
					int kk = 0;
					while(!prioQue3.isEmpty()){
						json_advice.add(new JSONObject());
						json_advice.get(kk).put("linereclist", prioQue3.poll());
						kk++;
					}

					String json_s  = json_linelist.toString() + "&" + json_linereclist.toString() + "&" + json_advice1.toString() + "&" + json_advice2.toString() + "&" + json_advice.toString();
					System.out.println("$"+json_s);
					pw.write(json_s);
				}else{
					pw.write("no");
				}
				
				
				
			}else if(type.equals("4")){
				
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
