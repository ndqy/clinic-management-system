package jsoft.ads.service;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.*;
import jsoft.ads.user.UserLibrary;
import jsoft.library.Utilities;
import jsoft.objects.ServiceObject;
import jsoft.objects.UserObject;
public class ServiceLibrary {

	public static ArrayList<String> viewServices(
			Pair<ArrayList<ServiceObject>,Integer> data, 
			Quartet<ServiceObject, Short, Byte, SERVICE_SORT_TYPE> infors
			){
		
		ArrayList<ServiceObject> items = data.getValue0(); // Danh sách dịch vụ
		int total = data.getValue1(); //Tổng số các dịch vụ
		
		ServiceObject ser = infors.getValue0();
		short page = infors.getValue1();
		byte totalpp = infors.getValue2();
		
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Table with hoverable rows -->");
        tmp.append("<table class=\"table table-hover\">");
          tmp.append("<thead>");
            tmp.append("<tr>");
              tmp.append("<th class=\"text-center\" scope=\"col\">ID</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Tên dịch vụ</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Giá</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Khuyến mại</th>");
        	  tmp.append("<th class=\"text-center\" scope=\"col\">Thời gian dự kiến</th>");
        	  tmp.append("<th class=\"text-center\" scope=\"col\">Ngày cập nhật</th>");
              
              tmp.append("<th class=\"text-center\" scope=\"col\" colspan=\"3\">Thực hiện</th>");
            tmp.append("</tr>");
          tmp.append("</thead>");
          tmp.append("<tbody>");
            
            items.forEach(item -> {
            	tmp.append("<tr>");
	            	tmp.append("<th scope=\"row\" class=\"text-center\">"+item.getService_id()+"</th>");
	                tmp.append("<td class=\"text-center\">"+Utilities.decode(item.getService_name())+"</td>");
                	tmp.append("<td class=\"text-center\">"+item.getService_price()+"</td>");
                	tmp.append("<td class=\"text-center\">"+item.getService_discount_price()+"</td>");
                	tmp.append("<td class=\"text-center\">"+item.getService_expected_time()+"</td>");
                	tmp.append("<td class=\"text-center\">"+item.getService_modified_date()+"</td>");
	                if(ser.isService_delete()) {
	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoService"+item.getService_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
		                tmp.append(ServiceLibrary.viewInfoService(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#resService"+item.getService_id()+"\"><i class=\"bi bi-bootstrap-reboot\"></i></button></td>");
		                tmp.append(ServiceLibrary.viewResService(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delService"+item.getService_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
		                tmp.append(ServiceLibrary.viewDelService(item));
	                }else {
	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoService"+item.getService_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
		                tmp.append(ServiceLibrary.viewInfoService(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-warning btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editService"+item.getService_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
		                tmp.append(ServiceLibrary.viewEditService(item));
		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delService"+item.getService_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
		                tmp.append(ServiceLibrary.viewDelService(item));
	                }
	                
	                tmp.append("</tr>");
            });
              

          tmp.append("</tbody>");
        tmp.append("</table>");
        tmp.append("<!-- End Table with hoverable rows -->");
		
        ArrayList<String> view = new ArrayList<String>();
        
        view.add(tmp.toString());
        //Lấy từ khóa tìm kiếm
        String key = ser.getService_name();
        //Cấu trúc phân trang
  		String url = "/btl/service/list";
  		if(ser.isService_delete()) {
  			url += "?trash&";
  			if(key!=null && !"".equalsIgnoreCase(key)) {
  				url += "key="+key+"&";
  			}
  		}else {
  			url += "?";
  			if(key!=null && !"".equalsIgnoreCase(key)) {
  				url += "key="+key+"&";
  			}
  		}
  		String paging = UserLibrary.getPagination(url, total, page, totalpp).toString(); // Đường dẫn - Tổng số bản ghi - trang hiện tại - số bản ghi/trang
		view.add(paging);
		
		return view;
		
	}

	private static StringBuilder viewInfoService(ServiceObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Modal -->");
		tmp.append("<div class=\"modal fade\" id=\"infoService"+item.getService_id()+"\" tabindex=\"-1\" aria-labelledby=\"editUserLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"infoServiceLabel\"><i class=\"bi bi-prescription2\"></i> Thông tin dịch vụ <b>"+ Utilities.decode(item.getService_name())+ "</b></h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		tmp.append("<div class=\"row mb-3\">");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"user-name\" class=\"form-label\">Tên dịch vụ</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-serivce\" name=\"txtSerName\" value=\""+Utilities.decode(item.getService_name())+"\" disabled readonly required>");
		tmp.append("</div>");
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"full-name\" class=\"form-label\">Thời gian dự kiến</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"time_service\" required name=\"txtSerTime\" value=\""+item.getService_expected_time()+"\" disabled readonly>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mb-3\">");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"price\" class=\"form-label\">Giá</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"price\" required name=\"txtPrice\" value=\""+item.getService_price()+"\" disabled readonly>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"discount\" class=\"form-label\">Giảm giá</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"discount\"  name=\"txtDiscount\" value=\""+item.getService_discount_price()+"\" disabled readonly>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("<div class=\"row mb-3\">");
		
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("<label for=\"note\" class=\"form-label\">Ghi chú</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"note\" name=\"txtNotes\" value=\""+Utilities.decode(item.getService_notes())+"\" disabled readonly>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("</div>");
		
		//==========================================
		//tmp.append("<input type=\"hidden\" name =\"idForPost\" value=\""+item.getService_id()+"\"  id=\"\">");
		//tmp.append("<input type=\"hidden\" name =\"act\" value=\"edit\">");
		tmp.append("<div class=\"modal-footer\">");
		tmp.append("<button class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#editService"+item.getService_id()+"\"><i class=\"bi bi-person-gear\"></i> Chỉnh sửa</button>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");

		tmp.append("</div>");
		tmp.append("</div>");
		//tmp.append("</form>");
		tmp.append("</div>");
		tmp.append("</div>");
		return tmp;
	}

	private static StringBuilder viewEditService(ServiceObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Modal -->");
		tmp.append("<div class=\"modal fade\" id=\"editService"+item.getService_id()+"\" tabindex=\"-1\" aria-labelledby=\"editSericeLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");

		tmp.append("<form method=\"post\" action=\"/btl/service/list\" class=\"needs-validation\" novalidate>");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"editServiceLabel\"><i class=\"bi bi-prescription2\"></i> Chỉnh sửa thông tin dịch vụ <b>"+ Utilities.decode(item.getService_name())+ "</b></h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		tmp.append("<div class=\"row mb-3\">");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"user-name\" class=\"form-label\">Tên dịch vụ</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"user-serivce\" name=\"txtSerName\" value=\""+Utilities.decode(item.getService_name())+"\" required>");
		tmp.append("</div>");
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"full-name\" class=\"form-label\">Thời gian dự kiến</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"time_service\" required name=\"txtSerTime\" value=\""+item.getService_expected_time()+"\" required>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mb-3\">");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"price\" class=\"form-label\">Giá</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"price\" required name=\"txtPrice\" value=\""+item.getService_price()+"\" required>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<label for=\"discount\" class=\"form-label\">Giảm giá</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"discount\"  name=\"txtDiscount\" value=\""+item.getService_discount_price()+"\">");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("<div class=\"row mb-3\">");
		
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("<label for=\"note\" class=\"form-label\">Ghi chú</label>");
		tmp.append("<input type=\"text\" class=\"form-control\" id=\"note\" name=\"txtNotes\" value=\""+Utilities.decode(item.getService_notes())+"\">");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("</div>");
		
		//==========================================
		tmp.append("<input type=\"hidden\" name =\"idForPost\" value=\""+item.getService_id()+"\"  id=\"\">");
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
	private static StringBuilder viewResService(ServiceObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"modal fade\" id=\"resService"+item.getService_id()+"\" tabindex=\"-1\" aria-labelledby=\"ResServiceLabel\"+item.getService_id()+\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"ResUserLabel"+item.getService_id()+"\"><i class=\"bi bi-bootstrap-reboot\"></i> Khôi phục dịch vụ <b>"+ Utilities.decode(item.getService_name())+" </b></h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("Bạn có chắc chắn muốn khôi phục dịch vụ <b>").append(Utilities.decode(item.getService_name())).append(" </b>?<br>");
		tmp.append("Dịch vụ sẽ được khôi khục lại. Khách hàng có thể đăng ký được lại dịch vụ này");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");

		tmp.append("<a href=\"/btl/service/dr?id="+item.getService_id()+"&name="+Utilities.decode(item.getService_name())+"&r\" class=\"btn btn-primary\"><i class=\"bi bi-bootstrap-reboot\"></i> Khôi phục</a>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}
	private static StringBuilder viewDelService(ServiceObject item) {
		StringBuilder tmp = new StringBuilder();
		String title;
		if(item.isService_delete()) {
			title = "Xóa vĩnh viễn ";
		}else {
			title = "Xóa dịch vụ ";
		}
		
		tmp.append("<div class=\"modal fade\" id=\"delService"+item.getService_id()+"\" tabindex=\"-1\" aria-labelledby=\"DelUserLabel\"+item.getService_id()+\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"DelServiceLabel"+item.getService_id()+"\"><i class=\"bi bi-trash3\"></i> "+title+"</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		if(item.isService_delete()) {
			tmp.append("Bạn có chắc chắn xóa vĩnh viễn dịch vụ <b>").append(Utilities.decode(item.getService_name())).append(" </b>?<br>");
			tmp.append("Dịch vụ này sẽ không thể khôi phục được nữa.");
		}else {
			tmp.append("Bạn có chắc chắn xóa dịch vụ <b>").append(Utilities.decode(item.getService_name())).append(" </b>?<br>");
			tmp.append("Hệ thống tạm thời lưu vào thùng rác, có thể phục hồi trong vòng 30 ngày.");
		}
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		if(item.isService_delete()) {
			tmp.append("<a href=\"/btl/service/dr?id="+item.getService_id()+"&name="+Utilities.decode(item.getService_name())+"\" class=\"btn btn-danger\"><i class=\"bi bi-trash3\"></i> Xóa vĩnh viễn</a>");
		}else {
			tmp.append("<a href=\"/btl/service/dr?id="+item.getService_id()+"&name="+Utilities.decode(item.getService_name())+"&t\" class=\"btn btn-danger\"><i class=\"bi bi-trash3\"></i> Xóa</a>");
		}
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}
	
	
}
