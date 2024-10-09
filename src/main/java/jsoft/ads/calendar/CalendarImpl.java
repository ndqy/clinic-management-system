package jsoft.ads.calendar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Quartet;
import org.javatuples.Quintet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.CalendarObject;

public class CalendarImpl extends BasicImpl implements Calendar{

	public CalendarImpl(ConnectionPool cp) {
		super(cp, "Calendar");
	}
	
	
	@Override
	public boolean addCalendar(CalendarObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblcalendar (");
		sql.append("calendar_create_date, calendar_start_date, calendar_end_date, ");			
		sql.append("calendar_customer_id, calendar_service_id, calendar_room_id, ");
		sql.append("calendar_notes, calendar_dentist_id, calendar_delete) ");
		sql.append("VALUES(?,?,ADDTIME(calendar_start_date, ?),?,?,?,?,?,0)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getCalendar_create_date());
			pre.setString(2, item.getCalendar_start_date());
			pre.setString(3, item.getCalendar_end_date());
			pre.setInt(4, item.getCalendar_customer_id());
			pre.setInt(5, item.getCalendar_service_id());
			pre.setInt(6, item.getCalendar_room_id());
			pre.setString(7, item.getCalendar_notes());
			pre.setInt(8, item.getCalendar_dentist_id());
			System.out.println(pre.toString());
			return this.add(pre);
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
	public boolean editCalendar(CalendarObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblcalendar SET ");
		sql.append("calendar_create_date = ? , calendar_start_date = ? , calendar_end_date = ADDTIME(calendar_start_date, ?) ,");
		sql.append("calendar_customer_id = ? , calendar_service_id = ? , calendar_room_id = ? ,");
		sql.append("calendar_notes = ? , calendar_dentist_id = ? ");
		sql.append("WHERE calendar_id = ? ; ");
		sql.append("");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getCalendar_create_date());
			pre.setString(2, item.getCalendar_start_date());
			pre.setString(3, item.getCalendar_end_date());
			pre.setInt(4, item.getCalendar_customer_id());
			pre.setInt(5, item.getCalendar_service_id());
			pre.setInt(6, item.getCalendar_room_id());
			pre.setString(7, item.getCalendar_notes());
			pre.setInt(8, item.getCalendar_dentist_id());
			pre.setInt(9, item.getCalendar_id());
			
			return this.add(pre);
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
	public boolean delCalendar(CalendarObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tblcalendar WHERE calendar_id = ? ";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getCalendar_id());

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
	public ResultSet getCalendar(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblcalendar t WHERE calendar_id = ?;";
		return this.get(sql, id);
	}

	public ResultSet getCalendars(String date) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tblcalendar WHERE calendar_create_date = ?";	
		
		System.out.println(sql);
		
		return this.gets(sql, date);
	}
	
	public ResultSet getCalendars(int id, CALENDAR_GET_TYPE type) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tblcalendar WHERE ";
		switch (type) {
		case ID:
			sql +="calendar_id = ? ";
		case SERVICE:
			sql += "calendar_service_id = ? ";
		case ROOM:
			sql += "calendar_room_id = ? ";
		}
		sql += " ORDER BY calendar_start_date ASC;";
		
		System.out.println(sql);
		return this.get(sql, id, type);
	}

	@Override
	public ArrayList<ResultSet> getCalendars(Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors) {
		// TODO Auto-generated method stub
		CalendarObject similar = infors.getValue0();
		if(infors.getValue(1)!=null && infors.getValue(2) !=null) {
			byte totalperpage = infors.getValue2();
			int at = (infors.getValue1() - 1) * totalperpage;
		}
		
		CALENDAR_GET_TYPE type = infors.getValue3();
		String date = infors.getValue4();
		StringBuilder sql = new StringBuilder();
		
		//Lấy danh sách lịch khám
		sql.append("SELECT * FROM tblcalendar ");
		sql.append("WHERE (DATE(calendar_start_date) = '"+date+"') AND ");	
		if(similar.isCalendar_delete()) {
			sql.append(" (calendar_delete = 1) ");
		}else{
			sql.append(" (calendar_delete = 0) ");
		}
		switch (type) {
			case ID:
				sql.append(" ORDER BY calendar_id DESC ");
				break;
			case ROOM:
				sql.append(" ORDER BY calendar_room_id DESC ");
				break;
			case SERVICE:
				sql.append(" ORDER BY calendar_service_id DESC ");
				break;
			default :
				sql.append(" ORDER BY calendar_id DESC ");
				break;
		}
		//Dùng để phân trang
		if(infors.getValue(1)!=null && infors.getValue(2) !=null) {
			byte totalperpage = infors.getValue2();
			int at = (infors.getValue1() - 1) * totalperpage;
			sql.append("LIMIT ").append(at).append(", ").append(totalperpage)/*.append("; ")*/;
		}
		//Đếm tổng số lịch khám trong ngày
		sql.append("; SELECT COUNT(calendar_id) as total FROM tblcalendar ");
		sql.append("WHERE (DATE(calendar_start_date) = '").append(date).append("') AND ");	
		if(similar.isCalendar_delete()) {
			sql.append(" (calendar_delete = 1); ");
		}else{
			sql.append(" (calendar_delete = 0); ");
		}
		
		//Danh sách tên dịch vụ - bệnh nhân - nha sĩ
		sql.append("SELECT * FROM tblcalendar ");
		sql.append("LEFT JOIN tbluser ON calendar_dentist_id = user_id ");
		sql.append("LEFT JOIN tblcustomer ON calendar_customer_id = customer_id ");
		sql.append("LEFT JOIN tblservice ON calendar_service_id = service_id ");
		sql.append("WHERE (DATE(calendar_start_date) = '"+date+"') AND ");	
		sql.append(" (calendar_delete = 0) ; ");
		
		//Danh sách các dịch vụ khám
		sql.append("SELECT service_id, service_name FROM tblservice ");
		sql.append("WHERE service_delete = 0; ");
		
		//Danh sách nha sĩ
		sql.append("SELECT * FROM tbluser ");
		sql.append("WHERE user_deleted = 0 AND user_permission = 1 ; ");
		
		//Thống kê doanh thu theo tháng
//		sql.append("SELECT SUM(calendar_service_id*(service_price-service_discount_price)) as summ ");
//		sql.append("FROM tblcalendar c INNER JOIN tblservice s ON c.calendar_service_id = s.service_id ");
//		sql.append("WHERE MONTH(calendar_start_date)='"+similar.getCalendar_create_date()+"' ");//Tháng
//		sql.append(" AND YEAR(calendar_start_date)='"+similar.getCalendar_end_date()+"'; ");//Năm
		
		//Thống kê doanh thu theo tháng
		sql.append("SELECT SUM(service_price - service_discount_price) * countt AS summ ");
		sql.append("FROM( SELECT calendar_service_id,    service_price, service_discount_price, ");
				sql.append("COUNT(calendar_service_id) AS countt FROM tblcalendar c ");
				sql.append("INNER JOIN tblservice s ON c.calendar_service_id = s.service_id ");
				sql.append("WHERE MONTH(calendar_start_date) = '"+similar.getCalendar_create_date()+"' ");
				sql.append("AND YEAR(calendar_start_date) = '"+similar.getCalendar_end_date()+"' ");
				sql.append("GROUP BY calendar_service_id, service_price, service_discount_price ");
				sql.append(") AS subquery ");
		sql.append("GROUP BY calendar_service_id; ");
		
				sql.append("");
				sql.append("");
				sql.append("");
				sql.append("");
				
		System.out.println(sql.toString());
				
		return this.getReLists(sql.toString());
	}

	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		
		CalendarImpl c = new CalendarImpl(cp);
		CalendarObject co = new CalendarObject();
		co.setCalendar_customer_id(4);
		co.setCalendar_dentist_id(4);
		co.setCalendar_room_id(4);
		co.setCalendar_service_id(4);
		co.setCalendar_start_date("2023-11-16 11:11:00");
		//co.setCalendar_end_date("2023-11-16 11:33:00");
		co.setCalendar_end_date("00:22");
		//co.setCalendar_end_date("ADDTIME('2023-11-16 11:00:00', '01:30')");
		
		if(c.addCalendar(co)) {
			System.out.println("THANG CONG");
		}else {
			System.out.println("THAT BAIOooooooo");
		}
	}

}
