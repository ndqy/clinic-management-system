package jsoft.ads.log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ads.customer.CustomerLibrary;
import jsoft.ads.user.UserLibrary;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.CustomerObject;
import jsoft.objects.LogObject;

public class LogLibrary {
	public static String viewLog(ArrayList<LogObject> items) {
		StringBuilder tmp = new StringBuilder();
		TreeSet<String> tree = new TreeSet<String>(Comparator.reverseOrder());
		items.forEach(item->{
			tree.add(item.getLog_date().substring(0,10));
		});
		tree.forEach(key-> {
			
            System.out.println("Phần tử: " + key);
            System.out.println("=========================");
        });
		tmp.append("<!-- Recent Activity -->");
        tmp.append("<div class=\"card\">");
         

          tmp.append("<div class=\"card-body\">");
          if(tree.contains(Utilities_date.getDate())) {
        	  tmp.append("<h5 class=\"card-title\" style=\"padding: 20px 0 0px 0\">Lịch sử làm việc<br><span>| Hôm nay</span></h5>");
          }else {
        	  tmp.append("<h5 class=\"card-title\" style=\"padding: 20px 0 0px 0\">Lịch sử làm việc</h5>");
          }
          //tree.forEach(key-> {
        	  //System.out.println(key);
        	  items.forEach(item->{ 
        		String activity = "";
              	String color = "";
              	switch (item.getLog_action()) {
  	            	case 1:
  	            		activity = " đã thêm mới ";
  	            		color = " text-primary ";
  	            		break;
  	            	case 2:
  	            		activity = " đã cập nhật ";
  	            		color = " text-warning ";
  	            		break;
  	            	case 3:
  	            		activity = " đã xóa ";
  	            		color = " text-muted ";
  	            		break;
  	            	case 4:
  	            		activity = " đã xóa vĩnh viễn ";
  	            		color = " text-danger ";
  	            		break;
  	            	case 5:
  	            		activity = " đã khôi phục ";
  	            		color = " text-success ";
  	            		break;
  	            	case 6:
  	            		activity = " đã khóa ";
  	            		color = " text-info  ";
  	            		break;
              	}
              	
              	String modul="";
              	switch (item.getLog_position()) {
  	            	case 1:
  	            		modul = " Người dùng ";
  	            		break;
  	            	case 2:
  	            		modul = " Lịch khám ";
  	            		break;
  	            	case 3:
  	            		modul = " Dịch vụ ";
  	            		break;
  	            	case 4:
  	            		modul = " Khách hàng ";
  	            		break;
              	}
        		
        		 
        		 if(item.getLog_date().substring(0,10).equals(Utilities_date.getDate())) {
        			//tmp.append("<h5 class=\"card-title\" style=\"margin: -20px 0 0px 0;\"><br><span>| "+key+"</span></h5>");
	   				  tmp.append("<div class=\"activity mb-2\">");
	
	   					tmp.append("<div class=\"activity-item d-flex\">");
	   					  tmp.append("<div class=\"activite-label\">"+item.getLog_date().substring(11)+"</div>");
	   					  tmp.append("<i class='bi bi-circle-fill activity-badge "+color+" align-self-start'></i>");
	   					  tmp.append("<div class=\"activity-content\">");
	   					  tmp.append("<b>"+Utilities.decode(item.getLog_user_name())+"</b>" + activity +" <a href=\"#\" class=\"fw-bold text-dark\"><i>"+Utilities.decode(item.getLog_name())+"</i></a> tại Modul<b>" + modul+"</b>");
	   	                tmp.append("</div>");
	   					tmp.append("</div><!-- End activity item-->");
	
	   				  tmp.append("</div>");
        		 }
        	  });
          //});
          
          tree.forEach(key -> {
        	  if(!key.equalsIgnoreCase(Utilities_date.getDate())) {
           		 tmp.append("<h5 class=\"card-title\" style=\"margin: -20px 0 0px 0\"><br><span>| "+key+"</span></h5>");
	       		 
	        	  //System.out.println(key);
	        	  items.forEach(item->{  
	        		String activity = "";
	              	String color = "";
	              	switch (item.getLog_action()) {
	  	            	case 1:
	  	            		activity = " đã thêm mới ";
	  	            		color = "text-primary";
	  	            		break;
	  	            	case 2:
	  	            		activity = " đã cập nhật ";
	  	            		color = "text-warning";
	  	            		break;
	  	            	case 3:
	  	            		activity = " đã xóa ";
	  	            		color = "text-danger";
	  	            	case 4:
	  	            		activity = " đã xóa vĩnh viễn ";
	  	            		color = " text-danger ";
	  	            		break;
	  	            	case 5:
	  	            		activity = " đã khôi phục ";
	  	            		color = " text-success ";
	  	            		break;
	  	            	case 6:
	  	            		activity = " đã khóa ";
	  	            		color = " text-info  ";
	  	            		break;
	              	}
	              	
	              	String modul="";
	              	switch (item.getLog_position()) {
	  	            	case 1:
	  	            		modul = " Người dùng ";
	  	            		break;
	  	            	case 2:
	  	            		modul = " Lịch khám ";
	  	            		break;
	  	            	case 3:
	  	            		modul = " Dịch vụ ";
	  	            		break;
	  	            	case 4:
	  	            		modul = " Khách hàng ";
	  	            		break;
	              	}
	        		
	        		 
	        		 if(item.getLog_date().substring(0,10).equalsIgnoreCase(key)) {
	        			//tmp.append("<h5 class=\"card-title\" style=\"margin: -20px 0 0px 0;\"><br><span>| "+key+"</span></h5>");
		   				  tmp.append("<div class=\"activity mb-2\">");
		
		   					tmp.append("<div class=\"activity-item d-flex\">");
		   					  tmp.append("<div class=\"activite-label\">"+item.getLog_date().substring(11)+"</div>");
		   					  tmp.append("<i class='bi bi-circle-fill activity-badge "+color+" align-self-start'></i>");
		   					  tmp.append("<div class=\"activity-content\">");
		   					  tmp.append("<b>"+Utilities.decode(item.getLog_user_name())+"</b>" + activity +" <a href=\"#\" class=\"fw-bold text-dark\"><i>"+Utilities.decode(item.getLog_name())+"</i></a> tại Modul<b>" + modul+"</b>");
		   	                 tmp.append("</div>");
		   					tmp.append("</div><!-- End activity item-->");
		
		   				  tmp.append("</div>");
	        		 }
	        	  });
        	  }
          });


          tmp.append("</div>");
        tmp.append("</div><!-- End Recent Activity -->");
		
		return tmp.toString();
		
	}
	
	public static ArrayList<String> viewLogForUser(
			Pair<ArrayList<LogObject>, Integer> data,
			Triplet<LogObject, Short, Byte> infors ) {
		
		ArrayList<LogObject> items = data.getValue0();
		int total = data.getValue1();
		
		LogObject cus = infors.getValue0();
		short page = infors.getValue1();
		byte totalperpage = infors.getValue2();	
		
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<!-- Table with hoverable rows -->");
        tmp.append("<table class=\"table table-hover\">");
          tmp.append("<thead>");
            tmp.append("<tr>");
              tmp.append("<th class=\"text-center\" scope=\"col\">ID</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Lịch sử</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Thời gian</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Thực hiện</th>");
            tmp.append("</tr>");
          tmp.append("</thead>");
          tmp.append("<tbody>");
          
          items.forEach(item -> {
        	  String activity = "";
        	  switch (item.getLog_action()) {
            	case 1:
            		activity = " đã thêm mới ";
            		break;
            	case 2:
            		activity = " đã cập nhật ";
            		break;
            	case 3:
            		activity = " đã xóa ";
            	case 4:
            		activity = " đã xóa vĩnh viễn ";
            		break;
            	case 5:
            		activity = " đã khôi phục ";
            		break;
            	case 6:
            		activity = " đã khóa ";
            		break;
        	}
        	
        	String modul="";
        	switch (item.getLog_position()) {
            	case 1:
            		modul = " Người dùng ";
            		break;
            	case 2:
            		modul = " Lịch khám ";
            		break;
            	case 3:
            		modul = " Dịch vụ ";
            		break;
            	case 4:
            		modul = " Khách hàng ";
            		break;
        	}
  		
          	tmp.append("<tr>");
        	tmp.append("<th scope=\"row\" class=\"text-center\">"+item.getLog_id()+"</th>");
            tmp.append("<td class=\"align-middle\"><b>"+Utilities.decode(item.getLog_user_name()) +"</b>" + activity + "<b>" + Utilities.decode(item.getLog_name())+"</b> tại Modul<i>" + modul+"</i>"+"</td>");
            tmp.append("<td class=\"text-center\">"+item.getLog_date()+"</td>");
          	tmp.append("<td class=\"text-center\"><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delLog"+item.getLog_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
            tmp.append(LogLibrary.viewDelLog(item));
            tmp.append("</tr>");
          });
          
          
          tmp.append("</tbody>");
          tmp.append("</table>");
          tmp.append("<!-- End Table with hoverable rows -->");
  				
          ArrayList<String> view = new ArrayList<>();
  		view.add(tmp.toString());
  		
  		
  		//Cấu trúc phân trang
  		String url = "/btl/log/list?";
  		String paging = UserLibrary.getPagination(url, total, page, totalperpage).toString();
  		view.add(paging);
  		
//  		//Biểu đồ
//  		String chart = CustomerLibrary.creatChart(items).toString();
//  		view.add(chart);
  		
  		return view;
	}
	private static StringBuilder viewDelLog(LogObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"modal fade\" id=\"delLog"+item.getLog_id()+"\" tabindex=\"-1\" aria-labelledby=\"ResUserLabel\"+item.getCustomer_id()+\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"ResUserLabel\"><i class=\"bi bi-person-x-fill\"></i> Xóa lịch sử </h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		tmp.append("<div class=\"row mb-3\">");
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("Bạn có chắc chắn muốn xóa lịch sử làm việc của <b>").append(Utilities.decode(item.getLog_name())+"</b>?<br>");
		tmp.append("Hành động của người dùng sẽ không thể khôi phục được nữa.");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");

		tmp.append("<a href=\"/btl/log/del?id="+item.getLog_id()+"\" class=\"btn btn-danger\"><i class=\"bi bi-person-x\"></i> Xóa vĩnh viễn</a>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}
	public static void main(String[] args) {
		ArrayList<String> items = new ArrayList<String>();
		items.add("2023-10-10 08:00:00");
		items.add("2023-10-10 08:00:00");
		items.add("2023-10-10 08:30:00");
		items.add("2023-10-10 10:00:00");
		items.add("2023-10-10 08:00:00");
		items.add("2023-10-10 08:30:00");
		items.add("2023-10-10 11:00:00");
		items.add("2023-10-10 11:45:00");
		items.add("2023-10-10 10:45:00");
		items.add("2023-10-10 15:45:00");
		items.add("2023-10-10 15:45:00");
		items.add("2023-10-10 11:45:00");
		items.add("2023-10-10 11:45:00");
		items.add("2023-10-30 12:45:00");
		items.add("2023-10-31 10:45:00");
		items.add("2023-11-14 11:00:00");
		items.add("2023-11-12 11:00:00");
		items.add("2023-11-15 11:00:00");
		items.add("2023-11-16 11:11:00");
		items.add("2023-11-16 12:00:00");
		items.add("2023-11-16 13:00:00");
		items.add("2023-11-16 13:00:00");
		items.add("2023-11-16 15:00:00");
		items.add("2023-11-16 15:00:00");
		items.add("2023-11-16 15:00:00");
		items.add("2023-11-16 11:00:00");
		items.add("2023-11-16 15:55:00");
		items.add("2023-11-16 11:00:00");
		items.add("2023-11-16 11:00:00");
		items.add("2023-11-16 11:11:00");
		items.add("2023-11-16 11:00:00");
		items.add("2023-11-10 11:00:00");
		items.add("2023-11-17 12:34:00");
		items.add("2023-11-17 12:34:00");
		items.add("2023-11-17 12:36:00");
		items.add("2023-11-17 12:55:00");
		items.add("2023-10-10 15:00:00");
		items.add("2023-10-10 08:00:00");
		items.add("2023-12-18 15:00:00");
		items.add("2023-12-16 11:00:00");
		TreeSet<String> hmap = new TreeSet<String>(Comparator.reverseOrder());
		items.forEach(item->{
			hmap.add(item.substring(0,10));
		});
		
		if(hmap.contains("2023-10-10")) {
			System.out.println("Bằng nhau=====================");
		}else {
			System.out.println(" 0 Bằng nhau=====================");
		}
		hmap.forEach(key-> {
			
            System.out.println("Phần tử: " + key);
            System.out.println("---------------------");
        });
	}
}
