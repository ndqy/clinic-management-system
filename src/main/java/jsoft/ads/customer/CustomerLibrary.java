package jsoft.ads.customer;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ads.user.USER_SORT_TYPE;
import jsoft.ads.user.UserLibrary;
import jsoft.library.Utilities;
import jsoft.objects.CustomerObject;

public class CustomerLibrary {

	/*public static ArrayList<String> viewCustomer(
			Pair<ArrayList<CustomerObject>, Integer> data,
			Quartet<CustomerObject, Short, Byte, CUSTOMER_SORT_TYPE> infors){
		
		ArrayList<CustomerObject> items = data.getValue0();
		int total = data.getValue1();
		CustomerObject cus = infors.getValue0();
		short page = infors.getValue1();
		byte totalperpage = infors.getValue2();	
		
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Table with hoverable rows -->");
        tmp.append("<table class=\"table table-hover\">");
          tmp.append("<thead>");
            tmp.append("<tr>");
              tmp.append("<th class=\"text-center\" scope=\"col\">ID</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Họ tên</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Tên đăng nhập</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Hộp thư</th>");
        	  tmp.append("<th class=\"text-center\" scope=\"col\">Điện thoại</th>");
        	  tmp.append("<th class=\"text-center\" scope=\"col\">Ngày cập nhật</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\" colspan=\"3\">Thực hiện</th>");
            tmp.append("</tr>");
          tmp.append("</thead>");
          tmp.append("<tbody>");
            
            items.forEach(item -> {
            	tmp.append("<tr>");
	            	tmp.append("<th scope=\"row\" class=\"text-center\">"+item.getCustomer_id()+"</th>");
	                tmp.append("<td class=\"text-center\">"+Utilities.decode(item.getCustomer_fullname())+"</td>");
	                tmp.append("<td class=\"text-center\">"+item.getCustomer_username()+"</td>");
	                tmp.append("<td class=\"text-center\">"+item.getCustomer_email()+"</td>");
                	tmp.append("<td class=\"text-center\">"+item.getCustomer_phone()+"</td>");
                	tmp.append("<td class=\"text-center\">"+item.getCustomer_modified_date()+"</td>");
	                if(cus.isCustomer_isactive()) {
	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoCus"+item.getCustomer_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewInfoCustomer(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-warning btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editCus"+item.getCustomer_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewEditCustomer(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#disCus"+item.getCustomer_id()+"\"><i class=\"bi bi-dash-circle\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewDisCustomer(item));
	                }else {
	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoCus"+item.getCustomer_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewInfoCustomer(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-success btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#enbCus"+item.getCustomer_id()+"\"><i class=\"bi bi-bootstrap-reboot\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewEnbCustomer(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delCus"+item.getCustomer_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewDelCustomer(item));
	                }
	                
	                tmp.append("</tr>");
            });
              

          tmp.append("</tbody>");
        tmp.append("</table>");
        tmp.append("<!-- End Table with hoverable rows -->");
				
        ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		//Lấy từ khóa tìm kiếm
		String key = cus.getCustomer_fullname();
		//Cấu trúc phân trang
		String url = "/btl/customer/list";
		if(cus.isCustomer_isactive()) {
			url += "?";
			if(key!=null && !"".equalsIgnoreCase(key)) {
				url += "key="+key+"&";
			}
		}else {
			url += "?dis&";
			if(key!=null && !"".equalsIgnoreCase(key)) {
				url += "key="+key+"&";
			}
		}
		String paging = UserLibrary.getPagination(url, total, page, totalperpage).toString();
		view.add(paging);
		
//		//Biểu đồ
//		String chart = CustomerLibrary.creatChart(items).toString();
//		view.add(chart);
		
		return view;
	}*/

	public static ArrayList<String> viewCustomer(
			Pair<ArrayList<CustomerObject>, Integer> data,
			Quartet<CustomerObject, Short, Byte, CUSTOMER_SORT_TYPE> infors){
		
		ArrayList<CustomerObject> items = data.getValue0();
		int total = data.getValue1();
		CustomerObject cus = infors.getValue0();
		/*short page = infors.getValue1();
		byte totalperpage = infors.getValue2();	
		*/
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Table with hoverable rows -->");
        tmp.append("<table id=\"example\" class=\"table table-hover\">");
          tmp.append("<thead>");
            tmp.append("<tr>");
              tmp.append("<th class=\"text-center\" scope=\"col\">ID</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Họ tên</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Tên đăng nhập</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Hộp thư</th>");
        	  tmp.append("<th class=\"text-center\" scope=\"col\">Điện thoại</th>");
        	  tmp.append("<th class=\"text-center\" scope=\"col\">Ngày cập nhật</th>");
        	  //tmp.append("<th style=\"display: none\"></th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Thêm</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Sửa</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Xóa</th>");
              //tmp.append("<th style=\"display: none\"></th>");
              //tmp.append("<th style=\"display: none\">AAAA</th>");
            tmp.append("</tr>");
          tmp.append("</thead>");
          tmp.append("<tbody>");
            
            items.forEach(item -> {
            	tmp.append("<tr>");
	            	tmp.append("<th scope=\"row\" class=\"text-center\">"+item.getCustomer_id()+"</th>");
	                tmp.append("<td class=\"text-center\">"+Utilities.decode(item.getCustomer_fullname())+"</td>");
	                tmp.append("<td class=\"text-center\">"+item.getCustomer_username()+"</td>");
	                tmp.append("<td class=\"text-center\">"+item.getCustomer_email()+"</td>");
                	tmp.append("<td class=\"text-center\">"+item.getCustomer_phone()+"</td>");
                	tmp.append("<td class=\"text-center\">"+item.getCustomer_modified_date()+"</td>");
	                if(cus.isCustomer_isactive()) {
	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoCus"+item.getCustomer_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewInfoCustomer(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-warning btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editCus"+item.getCustomer_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewEditCustomer(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#disCus"+item.getCustomer_id()+"\"><i class=\"bi bi-dash-circle\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewDisCustomer(item));
	                }else {
	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoCus"+item.getCustomer_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewInfoCustomer(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-success btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#enbCus"+item.getCustomer_id()+"\"><i class=\"bi bi-bootstrap-reboot\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewEnbCustomer(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delCus"+item.getCustomer_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
		                tmp.append(CustomerLibrary.viewDelCustomer(item));
	                }
	                //tmp.append("<td style=\"display: none\"></td>");
	                tmp.append("</tr>");
            });
              
            
          tmp.append("</tbody>");
        tmp.append("</table>");
        tmp.append("<!-- End Table with hoverable rows -->");
				
        ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		//Lấy từ khóa tìm kiếm
		String key = cus.getCustomer_fullname();
		/*//Cấu trúc phân trang
		String url = "/btl/customer/list";
		if(cus.isCustomer_isactive()) {
			url += "?";
			if(key!=null && !"".equalsIgnoreCase(key)) {
				url += "key="+key+"&";
			}
		}else {
			url += "?dis&";
			if(key!=null && !"".equalsIgnoreCase(key)) {
				url += "key="+key+"&";
			}
		}
		String paging = UserLibrary.getPagination(url, total, page, totalperpage).toString();
		view.add(paging);*/
		
//		//Biểu đồ
//		String chart = CustomerLibrary.creatChart(items).toString();
//		view.add(chart);
		
		return view;
	}
	
	private static StringBuilder viewInfoCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		tmp.append("<!-- Modal -->");
		tmp.append("<div class=\"modal fade\" id=\"infoCus"+item.getCustomer_id()+"\" tabindex=\"-1\" aria-labelledby=\"infoCus\" aria-hidden=\"true\">");
		  tmp.append("<div class=\"modal-dialog modal-lg\">");	  
		    tmp.append("<div class=\"modal-content\">");
		    
		      tmp.append("<div class=\"modal-header\">");
				tmp.append("<h1 class=\"modal-title fs-5\" id=\"infoUserLabel\"><i class=\"bi bi-person-vcard\"></i> Thông tin tài khoản khách hàng <b>"+Utilities.decode(item.getCustomer_fullname())+ " / " + item.getCustomer_username() +"</b></h1>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		      tmp.append("</div>");
		      
		      tmp.append("<div class=\"modal-body\">");
		      
				tmp.append("<div class=\"row mb-3\">");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-name\" class=\"form-label\">Tên tài khoản</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-name\" name=\"txtUserName\" value=\""+item.getCustomer_username()+"\" disabled readonly>");	
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"full-name\" class=\"form-label\">Họ tên</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"full-name\" name=\"txtFullName\" value=\""+Utilities.decode(item.getCustomer_fullname())+"\"disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"email-address\" class=\"form-label\">Hộp thư</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"email-address\" required name=\"txtEmail\" value=\""+item.getCustomer_email()+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" required name=\"txtPhone\" value=\""+item.getCustomer_phone()+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Địa chỉ</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\" required name=\"txtAddress\" value=\""+item.getCustomer_address()+"\" disabled readonly>");
				tmp.append("</div>");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Công việc</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\" required name=\"txtJob\" value=\""+item.getCustomer_jobarea()+"\" disabled readonly>");
				tmp.append("</div>");
				tmp.append("</div>");

				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-12\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Ghi chú</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\" required name=\"txtNote\" size=\"3\" value=\""+item.getCustomer_notes()+"\" disabled readonly>");
				tmp.append("</div>");
				tmp.append("</div>");

			  tmp.append("</div>");//modal-body
			  
		      tmp.append("<div class=\"modal-footer\">");
		      if(!item.isCustomer_isactive()) {
		    	  tmp.append("`<button class=\"btn btn-success\" data-bs-toggle=\"modal\" data-bs-target=\"#enbCus"+item.getCustomer_id()+"\"><i class=\"bi bi-bootstrap-reboot\"></i> Kích hoạt</button>");
	                //tmp.append(CustomerLibrary.viewEnbCustomer(item));
		    	  tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
		      }else {
		    	  tmp.append("<button class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#editCus"+item.getCustomer_id()+"\"><i class=\"bi bi-person-gear\"></i> Chỉnh sửa</button>");
		    	  tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
		      }
		      
		      tmp.append("</div>");//modal-footer
		    tmp.append("</div>");//modal-content
		  tmp.append("</div>");//modal-dialog
		tmp.append("</div>");//modal fade
		return tmp;
	}
	private static StringBuilder viewEditCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		tmp.append("<!-- Modal -->");
		tmp.append("<div class=\"modal fade\" id=\"editCus"+item.getCustomer_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\"  tabindex=\"-1\" aria-labelledby=\"editCus\" aria-hidden=\"true\">");
		  tmp.append("<div class=\"modal-dialog modal-lg\">");	  
		    tmp.append("<div class=\"modal-content\">");
		    tmp.append("<form method=\"post\" action=\"/btl/customer/list\" class=\"needs-validation\" novalidate>");
		      tmp.append("<div class=\"modal-header\">");
				tmp.append("<h1 class=\"modal-title fs-5\" id=\"infoUserLabel\"><i class=\"bi bi-person-vcard\"></i> Thông tin tài khoản khách hàng <b>"+ Utilities.decode(item.getCustomer_fullname())+ " / " + item.getCustomer_username() +"</b></h1>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		      tmp.append("</div>");
		      tmp.append("<div class=\"modal-body\">");
		      
				tmp.append("<div class=\"row mb-3\">");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-name\" class=\"form-label\">Tên tài khoản</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-name\" name=\"txtUserName\" value=\""+item.getCustomer_username()+"\" disabled readonly>");	
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"full-name\" class=\"form-label\">Họ tên</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"full-name\" name=\"txtFullName\" value=\""+Utilities.decode(item.getCustomer_fullname())+"\" required>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"email-address\" class=\"form-label\">Hộp thư</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"email-address\"  name=\"txtEmail\" value=\""+item.getCustomer_email()+"\" >");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" required name=\"txtPhone\" value=\""+item.getCustomer_phone()+"\"required>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Địa chỉ</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\"  name=\"txtAddress\" value=\""+item.getCustomer_address()+"\" >");
				tmp.append("</div>");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Công việc</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\"  name=\"txtJob\" value=\""+item.getCustomer_jobarea()+"\" >");
				tmp.append("</div>");
				tmp.append("</div>");

				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-12\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Ghi chú</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\"  name=\"txtNote\" size=\"3\" value=\""+item.getCustomer_notes()+"\" >");
				tmp.append("</div>");
				tmp.append("</div>");
		      tmp.append("</div>");//modal-body
		      tmp.append("<input type=\"hidden\" name =\"idForPost\" value=\""+item.getCustomer_id()+"\"  id=\"\">");
				tmp.append("<input type=\"hidden\" name =\"act\" value=\"edit\">");
		      tmp.append("<div class=\"modal-footer\">");
		      tmp.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-check2\"></i> Hoàn tất</button>");
				tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
				
		      tmp.append("</div>");//modal-footer
		    tmp.append("</div>");//modal-content
		    tmp.append("</form>");
		  tmp.append("</div>");//modal-dialog
		tmp.append("</div>");//modal fade
		return tmp;
	}

	public static StringBuilder viewAddCustomer() {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		tmp.append("<!-- Modal -->"); 
		tmp.append("<div class=\"modal fade\" id=\"addCus\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\"  tabindex=\"-1\" aria-labelledby=\"editCus\" aria-hidden=\"true\">");
		  tmp.append("<div class=\"modal-dialog modal-lg\">");	  
		    tmp.append("<div class=\"modal-content\">");
		    tmp.append("<form method=\"post\" action=\"/btl/customer/list\" class=\"needs-validation\" novalidate>");
		      tmp.append("<div class=\"modal-header\">");
				tmp.append("<h1 class=\"modal-title fs-5\" id=\"infoUserLabel\"><i class=\"bi bi-person-plus\"></i> Thêm mới tài khoản khách hàng </h1>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		      tmp.append("</div>");
		      tmp.append("<div class=\"modal-body\">");
		      
				tmp.append("<div class=\"row mb-3\">");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-name\" class=\"form-label\">Tên tài khoản</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-name\" name=\"txtUserName1\" >");	
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"full-name\" class=\"form-label\">Họ tên</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"full-name\" name=\"txtFullName1\" required>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"email-address\" class=\"form-label\">Hộp thư</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"email-address\" name=\"txtEmail1\" >");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" name=\"txtPhone1\" required>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Địa chỉ</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\" name=\"txtAddress1\" >");
				tmp.append("</div>");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Công việc</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\" name=\"txtJob1\">");
				tmp.append("</div>");
				tmp.append("</div>");

				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-12\">");
				tmp.append("<label for=\"user-permission\" class=\"form-label\">Ghi chú</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-permis\" name=\"txtNote1\" height=\"3\" >");
				tmp.append("</div>");
				tmp.append("</div>");
		      tmp.append("</div>");//modal-body
		      tmp.append("<div class=\"modal-footer\">");
		      tmp.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-check2\"></i> Thêm mới</button>");
				tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
				
		      tmp.append("</div>");//modal-footer
		    tmp.append("</div>");//modal-content
		    tmp.append("</form>");
		  tmp.append("</div>");//modal-dialog
		tmp.append("</div>");//modal fade
		return tmp;
	}
	
	private static StringBuilder viewEnbCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"modal fade\" id=\"enbCus"+item.getCustomer_id()+"\" tabindex=\"-1\" aria-labelledby=\"ResUserLabel\"+item.getCustomer_id()+\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"ResUserLabel"+item.getCustomer_id()+"\"><i class=\"bi bi-bootstrap-reboot\"></i> Kích hoạt tài khoản khách hàng <b>"+item.getCustomer_fullname()+" / "+ item.getCustomer_username()+"</b></h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row mb-3\">");
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("Bạn có chắc chắn muốn kích hoạt tài khoản của <b>").append(Utilities.decode(item.getCustomer_fullname())).append(" / "+item.getCustomer_username()+"</b>?<br>");
		tmp.append("Khách hàng có thể sử dụng tài khoản để đăng nhập và đăng ký lịch khám.");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");

		tmp.append("<a href=\"/btl/customer/dr?id="+item.getCustomer_id()+"&name="+Utilities.decode(item.getCustomer_fullname())+"&r\" class=\"btn btn-primary\"><i class=\"bi bi-bootstrap-reboot\"></i> Kích hoạt</a>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}

	private static StringBuilder viewDisCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"modal fade\" id=\"disCus"+item.getCustomer_id()+"\" tabindex=\"-1\" aria-labelledby=\"ResUserLabel\"+item.getCustomer_id()+\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"ResUserLabel"+item.getCustomer_id()+"\"><i class=\"bi bi-person-lock\"></i> Vô hiệu hóa tài khoản khách hàng <b>"+item.getCustomer_fullname()+" / "+ item.getCustomer_username()+"</b></h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row mb-3\">");
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("Bạn có chắc chắn muốn vô hiệu hóa tài khoản của <b>").append(Utilities.decode(item.getCustomer_fullname())).append(" / "+item.getCustomer_username()+"</b>?<br>");
		tmp.append("Khách hàng không thể sử dụng tài khoản để đăng nhập và đăng ký lịch khám.");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");

		tmp.append("<a href=\"/btl/customer/dr?id="+item.getCustomer_id()+"&name="+Utilities.decode(item.getCustomer_fullname())+"&d\" class=\"btn btn-warning\"><i class=\"bi bi-person-lock\"></i> Vô hiệu hóa</a>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}

	private static StringBuilder viewDelCustomer(CustomerObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"modal fade\" id=\"delCus"+item.getCustomer_id()+"\" tabindex=\"-1\" aria-labelledby=\"ResUserLabel\"+item.getCustomer_id()+\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"ResUserLabel"+item.getCustomer_id()+"\"><i class=\"bi bi-person-x-fill\"></i> Xóa tài khoản khách hàng <b>"+item.getCustomer_fullname()+" / "+ item.getCustomer_username()+"</b></h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row mb-3\">");
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("Bạn có chắc chắn muốn xóa vĩnh viễn tài khoản của <b>").append(Utilities.decode(item.getCustomer_fullname())).append(" / "+item.getCustomer_username()+"</b>?<br>");
		tmp.append("Tài khoản của khách hàng sẽ không thể khôi phục được nữa.");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");

		tmp.append("<a href=\"/btl/customer/dr?id="+item.getCustomer_id()+"&name="+Utilities.decode(item.getCustomer_fullname())+"\" class=\"btn btn-danger\"><i class=\"bi bi-person-x\"></i> Xóa vĩnh viễn</a>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}

		
}
