package jsoft.ads.user;
import jsoft.*;
import jsoft.library.Utilities;
import jsoft.objects.UserObject;

import java.util.*;

import org.javatuples.Pair;
import org.javatuples.Quartet;
public class UserControl {
	private UserModel um;
	
	public UserControl(ConnectionPool cp ) {
		this.um = new UserModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.um.getCP();
	}
	
	public void releaseConnection() {
		this.um.releaseConnection();
	}
	
	//*******************************************************************
	public boolean addUser(UserObject user) {
		return this.um.addUser(user);
	}
	public boolean editUser(UserObject user, USER_EDIT_TYPE type) {
		return this.um.editUser(user, type);
	}
	public boolean delUser(UserObject user) {
		return this.um.delUser(user);
	}
	//*******************************************************************
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}
	public UserObject getUserObject(String uname, String Upass) {
		return this.um.getUserObject(uname, Upass);
	}
	
	//*******************************************************************
	public ArrayList<String> viewUser(Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors){
		
		UserObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		USER_SORT_TYPE ust = infors.getValue3();
		Pair<ArrayList<UserObject>, Integer> data = this.um.getUserObjects(similar, page, total, ust);
		
		return UserLibrary.viewUser(data,infors);
	}
	
	public static void main(String[] args) {
		
		System.out.println(Utilities.decode("Đào Quỳnh Chi"));
		System.out.println(Utilities.decode("Lê Mai Anh"));
		System.out.println(Utilities.decode("Nguyễn Xuân Khải"));
		System.out.println(Utilities.decode("Lê Đình Tài"));
		System.out.println(Utilities.decode("Đào Quỳnh Chi"));
		System.out.println(Utilities.decode("Lê Mai Anh"));
		System.out.println(Utilities.decode("Nguyễn Xuân Khải"));
		System.out.println(Utilities.decode("Nguy&#7877;n V&#259;n Linh"));
		System.out.println(Utilities.decode("&#272;&igrave;nh Minh"));
		System.out.println(Utilities.decode("X&oacute;a &#272;&igrave;nh Minh"));
		System.out.println(Utilities.decode("&#272;&#7895; Ti&#7871;n Anh"));
		System.out.println(Utilities.decode("Nguy&#7877;n Lan Anh"));
		System.out.println(Utilities.decode("&#272;&#7895; Ti&#7871;n Anh"));
		System.out.println(Utilities.decode("Sua"));
		System.out.println(Utilities.decode("QQQQQQQQQ"));
		System.out.println(Utilities.decode("Nghieem Kieemr Thuw"));
		System.out.println(Utilities.decode("Th?"));
		System.out.println(Utilities.decode("AAAAA"));
		System.out.println(Utilities.decode("Th?"));
		System.out.println(Utilities.decode("AAAAAAAAAAAA"));
		System.out.println(Utilities.decode("AAAAAAAA"));
		System.out.println(Utilities.decode("Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;"));
		System.out.println(Utilities.decode("Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;"));
		System.out.println(Utilities.decode("Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;"));
		System.out.println(Utilities.decode("Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;"));
		System.out.println(Utilities.decode("M?n Anh Pháp"));
		System.out.println(Utilities.decode("M&#7851;n Anh Ph&aacute;p"));
	}
	
}
