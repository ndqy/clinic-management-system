package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.library.Utilities_date;

/**
 * Servlet implementation class Sidebar
 */
@WebServlet("/sidebar")
public class Sidebar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sidebar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		HashMap<String, String> collapsed = new HashMap<>();
		HashMap<String, String> show = new HashMap<>();
		HashMap<String, String> actives = new HashMap<>();
		
		//Tìm tham số vị trí
		String pos = request.getParameter("pos");
		if(pos!=null) {
			String menu = pos.substring(0,2);
			String act = pos.substring(2);
			
			switch(menu) {
				case "ur":
					collapsed.put("user", "");
					show.put("user", "show");
					switch(act) {
						case "list":
							actives.put("list", "class = \"active\" ");
							break;
						
							
						case "trash":
							actives.put("utrash", "class = \"active\" ");
							break;
						
					}
					
					break;
				case "cl":
					collapsed.put("cald", "");
					show.put("cald", "show");
					switch(act) {
						case "list":
							actives.put("list", "class = \"active\" ");
							break;
						
							
						case "chart":
							actives.put("chart", "class = \"active\" ");
							break;
						
						}
					
					break;

				case "sv":
					collapsed.put("serv", "");
					show.put("serv", "show");
					switch(act) {
						case "list":
							actives.put("list", "class = \"active\" ");
							break;
						
							
						case "trash":
							actives.put("strash", "class = \"active\" ");
							break;
						
						}
					
					break;
				
				case "cs":
					collapsed.put("cust", "");
					show.put("cust", "show");
					switch(act) {
						case "list":
							actives.put("list", "class = \"active\" ");
							break;
							
						case "dis":
							actives.put("ctrash", "class = \"active\" ");
							break;
						
						}
						
				break;
			}
		}
		
			

		
		out.append("<!-- ======= Sidebar ======= -->");
		  out.append("<aside id=\"sidebar\" class=\"sidebar\">");

		    out.append("<ul class=\"sidebar-nav\" id=\"sidebar-nav\">");

		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link "+collapsed.getOrDefault("Dashboard", "collapsed")+"\" href=\"/btl/view\">");
		          out.append("<i class=\"bi bi-grid\"></i>");
		          out.append("<span>Dashboard</span>");
		        out.append("</a>");
		      out.append("</li><!-- End Dashboard Nav -->");

		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link "+collapsed.getOrDefault("user", "collapsed")+"\" data-bs-target=\"#components-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		          out.append("<i class=\"bi bi-people\"></i><span>Người sử dụng</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		        out.append("</a>");
		        out.append("<ul id=\"components-nav\" class=\"nav-content collapse "+show.getOrDefault("user", " ")+"\" data-bs-parent=\"#sidebar-nav\">");  
		        out.append("<li>");
		            out.append("<a href=\"/btl/user/list\" "+actives.getOrDefault("list", " ")+">");
		              out.append("<i class=\"bi bi-person-gear\"></i><span>Quản lý | Nha sĩ</span>");
		            out.append("</a>");
		          out.append("</li>");
		          out.append("<li>");
		            out.append("<a href=\"/btl/user/list?trash\" "+actives.getOrDefault("utrash", " ")+">");
		              out.append("<i class=\"bi bi-trash3\"></i><span>Thùng rác</span>");
		            out.append("</a>");
		          out.append("</li>");

		        out.append("</ul>");
		      out.append("</li><!-- End Components Nav -->");
		      String date=request.getParameter("date");
				if(date==null || date.equalsIgnoreCase("")) {
					date = Utilities_date.getDate();
				}
		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link "+collapsed.getOrDefault("cald", "collapsed")+"\" data-bs-target=\"#forms-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		          out.append("<i class=\"bi bi-calendar2-week\"></i><span>Lịch khám</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		        out.append("</a>");
		        out.append("<ul id=\"forms-nav\" class=\"nav-content collapse "+show.getOrDefault("cald", " ")+"\" data-bs-parent=\"#sidebar-nav\">");
		          out.append("<li>");
		            out.append("<a href=\"/btl/calendar/list?view=grid&date="+date+"\""+actives.getOrDefault("list", " ")+">");
		              out.append("<i class=\"bi bi-calendar2-range\"></i><span>Danh sách</span>");
		            out.append("</a>");
		          out.append("</li>");
		          out.append("<li>");
		            out.append("<a href=\"/btl/calendar/chart?date="+date+"\""+actives.getOrDefault("chart", " ")+">");
		              out.append("<i class=\"bi bi-bar-chart-steps\"></i><span>Biểu đồ</span>");
		            out.append("</a>");
		          out.append("</li>");
		        out.append("</ul>");
		      out.append("</li><!-- End Forms Nav -->");
			  out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link "+collapsed.getOrDefault("serv", "collapsed")+"\" data-bs-target=\"#charts-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		          out.append("<i class=\"bi bi-prescription2\"></i><span>Dịch vụ</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		        out.append("</a>");
		        out.append("<ul id=\"charts-nav\" class=\"nav-content collapse "+show.getOrDefault("serv", " ")+" \" data-bs-parent=\"#sidebar-nav\">");
		          out.append("<li>");
		            out.append("<a href=\"/btl/service/list\" "+actives.getOrDefault("list", " ")+">");
		              out.append("<i class=\"bi bi-list-nested\"></i><span>Danh sách</span>");
		            out.append("</a>");
		          out.append("</li>");
		          out.append("<li>");
		            out.append("<a href=\"/btl/service/list?trash\" "+actives.getOrDefault("strash", " ")+">");
		              out.append("<i class=\"bi bi-trash3\"></i><span>Thùng rác</span>");
		            out.append("</a>");
		          out.append("</li>");	         
		        out.append("</ul>");
		      out.append("</li><!-- End Charts Nav -->");
		      
		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link "+collapsed.getOrDefault("cust", "collapsed")+"\" data-bs-target=\"#customer-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		          out.append("<i class=\"bi bi-person-hearts\"></i><span> Khách hàng</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		        out.append("</a>");
		        out.append("<ul id=\"customer-nav\" class=\"nav-content collapse "+show.getOrDefault("cust", " ")+"\" data-bs-parent=\"#sidebar-nav\">");
		          out.append("<li>");
		            out.append("<a href=\"/btl/customer/list\" "+actives.getOrDefault("list", " ")+">");
		              out.append("<i class=\"bi bi-person-lines-fill\"></i><span> Danh sách</span>");
		            out.append("</a>");
		          out.append("</li>");
		          out.append("<li>");
		            out.append("<a href=\"/btl/customer/list?dis\" "+actives.getOrDefault("ctrash", " ")+">");
		              out.append("<i class=\"bi bi-person-lock\"></i><span> Tài khoản hạn chế</span>");
		            out.append("</a>");
		          out.append("</li>");	         
		        out.append("</ul>");
		      out.append("</li><!-- End Charts Nav -->");

		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link collapsed\" href=\"/btl/log/list\">");
		          out.append("<i class=\"bi bi-clock-history\"></i>");
		          out.append("<span>Lịch sử</span>");
		        out.append("</a>");
		      out.append("</li><!-- End Register Page Nav -->");

//		      out.append("<li class=\"nav-item\">");
//		        out.append("");
//		          out.append("<i class=\"bi bi-person\"></i>");
//		          out.append("<span>Profile</span>");
//		        out.append("</a>");
//		      out.append("</li><!-- End Profile Page Nav -->");

		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link collapsed\" href=\"pages-faq.html\">");
		          out.append("<i class=\"bi bi-question-circle\"></i>");
		          out.append("<span>F.A.Q</span>");
		        out.append("</a>");
		      out.append("</li><!-- End F.A.Q Page Nav -->");

		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link collapsed\" href=\"pages-contact.html\">");
		          out.append("<i class=\"bi bi-envelope\"></i>");
		          out.append("<span>Contact</span>");
		        out.append("</a>");
		      out.append("</li><!-- End Contact Page Nav -->");

		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link collapsed\" href=\"pages-register.html\">");
		          out.append("<i class=\"bi bi-card-list\"></i>");
		          out.append("<span>Register</span>");
		        out.append("</a>");
		      out.append("</li><!-- End Register Page Nav -->");

		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link collapsed\" href=\"pages-login.html\">");
		          out.append("<i class=\"bi bi-box-arrow-in-right\"></i>");
		          out.append("<span>Login</span>");
		        out.append("</a>");
		      out.append("</li><!-- End Login Page Nav -->");

		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link collapsed\" href=\"pages-error-404.html\">");
		          out.append("<i class=\"bi bi-dash-circle\"></i>");
		          out.append("<span>Error 404</span>");
		        out.append("</a>");
		      out.append("</li><!-- End Error 404 Page Nav -->");

		      out.append("<li class=\"nav-item\">");
		        out.append("<a class=\"nav-link collapsed\" href=\"pages-blank.html\">");
		          out.append("<i class=\"bi bi-file-earmark\"></i>");
		          out.append("<span>Blank</span>");
		        out.append("</a>");
		      out.append("</li><!-- End Blank Page Nav -->");

		    out.append("</ul>");

		  out.append("</aside><!-- End Sidebar-->");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
