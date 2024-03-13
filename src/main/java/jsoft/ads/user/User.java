package jsoft.ads.user;

import java.sql.ResultSet;
import java.util.ArrayList;

import jsoft.ShareControl;
import jsoft.objects.UserObject;

public interface User extends ShareControl{

	// Các phương thức / chức năng cập nhật thông tin người sử dụng
	public boolean addUser(UserObject item);
	public boolean editUser(UserObject item, USER_EDIT_TYPE et);
	public boolean delUser(UserObject item);
	
	// Các phương thức / chức năng lấy thông tin người sử dụng
	public ResultSet getUser(int id);
	public ResultSet getUser(String name, String pass);
	public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total, USER_SORT_TYPE type);
}
