package jsoft.ads.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.ads.log.LogControl;
import jsoft.ads.user.USER_EDIT_TYPE;
import jsoft.ads.user.UserControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.LogObject;
import jsoft.objects.ServiceObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class ServiceDR
 */
@WebServlet("/service/dr")
public class ServiceDR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceDR() {
        super();
        // TODO Auto-generated constrsctor stub
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
			ServiceControl sc = new ServiceControl(cp);
			if(cp==null) {
				getServletContext().setAttribute("CPool", sc.getCP());
			}
			
			ServiceObject item = new ServiceObject();
			item.setService_id(id);
			item.setService_modified_date(Utilities_date.getDate());
			String trash = request.getParameter("t");
			String restore = request.getParameter("r");
			//String url = "/btl/service/list";
			boolean result;
			if(trash==null && restore == null) {
				result = sc.delService(item);
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
					lo.setLog_position((short)3);
					lo.setLog_user_name(user.getUser_name() +" / "+Utilities.encode(user.getUser_fullname()));
					lo.setLog_user_permission(user.getUser_permission());
					boolean checkLog = lc.addLog(lo);
					lc.releaseConnection();
					if(checkLog) {
						response.sendRedirect("/btl/service/list?trash");
					}else {
						response.sendRedirect("/btl/service/list?trash&err=loadd");
					}
				}else {
					response.sendRedirect("/btl/service/list?trash&err=svdel");
				}
				//url += "?trash";
			}else {
				if(restore!=null){							
					result = sc.editService(item, SERVICE_EDIT_TYPE.RESTORE);
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
						lo.setLog_position((short)3);
						lo.setLog_user_name(user.getUser_name() +" / "+Utilities.encode(user.getUser_fullname()));
						lo.setLog_user_permission(user.getUser_permission());
						boolean checkLog = lc.addLog(lo);
						lc.releaseConnection();
						if(checkLog) {
							response.sendRedirect("/btl/service/list?trash");
						}else {
							response.sendRedirect("/btl/service/list?trash&err=loadd");
						}
					}else {
						response.sendRedirect("/btl/service/list?trash&err=svdel");
					}
				}else {
					result = sc.editService(item, SERVICE_EDIT_TYPE.TRASH);
					if(result) {
						//Lưu trữ hành động
						LogControl lc =new LogControl(cp);
						if(lc==null) {
							getServletContext().setAttribute("CPool", lc.getCP());
						}
						LogObject lo = new LogObject();
						lo.setLog_action((short)3);
						lo.setLog_date(Utilities_date.getDateProfiles());
						lo.setLog_name(Utilities.encode(name));
						lo.setLog_position((short)3);
						lo.setLog_user_name(user.getUser_name() +" / "+Utilities.encode(user.getUser_fullname()));
						lo.setLog_user_permission(user.getUser_permission());
						boolean checkLog = lc.addLog(lo);
						lc.releaseConnection();
						if(checkLog) {
							response.sendRedirect("/btl/service/list?trash");
						}else {
							response.sendRedirect("/btl/service/list?trash&err=loadd");
						}
					}else {
						response.sendRedirect("/btl/service/list?trash&err=svdel");
					}
				}
			}
			
			sc.releaseConnection();
			/*if(result) {
				response.sendRedirect(url);
			}else {
				response.sendRedirect(url + "&err=svdel");
			}*/
		}else {
			response.sendRedirect("/btl/user/list?err=svnotlogin");
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
