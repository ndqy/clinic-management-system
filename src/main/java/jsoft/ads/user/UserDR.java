package jsoft.ads.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.ads.log.LogControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.LogObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserDR
 */
@WebServlet("/user/dr")
public class UserDR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDR() {
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
		int pid = Utilities.getIntParam(request, "pid");
		String name = request.getParameter("name");
		if(user!=null && id>0) {
			if(user.getUser_id()!=id ) {
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
				UserControl uc = new UserControl(cp);
				if(cp==null) {
					getServletContext().setAttribute("CPool", uc.getCP());
				}
				
				UserObject nUser = new UserObject();
				nUser.setUser_id(id);
				//System.out.println("nUSER"+ nUser.getUser_id());
				//System.out.println("USER" + user.getUser_id());
				if(user.getUser_permission()>nUser.getUser_permission()) {
					nUser.setUser_last_modified(Utilities_date.getDate());
					String trash = request.getParameter("t");
					String restore = request.getParameter("r");
					//String url = "/btl/user/list";
					boolean result;
					if(trash==null && restore == null) {
						
						result = uc.delUser(nUser);
						//Lưu trữ hành động
						LogControl lc =new LogControl(cp);
						if(lc==null) {
							getServletContext().setAttribute("CPool", lc.getCP());
						}
						LogObject lo = new LogObject();
						lo.setLog_action((short)4);
						lo.setLog_date(Utilities_date.getDateProfiles());
						lo.setLog_name(Utilities.encode(name));
						lo.setLog_position((short)1);
						lo.setLog_user_name(user.getUser_name() +" / "+Utilities.encode(user.getUser_fullname()));
						lo.setLog_user_permission(user.getUser_permission());
						boolean checkLog = lc.addLog(lo);
						lc.releaseConnection();
						if(checkLog) {
							response.sendRedirect("/btl/user/list?trash");
						}else {
							response.sendRedirect("/btl/user/list?trash&err=loadd");
						}
						//url += "?trash";
					}else {
						if(restore!=null){							
							result = uc.editUser(nUser, USER_EDIT_TYPE.RESTORE);
							//Lưu trữ hành động
							LogControl lc =new LogControl(cp);
							if(lc==null) {
								getServletContext().setAttribute("CPool", lc.getCP());
							}
							LogObject lo = new LogObject();
							lo.setLog_action((short)5);
							lo.setLog_date(Utilities_date.getDateProfiles());
							lo.setLog_name(Utilities.encode(name));
							lo.setLog_position((short)1);
							lo.setLog_user_name(user.getUser_name() +" / "+Utilities.encode(user.getUser_fullname()));
							lo.setLog_user_permission(user.getUser_permission());
							boolean checkLog = lc.addLog(lo);
							lc.releaseConnection();
							if(checkLog) {
								response.sendRedirect("/btl/user/list?trash");
							}else {
								response.sendRedirect("/btl/user/list?trash&err=loadd");
							}
						}else {
							result = uc.editUser(nUser, USER_EDIT_TYPE.TRASH);
							//Lưu trữ hành động
							LogControl lc =new LogControl(cp);
							if(lc==null) {
								getServletContext().setAttribute("CPool", lc.getCP());
							}
							LogObject lo = new LogObject();
							lo.setLog_action((short)3);
							lo.setLog_date(Utilities_date.getDateProfiles());
							lo.setLog_name(Utilities.encode(name));
							lo.setLog_position((short)1);
							lo.setLog_user_name(user.getUser_name() +" / "+Utilities.encode(user.getUser_fullname()));
							lo.setLog_user_permission(user.getUser_permission());
							boolean checkLog = lc.addLog(lo);
							lc.releaseConnection();
							if(checkLog) {
								response.sendRedirect("/btl/user/list?trash");
							}else {
								response.sendRedirect("/btl/user/list?trash&err=loadd");
							}
						}
					}
					
					uc.releaseConnection();
					/*if(result) {
						response.sendRedirect(url);
					}else {
						response.sendRedirect(url + "&err=del");
					}*/
				}else {
					response.sendRedirect("/btl/user/list?err=nopermis");
				}
				
			}else {
				response.sendRedirect("/btl/user/list?err=acclogin");
			}
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
