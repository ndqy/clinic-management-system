package jsoft.ads.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.CalendarObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class CalendarDR
 */
@WebServlet("/calendar/dr")
public class CalendarDR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarDR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		int id = Utilities.getIntParam(request, "id");
		String date = request.getParameter("date");
		if(date == null) {
			System.out.println("Không lấy được");
		}
		System.out.println("Ngày lấy ra là: " + date);
		if(user!=null && id > 0) {
			ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
			CalendarControl cc = new CalendarControl(cp);
			if(cp==null) {
				getServletContext().setAttribute("CPool", cc.getCP());
			}
			CalendarObject co = new CalendarObject();
			co.setCalendar_id(id);
			co.setCalendar_last_modified(Utilities_date.getDate());
			boolean result = cc.delCalendar(co);
			
			if(result) {
				response.sendRedirect("/btl/calendar/list?date="+date);
			}else {
				response.sendRedirect("/btl/calendar/list?date="+date+"&err=del");
			}
		}else {
			response.sendRedirect("/btl/calendar/list?date="+date+"&err=notlogin");
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
