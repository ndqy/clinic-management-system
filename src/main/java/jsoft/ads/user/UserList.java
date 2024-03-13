package jsoft.ads.user;

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
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.LogObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/user/list")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        // TODO Auto-generated constructor stub
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
		
		UserControl uc = new UserControl(cp);
		if(cp == null) {
			getServletContext().setAttribute("CPool", uc.getCP());
		}
		
		//Lấy từ khóa tìm kiếm
		String key = request.getParameter("key");
		String saveKey = (key!=null && !"".equalsIgnoreCase(key)) ? key.trim() : "";
		
		//System.out.println("UserList VIEW key = : " + key);
		//System.out.println("UserList VIEW savekey = : " + saveKey);
		//System.out.println("UserList VIEW savekey mã hóa = " + Utilities.encode(saveKey));
		UserObject similar = new UserObject();
		similar.setUser_id(user.getUser_id());
		similar.setUser_permission(user.getUser_permission());
		//saveKey = Utilities.encodeUTF(saveKey);
		similar.setUser_fullname(Utilities.encode(saveKey)); // Lưu trữ từ khóa tìm kiếm
		
		String trash = request.getParameter("trash");
		String title, pos;
		
		if(trash==null) {
			similar.setUser_deleted(false);
			title = "Danh sách Người sử dụng";
			pos = "urlist";
		}else {
			similar.setUser_deleted(true);
			title = "Danh sách tài khoản bị xóa";
			pos = "urtrash";
		}
		
		short page = Utilities.getShortParam(request, "page");
		if(page<1) {
			page=1;
		}
		Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors = new Quartet<>(similar, page, (byte) 5, USER_SORT_TYPE.ID);

		List<String> viewList = uc.viewUser(infors);

		// Tra ve ket noi ngay sau khi su dung xong
		uc.releaseConnection();
		
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
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
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
			out.append("<button type=\"button\" class=\"btn btn-primary btn-sm my-3\" data-bs-toggle=\"modal\" data-bs-target=\"#addUser\">");
			out.append("<i class=\"bi bi-person-plus\"></i> Thêm mới");
			out.append("</button>");
	
			out.append("<!-- Modal -->");
			out.append("<div class=\"modal fade\" id=\"addUser\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"addUserLabel\" aria-hidden=\"true\">");
			out.append("<div class=\"modal-dialog modal-lg\">");
			out.append("<form method=\"post\" action=\"/btl/user/list\" class=\"needs-validation\" novalidate>");
			out.append("<div class=\"modal-content\">");
			out.append("<div class=\"modal-header\">");
			out.append("<h1 class=\"modal-title fs-5\" id=\"addUserLabel\"><i class=\"bi bi-person-add\"></i> Thêm mới người sử dụng</h1>");
			out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("<div class=\"modal-body\">");
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"user-name\" class=\"form-label\">Tên tài khoản</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"user-name\" name=\"txtUserName1\" required>");
			out.append("<div class=\"invalid-feedback\">");
			out.append("Hãy nhập vào tên tài khoản");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"full-name\" class=\"form-label\">Họ tên</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"full-name\" name=\"txtFullName1\">");
			out.append("<div class=\"invalid-feedback\">");
			out.append("Hãy nhập vào họ tên của người dùng");
			out.append("</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"password\" class=\"form-label\">Mật khẩu</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"password\" required name=\"txtPassword1\">");
			out.append("<div class=\"invalid-feedback\">");
			out.append("Hãy nhập vào mật khẩu cho tài khoản");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"confirm-password\" class=\"form-label\">Nhập lại mật khẩu</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"confirm-password\" required name=\"txtConfirmPassword1\">");
			out.append("<div class=\"invalid-feedback\">");
			out.append("Hãy nhập lại mật khẩu cho tài khoản");
			out.append("</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"email-address\" class=\"form-label\">Hộp thư</label>");
			out.append("<input type=\"email\" class=\"form-control\" id=\"email-address\" required name=\"txtEmailAddress1\">");
			out.append("<div class=\"invalid-feedback\">");
			out.append("Hãy nhập vào tên địa chỉ hộp thư");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" required name=\"txtPhoneNumber1\">");
			out.append("<div class=\"invalid-feedback\">");
			out.append("Hãy nhập số điện thoại cho tài khoản");
			out.append("</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"user-permission\" class=\"form-label\">Quyền thực thi</label>");
			out.append("<select class=\"form-select\" required id=\"user-permission\" name=\"slcPermis1\">");
			out.append("<option value=\"\" selected>--- Chọn ---</option>");
			out.append("<option value=\"1\">Nha sĩ</option>");
			if(similar.getUser_permission() == 3 ) {
				out.append("<option value=\"2\">Quản trị</option>");
			}
			if(similar.getUser_permission() > 3 ) {
				out.append("<option value=\"2\">Quản trị</option>");
				out.append("<option value=\"3\">Quản trị cấp cao</option>");
			}
			out.append("</select>");
			out.append("<div class=\"invalid-feedback\">");
			out.append("Hãy lựa chọn quyền thực thi");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"col-lg-6\">");
			out.append("</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("<div class=\"modal-footer\">");
			out.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-person-plus\"></i> Thêm mới</button>");
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
		
		//Vẽ biểu đồ
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");

		out.append(viewList.get(2));
		
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
		request.setCharacterEncoding("UTF-8");
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		
		
		int id = jsoft.library.Utilities.getIntParam(request, "idForPost");
		String action = request.getParameter("act");
		
		// Lấy thông tin khi thêm mới
		String name1 = request.getParameter("txtUserName1");
		String pass11 = request.getParameter("txtPassword1");
		String pass12 = request.getParameter("txtConfirmPassword1");
		String email1 = request.getParameter("txtEmailAddress1");
		String phone1 = request.getParameter("txtPhoneNumber1");
		byte permis1 = jsoft.library.Utilities.getByteParam(request, "slcPermis1");
		
		if (name1 != null && !name1.equalsIgnoreCase("") && 
				jsoft.library.Utilities_text.checkvalidPass(pass11, pass12) && 
				email1 != null && !email1.equalsIgnoreCase("") && 
				phone1 != null && !phone1.equalsIgnoreCase("")&& 
				permis1 > 0) {
			String fullname1 = request.getParameter("txtFullName1");
			UserObject nUser = new UserObject();
			nUser.setUser_name(name1);
			nUser.setUser_fullname(Utilities.encode(fullname1));
			nUser.setUser_pass(pass11);
			nUser.setUser_email(email1);
			nUser.setUser_mobilephone(phone1);
			nUser.setUser_created_date(jsoft.library.Utilities_date.getDate());
			nUser.setUser_permission(permis1);
			
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
						
			UserControl uc = new UserControl(cp);
			
			if(cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			boolean result = uc.addUser(nUser);

			// Trả về kết nối
			uc.releaseConnection();

			if (result) {
				//Lưu lịch sử làm việc
				LogControl lc =new LogControl(cp);
				if(lc==null) {
					getServletContext().setAttribute("CPool", lc.getCP());
				}
				LogObject lo = new LogObject();
				lo.setLog_action((short)1);
				lo.setLog_date(Utilities_date.getDateProfiles());
				lo.setLog_name(Utilities.encode(fullname1));
				lo.setLog_position((short)1);
				lo.setLog_user_name(user.getUser_name());
				lo.setLog_user_permission(user.getUser_permission());
				boolean checkLog = lc.addLog(lo);
				lc.releaseConnection();
				if(checkLog) {
					response.sendRedirect("/btl/user/list");
				}else {
					response.sendRedirect("/btl/user/list?err=loadd");
				}
				//response.sendRedirect("/btl/user/list");
			} else {
				response.sendRedirect("/btl/user/list?err=uradd");
			}
		}
		if(id>0) {
			
			if(action!=null && action.equalsIgnoreCase("edit")) {
				// Lấy thông tin khi chỉnh sửa
				String name2 = request.getParameter("txtUserName2");
				String pass21 = request.getParameter("txtPassword2");
				String pass22 = request.getParameter("txtConfirmPassword2");
				String email2 = request.getParameter("txtEmailAddress2");
				String phone2 = request.getParameter("txtPhoneNumber2");
				byte permis2 = jsoft.library.Utilities.getByteParam(request, "slcPermis2");


				if(email2 !=null && !email2.equalsIgnoreCase("") &&
						phone2!=null && !phone2.equalsIgnoreCase("") && permis2>0) {
					String fullname2 = request.getParameter("txtFullName2");
					//fullname2 = Utilities.encode(fullname2);
					String address2 = request.getParameter("txtAddress2");
					UserObject nUser = new UserObject();
					nUser.setUser_id(id);
					nUser.setUser_fullname(Utilities.encode(fullname2));
					nUser.setUser_email(email2);
					nUser.setUser_mobilephone(phone2);
					nUser.setUser_last_modified(jsoft.library.Utilities_date.getDate());
					nUser.setUser_permission(permis2);
					nUser.setUser_address(Utilities.encode(address2));
					ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
					
					UserControl uc = new UserControl(cp);
					if(cp==null) {
						getServletContext().setAttribute("CPool", uc.getCP());
					}
					
					boolean result = uc.editUser(nUser, USER_EDIT_TYPE.GENERAL);
					// Trả về kết nối
					uc.releaseConnection();
					if(result) {
						//Lưu trữ hành động
						LogControl lc = new LogControl(cp);
						if(lc==null) {
							getServletContext().setAttribute("CPool", lc.getCP());
						}
						LogObject lo = new LogObject();
						lo.setLog_action((short)2); // Cập nhật
						lo.setLog_date(Utilities_date.getDateProfiles());
						lo.setLog_name(Utilities.encode(fullname2));
						lo.setLog_position((short)1); //tại modul người dùng
						lo.setLog_user_name(user.getUser_name()); //người thực hiện
						lo.setLog_user_permission(user.getUser_permission());
						boolean checkLog = lc.addLog(lo);
						lc.releaseConnection();
						if(checkLog) {
							response.sendRedirect("/btl/user/list");
						}else {
							response.sendRedirect("/btl/user/list?err=loadd");
						}
						//response.sendRedirect("/btl/user/list");
					}else {
						
						response.sendRedirect("/btl/user/list?err=uredit");
					}
				}else {
					response.sendRedirect("/btl/user/list?err=urnotoke");
				}
			}
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
