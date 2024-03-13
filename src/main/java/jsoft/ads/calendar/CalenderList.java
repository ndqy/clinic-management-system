package jsoft.ads.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.UIClientPropertyKey;

import org.javatuples.Quintet;

import jsoft.ConnectionPool;
import jsoft.ads.customer.CUSTOMER_EDIT_TYPE;
import jsoft.ads.customer.CustomerControl;
import jsoft.ads.service.ServiceControl;
import jsoft.ads.user.UserControl;
import jsoft.ads.user.UserLibrary;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.CalendarObject;
import jsoft.objects.CustomerObject;
import jsoft.objects.ServiceObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class Calender
 */
@WebServlet("/calendar/list")
public class CalenderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalenderList() {
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
			if(date==null) {
				date = Utilities_date.getDate();
			}
			view(request,response,user,date);
		}else{
			response.sendRedirect("/btl/user/login");
		}
		
	}

	private void view(HttpServletRequest request, HttpServletResponse response, UserObject user, String date) throws IOException, ServletException {
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
		
		short page = Utilities.getShortParam(request, "page");
		if(page<1) {
			page=1;
		}
		
		Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors = new Quintet<>(co,page,(byte)12, CALENDAR_GET_TYPE.ID, date);
				

		/*ArrayList<CalendarObject> items = cc.getCalendars("27/10/2023");
		items.forEach(item->{
			System.out.println(item.toString());
		});*/
		// Tra ve ket noi ngay sau khi su dung xong
		cc.releaseConnection();
		
		// Tham chieu tim Header
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=cllist");
		if(header != null) {
			header.include(request, response);
		}
		
		out.append("<main id=\"main\" class=\"main\">");
		RequestDispatcher error = getServletContext().getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}
		
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách lịch khám</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/btl/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Lịch khám</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		
		//Form ẩn lấy ngày
		/*out.append("<form  action=\"\" method=\"post\">");
			if(date==null) {
				date = Utilities_date.getDate();
			}
		out.append("<input type=\"hidden\" name=\"dateForPost\" value=\""+date+"\">");
		out.append("</form>");*/
		
		
		// Form chọn ngày
		out.append("<div class=\"row mb-4 my-3\">");
			out.append("<div class=\"col-md-8\">");
			
				out.append("<form  action=\"\" method=\"get\">");
					//out.append("<label for=\"date\" class=\"col-md-2 col-form-label \">Chọn ngày: </label>");
					out.append("<div class=\"row\">");
						out.append("<div class=\"col-md-3\">");
							if(date==null) {
								date = Utilities_date.getDate();
							}
							out.append("<input type=\"date\" name=\"date\" class=\"form-control \" value=\""+date+"\">");
							out.append("</div>");
							
						out.append("<div class=\"col-md-3\">");
							out.append("<button type=\"submit\" class=\"btn btn-primary btn-md\" >Xác nhận</button>");
						out.append("</div>");
					out.append("</div>");
				out.append("</form>");
			out.append("</div>");
			out.append("<div class=\"col-md-4\">");
				out.append("<div class=\"row justify-content-md-end\">");
					out.append("<div class=\"col-md-6\">");
						out.append("<!-- Button trigger modal -->");
						out.append("<button type=\"button\" class=\"btn btn-primary btn-md \" data-bs-toggle=\"modal\" data-bs-target=\"#addCal\">");
						out.append("<i class=\"bi bi-calendar-plus\"></i> Thêm mới");
						out.append("</button>");
					out.append("</div>");
					
					out.append("<div class=\"col-md-2\">");
						out.append("<div class=\"filter\">");
						  	out.append("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
						  	out.append("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
								out.append("<li class=\"dropdown-header text-start\">");
								  	out.append("<h6>Hiển thị</h6>");
								out.append("</li>");
			
								out.append("<li><a class=\"dropdown-item\" href=\"/btl/calendar/list?view=list&date="+date+"\"><i class=\"bi bi-list\"></i> Danh sách</a></li>");
								out.append("<li><a class=\"dropdown-item\" href=\"/btl/calendar/list?view=grid&date="+date+"\"><i class=\"bi bi-grid\"></i> Lưới</a></li>");
							out.append("</ul>");
						out.append("</div>");
					out.append("</div>");
				out.append("</div>");
			out.append("</div>");//col-md-4
		out.append(cc.viewAddCalendars(infors));
		out.append("</div>");//row mb-3 my-3
		
		// Cấu trúc hiển thị
		String typeview = request.getParameter("view");
		
		List<String> viewList = cc.viewCalendars(infors,date,typeview);
		//Danh sách
		out.append(viewList.get(0));
		
		
		//Phân trang
		out.append(viewList.get(1));
		
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
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		request.setCharacterEncoding("UTF-8");

		int id = jsoft.library.Utilities.getIntParam(request, "idForPost");
		String action = request.getParameter("act");
		
		/*String date=request.getParameter("txtDate");
		if(date==null) {
			date = Utilities_date.getDate();
		}
		System.out.println("DATE: " + date);*/
		String name1 = request.getParameter("txtCusName1");
		String phone1 = request.getParameter("txtPhoneNumber1");
		String date1 = request.getParameter("txtDate1");
		String time1 = request.getParameter("txtTime1");
		int service1 = Utilities.getIntParam(request, "slcService1");
		int dentist1 = Utilities.getIntParam(request, "slcDentist1");
		int room1 = Utilities.getIntParam(request, "slcRoom1");
		if(		name1 != null && !"".equalsIgnoreCase(name1)
				&& phone1 != null && !"".equalsIgnoreCase(phone1)
				&& date1 != null && !"".equalsIgnoreCase(date1)
				&& time1 != null && !"".equalsIgnoreCase(time1)
				&& service1 > 0 && dentist1 > 0 && room1 > 0) {
			
			String note1 = request.getParameter("txtNote1");
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			CustomerControl cusc = new CustomerControl(cp);		
			if(cp == null) {
				getServletContext().setAttribute("CPool", cusc.getCP());
			}
			CustomerObject cus = new CustomerObject();	
			cus.setCustomer_fullname(Utilities.encode(name1));
			cus.setCustomer_phone(phone1);
			//Thêm mới người dùng
			cusc.addCustomer(cus);
			cusc.releaseConnection();
			
			//Lấy người dùng vừa thêm
			CustomerObject tmp = new CustomerObject();	
			tmp = cusc.getCustomer(phone1);
			cusc.releaseConnection();	
			if(tmp!=null) {
									
				CalendarControl calc = new CalendarControl(cp);	
				CalendarObject cal = new CalendarObject();
				String start_date = date1 + " "+time1+":00";
				cal.setCalendar_create_date(Utilities_date.getDate());
				cal.setCalendar_start_date(start_date);
				//Lấy thông tin dịch vụ
				ServiceControl sc = new ServiceControl(cp);
				ServiceObject ser = new ServiceObject();
				ser = sc.getServiceObject(service1);
				sc.releaseConnection();
				cal.setCalendar_end_date(ser.getService_expected_time());
				cal.setCalendar_room_id(room1);
				cal.setCalendar_customer_id(tmp.getCustomer_id());
				cal.setCalendar_service_id(service1);
				cal.setCalendar_dentist_id(dentist1);
				
				//System.out.println(date1 + " "+time1+":00");
				//System.out.println(name1);
				//System.out.println(phone1);
				//System.out.println(service1);
				//System.out.println(dentist1);
				//System.out.println(room1);
				//System.out.println("ADDTIME('"+start_date+"', '"+ser.getService_expected_time()+"')");
				
				boolean result = calc.addCalendar(cal);
				
				calc.releaseConnection();
				if(result) {
					response.sendRedirect("/btl/calendar/list?date="+date1);
				}else {
					response.sendRedirect("/btl/calendar/list?date="+date1+"&err=cladd");
				}
			}else {
				response.sendRedirect("/btl/calendar/list?date="+date1+"&err=claddcus");
			}

		}
		
		if(id>0 && action!=null && !"".equalsIgnoreCase(action)) {
			String name = request.getParameter("txtCusName");
			String phone = request.getParameter("txtPhone22222");
			String datecal = request.getParameter("txtDate");
			String time = request.getParameter("txtTime");
			int service = Utilities.getIntParam(request, "slcService");
			int dentist = Utilities.getIntParam(request, "slcDentist");
			int room = Utilities.getIntParam(request, "slcRoom");
			
			//System.out.println("NAME : " + name);
			//System.out.println("phone : " + phone);
			//System.out.println("date : " + datecal);
			//System.out.println("time : " + time);
			//System.out.println("service : " + service);
			//System.out.println("dentist : " + dentist);
			//System.out.println("room : " + room);
			if(		name != null && !"".equalsIgnoreCase(name)
					&& phone != null && !"".equalsIgnoreCase(phone)
					&& datecal != null && !"".equalsIgnoreCase(datecal)
					&& time != null && !"".equalsIgnoreCase(time)
					&& service > 0 && dentist > 0 && room > 0){
				String note = request.getParameter("txtNote");
				String email = request.getParameter("txtEmail");
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
				CustomerControl cusc = new CustomerControl(cp);		
				if(cp == null) {
					getServletContext().setAttribute("CPool", cusc.getCP());
				}
				// Cập nhật thông tin khách hàng
				CustomerObject customer = new CustomerObject();
				customer = cusc.getCustomer(phone);
				cusc.releaseConnection();
				//System.out.println(customer.getCustomer_id()+"________________CUS___________________________");
				//customer.setCustomer_id(customer.getCustomer_id());
				customer.setCustomer_fullname(Utilities.encode(name));
				customer.setCustomer_email(email);
				boolean upcus = cusc.editCustomer(customer, CUSTOMER_EDIT_TYPE.GENERAL);
				cusc.releaseConnection();
				if(!upcus) {
					response.sendRedirect("/btl/calendar/list?date="+datecal+"&err=csadd");
				}
				// Cập nhật thông tin lịch khám
				String start_date = datecal + " "+time+":00";
				CalendarControl calc = new CalendarControl(cp);
				CalendarObject cal = new CalendarObject();
				
				cal.setCalendar_id(id);
				//System.out.println(id+"____________________CALL_______________________");
				cal.setCalendar_create_date(Utilities_date.getDate());
				cal.setCalendar_start_date(start_date);
				cal.setCalendar_service_id(service);
				cal.setCalendar_customer_id(customer.getCustomer_id());
				//Lấy thông tin dịch vụ - thời gian dự kiến của dịch vụ
				ServiceControl sc = new ServiceControl(cp);
				ServiceObject ser = sc.getServiceObject(service);
				sc.releaseConnection();
				//System.out.println(id+"____________________ser.getService_expected_time()_______________________");
				cal.setCalendar_end_date(ser.getService_expected_time());
				cal.setCalendar_dentist_id(dentist);
				cal.setCalendar_room_id(room);
				cal.setCalendar_notes(Utilities.encode(note));
				boolean result = calc.editCalendar(cal);
				calc.releaseConnection();
				if(result) {
					response.sendRedirect("/btl/calendar/list?date="+datecal);
				}else {
					response.sendRedirect("/btl/calendar/list?date="+datecal+"&err=cledit");
				}
			}else {
				response.sendRedirect("/btl/calendar/list?date="+datecal+"&err=clnull");
			}
			/*else {
				System.out.println("222222222222222222222222");
			}*/
				
		}/*else {
			System.out.println("111111111111111111111");
		}*/
	}

}
