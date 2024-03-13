package jsoft.ads.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.objects.ServiceObject;

public class ServiceModel {

	private Service s;
	
	public ServiceModel(ConnectionPool cp) {
		this.s = new ServiceImpl(cp);
	}
	protected void finalize() throws Throwable{
		this.s=null;
	} 
	public ConnectionPool getCP() {
		return this.s.getCP();
	}
	public void releaseConnection() {
		this.s.releaseConnection();
	}
	//============================================================
	public boolean addService(ServiceObject item) {
		return this.s.addService(item);
	}
	public boolean editService(ServiceObject item, SERVICE_EDIT_TYPE type) {
		return this.s.editService(item,type);
	}
	public boolean delService(ServiceObject item) {
		return this.s.delService(item);
	}
	//============================================================

	
	public ServiceObject getService(int id) {
		ServiceObject item = null;
		ResultSet rs = this.s.getService(id);
		if(rs!=null) {
			try {
				if(rs.next()) {
					item = new ServiceObject();
					item.setService_id(rs.getInt("service_id"));
					item.setService_name(Utilities.decode(rs.getString("service_name")));
					item.setService_price(rs.getFloat("service_price"));
					item.setService_discount_price(rs.getFloat("service_discount_price"));
					item.setService_notes(Utilities.decode(rs.getString("service_notes")));
					item.setService_expected_time(rs.getString("service_expected_time"));
					item.setService_modified_date(rs.getString("service_modified_date"));
					item.setService_delete(rs.getBoolean("service_delete"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	
	
	public Pair<ArrayList<ServiceObject>,Integer> getServices(Quartet<ServiceObject, Short, Byte, SERVICE_SORT_TYPE> infors){
		ArrayList<ServiceObject> items = new ArrayList<ServiceObject>();
		
		ServiceObject item = null;
		ArrayList<ResultSet> res = this.s.getServices(infors);
		ResultSet rs = res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
					item = new ServiceObject();
					item.setService_id(rs.getInt("service_id"));
					item.setService_name(Utilities.decode(rs.getString("service_name")));
					item.setService_price(rs.getFloat("service_price"));
					item.setService_discount_price(rs.getFloat("service_discount_price"));
					item.setService_notes(Utilities.decode(rs.getString("service_notes")));
					item.setService_expected_time(rs.getString("service_expected_time"));
					item.setService_modified_date(rs.getString("service_modified_date"));
					item.setService_delete(rs.getBoolean("service_delete"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Lấy tổng số bản ghi
		rs = res.get(1);
		int total = 0;
		if(rs!=null) {
			try {
				if(rs.next()) {
					total = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new Pair<>(items, total);
	}
	
	
}
