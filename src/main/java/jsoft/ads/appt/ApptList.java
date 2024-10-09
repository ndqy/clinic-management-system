package jsoft.ads.appt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.javatuples.Triplet;
import jsoft.ConnectionPool;
import jsoft.ads.calendar.CalendarControl;
import jsoft.ads.customer.CustomerControl;
import jsoft.ads.service.ServiceControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.UserObject;
import jsoft.objects.ApptObject;
import jsoft.objects.CalendarObject;
import jsoft.objects.CustomerObject;
import jsoft.objects.ServiceObject;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/appt/list")
public class ApptList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApptList() {
        super();
        // TODO Auto-generated constractor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//Tìm thông tin đăng nhập - Kiểm tra người dùng đã tồn tại hay chưa
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if(user != null) {
			view(request,response,user);
		}else{
			response.sendRedirect("/btl/user/login");
		}
	}

	private void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		
		ApptControl ac = new ApptControl(cp);
		if(cp == null) {
			getServletContext().setAttribute("CPool", ac.getCP());
		}
		
		//Lấy từ khóa tìm kiếm
		String key = request.getParameter("key");
		String saveKey = (key!=null && !"".equalsIgnoreCase(key)) ? key.trim() : "";
		
		ApptObject similar = new ApptObject();
		//saveKey = Utilities.encodeUTF(saveKey);
		similar.setAppt_fullname(Utilities.encode(saveKey)); // Lưu trữ từ khóa tìm kiếm
		

		short page = Utilities.getShortParam(request, "page");
		if(page<1) {
			page=1;
		}
		Triplet<ApptObject, Short, Byte> infors = new Triplet<>(similar, page, (byte) 2);

		List<String> viewList = ac.viewAppt(infors);

		// Tra ve ket noi ngay sau khi su dung xong
		ac.releaseConnection();
		
		// Tham chieu tim Header
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=aplist");
		if(header != null) {
			header.include(request, response);
		}
		
		out.append("<main id=\"main\" class=\"main\">");


		RequestDispatcher error = getServletContext().getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách lịch hẹn</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/btl/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Lịch hẹn</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		

		//Hiển thị danh sách
		out.append(viewList.get(0));
		
		
		// Cấu trúc phân trang
		out.append(viewList.get(1));
		
		out.append("</div>");//card
		out.append("</div>");//card-body
		out.append("</div>"); // col-lg-12
		out.append("</div>"); // row
		

		out.append("</div>"); // card-body
		out.append("</div>"); // card
		out.append("</div>"); // col-lg-12
		out.append("</div>"); // row
		
		
		
		out.append("</section>");

		out.append("</main><!-- End #main -->");
		out.append("<script>");
		out.append("document.getElementById('checkAll').addEventListener('click', function() {");
		out.append("const collection = document.getElementsByClassName(\"checkByID\");");
		out.append("for (let i = 0; i < collection.length; i++) {");
		out.append("if (collection[i].type == \"checkbox\") {");
		out.append("collection[i].checked = this.checked;");
        out.append("}");
        out.append("}");
        out.append("});");
        out.append("</script>");
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
		request.setCharacterEncoding("UTF-8");
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		
		
		int id = jsoft.library.Utilities.getIntParam(request, "idForPost");
		
		
		// Lấy thông tin khi thêm mới
		String name = request.getParameter("txtFullName1");
		String phone = request.getParameter("txtPhone1");
		String date = request.getParameter("txtDate1");
		
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		
		if (name != null && !name.equalsIgnoreCase("") && 
				phone != null && !phone.equalsIgnoreCase("") && 
				date != null && !date.equalsIgnoreCase("")
				) {
			int gender = jsoft.library.Utilities.getIntParam(request, "slcGender1");
			String note = request.getParameter("txtNote1");
			ApptObject nAppt = new ApptObject();
			nAppt.setAppt_date(date);
			nAppt.setAppt_fullname(name);
			if(gender == 1 ) {
				nAppt.setAppt_gender(true);
			}else {
				nAppt.setAppt_gender(false);
			}
			nAppt.setAppt_phone(phone);
			nAppt.setAppt_note(note);
			
						
			ApptControl ac = new ApptControl(cp);
			
			if(cp == null) {
				getServletContext().setAttribute("CPool", ac.getCP());
			}

			boolean result = ac.addAppt(nAppt);

			// Trả về kết nối
			ac.releaseConnection();

			if (result) {
//				//Lưu lịch sử làm việc
//				LogControl lc =new LogControl(cp);
//				if(lc==null) {
//					getServletContext().setAttribute("CPool", lc.getCP());
//				}
//				LogObject lo = new LogObject();
//				lo.setLog_action((short)1);
//				lo.setLog_date(Utilities_date.getDateProfiles());
//				lo.setLog_name(Utilities.encode(name));
//				lo.setLog_position((short)5);
//				//lo.setLog_user_name("Khách hàng");
//				lo.setLog_user_permission((short)0);
//				boolean checkLog = lc.addLog(lo);
//				lc.releaseConnection();
//				if(checkLog) {
//					response.sendRedirect("/btl/home");
//				}else {
//					response.sendRedirect("/btl/appt/list?err=loadd");
//				}
				response.sendRedirect("/btl/home");
			} else {
				response.sendRedirect("/btl/home?err=add");
			}
		}
		if(user.getUser_id()>1) {
			if(id > 0) {
				// Lấy thông tin khi chỉnh sửa
				String name2 = request.getParameter("txtFullName2");
				String phone2 = request.getParameter("txtPhoneNumber2");
				String date2 = request.getParameter("txtDate2");
				String time = request.getParameter("txtTime");
				byte service = jsoft.library.Utilities.getByteParam(request, "slcService");
				byte dentist = jsoft.library.Utilities.getByteParam(request, "slcDentist");
				byte room = jsoft.library.Utilities.getByteParam(request, "slcRoom");
				
				if (name2 != null && !name2.equalsIgnoreCase("") && 
						phone2 != null && !phone2.equalsIgnoreCase("") && 
						date2 != null && !date2.equalsIgnoreCase("") && 
						time != null && !time.equalsIgnoreCase("") && 
						service>0 && dentist>0 && room>0
						) {
					String address = request.getParameter("txtAddress");
					String note = request.getParameter("txtApptNote2");
					byte gender = jsoft.library.Utilities.getByteParam(request, "slcGender2");
					
					CustomerControl  cc = new CustomerControl(cp);
					if(cc == null) {
						getServletContext().setAttribute("CPool", cc.getCP());
					}
					
					CustomerObject cus = cc.getCustomer(phone2);
					cc.releaseConnection();
					if(cus==null) {
						cus.setCustomer_address(address);
						cus.setCustomer_created_date(Utilities_date.getDate());
						cus.setCustomer_fullname(name2);
						cus.setCustomer_phone(phone2);
						//cus.setCustomer_gender((byte)gender);
						//Thêm mới khách hàng
						boolean addCus = cc.addCustomer(cus);
						if(!addCus) {
							response.sendRedirect("/btl/appt/list?add=erradd");
						}
						cus = cc.getCustomer(phone2);
						cc.releaseConnection();
					}
					
					if(cus!=null) {
						CalendarControl cal = new CalendarControl(cp);
						CalendarObject item = new CalendarObject();
						item.setCalendar_create_date(Utilities_date.getDate());
						item.setCalendar_customer_id(cus.getCustomer_id());
						item.setCalendar_dentist_id(dentist);
						item.setCalendar_notes(note);
						item.setCalendar_room_id(room);
						item.setCalendar_service_id(service);
						item.setCalendar_start_date(date2 + " "+time+":00");
						//Lấy thông tin dịch vụ
						ServiceControl sc = new ServiceControl(cp);
						ServiceObject ser = new ServiceObject();
						ser = sc.getServiceObject(service);
						sc.releaseConnection();
						item.setCalendar_end_date(ser.getService_expected_time());
						
						boolean result = cal.addCalendar(item);
						
						cal.releaseConnection();
						if(result) {
							response.sendRedirect("/btl/appt/list");
						}else {
							response.sendRedirect("/btl/appt/list?err=cladd");
						}
					}else {
						response.sendRedirect("/btl/cus/list?err=csadd");
					}
				}else {
					response.sendRedirect("/btl/cus/list?err=apnook");
				}
			}else {
				response.sendRedirect("/btl/appt/list?err=apnoid");
			}
		}else {
			response.sendRedirect("/btl/user/login?err=urnopremis");
		}
		
		//TÌM KIẾM
		String url;
		String trash = request.getParameter("trash");
		if(trash!=null) {
			url = "/btl/user/list?trash&";
		}else {
			url = "/btl/user/list?";
		}
		String key = request.getParameter("keyword");
		//System.out.println("Từ khóa UserList: "+key);
		//System.out.println("Từ khóa UserList sau khi mã hóa: "+Utilities.encode(key));
		if(key!=null && !"".equalsIgnoreCase(key)) {
			response.sendRedirect(url+"key="+Utilities.encodeURL(key));
			//System.out.println(url+"key="+Utilities.encodeURL(key));
		}
	}

}
