package jsoft.ads.log;

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
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.customer.CUSTOMER_SORT_TYPE;
import jsoft.ads.customer.CustomerControl;
import jsoft.library.Utilities;
import jsoft.objects.CustomerObject;
import jsoft.objects.LogObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class LogList
 */
@WebServlet("/log/list")
public class LogList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogList() {
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
		
		LogControl sc = new LogControl(cp);
		if(cp == null) {
			getServletContext().setAttribute("CPool", sc.getCP());
		}
		
		short page = Utilities.getShortParam(request, "page");
		if(page<1) {
			page=1;
		}
		
		LogObject similar = new LogObject();
		similar.setLog_user_permission(user.getUser_permission());
		similar.setLog_user_name(user.getUser_name());
		similar.setLog_user_permission(user.getUser_permission());
		
		Triplet<LogObject, Short, Byte> infors = new Triplet<>(similar, page, (byte) 10);
		List<String> viewList = sc.viewLogForUser(infors);

		
		
		// Tra ve ket noi ngay sau khi su dung xong
		sc.releaseConnection();
		
		// Tham chieu tim Header
		RequestDispatcher header = request.getRequestDispatcher("/header");
		if(header != null) {
			header.include(request, response);
		}
		
		out.append("<main id=\"main\" class=\"main\">");


		RequestDispatcher error = getServletContext().getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}
		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Lịch sử làm việc</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/btl/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Lịch sử</li>");
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
		doGet(request, response);
	}

}
