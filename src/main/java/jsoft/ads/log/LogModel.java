package jsoft.ads.log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.CustomerObject;
import jsoft.objects.LogObject;
import jsoft.objects.ServiceObject;

public class LogModel {
	private Log l;
	
	public LogModel(ConnectionPool cp) {
		this.l = new LogImpl(cp);
	}
	
	protected void finalize() throws Throwable {
		this.l = null;
		
	}
	
	public ConnectionPool getCP() {
		return this.l.getCP();
	}
	
	public void releaseConnection() {
		this.l.releaseConnection();
	}
	//============================================================
	public boolean addLog(LogObject item) {
		return this.l.addLog(item);
	}
	public boolean delLog(LogObject item) {
		// TODO Auto-generated method stub
		return this.l.delLog(item);
	}
	//============================================================
	public LogObject getLog(int id) {
		LogObject item = null;
		ResultSet rs = this.l.getLog(id);
		if(rs!=null) {
			try {
				if(rs.next()) {
					item = new LogObject();
					item.setLog_id(rs.getInt("log_id"));
					item.setLog_name(rs.getString("log_user_name"));
					item.setLog_user_permission(rs.getShort("log_user_permission"));
					item.setLog_date(rs.getString("log_date"));
					item.setLog_user_name(rs.getString("log_user_name"));
					item.setLog_action(rs.getShort("log_action"));
					item.setLog_position(rs.getShort("log_position"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	
	public ArrayList<LogObject> getLogs() {
		ArrayList<LogObject> items = new ArrayList<LogObject>();
		ResultSet rs = this.l.getLogs();
		if(rs!=null) {
			try {
				while(rs.next()) {
					LogObject item = new LogObject();
					item.setLog_id(rs.getInt("log_id"));
					item.setLog_name(rs.getString("log_name"));
					item.setLog_user_permission(rs.getShort("log_user_permission"));
					item.setLog_date(rs.getString("log_date"));
					item.setLog_user_name(rs.getString("log_user_name"));
					item.setLog_action(rs.getShort("log_action"));
					item.setLog_position(rs.getShort("log_position"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return items;
	}
	
	public Pair<ArrayList<LogObject>, Integer> getLogsForUser(Triplet<LogObject, Short, Byte> infors) {
		ArrayList<LogObject> items = new ArrayList<LogObject>();
		ArrayList<ResultSet> res = this.l.getLogsForUser(infors);
		ResultSet rs = res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
					LogObject item = new LogObject();
					item.setLog_id(rs.getInt("log_id"));
					item.setLog_name(rs.getString("log_name"));
					item.setLog_user_permission(rs.getShort("log_user_permission"));
					item.setLog_date(rs.getString("log_date"));
					item.setLog_user_name(rs.getString("log_user_name"));
					item.setLog_action(rs.getShort("log_action"));
					item.setLog_position(rs.getShort("log_position"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Lấy tổng số bản ghi
		int totalAll = 0;
		rs = res.get(1);
		if(rs!=null) {
			try {
				if(rs.next()) {
					totalAll = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Pair<>(items,totalAll);
	}

	
	
	
}
