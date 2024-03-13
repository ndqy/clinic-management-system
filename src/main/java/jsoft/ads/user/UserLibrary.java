package jsoft.ads.user;
import jsoft.*;
import jsoft.library.Utilities;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import jsoft.objects.*;
import org.javatuples.*;
public class UserLibrary {

	public static ArrayList<String> viewUser(
			Pair<ArrayList<UserObject>, Integer> data,
			Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors){
		//Danh sách người dùng
		ArrayList<UserObject> items = data.getValue0();
		//Tổng số bản ghi
		int total = data.getValue1();
		UserObject user = infors.getValue0();
		short page = infors.getValue1();
		byte totalperpage = infors.getValue2();	
		
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Table with hoverable rows -->");
        tmp.append("<table class=\"table table-hover\">");
          tmp.append("<thead>");
            tmp.append("<tr>");
              tmp.append("<th class=\"text-center\" scope=\"col\">ID</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Tên đăng nhập</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Tên đầy đủ</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Hộp thư</th>");
              
              if(user.isUser_deleted()) {
            	  tmp.append("<th class=\"text-center\" scope=\"col\">Lần cuối đăng nhập</th>");
            	  tmp.append("<th class=\"text-center\" scope=\"col\">Ngày xóa</th>");
              }else {
            	  tmp.append("<th class=\"text-center\" scope=\"col\">Điện thoại</th>");
            	  tmp.append("<th class=\"text-center\" scope=\"col\">Số lần đăng nhập</th>");
              }
              tmp.append("<th class=\"text-center\" scope=\"col\" colspan=\"3\">Thực hiện</th>");
            tmp.append("</tr>");
          tmp.append("</thead>");
          tmp.append("<tbody>");
            
            items.forEach(item -> {
            	tmp.append("<tr>");
	            	tmp.append("<th scope=\"row\" class=\"text-center\">"+item.getUser_id()+"</th>");
	                tmp.append("<td class=\"text-center\">"+item.getUser_name()+"</td>");
	                tmp.append("<td class=\"text-center\">"+Utilities.decode(item.getUser_fullname())+"</td>");
	                tmp.append("<td class=\"text-center\">"+item.getUser_email()+"</td>");
	                if(user.isUser_deleted()) {
	                	tmp.append("<td class=\"text-center\">"+item.getUser_last_logined()+"</td>");
	                	tmp.append("<td class=\"text-center\">"+item.getUser_last_modified()+"</td>");
	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoUser"+item.getUser_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
		                tmp.append(UserLibrary.viewInfoUser(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#resUser"+item.getUser_id()+"\"><i class=\"bi bi-bootstrap-reboot\"></i></button></td>");
		                tmp.append(UserLibrary.viewResUser(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
		                tmp.append(UserLibrary.viewDelUser(item));
	                }else {
	                	tmp.append("<td class=\"text-center\">"+item.getUser_mobilephone()+"</td>");
	                	tmp.append("<td class=\"text-center\">"+item.getUser_logined()+"</td>");
	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoUser"+item.getUser_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
		                tmp.append(UserLibrary.viewInfoUser(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-warning btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editUser"+item.getUser_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
		                tmp.append(UserLibrary.viewEditUser(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
		                tmp.append(UserLibrary.viewDelUser(item));
	                }
	                
	                tmp.append("</tr>");
            });
              

          tmp.append("</tbody>");
        tmp.append("</table>");
        tmp.append("<!-- End Table with hoverable rows -->");
				
        ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		//Lấy từ khóa tìm kiếm 
		String key = user.getUser_fullname();
		
		//Cấu trúc phân trang
		String url = "/btl/user/list";
		if(user.isUser_deleted()) {
			url += "?trash&";
			if(key!=null && !"".equalsIgnoreCase(key)) {
				url += "key=" + key + "&";
			}
		}else {
			url += "?";
			if(key!=null && !"".equalsIgnoreCase(key)) {
				url += "key=" + key + "&";
			}
		}
		
		//Phân trang
		String paging = UserLibrary.getPagination(url, total, page, totalperpage).toString();
		view.add(paging);
		
		//Biểu đồ
		String chart = UserLibrary.creatChart(items).toString();
		view.add(chart);
		
		return view;
	}	
		/*
		String tag = similar.getArticle_tag();
		if(tag!=null && !tag.equalsIgnoreCase("")) {
			if(tmp.indexOf("=") !=-1) {
				tmp.append("&");
			}
			tmp.append("tag=").append(tag);
		}*/
		
	
	
	private static StringBuilder viewInfoUser(UserObject item) {
		StringBuilder tmp = new StringBuilder();
		String permis;
		byte per = item.getUser_permission();
		switch (per) {
		case 1: permis = "Nha sĩ"; break;
		case 2: permis = "Quản lý"; break;
		case 3: permis = "Quản trị"; break;
		case 4: permis = "Quản trị cấp cao"; break;
		default: permis = "none";
		}
		tmp.append("<!-- Modal -->");
		tmp.append("<div class=\"modal fade\" id=\"infoUser"+item.getUser_id()+"\" tabindex=\"-1\" aria-labelledby=\"infoUserLabel\" aria-hidden=\"true\">");
		  tmp.append("<div class=\"modal-dialog modal-lg\">");	  
		    tmp.append("<div class=\"modal-content\">");
		      tmp.append("<div class=\"modal-header\">");
				tmp.append("<h1 class=\"modal-title fs-5\" id=\"infoUserLabel\"><i class=\"bi bi-person-vcard\"></i> Thông tin tài khoản <b>"+Utilities.decode(item.getUser_fullname())+ " / " + item.getUser_name() +"</b></h1>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		      tmp.append("</div>");
		      tmp.append("<div class=\"modal-body\">");
		      
				tmp.append("<div class=\"row mb-3\">");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-name\" class=\"form-label\">Tên tài khoản</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-name"+item.getUser_id()+"\" name=\"txtUserName\" value=\""+item.getUser_name()+"\" disabled readonly>");	
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"full-name\" class=\"form-label\">Họ tên</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"full-name\" name=\"txtFullName\" value=\""+Utilities.decode(item.getUser_fullname())+"\"disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"email-address\" class=\"form-label\">Hộp thư</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"email-address\" required name=\"txtEmailAddress\" value=\""+item.getUser_email()+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" required name=\"txtPhoneNumber\" value=\""+item.getUser_mobilephone()+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Quyền thực thi</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\" required name=\"txtPermis\" value=\""+permis+"\" disabled readonly>");
				tmp.append("</div>");
				tmp.append("</div>");
 
		      tmp.append("</div>");//modal-body
		      tmp.append("<div class=\"modal-footer\">");
		      if(item.isUser_deleted()) {
		    	  tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
				  
		      }else {
		    	  tmp.append("<button class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#editUser"+item.getUser_id()+"\"><i class=\"bi bi-person-gear\"></i> Chỉnh sửa</button>");
		    	  tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
		      }
		      tmp.append("</div>");//modal-footer
		    tmp.append("</div>");//modal-content
		  tmp.append("</div>");//modal-dialog
		tmp.append("</div>");//modal fade
		return tmp;
		
		}
	
	private static StringBuilder viewEditUser(UserObject user) {
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Modal -->");
		tmp.append("<div class=\"modal fade\" id=\"editUser"+user.getUser_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"editUserLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		tmp.append("<form method=\"post\" action=\"/btl/user/list\" class=\"needs-validation\" novalidate>");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"editUserLabel\"><i class=\"bi bi-person-gear\"></i> Chỉnh sửa thông tin <b>"+ Utilities.decode(user.getUser_fullname())+ " / "+user.getUser_name()+"</b></h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		tmp.append("<div class=\"row mb-3\">");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"user-name\" class=\"form-label\">Tên tài khoản</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-name\" name=\"txtUserName\" value=\""+user.getUser_name()+"\" disabled readonly required>");
		tmp.append("</div>");
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"full-name\" class=\"form-label\">Họ tên</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"full-name\" name=\"txtFullName2\" value=\""+Utilities.decode(user.getUser_fullname())+"\">");
		tmp.append("</div>");
		
		tmp.append("</div>");
		
		/*
		tmp.append("<div class=\"row mb-3\">");
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"password\" class=\"form-label\">Mật khẩu</label>");
		tmp.append("<input type=\"password\" class=\"form-control\" id=\"password\" required name=\"txtPassword2\">");
		tmp.append("</div>");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"confirm-password\" class=\"form-label\">Nhập lại mật khẩu</label>");
		tmp.append("<input type=\"password\" class=\"form-control\" id=\"confirm-password\" required name=\"txtConfirmPassword2\">");
		tmp.append("</div>");
		
		tmp.append("</div>");
		*/
		tmp.append("<div class=\"row mb-3\">");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"email-address\" class=\"form-label\">Hộp thư</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"email-address\" required name=\"txtEmailAddress2\" value=\""+user.getUser_email()+"\">");
		tmp.append("</div>");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" required name=\"txtPhoneNumber2\" value=\""+user.getUser_mobilephone()+"\">");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("<div class=\"row mb-3\">");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"user-permission\" class=\"form-label\">Quyền thực thi</label>");
		tmp.append("<select class=\"form-select\" required id=\"user-permission\" name=\"slcPermis2\">");
		tmp.append("<option value=\"\" selected>--- Chọn ---</option>");
		tmp.append("<option value=\"1\">Nha sĩ</option>");
		if(user.getUser_permission() >=3 ) {
			tmp.append("<option value=\"2\">Quản lý</option>");
			tmp.append("<option value=\"3\">Quản trị </option>");
		}else {
			if(user.getUser_permission() < 3 ) {
			tmp.append("<option value=\"2\">Quản lý</option>");
			}
		}
		
		tmp.append("</select>");
		tmp.append("</div>");
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"address\" class=\"form-label\">Địa chỉ</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"address\" name=\"txtAddress2\" value=\""+Utilities.decode(user.getUser_address())+"\">");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("</div>");
		
		//==========================================
		tmp.append("<input type=\"hidden\" name =\"idForPost\" value=\""+user.getUser_id()+"\"  id=\"\">");
		tmp.append("<input type=\"hidden\" name =\"act\" value=\"edit\">");
		tmp.append("<div class=\"modal-footer\">");
		tmp.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-check2\"></i> Hoàn tất</button>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</form>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}
	private static StringBuilder viewResUser(UserObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"modal fade\" id=\"resUser"+item.getUser_id()+"\" tabindex=\"-1\" aria-labelledby=\"ResUserLabel\"+item.getUser_id()+\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"ResUserLabel"+item.getUser_id()+"\"><i class=\"bi bi-bootstrap-reboot\"></i> Khôi phục tài khoản người dùng <b>"+item.getUser_fullname()+" / "+ item.getUser_name()+"</b></h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row mb-3\">");
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("Bạn có chắc chắn muốn khôi phục tài khoản của <b>").append(Utilities.decode(item.getUser_fullname())).append(" / "+item.getUser_name()+"</b>?<br>");
		tmp.append("Người dùng có thể sử dụng tài khoản để đăng nhập và làm việc với hệ thống.");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");

		tmp.append("<a href=\"/btl/user/dr?id="+item.getUser_id()+"&name="+Utilities.decode(item.getUser_fullname())+"&r\" class=\"btn btn-primary\"><i class=\"bi bi-bootstrap-reboot\"></i> Khôi phục</a>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}
	
	
	public static StringBuilder getPagination(String url, int total, short page, byte totalperpage) { // Đường dẫn - Tổng số bản ghi - trang hiện tại - số bản ghi/trang
		
		StringBuilder tmp = new StringBuilder();
		
		//Đếm tổng số trang
		short pages = (short) (total / totalperpage);
		if(total % totalperpage != 0) {
			pages++;
		}
		//Trang không hợp lệ thì quay về trang 1
		if(page > pages || page <=0) {
			page = 1;
		}
		/*
		System.out.println("________TOTAL: "+ total);
		System.out.println("________TOTALPERPAGE: "+ totalperpage);
		System.out.println("________PAGE: "+ page);*/
		//1,2,...,[page-1],[page],[page+1],...,10,11

		tmp.append("<nav aria-label=\"...\">");
		tmp.append("<ul class=\"pagination justify-content-center\">");
		if(page == 1) {
			tmp.append("<li class=\"page-item\"><a class=\"page-link\"  href=\"#\" tabindex=\"-1\" aria-disabled=\"true\" disabled=\"disabled\" ><span aria-hidden=\"true\">&laquo;</span></a></li>");	
		}else {
			tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+(page -1)+ "\"tabindex=\"-1\" aria-disabled=\"true\"><span aria-hidden=\"true\">&laquo;</span></a></li>");
		}
		
		//Left Current 
		String leftcurrent = "";
		int count = 0;
		for(int i=page-1; i>0; i--) {
			leftcurrent ="<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+i+"\">"+i+"</a></li>"+leftcurrent;
			if(++count>=2) {
				break;
			}
		}
		if( page == 4 ) {
			tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page=1\">"+1+"</a></li>");
		}
		if(page>4) {
			leftcurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" disabled=\"disabled\">...</a></li>"+leftcurrent;
			leftcurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page=1"+"\"><span aria-hidden=\"true\">1</span></a></li>"+leftcurrent;
		}
			
		tmp.append(leftcurrent);
		
		tmp.append("<li class=\"page-item\"><a class=\"page-link active\" href=\"#\">"+page+"</a></li>");
		
		//Right Current
		String rightcurrent = "";
		count=0;
		for(int i=page+1; i<pages; i++) {
			rightcurrent +="<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+i+"\">"+i+"</a></li>";
			if(++count>=2) {
				break;
			}
		}
		
		if(page<pages-3) {
			rightcurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" disabled = \"disabled\">...</a></li>";
			rightcurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+pages+"\">"+pages+"</a></li>";
			rightcurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+(page+1)+"\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">&raquo;</span></a></li>";			
			
		}
		tmp.append(rightcurrent);
		if(page == pages-2 || page == pages-3) {
			tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+(page+2)+"\">"+pages+"</a>");
			tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+(page+1)+"\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">&raquo;</span></a></li>");
		}
		if( page == pages-1 ) {
			tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+(page+1)+"\">"+pages+"</a>");
			tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"page="+(page+1)+"\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">&raquo;</span></a></li>");
	
		}
		if(page == pages) {
			tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-disabled=\"false\" disabled=\"disabled\"><span aria-hidden=\"true\">&raquo;</span></a></li>");
		}/*else {
			tmp.append("<a class=\"prev\" href=\""+url+"&page="+(page+1)+"\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">&raquo;</span></a>");			
		}*/
		
		
		//tmp.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\""+url+"?page="+((page<pages) ? (page+1): pages)+"\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">&raquo;</span></a></li>");
			
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}
	
	private static StringBuilder viewSummary(String str) {
		StringBuilder tmp = new StringBuilder();
		if(str!=null && !str.equalsIgnoreCase("")) {
			tmp.append("<p><span class=\"firstcharacter\">"+str.substring(0,1)+"</span>"+"<span class=\"fw-semibold fs-6\" >"+ str.substring(1)+"</span></p>");
		}
		return tmp;
	}
	private static StringBuilder viewDelUser(UserObject item) {
		StringBuilder tmp = new StringBuilder();
		String title;
		if(item.isUser_deleted()) {
			title = "Xóa vĩnh viễn ";
		}else {
			title = "Xóa tài khoản ";
		}
		
		tmp.append("<div class=\"modal fade\" id=\"delUser"+item.getUser_id()+"\" tabindex=\"-1\" aria-labelledby=\"DelUserLabel\"+item.getUser_id()+\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"DelUserLabel"+item.getUser_id()+"\"><i class=\"bi bi-trash3\"></i> "+title+"</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		if(item.isUser_deleted()) {
			tmp.append("Bạn có chắc chắn xóa vĩnh viễn tài khoản <b>").append(Utilities.decode(item.getUser_fullname())).append(" ("+item.getUser_name()+")</b>?<br>");
			tmp.append("Tài khoản không thể phục hồi được nữa.");
		}else {
			tmp.append("Bạn có chắc chắn xóa tài khoản <b>").append(Utilities.decode(item.getUser_fullname())).append(" ("+item.getUser_name()+")</b>?<br>");
			tmp.append("Hệ thống tạm thời lưu vào thùng rác, có thể phục hồi trong vòng 30 ngày.");
		}
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		if(item.isUser_deleted()) {
			tmp.append("<a href=\"/btl/user/dr?id="+item.getUser_id()+"&name="+Utilities.decode(item.getUser_fullname())+"\" class=\"btn btn-danger\"><i class=\"bi bi-trash3\"></i> Xóa vĩnh viễn</a>");
		}else {
			tmp.append("<a href=\"/btl/user/dr?id="+item.getUser_id()+"&name="+Utilities.decode(item.getUser_fullname())+"&t\" class=\"btn btn-danger\"><i class=\"bi bi-trash3\"></i> Xóa</a>");
		}
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		return tmp;
	}
	
	public static StringBuilder creatChart(ArrayList<UserObject> items) {
		StringBuilder data = new StringBuilder();
		StringBuilder cates = new StringBuilder();
		
		items.forEach(item ->{
			data.append(item.getUser_logined());
			cates.append("'" + Utilities.decode(item.getUser_fullname())+" / " + item.getUser_name() + "'");
			if(items.indexOf(item)<items.size()) {
				data.append(",");
				cates.append(",");
			}
		});
		
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Biểu đồ số lần đăng nhập của người dùng</h5>");

		tmp.append("<!-- Bar Chart -->");
		tmp.append("<div id=\"barChart\"></div>");
		tmp.append("");
		tmp.append("<script>");
		tmp.append("document.addEventListener(\"DOMContentLoaded\", () => {");
		tmp.append("new ApexCharts(document.querySelector(\"#barChart\"), {");
		tmp.append("series: [{");
		tmp.append("data: ["+data.toString()+"]");
		tmp.append("}],");
		tmp.append("chart: {");
		tmp.append("type: 'bar',");
		if(items.size()<5) {
			tmp.append("height: 200");	
		}else {
			tmp.append("height: " + items.size()*50);	
		}
		tmp.append("},");
		tmp.append("plotOptions: {");
		tmp.append("bar: {");
		tmp.append("borderRadius: 4,");
		tmp.append("horizontal: true,");
		tmp.append("}");
		tmp.append("},");
		tmp.append("dataLabels: {");
		tmp.append("enabled: false");
		tmp.append("},");
		tmp.append("xaxis: {");
		tmp.append("categories: ["+cates.toString()+"],");
		tmp.append("show: true,");
		tmp.append("labels: {");
		tmp.append("show: true,");
		tmp.append("align: 'right',");
		tmp.append("minWidth: 0,");
		tmp.append("maxWidth: 300,");
		tmp.append("style: {");
		tmp.append("colors: [],");
		tmp.append("fontSize: '15px',");
		tmp.append("fontFamily: 'Helvetica, Arial, sans-serif',");
		tmp.append("fontWeight: 400,");
		tmp.append("cssClass: 'apexcharts-yaxis-label'");
		tmp.append("},");
		tmp.append("},");
		tmp.append("},");

		tmp.append("yaxis: {");
		tmp.append("show: true,");
		tmp.append("labels: {");
		tmp.append("show: true,");
		tmp.append("align: 'right',");
		tmp.append("minWidth: 0,");
		tmp.append("maxWidth: 300,");
		tmp.append("style: {");
		tmp.append("colors: [],");
		tmp.append("fontSize: '15px',");
		tmp.append("fontFamily: 'Helvetica, Arial, sans-serif',");
		tmp.append("fontWeight: 400,");
		tmp.append("cssClass: 'apexcharts-yaxis-label'");
		tmp.append("},");
		tmp.append("},");
		tmp.append("}");
		
		tmp.append("}).render();");
		tmp.append("});");
		tmp.append("</script>");
		tmp.append("<!-- End Bar Chart -->");
		  tmp.append("</div>");//card
		  tmp.append("</div>");//card-body	
		return tmp;
	}
}
