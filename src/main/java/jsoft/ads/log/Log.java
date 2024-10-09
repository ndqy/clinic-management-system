package jsoft.ads.log;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ShareControl;
import jsoft.objects.LogObject;

public interface Log extends ShareControl{
	// Phương thức thêm lịch sử làm việc
	public boolean addLog(LogObject item);
	public boolean delLog(LogObject item);
	
	
	//Lấy thông tin theo id
	public ResultSet getLog(int id);
	
	public ResultSet getLogs();
	//public ArrayList<ResultSet> getLogs(Triplet<LogObject, Short, Byte> infors);

	ArrayList<ResultSet> getLogsForUser(Triplet<LogObject, Short, Byte> infors);

	

}
