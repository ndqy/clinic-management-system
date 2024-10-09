package jsoft.objects;

public class ApptObject {
	private int appt_id;
	private String appt_fullname;
	private String appt_note;
	private String appt_date;
	private String appt_phone;
	private boolean appt_gender;
	
	public int getAppt_id() {
		return appt_id;
	}
	public String getAppt_fullname() {
		return appt_fullname;
	}
	public String getAppt_note() {
		return appt_note;
	}
	public String getAppt_date() {
		return appt_date;
	}

	public String getAppt_phone() {
		return appt_phone;
	}
	
	public boolean isAppt_gender() {
		return appt_gender;
	}
	public void setAppt_id(int appt_id) {
		this.appt_id = appt_id;
	}
	public void setAppt_fullname(String appt_fullname) {
		this.appt_fullname = appt_fullname;
	}
	public void setAppt_note(String appt_note) {
		this.appt_note = appt_note;
	}
	public void setAppt_date(String appt_date) {
		this.appt_date = appt_date;
	}
	public void setAppt_gender(boolean appt_gender) {
		this.appt_gender = appt_gender;
	}
	
	public void setAppt_phone(String appt_phone) {
		this.appt_phone = appt_phone;
	}
}
