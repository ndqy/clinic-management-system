package jsoft.ads.certificate;
import jsoft.*;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.CertificateObject;
import jsoft.objects.UserObject;

import java.util.*;

import org.javatuples.*;
public class CertificateControl {

	CertificateModel cm;
	
	public CertificateControl(ConnectionPool cp) {
		this.cm = new CertificateModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.cm.getCP();
	}
	
	public void releaseConnection() {
		this.cm.releaseConnection();
	}
	
	//=====================================================
	public boolean addCertificate(CertificateObject item) {
		return this.cm.addCertificate(item);
	}
	public boolean editCertificate(CertificateObject item) {
		return this.cm.editCertificate(item);
	}
	public boolean delCertificate(CertificateObject item) {
		return this.cm.delCertificate(item);
	}
	
	//=====================================================
	
	public CertificateObject getCertificate(int id) {
		return this.cm.getCertificate(id);
	}
	public ArrayList<CertificateObject> getCertificates(Triplet<CertificateObject, Short, Byte> infors) {
		return this.cm.getCertificates(infors);
	}
	
	public static void main(String[] args) {
		
		ConnectionPool cp = (ConnectionPool) new ConnectionPoolImpl();
		CertificateControl cc = new CertificateControl(cp);
		
		CertificateObject item = new CertificateObject();
		item.setCertificate_code("654321");
		item.setCertificate_customer_id(100);
		item.setCertificate_created_date(Utilities_date.getDate());
		boolean res = cc.addCertificate(item);
		cc.releaseConnection();
		if(res) {
			System.out.println("ThanhCONG");
		}else {
			System.out.println("ThatBai");
		}

		//item.setCertificate_id(2);
		//item = cc.getCertificate(1);
		ArrayList<CertificateObject> items = cc.getCertificates(new Triplet<CertificateObject, Short, Byte>(null, null, null));
		System.out.println(items.size() + "==========================");
		items.forEach(tmp ->{
			System.out.println(tmp.getCertificate_id()+ "||" +tmp.getCertificate_code()+ 
					"||" +tmp.getCertificate_customer_id()+ "||" + tmp.getCertificate_created_date());
		});
		cc.releaseConnection();
	}
}
