package edu.swjtu.dao;

import java.sql.Connection;
import java.util.ArrayList;

import edu.swjtu.model.Car;

/**
 * 
 * CarDao.java类
 * 2016年7月9日
 * @author wujunyu
 * TODo
 */
public interface CarDao {
	/**
	 * 
	 * 2016年7月9日上午10:03:35
	 * @author wujunyu
	 * TODO
	 * @param car
	 * @param con
	 * @return
	 */
	public int addOneCar( Car car , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:24:55
	 * @author wujunyu
	 * TODO 添加一个列表的车
	 * @param carList
	 * @param con
	 * @return
	 */
	public int addListCar( ArrayList<Car> carList , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:25:17
	 * @author wujunyu
	 * TODO 删除一辆车
	 * @param car
	 * @param con
	 * @return
	 */
	public int deleteOneCar( Car car , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:25:45
	 * @author wujunyu
	 * TODO 删除一个列表的车
	 * @param carList
	 * @param con
	 * @return
	 */
	public int deleteListCar( ArrayList<Car> carList , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:26:01
	 * @author wujunyu
	 * TODO 更新一辆车
	 * @param car
	 * @param con
	 * @return
	 */
	public int updateCar( Car car , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:26:16
	 * @author wujunyu
	 * TODO 根据id查询一辆车
	 * @param carId
	 * @param con
	 * @return
	 */
	public Car getCarById( int carId , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:26:33
	 * @author wujunyu
	 * TODO 根据车牌号查询一辆车
	 * @param licensePlate
	 * @param con
	 * @return
	 */
	public Car getCarByLicensePlate( String licensePlate , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:26:59
	 * @author wujunyu
	 * TODO 根据行驶证查询一辆车
	 * @param license
	 * @param con
	 * @return
	 */
	public Car getCarByLicense( String license , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:27:24
	 * @author wujunyu
	 * TODO 根据驾驶证查询一辆车
	 * @param drivingLicense
	 * @param con
	 * @return
	 */
	public Car getCarByDrivingLicense( String drivingLicense , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:27:41
	 * @author wujunyu
	 * TODO 根据司机姓名查询一辆车
	 * @param driver
	 * @param con
	 * @return
	 */
	public Car getCarByDriver( String driver , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:28:01
	 * @author wujunyu
	 * TODO 根据班次id查询一辆车 
	 * @param arrangeId
	 * @param con
	 * @return
	 */
	public Car getCarByArrangeId( int arrangeId , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:29:16
	 * @author wujunyu
	 * TODO 根据汽车品牌得到汽车列表
	 * @param brand
	 * @param con
	 * @return
	 */
	public ArrayList<Car> getCarByBrand( String brand , Connection con );
	/**
	 * 
	 * 2016年7月9日上午10:29:47
	 * @author wujunyu
	 * TODO 根据id得到汽车座位数
	 * @param carId
	 * @param con
	 * @return
	 */
	public int getCarNumberByCarId( int carId , Connection con );
}
