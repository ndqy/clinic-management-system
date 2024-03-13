package jsoft.ads.basic;

import jsoft.*;
import jsoft.ads.calendar.CALENDAR_GET_TYPE;

import java.util.*;
import java.sql.*;

public interface Basic extends ShareControl{

	public boolean add(PreparedStatement pre);
	public boolean edit(PreparedStatement pre);
	public boolean del(PreparedStatement pre);
	

	public ResultSet get(String sql, int id);
	public ResultSet get(String sql, int id, CALENDAR_GET_TYPE type);
	public ResultSet get(ArrayList<String> sql, String name, String pass);
	public ResultSet gets(String sql);
	public ResultSet gets(String sql, String date);
	
	public ArrayList<ResultSet> getReLists(String mutilSelect);
}
