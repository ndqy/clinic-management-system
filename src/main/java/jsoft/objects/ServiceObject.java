package jsoft.objects;

public class ServiceObject {
	private int service_id; 
	private String service_name; 
	private float service_price; 
	private float service_discount_price; 
	private String service_created_date; 
	private boolean service_delete; 
	private int service_manager_id; 
	private boolean service_enable; 
	private String service_modified_date; 
	private String service_expected_time; 
	private int service_created_author_id; 
	private String service_notes;
	public int getService_id() {
		return service_id;
	}
	public String getService_name() {
		return service_name;
	}
	public float getService_price() {
		return service_price;
	}
	public float getService_discount_price() {
		return service_discount_price;
	}
	public String getService_created_date() {
		return service_created_date;
	}
	public boolean isService_delete() {
		return service_delete;
	}
	public int getService_manager_id() {
		return service_manager_id;
	}
	public boolean isService_enable() {
		return service_enable;
	}
	public String getService_modified_date() {
		return service_modified_date;
	}
	public String getService_expected_time() {
		return service_expected_time;
	}
	public int getService_created_author_id() {
		return service_created_author_id;
	}
	public String getService_notes() {
		return service_notes;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public void setService_price(float service_price) {
		this.service_price = service_price;
	}
	public void setService_discount_price(float service_discount_price) {
		this.service_discount_price = service_discount_price;
	}
	public void setService_created_date(String service_created_date) {
		this.service_created_date = service_created_date;
	}
	public void setService_delete(boolean service_delete) {
		this.service_delete = service_delete;
	}
	public void setService_manager_id(int service_manager_id) {
		this.service_manager_id = service_manager_id;
	}
	public void setService_enable(boolean service_enable) {
		this.service_enable = service_enable;
	}
	public void setService_modified_date(String service_modified_date) {
		this.service_modified_date = service_modified_date;
	}
	public void setService_expected_time(String service_expected_time) {
		this.service_expected_time = service_expected_time;
	}
	public void setService_created_author_id(int service_created_author_id) {
		this.service_created_author_id = service_created_author_id;
	}
	public void setService_notes(String service_notes) {
		this.service_notes = service_notes;
	}
	@Override
	public String toString() {
		return "ServiceObject [service_id=" + service_id + ", service_name=" + service_name + ", service_price="
				+ service_price + ", service_discount_price=" + service_discount_price + ", service_created_date="
				+ service_created_date + ", service_delete=" + service_delete + ", service_manager_id="
				+ service_manager_id + ", service_enable=" + service_enable + ", service_modified_date="
				+ service_modified_date + ", service_expected_time=" + service_expected_time
				+ ", service_created_author_id=" + service_created_author_id + ", service_notes=" + service_notes + "]";
	}
	
	
}
