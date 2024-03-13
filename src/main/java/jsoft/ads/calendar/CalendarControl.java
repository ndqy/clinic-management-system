package jsoft.ads.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.javatuples.Quintet;
import org.javatuples.Septet;

import jsoft.ConnectionPool;
import jsoft.ads.service.ServiceControl;
import jsoft.ads.user.UserLibrary;
import jsoft.objects.CalendarObject;

public class CalendarControl {
	private CalendarModel cm;
	
	public CalendarControl(ConnectionPool cp) {
		this.cm = new CalendarModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.cm.getCP();
	}
	
	public void releaseConnection() {
		this.cm.releaseConnection();
	}
	
	//*******************************************************************
	public boolean addCalendar(CalendarObject item) {
		return this.cm.addCalendar(item);
	}
	public boolean editCalendar(CalendarObject item) {
		return this.cm.editCalendar(item);
	}
	public boolean delCalendar(CalendarObject item) {
		return this.cm.delCalendar(item);
	}
	//*******************************************************************
	public CalendarObject getCalendar(int id){
		return this.cm.getCalendar(id);
	}
	public ArrayList<CalendarObject> getCalendars(int id, CALENDAR_GET_TYPE type){
		return this.cm.getCalendars(id, type);
	}
	public ArrayList<CalendarObject> getCalendars(String date){
		return this.cm.getCalendars(date);
	}
	
	public ArrayList<String> viewCalendars(Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors, String date, String view){
		Septet<ArrayList<CalendarObject>, // Danh sách lịch
				Integer,  // Tổng số bản ghi
				HashMap<Integer, ArrayList<String>>, //Tên dịch vụ
				HashMap<Integer, ArrayList<String>>, //Tên khách hàng
				HashMap<Integer, String>, // Tên nha sĩ
				HashMap<Integer, String>,
				HashMap<Integer, String>
				> data = this.cm.getCalenders(infors);
		
		return CalendarLibrary.getCalendars(data, infors, date,view);
	}
	public String viewAddCalendars(Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors){
		Septet<ArrayList<CalendarObject>, // Danh sách lịch
				Integer,  // Tổng số bản ghi
				HashMap<Integer, ArrayList<String>>, //Tên dịch vụ
				HashMap<Integer, ArrayList<String>>, //Tên khách hàng
				HashMap<Integer, String>, // Tên nha sĩ
				HashMap<Integer, String>,
				HashMap<Integer, String>
				> data = this.cm.getCalenders(infors);
		HashMap<Integer, String> arrSer = data.getValue5();
		HashMap<Integer, String> arrDen = data.getValue6();
		return CalendarLibrary.viewAddCal(arrSer, arrDen).toString();
	}
	
	public String viewStatisticalChart(Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors){
		LinkedHashMap<String, Float> data = this.cm.getStatistical(infors);
		return CalendarLibrary.createStatisticalChart(data).toString();
	}
	
}
