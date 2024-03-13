package jsoft.ads.service;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.objects.ServiceObject;

public class ServiceControl {
	private ServiceModel sm;
	
	public ServiceControl(ConnectionPool cp ) {
		this.sm = new ServiceModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.sm.getCP();
	}
	
	public void releaseConnection() {
		this.sm.releaseConnection();
	}
	
	//*******************************************************************
	public boolean addService(ServiceObject item) {
		return this.sm.addService(item);
	}
	public boolean editService(ServiceObject item, SERVICE_EDIT_TYPE type) {
		return this.sm.editService(item, type);
	}
	public boolean delService(ServiceObject item) {
		return this.sm.delService(item);
	}
	//*******************************************************************
	
	public ServiceObject getServiceObject(int id) {
		return this.sm.getService(id);
	}
	
	public ArrayList<String> viewService(Quartet<ServiceObject, Short, Byte, SERVICE_SORT_TYPE> infors){
		
		ServiceObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		SERVICE_SORT_TYPE type = infors.getValue3();
		Pair<ArrayList<ServiceObject>, Integer> data = this.sm.getServices(infors);
		
		return ServiceLibrary.viewServices(data, infors);
	}
	
	
	
	
}
