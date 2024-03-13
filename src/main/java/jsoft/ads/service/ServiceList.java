package jsoft.ads.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.ads.log.LogControl;
import jsoft.ads.user.USER_EDIT_TYPE;
import jsoft.ads.user.USER_SORT_TYPE;
import jsoft.ads.user.UserControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.LogObject;
import jsoft.objects.ServiceObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class ServiceList
 */
@WebServlet("/service/list")
public class ServiceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceList() {
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
		
		ServiceControl sc = new ServiceControl(cp);
		if(cp == null) {
			getServletContext().setAttribute("CPool", sc.getCP());
		}
		
		
		
		ServiceObject similar = new ServiceObject();
		
		String trash = request.getParameter("trash");
		String title, pos;
		
		if(trash==null) {
			similar.setService_delete(false);
			title = "Danh sách Dịch vụ";
			pos = "svlist";
			
		}else {
			similar.setService_delete(true);
			title = "Danh sách Dịch vụ bị xóa";
			pos = "svtrash";
			
		}
		
		//Lấy từ khóa tìm kiếm
		String key = request.getParameter("key");
		String savekey = ((key!=null && !"".equalsIgnoreCase(key)) ? key.trim() :"" );
		
		//Lưu trữ từ khóa tìm kiếm
		similar.setService_name(Utilities.encode(savekey));
		
		short page = Utilities.getShortParam(request, "page");
		if(page<1) {
			page=1;
		}
		Quartet<ServiceObject, Short, Byte, SERVICE_SORT_TYPE> infors = new Quartet<>(similar, page, (byte) 5, SERVICE_SORT_TYPE.ID);

		List<String> viewList = sc.viewService(infors);

		// Tra ve ket noi ngay sau khi su dung xong
		sc.releaseConnection();
		
		// Tham chieu tim Header
		RequestDispatcher header = request.getRequestDispatcher("/header?pos="+pos);
		if(header != null) {
			header.include(request, response);
		}
		
		out.append("<main id=\"main\" class=\"main\">");


		RequestDispatcher error = getServletContext().getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>"+title+"</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/btl/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Dịch vụ</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		
		
		//out.append("<h5 class=\"card-title\">Example Card</h5>");
		//out.append("<p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>");
		
		if(trash==null) {
			out.append("<!-- Button trigger modal -->");
			out.append("<button type=\"button\" class=\"btn btn-primary btn-sm my-3\" data-bs-toggle=\"modal\" data-bs-target=\"#addService\">");
			out.append("<i class=\"bi bi-person-plus\"></i> Thêm mới");
			out.append("</button>");
	
			out.append("<!-- Modal -->");
			out.append("<div class=\"modal fade\" id=\"addService\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"addUserLabel\" aria-hidden=\"true\">");
			out.append("<div class=\"modal-dialog modal-lg\">");
			out.append("<form method=\"post\" action=\"/btl/service/list\" class=\"needs-validation\" novalidate>");
			out.append("<div class=\"modal-content\">");
			out.append("<div class=\"modal-header\">");
			out.append("<h1 class=\"modal-title fs-5\" id=\"addUserLabel\"><i class=\"bi bi-prescription2\"></i> Thêm mới dịch vụ</h1>");
			out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("<div class=\"modal-body\">");
			out.append("<div class=\"row mb-3\">");
			
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"user-name\" class=\"form-label\">Tên dịch vụ</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"user-serivce\" name=\"txtSerName1\"  required>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"full-name\" class=\"form-label\">Thời gian dự kiến</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"time_service\" required name=\"txtSerTime1\" required>");
			out.append("</div>");
			
			out.append("</div>");
			
			out.append("<div class=\"row mb-3\">");
			
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"price\" class=\"form-label\">Giá</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"price\" required name=\"txtPrice1\"  >");
			out.append("</div>");
			
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"discount\" class=\"form-label\">Giảm giá</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"discount\"  name=\"txtDiscount1\" >");
			out.append("</div>");
			
			out.append("</div>");
			out.append("<div class=\"row mb-3\">");
			
			out.append("<div class=\"col-lg-12\">");
			out.append("<label for=\"note\" class=\"form-label\">Ghi chú</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"note\" name=\"txtNotes1\">");
			out.append("</div>");
			
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"modal-footer\">");
			out.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-check2\"></i> Thêm mới</button>");
			out.append("<button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
			out.append("</div>");
			out.append("</div>");
			out.append("</form>");
			out.append("</div>");
			out.append("</div>");
		}
		
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
		
		//Lấy thông tin
		String name1 = request.getParameter("txtSerName1");
		String time1 = request.getParameter("txtSerTime1");
		float price1 = Utilities.getFloatParam(request, "txtPrice1");
//		System.out.println("=============" + name1);
//		System.out.println("=============" + time1);
//		System.out.println("=============" + price1);
		if( name1!=null && !"".equalsIgnoreCase(name1)
			&& time1!=null && !"".equalsIgnoreCase(time1)
			&& price1>0) {

			float discount1 = Utilities.getFloatParam(request, "txtDiscount1");
			String notes1 = request.getParameter("txtNotes1");
			ServiceObject item = new ServiceObject();
			item.setService_name(Utilities.encode(name1));
			item.setService_expected_time(time1);
			item.setService_price(price1);
			item.setService_discount_price(discount1);
			item.setService_notes(Utilities.encode(notes1));
			item.setService_created_date(Utilities_date.getDate());
			item.setService_created_author_id(user.getUser_id());
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			
			ServiceControl sc = new ServiceControl(cp);

			
			//System.out.println("=============" + discount1);
			//System.out.println("=============" + notes1);
			if(cp == null) {
				getServletContext().setAttribute("CPool", sc.getCP());
			}

			boolean result = sc.addService(item);

			// Trả về kết nối
			sc.releaseConnection();

			if (result) {
				//System.out.println("Thanf coong");
				//Lưu trữ hành động
				LogControl lc =new LogControl(cp);
				if(lc==null) {
					getServletContext().setAttribute("CPool", lc.getCP());
				}
				LogObject lo = new LogObject();
				lo.setLog_action((short)1);
				lo.setLog_date(Utilities_date.getDateProfiles());
				lo.setLog_name(Utilities.encode(name1));
				lo.setLog_position((short)3);
				lo.setLog_user_name(user.getUser_name());
				lo.setLog_user_permission(user.getUser_permission());
				boolean checkLog = lc.addLog(lo);
				lc.releaseConnection();
				if(checkLog) {
					response.sendRedirect("/btl/service/list");
				}else {
					response.sendRedirect("/btl/service/list&err=loadd");
				}
				//response.sendRedirect("/btl/service/list");
			} else {
				response.sendRedirect("/btl/service/list?err=svadd");
			}
		}
		if(id>0) {
			if(action!=null && action.equalsIgnoreCase("edit")) {
				String name = request.getParameter("txtSerName");
				String time = request.getParameter("txtSerTime");
				float price = Utilities.getFloatParam(request, "txtPrice");
				
				if( name!=null && !"".equalsIgnoreCase(name)
					&& time!=null && !"".equalsIgnoreCase(time)
					&& price>0) {
	
					float discount = Utilities.getFloatParam(request, "txtDiscount");
					String notes = request.getParameter("txtNotes");
					ServiceObject item = new ServiceObject();
					item.setService_id(id);
					item.setService_name(Utilities.encode(name));
					item.setService_expected_time(time);
					item.setService_price(price);
					item.setService_discount_price(discount);
					item.setService_notes(Utilities.encode(notes));
					item.setService_modified_date(Utilities_date.getDate());
					item.setService_created_author_id(user.getUser_id());
					ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
					
					ServiceControl sc = new ServiceControl(cp);
					
					if(cp == null) {
						getServletContext().setAttribute("CPool", sc.getCP());
					}
	
					boolean result = sc.editService(item, SERVICE_EDIT_TYPE.GENERAL);
					// Trả về kết nối
					sc.releaseConnection();
	
					if (result) {
						LogControl lc =new LogControl(cp);
						if(lc==null) {
							getServletContext().setAttribute("CPool", lc.getCP());
						}
						LogObject lo = new LogObject();
						lo.setLog_action((short)2);
						lo.setLog_date(Utilities_date.getDateProfiles());
						lo.setLog_name(Utilities.encode(name));
						lo.setLog_position((short)3);
						lo.setLog_user_name(user.getUser_name());
						lo.setLog_user_permission(user.getUser_permission());
						boolean checkLog = lc.addLog(lo);
						lc.releaseConnection();
						if(checkLog) {
							response.sendRedirect("/btl/service/list");
						}else {
							response.sendRedirect("/btl/service/list&err=loadd");
						}
						//response.sendRedirect("/btl/service/list");
					} else {
						response.sendRedirect("/btl/service/list?err=svedit");
					}
				}else {
					response.sendRedirect("/btl/service/list?err=svnull");
				}
			}
			
		}
		
		String url;
		String trash = request.getParameter("trash");
		if(trash!=null) {
			url = "/btl/service/list?trash&";
		}else {
			url = "/btl/service/list?";
		}
		//Lấy từ khóa tìm kiếm
		String key =request.getParameter("keyword");
		if(key!=null && !"".equalsIgnoreCase(key)) {
			response.sendRedirect(url+"key="+Utilities.encodeURL(key));
		}
	}

}
