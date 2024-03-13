package jsoft.ads.log;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.customer.CUSTOMER_SORT_TYPE;
import jsoft.objects.CustomerObject;
import jsoft.objects.LogObject;

public class LogControl {
	private LogModel lm;
	public LogControl(ConnectionPool cp ) {
		this.lm = new LogModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.lm.getCP();
	}
	public void releaseConnection() {
		this.lm.releaseConnection();
	}
	
	//================================================
	public boolean addLog(LogObject item) {
		return this.lm.addLog(item);
	}
	public boolean delLog(LogObject item) {
		return this.lm.delLog(item);
	}
	//================================================
	public LogObject getLog(int id) {
		return this.lm.getLog(id);
	}
	
	public String viewLog() {
		ArrayList<LogObject> items = this.lm.getLogs();
		return LogLibrary.viewLog(items);
	}
	
	public ArrayList<String> viewLogForUser(Triplet<LogObject, Short, Byte> infors) {
		LogObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		Pair<ArrayList<LogObject>, Integer> datas = this.lm.getLogsForUser(infors);
		return LogLibrary.viewLogForUser(datas , infors);
	}

}
