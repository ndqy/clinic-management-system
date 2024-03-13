package jsoft.ads.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.library.Utilities;
import jsoft.objects.ServiceObject;

public class ServiceImpl extends BasicImpl implements Service {

	public ServiceImpl(ConnectionPool cp) {
		super(cp, "Service");
		// TODO Auto-generated constructor stub
	}

	private boolean isExist(ServiceObject item) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT service_id FROM tblservice WHERE service_name = '"+item.getService_name()+"' ;");
		ResultSet rs = this.gets(sql.toString());
		if(rs!=null) {
			try {
				if(rs.next()) {
					flag = true;
					
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	@Override
	public boolean addService(ServiceObject item) {
		// TODO Auto-generated method stub
		
		if(isExist(item)) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tblservice (");
		sql.append("service_name, service_price, service_discount_price, ");
		sql.append("service_manager_id, service_expected_time, service_modified_date, ");
		sql.append("service_created_author_id, service_notes, service_created_date, service_delete )");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,0)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getService_name());
			pre.setFloat(2, item.getService_price());
			pre.setFloat(3, item.getService_discount_price());
			pre.setInt(4, item.getService_manager_id());
			pre.setString(5, item.getService_expected_time());
			pre.setString(6, item.getService_created_date());
			pre.setInt(7, item.getService_created_author_id());
			pre.setString(8,  item.getService_notes());
			pre.setString(9, item.getService_modified_date());
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	
	@Override
	public boolean editService(ServiceObject item, SERVICE_EDIT_TYPE type) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblservice SET ");
		switch (type) {
			case GENERAL: 
				sql.append("service_name  = ? , service_price  = ? , service_discount_price  = ? ,");
				sql.append("service_manager_id  = ?, ");
				sql.append("service_modified_date  = ? ,service_expected_time  = ? ,");
				sql.append("service_created_author_id  = ? ,service_notes  = ? ");
				break;
			case RESTORE:
				sql.append("service_delete  = 0, service_modified_date  = ? ");
				break;
			case TRASH:
				sql.append("service_delete  = 1, service_modified_date  = ? ");
				break;
		}
		sql.append("WHERE service_id = ?");
		
		try {
			PreparedStatement pre= this.con.prepareStatement(sql.toString());
			switch (type) {
			case GENERAL: 
				pre.setString(1, item.getService_name());
				pre.setFloat(2, item.getService_price());
				pre.setFloat(3, item.getService_discount_price());
				pre.setInt(4, item.getService_id());
				pre.setString(5, item.getService_modified_date());
				pre.setString(6, item.getService_expected_time());
				pre.setInt(7, item.getService_created_author_id());
				pre.setString(8, item.getService_notes());
				pre.setInt(9, item.getService_id());
				break;
			case RESTORE:
				sql.append("service_delete  = 0, service_modified_date  = ?");
				pre.setString(1, item.getService_modified_date());
				pre.setInt(2, item.getService_id());
				break;
			case TRASH:
				sql.append("service_delete  = 1, service_modified_date  = ?");
				pre.setString(1, item.getService_modified_date());
				pre.setInt(2, item.getService_id());
				break;
		}
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return false;
	}

	@Override
	public boolean delService(ServiceObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblservice WHERE service_id = ?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, item.getService_id());
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet getService(int id) {
		// TODO Auto-generated method stub
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblservice ");
		sql.append("WHERE service_id = ? AND service_delete = 0; ");		
		return this.get(sql.toString(), id);
	}

	@Override
	public ArrayList<ResultSet> getServices(Quartet<ServiceObject, Short, Byte, SERVICE_SORT_TYPE> infors) {
		// TODO Auto-generated method stub		
		ServiceObject similar = infors.getValue0();
		byte total = infors.getValue2(); // số bản ghi trong 1 trang
		int  at = (infors.getValue1()-1)*total;
		SERVICE_SORT_TYPE type = infors.getValue3();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblservice ");
		sql.append(createConditon(similar));
		
		switch (type){
			case ID:
				sql.append("ORDER BY service_id DESC ");
				break;
			case PRICE:
				sql.append("ORDER BY service_price DESC ");
				break;
			default: break;
		}
		
		sql.append("LIMIT ").append(at).append(" , ").append(total).append(";");
		sql.append("SELECT COUNT(service_id) AS total FROM tblservice ");
		sql.append(createConditon(similar));
		
		return this.getReLists(sql.toString());
	}
	
	public static String createConditon(ServiceObject item) {
		StringBuilder cond = new StringBuilder();
		if(item.isService_delete()) {
			cond.append("WHERE service_delete = 1 ");
		}else {
			cond.append("WHERE service_delete = 0 ");
		}
		
		String key = item.getService_name();
		if(key!=null && !"".equalsIgnoreCase(key)) {
			cond.append(" AND ( ");
			cond.append(" (service_name LIKE '%"+key+"%' ) OR ");
			cond.append(" (service_notes LIKE '%"+key+"%' ) ");
			cond.append(" ) ");
		}
		return cond.toString();
	}
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		ServiceImpl sm = new ServiceImpl(cp);
		ServiceObject so = new ServiceObject();
		//so.setService_id(5);
		so.setService_name("them");
		so.setService_price(100000);
		so.setService_expected_time("00:30");
//		if(sm.editService(so, SERVICE_EDIT_TYPE.RESTORE)) {
//			System.out.println("Thanf coong");
//		}else {
//			System.out.println("That bai");
//		}
		
		//them
		if(sm.addService(so)) {
			System.out.println("Thanf coong");
		}else {
			System.out.println("That bai");
		}
	}
}
