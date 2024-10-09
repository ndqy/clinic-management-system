package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Quintet;

import jsoft.ConnectionPool;
import jsoft.ads.calendar.CALENDAR_GET_TYPE;
import jsoft.ads.calendar.CalendarControl;
import jsoft.ads.log.LogControl;
import jsoft.library.Utilities_date;
import jsoft.objects.CalendarObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class View
 */
@WebServlet("/view")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Tìm thông tin đăng nhập
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if (user != null) {

			view(request, response, user);
		} else {
			response.sendRedirect("/btl/user/login");
		}
	}

	private void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws IOException, ServletException {
		// TODO Auto-generated method stub

		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng thực hiện xuất nội dung
		PrintWriter out = response.getWriter();

		ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
		
		
		
		// Tham chiếu tìm Header
		RequestDispatcher h = request.getRequestDispatcher("/header");
		if (h != null) {
			h.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Trang chủ</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		//out.append("<li class=\"breadcrumb-item\">Home</li>");
		out.append("<li class=\"breadcrumb-item active\">Dashbroad</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section dashboard\">");
		out.append("<div class=\"row\">");


        out.append("<!-- Left side columns -->");
        out.append("<div class=\"col-lg-8\">");
          out.append("<div class=\"row\">");


	          //Vẽ biểu đồ báo cáo doanh thu
	          out.append("<!-- Reports -->");
	          out.append("<div class=\"col-12\">");
	            out.append("<div class=\"card\">");
	
	              out.append("<div class=\"filter\">");
	                out.append("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
	                out.append("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
	                  out.append("<li class=\"dropdown-header text-start\">");
	                    out.append("<h6>Filter</h6>");
	                  out.append("</li>");
	
	                  out.append("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
	                  out.append("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
	                  out.append("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
	                out.append("</ul>");
	              out.append("</div>");
	
	              out.append("<div class=\"card-body\">");
	                out.append("<h5 class=\"card-title\">Báo cáo doanh thu theo năm </h5>");/*<span>/Today</span>*/
	
	               CalendarObject item = new CalendarObject();
	               //item.setCalendar_room_id(Integer.parseInt(Utilities_date.getDate().substring(5, 7)));
	               item.setCalendar_create_date(Utilities_date.getDate().substring(5, 7));
	               item.setCalendar_end_date(Utilities_date.getDate().substring(0, 4));
	               
	               CalendarControl cc = new CalendarControl(cp);
	               if(cc==null) {
	              	 getServletContext().setAttribute("CPool", cc.getCP());
	               }
	               Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors = new Quintet<>(item, null, null, CALENDAR_GET_TYPE.ID, null);
	              out.append(cc.viewStatisticalChart(infors));
	               cc.releaseConnection();
	               
	              out.append("</div>");
	
	            out.append("</div>");
	          out.append("</div><!-- End Reports -->");
          
          
            out.append("<!-- Sales Card -->");
            out.append("<div class=\"col-xxl-4 col-md-6\">");
              out.append("<div class=\"card info-card sales-card\">");

                out.append("<div class=\"filter\">");
                  out.append("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
                  out.append("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
                    out.append("<li class=\"dropdown-header text-start\">");
                      out.append("<h6>Bộ lọc</h6>");
                    out.append("</li>");

                    out.append("<li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
                    out.append("<li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
                    out.append("<li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
                  out.append("</ul>");
                out.append("</div>");

                out.append("<div class=\"card-body\">");
                  out.append("<h5 class=\"card-title\">Sales <span>| Today</span></h5>");

                  out.append("<div class=\"d-flex align-items-center\">");
                    out.append("<div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
                      out.append("<i class=\"bi bi-cart\"></i>");
                    out.append("</div>");
                    out.append("<div class=\"ps-3\">");
                      out.append("<h6>145</h6>");
                      out.append("<span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">increase</span>");

                    out.append("</div>");
                  out.append("</div>");
                out.append("</div>");

              out.append("</div>");
            out.append("</div><!-- End Sales Card -->");

            out.append("<!-- Revenue Card -->");
            out.append("<div class=\"col-xxl-4 col-md-6\">");
              out.append("<div class=\"card info-card revenue-card\">");

                out.append("<div class=\"filter\">");
                  out.append("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
                  out.append("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
                    out.append("<li class=\"dropdown-header text-start\">");
                      out.append("<h6>Filter</h6>");
                    out.append("</li>");

                    out.append("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
                    out.append("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
                    out.append("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
                  out.append("</ul>");
                out.append("</div>");

                out.append("<div class=\"card-body\">");
                  out.append("<h5 class=\"card-title\">Revenue <span>| This Month</span></h5>");

                  out.append("<div class=\"d-flex align-items-center\">");
                    out.append("<div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
                      out.append("<i class=\"bi bi-currency-dollar\"></i>");
                    out.append("</div>");
                    out.append("<div class=\"ps-3\">");
                      out.append("<h6>$3,264</h6>");
                      out.append("<span class=\"text-success small pt-1 fw-bold\">8%</span> <span class=\"text-muted small pt-2 ps-1\">increase</span>");

                    out.append("</div>");
                  out.append("</div>");
                out.append("</div>");

              out.append("</div>");
            out.append("</div><!-- End Revenue Card -->");

            out.append("<!-- Customers Card -->");
            out.append("<div class=\"col-xxl-4 col-xl-12\">");

              out.append("<div class=\"card info-card customers-card\">");

                out.append("<div class=\"filter\">");
                  out.append("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
                  out.append("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
                    out.append("<li class=\"dropdown-header text-start\">");
                      out.append("<h6>Filter</h6>");
                    out.append("</li>");

                    out.append("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
                    out.append("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
                    out.append("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
                  out.append("</ul>");
                out.append("</div>");

                out.append("<div class=\"card-body\">");
                  out.append("<h5 class=\"card-title\">Customers <span>| This Year</span></h5>");

                  out.append("<div class=\"d-flex align-items-center\">");
                    out.append("<div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
                      out.append("<i class=\"bi bi-people\"></i>");
                    out.append("</div>");
                    out.append("<div class=\"ps-3\">");
                      out.append("<h6>1244</h6>");
                      out.append("<span class=\"text-danger small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">decrease</span>");

                    out.append("</div>");
                  out.append("</div>");

                out.append("</div>");
              out.append("</div>");

            out.append("</div><!-- End Customers Card -->");

            
            
            
          out.append("</div>");//row
        out.append("</div><!-- End Left side columns -->");//col-lg-8
        
        out.append("<!-- Right side columns -->");
	        out.append("<div class=\"col-lg-4\">");
	        
	        
	        LogControl lc = new LogControl(cp);
			if(cp==null) {
				getServletContext().setAttribute("CPool", lc.getCP());
			}
			
	          out.append(lc.viewLog());
	          lc.releaseConnection();
	        out.append("</div><!-- End Right side columns -->");
		
		out.append("</div>");//row
		out.append("</section>");

		out.append("</main><!-- End #main -->");

		// Tham chiếu tìm Footer
		RequestDispatcher f = request.getRequestDispatcher("/footer");
		if (f != null) {
			f.include(request, response);
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
