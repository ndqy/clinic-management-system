package jsoft.ads.customer;

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
import jsoft.ads.service.SERVICE_SORT_TYPE;
import jsoft.ads.service.ServiceControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.CustomerObject;
import jsoft.objects.LogObject;
import jsoft.objects.ServiceObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class CustomerList
 */
@WebServlet("/customer/list")
public class CustomerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		if(user != null) {
			view(request,response,user);
		}else{
			response.sendRedirect("/btl/user/login");
		}
	}

	private void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		
		CustomerControl sc = new CustomerControl(cp);
		if(cp == null) {
			getServletContext().setAttribute("CPool", sc.getCP());
		}
		CustomerObject similar = new CustomerObject();
		
		String dis = request.getParameter("dis");
		String title, pos;
		
		if(dis==null) {
			similar.setCustomer_isactive(true);
			title = "Danh sách Tài khoản khách hàng";
			pos = "cslist";
			
		}else {
			similar.setCustomer_isactive(false);
			title = "Danh sách Tài khoản khách hàng bị vô hiệu hóa";
			pos = "csdis";
			
		}
		
		//Lấy từ khóa tìm kiếm
		String key = request.getParameter("key");
		String saveKey = ((key!=null && !"".equalsIgnoreCase(key))? key.trim() : "");
		
		similar.setCustomer_fullname(Utilities.encode(saveKey));
		
		/*short page = Utilities.getShortParam(request, "page");
		if(page<1) {
			page=1;
		}*/
		Quartet<CustomerObject, Short, Byte, CUSTOMER_SORT_TYPE> infors = new Quartet<>(similar,(short)-1, null, CUSTOMER_SORT_TYPE.ID);
		List<String> viewList = sc.viewCustomer(infors);
		
		
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
		out.append("<li class=\"breadcrumb-item\">Khách hàng</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		
		if(dis==null) {
			out.append("<!-- Button trigger modal -->");
			out.append("<button type=\"button\" class=\"btn btn-primary btn-sm my-3\" data-bs-toggle=\"modal\" data-bs-target=\"#addCus\">");
			out.append("<i class=\"bi bi-person-plus\"></i> Thêm mới");
			out.append("</button>");
			out.append(CustomerLibrary.viewAddCustomer());
		}

		
		//Hiển thị danh sách
		out.append(viewList.get(0));
		
		
		// Cấu trúc phân trang
		//out.append(viewList.get(1));
		
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
		String fullname1 = request.getParameter("txtFullName1");
		String phone1 = request.getParameter("txtPhone1");
		if(	fullname1!=null && !"".equalsIgnoreCase(fullname1)
			&& phone1!=null && !"".equalsIgnoreCase(phone1)) {
			String username1 = request.getParameter("txtUserName1");
			String address1 = request.getParameter("txtAddress1");
			String email1 = request.getParameter("txtEmail1");
			String job1 = request.getParameter("txtJob1");
			String note1 = request.getParameter("txtNote1");
			
			CustomerObject item = new CustomerObject();
			item.setCustomer_fullname(Utilities.encode(fullname1));
			item.setCustomer_phone(phone1);
			item.setCustomer_username(username1);
			item.setCustomer_address(Utilities.encode(address1));
			item.setCustomer_email(email1);
			item.setCustomer_jobarea(Utilities.encode(job1));
			item.setCustomer_notes(Utilities.encode(note1));
			item.setCustomer_created_date(Utilities_date.getDate());
			item.setCustomer_modified_date(Utilities_date.getDate());
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			CustomerControl cc = new CustomerControl(cp);

			
			//System.out.println("=============" + discount1);
			//System.out.println("=============" + notes1);
			if(cp == null) {
				getServletContext().setAttribute("CPool", cc.getCP());
			}

			boolean result = cc.addCustomer(item);

			// Trả về kết nối
			cc.releaseConnection();

			if (result) {
				//System.out.println("Thanf coong");
				//if(result) {
					//Lưu trữ hành động
					LogControl lc =new LogControl(cp);
					if(lc==null) {
						getServletContext().setAttribute("CPool", lc.getCP());
					}
					LogObject lo = new LogObject();
					lo.setLog_action((short)1);
					lo.setLog_date(Utilities_date.getDateProfiles());
					lo.setLog_name(Utilities.encode(fullname1));
					lo.setLog_position((short)4);
					lo.setLog_user_name(user.getUser_name());
					lo.setLog_user_permission(user.getUser_permission());
					boolean checkLog = lc.addLog(lo);
					lc.releaseConnection();
					if(checkLog) {
						response.sendRedirect("/btl/customer/list");
					}else {
						response.sendRedirect("/btl/customer/list?err=loadd");
					}
				//response.sendRedirect("/btl/customer/list");
			} else {
				response.sendRedirect("/btl/customer/list?err=csadd");
			}
		}
		if(id > 0 && action!=null && action.equalsIgnoreCase("edit"))	{
			String fullname = request.getParameter("txtFullName");
			String phone = request.getParameter("txtPhone");
			if(	fullname!=null && !"".equalsIgnoreCase(fullname)
				&& phone!=null && !"".equalsIgnoreCase(phone)) {
				String username = request.getParameter("txtUserName");
				String address = request.getParameter("txtAddress");
				String email = request.getParameter("txtEmail");
				String job = request.getParameter("txtJob");
				String note = request.getParameter("txtNote");
				
				CustomerObject item = new CustomerObject();
				item.setCustomer_id(id);
				item.setCustomer_fullname(Utilities.encode(fullname));
				item.setCustomer_phone(phone);
				item.setCustomer_username(username);
				item.setCustomer_address(Utilities.encode(address));
				item.setCustomer_email(email);
				item.setCustomer_jobarea(Utilities.encode(job));
				item.setCustomer_notes(Utilities.encode(note));
				item.setCustomer_modified_date(Utilities_date.getDate());
				
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
				CustomerControl cc = new CustomerControl(cp);

				
				//System.out.println("=============" + discount1);
				//System.out.println("=============" + notes1);
				if(cp == null) {
					getServletContext().setAttribute("CPool", cc.getCP());
				}

				boolean result = cc.editCustomer(item, CUSTOMER_EDIT_TYPE.GENERAL);

				// Trả về kết nối
				cc.releaseConnection();

				if (result) {
					//System.out.println("Thanf coong");
					//Lưu trữ hành động
					LogControl lc =new LogControl(cp);
					if(lc==null) {
						getServletContext().setAttribute("CPool", lc.getCP());
					}
					LogObject lo = new LogObject();
					lo.setLog_action((short)2);
					lo.setLog_date(Utilities_date.getDateProfiles());
					lo.setLog_name(Utilities.encode(fullname));
					lo.setLog_position((short)4);
					lo.setLog_user_name(user.getUser_name());
					lo.setLog_user_permission(user.getUser_permission());
					boolean checkLog = lc.addLog(lo);
					lc.releaseConnection();
					if(checkLog) {
						response.sendRedirect("/btl/customer/list");
					}else {
						response.sendRedirect("/btl/customer/list?err=loadd");
					}
					//response.sendRedirect("/btl/customer/list");
				} else {
					response.sendRedirect("/btl/customer/list?err=csedit");
				}
			}else {
				response.sendRedirect("/btl/customer/list?err=csnull");
			}
		}
		String url;
		String trash = request.getParameter("dis");
		if(trash!=null) {
			url = "/btl/customer/list?dis&";
		}else {
			url = "/btl/customer/list?";
		}
		//Lấy từ khóa tìm kiếm
		String key =request.getParameter("keyword");
		if(key!=null && !"".equalsIgnoreCase(key)) {
			response.sendRedirect(url+"key="+Utilities.encodeURL(key));
		}
		
	}

}
