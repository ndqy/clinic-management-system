package jsoft.ads.calendar;

import java.sql.*;
import java.util.*;

import org.javatuples.*;
import jsoft.library.*;
import jsoft.*;
import jsoft.ads.user.USER_EDIT_TYPE;
import jsoft.ads.user.UserLibrary;
import jsoft.objects.CalendarObject;
import jsoft.objects.CustomerObject;
import jsoft.objects.ServiceObject;
import jsoft.objects.UserObject;

public class CalendarModel {

	private Calendar c;
	
	public CalendarModel(ConnectionPool cp){
		this.c = new CalendarImpl(cp);
	};
	
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
	public boolean addCalendar(CalendarObject item) {
		return this.c.addCalendar(item);
	}

	public boolean editCalendar(CalendarObject item) {
		return this.c.editCalendar(item);
	}

	public boolean delCalendar(CalendarObject item) {
		return this.c.delCalendar(item);
	}
	// **********************************************************************************
	public CalendarObject getCalendar(int id) {
		CalendarObject item = null;
		
		ResultSet rs = this.c.getCalendar(id);
		if(rs!=null) {

			try {
				while (rs.next()) {
					item = new CalendarObject();
					item.setCalendar_id(rs.getInt("calendar_id"));
					item.setCalendar_create_date(rs.getString("calendar_create_date"));
					item.setCalendar_start_date(rs.getString("calendar_start_date"));
					item.setCalendar_end_date(rs.getString("calendar_end_date"));
					item.setCalendar_customer_id(rs.getInt("calendar_customer_id"));
					item.setCalendar_service_id(rs.getInt("calendar_service_id"));
					item.setCalendar_room_id(rs.getInt("calendar_room_id"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;

	}
	
	/**
	 * Lấy lịch theo id
	 * @param id - id lịch, id khách hàng, id nha sĩ
	 * @param type
	 * @return
	 */
	 
	public ArrayList<CalendarObject> getCalendars(int id, CALENDAR_GET_TYPE type) {
		ArrayList<CalendarObject> items = new ArrayList<>();
		CalendarObject item = null;
		ResultSet rs = this.c.getCalendars(id, type);
		if(rs!=null) {
			try {
				while (rs.next()) {
					item = new CalendarObject();
					item.setCalendar_id(rs.getInt("calendar_id"));
					item.setCalendar_create_date(rs.getString("calendar_create_date"));
					item.setCalendar_start_date(rs.getString("calendar_start_date"));
					item.setCalendar_end_date(rs.getString("calendar_end_date"));
					item.setCalendar_customer_id(rs.getInt("calendar_customer_id"));
					item.setCalendar_service_id(rs.getInt("calendar_service_id"));
					item.setCalendar_room_id(rs.getInt("calendar_room_id"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return items;
	}

	/**
	 *  Lấy danh sách lịch theo ngày
	 * @param data
	 * @return
	 */
	public ArrayList<CalendarObject> getCalendars(String data) {
		ArrayList<CalendarObject> items = new ArrayList<>();
		
		ResultSet rs = this.c.getCalendars(data);

			try {
				while (rs.next()) {
					CalendarObject item = new CalendarObject();
					item.setCalendar_id(rs.getInt("calendar_id"));
					item.setCalendar_create_date(rs.getString("calendar_create_date"));
					item.setCalendar_start_date(rs.getString("calendar_start_date"));
					item.setCalendar_end_date(rs.getString("calendar_end_date"));
					item.setCalendar_customer_id(rs.getInt("calendar_customer_id"));
					item.setCalendar_service_id(rs.getInt("calendar_service_id"));
					item.setCalendar_room_id(rs.getInt("calendar_room_id"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return items;
	}
	
	public LinkedHashMap<String, Float> getStatistical(Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors){
		LinkedHashMap<String, Float> items = new LinkedHashMap<String, Float>();
		
		
		CalendarObject similar = infors.getValue0();
		float all;
		for(int i = 1; i<=12; i++) {
			similar.setCalendar_create_date(i+"");
			infors.setAt0(similar);
			ArrayList<ResultSet> res = this.c.getCalendars(infors);
			ResultSet rs = res.get(5);
			all = 0;
			if(rs!=null) {
				try {
					if (rs.next()) {
						all = rs.getInt("summ");
						items.put(i + " - " + similar.getCalendar_end_date(), all);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return items;
		
		
	}
	public Septet<
					ArrayList<CalendarObject>, // Danh sách lịch
					Integer,  // Tổng số bản ghi
					HashMap<Integer, ArrayList<String>>, //Tên dịch vụ
					HashMap<Integer,ArrayList<String>>, //Tên khách hàng
					HashMap<Integer, String>, // Tên nha sĩ
					HashMap<Integer, String>, // Danh sách dịch vụ
					HashMap<Integer, String> // Danh sách nha sĩ
				> getCalenders(Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors){
		
		ArrayList<CalendarObject> items = new ArrayList<>();
		CalendarObject item = null;
				
		ArrayList<ResultSet> res = this.c.getCalendars(infors);
		ResultSet rs = res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
					item = new CalendarObject();
					item.setCalendar_id(rs.getInt("calendar_id"));
					item.setCalendar_create_date(rs.getString("calendar_create_date"));
					item.setCalendar_start_date(rs.getString("calendar_start_date"));
					item.setCalendar_end_date(rs.getString("calendar_end_date"));
					item.setCalendar_customer_id(rs.getInt("calendar_customer_id"));
					item.setCalendar_service_id(rs.getInt("calendar_service_id"));
					item.setCalendar_room_id(rs.getInt("calendar_room_id"));
					item.setCalendar_dentist_id(rs.getInt("calendar_dentist_id"));
					items.add(item);
				}
			} catch (SQLException e) {				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Lấy tổng số bản ghi
		int all = 0;
		rs = res.get(1);
		if(rs!=null) {
			try {
				if (rs.next()) {
					all = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Danh sách tên dịch vụ - bệnh nhân - nha sĩ
		rs = res.get(2);
		HashMap<Integer, ArrayList<String>> ser = new HashMap<>();		
		HashMap<Integer, ArrayList<String>> cus = new HashMap<>();
		HashMap<Integer, String> den = new HashMap<>();
		//Danh sách thông tin
		ArrayList<String> arrSer;
		ArrayList<String> arrCus;
		ArrayList<String> arrDen = new ArrayList<>();
		if(rs!=null) {
			try {
				while(rs.next()) {
					arrSer = new ArrayList<>();
					arrSer.add(Utilities.decode(rs.getString("service_name")));
					arrSer.add(rs.getString("service_price"));
					arrSer.add(rs.getString("service_discount_price"));
					arrSer.add(rs.getString("service_id"));
					//Thêm thông tin vào HashMap
					ser.put(rs.getInt("service_id"), arrSer);
					
					
					arrCus = new ArrayList<String>();
					arrCus.add(Utilities.decode(rs.getString("customer_fullname")));
					arrCus.add(Utilities.decode(rs.getString("customer_address")));
					arrCus.add(rs.getString("customer_phone"));
					arrCus.add(rs.getString("customer_email"));
					arrSer.add(rs.getString("customer_id"));
					cus.put(rs.getInt("customer_id"), arrCus);
					
					/*arrDen.add(Utilities.decode(rs.getString("user_fullname")));*/
					den.put(rs.getInt("user_id"), Utilities.decode(rs.getString("user_fullname")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Danh sách các dịch vụ khám
		rs = res.get(3);
		HashMap<Integer, String> services = new HashMap<>();	
		ServiceObject se = null;
		if (rs != null) {
			try {
				while (rs.next()) {
					se = new ServiceObject();
					services.put(rs.getInt("service_id"), Utilities.decode(rs.getString("service_name")));
					
}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Danh sách nha sĩ
		rs = res.get(4);
		HashMap<Integer, String> dentists = new HashMap<>();	
		UserObject user = null;
		if(rs!=null) {
			try {
				while(rs.next()) {
					user = new UserObject();
					dentists.put(rs.getInt("user_id"), Utilities.decode(rs.getString("user_fullname"))+" ("+rs.getString("user_name")+")");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*Nha sĩ
		rs = res.get(4);
		HashMap<Integer, String> den = new HashMap<>();
		if(rs!=null) {
			try {
				while(rs.next()) {
					item.setCalendar_id(rs.getInt("calendar_id"));
					item.setCalendar_dentist_id(rs.getInt("calendar_dentist_id"));
					den.put(rs.getInt("user_id"), rs.getString("user_fullname"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return new Septet(items, all, ser, cus, den, services, dentists);
	}
	
//	public static void main(String[] args) {
//		ConnectionPool cp = new ConnectionPoolImpl();
//		CalendarModel cm = new CalendarModel(cp);
//		
//		/*ArrayList<CalendarObject> items = cm.getCalendars(1, CALENDAR_GET_TYPE.ROOM);
//		
//	
//		items.forEach(item->{
//			System.out.println(item.toString());
//		});*/
//		
//		CalendarObject cal = new CalendarObject();
//		cal.setCalendar_delete(false);
//		Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors = new Quintet(cal,null, null,CALENDAR_GET_TYPE.ID,"2023-10-10");
//		Quintet<
//				ArrayList<CalendarObject>, // Danh sách lịch
//				Integer,  // Tổng số bản ghi
//				HashMap<Integer, ArrayList<String>>, //Tên dịch vụ
//				HashMap<Integer, ArrayList<String>>, //Tên khách hàng
//				HashMap<Integer, String> // Tên nha sĩ
//			> data = cm.getCalenders(infors);
//		ArrayList<CalendarObject> items = data.getValue0();
//		items.forEach(item->{
//			System.out.println(item.toString());
//		});
//
//		System.out.println("----------------------------------");
//
//		System.out.println(data.getValue(1));
//		
//		System.out.println("----------------------------------");
//		HashMap<Integer, ArrayList<String>> a2 = data.getValue2();
//		HashMap<Integer, ArrayList<String>> a3 = data.getValue3();
//		HashMap<Integer, String> a4 = data.getValue4();
//		System.out.println("---------------SERVICE-----------------");
//		a2.forEach((k,v)->{
//			System.out.println("ID = "+ k + "| NAME = " + v.get(0) + "| ABC = " + v.get(1));
//		});
//		
//		System.out.println("---------------CUSTOMER-----------------");
//		a3.forEach((k,v)->{
//			System.out.println("ID = "+ k + "| NAME = " + v.get(0) + "| ABC = " + v.get(1));
//		});
//		
//		System.out.println("---------------CALENDAR-----------------");
//		a4.forEach((k,v)->{
//			System.out.println("ID = "+ k + "| NAME = " + v);
//		});
//		
//	}
}
