package jsoft.ads.appt;
import java.sql.*;
import java.util.*;

import jsoft.*;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.ApptObject;

public class ApptImpl extends BasicImpl implements Appt {

	public ApptImpl(ConnectionPool cp) {
		super(cp, "Appt");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean addAppt(ApptObject item) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO tblappt(";
		sql += "appt_fullname, appt_note, appt_date, appt_phone, appt_gender";
		sql += ") ";
		sql += "VALUES(?, ?, ?, ?, ?)";
		
		// Biên dịch (phải có kết nối)
				try {
					PreparedStatement pre = this.con.prepareStatement(sql);
					pre.setString(1, item.getAppt_fullname());
					pre.setString(2, item.getAppt_note());
					pre.setString(3, item.getAppt_date());
					pre.setString(4, item.getAppt_phone());
					pre.setBoolean(5, item.isAppt_gender());
					return this.add(pre);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					try {
						this.con.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		
		return false;
	}
	
	
	@Override
	public boolean delAppt(ApptObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tblappt WHERE appt_id = ?;";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getAppt_id());
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

	@Override
	public boolean delListAppt(ArrayList<Integer> list) {
		StringBuilder appt = new StringBuilder();
		for(Integer item:list) {
			appt.append("?,");
		}
		appt.deleteCharAt(appt.length()-1);
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblappt WHERE appt_id IN ( ").append(appt).append(" );");
		System.out.println(sql.toString());
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			for(int i = 0; i<list.size(); i++) {
				pre.setInt(i+1, list.get(i));
			}
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
	@Override
	public ResultSet getAppt(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblappt WHERE appt_id = ?;";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getAppts(ApptObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblappt ");
		sql.append(" LIMIT "+at+", "+total+"; ");
		
		StringBuilder multiSelect = new StringBuilder();
		multiSelect.append(sql);
		//System.out.println(sql);
		multiSelect.append("SELECT COUNT(appt_id) as total FROM tblappt; ");
		
		multiSelect.append("SELECT * FROM tblservice; ");
		multiSelect.append("SELECT * FROM tbluser WHERE user_permission = 1; ");
		//System.out.println(multiSelect);
		return this.getReLists(multiSelect.toString());
	}

	@Override
	public boolean delAllAppt() {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tblappt;";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
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
	
	public static void main(String[] args) {
		StringBuilder appt = new StringBuilder("?,?,?,?,?,?,?,?,?,?,");
		System.out.println(appt);
		appt.deleteCharAt(appt.length()-1);
		System.out.println(appt);
	}
}
