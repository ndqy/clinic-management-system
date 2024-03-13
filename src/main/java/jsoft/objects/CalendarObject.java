package jsoft.objects;

import java.util.ArrayList;

public class CalendarObject {
	
	private int calendar_id;
	private String calendar_create_date;
	private String calendar_start_date;
	private String calendar_end_date;
	private int calendar_customer_id;
	private int calendar_service_id;
	private int calendar_room_id;
	private String calendar_notes;
	private int calendar_dentist_id;
	private boolean calendar_delete;
	private String calendar_last_modified;
	
	public String getCalendar_notes() {
		return calendar_notes;
	}

	public int getCalendar_dentist_id() {
		return calendar_dentist_id;
	}



	public void setCalendar_notes(String calendar_notes) {
		this.calendar_notes = calendar_notes;
	}

	public void setCalendar_dentist_id(int calendar_dentist_id) {
		this.calendar_dentist_id = calendar_dentist_id;
	}



	public boolean isCalendar_delete() {
		return calendar_delete;
	}

	public void setCalendar_delete(boolean calendar_delete) {
		this.calendar_delete = calendar_delete;
	}

	public CalendarObject() {
		// TODO Auto-generated constructor stub
	}

	public int getCalendar_id() {
		return calendar_id;
	}

	public String getCalendar_create_date() {
		return calendar_create_date;
	}

	public String getCalendar_start_date() {
		return calendar_start_date;
	}

	public String getCalendar_end_date() {
		return calendar_end_date;
	}

	public int getCalendar_customer_id() {
		return calendar_customer_id;
	}

	public int getCalendar_service_id() {
		return calendar_service_id;
	}

	public int getCalendar_room_id() {
		return calendar_room_id;
	}

	public void setCalendar_id(int calendar_id) {
		this.calendar_id = calendar_id;
	}

	public void setCalendar_create_date(String calendar_create_date) {
		this.calendar_create_date = calendar_create_date;
	}

	public void setCalendar_start_date(String calendar_start_date) {
		this.calendar_start_date = calendar_start_date;
	}

	public void setCalendar_end_date(String calendar_end_date) {
		this.calendar_end_date = calendar_end_date;
	}

	public void setCalendar_customer_id(int calendar_customer_id) {
		this.calendar_customer_id = calendar_customer_id;
	}

	public void setCalendar_service_id(int calendar_service_id) {
		this.calendar_service_id = calendar_service_id;
	}

	public void setCalendar_room_id(int calendar_room_id) {
		this.calendar_room_id = calendar_room_id;
	}
	
	public String getCalendar_last_modified() {
		return calendar_last_modified;
	}

	public void setCalendar_last_modified(String calendar_last_modified) {
		this.calendar_last_modified = calendar_last_modified;
	}

	@Override
	public String toString() {
		return "CalendarObject [calendar_id=" + calendar_id + ", calendar_create_date=" + calendar_create_date
				+ ", calendar_start_date=" + calendar_start_date + ", calendar_end_date=" + calendar_end_date
				+ ", calendar_customer_id=" + calendar_customer_id + ", calendar_service_id=" + calendar_service_id
				+ ", calendar_room_id=" + calendar_room_id + "]";
	}
	
	
}
