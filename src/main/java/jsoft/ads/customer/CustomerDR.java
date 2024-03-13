package jsoft.ads.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.ads.log.LogControl;
import jsoft.ads.service.SERVICE_EDIT_TYPE;
import jsoft.ads.service.ServiceControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.CustomerObject;
import jsoft.objects.LogObject;
import jsoft.objects.ServiceObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class CustomerDR
 */
@WebServlet("/customer/dr")
public class CustomerDR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		
		int id = Utilities.getIntParam(request, "id");
		String name = request.getParameter("name");
		if(user!=null && id>0) {
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			CustomerControl sc = new CustomerControl(cp);
			if(cp==null) {
				getServletContext().setAttribute("CPool", sc.getCP());
			}
			
			CustomerObject item = new CustomerObject();
			item.setCustomer_id(id);
			item.setCustomer_modified_date(Utilities_date.getDate());
			String disable = request.getParameter("d");
			String enable = request.getParameter("r");
			//String url = "/btl/customer/list";
			boolean result;
			if(disable==null && enable == null) {
				result = sc.delCustomer(item);
				if(result) {
					//Lưu trữ hành động
					LogControl lc =new LogControl(cp);
					if(lc==null) {
						getServletContext().setAttribute("CPool", lc.getCP());
					}
					LogObject lo = new LogObject();
					lo.setLog_action((short)4);
					lo.setLog_date(Utilities_date.getDateProfiles());
					lo.setLog_name(Utilities.encode(name));
					lo.setLog_position((short)4);
					lo.setLog_user_name(user.getUser_name() +" / "+Utilities.encode(user.getUser_fullname()));
					lo.setLog_user_permission(user.getUser_permission());
					boolean checkLog = lc.addLog(lo);
					lc.releaseConnection();
					if(checkLog) {
						response.sendRedirect("/btl/customer/list");
					}else {
						response.sendRedirect("/btl/customer/list?err=loadd");
					}
				}else {
					response.sendRedirect("/btl/customer/list?dis&err=csdel");
				}
				//url += "?dis";
			}else {
				if(enable!=null){							
					result = sc.editCustomer(item, CUSTOMER_EDIT_TYPE.ENABLE);	
					if(result) {
						//Lưu trữ hành động
						LogControl lc =new LogControl(cp);
						if(lc==null) {
							getServletContext().setAttribute("CPool", lc.getCP());
						}
						LogObject lo = new LogObject();
						lo.setLog_action((short)5);
						lo.setLog_date(Utilities_date.getDateProfiles());
						lo.setLog_name(Utilities.encode(name));
						lo.setLog_position((short)4);
						lo.setLog_user_name(user.getUser_name() +" / "+Utilities.encode(user.getUser_fullname()));
						lo.setLog_user_permission(user.getUser_permission());
						boolean checkLog = lc.addLog(lo);
						lc.releaseConnection();
						if(checkLog) {
							response.sendRedirect("/btl/customer/list?dis");
						}else {
							response.sendRedirect("/btl/customer/list?dis&err=loadd");
						}
					}else {
						response.sendRedirect("/btl/customer/list?dis&err=csrestore");
					}
				}else {
					result = sc.editCustomer(item, CUSTOMER_EDIT_TYPE.DISABLE);	
					if(result) {
						//Lưu trữ hành động
						LogControl lc =new LogControl(cp);
						if(lc==null) {
							getServletContext().setAttribute("CPool", lc.getCP());
						}
						LogObject lo = new LogObject();
						lo.setLog_action((short)6);
						lo.setLog_date(Utilities_date.getDateProfiles());
						lo.setLog_name(Utilities.encode(name));
						lo.setLog_position((short)4);
						lo.setLog_user_name(user.getUser_name() +" / "+Utilities.encode(user.getUser_fullname()));
						lo.setLog_user_permission(user.getUser_permission());
						boolean checkLog = lc.addLog(lo);
						lc.releaseConnection();
						if(checkLog) {
							response.sendRedirect("/btl/customer/list?dis");
						}else {
							response.sendRedirect("/btl/customer/list?dis&err=loadd");
						}
				}else {
					response.sendRedirect("/btl/customer/list?dis&err=loadd");
				}
			}
			
			sc.releaseConnection();
			}
			/*if(result) {
				response.sendRedirect(url);
			}else {
				response.sendRedirect(url + "&err=notok");
			}*/
		}else {
			response.sendRedirect("/btl/user/list?err=notlogin");
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
