package edu.swjtu.dao;

import java.sql.Connection;
import java.util.ArrayList;

import edu.swjtu.model.Staff;
import edu.swjtu.model.Synch;

public interface SynchDao {
	
	public int addSynch(Synch synch,Connection con);
	
	public ArrayList<Synch> getSynch(int startPage,int pageNum,Connection con);

	int getSynchNum(Connection con);

	Synch getLastSynch(Connection con);
}
