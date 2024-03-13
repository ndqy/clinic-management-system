package jsoft.ads.user;
import java.sql.*;
import java.util.*;

import jsoft.*;
import jsoft.ads.basic.BasicImpl;
import jsoft.library.Utilities;
import jsoft.objects.UserObject;

public class UserImpl extends BasicImpl implements User {

	public UserImpl(ConnectionPool cp) {
		super(cp, "User");
		// TODO Auto-generated constructor stub
	}
	// Kiểm tra xem người dùng đã tồn tại chưa
	private boolean isExisting(UserObject item) {
		boolean flag = false;
		String sql = "SELECT user_id FROM tbluser WHERE user_name = '" + item.getUser_name() + "' ;";
		ResultSet rs = this.gets(sql);
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
		return flag ;
	}
	@Override
	public boolean addUser(UserObject item) {
		// TODO Auto-generated method stub
		if (this.isExisting(item)) {
			return false;
		}
		
		String sql = "INSERT INTO tbluser(";
		sql += "user_name, user_pass, user_fullname, user_birthday,";
		sql += "user_mobilephone, user_homephone, user_officephone, user_email, user_address,";
		sql += "user_jobarea, user_job, user_position, user_applyyear, user_permission,";
		sql += "user_notes, user_roles, user_logined, user_created_date, user_last_modified,";
		sql += "user_last_logined, user_parent_id, user_actions";
		sql += ") ";
		sql += "VALUES(?, md5(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// Biên dịch (phải có kết nối)
				try {
					PreparedStatement pre = this.con.prepareStatement(sql);
					pre.setString(1, item.getUser_name());
					pre.setString(2, item.getUser_pass());
					pre.setString(3, item.getUser_fullname());
					pre.setString(4, item.getUser_birthday());
					pre.setString(5, item.getUser_mobilephone());
					pre.setString(6, item.getUser_homephone());
					pre.setString(7, item.getUser_officephone());
					pre.setString(8, item.getUser_email());
					pre.setString(9, item.getUser_address());
					pre.setString(10, item.getUser_jobarea());
					pre.setString(11, item.getUser_job());
					pre.setString(12, item.getUser_position());
					pre.setShort(13, item.getUser_applyyear());
					pre.setByte(14, item.getUser_permission());
					pre.setString(15, item.getUser_notes());
					pre.setString(16, item.getUser_roles());
					pre.setShort(17, item.getUser_logined());
					pre.setString(18, item.getUser_created_date());
					pre.setString(19, item.getUser_last_modified());
					pre.setString(20, item.getUser_last_logined());
					pre.setInt(21, item.getUser_parent_id());
					pre.setByte(22, item.getUser_actions());
					
					
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
	public boolean editUser(UserObject item, USER_EDIT_TYPE et) {
		// TODO Auto-generated method stub
		String sql = " UPDATE tbluser SET ";
		switch(et) {
			case GENERAL:
				
				sql += "user_fullname = ?, user_birthday = ?, user_mobilephone = ?, ";
				sql += "user_homephone = ?, user_officephone = ?, user_email = ?, user_address = ?, ";
				sql += "user_jobarea = ?, user_job = ?, user_position = ?, user_applyyear = ?, ";
				sql += "user_notes = ?, user_last_modified = ?, ";
				sql += "user_actions = ?, user_permission = ? ";
				break;
			/*case SETTING: 
				sql += " user_permission = ? "; //user_roles = ?";
				break;*/
			case PASS:
				sql += " user_pass=md5(?) ";
				break;
			case TRASH: 
				sql += " user_deleted=1, user_last_modified = ? ";
				break;
			case RESTORE:
				sql += " user_deleted=0, user_last_modified = ? ";
				break;	
			default:
				System.out.println("ERROR");
				break;
		}
		sql += " WHERE user_id = ? ";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			switch (et){
				case GENERAL:
					pre.setString(1, item.getUser_fullname());
					pre.setString(2, item.getUser_birthday());
					pre.setString(3, item.getUser_mobilephone());
					pre.setString(4, item.getUser_homephone());
					pre.setString(5, item.getUser_officephone());
					pre.setString(6, item.getUser_email());
					pre.setString(7, item.getUser_address());
					pre.setString(8, item.getUser_jobarea());
					pre.setString(9, item.getUser_job());
					pre.setString(10, item.getUser_position());
					pre.setShort(11, item.getUser_applyyear());
					pre.setString(12, item.getUser_notes());
					pre.setString(13, item.getUser_last_modified());
					pre.setByte(14, item.getUser_actions());
					pre.setByte(15, item.getUser_permission());
					pre.setInt(16, item.getUser_id());
					break;
				/*case SETTING:
					pre.setByte(1, item.getUser_permission());
					pre.setInt(2, item.getUser_id());	
					break;*/
				case PASS:
					pre.setString(1, item.getUser_pass());
					pre.setInt(2, item.getUser_id());
					break;
				case TRASH:
					pre.setString(1, item.getUser_last_modified());
					pre.setInt(2, item.getUser_id());
					break;
				case RESTORE:
					pre.setString(1, item.getUser_last_modified());
					pre.setInt(2, item.getUser_id());
					break;
			}
			return this.edit(pre);
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
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tbluser WHERE user_id = ?;";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getUser_id());
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
	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbluser WHERE (user_id = ?) AND (user_deleted=0);";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String name, String pass) {
		// TODO Auto-generated method stub
		//Phương thức đăng nhập
		//Kiểm tra tài khoản
		String sqlSelect = "SELECT * FROM tbluser WHERE (user_name = ?) AND (user_pass = md5(?)) AND (user_deleted=0);";
		//Cập nhật số lần đăng nhập
		String sqlUpadate = "UPDATE tbluser SET user_logined = user_logined +1 WHERE (user_name = ?) AND (user_pass = md5(?));";
		
		ArrayList<String> sql = new ArrayList<>();
		sql.add(sqlSelect);
		sql.add(sqlUpadate);
		
		return this.get(sql, name, pass);
	}

	@Override
	public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total, USER_SORT_TYPE type) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbluser ";// WHERE user_delete " + similar.getUser_id();
		sql += creatCondition(similar);
		switch (type) {
			case ID: 
				sql += " ORDER BY user_id DESC ";
				break;
			case FULLNAME: 
				sql += " ORDER BY user_fullname DESC ";
				break;
			case UPDATE:
				sql += "ORDER BY user_last_modified DESC ";
				break;
			default:
				sql += "ORDER BY user_id DESC ";
				break;
				
		}
		sql += " LIMIT "+at+", "+total+"; ";
		
		StringBuilder multiSelect = new StringBuilder();
		multiSelect.append(sql);
		//System.out.println(sql);
		multiSelect.append("SELECT COUNT(user_id) as total FROM tbluser ").append(creatCondition(similar));
		//System.out.println(multiSelect);
		return this.getReLists(multiSelect.toString());
	}
	
	private String creatCondition(UserObject similar) {
		StringBuilder cond = new StringBuilder();
		
		if(similar !=null) {
			byte permis = similar.getUser_permission();
			cond.append(" ( user_permission < " ).append(permis).append(" ) ");
			if(permis < 3) {
				int id = similar.getUser_id();
				cond.append(" AND ((user_parent_id = ").append(id).append(" )")
				.append("OR (user_id = ").append(id).append(" ))");
			}
		}
		
		// Lấy từ khóa tìm kiếm
		//System.out.println("________________________________________________");
		String key = similar.getUser_fullname();
		//System.out.println("Từ khóa ban đầu: " +key);
		String encodeKey = Utilities.decode(key);
		//System.out.println("Từ khóa sau khi giải mã: " + encodeKey);
		//System.out.println("________________________END IMPL________________________");
		if(key!=null && !"".equalsIgnoreCase(key)) {
			cond.append(" AND (");
			cond.append(" (user_name LIKE '%"+key+"%') OR ");
			cond.append(" (user_fullname LIKE '%"+key+"%') OR ");
			cond.append(" (user_email LIKE '%"+key+"%') OR ");
			
			cond.append(" (user_name LIKE '%"+encodeKey+"%') OR ");
			cond.append(" (user_fullname LIKE '%"+encodeKey+"%') OR ");
			cond.append(" (user_email LIKE '%"+encodeKey+"%') ");
			cond.append(" )");
		}
		if(similar.isUser_deleted()) {
			cond.append(" AND (user_deleted = 1) ");
		}else {
			cond.append(" AND (user_deleted = 0) ");
		}
		if(cond.length()>0) {
			cond.insert(0, "WHERE");
		}
		
		return cond.toString();
	}
}
