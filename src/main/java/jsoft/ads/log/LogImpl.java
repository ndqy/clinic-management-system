package jsoft.ads.log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.ads.customer.CUSTOMER_SORT_TYPE;
import jsoft.library.Utilities;
import jsoft.objects.CustomerObject;
import jsoft.objects.LogObject;

public class LogImpl extends BasicImpl implements Log{

	public LogImpl(ConnectionPool cp) {
		super(cp, "Log");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addLog(LogObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tbllog (log_name, log_user_permission, ");
		sql.append("log_date, log_user_name, log_action, log_position )");
		sql.append("VALUES (?,?,?,?,?,?)");
		 
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getLog_name());
			pre.setShort(2, item.getLog_user_permission());
			pre.setString(3, item.getLog_date());
			pre.setString(4, item.getLog_user_name());
			pre.setShort(5, item.getLog_action());
			pre.setShort(6, item.getLog_position());
			System.out.println(sql);
			return this.add(pre);
		} catch (SQLException e) {
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ResultSet getLog(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbllog WHERE log_id = "+ id);
		return this.get(sql.toString(), id);
	}

	@Override
	public ResultSet getLogs() {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbllog ORDER BY log_id DESC LIMIT 0,15;");
		return this.gets(sql.toString());
	}
	
	@Override
	public ArrayList<ResultSet> getLogsForUser(Triplet<LogObject, Short, Byte> infors) {
		// TODO Auto-generated method stub
		byte total = infors.getValue2(); // số bản ghi trong 1 trang
		int  at = (infors.getValue1()-1)*total;
		LogObject item = infors.getValue0();
		
		StringBuilder sql = new StringBuilder("SELECT * FROM tbllog ");
		
		//Câu lệnh WHERE
		sql.append(createCondition(item));
		sql.append(" ORDER BY log_id DESC");
		sql.append(" LIMIT "+at+", "+total+"; ");

		sql.append("SELECT COUNT(log_id) as total FROM tbllog ");
		//Câu lệnh WHERE
		sql.append(createCondition(item));
		//System.out.println(sql.toString());
		return this.getReLists(sql.toString());
	}
	
	//Viết thêm điều kiện
	public static String createCondition(LogObject item) {
		StringBuilder cond = new StringBuilder();
		short permis = item.getLog_user_permission();
		cond.append("WHERE log_user_name ='"+item.getLog_user_name()+"' ");
		cond.append("OR log_user_permission < "+item.getLog_user_permission()+" ");
		

		return cond.toString();
	}

	@Override
	public boolean delLog(LogObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tbllog WHERE log_id = ? ";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getLog_id());

			return this.del(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		}
		return false;
	}
	
}
