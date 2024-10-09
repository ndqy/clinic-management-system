package jsoft.ads.customer;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.objects.CustomerObject;

public class CustomerControl {
	private CustomerModel cm;
	
	public CustomerControl(ConnectionPool cp) {
		this.cm = new CustomerModel(cp);
	}
	
	protected void finalize() throws Throwable{
		this.cm = null;
	}
	
	public ConnectionPool getCP() {
		return this.cm.getCP();
	}
	

	public void releaseConnection() {
		this.cm.releaseConnection();
	}

	// *********************************************************************************
	public boolean addCustomer(CustomerObject item) {
		return this.cm.addCustomer(item);
	}

	public boolean editCustomer(CustomerObject item, CUSTOMER_EDIT_TYPE et) {
		return this.cm.editCustomer(item,et);
	}

	public boolean delCustomer(CustomerObject item) {
		return this.cm.delCustomer(item);
	}
	// **********************************************************************************
	public CustomerObject getCustomer(int id) {
		return this.cm.getCustomer(id);
	}
	public CustomerObject getCustomer(String phone) {
		return this.cm.getCustomer(phone);
	}
	public CustomerObject getCustomerObject(String uname, String Upass) {
		return this.cm.getCustomerObject(uname, Upass);
	}
	
	//*******************************************************************
	/*public ArrayList<String> viewCustomer(Quartet<CustomerObject, Short, Byte,CUSTOMER_SORT_TYPE> infors){
		
		CustomerObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		CUSTOMER_SORT_TYPE ust = infors.getValue3();
		Pair<ArrayList<CustomerObject>, Integer> data = this.cm.getCustomerObjects(infors);
		
		return CustomerLibrary.viewCustomer(data, infors);
	}*/
	public ArrayList<String> viewCustomer(Quartet<CustomerObject, Short, Byte,CUSTOMER_SORT_TYPE> infors){
		
		/*CustomerObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		CUSTOMER_SORT_TYPE ust = infors.getValue3();*/
		Pair<ArrayList<CustomerObject>, Integer> data = this.cm.getCustomerObjects(infors);
		
		return CustomerLibrary.viewCustomer(data, infors);
	}
	
}
