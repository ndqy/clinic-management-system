package jsoft.ads.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ShareControl;
import jsoft.objects.ServiceObject;

public interface Service extends ShareControl {
		// Các phương thức / chức năng cập nhật thông tin dịch vụ

		public boolean addService(ServiceObject item);
		public boolean editService(ServiceObject item,SERVICE_EDIT_TYPE type);
		public boolean delService(ServiceObject item);
		
		// Các phương thức / chức năng lấy thông tin dịch vụ
		//Lấy thông tin theo id
		public ResultSet getService(int id);	
		//Phân trang
		public ArrayList<ResultSet> getServices(Quartet<ServiceObject, Short, Byte, SERVICE_SORT_TYPE> infors);

}
