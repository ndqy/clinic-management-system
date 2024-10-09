package jsoft.ads.appt;
import jsoft.*;
import jsoft.library.Utilities;
import jsoft.objects.ApptObject;
import jsoft.objects.ServiceObject;
import jsoft.objects.UserObject;

import java.util.*;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
public class ApptControl {
	private ApptModel tm;
	
	public ApptControl(ConnectionPool cp ) {
		this.tm = new ApptModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.tm.getCP();
	}
	
	public void releaseConnection() {
		this.tm.releaseConnection();
	}
	
	//*******************************************************************
	public boolean addAppt(ApptObject tmp) {
		return this.tm.addAppt(tmp);
	}
	
	public boolean delAppt(ApptObject tmp) {
		return this.tm.delAppt(tmp);
	}
	
	public boolean delListAppt(ArrayList<Integer> list) {
		return this.tm.delListAppt(list);
	}
	
	public boolean delAllAppt() {
		return this.tm.delAllAppt();
	}
	//*******************************************************************
	public ApptObject getApptObject(int id) {
		return this.tm.getApptObject(id);
	}
	
	public ArrayList<String> viewAppt(Triplet<ApptObject, Short, Byte> infors){
		
		ApptObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		Pair<ArrayList<ApptObject>, Integer> data = this.tm.getApptObjects(similar, page, total);
		Pair<ArrayList<ServiceObject>, ArrayList<UserObject>> list = this.tm.getListSerDen();
		return ApptLibrary.viewAppt(data,infors, list);
	}
	
	
}
