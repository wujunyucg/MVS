package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.model.Arrange;
import edu.swjtu.model.Car;
import edu.swjtu.model.Line;
import edu.swjtu.model.Powers;
import edu.swjtu.util.DBUtil;

public class CreateArrServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBUtil db = new DBUtil();
		Connection con = null;
		HttpSession session = request.getSession();
		/* 0:初始进入，1:验证日期,2:提交,3:多个排班 */
		String type = request.getParameter("type");
		String multi = request.getParameter("multi");
		if (null == multi) {
			multi = "no";
		}
		if (null == type) {
			type = "0";
		}
		try {
			con = db.getCon();
			LineDaoImpl ldi = new LineDaoImpl();
			CarDaoImpl cdi = new CarDaoImpl();
			ArrangeDaoImpl adi = new ArrangeDaoImpl();

			if (type.equals("0")) {
				ArrayList<Line> lines = ldi.getAllLine(con);
				ArrayList<Car> cars = cdi.getAllCar(con);

				session.setAttribute("cre_arr_lines", lines);
				session.setAttribute("cre_arr_cars", cars);

				if (multi.equals("yes")) {
					request.getRequestDispatcher(
							"../jsp_user/create_arr_monthly.jsp").forward(
							request, response);
				} else {
					request.getRequestDispatcher(
							"../jsp_user/create_arrange.jsp").forward(request,
							response);
				}
			} else if (type.equals("1")) {
				PrintWriter pw = response.getWriter();
				String date = request.getParameter("date");
				ArrayList<Arrange> arrs = adi.getAllArrByDate(con, date);
				StringBuffer re = new StringBuffer("");
				for (int i = 0; i < arrs.size(); i++) {
					re.append(arrs.get(i).getTime() + ",");
				}
				re.append(arrs.size());
				pw.write(re.toString());
				pw.close();
			} else if (type.equals("2")) {
				PrintWriter pw = response.getWriter();
				String date = request.getParameter("date");
				String time = request.getParameter("time");
				String name = request.getParameter("name");
				int carId = Integer.parseInt(request.getParameter("car_id"));
				int lineId = Integer.parseInt(request.getParameter("line_id"));

				System.out.println("2:" + date + " " + time + " " + name + " "
						+ carId + " " + lineId);

				/* 首先将班次存好 */
				Arrange arr = new Arrange();
				arr.setCarId(carId);
				arr.setDate(date);
				arr.setLineId(lineId);
				arr.setName(name);
				arr.setTime(time);

				int re = adi.addArr(arr, con);
				if (re > 0) {
					/* 插入成功后,更新line和car */
					Arrange a = adi.getArrByDateAndTime(con, date, time);
					if (null != a) {
						int arr_id = a.getArrangeId();
						Car car = cdi.getCarById(carId, con);
						car.setArrangeId(car.getArrangeId() + "," + arr_id);
						int rc = cdi.updateCar(car, con);
						Line line = ldi.getLineById(con, lineId);
						line.setCarId(carId + "");
						int rl = ldi.updateLine(con, line);
						if (1 == rc && 1 == rl) {
							pw.write("yes");
						} else {
							pw.write("no");
						}
					}
				} else {
					pw.write("no");
				}
				pw.close();
			} else if (type.equals("3")) {
				PrintWriter pw = response.getWriter();
				String date = request.getParameter("date");
				String time = request.getParameter("times");
				String lineIds = request.getParameter("line_ids");
				String carIds = request.getParameter("car_ids");

				String[] lineArr = lineIds.split(",");
				String[] carArr = carIds.split(",");
				String[] ts = time.split(",");

				for(int t=1;t<=30;t++){
					for (int i = 1; i <= lineArr.length; i++) {
						Arrange arr = new Arrange();
						arr.setCarId(Integer.parseInt(carArr[i-1]));
						String nd = date+"-"+(t>9?t:("0"+t));
						//System.out.println(nd);
						arr.setDate(nd);/*构造日期*/
						arr.setLineId(Integer.parseInt(lineArr[i-1]));
						arr.setName("班次"+i);
						arr.setTime(ts[i-1]);
						adi.addArr(arr, con);
						
						/* 插入成功后,更新line和car */
						Arrange a = adi.getArrByDateAndTime(con, nd, time);
						if (null != a) {
							int arr_id = a.getArrangeId();
							Car car = cdi.getCarById(Integer.parseInt(carArr[i-1]), con);
							car.setArrangeId(car.getArrangeId() + "," + arr_id);
							int rc = cdi.updateCar(car, con);
							Line line = ldi.getLineById(con, Integer.parseInt(lineArr[i-1]));
							line.setCarId(carArr[i-1]);
							int rl = ldi.updateLine(con, line);
							if (1 == rc && 1 == rl) {
								pw.write("yes");
							} else {
								pw.write("no");
							}
						}
					}
				}
				pw.write("yes");
				pw.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != con) {
				try {
					db.closeCon(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
