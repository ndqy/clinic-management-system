package jsoft.ads.gksk;

import java.util.ArrayList;
import java.util.HashMap;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.objects.CalendarObject;
import jsoft.objects.CustomerObject;
import jsoft.objects.GKSKObject;

public class GKSKLibrary {
	
	public static ArrayList<String> viewGKSK(
			Quartet<
				ArrayList<GKSKObject>, 
				Integer, 
				HashMap<Integer, CustomerObject>, 
				ArrayList<String> //Danh sách tên bác sĩ
				> data,
			Triplet<GKSKObject, Short, Byte> infors){
		
		ArrayList<GKSKObject> items = data.getValue0();
		int total = data.getValue1();
		HashMap<Integer, CustomerObject> cuss = data.getValue2();
		ArrayList<String> users = data.getValue3();
		
		GKSKObject gk = infors.getValue0();
		short page = infors.getValue1();
		byte totalperpage = infors.getValue2();	
		
		StringBuilder tmp = new StringBuilder();
		tmp.append("<!-- Table with hoverable rows -->");
        tmp.append("<table class=\"table table-hover\">");
          tmp.append("<thead>");
            tmp.append("<tr>");
              tmp.append("<th class=\"text-center\" scope=\"col\">STT</th>");
              tmp.append("<th class=\"text-left\" scope=\"col\">Họ tên</th>");
              tmp.append("<th class=\"text-right\" scope=\"col\">Số điện thoại</th>");
              tmp.append("<th class=\"text-left\" scope=\"col\">Giới tính</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Ngày khám</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\">Số CCCD</th>");
              tmp.append("<th class=\"text-center\" scope=\"col\" colspan=\"3\">Thực hiện</th>");
            tmp.append("</tr>");
          tmp.append("</thead>");
          tmp.append("<tbody>");
             if(items.size()>0) {
            	 GKSKObject item = null;
            	 CustomerObject cus = new CustomerObject();
            	for(int i=0; i<items.size(); i++) {
            		item = items.get(i);
            		cus = cuss.get(item.getBenh_nhan_id());
            		tmp.append("<tr>");
         			tmp.append("<td class=\"text-center\" data-bs-toggle=\"modal\" data-bs-target=\"#viewGKSK"+item.getId()+"\">"+(i+1)+"</td>");
 	                tmp.append("<td class=\"text-left\" data-bs-toggle=\"modal\" data-bs-target=\"#viewGKSK"+item.getId()+"\">"+cus.getCustomer_fullname()+"</td>");
 	                tmp.append("<td class=\"text-right\" data-bs-toggle=\"modal\" data-bs-target=\"#viewGKSK"+item.getId()+"\">"+cus.getCustomer_phone()+"</td>");
 	                tmp.append("<td class=\"text-left\" data-bs-toggle=\"modal\" data-bs-target=\"#viewGKSK"+item.getId()+"\">Nam</td>");
                 	tmp.append("<td class=\"text-center\" data-bs-toggle=\"modal\" data-bs-target=\"#viewGKSK"+item.getId()+"\">"+item.getNgay_kham()+"</td>");
                 	tmp.append("<td class=\"text-center\" data-bs-toggle=\"modal\" data-bs-target=\"#viewGKSK"+item.getId()+"\">"+cus.getCustomer_identity_card()+"</td>");
                 	tmp.append("<td class=\"text-center\"><button type=\"button\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delGKSK"+item.getId()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
                 	tmp.append("<td class=\"text-center\"><a href=\"/gksk/pdf?id="+item.getId()+"\" type=\"button\" class=\"btn btn-success btn-sm\"><i class=\"bi bi-filetype-pdf\"></i></button></td>");
	                tmp.append("</tr>");
	              tmp.append(GKSKLibrary.viewEditGKSK(item, users, cus));
	              tmp.append(GKSKLibrary.viewDelGKSK(item));
	              
            	}
            	tmp.append("</div>");
            	
             }else {
            	 tmp.append("<tr>");
                   tmp.append("<td class=\"text-center\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-left\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-right\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-left\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-center\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-center\" scope=\"col\">Đang cập nhật</td>");
                   tmp.append("<td class=\"text-center\" scope=\"col\" colspan=\"3\">Đang cập nhật</td>");
                 tmp.append("</tr>");
                 
             }
            
          tmp.append("</tbody>");
        tmp.append("</table>");
        
        ArrayList<String> view = new ArrayList<>();
        view.add(tmp.toString());
        
		return view;
	}
	
	private static StringBuilder viewEditGKSK(GKSKObject item, ArrayList<String> users, CustomerObject cus) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"modal fade\" id=\"viewGKSK"+item.getId()+"\" tabindex=\"-1\">");
		  tmp.append("<div class=\"modal-dialog modal-xl modal-dialog-scrollable\">");
			tmp.append("<div class=\"modal-content\">");
			  tmp.append("<div class=\"modal-header\">");
				tmp.append("<h5 class=\"modal-title\">Kết quả khám sức khỏe của "+cus.getCustomer_fullname()+"</h5>");
				tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			  tmp.append("</div>");
			  tmp.append("<div class=\"modal-body\">");
				tmp.append("<!-- Bordered Tabs -->");
				tmp.append("<ul class=\"nav nav-tabs nav-tabs-bordered\">");
				  tmp.append("<li class=\"nav-item\">");
					tmp.append("<button class=\"nav-link active\" data-bs-toggle=\"tab\" data-bs-target=\"#patient-overview"+item.getId()+"\">Thông tin");
					  tmp.append(" bệnh nhân</button>");
				  tmp.append("</li>");
				  tmp.append("<li class=\"nav-item\">");
					tmp.append("<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#patient-disease-prehistory"+item.getId()+"\">Tiền");
					  tmp.append(" sử</button>");
				  tmp.append("</li>");
				  tmp.append("<li class=\"nav-item\">");
					tmp.append("<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#physical-strength-examination"+item.getId()+"\">Khám");
					  tmp.append(" lâm sàn</button>");
				  tmp.append("</li>");
				  tmp.append("<li class=\"nav-item\">");
					tmp.append("<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#subclinical-examination"+item.getId()+"\">Cận lâm");
					  tmp.append(" sàn</button>");
				  tmp.append("</li>");
				  tmp.append("<li class=\"nav-item\">");
					tmp.append("<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#conclusion"+item.getId()+"\">Kết luận</button>");
				  tmp.append("</li>");
				tmp.append("</ul>");
				tmp.append("<div class=\"tab-content pt-2\">");
				  tmp.append("<div class=\"tab-pane fade show active profile-overview\" id=\"patient-overview"+item.getId()+"\">");
					tmp.append("<h5 class=\"card-title\">Thông tin người khám</h5>");
					tmp.append("<form action=\"/btl/gksk/list\" method=\"post\"  class=\"needs-validation\" novalidate>");
					  tmp.append("<div class=\"row\">");
						tmp.append("<div class=\"col-md-6\">");
						  tmp.append("<div class=\"row mb-3\">");
							tmp.append("<label for=\"cusName\" class=\"col-sm-4 col-form-label label\">Họ tên</label>");
							tmp.append("<div class=\"col-sm-8\">");
							  tmp.append("<input type=\"text\" class=\"form-control\" name=\"cusName\" value=\""+cus.getCustomer_fullname()+"\" >");
							tmp.append("</div>");
						  tmp.append("</div>");
						  tmp.append("<div class=\"row mb-3\">");
							tmp.append("<label for=\"cusBirth\" class=\"col-sm-4 col-form-label label\">Ngày sinh</label>");
							tmp.append("<div class=\"col-sm-8\">");
							  tmp.append("<input type=\"date\" class=\"form-control\" value=\"2024-09-09\" name=\"cusBirth\">");
							tmp.append("</div>");
						  tmp.append("</div>");
						  tmp.append("<div class=\"row mb-3\">");
							tmp.append("<label for=\"cusGender\" class=\"col-sm-4 col-form-label label\">Giới tính</label>");
							tmp.append("<div class=\"col-sm-8\">");
							  tmp.append("<select class=\"form-select\" name=\"cusGender\">");
								tmp.append("<option value=\"0\" selected>Nữ</option>");
								tmp.append("<option value=\"1\">Nam</option>");
							  tmp.append("</select>");
							tmp.append("</div>");
						  tmp.append("</div>");
						  tmp.append("<div class=\"row mb-3\">");
							tmp.append("<label for=\"cusPhone\" class=\"col-sm-4 col-form-label label\">Số điện thoại</label>");
							tmp.append("<div class=\"col-sm-8\">");
							  tmp.append("<input type=\"text\" class=\"form-control\" name=\"cusPhone\" value=\""+cus.getCustomer_phone()+"\" readonly>");
							tmp.append("</div>");
						  tmp.append("</div>");
						tmp.append("</div>");
						tmp.append("<div class=\"col-md-6\">");
						  tmp.append("<div class=\"row mb-3\">");
							tmp.append("<label for=\"cusCCCD\" class=\"col-sm-4 col-form-label label\">Số CCCD</label>");
							tmp.append("<div class=\"col-sm-8\">");
							  tmp.append("<input type=\"text\" class=\"form-control\" name=\"cusCCCD\" value=\""+cus.getCustomer_identity_card()+"\" >");
							tmp.append("</div>");
						  tmp.append("</div>");
						  tmp.append("<div class=\"row mb-3\">");
							tmp.append("<label for=\"cusCCCDDate\" class=\"col-sm-4 col-form-label label\">Ngày cấp</label>");
							tmp.append("<div class=\"col-sm-8\">");
							  tmp.append("<input type=\"date\" class=\"form-control\" value=\"2024-09-09\" name=\"cusCCCDDate\" value=\"2024-09-09\">");
							tmp.append("</div>");
						  tmp.append("</div>");
						  tmp.append("<div class=\"row mb-3\">");
							tmp.append("<label for=\"name=\" cusCCCDAddress\" class=\"col-sm-4 col-form-label label\">Nơi cấp</label>");
							tmp.append("<div class=\"col-sm-8\">");
							  tmp.append("<input type=\"text\" class=\"form-control\" name=\"cusCCCDAddress\">");
							tmp.append("</div>");
						  tmp.append("</div>");
						  tmp.append("<div class=\"row mb-3\">");
							tmp.append("<label for=\"cusAddress\" class=\"col-sm-4 col-form-label label\">Địa chỉ</label>");
							tmp.append("<div class=\"col-sm-8\">");
							  tmp.append("<input type=\"text\" class=\"form-control\" name=\"cusAddress\">");
							tmp.append("</div>");
						  tmp.append("</div>");
						tmp.append("</div>");
					  tmp.append("</div>");
				  tmp.append("</div>");
				  tmp.append("<div class=\"tab-pane fade profile-edit pt-2\" id=\"patient-disease-prehistory"+item.getId()+"\">");
					
					tmp.append("<h5 class=\"card-title\">1. Tiểu sử gia đình</h5>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-7 col-lg-7\">");
						tmp.append("<label for=\"tien_su_gia_dinh\" class=\"form-check-label col-md-11 col-lg-11\">Có ai trong gia đình");
						  tmp.append(" ông(bà) mắc một trong các bệnh: truyền nhiễm, tim mạch, đái tháo đường, lao, hen phế quản, ung");
						  tmp.append(" thư, động kinh, rối loạn tâm thần, bệnh khác</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1\" type=\"checkbox\" id=\"tien_su_gia_dinh\" ");
						  tmp.append("name=\"tien_su_gia_dinh\" "+(item.isTien_su_gia_dinh()? "checked": "") +">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-5 col-lg-5\">");
						tmp.append("<textarea name=\"tien_su_gia_dinh_ten_benh\" class=\"form-control\" id=\"tien_su_gia_dinh_ten_benh\"");
						  tmp.append("rows=\"1\" placeholder='Nếu \"có\" ghi rõ tên bệnh' name=\"tien_su_gia_dinh_ten_benh\">"+item.getTien_su_gia_dinh_ten_benh()+"</textarea>");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<h5 class=\"card-title\">2. Tiểu sử, bệnh sử bản thân</h5>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"co_benh_trong_5_nam_qua\" class=\"col-md-11 col-lg-11\">1.1. Có bệnh hay bị thương");
						  tmp.append(" trong 5 năm qua</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"aboutaboutaboutabout\" ");
						  tmp.append("name=\"co_benh_trong_5_nam_qua\" "+(item.isCo_benh_trong_5_nam_qua()? "checked": "")+">");

					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"dai_thao_duong\" class=\"col-md-11 col-lg-11\">1.2. Đái tháo đường hoặc kiểm soát tăng");
						  tmp.append(" đường huyết</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"dai_thao_duong\" ");
						  tmp.append("name=\"dai_thao_duong\""+(item.isDai_thao_duong()? "checked": "")+">");

					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"co_benh_than_kinh\" class=\"col-md-11 col-lg-11\">1.3. Có bệnh thần kinh hay bị thương");
						  tmp.append(" ở đầu</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"co_benh_than_kinh\" ");
						tmp.append("name=\"co_benh_than_kinh\""+(item.isCo_benh_than_kinh()? "checked": "")+">");
					  tmp.append("</div>");
					tmp.append("</div>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"benh_tam_than\" class=\"col-md-11 col-lg-11\">1.4. Bệnh tâm thần</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_tam_than\" ");
						  tmp.append("name=\"benh_tam_than\""+(item.isBenh_tam_than()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"benh_mat_giam_thi_luc\" class=\"col-md-11 col-lg-11\">1.5. Bệnh mắt hoặc giảm thị lực");
						  tmp.append(" (trừ trường hợp đeo kính thuốc)</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_mat_giam_thi_luc\" ");
						  tmp.append("name=\"benh_mat_giam_thi_luc\""+(item.isBenh_mat_giam_thi_luc()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"mat_y_thuc_roi_loan_y_thuc\" class=\"col-md-11 col-lg-11\">1.6. Mất ý thức, rối loạn ý");
						  tmp.append(" thức</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\"");
						  tmp.append("id=\"mat_y_thuc_roi_loan_y_thuc\" ");
						  tmp.append("name=\"mat_y_thuc_roi_loan_y_thuc\""+(item.isMat_y_thuc_roi_loan_y_thuc()? "checked": "")+">");
					  tmp.append("</div>");
					tmp.append("</div>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"benh_o_tai\" class=\"col-md-11 col-lg-11\">1.7. Bệnh ở tai, giảm sức nghe hoặc thăng");
						  tmp.append(" bằng</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_o_tai\" ");
						  tmp.append("name=\"benh_o_tai\""+(item.isBenh_o_tai()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"ngat_chong_mat\" class=\"col-md-11 col-lg-11\">1.8. Ngất, chóng mặt</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"ngat_chong_mat\" ");
						  tmp.append("name=\"ngat_chong_mat\""+(item.isNgat_chong_mat()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"benh_o_tim\" class=\"col-md-11 col-lg-11\">1.9. Bệnh ở tim, hoặc nhồi máu cơ tim, các");
						  tmp.append(" bệnh tim mạch khác</label>"); 
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_o_tim\" ");
						  tmp.append("name=\"benh_o_tim\""+(item.isBenh_o_tim()? "checked": "")+">");
					  tmp.append("</div>");
					tmp.append("</div>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"benh_tieu_hoa\" class=\"col-md-11 col-lg-11\">1.10. Bệnh tiêu hóa</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_tieu_hoa\" ");
						  tmp.append("name=\"benh_tieu_hoa\""+(item.isBenh_tieu_hoa()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"phau_thuat_can_thiep_tim_mach\" class=\"col-md-11 col-lg-11\">1.11. Phẫu thuật can");
						  tmp.append(" thiệp tim - mạch (thay van, bắc cầu");
						  tmp.append(" nối, tạo hình mạch, máy tạo nhịp, đặt slent mạch,ghép tim)</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\"");
						  tmp.append("id=\"phau_thuat_can_thiep_tim_mach\"");
						  tmp.append("name=\"phau_thuat_can_thiep_tim_mach\""+(item.isPhau_thuat_can_thiep_tim_mach()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"roi_loan_giac_ngu\" class=\"col-md-11 col-lg-11\">1.12. Rối loạn giấc ngủ, ngừng thở");
						  tmp.append(" khi ngủ, ngủ rũ ban");
						  tmp.append(" ngày, ngáy to</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"roi_loan_giac_ngu\" ");
						  tmp.append("name=\"roi_loan_giac_ngu\""+(item.isRoi_loan_giac_ngu()? "checked": "")+">");
					  tmp.append("</div>");
					tmp.append("</div>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"tang_huyet_ap\" class=\"col-md-11 col-lg-11\">1.13. Tăng huyết áp</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"tang_huyet_ap\" ");
						  tmp.append("name=\"tang_huyet_ap\""+(item.isTang_huyet_ap()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"tai_bien_mach_mau_nao\" class=\"col-md-11 col-lg-11\">1.14. Tai biến mạch máu não hoặc liệt</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"tai_bien_mach_mau_nao\"");
						  tmp.append("name=\"tai_bien_mach_mau_nao\""+(item.isTai_bien_mach_mau_nao()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"kho_tho\" class=\"col-md-11 col-lg-11\">1.15. Khó thở</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"kho_tho\" ");
						  tmp.append("name=\"kho_tho\""+(item.isKho_tho()? "checked": "")+">");
					  tmp.append("</div>");
					tmp.append("</div>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"benh_hoac_ton_thuong_cot_song\" class=\"col-md-11 col-lg-11\">1.16. Bệnh hoặc tổn thương cột sống</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\"");
						  tmp.append("id=\"benh_hoac_ton_thuong_cot_song\" ");
						  tmp.append("name=\"benh_hoac_ton_thuong_cot_song\""+(item.isBenh_hoac_ton_thuong_cot_song()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"benh_phoi_hen\" class=\"col-md-11 col-lg-11\">1.17. Bệnh phổi, hen, khí phế thũng, viêm phế quản mạn tính</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_phoi_hen\" ");
						  tmp.append("name=\"benh_phoi_hen\""+(item.isBenh_phoi_hen()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"su_dung_ruou\" class=\"col-md-11 col-lg-11\">1.18. Sử dụng rượu thường xuyên, liên tục");
						tmp.append("</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"su_dung_ruou\" ");
						  tmp.append("name=\"su_dung_ruou\""+(item.isSu_dung_ruou()? "checked": "")+">");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"benh_than_loc_mau\" class=\"col-md-11 col-lg-11\">1.19. Bệnh thận, lọc máu</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_than_loc_mau\" ");
						  tmp.append("name=\"benh_than_loc_mau\""+(item.isBenh_than_loc_mau()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<label for=\"su_dung_ma_tuy\" class=\"col-md-11 col-lg-11\">1.20. Sử dụng ma túy và chất gây nghiện</label>");
						tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"su_dung_ma_tuy\" ");
						  tmp.append("name=\"su_dung_ma_tuy\""+(item.isSu_dung_ma_tuy()? "checked": "")+">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<textarea name=\"tien_su_benh_nhan_ten_benh\" type=\"text\" class=\"form-control\"");
						  tmp.append("id=\"tien_su_benh_nhan_ten_benh\" placeholder='Nếu \"có\" ghi rõ tên bệnh'>"+item.getTien_su_benh_nhan_ten_benh()+"</textarea>");

					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<h5 class=\"card-title\">3. Câu hỏi khác (nếu có)</h5>");

					tmp.append("<div class=\"row mb-4\">");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"dang_dieu_tri_benh_ten_benh\" class=\"form-control\"");
						  tmp.append("id=\"dang_dieu_tri_benh_ten_benh\" placeholder=\"Các thuốc đang dùng và liều lượng\">"+item.getDang_dieu_tri_benh_ten_benh()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh\" class=\"form-control\"");
						  tmp.append("id=\"mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh\"");
						  tmp.append("placeholder=\"Có thai hoặc nuôi con nhỏ dưới 12 tháng(Đối với phụ nữ)\">"+item.getMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh()+"</textarea>");
					  tmp.append("</div>");
					tmp.append("</div>");
				  tmp.append("</div>");
				  tmp.append("<div class=\"tab-pane fade profile-edit pt-2\" id=\"physical-strength-examination"+item.getId()+"\">");
					tmp.append("<h5 class=\"card-title\">1. Tâm thần</h5>");
					tmp.append("<div class=\"row mb-2\">");

					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"tam_than_noi_dung_kham\" class=\"form-control\" id=\"tam_than_noi_dung_kham\"");
						  tmp.append("placeholder=\"Nội dung khám\" rows=\"1\">"+item.getTam_than_noi_dung_kham()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<textarea name=\"tam_than_ket_luan\" class=\"form-control\" id=\"tam_than_ket_luan\"");
						  tmp.append("placeholder=\"Kết luận\" rows=\"1\">"+item.getTam_than_ket_luan()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<select class=\"form-select\" name=\"tam_than_bac_si\">");
						  users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getTam_than_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<h5 class=\"card-title\">2. Thần Kinh</h5>");
					tmp.append("<div class=\"row mb-2\">");

					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"than_kinh_noi_dung_kham\" class=\"form-control\" id=\"than_kinh_noi_dung_kham\"");
						  tmp.append("placeholder=\"Nội dung khám\" rows=\"1\">"+item.getThan_kinh_noi_dung_kham()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<textarea name=\"than_kinh_ket_luan\" class=\"form-control\" id=\"than_kinh_ket_luan\"");
						  tmp.append("placeholder=\"Kết luận\" rows=\"1\">"+item.getThan_kinh_ket_luan()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\" name=\"than_kinh_bac_si\">");
						tmp.append("<select class=\"form-select\">");
						users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getThan_kinh_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");


					tmp.append("<h5 class=\"card-title\">3. Mắt</h5>");

					tmp.append("<div class=\"row mb-2\">");

					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<p style=\"text-align: center; font-weight: 600;\">Nhìn xa từng mắt</p>");
						tmp.append("<div class=\"row mb-3\">");
						  tmp.append("<label for=\"about\" class=\"col-md-4 col-lg-4 col-form-label label\"");
							tmp.append("style=\"font-size: 16px;\">Không kính</label>");
						  tmp.append("<div class=\"col-md-4 col-lg-4\">");
							tmp.append("<input name=\"mat_tung_mat_khong_kinh_mat_trai\" class=\"form-control\"");
							  tmp.append("id=\"mat_tung_mat_khong_kinh_mat_trai\" placeholder=\"Mắt trái\" value=\""+item.getMat_tung_mat_khong_kinh_mat_trai()+"\">");
						  tmp.append("</div>");
						  tmp.append("<div class=\"col-md-4 col-lg-4\">");
							tmp.append("<input name=\"mat_tung_mat_khong_kinh_mat_phai\" class=\"form-control\"");
							  tmp.append("id=\"mat_tung_mat_khong_kinh_mat_phai\" placeholder=\"Mắt phải\" value=\""+item.getMat_tung_mat_co_kinh_mat_phai()+"\">");
						  tmp.append("</div>");
						tmp.append("</div>");
						tmp.append("<div class=\"row mb-2\">");
						  tmp.append("<label for=\"about\" class=\"col-md-4 col-lg-4 col-form-label label\" style=\"font-size: 16px;\">Có");
							tmp.append(" kính</label>");
						  tmp.append("<div class=\"col-md-4 col-lg-4\">");
							tmp.append("<input name=\"mat_tung_mat_co_kinh_mat_trai\" class=\"form-control\"");
							  tmp.append("id=\"mat_tung_mat_co_kinh_mat_trai\" placeholder=\"Mắt trái\" value=\""+item.getMat_tung_mat_co_kinh_mat_trai()+"\">");
						  tmp.append("</div>");
						  tmp.append("<div class=\"col-md-4 col-lg-4\">");
							tmp.append("<input name=\"mat_tung_mat_co_kinh_mat_phai\" class=\"form-control\"");
							  tmp.append("id=\"mat_tung_mat_co_kinh_mat_phai\" placeholder=\"Mắt phải\" value=\""+item.getMat_tung_mat_co_kinh_mat_phai()+"\">");
						  tmp.append("</div>");
						tmp.append("</div>");
					  tmp.append("</div>");

					  tmp.append("<div class=\"col-md-2 col-lg-2\">");
						tmp.append("<p style=\"text-align: center;font-weight: 600;\">Nhìn xa hai mắt</p>");
						tmp.append("<div class=\"row mb-3\">");
						  tmp.append("<input name=\"mat_hai_mat_khong_kinh\" class=\"form-control offset-lg-1 col-md-10 col-lg-10 mb-3\"");
							tmp.append("id=\"mat_hai_mat_khong_kinh\" placeholder=\"Không kính\" value=\""+item.getMat_hai_mat_khong_kinh()+"\">");
						  tmp.append("<input name=\"mat_hai_mat_co_kinh\" class=\"form-control offset-lg-1 col-md-10 col-lg-10 \"");
							tmp.append("id=\"mat_hai_mat_co_kinh\" placeholder=\"Có kính\" value=\""+item.getMat_hai_mat_co_kinh()+"\">");
						tmp.append("</div>");

					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<p style=\"text-align: center;font-weight: 600;\">Thị trường</p>");
						tmp.append("<div class=\"mb-3\">");
						  tmp.append("<label for=\"mat_thi_truong_ngang\" class=\"offset-lg-1 col-md-10 col-lg-10 col-form-label label\"");
							tmp.append("style=\"font-size: 16px;\">Thị trường ngang hạn chế</label>");
						  tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"mat_thi_truong_ngang\"");
							tmp.append("name=\"mat_thi_truong_ngang\" value=\""+item.isMat_thi_truong_ngang()+"\">");
						tmp.append("</div>");
						tmp.append("<div>");
						  tmp.append("<label for=\"mat_thi_truong_dung\" class=\"offset-lg-1 col-md-10 col-lg-10 col-form-label label\"");
							tmp.append("style=\"font-size: 16px;\">Thị trường đứng hạn chế</label>");
						  tmp.append("<input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"mat_thi_truong_dung\"");
							tmp.append("name=\"mat_thi_truong_dung\" value=\""+item.isMat_thi_truong_dung()+"\">");
						tmp.append("</div>");
					  tmp.append("</div>");

					tmp.append("</div>");

					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"about1\" class=\"col-md-2 col-lg-2\" style=\"font-size: 16px;\">Sắc giác</label>");
					  tmp.append("<div class=\"col-md-2 col-lg-2\">");
						tmp.append("<label for=\"mat_sac_giac_binh_thuong\" class=\"col-md-10 col-lg-10\">Bình thường</label>");
						tmp.append("<input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\" id=\"mat_sac_giac_binh_thuong\"");
						  tmp.append("name=\"mat_sac_giac_binh_thuong\" value=\""+item.isMat_sac_giac_binh_thuong()+"\">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-2 col-lg-2\">");
						tmp.append("<label for=\"mat_sac_giac_mu_mau_toan_bo\" class=\"col-md-10 col-lg-10\">Mù màu toàn bộ</label>");
						tmp.append("<input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\"");
						  tmp.append("id=\"mat_sac_giac_mu_mau_toan_bo\" name=\"mat_sac_giac_mu_mau_toan_bo\" value=\""+item.isMat_sac_giac_mu_mau_toan_bo()+"\">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-2 col-lg-2\">");
						tmp.append("<label for=\"mat_sac_giac_mu_mau_do\" class=\"col-md-10 col-lg-10\">Mù màu đỏ</label>");
						tmp.append("<input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\" id=\"mat_sac_giac_mu_mau_do\"");
						  tmp.append("name=\"mat_sac_giac_mu_mau_do\" value=\""+item.isMat_sac_giac_mu_mau_do()+"\">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-2 col-lg-2\">");
						tmp.append("<label for=\"mat_sac_giac_mu_mau_xanh_la\" class=\"col-md-10 col-lg-10\">Mù màu xanh lá </label>");
						tmp.append("<input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\"");
						  tmp.append("id=\"mat_sac_giac_mu_mau_xanh_la\" name=\"mat_sac_giac_mu_mau_xanh_la\" value=\""+item.isMat_sac_giac_mu_mau_xanh_la()+"\">");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-2 col-lg-2\">");
						tmp.append("<label for=\"mat_sac_giac_mu_mau_vang\" class=\"col-md-10 col-lg-10\">Mù màu vàng</label>");
						tmp.append("<input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\" id=\"mat_sac_giac_mu_mau_vang\"");
						  tmp.append("name=\"mat_sac_giac_mu_mau_vang\" value=\""+item.isMat_sac_giac_mu_mau_vang()+"\">");
					  tmp.append("</div>");

					tmp.append("</div>");

					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"mat_ten_benh\" class=\"form-control\" id=\"mat_ten_benh\"");
						  tmp.append("placeholder=\"Các bệnh về mắt\" rows=\"1\">"+item.getMat_ten_benh()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<textarea name=\"mat_ket_luan\" class=\"form-control\" id=\"mat_ket_luan\" placeholder=\"Kết luận\"");
						  tmp.append("rows=\"1\">"+item.getMat_ket_luan()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<select class=\"form-select\" name=\"mat_bac_si\">");
						users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getMat_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<h5 class=\"card-title\">4. Tai mũi họng</h5>");
					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<div class=\"row\">");
						  tmp.append("<label for=\"about\" class=\"col-md-4 col-lg-4 col-form-label label\" style=\"font-size: 16px;\">Tai");
							tmp.append(" trái</label>");
						  tmp.append("<div class=\"col-md-4 col-lg-4\">");
							tmp.append("<input name=\"tai_mui_hong_tai_trai_noi_thuong\" class=\"form-control\"");
							  tmp.append("id=\"tai_mui_hong_tai_trai_noi_thuong\" placeholder=\"Nói thường\" value=\""+item.getTai_mui_hong_tai_trai_noi_thuong()+"\">");
						  tmp.append("</div>");
						  tmp.append("<div class=\"col-md-4 col-lg-4\">");
							tmp.append("<input name=\"tai_mui_hong_tai_trai_noi_tham\" class=\"form-control\"");
							  tmp.append("id=\"tai_mui_hong_tai_trai_noi_tham\" placeholder=\"Nói thầm\" value=\""+item.getTai_mui_hong_tai_trai_noi_tham()+"\">");
						  tmp.append("</div>");
						tmp.append("</div>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<div class=\"row\">");
						  tmp.append("<label for=\"about\" class=\"col-md-4 col-lg-4 col-form-label label\" style=\"font-size: 16px;\">Tai");
							tmp.append(" phải</label>");
						  tmp.append("<div class=\"col-md-4 col-lg-4\">");
							tmp.append("<input name=\"tai_mui_hong_tai_phai_noi_thuong\" class=\"form-control\"");
							  tmp.append("id=\"tai_mui_hong_tai_phai_noi_thuong\" placeholder=\"Nói thường\" value=\""+item.getTai_mui_hong_tai_phai_noi_thuong()+"\">");
						  tmp.append("</div>");
						  tmp.append("<div class=\"col-md-4 col-lg-4\">");
							tmp.append("<input name=\"tai_mui_hong_tai_phai_noi_tham\" class=\"form-control\"");
							  tmp.append("id=\"tai_mui_hong_tai_phai_noi_tham\" placeholder=\"Nói thầm\" value=\""+item.getTai_mui_hong_tai_phai_noi_tham()+"\">");
						  tmp.append("</div>");
						tmp.append("</div>");
					  tmp.append("</div>");
					tmp.append("</div>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"tai_mui_hong_ten_benh\" class=\"form-control\" id=\"tai_mui_hong_ten_benh\"");
						  tmp.append("placeholder=\"Các bệnh về tai mũi họng\" rows=\"1\">"+item.getTai_mui_hong_ten_benh()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<textarea name=\"tai_mui_hong_ket_luan\" class=\"form-control\" id=\"tai_mui_hong_ket_luan\"");
						  tmp.append("placeholder=\"Kết luận\" rows=\"1\">"+item.getTai_mui_hong_ket_luan()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<select class=\"form-select\" name=\"tai_mui_hong_bac_si\">");
						users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getTai_mui_hong_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");


					tmp.append("<h5 class=\"card-title\">5. Tim mạch</h5>");
					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"tim_mach_mach\" class=\"col-md-3 col-lg-2 col-form-label label\"");
						tmp.append("style=\"font-size: 16px;\">Nhịp tim</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<input name=\"tim_mach_mach\" class=\"form-control\" id=\"tim_mach_mach\" value=\""+item.getTim_mach_mach()+"\">");
					  tmp.append("</div>");

					  tmp.append("<label for=\"tim_mach_huyet_ap\" class=\"col-md-3 col-lg-2 col-form-label label\"");
						tmp.append("style=\"font-size: 16px;\">Huyết áp</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<input name=\"tim_mach_huyet_ap\" class=\"form-control\" id=\"tim_mach_huyet_ap\" value=\""+item.getTim_mach_mach()+"\">");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"tim_mach_ten_benh\" class=\"form-control\" id=\"tim_mach_ten_benh\"");
						  tmp.append("placeholder=\"Các bệnh về tim\" rows=\"1\">"+item.getTim_mach_ten_benh()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<textarea name=\"tim_mach_ket_luan\" class=\"form-control\" id=\"tim_mach_ket_luan\"");
						  tmp.append("placeholder=\"Kết luận\" rows=\"1\">"+item.getTim_mach_ket_luan()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<select class=\"form-select\" name=\"tim_mach_bac_si\">");
						users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getTim_mach_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");


					tmp.append("<h5 class=\"card-title\">6. Hô hấp</h5>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"ho_hap_noi_dung_kham\" class=\"form-control\" id=\"ho_hap_noi_dung_kham\"");
						  tmp.append("placeholder=\"Các bệnh về hô hấp\" rows=\"1\">"+item.getHo_hap_noi_dung_kham()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<textarea name=\"ho_hap_ket_luan\" class=\"form-control\" id=\"ho_hap_ket_luan\"");
						  tmp.append("placeholder=\"Kết luận\" rows=\"1\">"+item.getHo_hap_ket_luan()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<select class=\"form-select\" name=\"ho_hap_bac_si\">");
						users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getHo_hap_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<h5 class=\"card-title\">7. Cơ xương khớp</h5>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"co_xuong_khop_noi_dung_kham\" class=\"form-control\"");
						  tmp.append("id=\"co_xuong_khop_noi_dung_kham\" placeholder=\"Các bệnh về cơ xương khớp\" rows=\"1\">"+item.getCo_xuong_khop_noi_dung_kham()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<textarea name=\"co_xuong_khop_ket_luan\" class=\"form-control\" id=\"co_xuong_khop_ket_luan\"");
						  tmp.append("placeholder=\"Kết luận\" rows=\"1\">"+item.getCo_xuong_khop_ket_luan()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<select class=\"form-select\" name=\"co_xuong_khop_bac_si\">");
						users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getCo_xuong_khop_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<h5 class=\"card-title\">8. Nội tiết</h5>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"noi_tiet_noi_dung_kham\" class=\"form-control\" id=\"noi_tiet_noi_dung_kham\"");
						  tmp.append("placeholder=\"Các bệnh về nội tiết\" rows=\"1\">"+item.getNoi_tiet_noi_dung_kham()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<textarea name=\"noi_tiet_ket_luan\" class=\"form-control\" id=\"noi_tiet_ket_luan\"");
						  tmp.append("placeholder=\"Kết luận\" rows=\"1\">"+item.getNoi_tiet_ket_luan()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<select class=\"form-select\" name=\"noi_tiet_bac_si\">");
						users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getNoi_tiet_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<h5 class=\"card-title\">9. Thai sản</h5>");
					tmp.append("<div class=\"row mb-2\">");
					  tmp.append("<div class=\"col-md-6 col-lg-6\">");
						tmp.append("<textarea name=\"thai_san_noi_dung_kham\" class=\"form-control\" id=\"thai_san_noi_dung_kham\"");
						  tmp.append("placeholder=\"Các bệnh về thai sản\" rows=\"1\">"+item.getThai_san_noi_dung_kham()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<textarea name=\"thai_san_ket_luan\" class=\"form-control\" id=\"thai_san_ket_luan\"");
						  tmp.append("placeholder=\"Kết luận\" rows=\"1\">"+item.getThai_san_ket_luan()+"</textarea>");
					  tmp.append("</div>");
					  tmp.append("<div class=\"col-md-3 col-lg-3\">");
						tmp.append("<select class=\"form-select\" name=\"thai_san_bac_si\">");
						users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getThai_san_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");

				  tmp.append("</div>");

				  tmp.append("<div class=\"tab-pane fade pt-2\" id=\"subclinical-examination"+item.getId()+"\">");
					tmp.append("<h5 class=\"card-title\">1. Các xét nghiệm bắt buộc</h5>");

					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"xet_nghiem_morphin_heroin\"");
						tmp.append("class=\"col-md-3 col-lg-2 col-form-label label\">Morphin/Heroin</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<select class=\"form-select\" name=\"xet_nghiem_morphin_heroin\">");
						if(item.isXet_nghiem_morphin_heroin()) {
							tmp.append("<option value=\"0\" >Âm tính</option>");
							tmp.append("<option value=\"1\" selected>Dương tính</option>");
						}else {
							tmp.append("<option value=\"0\" selected>Âm tính</option>");
							tmp.append("<option value=\"1\" >Dương tính</option>");
						}
						tmp.append("</select>");
					  tmp.append("</div>");

					  tmp.append("<label for=\"xet_nghiem_amphetamin\"");
						tmp.append("class=\"col-md-3 col-lg-2 col-form-label label\">Amphetamin</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<select class=\"form-select\" name=\"xet_nghiem_amphetamin\">");
						if(item.isXet_nghiem_amphetamin()) {
							tmp.append("<option value=\"0\" >Âm tính</option>");
							tmp.append("<option value=\"1\" selected>Dương tính</option>");
						}else {
							tmp.append("<option value=\"0\" selected>Âm tính</option>");
							tmp.append("<option value=\"1\" >Dương tính</option>");
						}
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");
					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"xet_nghiem_methamphetamin\"");
						tmp.append("class=\"col-md-3 col-lg-2 col-form-label label\">Methamphetamin</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<select class=\"form-select\" name=\"xet_nghiem_methamphetamin\">");
						if(item.isXet_nghiem_methamphetamin()) {
							tmp.append("<option value=\"0\" >Âm tính</option>");
							tmp.append("<option value=\"1\" selected>Dương tính</option>");
						}else {
							tmp.append("<option value=\"0\" selected>Âm tính</option>");
							tmp.append("<option value=\"1\" >Dương tính</option>");
						}
						tmp.append("</select>");
					  tmp.append("</div>");

					  tmp.append("<label for=\"xet_nghiem_marijuana\" class=\"col-md-3 col-lg-2 col-form-label label\">Marijuana (cần");
						tmp.append("sa)</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<select class=\"form-select\" name=\"xet_nghiem_marijuana\">");
						if(item.isXet_nghiem_marijuana()) {
							tmp.append("<option value=\"0\" >Âm tính</option>");
							tmp.append("<option value=\"1\" selected>Dương tính</option>");
						}else {
							tmp.append("<option value=\"0\" selected>Âm tính</option>");
							tmp.append("<option value=\"1\" >Dương tính</option>");
						}
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"xet_nghiem_nong_do_con\" class=\"col-md-3 col-lg-2 col-form-label label\">Độ cồn</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<input name=\"xet_nghiem_nong_do_con\" class=\"form-control\" id=\"xet_nghiem_nong_do_con\"");
						  tmp.append("value=\""+item.getXet_nghiem_nong_do_con()+"\">");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<h5 class=\"card-title\">2. Các xét nghiệm khác</h5>");
					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"xet_nghiem_khac_ket_qua\" class=\"col-md-3 col-lg-2 col-form-label label\">Kết");
						tmp.append("quả</label>");
					  tmp.append("<div class=\"col-md-9 col-lg-10\">");
						tmp.append("<textarea name=\"xet_nghiem_khac_ket_qua\" class=\"form-control\"");
						  tmp.append("id=\"xet_nghiem_khac_ket_qua\">"+item.getXet_nghiem_khac_ket_qua()+"</textarea>");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"xet_nghiem_khac_ket_luan\" class=\"col-md-3 col-lg-2 col-form-label label\">Kết");
						tmp.append("luận</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<textarea name=\"xet_nghiem_khac_ket_luan\" class=\"form-control\" id=\"xet_nghiem_khac_ket_luan\"");
						  tmp.append("placeholder=\"Kết luận\" rows=\"1\">"+item.getXet_nghiem_khac_ket_luan()+"</textarea>");
					  tmp.append("</div>");

					  tmp.append("<label for=\"xet_nghiem_bac_si\" class=\"col-md-3 col-lg-2 col-form-label label\">Bác sĩ</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<select class=\"form-select\" name=\"xet_nghiem_bac_si\">");
						users.forEach(user->{
							  if(user.equalsIgnoreCase(item.getXet_nghiem_bac_si())) {
								  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
							  }else {
								  tmp.append("<option value=\""+user+"\">"+user+"</option>");
							  }							  
						  });
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");
				  tmp.append("</div>");

				  tmp.append("<div class=\"tab-pane fade pt-2\" id=\"conclusion"+item.getId()+"\">");
					tmp.append("<h5 class=\"card-title\">1. Kết luận</h5>");
					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"ket_luan\" class=\"col-md-3 col-lg-2 col-form-label label\">Kết luận</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<textarea name=\"ket_luan\" class=\"form-control\" id=\"ket_luan\" placeholder=\"Kết luận\"");
						  tmp.append("rows=\"1\">"+item.getKet_luan()+"</textarea>");
					  tmp.append("</div>");

					  tmp.append("<label for=\"ngay_kham_lai\" class=\"col-md-3 col-lg-2 col-form-label label\">Ngày khám</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<input name=\"ngay_kham\" type=\"date\" class=\"form-control\" id=\"ngay_kham\" value=\""+item.getNgay_kham()+"\">");
					  tmp.append("</div>");
					  
//					  tmp.append("<!-- <label for=\"about\" class=\"col-md-3 col-lg-2 col-form-label label\">Bác sĩ</label>");
//					tmp.append("<div class=\"col-md-4 col-lg-4\">");
//					  tmp.append("<select class=\"form-select\">");
//					  users.forEach(user->{
//						  if(user.equalsIgnoreCase(item.getTam_than_bac_si())) {
//							  tmp.append("<option value=\""+user+"\" selected>"+user+"</option>");
//						  }else {
//							  tmp.append("<option value=\""+user+"\">"+user+"</option>");
//						  }							  
//					  });
//					  tmp.append("</select>");
//					tmp.append("</div> -->");
					tmp.append("</div>");

					tmp.append("<h5 class=\"card-title\">2. Kết luận khác</h5>");
					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"ket_luan_suc_khoe\" class=\"col-md-3 col-lg-2 col-form-label label\">Kết luận</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<select class=\"form-select\" name=\"ket_luan_suc_khoe\">");
						if(item.getKet_luan_suc_khoe()==1) {
							tmp.append("<option value=\"1\" selected>Đủ điều kiện sức khỏe lái xe hạng...</option>");
							  tmp.append("<option value=\"2\">Không đủ điều kiện sức khỏe lái xe hạng...</option>");
							  tmp.append("<option value=\"3\">Đạt tiêu chuẩn sức khỏe lái xe hạng... nhưng yêu cầu khám lại</option>");
						}
						if(item.getKet_luan_suc_khoe()==2) {
							tmp.append("<option value=\"1\" >Đủ điều kiện sức khỏe lái xe hạng...</option>");
							  tmp.append("<option value=\"2\" selected>Không đủ điều kiện sức khỏe lái xe hạng...</option>");
							  tmp.append("<option value=\"3\">Đạt tiêu chuẩn sức khỏe lái xe hạng... nhưng yêu cầu khám lại</option>");
						}
						if(item.getKet_luan_suc_khoe()==3) {
							tmp.append("<option value=\"1\" >Đủ điều kiện sức khỏe lái xe hạng...</option>");
							  tmp.append("<option value=\"2\">Không đủ điều kiện sức khỏe lái xe hạng...</option>");
							  tmp.append("<option value=\"3\" selected>Đạt tiêu chuẩn sức khỏe lái xe hạng... nhưng yêu cầu khám lại</option>");
						}
						  tmp.append("</select>");
					  tmp.append("</div>");

					  tmp.append("<label for=\"hang_xe\" class=\"col-md-3 col-lg-2 col-form-label label\">Bằng lái xe</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<select class=\"form-select\" name=\"hang_xe\">");
						ArrayList<String> xe = new ArrayList<String>();
						xe.add("A1");
						xe.add("A2");
						xe.add("B1");
						xe.add("B2");
						xe.add("C");
						xe.add("D");
						xe.add("E");
						xe.add("F");
						xe.forEach(b->{
							if(b.equalsIgnoreCase(item.getHang_xe())) {
								  tmp.append("<option value=\""+b+"\" selected>"+b+"</option>");
							}else {
								tmp.append("<option value=\""+b+"\">"+b+"</option>");
							}
							
						});
						xe = null;
						tmp.append("</select>");
					  tmp.append("</div>");
					tmp.append("</div>");

					tmp.append("<div class=\"row mb-3\">");
					  tmp.append("<label for=\"ngay_kham_lai\" class=\"col-md-3 col-lg-2 col-form-label label\">Ngày khám lại</label>");
					  tmp.append("<div class=\"col-md-4 col-lg-4\">");
						tmp.append("<input name=\"ngay_kham_lai\" type=\"date\" class=\"form-control\" id=\"ngay_kham_lai\" value=\""+item.getNgay_kham_lai()+"\">");
					  tmp.append("</div>");

					tmp.append("</div>");

					tmp.append("<div class=\"text-center\">");
					  tmp.append("<a href=\"#\" target=\"_blank\" class=\"btn btn-success\"><i class=\"bi bi-filetype-pdf\"></i> In kết quả</a>");
						
					tmp.append("</div>");
					//================================
					tmp.append("<input name=\"idGKSK\" type=\"hidden\"  id=\"idForPost\" value=\""+item.getId()+"\">");
					tmp.append("<input name=\"idCustomer\" type=\"hidden\"  id=\"idForPost\" value=\""+cus.getCustomer_id()+"\">");
				  tmp.append("</div>");
				tmp.append("</div><!-- End Bordered Tabs -->");
			  tmp.append("</div>");
			  tmp.append("<div class=\"modal-footer\">");
				tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Đóng</button>");
				tmp.append("<button type=\"submit\" class=\"btn btn-warning\"><i class=\"bi bi-pencil-square\"></i> Cập nhật</button>");
			  tmp.append("</div>");
			tmp.append("</div>");
		  tmp.append("</div>");
		tmp.append("</div><!-- End Basic Modal-->");
		tmp.append("</form>");
		
		return tmp;
		

	}
	
	
	public static String viewAddGKSK(ArrayList<String> users, CustomerObject cus) {
		StringBuilder tmp = new StringBuilder();

	tmp.append(" <!-- Bordered Tabs -->");
	tmp.append(" <ul class=\"nav nav-tabs nav-tabs-bordered mt-3\">");

	tmp.append(" <li class=\"nav-item\">");
	  tmp.append(" <button class=\"nav-link active\" data-bs-toggle=\"tab\" data-bs-target=\"#patient-overview\">Thông tin bệnh nhân</button>");
	tmp.append(" </li>");

	tmp.append(" <li class=\"nav-item\">");
	  tmp.append(" <button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#patient-disease-prehistory\">Tiền sử</button>");
	tmp.append(" </li>");

	tmp.append(" <li class=\"nav-item\">");
	  tmp.append(" <button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#physical-strength-examination\">Khám lâm sàn</button>");
	tmp.append(" </li>");

	tmp.append(" <li class=\"nav-item\">");
	  tmp.append(" <button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#subclinical-examination\">Cận lâm sàn</button>");
	tmp.append(" </li>");

	tmp.append(" <li class=\"nav-item\">");
	  tmp.append(" <button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#conclusion\">Kết luận</button>");
	tmp.append(" </li>");

	tmp.append(" </ul>");
	tmp.append(" <div class=\"tab-content pt-2\">");
	tmp.append(" <div class=\"tab-pane fade show active profile-overview\" id=\"patient-overview\">");
		tmp.append(" <h5 class=\"card-title\">Thông tin người khám</h5>");
		tmp.append(" <form action=\"/btl/gksk/add\" method=\"post\">");
		tmp.append(" <div class=\"row\">");
		  tmp.append(" <div class=\"col-md-6\">");
			tmp.append(" <div class=\"row mb-3\">");
			  tmp.append(" <label for=\"cusName\" class=\"col-sm-4 col-form-label label\">Họ tên</label>");
			  tmp.append(" <div class=\"col-sm-8\">");
				tmp.append(" <input type=\"text\" class=\"form-control\" name=\"cusName\" value=\""+cus.getCustomer_fullname()+"\">");
			  tmp.append(" </div>");
			tmp.append(" </div>");
			tmp.append(" <div class=\"row mb-3\">");
			  tmp.append(" <label for=\"cusBirth\" class=\"col-sm-4 col-form-label label\">Ngày sinh</label>");
			  tmp.append(" <div class=\"col-sm-8\">");
				tmp.append(" <input type=\"date\" class=\"form-control\" value=\"2024-09-09\" name=\"cusBirth\">");
			  tmp.append(" </div>");
			tmp.append(" </div>");
			tmp.append(" <div class=\"row mb-3\">");
			  tmp.append(" <label for=\"cusGender\" class=\"col-sm-4 col-form-label label\">Giới tính</label>");
			  tmp.append(" <div class=\"col-sm-8\">");
				tmp.append(" <select class=\"form-select\" name=\"cusGender\">");
				  tmp.append(" <option value=\"0\" selected>Nữ</option>");
				  tmp.append(" <option value=\"1\">Nam</option>");
				tmp.append(" </select>");
			  tmp.append(" </div>");
			tmp.append(" </div>");
			tmp.append(" <div class=\"row mb-3\">");
			  tmp.append(" <label for=\"cusPhone\" class=\"col-sm-4 col-form-label label\">Số điện thoại</label>");
			  tmp.append(" <div class=\"col-sm-8\">");
				tmp.append(" <input type=\"text\" class=\"form-control\" name=\"cusPhone\" value=\""+cus.getCustomer_phone()+"\">");
			  tmp.append(" </div>");
			tmp.append(" </div>");
		  tmp.append(" </div>");
		  tmp.append(" <div class=\"col-md-6\">");
			tmp.append(" <div class=\"row mb-3\">");
			  tmp.append(" <label for=\"cusCCCD\" class=\"col-sm-4 col-form-label label\">Số CCCD</label>");
			  tmp.append(" <div class=\"col-sm-8\">");
				tmp.append(" <input type=\"text\" class=\"form-control\" name=\"cusCCCD\">");
			  tmp.append(" </div>");
			tmp.append(" </div>");
			tmp.append(" <div class=\"row mb-3\">");
			  tmp.append(" <label for=\"cusCCCDDate\" class=\"col-sm-4 col-form-label label\">Ngày cấp</label>");
			  tmp.append(" <div class=\"col-sm-8\">");
				tmp.append(" <input type=\"date\" class=\"form-control\" value=\"2024-09-09\" name=\"cusCCCDDate\">");
			  tmp.append(" </div>");
			tmp.append(" </div>");
			tmp.append(" <div class=\"row mb-3\">");
			  tmp.append(" <label for=\"name=\"cusCCCDAddress\" class=\"col-sm-4 col-form-label label\">Nơi cấp</label>");
			  tmp.append(" <div class=\"col-sm-8\">");
				tmp.append(" <input type=\"text\" class=\"form-control\" name=\"cusCCCDAddress\">");
			  tmp.append(" </div>");
			tmp.append(" </div>");
			tmp.append(" <div class=\"row mb-3\">");
			  tmp.append(" <label for=\"cusAddress\" class=\"col-sm-4 col-form-label label\">Địa chỉ</label>");
			  tmp.append(" <div class=\"col-sm-8\">");
				tmp.append(" <input type=\"text\" class=\"form-control\" name=\"cusAddress\" value=\""+cus.getCustomer_address()+"\">");
			  tmp.append(" </div>");
			tmp.append(" </div>");
		  tmp.append(" </div> ");
		tmp.append(" </div>");

	tmp.append(" </div>");
	tmp.append(" <div class=\"tab-pane fade profile-edit pt-2\" id=\"patient-disease-prehistory\">");
	  tmp.append(" <!-- style=\"margin-top: -15px; margin-bottom: -15px;\" -->");
	  tmp.append(" <h5 class=\"card-title\">1. Tiểu sử gia đình</h5>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-7 col-lg-7\">");
		  tmp.append(" <label for=\"tien_su_gia_dinh\" class=\"form-check-label col-md-11 col-lg-11\">Có ai trong gia đình ông");
			tmp.append(" (bà) mắc một trong các bệnh: truyền nhiễm, tim mạch, đái tháo đường, lao, hen phế quản, ung");
			tmp.append(" thư, động kinh, rối loạn tâm thần, bệnh khác</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1\" type=\"checkbox\" id=\"tien_su_gia_dinh\" name=\"tien_su_gia_dinh\">");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-5 col-lg-5\">");
		  tmp.append(" <textarea name=\"tien_su_gia_dinh_ten_benh\" class=\"form-control\" id=\"tien_su_gia_dinh_ten_benh\" rows=\"1\"");
			tmp.append(" placeholder='Nếu \"có\" ghi rõ tên bệnh' name=\"tien_su_gia_dinh_ten_benh\"></textarea>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <h5 class=\"card-title\">2. Tiểu sử, bệnh sử bản thân</h5>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"co_benh_trong_5_nam_qua\" class=\"col-md-11 col-lg-11\">1.1. Có bệnh hay bị thương trong 5 năm qua</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"aboutaboutaboutabout\" name=\"co_benh_trong_5_nam_qua\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"dai_thao_duong\" class=\"col-md-11 col-lg-11\">1.2. Đái tháo đường hoặc kiểm soát tăng đường huyết</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"dai_thao_duong\" name=\"dai_thao_duong\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"co_benh_than_kinh\" class=\"col-md-11 col-lg-11\">1.3. Có bệnh thần kinh hay bị thương ở đầu</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"co_benh_than_kinh\" name=\"co_benh_than_kinh\">");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"benh_tam_than\" class=\"col-md-11 col-lg-11\">1.4. Bệnh tâm thần</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_tam_than\" name=\"benh_tam_than\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"benh_mat_giam_thi_luc\" class=\"col-md-11 col-lg-11\">1.5. Bệnh mắt hoặc giảm thị lực (trừ trường hợp đeo kính");
			tmp.append(" thuốc)</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_mat_giam_thi_luc\" name=\"benh_mat_giam_thi_luc\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"mat_y_thuc_roi_loan_y_thuc\" class=\"col-md-11 col-lg-11\">1.6. Mất ý thức, rối loạn ý thức</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"mat_y_thuc_roi_loan_y_thuc\" name=\"mat_y_thuc_roi_loan_y_thuc\">");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"benh_o_tai\" class=\"col-md-11 col-lg-11\">1.7. Bệnh ở tai, giảm sức nghe hoặc thăng bằng</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_o_tai\" name=\"benh_o_tai\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"ngat_chong_mat\" class=\"col-md-11 col-lg-11\">1.8. Ngất, chóng mặt</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"ngat_chong_mat\" name=\"ngat_chong_mat\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"benh_o_tim\" class=\"col-md-11 col-lg-11\">1.9. Bệnh ở tim, hoặc nhồi máu cơ tim, các bệnh tim mạch");
			tmp.append(" khác</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_o_tim\" name=\"benh_o_tim\">");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"benh_tieu_hoa\" class=\"col-md-11 col-lg-11\">1.10. Bệnh tiêu hóa</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_tieu_hoa\" name=\"benh_tieu_hoa\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"phau_thuat_can_thiep_tim_mach\" class=\"col-md-11 col-lg-11\">1.11. Phẫu thuật can thiệp tim - mạch (thay van, bắc cầu");
			tmp.append(" nối, tạo hình mạch, máy tạo nhịp, đặt slent mạch,");
			tmp.append(" ghép tim)</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"phau_thuat_can_thiep_tim_mach\" name=\"phau_thuat_can_thiep_tim_mach\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"roi_loan_giac_ngu\" class=\"col-md-11 col-lg-11\">1.12. Rối loạn giấc ngủ, ngừng thở khi ngủ, ngủ rũ ban");
			tmp.append(" ngày, ngáy to</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"roi_loan_giac_ngu\" name=\"roi_loan_giac_ngu\">");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"tang_huyet_ap\" class=\"col-md-11 col-lg-11\">1.13. Tăng huyết áp</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"tang_huyet_ap\" name=\"tang_huyet_ap\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"tai_bien_mach_mau_nao\" class=\"col-md-11 col-lg-11\">1.14. Tai biến mạch máu não hoặc liệt</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"tai_bien_mach_mau_nao\" name=\"tai_bien_mach_mau_nao\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"kho_tho\" class=\"col-md-11 col-lg-11\">1.15. Khó thở</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"kho_tho\" name=\"kho_tho\">");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"benh_hoac_ton_thuong_cot_song\" class=\"col-md-11 col-lg-11\">1.16. Bệnh hoặc tổn thương cột sống</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_hoac_ton_thuong_cot_song\" name=\"benh_hoac_ton_thuong_cot_song\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"benh_phoi_hen\" class=\"col-md-11 col-lg-11\">1.17. Bệnh phổi, hen, khí phế thũng, viêm phế quản mạn");
			tmp.append(" tính</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_phoi_hen\" name=\"benh_phoi_hen\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"su_dung_ruou\" class=\"col-md-11 col-lg-11\">1.18. Sử dụng rượu thường xuyên, liên tục </label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"su_dung_ruou\" name=\"su_dung_ruou\">");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	tmp.append("   ");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"benh_than_loc_mau\" class=\"col-md-11 col-lg-11\">1.19. Bệnh thận, lọc máu</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"benh_than_loc_mau\" name=\"benh_than_loc_mau\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <label for=\"su_dung_ma_tuy\" class=\"col-md-11 col-lg-11\">1.20. Sử dụng ma túy và chất gây nghiện</label>");
		  tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"su_dung_ma_tuy\" name=\"su_dung_ma_tuy\">");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <textarea name=\"tien_su_benh_nhan_ten_benh\" type=\"text\" class=\"form-control\" id=\"tien_su_benh_nhan_ten_benh\"");
			tmp.append(" placeholder='Nếu \"có\" ghi rõ tên bệnh'></textarea>");

		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <h5 class=\"card-title\">3. Câu hỏi khác (nếu có)</h5>");

	  tmp.append(" <div class=\"row mb-4\">");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"dang_dieu_tri_benh_ten_benh\" class=\"form-control\" id=\"dang_dieu_tri_benh_ten_benh\"");
			tmp.append(" placeholder=\"Các thuốc đang dùng và liều lượng\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh\" class=\"form-control\" id=\"mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh\"");
			tmp.append(" placeholder=\"Có thai hoặc nuôi con nhỏ dưới 12 tháng(Đối với phụ nữ)\"></textarea>");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	tmp.append(" </div>");
	tmp.append(" <div class=\"tab-pane fade profile-edit pt-2\" id=\"physical-strength-examination\">");
	  tmp.append(" <h5 class=\"card-title\">1. Tâm thần</h5>");
	  tmp.append(" <div class=\"row mb-2\">");

		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"tam_than_noi_dung_kham\" class=\"form-control\" id=\"tam_than_noi_dung_kham\"");
		   tmp.append(" placeholder=\"Nội dung khám\"");
			tmp.append(" rows=\"1\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <textarea name=\"tam_than_ket_luan\" class=\"form-control\" id=\"tam_than_ket_luan\"");
		  tmp.append(" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <select class=\"form-select\" name=\"tam_than_bac_si\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <h5 class=\"card-title\">2. Thần Kinh</h5>");
	  tmp.append(" <div class=\"row mb-2\">");

		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"than_kinh_noi_dung_kham\" class=\"form-control\" ");
		  tmp.append(" id=\"than_kinh_noi_dung_kham\" placeholder=\"Nội dung khám\"");
			tmp.append(" rows=\"1\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <textarea name=\"than_kinh_ket_luan\" class=\"form-control\" id=\"than_kinh_ket_luan\" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\" name=\"than_kinh_bac_si\">");
		  tmp.append(" <select class=\"form-select\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
			
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");


	  tmp.append(" <h5 class=\"card-title\">3. Mắt</h5>");

	  tmp.append(" <div class=\"row mb-2\">");

		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <p style=\"text-align: center; font-weight: 600;\">Nhìn xa từng mắt</p>");
		  tmp.append(" <div class=\"row mb-3\">");
			tmp.append(" <label for=\"about\" class=\"col-md-4 col-lg-4 col-form-label label\" style=\"font-size: 16px;\">Không kính</label>");
			tmp.append(" <div class=\"col-md-4 col-lg-4\">");
			  tmp.append(" <input name=\"mat_tung_mat_khong_kinh_mat_trai\" class=\"form-control\" id=\"mat_tung_mat_khong_kinh_mat_trai\" placeholder=\"Mắt trái\">");
			tmp.append(" </div>");
			tmp.append(" <div class=\"col-md-4 col-lg-4\">");
			  tmp.append(" <input name=\"mat_tung_mat_khong_kinh_mat_phai\" class=\"form-control\" id=\"mat_tung_mat_khong_kinh_mat_phai\" placeholder=\"Mắt phải\">");
			tmp.append(" </div>");
		  tmp.append(" </div>");
		  tmp.append(" <div class=\"row mb-2\">");
			tmp.append(" <label for=\"about\" class=\"col-md-4 col-lg-4 col-form-label label\" style=\"font-size: 16px;\">Có kính</label>");
			tmp.append(" <div class=\"col-md-4 col-lg-4\">");
			  tmp.append(" <input name=\"mat_tung_mat_co_kinh_mat_trai\" class=\"form-control\" id=\"mat_tung_mat_co_kinh_mat_trai\" placeholder=\"Mắt trái\">");
			tmp.append(" </div>");
			tmp.append(" <div class=\"col-md-4 col-lg-4\">");
			  tmp.append(" <input name=\"mat_tung_mat_co_kinh_mat_phai\" class=\"form-control\" id=\"mat_tung_mat_co_kinh_mat_phai\" placeholder=\"Mắt phải\">");
			tmp.append(" </div>");
		  tmp.append(" </div>");
		tmp.append(" </div>");

		tmp.append(" <div class=\"col-md-2 col-lg-2\">");
		  tmp.append(" <p style=\"text-align: center;font-weight: 600;\">Nhìn xa hai mắt</p>");
		  tmp.append(" <div class=\"row mb-3\">");
			tmp.append(" <input name=\"mat_hai_mat_khong_kinh\" class=\"form-control offset-lg-1 col-md-10 col-lg-10 mb-3\" id=\"mat_hai_mat_khong_kinh\"");
			  tmp.append(" placeholder=\"Không kính\">");
			tmp.append(" <input name=\"mat_hai_mat_co_kinh\" class=\"form-control offset-lg-1 col-md-10 col-lg-10 \" id=\"mat_hai_mat_co_kinh\"");
			  tmp.append(" placeholder=\"Có kính\">");
		  tmp.append(" </div>");

		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <p style=\"text-align: center;font-weight: 600;\">Thị trường</p>");
		  tmp.append(" <div class=\"mb-3\">");
			tmp.append(" <label for=\"mat_thi_truong_ngang\" class=\"offset-lg-1 col-md-10 col-lg-10 col-form-label label\"");
			  tmp.append(" style=\"font-size: 16px;\">Thị trường ngang hạn chế</label>");
			tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"mat_thi_truong_ngang\" name=\"mat_thi_truong_ngang\">");
		  tmp.append(" </div>");
		  tmp.append(" <div>");
			tmp.append(" <label for=\"mat_thi_truong_dung\" class=\"offset-lg-1 col-md-10 col-lg-10 col-form-label label\"");
			  tmp.append(" style=\"font-size: 16px;\">Thị trường đứng hạn chế</label>");
			tmp.append(" <input class=\"form-check-input col-md-1 col-lg-1 \" type=\"checkbox\" id=\"mat_thi_truong_dung\" name=\"mat_thi_truong_dung\">");
		  tmp.append(" </div>");
		tmp.append(" </div>");

	  tmp.append(" </div>");

	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"about1\" class=\"col-md-2 col-lg-2\" style=\"font-size: 16px;\">Sắc giác</label>");
		tmp.append(" <div class=\"col-md-2 col-lg-2\">");
		  tmp.append(" <label for=\"mat_sac_giac_binh_thuong\" class=\"col-md-10 col-lg-10\">Bình thường</label>");
		  tmp.append(" <input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\" id=\"mat_sac_giac_binh_thuong\" name=\"mat_sac_giac_binh_thuong\">");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-2 col-lg-2\">");
		  tmp.append(" <label for=\"mat_sac_giac_mu_mau_toan_bo\" class=\"col-md-10 col-lg-10\">Mù màu toàn bộ</label>");
		  tmp.append(" <input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\" id=\"mat_sac_giac_mu_mau_toan_bo\" name=\"mat_sac_giac_mu_mau_toan_bo\">");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-2 col-lg-2\">");
		  tmp.append(" <label for=\"mat_sac_giac_mu_mau_do\" class=\"col-md-10 col-lg-10\">Mù màu đỏ</label>");
		  tmp.append(" <input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\" id=\"mat_sac_giac_mu_mau_do\" name=\"mat_sac_giac_mu_mau_do\">");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-2 col-lg-2\">");
		  tmp.append(" <label for=\"mat_sac_giac_mu_mau_xanh_la\" class=\"col-md-10 col-lg-10\">Mù màu xanh lá </label>");
		  tmp.append(" <input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\" id=\"mat_sac_giac_mu_mau_xanh_la\" name=\"mat_sac_giac_mu_mau_xanh_la\">");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-2 col-lg-2\">");
		  tmp.append(" <label for=\"mat_sac_giac_mu_mau_vang\" class=\"col-md-10 col-lg-10\">Mù màu vàng</label>");
		  tmp.append(" <input class=\"col-md-1 col-lg-1 form-check-input\" type=\"checkbox\" id=\"mat_sac_giac_mu_mau_vang\" name=\"mat_sac_giac_mu_mau_vang\">");
		tmp.append(" </div>");

	  tmp.append(" </div>");

	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"mat_ten_benh\" class=\"form-control\" id=\"mat_ten_benh\" placeholder=\"Các bệnh về mắt\"");
			tmp.append(" rows=\"1\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <textarea name=\"mat_ket_luan\" class=\"form-control\" id=\"mat_ket_luan\" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <select class=\"form-select\" name=\"mat_bac_si\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
			
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <h5 class=\"card-title\">4. Tai mũi họng</h5>");
	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <div class=\"row\">");
			tmp.append(" <label for=\"about\" class=\"col-md-4 col-lg-4 col-form-label label\" style=\"font-size: 16px;\">Tai");
			  tmp.append(" trái</label>");
			tmp.append(" <div class=\"col-md-4 col-lg-4\">");
			  tmp.append(" <input name=\"tai_mui_hong_tai_trai_noi_thuong\" class=\"form-control\" id=\"tai_mui_hong_tai_trai_noi_thuong\" placeholder=\"Nói thường\">");
			tmp.append(" </div>");
			tmp.append(" <div class=\"col-md-4 col-lg-4\">");
			  tmp.append(" <input name=\"tai_mui_hong_tai_trai_noi_tham\" class=\"form-control\" id=\"tai_mui_hong_tai_trai_noi_tham\" placeholder=\"Nói thầm\">");
			tmp.append(" </div>");
		  tmp.append(" </div>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <div class=\"row\">");
			tmp.append(" <label for=\"about\" class=\"col-md-4 col-lg-4 col-form-label label\" style=\"font-size: 16px;\">Tai");
			  tmp.append(" phải</label>");
			tmp.append(" <div class=\"col-md-4 col-lg-4\">");
			  tmp.append(" <input name=\"tai_mui_hong_tai_phai_noi_thuong\" class=\"form-control\" id=\"tai_mui_hong_tai_phai_noi_thuong\" placeholder=\"Nói thường\">");
			tmp.append(" </div>");
			tmp.append(" <div class=\"col-md-4 col-lg-4\">");
			  tmp.append(" <input name=\"tai_mui_hong_tai_phai_noi_tham\" class=\"form-control\" id=\"tai_mui_hong_tai_phai_noi_tham\" placeholder=\"Nói thầm\">");
			tmp.append(" </div>");
		  tmp.append(" </div>");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"tai_mui_hong_ten_benh\" class=\"form-control\" id=\"tai_mui_hong_ten_benh\" placeholder=\"Các bệnh về tai mũi họng\"");
			tmp.append(" rows=\"1\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <textarea name=\"tai_mui_hong_ket_luan\" class=\"form-control\" id=\"tai_mui_hong_ket_luan\" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <select class=\"form-select\" name=\"tai_mui_hong_bac_si\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
			
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	tmp.append("   ");

	  tmp.append(" <h5 class=\"card-title\">5. Tim mạch</h5>");
	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"tim_mach_mach\" class=\"col-md-3 col-lg-2 col-form-label label\"  style=\"font-size: 16px;\">Nhịp tim</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <input name=\"tim_mach_mach\" class=\"form-control\" id=\"tim_mach_mach\">");
		tmp.append(" </div>");

		tmp.append(" <label for=\"tim_mach_huyet_ap\" class=\"col-md-3 col-lg-2 col-form-label label\"  style=\"font-size: 16px;\">Huyết áp</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <input name=\"tim_mach_huyet_ap\" class=\"form-control\" id=\"tim_mach_huyet_ap\">");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"tim_mach_ten_benh\" class=\"form-control\" id=\"tim_mach_ten_benh\" placeholder=\"Các bệnh về tim\"");
			tmp.append(" rows=\"1\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <textarea name=\"tim_mach_ket_luan\" class=\"form-control\" id=\"tim_mach_ket_luan\" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <select class=\"form-select\" name=\"tim_mach_bac_si\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
			
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");


	  tmp.append(" <h5 class=\"card-title\">6. Hô hấp</h5>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"ho_hap_noi_dung_kham\" class=\"form-control\" id=\"ho_hap_noi_dung_kham\" placeholder=\"Các bệnh về hô hấp\"");
			tmp.append(" rows=\"1\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <textarea name=\"ho_hap_ket_luan\" class=\"form-control\" id=\"ho_hap_ket_luan\" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <select class=\"form-select\" name=\"ho_hap_bac_si\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
			
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <h5 class=\"card-title\">7. Cơ xương khớp</h5>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"co_xuong_khop_noi_dung_kham\" class=\"form-control\" id=\"co_xuong_khop_noi_dung_kham\" placeholder=\"Các bệnh về cơ xương khớp\"");
			tmp.append(" rows=\"1\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <textarea name=\"co_xuong_khop_ket_luan\" class=\"form-control\" id=\"co_xuong_khop_ket_luan\" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <select class=\"form-select\" name=\"co_xuong_khop_bac_si\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
			
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <h5 class=\"card-title\">8. Nội tiết</h5>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"noi_tiet_noi_dung_kham\" class=\"form-control\" id=\"noi_tiet_noi_dung_kham\" placeholder=\"Các bệnh về nội tiết\"");
			tmp.append(" rows=\"1\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <textarea name=\"noi_tiet_ket_luan\" class=\"form-control\" id=\"noi_tiet_ket_luan\" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <select class=\"form-select\" name=\"noi_tiet_bac_si\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
			
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <h5 class=\"card-title\">9. Thai sản</h5>");
	  tmp.append(" <div class=\"row mb-2\">");
		tmp.append(" <div class=\"col-md-6 col-lg-6\">");
		  tmp.append(" <textarea name=\"thai_san_noi_dung_kham\" class=\"form-control\" id=\"thai_san_noi_dung_kham\" placeholder=\"Các bệnh về thai sản\"");
			tmp.append(" rows=\"1\"></textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <textarea name=\"thai_san_ket_luan\" class=\"form-control\" id=\"thai_san_ket_luan\" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");
		tmp.append(" <div class=\"col-md-3 col-lg-3\">");
		  tmp.append(" <select class=\"form-select\" name=\"thai_san_bac_si\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
			
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	tmp.append(" </div>");

	tmp.append(" <div class=\"tab-pane fade pt-2\" id=\"subclinical-examination\">");
	  tmp.append(" <h5 class=\"card-title\">1. Các xét nghiệm bắt buộc</h5>");

	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"xet_nghiem_morphin_heroin\" class=\"col-md-3 col-lg-2 col-form-label label\">Morphin/Heroin</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <select class=\"form-select\" name=\"xet_nghiem_morphin_heroin\">");
			tmp.append(" <option value=\"0\" selected>Âm tính</option>");
			tmp.append(" <option value=\"1\">Dương tính</option>");
		  tmp.append(" </select>");
		tmp.append(" </div>");

		tmp.append(" <label for=\"xet_nghiem_amphetamin\" class=\"col-md-3 col-lg-2 col-form-label label\">Amphetamin</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <select class=\"form-select\" name=\"xet_nghiem_amphetamin\">");
			tmp.append(" <option value=\"0\" selected>Âm tính</option>");
			tmp.append(" <option value=\"1\">Dương tính</option>");
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"xet_nghiem_methamphetamin\" class=\"col-md-3 col-lg-2 col-form-label label\">Methamphetamin</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <select class=\"form-select\" name=\"xet_nghiem_methamphetamin\">");
			tmp.append(" <option value=\"0\" selected>Âm tính</option>");
			tmp.append(" <option value=\"1\">Dương tính</option>");
		  tmp.append(" </select>");
		tmp.append(" </div>");

		tmp.append(" <label for=\"xet_nghiem_marijuana\" class=\"col-md-3 col-lg-2 col-form-label label\">Marijuana (cần sa)</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <select class=\"form-select\" name=\"xet_nghiem_marijuana\">");
			tmp.append(" <option value=\"0\" selected>Âm tính</option>");
			tmp.append(" <option value=\"1\">Dương tính</option>");
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"xet_nghiem_nong_do_con\" class=\"col-md-3 col-lg-2 col-form-label label\">Độ cồn</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <input name=\"xet_nghiem_nong_do_con\" class=\"form-control\" id=\"xet_nghiem_nong_do_con\" value=\"0,0 mg/1 lít \">");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <h5 class=\"card-title\">2. Các xét nghiệm khác</h5>");
	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"xet_nghiem_khac_ket_qua\" class=\"col-md-3 col-lg-2 col-form-label label\">Kết quả</label>");
		tmp.append(" <div class=\"col-md-9 col-lg-10\">");
		  tmp.append(" <textarea name=\"xet_nghiem_khac_ket_qua\" class=\"form-control\" id=\"xet_nghiem_khac_ket_qua\"></textarea>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"xet_nghiem_khac_ket_luan\" class=\"col-md-3 col-lg-2 col-form-label label\">Kết luận</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <textarea name=\"xet_nghiem_khac_ket_luan\" class=\"form-control\" id=\"xet_nghiem_khac_ket_luan\" placeholder=\"Kết luận\"");
		  tmp.append(" rows=\"1\">Bình thường</textarea>");
		tmp.append(" </div>");

		tmp.append(" <label for=\"xet_nghiem_bac_si\" class=\"col-md-3 col-lg-2 col-form-label label\">Bác sĩ</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <select class=\"form-select\" name=\"xet_nghiem_bac_si\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");
	tmp.append(" </div>");

	tmp.append(" <div class=\"tab-pane fade pt-2\" id=\"conclusion\">");
	  tmp.append(" <h5 class=\"card-title\">1. Kết luận</h5>");
	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"ket_luan\" class=\"col-md-3 col-lg-2 col-form-label label\">Kết luận</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <textarea name=\"ket_luan\" class=\"form-control\" id=\"ket_luan\" placeholder=\"Kết luận\"");
			tmp.append(" rows=\"1\">Đủ điều kiện sức khỏe</textarea>");
	tmp.append(" 	  ");
		tmp.append(" </div>");

		tmp.append(" <!-- <label for=\"about\" class=\"col-md-3 col-lg-2 col-form-label label\">Bác sĩ</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <select class=\"form-select\">");
			tmp.append(" <option selected>Chọn bác sĩ</option>");
			users.forEach(item->tmp.append(" <option value=\""+item+"\">"+item+"</option>"));
		  tmp.append(" </select>");
		tmp.append(" </div> -->");
	  tmp.append(" </div>");

	  tmp.append(" <h5 class=\"card-title\">2. Kết luận khác</h5>");
	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"ket_luan_suc_khoe\" class=\"col-md-3 col-lg-2 col-form-label label\">Kết luận</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <select class=\"form-select\" name=\"ket_luan_suc_khoe\">");
			tmp.append(" <option value=\"1\" selected>Đủ điều kiện sức khỏe lái xe hạng...</option>");
			tmp.append(" <option value=\"2\">Không đủ điều kiện sức khỏe lái xe hạng...</option>");
			tmp.append(" <option value=\"3\">Đạt tiêu chuẩn sức khỏe lái xe hạng... nhưng yêu cầu khám lại</option>");
		  tmp.append(" </select>");
		tmp.append(" </div>");

		tmp.append(" <label for=\"hang_xe\" class=\"col-md-3 col-lg-2 col-form-label label\">Bằng lái xe</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <select class=\"form-select\" name=\"hang_xe\">");
			tmp.append(" <option selected>Chọn bằng lái</option>");
			tmp.append(" <option value=\"A1\">A1</option>");
			tmp.append(" <option value=\"A2\">A2</option>");
			tmp.append(" <option value=\"B1\">B1</option>");
			tmp.append(" <option value=\"B2\">B2</option>");
			tmp.append(" <option value=\"C\">C</option>");
			tmp.append(" <option value=\"D\">D</option>");
			tmp.append(" <option value=\"E\">E</option>");
			tmp.append(" <option value=\"F\">F</option>");
		  tmp.append(" </select>");
		tmp.append(" </div>");
	  tmp.append(" </div>");

	  tmp.append(" <div class=\"row mb-3\">");
		tmp.append(" <label for=\"ngay_kham_lai\" class=\"col-md-3 col-lg-2 col-form-label label\">Ngày khám lại</label>");
		tmp.append(" <div class=\"col-md-4 col-lg-4\">");
		  tmp.append(" <input name=\"ngay_kham_lai\" type=\"date\" class=\"form-control\" id=\"ngay_kham_lai\">");
		tmp.append(" </div>");

	  tmp.append(" </div>");
	  tmp.append(" <input name=\"benh_nhan_id\" type=\"hidden\" class=\"form-control\" id=\"benh_nhan_id\" value=\""+cus.getCustomer_id()+"\">");
	  tmp.append(" <div class=\"text-center\">");
		tmp.append(" <button type=\"button\" class=\"btn btn-danger\">Hủy</button>");
		tmp.append(" <button type=\"submit\" class=\"btn btn-primary\">Lưu</button>");
	  tmp.append(" </div>");

	tmp.append(" </form>");

	tmp.append(" </div>");

	tmp.append(" </div><!-- End Bordered Tabs -->");
		
		return tmp.toString();
		

	}
	
	private static StringBuilder viewDelGKSK(GKSKObject item) {
		// TODO Auto-generated method stub
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"modal fade\" id=\"delGKSK"+item.getId()+"\" tabindex=\"-1\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"delGKSK"+item.getId()+"\"><i class=\"bi bi-trash3\"></i> Xóa kết quả khám sức khỏe</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		tmp.append("Bạn có chắc chắn xóa kết quả khám sức khỏe này không?<br>");
		tmp.append("Kết quả sẽ không thể phục hồi được nữa.");	
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("<a href=\"/btl/gksk/list?delid="+item.getId()+"\" class=\"btn btn-danger\"><i class=\"bi bi-trash3\"></i> Xóa</a>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		return tmp;
	}
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=1; i<10; i++) {

			list.add("aaaaa"+i);
		}
		
		list.forEach(item->{
			if(item.equalsIgnoreCase("aaaaa5")) {
				System.out.println("ccccccccccc");
			}else {
				System.out.println("bbbbbbbbbbb");
			}
		});
	}
}
 