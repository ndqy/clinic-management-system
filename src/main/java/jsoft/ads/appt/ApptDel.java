package jsoft.ads.appt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

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
 * Servlet implementation class ApptDR
 */
@WebServlet("/appt/del")
public class ApptDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApptDel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		
        if(user!=null) {
			if(user.getUser_id() >1) {
				ArrayList<Integer> checkIdList = new ArrayList<>();
		        // Lấy tất cả các tên tham số từ request
		        Enumeration<String> parameterNames = request.getParameterNames();
		        ArrayList<Integer> listId = new ArrayList<Integer>();
		        HashMap<Integer, String> list = new HashMap<Integer, String>();
 		        while (parameterNames.hasMoreElements()) {
		            String paramName = parameterNames.nextElement();
		            if (paramName.contains("checkId") && request.getParameter(paramName).contains("on")) {
		                listId.add((Integer.parseInt(paramName.substring(7))));
		               //.out.println(Integer.parseInt(paramName.substring(7)));
		            }
		        }
 		       parameterNames = request.getParameterNames();
		        while (parameterNames.hasMoreElements()) {
		        	String paramName = parameterNames.nextElement();
		        	if (paramName.contains("name")) {		            
			        	//System.out.println(paramName);
			        	int id = Integer.parseInt(paramName.substring(4));
			        	//System.out.println("id= " + id+ "----" +request.getParameter(paramName));
		                if(listId.contains(id)) {
		                	list.put(id, request.getParameter(paramName));
		                	//System.out.println("id= " + id+ "----" +request.getParameter(paramName));
		                }
		        	}
		        }
		        list.forEach((key,value)->{
		        	System.out.println("List: " + key + " - " + value);
		        });
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
				ApptControl ac = new ApptControl(cp);
				if(cp==null) {
					getServletContext().setAttribute("CPool", ac.getCP());
				}
				//Lưu trữ hành động
				LogControl lc =new LogControl(cp);
//				if(lc==null) {
//					getServletContext().setAttribute("CPool", lc.getCP());
//				}
				
				if(list!=null) {
					boolean result = ac.delListAppt(listId);		
					if(result) {
						for (HashMap.Entry<Integer, String> item : list.entrySet()) {
							LogObject lo = new LogObject();
							lo.setLog_action((short)4);
							lo.setLog_date(Utilities_date.getDateProfiles());
							lo.setLog_name(Utilities.encode(item.getValue()));
							lo.setLog_position((short)1);
							lo.setLog_user_name(user.getUser_name());
							lo.setLog_user_permission(user.getUser_permission());
							boolean checkLog = lc.addLog(lo);
							lc.releaseConnection();
							if(!checkLog) {
								response.sendRedirect("/btl/appt/list?err=loadd");
								return;
							}
						}
					}else {
						response.sendRedirect("/btl/appt/list?err=apdel");
					}
					response.sendRedirect("/btl/appt/list");
				}
			}else {
				response.sendRedirect("/btl/user/login?err=nopermis");
			}
		}else {
			response.sendRedirect("/btl/user/login?err=notlogin");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
//		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
//		ApptControl uc = new ApptControl(cp);
//		if(cp==null) {
//			getServletContext().setAttribute("CPool", uc.getCP());
//		}
//		
//		ApptObject nAppt = new ApptObject();
//				nAppt.setAppt_id(id);
//				LogObject lo = new LogObject();
//				lo.setLog_action((short)4);
//				lo.setLog_date(Utilities_date.getDateProfiles());
//				lo.setLog_name(Utilities.encode(name));
//				lo.setLog_position((short)1);
//				lo.setLog_user_name(user.getAppt_name() +" / "+Utilities.encode(user.getAppt_fullname()));
//				lo.setLog_user_permission(user.getAppt_permission());
//				boolean checkLog = lc.addLog(lo);
//				lc.releaseConnection();
//				if(checkLog) {
//					response.sendRedirect("/btl/user/list?trash");
//				}else {
//					response.sendRedirect("/btl/user/list?trash&err=loadd");
//				}
//			
//			uc.releaseConnection();
//			/*if(result) {
//				response.sendRedirect(url);
//			}else {
//				response.sendRedirect(url + "&err=del");
//			}*/
//		}else {
//			response.sendRedirect("/btl/user/list?err=nopermis");
//		}
//		
//	}else {
//		response.sendRedirect("/btl/user/list?err=acclogin");
//	}
	}

}
