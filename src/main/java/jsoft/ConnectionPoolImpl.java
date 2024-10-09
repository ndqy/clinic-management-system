package jsoft;
import java.sql.*;
import java.util.*;

public class ConnectionPoolImpl implements ConnectionPool{
	
	private String drive;
	private String url;
	private String username;
	private String userpass;
	private Stack<Connection> pool;
	
	public ConnectionPoolImpl() {
		this.drive = "com.mysql.jdbc.Driver";
		this.url = "jdbc:MySQL://127.0.0.1:3306/btl?allowMultiQueries=true";
		this.username = "root";
		this.userpass = "0000";
		this.loadDriver();
		//Khởi tạo bộ nhớ đối tượng lưu trữ
		this.pool = new Stack<>();
	}
	
	private void loadDriver() {
		try {
			Class.forName(drive);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		if(this.pool.isEmpty()) {
			System.out.println(objectName + " have created a new Connection.");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
		}else {
			System.out.println(objectName + " have popped the Connection");
			return this.pool.pop();
		}
	}
	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(objectName + " have pushed the Connection.");
		this.pool.push(con);
	}
	
	protected void finalize() throws Throwable{
		this.pool.clear();
		this.pool = null;
		
		System.gc();
		System.out.println("ConnectionPool is closed.");
	}
}
