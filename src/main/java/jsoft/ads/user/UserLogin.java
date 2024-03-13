package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsoft.ConnectionPool;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/user/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Định nghĩa kiểu trả về trình khách
	public static final String CONTENT_TYPE = "text/html; charset = utf-8";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType(CONTENT_TYPE);
		
		PrintWriter out = response.getWriter();
		
		out.append("<!-- doctype html -->");
		out.append("<html lang=\"en\">");
		  out.append("<head>");
		    out.append("<meta charset=\"utf-8\">");
		    out.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		    out.append("<title>Login</title>");
		    out.append("<link href=\"/btl/adcss/all.min.css\" rel=\"stylesheet\">");
		    out.append("<link href=\"/btl/adcss/bootstrap.min.css\" rel=\"stylesheet\">");
		    out.append("<link href=\"/btl/adcss/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		    out.append("<link href=\"/btl/adcss/loginV3.css\" rel=\"stylesheet\">");
			out.append("<script language=\"javascript\" src=\"/btl/adjs/loginv3.js\"></script>");
		  out.append("</head>");
		  out.append("<body>");
		  
		    out.append("<div class=\"container-lg\">");
			    RequestDispatcher error = getServletContext().getRequestDispatcher("/error");
				if (error != null) {
					error.include(request, response);
				}
		        out.append("<div class=\"row my-5\">");
		            out.append("<div class=\"col-lg-6 offset-lg-3 text-bg-light\">");
		                out.append("<form method=\"post\" class=\"loginview\">");
		                    out.append("<div class=\"row\">");
		                        out.append("<div class=\"col-12 text-bg-primary py-3 text-center fw-bolder text-uppercase\">");
		                            out.append("<i class=\"fa-solid fa-user\"></i> &nbsp;&nbsp;login");
		                        out.append("</div>");
		                    out.append("</div>");

		                    out.append("<div class=\"row mb-3 mt-3\">");
		                        out.append("<label for=\"name\" class=\"col-sm-4 col-form-label text-end fw-semibold\">Username / Email</label>");
		                        out.append("<div class=\"col-sm-6\">");
									out.append("<input type=\"text\" class=\"form-control\" id=\"name\" name =\"txtName\" onKeyup=\"checkValidLogin()\">");
									out.append("<div id=\"errName\"></div>");
		                        out.append("</div>");
		                    out.append("</div>");


		                    out.append("<div class=\"row mb-3\">");
		                        out.append("<label for=\"pass\" class=\"col-sm-4 col-form-label text-end fw-semibold\">Password</label>");
		                        out.append("<div class=\"col-sm-6\">");
									out.append("<input type=\"password\" class=\"form-control\" id=\"pass\" name = \"txtPass\" onKeyup=\"checkValidLogin()\">");
									out.append("<div id=\"errPass\"></div>");
		                        out.append("</div>");
		                    out.append("</div>");

		                    out.append("<div class=\"row mb-3\">");
		                        out.append("<div class=\"col-sm-6 offset-sm-4\">");
		                        out.append("<div class=\"form-check\">");
		                            out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"chkSave\">");
		                            out.append("<label class=\"form-check-label\" for=\"chkSave\">");
		                                out.append("Save the account information?");
		                            out.append("</label>");
		                        out.append("</div>");
		                        out.append("</div>");
		                    out.append("</div>");

		                    out.append("<div class=\"row mb-3\">");
		                        out.append("<div class=\"col-12 text-center\">");
		                            out.append("<a href=\"#\">Forget password</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
		                            out.append("<a href=\"#\">Help!</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
		                            out.append("<a href=\"#\">Sign up!</a>");
		                        out.append("</div>");
		                    out.append("</div>");

		                    out.append("<div class=\"row mb-3\">");
		                        out.append("<div class=\"col-12 text-center\">");
		                            out.append("<button type=\"submit\" class=\"btn btn-primary\">Sign in</button>&nbsp;");
		                            out.append("<button type=\"submit\" class=\"btn btn-secondary\">Cancel</button>");
		                        out.append("</div>");
		                    out.append("</div>");

		                    out.append("<div class=\"row mb-3\">");
		                        out.append("<div class=\"col-12 text-end\">");
		                            out.append("<a href=\"#\"><i class=\"fa-solid fa-language\"></i> &nbsp;Vietnamese</a>");
		                        out.append("</div>");
		                    out.append("</div>");
		                out.append("</form>");
		            out.append("</div>");
		        out.append("</div>");
		    out.append("</div>");

		    out.append("<script src=\"/btl/adjs/bootstrap.bundle.min.js\"></script>");
		  out.append("</body>");
		out.append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("txtName");
		String upass = request.getParameter("txtPass");
		
		if(uname !=null && upass!=null) {
			uname.trim();
			upass.trim();
			if(!uname.equalsIgnoreCase("") && !upass.equalsIgnoreCase("")) {
				ServletContext application = getServletConfig().getServletContext();
				
				ConnectionPool cp = (ConnectionPool)application.getAttribute("CPool");
				
				UserControl uc = new UserControl(cp);
				
				if(cp==null) {
					application.setAttribute("CPool", uc.getCP());
				}
				//Thực hiện chức năng đăng nhập
				UserObject user = uc.getUserObject(uname, upass);
				//Trả về kết nối
				uc.releaseConnection();
				
				if(user!=null) {
					//Tham chiếu phiên làm việc
					HttpSession sestion = request.getSession(true); //Khởi tạo phiên làm việc mới
					//Đưa thông tin đăng nhập vào phiên
					sestion.setAttribute("userLogined", user);
					
					response.sendRedirect("/btl/view");
					
				}else {
					response.sendRedirect("/btl/user/login?err=lgnotok");
				}
			}else {
				response.sendRedirect("/btl/user/login?err=lgvalue");
				
			}
		}else {
			response.sendRedirect("/btl/user/login?err=lgparam");
			
		}
		
	}

}
