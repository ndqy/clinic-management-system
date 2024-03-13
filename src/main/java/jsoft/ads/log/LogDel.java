package jsoft.ads.log;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.ads.customer.CUSTOMER_EDIT_TYPE;
import jsoft.ads.customer.CustomerControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.CustomerObject;
import jsoft.objects.LogObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class LogDel
 */
@WebServlet("/log/del")
public class LogDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogDel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		
		int iddel = Utilities.getIntParam(request, "id");
		if(user!=null && iddel>0) {
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			LogControl sc = new LogControl(cp);
			if(cp==null) {
				getServletContext().setAttribute("CPool", sc.getCP());
			}
			LogObject item = new LogObject();
			item.setLog_id(iddel);
			boolean result = sc.delLog(item);
			sc.releaseConnection();
			if(result) {
				response.sendRedirect("/btl/log/list");
			}else {
				response.sendRedirect("/btl/log/list?err=notok");
			}
		}else {
			response.sendRedirect("/btl/log/list?err=notlogin");
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
