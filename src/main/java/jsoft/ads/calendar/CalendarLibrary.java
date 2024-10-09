package jsoft.ads.calendar;

import java.util.*;
import org.javatuples.*;
import jsoft.ads.user.UserLibrary;
import jsoft.library.Utilities_date;
import jsoft.objects.CalendarObject;

public class CalendarLibrary {
	
	public static ArrayList<String>  getCalendars( 
			Septet<	ArrayList<CalendarObject>, // Danh sách lịch
					Integer,  // Tổng số bản ghi
					HashMap<Integer, ArrayList<String>>, //Tên dịch vụ
					HashMap<Integer, ArrayList<String>>, //Tên khách hàng
					HashMap<Integer, String>, // Tên nha sĩ
					HashMap<Integer, String>, // Danh sách các dịch vụ khám
					HashMap<Integer, String> // Danh sách nha sĩ
					> data, 
			Quintet<CalendarObject, Short, Byte, CALENDAR_GET_TYPE, String> infors, 
			String date,
			String viewtype) {
		
		//Chuẩn bị dữ liệu
		ArrayList<CalendarObject> items = data.getValue0();
		HashMap<Integer, ArrayList<String>> services = data.getValue2();
		HashMap<Integer, ArrayList<String>> customers = data.getValue3();
		HashMap<Integer, String> dentists = data.getValue4();
		
		HashMap<Integer, String> arrSer = data.getValue5();//Danh sách các dịch vụ khám
		HashMap<Integer, String> arrDen = data.getValue6();//Danh sách nha sĩ
		int total = data.getValue1();
		
		CalendarObject cal = infors.getValue0();
		
		
		StringBuilder tmp = new StringBuilder();
		//System.out.println(viewtype+"==========================");
		if(viewtype == null || viewtype.equalsIgnoreCase("grid") ) {			
			//System.out.println(viewtype+"=list");
			if(!items.isEmpty()) {
				tmp.append("<section id=\"portfolio-details\" class=\"portfolio-details\">");
			    tmp.append("<div class=\"row gy-4\">");
			    
			    items.forEach(item -> {
					tmp.append("<div class=\"col-md-3\">");
					//tmp.append("<div class=\"member\">");
						tmp.append("<div class=\"portfolio-info\" >");
							tmp.append("<div class=\"ctnbook\"  data-bs-toggle=\"modal\" data-bs-target=\"#infoCal"+item.getCalendar_id()+"\">");
								tmp.append("<h3>"+customers.get(item.getCalendar_customer_id()).get(0)+"</h3>");
								tmp.append("<ul>");
									tmp.append("<li><strong>Dịch vụ</strong>: "+services.get(item.getCalendar_service_id()).get(0)+"</li>");
									tmp.append("<li><strong>Thời gian</strong>: "+item.getCalendar_start_date().substring(11, 16) + " - " + item.getCalendar_end_date().substring(11, 16)+"</li>");
									tmp.append("<li><strong>Phòng</strong>: Phòng "+item.getCalendar_room_id()+"</li>");
									tmp.append("<li><strong>Nha sĩ</strong>: "+dentists.get(item.getCalendar_dentist_id())+"</li>");
								tmp.append("</ul>");
							tmp.append("</div>");
							tmp.append("<div class=\"social\">");
								tmp.append("<button class=\"btn btn-sm btn-warning\" id=\"btnnn\" data-bs-toggle=\"modal\" data-bs-target=\"#editCal"+item.getCalendar_id()+"\"><i class=\"bi bi-pencil-square\"></i></button>");
								tmp.append(CalendarLibrary.viewEditCal(item, services, customers, dentists, arrSer, arrDen).toString());
								tmp.append("<button class=\"btn btn-sm btn-danger\" id=\"btnnn\" data-bs-toggle=\"modal\" data-bs-target=\"#delCanlendar"+item.getCalendar_id()+"\"><i class=\"bi bi-trash3\"></i></button>");
								tmp.append(CalendarLibrary.viewDelCal(item).toString());
							tmp.append("</div>");
						tmp.append("</div>");
					//tmp.append("</div>");
				tmp.append("</div>");
				tmp.append(CalendarLibrary.viewInfoCal(item, services, customers,dentists).toString());
			    });
			}
		}else {
			if(!items.isEmpty()) {
            	tmp.append("<!-- Table with hoverable rows -->");
            	tmp.append("<table class=\"table table-hover\">");
            	tmp.append("<thead>");
                tmp.append("<tr>");
                  tmp.append("<th class=\"text-center\" scope=\"col\">ID</th>");
                  tmp.append("<th class=\"text-center\" scope=\"col\">Khách hàng</th>");
                  tmp.append("<th class=\"text-center\" scope=\"col\">Dịch vụ</th>");
                  tmp.append("<th class=\"text-center\" scope=\"col\">Thời gian</th>");
                  tmp.append("<th class=\"text-center\" scope=\"col\">Phòng</th>");
                  tmp.append("<th class=\"text-center\" scope=\"col\">Nha sĩ phụ trách</th>");
                  //if(cal.isCalendar_delete()) {
                	  //tmp.append("<th class=\"text-center\" scope=\"col\">Ngày xóa</th>");
                  //}else {
                	  tmp.append("<th class=\"text-center\" scope=\"col\">Điện thoại</th>");
                  //}
                  tmp.append("<th class=\"text-center\" scope=\"col\" colspan=\"3\">Thực hiện</th>");
                tmp.append("</tr>");
              tmp.append("</thead>");
              tmp.append("<tbody>");
            	items.forEach(item -> {
                	tmp.append("<tr>");
    	            	tmp.append("<th scope=\"row\" class=\"text-center\">"+item.getCalendar_id()+"</th>");
    	                tmp.append("<td class=\"text-center\">"+customers.get(item.getCalendar_customer_id()).get(0)+"</td>");
    	                tmp.append("<td class=\"text-center\">"+services.get(item.getCalendar_service_id()).get(0)+"</td>");
    	                tmp.append("<td class=\"text-center\">"+item.getCalendar_start_date().substring(11, 16) + " - " + item.getCalendar_end_date().substring(11, 16)+"</td>");
    	                tmp.append("<td class=\"text-center\"> Phòng "+item.getCalendar_room_id()+"</td>");
    	                tmp.append("<td class=\"text-center\">"+dentists.get(item.getCalendar_dentist_id())+"</td>");
//    	                if(cal.isCalendar_delete()) {
//    	                	tmp.append("<td class=\"text-center\">"+item.getCalendar_last_modified()+"</td>");
//    	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-secondary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoCal"+item.getCalendar_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
//    		                tmp.append(CalendarLibrary.viewInfoCal(item, services, customers,dentists).toString());
//    		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#resCal"+item.getCalendar_id()+"\"><i class=\"bi bi-bootstrap-reboot\"></i></button></td>");
//    		               // tmp.append(CalendarLibrary.viewResCal(item, services, customers,dentists).toString());
//    		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delCal"+item.getCalendar_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
//    		               // tmp.append(CalendarLibrary.viewDelCal(item).toString());
//    	                }else {
    	                	tmp.append("<td class=\"text-center\">"+customers.get(item.getCalendar_customer_id()).get(2)+"</td>");
    	                	tmp.append("<td class=\"align-middle\"><button type=\"button\" class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#infoCal"+item.getCalendar_id()+"\"><i class=\"bi bi-eye\"></i></button></td>");
    		                tmp.append(CalendarLibrary.viewInfoCal(item, services, customers,dentists).toString());
    		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-warning btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#editCal"+item.getCalendar_id()+"\"><i class=\"bi bi-pencil-square\"></i></button></td>");
    		                tmp.append(CalendarLibrary.viewEditCal(item, services, customers, dentists, arrSer, arrDen).toString());
    		                tmp.append("<td class=\"align-middle\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delCanlendar"+item.getCalendar_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
    		                tmp.append(CalendarLibrary.viewDelCal(item).toString());
    	              //  }
    	                
    	                tmp.append("</tr>");

    	               
                });
            	 tmp.append("</tbody>");
	                
                tmp.append("</table>");
                tmp.append("<!-- End Table with hoverable rows -->");
            }
			
		} 
           
           
        ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		//Cấu trúc phân trang
		String url;
		if(viewtype == null || viewtype.equalsIgnoreCase("grid") ) {
			url = "/btl/calendar/list?view=grid&date="+date +"&";
		}else {
			url = "/btl/calendar/list?view=list&date="+date +"&";
		}
		
		if(!items.isEmpty() && infors.getValue1()!=null && infors.getValue2()!=null) {
			short page = infors.getValue1();
			byte totalperpage = infors.getValue2();	
			String paging = UserLibrary.getPagination(url, total, page, totalperpage).toString();
			view.add(paging);
		}
		String chart;
		if(!items.isEmpty()) {
			//Vẽ biểu đồ
			chart = CalendarLibrary.createCalendarChart(data).toString();
			
		}else {
			chart = "Chưa có dữ liệu";
		}
		view.add(chart);
		return view;
	}


	private static StringBuilder viewDelCal(CalendarObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"modal fade\" id=\"delCanlendar"+item.getCalendar_id()+"\" tabindex=\"-1\" aria-labelledby=\"DelUserLabel\"+item.getUser_id()+\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"delCanlendar"+item.getCalendar_id()+"\"><i class=\"bi bi-trash3\"></i> Xóa lịch khám</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		tmp.append("Bạn có chắc chắn xóa lịch khám không?<br>");
		tmp.append("Lịch khám không thể phục hồi được nữa.");	
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		
		tmp.append("<a href=\"/btl/calendar/dr?id="+item.getCalendar_id()+"&date="+item.getCalendar_start_date().substring(0,10)+"\" class=\"btn btn-danger\"><i class=\"bi bi-trash3\"></i> Xóa vĩnh viễn</a>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		return tmp;
	}

	
	private static StringBuilder viewEditCal(
			CalendarObject item, 
			HashMap<Integer, ArrayList<String>> service,
			HashMap<Integer, ArrayList<String>> customer, 
			HashMap<Integer, String> dentist,
			HashMap<Integer, String> arrSer,
			HashMap<Integer, String> arrDen) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Modal -->");
		tmp.append("<div class=\"modal fade\" id=\"editCal"+item.getCalendar_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"infoUserLabel\" aria-hidden=\"true\">");
		  tmp.append("<div class=\"modal-dialog modal-lg\">");
		  tmp.append("<form method=\"post\" action=\"/btl/calendar/list\" class=\"needs-validation\" novalidate>");
		    tmp.append("<div class=\"modal-content\">");
		      tmp.append("<div class=\"modal-header\">");
				tmp.append("<h1 class=\"modal-title fs-5\" id=\"infoCalLabel\"><i class=\"bi bi-calendar-event\"></i> Cập nhật thông tin lịch khám của <b>"+ customer.get(item.getCalendar_customer_id()).get(0) +"</b></h1>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		      tmp.append("</div>");
		      tmp.append("<div class=\"modal-body\">");
		      
				tmp.append("<div class=\"row mb-3\">");				
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"calCus-name\" class=\"form-label\">Họ tên</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"calCus-name\" name=\"txtCusName\" value=\""+customer.get(item.getCalendar_customer_id()).get(0)+"\" required>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" name=\"txtPhone22222\" value=\""+customer.get(item.getCalendar_customer_id()).get(2)+"\" readonly required>");
				tmp.append("</div>");
				
				tmp.append("</div>");			
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"date\" class=\"form-label\">Ngày khám</label>");
				tmp.append("<input type=\"date\" class=\"form-control\" id=\"date\"  name=\"txtDate\" value=\""+item.getCalendar_start_date().substring(0, 10)+"\" required>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"hour\" class=\"form-label\">Thời gian khám</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"hour\"  name=\"txtTime\" value=\""+item.getCalendar_start_date().substring(11, 16) +"\" required>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"email-address\" class=\"form-label\">Hộp thư</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"email-address\" required name=\"txtEmailAddress\" value=\""+customer.get(item.getCalendar_customer_id()).get(3)+"\" >");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"calCus-name\" class=\"form-label\">Phòng</label>");
				tmp.append("<select class=\"form-select\" id=\"room\" name=\"slcRoom\" required>");
				tmp.append("<option value=\"1\" >Phòng 1 </option> ");
				tmp.append("<option value=\"2\" >Phòng 2 </option> ");
				tmp.append("<option value=\"3\" >Phòng 3 </option> ");
				tmp.append("<option value=\"4\" >Phòng 4 </option> ");
				tmp.append("<option value=\"5\" >Phòng 5 </option> ");
				tmp.append("</select>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"service-name\" class=\"form-label\">Dịch vụ đăng ký</label>");
				tmp.append("<select class=\"form-select\"  id=\"service\" name=\"slcService\" required>");
				arrSer.forEach((k,v) -> {
					if(Integer.parseInt(service.get(item.getCalendar_service_id()).get(3))==k) {
						tmp.append("<option value=\"").append(k).append("\" selected >");
					}else {
						tmp.append("<option value=\"").append(k).append("\" >");
					}
					
					tmp.append(v);
					tmp.append("</option>");
				});
				tmp.append("</select>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"dentist-name\" class=\"form-label\">Nha sĩ phụ trách</label>");
				tmp.append("<select class=\"form-select\"  id=\"service\" name=\"slcDentist\" required>");
				arrDen.forEach((k,v) -> {
					if(dentist.get(item.getCalendar_dentist_id())==v) {
						tmp.append("<option value=\"").append(k).append("\" selected >");
					}else {
						tmp.append("<option value=\"").append(k).append("\" >");
					}
					
					tmp.append(v);
					tmp.append("</option>");
				});
				tmp.append("</select>");
				tmp.append("</div>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"service-price\" class=\"form-label\">Đơn giá</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"service-price\"  disabled readonly required name=\"txtServicePrice\" value=\""+service.get(item.getCalendar_service_id()).get(1)+"\" required>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"service-discount\" class=\"form-label\">Khuyến mại</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"service-discount\"  disabled readonly required name=\"txtServiceDiscount\" value=\""+service.get(item.getCalendar_service_id()).get(2)+"\" >");
				tmp.append("</div>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"row-lg-6\">");				
				tmp.append("<label for=\"note\" class=\"form-label\">Ghi chú</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"note\"  name=\"txtNote\" value=\""+item.getCalendar_notes()+"\" >");
				tmp.append("</div>");				
				
				
		      tmp.append("</div>");//modal-body
		      
		      tmp.append("<input type=\"hidden\" name =\"idForPost\" value=\""+item.getCalendar_id()+"\"  id=\"\">");
				tmp.append("<input type=\"hidden\" name =\"act\" value=\"edit\">");
		      tmp.append("<div class=\"modal-footer\">");
		      
		      tmp.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-check2\"></i> Hoàn tất</button>");
		      tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
		      
		      tmp.append("</div>");//modal-footer
		    tmp.append("</div>");//modal-content
		    tmp.append("</form>");
		  tmp.append("</div>");//modal-dialog
		tmp.append("</div>");//modal fade
		return tmp;
	}
	public static StringBuilder viewAddCal(
			HashMap<Integer, String> arrSer,
			HashMap<Integer, String> arrDen
			) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Modal -->");
		tmp.append("<div class=\"modal fade\" id=\"addCal\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"infoUserLabel\" aria-hidden=\"true\">");
		  tmp.append("<div class=\"modal-dialog modal-lg\">");
		  tmp.append("<form method=\"post\" action=\"/btl/calendar/list\" class=\"needs-validation\" novalidate>");
		    tmp.append("<div class=\"modal-content\">");
		      tmp.append("<div class=\"modal-header\">");
				tmp.append("<h1 class=\"modal-title fs-5\" id=\"infoCalLabel\"><i class=\"bi bi-calendar-plus\"></i> Thêm lịch khám mới </h1>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		      tmp.append("</div>");
		      tmp.append("<div class=\"modal-body\">");
		      
				tmp.append("<div class=\"row mb-3\">");
							
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"calCus-name\" class=\"form-label\">Họ tên</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"calCus-name\" name=\"txtCusName1\" required>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" name=\"txtPhoneNumber1\" required>");
				tmp.append("</div>");
				
				tmp.append("</div>");			
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"date\" class=\"form-label\">Ngày khám</label>");
				tmp.append("<input type=\"date\" class=\"form-control\" id=\"date\"  name=\"txtDate1\" required>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"hour\" class=\"form-label\">Thời gian khám</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"hour\"  name=\"txtTime1\" required>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"service-name\" class=\"form-label\">Dịch vụ đăng ký</label>");
				tmp.append("<select class=\"form-select\"  id=\"service\" name=\"slcService1\" required>");
				arrSer.forEach((k,v) -> {

						tmp.append("<option value=\"").append(k).append("\" >");

					tmp.append(v);
					tmp.append("</option>");
				});
				tmp.append("</select>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"dentist-name\" class=\"form-label\">Nha sĩ</label>");
				tmp.append("<select class=\"form-select\"  id=\"service\" name=\"slcDentist1\" required>");
				arrDen.forEach((k,v) -> {

						tmp.append("<option value=\"").append(k).append("\" >");
					
					tmp.append(v);
					tmp.append("</option>");
				});
				tmp.append("</select>");
				tmp.append("</div>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"calCus-name\" class=\"form-label\">Phòng</label>");
				tmp.append("<select class=\"form-select\" id=\"room\" name=\"slcRoom1\" required>");
				tmp.append("<option value=\"1\" >Phòng 1 </option> ");
				tmp.append("<option value=\"2\" >Phòng 2 </option> ");
				tmp.append("<option value=\"3\" >Phòng 3 </option> ");
				tmp.append("<option value=\"4\" >Phòng 4 </option> ");
				tmp.append("<option value=\"5\" >Phòng 5 </option> ");
				tmp.append("</select>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"note\" class=\"form-label\">Ghi chú</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"note\" name=\"txtNote1\" >");
				tmp.append("</div>");
				
				tmp.append("</div>");	
				
				
				
		      tmp.append("</div>");//modal-body

		      tmp.append("<div class=\"modal-footer\">");
		      
		      tmp.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-check2\"></i> Hoàn tất</button>");
		      tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
		      
		      tmp.append("</div>");//modal-footer
		    tmp.append("</div>");//modal-content
		    tmp.append("</form>");
		  tmp.append("</div>");//modal-dialog
		tmp.append("</div>");//modal fade
		return tmp;
	}

	private static StringBuilder viewInfoCal(
					CalendarObject item,
					HashMap<Integer, ArrayList<String>> service, //Tên dịch vụ
					HashMap<Integer, ArrayList<String>> customer, //Tên khách hàng
					HashMap<Integer, String> dentist // Tên nha sĩ				
					) {

		
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Modal -->");
		tmp.append("<div class=\"modal fade\" id=\"infoCal"+item.getCalendar_id()+"\" tabindex=\"-1\" aria-labelledby=\"infoUserLabel\" aria-hidden=\"true\">");
		  tmp.append("<div class=\"modal-dialog modal-lg\">");	  
		    tmp.append("<div class=\"modal-content\">");
		      tmp.append("<div class=\"modal-header\">");
				tmp.append("<h1 class=\"modal-title fs-5\" id=\"infoCalLabel\"><i class=\"bi bi-calendar-event\"></i> Thông tin lịch khám của <b>"+ customer.get(item.getCalendar_customer_id()).get(0) +"</b></h1>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		      tmp.append("</div>");
		      tmp.append("<div class=\"modal-body\">");
		      
				tmp.append("<div class=\"row mb-3\">");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"cal-id\" class=\"form-label\">ID</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"cal-id"+item.getCalendar_id()+"\" name=\"txtCalName\" value=\""+item.getCalendar_id()+"\" disabled readonly>");	
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"calCus-name\" class=\"form-label\">Họ tên</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"calCus-name\" name=\"txtCusFullName\" value=\""+customer.get(item.getCalendar_customer_id()).get(0)+"\"disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("</div>");			
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"date\" class=\"form-label\">Ngày khám</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"date\" name=\"txtDate\" value=\""+item.getCalendar_start_date().substring(0, 10)+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"hour\" class=\"form-label\">Thời gian</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"hour\" name=\"txtPhoneNumber\" value=\""+item.getCalendar_start_date().substring(11, 16) + " - " + item.getCalendar_end_date().substring(11, 16)+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"email-address\" class=\"form-label\">Hộp thư</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"email-address\" name=\"txtEmailAddress\" value=\""+customer.get(item.getCalendar_customer_id()).get(3)+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"phone-number\" class=\"form-label\">Điện thoại</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"phone-number\" name=\"txtPhoneNumber\" value=\""+customer.get(item.getCalendar_customer_id()).get(2)+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"service-name\" class=\"form-label\">Dịch vụ sử dụng</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"service-name\" name=\"txtService\" value=\""+service.get(item.getCalendar_service_id()).get(0)+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"dentist-name\" class=\"form-label\">Nha sĩ phụ trách</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"dentist-name\" name=\"txtDentistNumber\" value=\""+dentist.get(item.getCalendar_dentist_id())+"\" disabled readonly>");
				tmp.append("</div>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"row mb-3\">");
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"service-price\" class=\"form-label\">Đơn giá</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"service-price\" name=\"txtServicePrice\" value=\""+service.get(item.getCalendar_service_id()).get(1)+"\" disabled readonly>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"col-lg-6\">");
				tmp.append("<label for=\"service-discount\" class=\"form-label\">Khuyến mại</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"service-discount\" name=\"txtServiceDiscount\" value=\""+service.get(item.getCalendar_service_id()).get(2)+"\" disabled readonly>");
				tmp.append("</div>");
				tmp.append("</div>");
				
				tmp.append("<div class=\"row-lg-6\">");				
				tmp.append("<label for=\"note\" class=\"form-label\">Ghi chú</label>");
				tmp.append("<input type=\"text\" class=\"form-control\" id=\"note\" name=\"txtEmailAddress\" value=\""+item.getCalendar_notes()+"\" disabled readonly>");
				tmp.append("</div>");				
				
				
		      tmp.append("</div>");//modal-body
		      tmp.append("<div class=\"modal-footer\">");
		      if(item.isCalendar_delete()) {
		    	  tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
				  
		      }else {
		    	  tmp.append("<button class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#editCal"+item.getCalendar_id()+"\"><i class=\"bi bi-gear\"></i> Chỉnh sửa</button>");
		    	  tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
		      }
		      tmp.append("</div>");//modal-footer
		    tmp.append("</div>");//modal-content
		  tmp.append("</div>");//modal-dialog
		tmp.append("</div>");//modal fade
		
		return tmp;
	}

	/**
	 * 
	 * @param items - lấy danh sách lịch đặt theo ngày 
	 * @return
	 */
	public static StringBuilder createCalendarChart( 
			Septet<ArrayList<CalendarObject>, // Danh sách lịch
					Integer,  // Tổng số bản ghi
					HashMap<Integer, ArrayList<String>>, //Tên dịch vụ
					HashMap<Integer, ArrayList<String>>, //Tên khách hàng
					HashMap<Integer, String>, // Tên nha sĩ
					HashMap<Integer, String>, 
					HashMap<Integer, String> 							
					> data
					) {
		
		
		
		StringBuilder tmp = new StringBuilder();
		
		//Chuẩn bị dữ liệu
		ArrayList<CalendarObject> items = data.getValue0();
		HashMap<Integer, ArrayList<String>> services = data.getValue2();
		HashMap<Integer, ArrayList<String>> customers = data.getValue3();
		HashMap<Integer, String> dentists = data.getValue4();
		
		ArrayList<String> colors = new ArrayList<>(Arrays.asList("rgb(54,235,217)","rgb(127,54,235)","rgb(221,77,237)","rgb(54,235,217)","rgb(127,54,235)","rgb(221,77,237)","rgb(163,235,54)","rgb(72,235,54)","rgb(235,127,54)","rgb(235,217,54)","rgb(163,235,54)","rgb(72,235,54)","rgb(235,127,54)","rgb(235,217,54)"));

		//Lấy ngày thực hiện
		String date;
		if(!items.isEmpty()) {
			date = items.get(0).getCalendar_start_date();
			date = date.substring(0, 10);
		}else {
			date = Utilities_date.getDate();
		}

		//System.out.println(date + "--------------------");
		StringBuilder dt = new StringBuilder();
		services.forEach((k,v)->{
			dt.append("{ \n");
			dt.append("label: '" + v.get(0)  + "',");
			dt.append("data: [ \n");
			items.forEach(item->{
				if(item.getCalendar_service_id() == k ) {
					dt.append("{x: ['"+item.getCalendar_start_date()+"','"+item.getCalendar_end_date()+"'], y: 'Phòng "+item.getCalendar_room_id()+"', service: '" + v.get(0) + "', customer: '"+customers.get(item.getCalendar_customer_id()).get(0)+"', phone: '"+customers.get(item.getCalendar_customer_id()).get(2)+"', dentist: '"+dentists.get(item.getCalendar_dentist_id())+"'},\n");
					
				}
				
			});
			dt.deleteCharAt(dt.lastIndexOf(","));
			dt.append("], \n");
			dt.append("backgroundColor: [ '"+colors.get(k)+"' ] \n");	
			dt.append("}, \n");
		});

		dt.deleteCharAt(dt.lastIndexOf(","));
		
		tmp.append("<div class=\"chartCard\">");
		tmp.append("<div class=\"chartBox\">");
	        tmp.append("<canvas id=\"myChart\"></canvas> \n");
        tmp.append("</div>");
        tmp.append("</div>");
	    tmp.append("<script type=\"text/javascript\" src=\"https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.min.js\"></script> \n");
	    tmp.append("<script src=\"https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns/dist/chartjs-adapter-date-fns.bundle.min.js\"></script> \n");

		tmp.append("<script> \n");
		

		//tmp.append("var colors = ['rgb(54,235,217)','rgb(127,54,235)','rgb(221,77,237)','rgb(163,235,54)','rgb(72,235,54)','rgb(235,127,54)','rgb(235,217,54)'];");

		tmp.append("/* setup */ \n");
	    tmp.append("const data = { \n");
	      tmp.append("labels: ['Phòng 5', 'Phòng 4', 'Phòng 3', 'Phòng 2', 'Phòng 1'], \n");
	      tmp.append("datasets: [ \n" );
	      
	      tmp.append(dt.toString());

		  tmp.append("] \n");
	    tmp.append("}; \n");

	    tmp.append("/* config */ \n");
	    tmp.append("const config = { \n");
	      tmp.append("type: 'bar', \n");
	      tmp.append("data, \n");
	      tmp.append("options: { \n");

	        tmp.append("indexAxis: 'y', \n");
	        tmp.append("scales: { \n");
	        tmp.append("x: { \n");
	        tmp.append("type: 'time', \n");
			tmp.append("time: { \n");
			tmp.append("displayFormats: { \n");
				tmp.append("hour: 'HH:mm' \n");
				tmp.append("} \n");
				tmp.append("}, \n");
				tmp.append("min: '"+date+" 08:00:00', \n");
				tmp.append("max: '"+date+" 20:00:00' \n");
	          tmp.append("}, \n");
	          tmp.append("y: { \n");
	          tmp.append("beginAtZero: true, \n");
				tmp.append("stacked: true \n");		
				tmp.append("} \n");
	        tmp.append("},\n");
		  tmp.append("plugins: { \n");
			tmp.append("tooltip: { \n");
				tmp.append("displayColors: false, \n");
				tmp.append("callbacks: { \n");
					tmp.append("/*title: function(context){ \n");
						tmp.append("return arrLabels[1]; \n");
					tmp.append("},*/ \n");
					tmp.append("label: (ctx) => { \n");
						tmp.append("const startDate = new Date(ctx.parsed._custom.barStart); \n");
						tmp.append("const startEnd = new Date(ctx.parsed._custom.barEnd); \n");

						tmp.append("const formattedStartDate = startDate.toLocaleString([], \n");
							tmp.append("{ \n");
								tmp.append("hour: '2-digit', \n");
								tmp.append("hour12: false, \n");
								tmp.append("minute: '2-digit' \n");
							tmp.append("}) \n");
						tmp.append("const formattedEndDate = startEnd.toLocaleString([], \n");
							tmp.append("{ \n");
								tmp.append("hour: '2-digit', \n");
								tmp.append("hour12: false, \n");
								tmp.append("minute: '2-digit' \n");
							tmp.append("}) \n");
							tmp.append("var arr = [`Dịch vụ: ${ctx.raw.service}`,`Nha sĩ: ${ctx.raw.dentist}`,`Khách hàng: ${ctx.raw.customer}`,`Điện thoại: ${ctx.raw.phone}`,`Thời gian: ${formattedStartDate} - ${formattedEndDate}`] \n");
					tmp.append("return arr; ");
					tmp.append("} \n");
					
				tmp.append("} \n");
			tmp.append("} \n");
		  tmp.append("} \n");
	      tmp.append("} \n");
	    tmp.append("}; \n");

	    tmp.append("/* render init block*/ \n");
	    tmp.append("const myChart = new Chart( \n");
	      tmp.append("document.getElementById('myChart'), \n");
	      tmp.append("config \n");
	    tmp.append("); \n");

	    tmp.append("/* Instantly assign Chart.js version*/ \n");
	    tmp.append("const chartVersion = document.getElementById('chartVersion'); \n");
	    tmp.append("chartVersion.innerText = Chart.version; \n");
	    tmp.append("</script> \n");
	    

	   // System.out.println(dt.toString());
	   // System.out.println("===================================");
	  // System.out.println(tmp.toString());
		return tmp;
	}
	
	//Vẽ biểu đồ thống kê theo tháng
	public static StringBuilder createStatisticalChart(LinkedHashMap<String, Float> items) {
		StringBuilder tmp = new StringBuilder();
		
		StringBuilder month = new StringBuilder();
		StringBuilder data = new StringBuilder();
		
		
		items.forEach((k,v)->{
			month.append("'"+k+"',");
			data.append(v+",");
			
			
			
//			month.append("'"+k+"'");
//			data.append(v+",");
//			if(items.indexOf((k,v))<items.size()) {
//				data.append(",");
//				month.append(",");
//			}
		});
		
		month.deleteCharAt(month.lastIndexOf(","));
		data.deleteCharAt(data.lastIndexOf(","));
		
		System.out.print(month.toString() +data.toString());;
		
		 tmp.append("<!-- Line Chart --> \n");
         tmp.append("<div id=\"reportsChart\"></div> \n");

         tmp.append("<script> \n");
           tmp.append("document.addEventListener(\"DOMContentLoaded\", () => { \n");
             tmp.append("new ApexCharts(document.querySelector(\"#reportsChart\"), { \n");
               tmp.append("series: [{ \n");
                 tmp.append("name: 'Danh thu', \n");
                 tmp.append("data: ["+data+"], \n");
               tmp.append("}], \n");
               tmp.append("chart: { \n");
                 tmp.append("height: 350, \n");
                 tmp.append("type: 'area', \n");
                 tmp.append("toolbar: { \n");
                   tmp.append("show: false \n");
                 tmp.append("}, \n");
               tmp.append("}, \n");
               tmp.append("markers: { \n");
                 tmp.append("size: 4 \n");
               tmp.append("}, \n");
               tmp.append("colors: ['#2eca6a'], \n");
               tmp.append("fill: { \n");
                 tmp.append("type: \"gradient\", \n");
                 tmp.append("gradient: { \n");
                   tmp.append("shadeIntensity: 1, \n");
                   tmp.append("opacityFrom: 0.3, \n");
                   tmp.append("opacityTo: 0.4, \n");
                   tmp.append("stops: [0, 90, 100] \n");
                 tmp.append("} \n");
               tmp.append("}, \n");
               tmp.append("dataLabels: { \n");
                 tmp.append("enabled: false \n");
               tmp.append("}, \n");
               tmp.append("stroke: { \n");
                 tmp.append("curve: 'smooth', \n");
                 tmp.append("width: 2 \n");
               tmp.append("}, \n");
               tmp.append("xaxis: { \n");
                 tmp.append("type: 'month', \n");
                 tmp.append("categories: ["+month+"] \n");
               tmp.append("}, \n");
               tmp.append("tooltip: { \n");
                 tmp.append("x: { \n");
                   tmp.append("format: 'dd/MM/yy HH:mm' \n");
                 tmp.append("}, \n");
               tmp.append("} \n");
             tmp.append("}).render(); \n");
           tmp.append("}); \n");
         tmp.append("</script> \n"); 
         tmp.append("<!-- End Line Chart --> \n");
		
         System.out.println(tmp.toString());
		return tmp;
	}
	
}
