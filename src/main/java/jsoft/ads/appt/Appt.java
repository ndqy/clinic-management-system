package jsoft.ads.appt;

import java.sql.ResultSet;
import java.util.ArrayList;

import jsoft.ShareControl;
import jsoft.objects.ApptObject;

public interface Appt extends ShareControl{

	public boolean addAppt(ApptObject item);
	public boolean delAppt(ApptObject item);
	public boolean delListAppt(ArrayList<Integer> list);
	public boolean delAllAppt();
	public ResultSet getAppt(int id);
	public ArrayList<ResultSet> getAppts(ApptObject similar, int at, byte total);
}
