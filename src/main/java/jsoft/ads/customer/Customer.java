package jsoft.ads.customer;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ShareControl;
import jsoft.objects.CustomerObject;
import jsoft.objects.ServiceObject;

public interface Customer extends ShareControl{
	
		// Các phương thức / chức năng cập nhật thông tin dịch vụ
		public boolean addCustomer(CustomerObject item);
		public boolean editCustomer(CustomerObject item, CUSTOMER_EDIT_TYPE type);
		public boolean delCustomer(CustomerObject item);
		
		// Các phương thức / chức năng lấy thông tin dịch vụ
		
		//Lấy thông tin theo id
		public ResultSet getCustomer(int id);
		
		public ResultSet getCustomer(String phone);
		//Đăng nhập
		public ResultSet getCustomer(String name, String pass);
		//Phân trang
		public ArrayList<ResultSet> getCustomers(Quartet<CustomerObject, Short, Byte, CUSTOMER_SORT_TYPE> infors);

}
