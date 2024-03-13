package jsoft.ads.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Quintet;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.CalendarObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class CalendarChart
 */
@WebServlet("/calendar/chart")
public class CalendarChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarChart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Tìm thông tin đăng nhập
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if(user != null) {
			String date=request.getParameter("date");
			if(date==null || date.equalsIgnoreCase("")) {
				date = Utilities_date.getDate();
			}
			view(request,response,user,date);
		}else{
			response.sendRedirect("/btl/user/login");
		}

	}

	private void view(HttpServletRequest request, HttpServletResponse response, UserObject user, String date) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		


		CalendarControl cc = new CalendarControl(cp);
		if(cp == null) {
			getServletContext().setAttribute("CPool", cc.getCP());
		}
		
		CalendarObject co = new CalendarObject();
		co.setCalendar_delete(false);

		
		Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors = new Quintet<>(co,null,null, CALENDAR_GET_TYPE.ID, date);
				


		// Tra ve ket noi ngay sau khi su dung xong
		cc.releaseConnection();
		
		// Tham chieu tim Header
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=clchart");
		if(header != null) {
			header.include(request, response);
		}
		
		out.append("<main id=\"main\" class=\"main\">");
		
		
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Biểu đồ đăng ký lịch khám</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/btl/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Lịch khám</li>");
		out.append("<li class=\"breadcrumb-item active\">Biểu đồ</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		
		
		// Form chọn ngày
		
		out.append("<div class=\"row mb-3 my-3\">");
		out.append("<form class=\"col-md-10 d-grid gap-2 d-md-flex justify-content-start\" action=\"\" method=\"get\">");
			//out.append("<label for=\"date\" class=\"col-md-2 col-form-label \">Chọn ngày: </label>");
			out.append("<div class=\"col-md-3\">");
			if(date==null) {
				date = Utilities_date.getDate();
			}
				out.append("<input type=\"date\" name=\"date\" class=\"form-control \" value=\""+date+"\">");
			out.append("</div>");
			//out.append("<div class=\"col-md-2\">");
				out.append("<input type=\"submit\" class=\"btn btn-primary btn-sm\" value=\"Xác nhận\">");
			//out.append("</div>");
		out.append("</form>");
		out.append("<div class=\"col-md-2 d-grid gap-2 d-md-flex justify-content-md-end\">");
		out.append("<!-- Button trigger modal -->");
		out.append("<button type=\"button\" class=\"btn btn-primary btn-sm \" data-bs-toggle=\"modal\" data-bs-target=\"#addCal\">");
		out.append("<i class=\"bi bi-person-plus\"></i> Thêm mới");
		out.append("</button>");
		out.append("</div>");
	out.append("</div>");
	out.append(cc.viewAddCalendars(infors));
		
	
		
		List<String> viewList = cc.viewCalendars(infors,date,null);
		//Vẽ biểu đồ
		out.append(viewList.get(1));
		/*
		Danh sách
		out.append(viewList.get(0));
		
		
		//Phân trang
		out.append(viewList.get(1));*/
		
		/*
		//Lấy danh sách
		cc.viewCalendars(infors).toString();
		
		//Cấu trúc phân trang
		String url = "/btl/calendar/list?date="+date;
		if(co.isCalendar_delete()) {
			url += "&trash&";
		}else {
			url += "&";
		}
		String paging = UserLibrary.getPagination(url, page, page, totalperpage).toString();
		out.append(paging)*/
		
		out.append("</div>"); // card-body
		out.append("</div>"); // card
		out.append("</div>");//col-lg-12
		out.append("</div>");//row
		
		out.append("</section>");

		out.append("</main><!-- End #main -->");

		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer != null) {
			footer.include(request, response);
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
