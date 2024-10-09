package jsoft.ads.appt;

import jsoft.ads.user.UserLibrary;
import java.util.*;
import jsoft.objects.*;
import org.javatuples.*;
public class ApptLibrary {

	public static ArrayList<String> viewAppt(
			Pair<ArrayList<ApptObject>, Integer> data,
			Triplet<ApptObject, Short, Byte> infors,
			Pair<ArrayList<ServiceObject>, ArrayList<UserObject>> list){
		//Danh sách
		ArrayList<ApptObject> items = data.getValue0();
		//Tổng số bản ghi
		int total = data.getValue1();
		ApptObject appt = infors.getValue0();
		short page = infors.getValue1();
		byte totalperpage = infors.getValue2();	
		ArrayList<ServiceObject> sers = list.getValue0();
		ArrayList<UserObject> dens = list.getValue1();
		
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Table with hoverable rows -->");
        tmp.append("<table class=\"table table-hover\">");
          tmp.append("<thead>");
            tmp.append("<tr>");
              tmp.append("<th class=\"text-center\" scope=\"col\"><input class=\"form-check-input\" type=\"checkbox\" id=\"checkAll\" data-bs-dismiss=\"modal\" name=\"checkAll\" value=\"all\"></th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">STT</th>");
              tmp.append("<th class=\"text-left\" scope=\"col\">Họ và tên</th>");
              tmp.append("<th class=\"text-right\" scope=\"col\">Số điện thoại</th>");
              tmp.append("<th class=\"text-left\" scope=\"col\">Dịch vụ</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Ngày khám</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Giới tính</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Xóa</th>");
            tmp.append("</tr>");
          tmp.append("</thead>");
          tmp.append("<tbody>");
             if(items.size()>0) {
            	 ApptObject item = null;
            	tmp.append("<form method=\"get\" action=\"/btl/appt/del\" novalidate>");
            	for(int i=0; i<items.size(); i++) {
            		item = items.get(i);
            		tmp.append("<tr>");
             	 	tmp.append("<td scope=\"row\" class=\"text-center\"><input class=\"checkByID form-check-input\" type=\"checkbox\" name=\"checkId"+item.getAppt_id()+"\"></td>");
         			tmp.append("<td scope=\"row\" data-bs-toggle=\"modal\" data-bs-target=\"#updateAppt\" class=\"text-center\">"+(i+1)+"</td>");
 	                tmp.append("<td class=\"text-left\" data-bs-toggle=\"modal\" data-bs-target=\"#updateAppt\">"+item.getAppt_fullname()+"</td>");
 	                tmp.append("<td class=\"text-right\" data-bs-toggle=\"modal\" data-bs-target=\"#updateAppt\">"+item.getAppt_phone()+"</td>");
 	                tmp.append("<td class=\"text-left\" data-bs-toggle=\"modal\" data-bs-target=\"#updateAppt\">"+item.getAppt_note()+"</td>");
                 	tmp.append("<td class=\"text-center\" data-bs-toggle=\"modal\" data-bs-target=\"#updateAppt\">"+item.getAppt_date()+"</td>");
                 	tmp.append("<td class=\"text-center\" data-bs-toggle=\"modal\" data-bs-target=\"#updateAppt\">"+(item.isAppt_gender() ? "Nam" : "Nữ")+"</td>");
                 	tmp.append("<td class=\"text-center\"><button type=\"button\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delAppt"+item.getAppt_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
	                tmp.append("</tr>");
	                tmp.append("<input type=\"hidden\" name=\"name"+item.getAppt_id()+"\" value=\""+item.getAppt_fullname()+"\">");
            	}
            	tmp.append("<div class=\"row d-flex justify-content-end\">");
            	//tmp.append("<div class=\"col\">");
            	tmp.append("<button type=\"button\" disabled id=\"btnDelListAppt\" data-bs-toggle=\"modal\" data-bs-target=\"#delListAppt\" class=\"btn btn-danger col-1 btn-sm mt-3 me-3\"><i class=\"bi bi-calendar2-x\"></i>    Xóa</button>");
            	//tmp.append("</div>");
            	tmp.append(ApptLibrary.viewDelListAppt());
            	tmp.append("</div>");
            	tmp.append("</form>");
            	items.forEach(item1->{
            		tmp.append(ApptLibrary.viewDelAppt(item1));
	                tmp.append(ApptLibrary.viewUpdateAppt(item1, sers, dens).toString());
            	});
             }else {
            	 tmp.append("<tr>");
                 tmp.append("<td class=\"text-center\" scope=\"col\"><input class=\"checkByID form-check-input\" type=\"checkbox\" id=\"checkID\" name=\"checkAll\" value=\"all\"></td>");
                   tmp.append("<td class=\"text-center\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-left\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-right\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-left\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-center\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-center\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-center\" scope=\"col\">Đang cập nhật</td>");
                 tmp.append("</tr>");
                 
             }
            
          tmp.append("</tbody>");
        tmp.append("</table>");
        
        tmp.append("<!-- End Table with hoverable rows -->");
				
        ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		//Lấy từ khóa tìm kiếm 
		String key = appt.getAppt_fullname();
		
		//Cấu trúc phân trang
		String url = "/btl/appt/list";
		url += "?";
		if(key!=null && !"".equalsIgnoreCase(key)) {
			url += "key=" + key + "&";
		}
		
		//Phân trang
		String paging = UserLibrary.getPagination(url, total, page, totalperpage).toString();
		view.add(paging);
		
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
		
	private static StringBuilder viewUpdateAppt(ApptObject appt, 
			ArrayList<ServiceObject> arrSer,
			ArrayList<UserObject> arrDen) {
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Modal --> ");
		tmp.append("<form method=\"post\" action=\"/btl/appt/list\" class=\"needs-validation\" novalidate> ");
		tmp.append("<div class=\"modal fade\" id=\"updateAppt\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"  aria-hidden=\"true\"> ");
		tmp.append("<div class=\"modal-dialog modal-lg\"> ");
			tmp.append("<div class=\"modal-content\"> ");
				tmp.append("<div class=\"modal-header\"> ");
					tmp.append("<h1 class=\"modal-title fs-5\" id=\"editApptLabel\"><i class=\"bi bi-person-gear\"></i> Cập nhật thông tin lịch hẹn <b>"+ appt.getAppt_fullname() + "</b></h1> ");
					tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button> ");
				tmp.append("</div> ");
				tmp.append("<div class=\"modal-body\"> ");
				tmp.append("<div class=\"row mb-3\"> ");
					tmp.append("<div class=\"col-lg-6\"> ");
						tmp.append("<label for=\"appt-name\" class=\"form-label\">Họ tên</label> ");
						tmp.append("<input type=\"text\" class=\"form-control\" id=\"appt-name\" name=\"txtFullName2\" value=\""+appt.getAppt_fullname()+"\" required> ");
					tmp.append("</div> ");
					tmp.append("<div class=\"col-lg-6\"> ");
						tmp.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label> ");
						tmp.append("<input type=\"tel\" minlength=\"10\" maxlength=\"10\" class=\"form-control\" id=\"phone-number\" name=\"txtPhoneNumber2\" value=\""+appt.getAppt_phone()+"\" required> ");
					tmp.append("</div> ");
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\"> ");
					tmp.append("<div class=\"col-lg-6\"> ");
						tmp.append("<label for=\"gender\" class=\"form-label\">Giới tính</label> ");
						tmp.append("<select class=\"form-select\" required id=\"gender\" name=\"slcGender2\"> ");
						if(appt.isAppt_gender()) {
							tmp.append("<option value=\"1\" selected>Nam</option> ");
							tmp.append("<option value=\"0\">Nữ</option> ");
						}else {
							tmp.append("<option value=\"0\" selected>Nữ</option> ");
							tmp.append("<option value=\"1\">Nam</option> ");
						}
						tmp.append("</select> ");
					tmp.append("</div> ");
					tmp.append("<div class=\"col-lg-6\"> ");
						tmp.append("<label for=\"address\" class=\"form-label\">Địa chỉ</label> ");
						tmp.append("<input type=\"text\" class=\"form-control\" id=\"address\" name=\"txtAddress\" value=\"\"> ");
					tmp.append("</div> ");
				tmp.append("</div> ");
				tmp.append("<div class=\"row mb-3\"> ");
					tmp.append("<div class=\"col-lg-4\"> ");
						tmp.append("<label for=\"appt-date\" class=\"form-label\">Ngày khám </label> ");
						tmp.append("<input type=\"date\" class=\"form-control\" id=\"appt-date\" name=\"txtDate2\" value=\""+appt.getAppt_date()+"\"> ");
					tmp.append("</div> ");
					tmp.append("<div class=\"col-lg-4\"> ");
						tmp.append("<label for=\"appt-time\" class=\"form-label\">Thời gian </label> ");
						tmp.append("<input type=\"time\" class=\"form-control\" id=\"appt-time\" name=\"txtTime\"> ");
					tmp.append("</div>");
					tmp.append("<div class=\"col-lg-4\"> ");
						tmp.append("<label for=\"appt-room\" class=\"form-label\">Phòng </label> ");
						tmp.append("<select class=\"form-select\" id=\"appt-room\" name=\"slcRoom\" required> ");
						tmp.append("<option value=\"0\">Chọn phòng</option>");
						for(int i = 1; i<6; i++) {
							tmp.append("<option value=\""+i+"\">Phòng "+i+" </option>");
						}
						tmp.append("</select>");
					tmp.append("</div>");
				tmp.append("</div> ");

				tmp.append("<div class=\"row mb-3\"> ");
					tmp.append("<div class=\"col-lg-6\"> ");
						tmp.append("<label for=\"service-name\" class=\"form-label\">Dịch vụ đăng ký</label> ");
						tmp.append("<select class=\"form-select\" id=\"service-name\" name=\"slcService\" required> ");
						tmp.append("<option value=\"0\">Chọn dịch vụ khám</option>");
						arrSer.forEach(item-> {
							tmp.append("<option value=\""+item.getService_id()+"\">"+item.getService_name()+" </option>");
						});
						tmp.append("</select>");
					tmp.append("</div> ");
					tmp.append("<div class=\"col-lg-6\"> ");
						tmp.append("<label for=\"dentist-name\" class=\"form-label\">Bác sĩ</label> ");
						tmp.append("<select class=\"form-select\"  id=\"dentist-name\" name=\"slcDentist\" required> ");
						tmp.append("<option value=\"0\">Chọn bác sĩ</option>");
						arrDen.forEach(item -> {
							tmp.append("<option value=\""+item.getUser_id()+"\">"+item.getUser_fullname()+" </option>");
						});
						tmp.append("</select> ");
					tmp.append("</div> 		");
				tmp.append("</div> ");

				tmp.append("<div class=\"row mb-3\"> ");
					tmp.append("<div class=\"col-lg-12\"> ");
						tmp.append("<label for=\"appt-note\" class=\"form-label\">Ghi chú</label> ");
						tmp.append("<textarea class=\"col-12 form-control\" id=\"appt-note\" name=\"txtApptNote2\" rows=\"2\" >"+appt.getAppt_note()+"</textarea> ");
						tmp.append("</div> ");
				tmp.append("</div> ");

			//==========================================
				tmp.append("<input type=\"hidden\" name =\"idForPost\" value=\""+appt.getAppt_id()+"\"> ");
			tmp.append("</div> ");
				tmp.append("<div class=\"modal-footer\"> ");
					tmp.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-check2\"></i> Hoàn tất</button> ");
					tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button> ");
				tmp.append("</div> ");
			tmp.append("</div> ");
			tmp.append("</div> ");
			tmp.append("</div> ");
		tmp.append("</form> ");
		return tmp;
	}
	
	private static StringBuilder viewDelAppt(ApptObject item) {
		StringBuilder tmp = new StringBuilder();

		tmp.append("<div class=\"modal fade\" id=\"delAppt"+item.getAppt_id()+"\" tabindex=\"-1\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
			tmp.append("<div class=\"modal-header\">");
				tmp.append("<h1 class=\"modal-title fs-5\" id=\"DelApptLabel"+item.getAppt_id()+"\"><i class=\"bi bi-trash3\"></i> Xóa</h1>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			tmp.append("</div>");
			tmp.append("<div class=\"modal-body\">");
				tmp.append("Bạn có chắc chắn xóa lịch hẹn của <b>").append(item.getAppt_fullname()).append(" </b>?<br>");
				tmp.append("Lịch hẹn không thể phục hồi được nữa.");
			tmp.append("</div>");
			tmp.append("<div class=\"modal-footer\">");
				tmp.append("<a href=\"/btl/appt/del?checkId"+item.getAppt_id()+"=on&name"+item.getAppt_id()+"="+item.getAppt_fullname()+"\" class=\"btn btn-danger\"><i class=\"bi bi-trash3\"></i> Xóa</a>");
				tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
			tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		return tmp;
	}
	
	private static StringBuilder viewDelListAppt() {
		StringBuilder tmp = new StringBuilder();

		tmp.append("<div class=\"modal fade\" id=\"delListAppt\" tabindex=\"-1\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
			tmp.append("<div class=\"modal-header\">");
				tmp.append("<h1 class=\"modal-title fs-5\" id=\"DelApptLabel\"><i class=\"bi bi-trash3\"></i> Xóa lịch hẹn đã chọn</h1>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			tmp.append("</div>");
			tmp.append("<div class=\"modal-body\">");
				tmp.append("Bạn có chắc chắn xóa những lịch hẹn <b>đã chọn</b> không?<br>");
				tmp.append("Lịch hẹn không thể phục hồi được nữa.");
			tmp.append("</div>");
			tmp.append("<div class=\"modal-footer\">");
				tmp.append("<button type=\"submit\" class=\"btn btn-danger\"><i class=\"bi bi-trash3\"></i> Xóa</a>");
				tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
			tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<script>");
		tmp.append("document.getElementById('checkAll').addEventListener('click', function() {");
			tmp.append("const collection = document.getElementsByClassName(\"checkByID\");");
			tmp.append("for (let i = 0; i < collection.length; i++) {");
				tmp.append("if (collection[i].type == \"checkbox\") {");
					tmp.append("collection[i].checked = this.checked;");
					tmp.append("document.getElementById(\"btnDelListAppt\").disabled = !this.checked;");
				tmp.append("}");
			tmp.append("}");
		tmp.append("});");
		tmp.append("const checkboxes = document.getElementsByClassName('checkByID');");
		tmp.append("console.log(checkboxes);");
		// Gắn sự kiện \"change\" cho từng checkbox"
		tmp.append("for (let i = 0; i < checkboxes.length; i++) {");
			tmp.append("checkboxes[i].addEventListener('change', function() {");
				tmp.append("let isDisable = true;");
				// Kiểm tra xem có checkbox nào được chọn không
				tmp.append("for (let j = 0; j < checkboxes.length; j++) {");
					tmp.append("if (checkboxes[j].type === \"checkbox\" && checkboxes[j].checked) {");
						tmp.append("isDisable = false;");
						tmp.append("break;");
					tmp.append("}");
				tmp.append("}");

				// Enable hoặc disable button dựa trên trạng thái của các checkbox"
				tmp.append("document.getElementById(\"btnDelListAppt\").disabled = isDisable;");
			tmp.append("});");
		tmp.append("}");
	tmp.append("</script>");
		return tmp;
	}
	
}
