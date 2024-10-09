package jsoft.ads.gksk;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.javatuples.Triplet;
import jsoft.ShareControl;
import jsoft.objects.GKSKObject;

public interface GKSK extends ShareControl {
	
		// Các phương thức / chức năng cập nhật thông tin
		public boolean addGKSK(GKSKObject item);
		public boolean editGKSK(GKSKObject item);
		public boolean delGKSK(GKSKObject item);
		
		// Các phương thức / chức năng lấy thông tin dịch vụ
		//Lấy thông tin theo id
		public ResultSet getGKSK(int id);	
		public ArrayList<ResultSet> getGKSKs(Triplet<GKSKObject, Short, Byte> infors);

}
