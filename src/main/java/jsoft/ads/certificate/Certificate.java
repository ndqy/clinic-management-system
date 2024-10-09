package jsoft.ads.certificate;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ShareControl;
import jsoft.objects.CertificateObject;

public interface Certificate extends ShareControl{

	// Các phương thức / chức năng cập nhật thông tin chứng chỉ
	public boolean addCertificate(CertificateObject item);
	public boolean editCertificate(CertificateObject item);
	public boolean delCertificate(CertificateObject item);
	

	//Lấy thông tin theo id
	public ResultSet getCertificate(int id);
	
	//Phân trang
	public ArrayList<ResultSet> getCertificates(Triplet<CertificateObject, Short, Byte> infors);
}
