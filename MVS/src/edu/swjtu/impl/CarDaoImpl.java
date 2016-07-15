package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.CarDao;
import edu.swjtu.model.Admin;
import edu.swjtu.model.Arrange;
import edu.swjtu.model.Car;
import edu.swjtu.model.User;

public class CarDaoImpl implements CarDao {

	private Car getCarOne(ResultSet rs) throws SQLException{
		Car car = new Car();
		car.setArrangeId(rs.getString("car_arrangeId"));
		car.setBrand(rs.getString("car_brand"));
		car.setCarId(rs.getInt("car_id"));
		car.setDriver(rs.getString("car_driver"));
		car.setDrivingLicense(rs.getString("car_drivingLicense"));
		car.setInsuranceDate( rs.getString("car_insuranceDate"));
		car.setLicense(rs.getString("car_license"));
		car.setLicensePlate(rs.getString("car_licensePlate"));
		car.setNumber(rs.getInt("car_number"));
		car.setRegistrationDate( rs.getString("car_registrationDate"));
		return car;
	}
	
	private void getPreSta(PreparedStatement ps, Car car) throws SQLException{
		
		ps.setInt(1,car.getCarId());
		ps.setString(2,car.getLicensePlate());
		ps.setString(3,car.getBrand());
		ps.setString(4, car.getRegistrationDate());
		ps.setString(5,car.getInsuranceDate());
		ps.setString(6,car.getDrivingLicense());
		ps.setString(7,car.getLicense());
		ps.setString(8,car.getArrangeId());
		ps.setString(9,car.getDriver());
		ps.setInt(10,car.getNumber());
		
	}
	
	@Override
	public int addOneCar(Car car, Connection con) {
		// TODO Auto-generated method stub
		String sql = "insert into car (car_id,car_licensePlate,car_brand,car_registrationDate,car_insuranceDate,car_drivingLicense,car_license,car_arrangeId,car_driver,car_number) values (? ,?, ?,?,?,?,?,?,?,?)";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			getPreSta(pstm,car);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int addListCar(ArrayList<Car> carList, Connection con) {
		// TODO Auto-generated method stub
		String sql = "insert into car (car_id,car_licensePlate,car_brand,car_registrationDate,car_insuranceDate,car_drivingLicense,car_license,car_arrangeId,car_driver,car_number) values (? ,?, ?,?,?,?,?,?,?,?)";
		int [] rs = null;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			for(Car car: carList){
				getPreSta(pstm,car);
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs[0];
	}

	@Override
	public int deleteOneCar(Car car, Connection con) {
		// TODO Auto-generated method stub
		String sql = "delete  from car where car_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, car.getCarId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public int deleteListCar(ArrayList<Car> carList, Connection con) {
		// TODO Auto-generated method stub
		String sql = "delete  from car where car_id = ?";
		int [] rs = null;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			for(Car car:carList){
				pstm.setInt(1, car.getCarId());
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs[0];
	}

	@Override
	public int updateCar(Car car, Connection con) {
		// TODO Auto-generated method stub
		String sql = "update car  set car_id = ?,car_licensePlate = ?,car_brand = ?,car_registrationDate= ?,car_insuranceDate= ?,car_drivingLicense = ?,car_license = ?,car_arrangeId = ?,car_driver = ?,car_number = ? where car_id = ?";
		int rs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			getPreSta(pstm, car);
			pstm.setInt(11, car.getCarId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	@Override
	public Car getCarById(int carId, Connection con) {
		// TODO Auto-generated method stub
		Car car = null;
		String sql = "select * from car where car_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, carId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				car = getCarOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return car;
	}

	@Override
	public Car getCarByLicensePlate(String licensePlate, Connection con) {
		// TODO Auto-generated method stub
		Car car = null;
		String sql = "select * from car where car_licensePlate=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, licensePlate);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				car = getCarOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return car;
	}

	@Override
	public Car getCarByLicense(String license, Connection con) {
		// TODO Auto-generated method stub
		Car car = null;
		String sql = "select * from car where car_license=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, license);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				car = getCarOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return car;
	}

	@Override
	public Car getCarByDrivingLicense(String drivingLicense, Connection con) {
		// TODO Auto-generated method stub
		Car car = null;
		String sql = "select * from car where car_drivingLicense=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, drivingLicense);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				car = getCarOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return car;
	}

	@Override
	public Car getCarByDriver(String driver, Connection con) {
		// TODO Auto-generated method stub
		Car car = null;
		String sql = "select * from car where car_driver=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, driver);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				car = getCarOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return car;
	}

	@Override
	public Car getCarByArrangeId(int arrangeId, Connection con) {
		// TODO Auto-generated method stub
		Car car = null;
		String sql = "select * from car where car_arrangeId like ?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, "%,"+arrangeId+",%");
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				car = getCarOne(rs);
			}
			else{
				pstm.setString(1,arrangeId+",%");
				rs = pstm.executeQuery();
				if(rs.next()){
					car = getCarOne(rs);
				}
					else{
						pstm.setString(1, "%,"+arrangeId);
						rs = pstm.executeQuery();
						if(rs.next()){
							car = getCarOne(rs);
						}
							else{
								pstm.setString(1, arrangeId+"");
								rs = pstm.executeQuery();
								if(rs.next()){
									car = getCarOne(rs);
							}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return car;
	}

	@Override
	public ArrayList<Car> getCarByBrand(String brand, Connection con) {
		// TODO Auto-generated method stub
		ArrayList<Car> carList = new ArrayList<Car>();
		String sql = "select * from car where car_brand=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, brand);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carList.add(getCarOne(rs)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carList;
	}

	@Override
	public int getCarNumberByCarId(int carId, Connection con) {
		// TODO Auto-generated method stub
		Car car = null;
		String sql = "select * from car where car_id=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, carId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				car = getCarOne(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return car.getNumber();
	}

	@Override
	public ArrayList<Car> getAllCar(Connection con) {
		ArrayList<Car> carList = new ArrayList<Car>(); 
		String sql = "select * from car";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carList.add(getCarOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carList;
	}

	@Override
	public ArrayList<Car> getPageCar(Connection con, int startPage,
			int pageNum) throws SQLException {
		String sql = "select *from car limit "+((startPage-1)*pageNum)+","+pageNum;
		ArrayList<Car> re = new ArrayList<Car>();
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()){
			re.add(getCarOne(rs));
		}
		return re;
	}

	@Override
	public int getTotal(Connection con) throws SQLException {
		String sql = "select count(*) from car";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();//移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public ArrayList<Car> getCarByLicensePlate_V(String licensePlate,
			Connection con) {
		ArrayList<Car> carList = new ArrayList<Car>(); 
		String sql="select * from car where car_licensePlate like '%"+licensePlate+"%'";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carList.add(getCarOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carList;
	}

	@Override
	public ArrayList<Car> getCarByLicense_V(String license, Connection con) {
		ArrayList<Car> carList = new ArrayList<Car>(); 
		String sql="select * from car where car_license like '%"+license+"%'";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carList.add(getCarOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carList;
	}

	@Override
	public ArrayList<Car> getCarByDrivingLicense_V(String drivingLicense,
			Connection con) {
		ArrayList<Car> carList = new ArrayList<Car>(); 
		String sql="select * from car where car_drivingLicense like '%"+drivingLicense+"%'";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carList.add(getCarOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carList;
	}

	@Override
	public ArrayList<Car> getCarByDriver_V(String driver, Connection con) {
		ArrayList<Car> carList = new ArrayList<Car>(); 
		String sql="select * from car where car_driver like '%"+driver+"%'";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carList.add(getCarOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carList;
	}

	@Override
	public ArrayList<Car> getCarByArrangeId_V(String arrangeId, Connection con) {
		ArrayList<Car> carList = new ArrayList<Car>(); 
		String sql="select * from car where car_arrangeId like '%"+arrangeId+"%'";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carList.add(getCarOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carList;
	}

	@Override
	public ArrayList<Car> getCarByBrand_V(String brand, Connection con) {
		ArrayList<Car> carList = new ArrayList<Car>(); 
		String sql="select * from car where car_brand like '%"+brand+"%'";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carList.add(getCarOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carList;
	}

	@Override
	public ArrayList<Car> getCarByNumber_V(String number, Connection con) {
		ArrayList<Car> carList = new ArrayList<Car>(); 
		String sql="select * from car where car_number like '%"+number+"%'";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				carList.add(getCarOne(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carList;
	}

	@Override
	public int getTotalByLicensePlate(Connection con, String licensePlate)
			throws SQLException {
		String sql = "select count(*) from car where car_licensePlate like '%"
				+ licensePlate + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();// 移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public ArrayList<Car> getPageLicensePlateCar(Connection con, int startPage,
			int pageNum, String licensePlate) throws SQLException {
		ArrayList<Car> list = new ArrayList<Car>();
		String sql = "select *from car where car_licensePlate like '%"
				+ licensePlate + "%' limit " + ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getCarOne(rs));
		}
		return list;
	}

	@Override
	public int getTotalByLicense(Connection con, String license)
			throws SQLException {
		String sql = "select count(*) from car where car_license like '%"
				+ license + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();// 移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public ArrayList<Car> getPageLicenseCar(Connection con, int startPage,
			int pageNum, String license) throws SQLException {
		ArrayList<Car> list = new ArrayList<Car>();
		String sql = "select *from car where car_license like '%"
				+ license + "%' limit " + ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getCarOne(rs));
		}
		return list;
	}

	@Override
	public int getTotalByDrivingLicense(Connection con, String drivingLicense)
			throws SQLException {
		String sql = "select count(*) from car where car_drivingLicense like '%"
				+ drivingLicense + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();// 移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public ArrayList<Car> getPageDrivingLicenseCar(Connection con,
			int startPage, int pageNum, String drivingLicense) throws SQLException {
		ArrayList<Car> list = new ArrayList<Car>();
		String sql = "select *from car where car_drivingLicense like '%"
				+ drivingLicense + "%' limit " + ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getCarOne(rs));
		}
		return list;
	}

	@Override
	public int getTotalByDriver(Connection con, String driver)
			throws SQLException {
		String sql = "select count(*) from car where car_driver like '%"
				+ driver + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();// 移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public ArrayList<Car> getPageDriverCar(Connection con, int startPage,
			int pageNum, String driver) throws SQLException {
		ArrayList<Car> list = new ArrayList<Car>();
		String sql = "select *from car where car_driver like '%"
				+ driver + "%' limit " + ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getCarOne(rs));
		}
		return list;
	}

	@Override
	public int getTotalByArrangeId(Connection con, String arrangeId)
			throws SQLException {
		String sql = "select count(*) from car where car_arrangeId like '%"
				+ arrangeId + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();// 移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public ArrayList<Car> getPageArrangeIdCar(Connection con, int startPage,
			int pageNum, String arrangeId) throws SQLException {
		ArrayList<Car> list = new ArrayList<Car>();
		String sql = "select *from car where car_arrangeId like '%"
				+ arrangeId + "%' limit " + ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getCarOne(rs));
		}
		return list;
	}

	@Override
	public int getTotalByBrand(Connection con, String brand)
			throws SQLException {
		String sql = "select count(*) from car where car_brand like '%"
				+ brand + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();// 移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public ArrayList<Car> getPageBrandCar(Connection con, int startPage,
			int pageNum, String brand) throws SQLException {
		ArrayList<Car> list = new ArrayList<Car>();
		String sql = "select *from car where car_brand like '%"
				+ brand + "%' limit " + ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getCarOne(rs));
		}
		return list;
	}

	@Override
	public int getTotalByNumber(Connection con, String number)
			throws SQLException {
		String sql = "select count(*) from car where car_number like '%"
				+ number + "%'";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		rs.next();// 移到第一条数据
		int sum = rs.getInt(1);
		return sum;
	}

	@Override
	public ArrayList<Car> getPageNumberCar(Connection con, int startPage,
			int pageNum, String number) throws SQLException {
		ArrayList<Car> list = new ArrayList<Car>();
		String sql = "select *from car where car_number like '%"
				+ number + "%' limit " + ((startPage - 1) * pageNum) + "," + pageNum;
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(getCarOne(rs));
		}
		return list;
	}
	
}
