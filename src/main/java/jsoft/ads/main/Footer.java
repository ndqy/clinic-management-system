package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Footer
 */
@WebServlet("/footer")
public class Footer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Footer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		out.append("<!-- ======= Footer ======= -->");
		  out.append("<footer id=\"footer\" class=\"footer\">");
		    out.append("<div class=\"copyright\">");
		      out.append("&copy; Copyright <strong><span>BTLAdmin</span></strong>. All Rights Reserved");
		    out.append("</div>");
		    out.append("<div class=\"credits\">");
		     out.append("Designed by <a href=\"#\">DinhQuy</a>");
		    out.append("</div>");
		  out.append("</footer><!-- End Footer -->");

		  out.append("<a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");

		  out.append("<!-- Vendor JS Files -->");
		  out.append("<script src=\"/btl/adjs/apexcharts/apexcharts.min.js\"></script>");
		  out.append("<script src=\"/btl/adjs/bootstrap.bundle.min.js\"></script>");

		  out.append("<!-- Template Main JS File -->");
		  out.append("<script src=\"/btl/adjs/main.js\"></script>");
		  
		  out.append("<script src=\"/btl/adjs/jquery-3.6.0.min.js\"></script>");
		  out.append("<script src=\"/btl/adjs/datatables.min.js\"></script>");
		  out.append("<script src=\"/btl/adjs/pdfmake.min.js\"></script>");
		  out.append("<script src=\"/btl/adjs/vfs_fonts.js\"></script>");
		  out.append("<script src=\"/btl/adjs/custom.js\"></script>");
		  out.append("</body>");

		out.append("</html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
