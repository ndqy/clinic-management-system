package jsoft.ads.appt;

import java.sql.*;
import java.util.*;
import jsoft.*;
import jsoft.library.Utilities;
import jsoft.objects.ApptObject;
import jsoft.objects.ServiceObject;
import jsoft.objects.UserObject;

import org.javatuples.*;

public class ApptModel {
	private Appt t;
	
	public ApptModel(ConnectionPool cp) {
		this.t = new ApptImpl(cp);
	}
	
	protected void finalize() throws Throwable{
		this.t = null;
	}
	
	public ConnectionPool getCP() {
		return this.t.getCP();
	}
	

	public void releaseConnection() {
		this.t.releaseConnection();
	}

	// *********************************************************************************
	public boolean addAppt(ApptObject item) {
		return this.t.addAppt(item);
	}

	public boolean delAppt(ApptObject item) {
		return this.t.delAppt(item);
	}

	public boolean delListAppt(ArrayList<Integer> list) {
		return this.t.delListAppt(list);
	}
	
	public boolean delAllAppt() {
		return this.t.delAllAppt();
	}
	// **********************************************************************************
	public ApptObject getApptObject(int id) {
		ApptObject item = null;
		//Lấy bản ghi
		ResultSet rs = this.t.getAppt(id);
		if(rs!=null) {
			try {
				if(rs.next()) {
					item = new ApptObject();
					item.setAppt_id(rs.getInt("appt_id"));
					item.setAppt_fullname(Utilities.decode(rs.getString("appt_fullname")));
					item.setAppt_note(rs.getString("appt_note"));
					item.setAppt_gender(rs.getBoolean("appt_gender"));
					item.setAppt_date(rs.getString("appt_date"));
					item.setAppt_phone(rs.getString("appt_phone"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	
	public Pair<ArrayList<ApptObject>, Integer> getApptObjects(ApptObject similar, short page, byte total){
		ArrayList<ApptObject> items = new ArrayList<>();
		ApptObject item = null;
		
		//Lấy danh sách bản ghi
		int at = (page-1)*total;
		ArrayList<ResultSet> res = this.t.getAppts(similar, at, total);
		ResultSet rs = res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
					item = new ApptObject();
					item.setAppt_id(rs.getInt("appt_id"));
					item.setAppt_fullname(Utilities.decode(rs.getString("appt_fullname")));
					item.setAppt_note(rs.getString("appt_note"));
					item.setAppt_gender(rs.getBoolean("appt_gender"));
					item.setAppt_date(rs.getString("appt_date"));
					item.setAppt_phone(rs.getString("appt_phone"));
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
	
	public Pair<ArrayList<ServiceObject>, ArrayList<UserObject>> getListSerDen(){
		ArrayList<ServiceObject> sers = new ArrayList<>();
		ArrayList<UserObject> dens = new ArrayList<>();
		
		ArrayList<ResultSet> res = this.t.getAppts(null, 0, (byte)0);
		ResultSet rs = res.get(2);
		ServiceObject ser = null;
		if(rs!=null) {
			try {
				while(rs.next()) {
					ser = new ServiceObject();
					ser.setService_id(rs.getInt("service_id"));
					ser.setService_name(rs.getString("service_name"));
					sers.add(ser);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		rs = res.get(3);
		UserObject den = null;
		if(rs!=null) {
			try {
				while(rs.next()) {
					den = new UserObject();
					den.setUser_id(rs.getInt("user_id"));
					den.setUser_fullname(Utilities.decode(rs.getString("user_fullname")));
					dens.add(den);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return new Pair<>(sers,dens);
		
	}
	
	public static void main(String[] args) {
		String name = "name05";
		System.out.println(name.substring(4));
	}
}
