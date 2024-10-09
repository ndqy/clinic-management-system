package jsoft.ads.gksk;

import java.util.ArrayList;
import java.util.HashMap;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import jsoft.ConnectionPool;
import jsoft.objects.CustomerObject;
import jsoft.objects.GKSKObject;

public class GKSKControl {

	private GKSKModel gm;
	
	public GKSKControl(ConnectionPool cp) {
		this.gm = new GKSKModel(cp);
	}
	
	protected void finalize() throws Throwable{
		this.gm = null;
	}
	
	public ConnectionPool getCP() {
		return this.gm.getCP();
	}
	

	public void releaseConnection() {
		this.gm.releaseConnection();
	}

	// *********************************************************************************
	public boolean addGKSK(GKSKObject item) {
		return this.gm.addGKSK(item);
	}

	public boolean editGKSK(GKSKObject item) {
		return this.gm.editGKSK(item);
	}

	public boolean delGKSK(GKSKObject item) {
		return this.gm.delGKSK(item);
	}
	// **********************************************************************************
	public GKSKObject getGKSK(int id) {
		return this.gm.getGKSK(id);
	}
	
	public ArrayList<String> viewGKSK(Triplet<GKSKObject, Short, Byte> infors){
		
		Quartet<
			ArrayList<GKSKObject>, 
			Integer, 
			HashMap<Integer, CustomerObject>, 
			ArrayList<String> //Danh sách tên bác sĩ
			> data = this.gm.getGKSKs(infors);
		return GKSKLibrary.viewGKSK(data,infors);
	}
	
	public String viewAddGKSK(Triplet<GKSKObject, Short, Byte> infors){
		
		Quartet<
			ArrayList<GKSKObject>, 
			Integer, 
			HashMap<Integer, CustomerObject>, 
			ArrayList<String> //Danh sách tên bác sĩ
			> data = this.gm.getGKSKs(infors);
		HashMap<Integer, CustomerObject> cuss= data.getValue2();
		CustomerObject cus = new CustomerObject();
		if(cuss.size()>0) {
			cus = cuss.values().iterator().next();
		}else {
			return "<p class=\"mt-3\">Số điện thoại và số CCCD chưa đúng. Vui lòng nhập lại.</p>";
		}
		
		return GKSKLibrary.viewAddGKSK(data.getValue3(),cus);
	}
	
	public static void main(String[] args) {
//		HashMap<Integer, CustomerObject> cuss= new HashMap<Integer, CustomerObject>();
//		
//		for(int i=2;i<5;i++) {
//			CustomerObject cus = new CustomerObject();
//			cus.setCustomer_id(i);
//			cus.setCustomer_fullname("AAAA"+i);
//			cuss.put(i, cus);
//		}
//		
//		
//		CustomerObject cus = new CustomerObject();
//		cus = cuss.values().iterator().next();
//		System.out.println("ID: " + cus.getCustomer_id() + "NAME: " + cus.getCustomer_fullname());
	}
}

