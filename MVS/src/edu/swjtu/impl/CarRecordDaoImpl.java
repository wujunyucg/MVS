package edu.swjtu.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.dao.CarRecordDao;
import edu.swjtu.model.CarRecord;

public class CarRecordDaoImpl implements CarRecordDao {

	@Override
	public int addCarRecord(CarRecord carrecord, Connection con)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCarRecord(int carrecordId, Connection con)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCarRecord(CarRecord carrecord, Connection con)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CarRecord getCarRecordById(int carrecordId, Connection con)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList getAllCarRecord(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
