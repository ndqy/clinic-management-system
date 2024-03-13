package jsoft.ads.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;

import jsoft.ConnectionPool;
import jsoft.ads.user.USER_EDIT_TYPE;
import jsoft.ads.user.USER_SORT_TYPE;
import jsoft.ads.user.User;
import jsoft.ads.user.UserImpl;
import jsoft.library.Utilities;
import jsoft.objects.CustomerObject;
import jsoft.objects.UserObject;

public class CustomerModel {
	private Customer c;
	
	public CustomerModel(ConnectionPool cp) {
		this.c = new CustomerImpl(cp);
	}
	
	protected void finalize() throws Throwable{
		this.c = null;
	}
	
	public ConnectionPool getCP() {
		return this.c.getCP();
	}
	

	public void releaseConnection() {
		this.c.releaseConnection();
	}

	// *********************************************************************************
	public boolean addCustomer(CustomerObject item) {
		return this.c.addCustomer(item);
	}

	public boolean editCustomer(CustomerObject item, CUSTOMER_EDIT_TYPE et) {
		return this.c.editCustomer(item,et);
	}

	public boolean delCustomer(CustomerObject item) {
		return this.c.delCustomer(item);
	}
	// **********************************************************************************
	public CustomerObject getCustomer(int id) {
		CustomerObject item = null;
		ResultSet rs = this.c.getCustomer(id);
		if(rs!=null) {
			try {
				if(rs.next()) {
					item = new CustomerObject();
					item.setCustomer_id(rs.getInt("customer_id"));
					item.setCustomer_fullname(Utilities.decode(rs.getString("customer_fullname")));
					item.setCustomer_address(Utilities.decode(rs.getString("customer_address")));
					item.setCustomer_phone(rs.getString("customer_phone"));
					item.setCustomer_notes(Utilities.decode(rs.getString("customer_notes")));
					item.setCustomer_email(rs.getString("customer_email"));
					item.setCustomer_username(rs.getString("customer_username"));
					item.setCustomer_modified_date(rs.getString("customer_modified_date"));
					item.setCustomer_isactive(rs.getBoolean("customer_isactive"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return item;
	}
	
	public CustomerObject getCustomer(String phone) {
		CustomerObject item = null;
		ResultSet rs = this.c.getCustomer(phone);
		if(rs!=null) {
			try {
				if(rs.next()) {
					item = new CustomerObject();
					item.setCustomer_id(rs.getInt("customer_id"));
					item.setCustomer_fullname(Utilities.decode(rs.getString("customer_fullname")));
					item.setCustomer_address(Utilities.decode(rs.getString("customer_address")));
					item.setCustomer_phone(rs.getString("customer_phone"));
					item.setCustomer_notes(Utilities.decode(rs.getString("customer_notes")));
					item.setCustomer_email(rs.getString("customer_email"));
					item.setCustomer_username(rs.getString("customer_username"));
					item.setCustomer_modified_date(rs.getString("customer_modified_date"));
					item.setCustomer_isactive(rs.getBoolean("customer_isactive"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return item;
	}
	

	//Phương thức đăng nhập
	public CustomerObject getCustomerObject(String phone, String userpass) {
		CustomerObject item = null;
			
		//Lấy bản ghi
		ResultSet rs = this.c.getCustomer(phone, userpass);
		if(rs!=null) {
			try {
				if(rs.next()) {
					item = new CustomerObject();
					item.setCustomer_id(rs.getInt("customer_id"));
					item.setCustomer_fullname(Utilities.decode(rs.getString("customer_fullname")));
					item.setCustomer_address(Utilities.decode(rs.getString("customer_address")));
					item.setCustomer_phone(rs.getString("customer_phone"));
					item.setCustomer_notes(Utilities.decode(rs.getString("customer_notes")));
					item.setCustomer_email(rs.getString("customer_email"));
					item.setCustomer_username(rs.getString("customer_username"));
					item.setCustomer_modified_date(rs.getString("customer_modified_date"));
					item.setCustomer_isactive(rs.getBoolean("customer_isactive"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}
	
	/*public Pair<ArrayList<CustomerObject>, Integer> getCustomerObjects(Quartet<CustomerObject , Short , Byte , CUSTOMER_SORT_TYPE> infors){
		ArrayList<CustomerObject> items = new ArrayList<>();
		CustomerObject item = null;

		ArrayList<ResultSet> res = this.c.getCustomers(infors);
		ResultSet rs = res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
					item = new CustomerObject();
					item.setCustomer_id(rs.getInt("customer_id"));
					item.setCustomer_fullname(Utilities.decode(rs.getString("customer_fullname")));
					item.setCustomer_address(Utilities.decode(rs.getString("customer_address")));
					item.setCustomer_phone(rs.getString("customer_phone"));
					item.setCustomer_notes(Utilities.decode(rs.getString("customer_notes")));
					item.setCustomer_jobarea(Utilities.decode(rs.getString("customer_jobarea")));
					item.setCustomer_email(rs.getString("customer_email"));
					item.setCustomer_username(rs.getString("customer_username"));
					item.setCustomer_modified_date(rs.getString("customer_modified_date"));
					item.setCustomer_isactive(rs.getBoolean("customer_isactive"));
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
		
	}*/
	public Pair<ArrayList<CustomerObject>, Integer> getCustomerObjects(Quartet<CustomerObject , Short , Byte , CUSTOMER_SORT_TYPE> infors){
		ArrayList<CustomerObject> items = new ArrayList<>();
		CustomerObject item = null;

		ArrayList<ResultSet> res = this.c.getCustomers(infors);
		ResultSet rs = res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
					item = new CustomerObject();
					item.setCustomer_id(rs.getInt("customer_id"));
					item.setCustomer_fullname(Utilities.decode(rs.getString("customer_fullname")));
					item.setCustomer_address(Utilities.decode(rs.getString("customer_address")));
					item.setCustomer_phone(rs.getString("customer_phone"));
					item.setCustomer_notes(Utilities.decode(rs.getString("customer_notes")));
					item.setCustomer_jobarea(Utilities.decode(rs.getString("customer_jobarea")));
					item.setCustomer_email(rs.getString("customer_email"));
					item.setCustomer_username(rs.getString("customer_username"));
					item.setCustomer_modified_date(rs.getString("customer_modified_date"));
					item.setCustomer_isactive(rs.getBoolean("customer_isactive"));
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
