package jsoft.ads.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.library.Utilities;
import jsoft.objects.CustomerObject;
import jsoft.objects.ServiceObject;

public class CustomerImpl extends BasicImpl implements Customer {

	public CustomerImpl(ConnectionPool cp) {
		super(cp, "Customer");
		// TODO Auto-generated constructor stub
	}

	private boolean isExist(CustomerObject item) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT customer_id FROM tblcustomer WHERE customer_phone = '"+item.getCustomer_phone()+"'");// OR customer_email = '"+item.getCustomer_email()+"'
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
	public boolean addCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		if(this.isExist(item)) {
			this.editCustomer(item,CUSTOMER_EDIT_TYPE.GENERAL);
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblcustomer (");
		sql.append("customer_fullname, customer_address, customer_phone, ");
		sql.append("customer_notes, customer_created_date, ");
		sql.append("customer_username, customer_password, ");
		sql.append("customer_isactive ) ");
		sql.append("VALUES(?,?,?,?,?,?,'123456',1);");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getCustomer_fullname());
			pre.setString(2, item.getCustomer_address());
			pre.setString(3, item.getCustomer_phone());
			pre.setString(4, item.getCustomer_notes());
			pre.setString(5, item.getCustomer_created_date());
			pre.setString(6, item.getCustomer_username());
			System.out.println(sql.toString());
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
	public boolean editCustomer(CustomerObject item, CUSTOMER_EDIT_TYPE type) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql = sql.append("UPDATE tblcustomer SET ");
		switch(type) {
		case GENERAL:
			sql.append("customer_fullname = ? , customer_address = ? , customer_phone = ? , ");
			sql.append("customer_mobile = ? , customer_office = ? , customer_jobarea = ? , customer_notes = ? , ");
			sql.append("customer_email = ? ,  customer_username = ? , customer_modified_date = ? ");
			break;
		case PASS:
			sql.append("customer_password = ?, ");
			sql.append("customer_modified_date = ? ");
			break;
		case DISABLE:
			sql.append("customer_isactive = 0 , ");
			sql.append("customer_modified_date = ? ");
			break;
		case ENABLE:
			sql.append("customer_isactive = 1 , ");
			sql.append("customer_modified_date = ? ");
			break;
		}
		sql.append("WHERE customer_id = ?");
		
		// Biên dịch (phải có kết nối)
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			
			switch(type) {
			case GENERAL:				
				pre.setString(1, item.getCustomer_fullname());
				pre.setString(2, item.getCustomer_address());
				pre.setString(3, item.getCustomer_phone());
				pre.setString(4, item.getCustomer_mobile());
				pre.setString(5, item.getCustomer_office());
				pre.setString(6, item.getCustomer_jobarea());
				pre.setString(7, item.getCustomer_notes());
				pre.setString(8, item.getCustomer_email());
				pre.setString(9, item.getCustomer_username());
				pre.setString(10, item.getCustomer_modified_date());
				pre.setInt(11, item.getCustomer_id());	
				break;
			case PASS:
				pre.setString(1, item.getCustomer_modified_date());
				pre.setInt(2, item.getCustomer_id());
				break;
			case DISABLE:
				pre.setString(1, item.getCustomer_modified_date());
				pre.setInt(2, item.getCustomer_id());
				break;
			case ENABLE:
				pre.setString(1, item.getCustomer_modified_date());
				pre.setInt(2, item.getCustomer_id());
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
	public boolean delCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tblcustomer WHERE customer_id = ? ";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getCustomer_id());

			return this.del(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public ResultSet getCustomer(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblcustomer t WHERE customer_id = ?;";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getCustomer(String phone) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblcustomer t WHERE customer_phone = ?;";
		return this.gets(sql, phone);
	}
	
	@Override
	public ResultSet getCustomer(String phone, String pass) {
		// TODO Auto-generated method stub
		String sqlSelect = "SELECT * FROM tblcalendar WHERE (customer_phone = ?) AND (customer_pass = md5(?)) AND (customer_isactive = 1);";
		String sqlUpadate = "UPDATE tblcalendar SET customer_logined = customer_logined +1 WHERE (customer_phone = ?) AND (customer_pass = md5(?));";
		
		ArrayList<String> sql = new ArrayList<>();
		sql.add(sqlSelect);
		sql.add(sqlUpadate);
		
		return this.get(sql, phone, pass);
	}

	@Override
	public ArrayList<ResultSet> getCustomers(Quartet<CustomerObject, Short, Byte, CUSTOMER_SORT_TYPE> infors) {
				
		CustomerObject item = infors.getValue0();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblcustomer ");
		
		//Câu lệnh WHERE
		sql.append(createCondition(item));
		switch (infors.getValue3()) {
			case ID: 
				sql.append(" ORDER BY customer_id DESC ");
				break;
			default:
				sql.append(" ORDER BY customer_id DESC ");
				break;
				
		}
		if(infors.getValue1()!=-1) {
			byte total = infors.getValue2(); // số bản ghi trong 1 trang
			int  at = (infors.getValue1()-1)*total;
			sql.append(" LIMIT "+at+", "+infors.getValue2()+"; ");
		}else {
			sql.append("; ");
		}
		

		sql.append("SELECT COUNT(customer_id) as total FROM tblcustomer ");
		//Câu lệnh WHERE
		sql.append(createCondition(item));
		System.out.println(sql.toString());
		return this.getReLists(sql.toString());
	}
	/*public ArrayList<ResultSet> getCustomers(Quartet<CustomerObject, Short, Byte, CUSTOMER_SORT_TYPE> infors) {
				
		CustomerObject item = infors.getValue0();
		StringBuilder sql = new StringBuilder("SELECT * FROM tblcustomer ");
		
		//Câu lệnh WHERE
		sql.append(createCondition(item));
		switch (infors.getValue3()) {
			case ID: 
				sql.append(" ORDER BY customer_id DESC ");
				break;
			default:
				sql.append(" ORDER BY customer_id DESC ");
				break;
				
		}
		if(infors.getValue1()!=-1) {
			byte total = infors.getValue2(); // số bản ghi trong 1 trang
			int  at = (infors.getValue1()-1)*total;
			sql.append(" LIMIT "+at+", "+infors.getValue2()+"; ");
		}else {
			sql.append("; ");
		}
		

		sql.append("SELECT COUNT(customer_id) as total FROM tblcustomer ");
		//Câu lệnh WHERE
		sql.append(createCondition(item));
		System.out.println(sql.toString());
		return this.getReLists(sql.toString());
	}
	*/
	//Viết thêm điều kiện
	public static String createCondition(CustomerObject item) {
		StringBuilder cond = new StringBuilder();
		if(item.isCustomer_isactive()) {
			cond.append("WHERE customer_isactive = 1 ");
		}else {
			cond.append("WHERE customer_isactive = 0 ");
		}
		String key = item.getCustomer_fullname();
		if(key!=null && !"".equalsIgnoreCase(key)) {
			String decodeKey = Utilities.decode(key);
			cond.append(" AND ( ");
			cond.append("(customer_fullname LIKE '%"+key+"%') OR ");
			cond.append("(customer_address LIKE '%"+key+"%') OR ");
			cond.append("(customer_notes LIKE '%"+key+"%') OR ");
			cond.append("(customer_email LIKE '%"+key+"%') OR ");
			cond.append("(customer_username LIKE '%"+key+"%') OR ");
			
			cond.append("(customer_fullname LIKE '%"+decodeKey+"%') OR ");
			cond.append("(customer_address LIKE '%"+decodeKey+"%') OR ");
			cond.append("(customer_notes LIKE '%"+decodeKey+"%') OR ");
			cond.append("(customer_email LIKE '%"+decodeKey+"%') OR ");
			cond.append("(customer_username LIKE '%"+decodeKey+"%') ");
			cond.append(" )");
		}
		
		
		return cond.toString();
	}
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();

		Customer u = new CustomerImpl(cp);
		CustomerObject item = new CustomerObject();
		item.setCustomer_fullname("Sua");
		item.setCustomer_phone("1111111111");
		if(u.addCustomer(item)) {
			System.out.println("Thanh cong");
		}else {
			System.out.println("TB");
		}
		/*
		if(u.editCustomer(item, CUSTOMER_EDIT_TYPE.GENERAL)) {
			System.out.println("Thanh cong");
		}else {
			System.out.println("TB");
		}
		*/
		
		ResultSet rs = u.getCustomer("1111111111");
		if(rs!=null) {
			try {
				if(rs.next()) {
					item = new CustomerObject();
					item.setCustomer_id(rs.getInt("customer_id"));
					item.setCustomer_fullname(Utilities.decode(rs.getString("customer_fullname")));
					item.setCustomer_address(Utilities.decode(rs.getString("customer_address")));
					item.setCustomer_phone(rs.getString("customer_phone"));
					item.setCustomer_notes(Utilities.decode(rs.getString("customer_notes")));
					item.setCustomer_email(rs.getString("customer_email"));
					item.setCustomer_username(rs.getString("customer_username"));
					item.setCustomer_modified_date(rs.getString("customer_modified_date"));
					item.setCustomer_isactive(rs.getBoolean("customer_isactive"));
					System.out.println(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
