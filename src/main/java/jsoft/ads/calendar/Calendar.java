package jsoft.ads.calendar;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quintet;

import jsoft.ShareControl;
import jsoft.objects.CalendarObject;

public interface Calendar extends ShareControl{
		// Các phương thức / chức năng cập nhật thông tin lịch khám
		public boolean addCalendar(CalendarObject item);
		public boolean editCalendar(CalendarObject item);
		public boolean delCalendar(CalendarObject item);
		
		// Các phương thức / chức năng lấy thông tin lịch khám
		//Lấy thông tin theo id
		public ResultSet getCalendar(int id);
		//Lấy danh sách lịch khám theo ngày
		public ResultSet getCalendars(String date);
		//Vẽ biểu đồ - Lấy thông tin theo từng loại
		public ResultSet getCalendars(int id, CALENDAR_GET_TYPE type);
		//Phân trang
		public ArrayList<ResultSet> getCalendars(Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors);

}
