package jsoft.ads.gksk;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.GKSKObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class GKSKAdd
 */
@WebServlet("/gksk/add")
public class GKSKAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GKSKAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tìm thông tin đăng nhập - Kiểm tra người dùng đã tồn tại hay chưa
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if (user != null) {
			view(request, response, user);
		} else {
			response.sendRedirect("/btl/user/login");
		}
	}

	private void view(HttpServletRequest request, HttpServletResponse response, UserObject user)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		GKSKControl gc = new GKSKControl(cp);
		if (cp == null) {
			getServletContext().setAttribute("CPool", gc.getCP());
		}
		
		GKSKObject similar = new GKSKObject();
		
		String phone = request.getParameter("phoneSearch");
		String cccd = request.getParameter("cccdSearch");
		if(phone != null || cccd !=null) {
			similar.setCo_xuong_khop_bac_si(phone);
			similar.setCo_xuong_khop_ket_luan(cccd);
		}else {
			similar.setCo_xuong_khop_bac_si("");
			similar.setCo_xuong_khop_ket_luan("");
		}
		similar.setCo_xuong_khop_noi_dung_kham("add");
		Triplet<GKSKObject, Short, Byte> infors = new Triplet<>(similar, (short)100, (byte) 1);

		String viewList = gc.viewAddGKSK(infors);

		// Tra ve ket noi ngay sau khi su dung xong
		gc.releaseConnection();

		// Tham chieu tim Header
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=aplist");
		if (header != null) {
			header.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");
		RequestDispatcher error = getServletContext().getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}
		out.append("<div class=\"pagetitle  d-flex\">");
		out.append("<h1>Danh sách kết quả khám sức khỏe</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/btl/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">GKSK</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");
		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		out.append(" <div class=\"profile-overview\">");
		out.append(" <h5 class=\"card-title\">Tìm kiếm bệnh nhân</h5>");
		out.append(" <form action=\"\" method=\"get\">");
		out.append(" <div class=\"row d-flex justify-content-between\">");
		out.append(" <div class=\"col-md-9 d-flex justify-content-between gap-3\">");
		out.append(" <label for=\"phoneSearch\" class=\"col-form-label label\">Số ĐT</label>");
		out.append(" <div class=\"col-md-4 col-lg-4\">");
		out.append(" <input name=\"phoneSearch\" class=\"form-control\" id=\"phoneSearch\" value=\""+((phone!=null)? phone:"")+"\">");
		out.append(" </div>");
		out.append(" <label for=\"cccdSearch\" class=\"col-form-label label\">Số CCCD</label>");
		out.append(" <div class=\"col-md-4 col-lg-4\">");
		out.append(" <input name=\"cccdSearch\" class=\"form-control\" id=\"cccdSearch\" value=\""+((cccd!=null)? cccd:"")+"\">");
		out.append(" </div> ");
		out.append(" </div> ");
		out.append(" <div class=\"col-md-3 d-flex justify-content-end\">");
		out.append(" <button type=\"submit\" class=\"btn btn-primary \"><i class=\"bi bi-search\"></i> Tìm kiếm</button>");
		out.append(" </div>");
		out.append(" </div> ");
		out.append(" </form>");
		out.append(" </div> ");
		
		if(phone != null || cccd !=null) {
			//Hiển thị danh sách
			out.append(viewList);
		}else {
			out.append(" <p class=\"mt-3\">Vui lòng tìm kiếm bệnh nhân</p>");
		}
		
		
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</section>");
		out.append("</main><!-- End #main -->");
		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer != null) {
			footer.include(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		
		
		int benh_nhan_id = jsoft.library.Utilities.getIntParam(request, "benh_nhan_id");
		if(user.getUser_id()>0 && user.getUser_permission()>1) {
			if (benh_nhan_id>0) {
				boolean tien_su_gia_dinh = jsoft.library.Utilities.getBooleanParam(request, "tien_su_gia_dinh");
				System.out.println(tien_su_gia_dinh);
				String tien_su_gia_dinh_ten_benh = request.getParameter("tien_su_gia_dinh_ten_benh");
				boolean co_benh_trong_5_nam_qua = jsoft.library.Utilities.getBooleanParam(request, "co_benh_trong_5_nam_qua");
				boolean dai_thao_duong = jsoft.library.Utilities.getBooleanParam(request, "dai_thao_duong");
				boolean co_benh_than_kinh = jsoft.library.Utilities.getBooleanParam(request, "co_benh_than_kinh");
				boolean benh_tam_than = jsoft.library.Utilities.getBooleanParam(request, "benh_tam_than");
				boolean benh_mat_giam_thi_luc = jsoft.library.Utilities.getBooleanParam(request, "benh_mat_giam_thi_luc");
				boolean mat_y_thuc_roi_loan_y_thuc = jsoft.library.Utilities.getBooleanParam(request, "mat_y_thuc_roi_loan_y_thuc");
				boolean benh_o_tai = jsoft.library.Utilities.getBooleanParam(request, "benh_o_tai");
				boolean ngat_chong_mat = jsoft.library.Utilities.getBooleanParam(request, "ngat_chong_mat");
				boolean benh_o_tim = jsoft.library.Utilities.getBooleanParam(request, "benh_o_tim");
				boolean benh_tieu_hoa = jsoft.library.Utilities.getBooleanParam(request, "benh_tieu_hoa");
				boolean phau_thuat_can_thiep_tim_mach = jsoft.library.Utilities.getBooleanParam(request, "phau_thuat_can_thiep_tim_mach");
				boolean roi_loan_giac_ngu = jsoft.library.Utilities.getBooleanParam(request, "roi_loan_giac_ngu");
				boolean tang_huyet_ap = jsoft.library.Utilities.getBooleanParam(request, "tang_huyet_ap");
				boolean tai_bien_mach_mau_nao = jsoft.library.Utilities.getBooleanParam(request, "tai_bien_mach_mau_nao");
				boolean kho_tho = jsoft.library.Utilities.getBooleanParam(request, "kho_tho");
				boolean benh_hoac_ton_thuong_cot_song = jsoft.library.Utilities.getBooleanParam(request, "benh_hoac_ton_thuong_cot_song");
				boolean benh_phoi_hen = jsoft.library.Utilities.getBooleanParam(request, "benh_phoi_hen");
				boolean su_dung_ruou = jsoft.library.Utilities.getBooleanParam(request, "su_dung_ruou");
				boolean benh_than_loc_mau = jsoft.library.Utilities.getBooleanParam(request, "benh_than_loc_mau");
				boolean su_dung_ma_tuy = jsoft.library.Utilities.getBooleanParam(request, "su_dung_ma_tuy");
				String tien_su_benh_nhan_ten_benh = request.getParameter("tien_su_benh_nhan_ten_benh");
				String dang_dieu_tri_benh_ten_benh = request.getParameter("dang_dieu_tri_benh_ten_benh");
				String mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh = request.getParameter("mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh");

				String tam_than_noi_dung_kham = request.getParameter("tam_than_noi_dung_kham");
				String tam_than_ket_luan = request.getParameter("tam_than_ket_luan");
				String tam_than_bac_si = request.getParameter("tam_than_bac_si");
				String than_kinh_noi_dung_kham = request.getParameter("than_kinh_noi_dung_kham");
				String than_kinh_ket_luan = request.getParameter("than_kinh_ket_luan");
				String than_kinh_bac_si = request.getParameter("than_kinh_bac_si");
				String mat_tung_mat_khong_kinh_mat_trai = request.getParameter("mat_tung_mat_khong_kinh_mat_trai");
				String mat_tung_mat_khong_kinh_mat_phai = request.getParameter("mat_tung_mat_khong_kinh_mat_phai");
				String mat_tung_mat_co_kinh_mat_trai = request.getParameter("mat_tung_mat_co_kinh_mat_trai");
				String mat_tung_mat_co_kinh_mat_phai = request.getParameter("mat_tung_mat_co_kinh_mat_phai");
				String mat_hai_mat_khong_kinh = request.getParameter("mat_hai_mat_khong_kinh");
				String mat_hai_mat_co_kinh = request.getParameter("mat_hai_mat_co_kinh");
				boolean mat_thi_truong_ngang = jsoft.library.Utilities.getBooleanParam(request, "mat_thi_truong_ngang");
				boolean mat_thi_truong_dung = jsoft.library.Utilities.getBooleanParam(request, "mat_thi_truong_dung");
				boolean mat_sac_giac_binh_thuong = jsoft.library.Utilities.getBooleanParam(request, "mat_sac_giac_binh_thuong");
				boolean mat_sac_giac_mu_mau_toan_bo = jsoft.library.Utilities.getBooleanParam(request, "mat_sac_giac_mu_mau_toan_bo");
				boolean mat_sac_giac_mu_mau_do = jsoft.library.Utilities.getBooleanParam(request, "mat_sac_giac_mu_mau_do");
				boolean mat_sac_giac_mu_mau_xanh_la = jsoft.library.Utilities.getBooleanParam(request, "mat_sac_giac_mu_mau_xanh_la");
				boolean mat_sac_giac_mu_mau_vang = jsoft.library.Utilities.getBooleanParam(request, "mat_sac_giac_mu_mau_vang");
				String mat_ten_benh = request.getParameter("mat_ten_benh");
				String mat_ket_luan = request.getParameter("mat_ket_luan");
				String mat_bac_si = request.getParameter("mat_bac_si");
				String tai_mui_hong_tai_trai_noi_thuong = request.getParameter("tai_mui_hong_tai_trai_noi_thuong");
				String tai_mui_hong_tai_trai_noi_tham = request.getParameter("tai_mui_hong_tai_trai_noi_tham");
				String tai_mui_hong_tai_phai_noi_thuong = request.getParameter("tai_mui_hong_tai_phai_noi_thuong");
				String tai_mui_hong_tai_phai_noi_tham = request.getParameter("tai_mui_hong_tai_phai_noi_tham");
				String tai_mui_hong_ten_benh = request.getParameter("tai_mui_hong_ten_benh");
				String tai_mui_hong_ket_luan = request.getParameter("tai_mui_hong_ket_luan");
				String tai_mui_hong_bac_si = request.getParameter("tai_mui_hong_bac_si");
				String tim_mach_mach = request.getParameter("tim_mach_mach");
				String tim_mach_huyet_ap = request.getParameter("tim_mach_huyet_ap");
				String tim_mach_ten_benh = request.getParameter("tim_mach_ten_benh");
				String tim_mach_ket_luan = request.getParameter("tim_mach_ket_luan");
				String tim_mach_bac_si = request.getParameter("tim_mach_bac_si");
				String ho_hap_noi_dung_kham = request.getParameter("ho_hap_noi_dung_kham");
				String ho_hap_ket_luan = request.getParameter("ho_hap_ket_luan");
				String ho_hap_bac_si = request.getParameter("ho_hap_bac_si");
				String co_xuong_khop_noi_dung_kham = request.getParameter("co_xuong_khop_noi_dung_kham");
				String co_xuong_khop_ket_luan = request.getParameter("co_xuong_khop_ket_luan");
				String co_xuong_khop_bac_si = request.getParameter("co_xuong_khop_bac_si");
				String noi_tiet_noi_dung_kham = request.getParameter("noi_tiet_noi_dung_kham");
				String noi_tiet_ket_luan = request.getParameter("noi_tiet_ket_luan");
				String noi_tiet_bac_si = request.getParameter("noi_tiet_bac_si");
				String thai_san_noi_dung_kham = request.getParameter("thai_san_noi_dung_kham");
				String thai_san_ket_luan = request.getParameter("thai_san_ket_luan");
				String thai_san_bac_si = request.getParameter("thai_san_bac_si");
				boolean xet_nghiem_morphin_heroin = jsoft.library.Utilities.getBooleanParam(request, "xet_nghiem_morphin_heroin");
				boolean xet_nghiem_amphetamin = jsoft.library.Utilities.getBooleanParam(request, "xet_nghiem_amphetamin");
				boolean xet_nghiem_methamphetamin = jsoft.library.Utilities.getBooleanParam(request, "xet_nghiem_methamphetamin");
				boolean xet_nghiem_marijuana = jsoft.library.Utilities.getBooleanParam(request, "xet_nghiem_marijuana");
				String xet_nghiem_nong_do_con = request.getParameter("xet_nghiem_nong_do_con");
				String xet_nghiem_khac_ket_qua = request.getParameter("xet_nghiem_khac_ket_qua");
				String xet_nghiem_khac_ket_luan = request.getParameter("xet_nghiem_khac_ket_luan");
				String xet_nghiem_bac_si = request.getParameter("xet_nghiem_bac_si");
				String ket_luan = request.getParameter("ket_luan");
				String hang_xe = request.getParameter("hang_xe");
				String ngay_kham_lai = request.getParameter("ngay_kham_lai");
				int ket_luan_suc_khoe = jsoft.library.Utilities.getIntParam(request, "ket_luan_suc_khoe");
				String ngay_kham = Utilities_date.getDate();
				
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
				GKSKControl uc = new GKSKControl(cp);
	
				if(cp == null) {
					getServletContext().setAttribute("CPool", uc.getCP());
				}
				
				GKSKObject item = new GKSKObject();
				
				item.setTien_su_gia_dinh(tien_su_gia_dinh);
				item.setTien_su_gia_dinh_ten_benh(tien_su_gia_dinh_ten_benh);
				item.setCo_benh_trong_5_nam_qua(co_benh_trong_5_nam_qua);
				item.setDai_thao_duong(dai_thao_duong);
				item.setCo_benh_than_kinh(co_benh_than_kinh);
				item.setBenh_tam_than(benh_tam_than);
				item.setMat_y_thuc_roi_loan_y_thuc(mat_y_thuc_roi_loan_y_thuc);
				item.setBenh_o_tai(benh_o_tai);
				item.setNgat_chong_mat(ngat_chong_mat);
				item.setBenh_o_tim(benh_o_tim);
				item.setBenh_tieu_hoa(benh_tieu_hoa);
				item.setPhau_thuat_can_thiep_tim_mach(phau_thuat_can_thiep_tim_mach);
				item.setRoi_loan_giac_ngu(roi_loan_giac_ngu);
				item.setTang_huyet_ap(tang_huyet_ap);
				item.setTai_bien_mach_mau_nao(tai_bien_mach_mau_nao);
				item.setKho_tho(kho_tho);
				item.setBenh_hoac_ton_thuong_cot_song(benh_hoac_ton_thuong_cot_song);
				item.setBenh_phoi_hen(benh_phoi_hen);
				item.setSu_dung_ruou(su_dung_ruou);
				item.setBenh_than_loc_mau(benh_than_loc_mau);
				item.setSu_dung_ma_tuy(su_dung_ma_tuy);
				item.setTien_su_benh_nhan_ten_benh(tien_su_benh_nhan_ten_benh);
				item.setDang_dieu_tri_benh_ten_benh(dang_dieu_tri_benh_ten_benh);
				item.setMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh(mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh);
				item.setTam_than_noi_dung_kham(tam_than_noi_dung_kham);
				item.setTam_than_ket_luan(tam_than_ket_luan);
				item.setTam_than_bac_si(tam_than_bac_si);
				item.setThan_kinh_noi_dung_kham(than_kinh_noi_dung_kham);
				item.setThan_kinh_ket_luan(than_kinh_ket_luan);
				item.setThan_kinh_bac_si(than_kinh_bac_si);
				item.setMat_tung_mat_khong_kinh_mat_trai(mat_tung_mat_khong_kinh_mat_trai);
				item.setMat_tung_mat_khong_kinh_mat_phai(mat_tung_mat_khong_kinh_mat_phai);
				item.setMat_tung_mat_co_kinh_mat_trai(mat_tung_mat_co_kinh_mat_trai);
				item.setMat_tung_mat_co_kinh_mat_phai(mat_tung_mat_co_kinh_mat_phai);
				item.setMat_hai_mat_khong_kinh(mat_hai_mat_khong_kinh);
				item.setMat_hai_mat_co_kinh(mat_hai_mat_co_kinh);
				item.setMat_thi_truong_ngang(mat_thi_truong_ngang);
				item.setMat_thi_truong_dung(mat_thi_truong_dung);
				item.setMat_sac_giac_binh_thuong(mat_sac_giac_binh_thuong);
				item.setMat_sac_giac_mu_mau_toan_bo(mat_sac_giac_mu_mau_toan_bo);
				item.setMat_sac_giac_mu_mau_do(mat_sac_giac_mu_mau_do);
				item.setMat_sac_giac_mu_mau_xanh_la(mat_sac_giac_mu_mau_xanh_la);
				item.setMat_sac_giac_mu_mau_vang(mat_sac_giac_mu_mau_vang);
				item.setMat_ten_benh(mat_ten_benh);
				item.setMat_ket_luan(mat_ket_luan);
				item.setMat_bac_si(mat_bac_si);
				item.setTai_mui_hong_tai_trai_noi_thuong(tai_mui_hong_tai_trai_noi_thuong);
				item.setTai_mui_hong_tai_trai_noi_tham(tai_mui_hong_tai_trai_noi_tham);
				item.setTai_mui_hong_tai_phai_noi_thuong(tai_mui_hong_tai_phai_noi_thuong);
				item.setTai_mui_hong_tai_phai_noi_tham(tai_mui_hong_tai_phai_noi_tham);
				item.setTai_mui_hong_ten_benh(tai_mui_hong_ten_benh);
				item.setTai_mui_hong_ket_luan(tai_mui_hong_ket_luan);
				item.setTai_mui_hong_bac_si(tai_mui_hong_bac_si);
				item.setTim_mach_mach(tim_mach_mach);
				item.setTim_mach_huyet_ap(tim_mach_huyet_ap);
				item.setTim_mach_ten_benh(tim_mach_ten_benh);
				item.setTim_mach_ket_luan(tim_mach_ket_luan);
				item.setTim_mach_bac_si(tim_mach_bac_si);
				item.setHo_hap_noi_dung_kham(ho_hap_noi_dung_kham);
				item.setHo_hap_ket_luan(ho_hap_ket_luan);
				item.setHo_hap_bac_si(ho_hap_bac_si);
				item.setCo_xuong_khop_noi_dung_kham(co_xuong_khop_noi_dung_kham);
				item.setCo_xuong_khop_ket_luan(co_xuong_khop_ket_luan);
				item.setCo_xuong_khop_bac_si(co_xuong_khop_bac_si);
				item.setNoi_tiet_noi_dung_kham(noi_tiet_noi_dung_kham);
				item.setNoi_tiet_ket_luan(noi_tiet_ket_luan);
				item.setNoi_tiet_bac_si(noi_tiet_bac_si);
				item.setThai_san_noi_dung_kham(thai_san_noi_dung_kham);
				item.setThai_san_ket_luan(thai_san_ket_luan);
				item.setThai_san_bac_si(thai_san_bac_si);
				item.setXet_nghiem_morphin_heroin(xet_nghiem_morphin_heroin);
				item.setXet_nghiem_amphetamin(xet_nghiem_amphetamin);
				item.setXet_nghiem_methamphetamin(xet_nghiem_methamphetamin);
				item.setXet_nghiem_marijuana(xet_nghiem_marijuana);
				item.setXet_nghiem_nong_do_con(xet_nghiem_nong_do_con);
				item.setXet_nghiem_khac_ket_qua(xet_nghiem_khac_ket_qua);
				item.setXet_nghiem_khac_ket_luan(xet_nghiem_khac_ket_luan);
				item.setXet_nghiem_bac_si(xet_nghiem_bac_si);
				item.setKet_luan(ket_luan);
				item.setHang_xe(hang_xe);
				item.setNgay_kham_lai(ngay_kham_lai);
				item.setBenh_nhan_id(benh_nhan_id);
				item.setNgay_kham(ngay_kham);
				item.setKet_luan_suc_khoe(ket_luan_suc_khoe);
				item.setBenh_mat_giam_thi_luc(benh_mat_giam_thi_luc);

				
				boolean result = uc.addGKSK(item);

				// Trả về kết nối
				uc.releaseConnection();

				if (result) {
//					//Lưu lịch sử làm việc
//					LogControl lc =new LogControl(cp);
//					if(lc==null) {
//						getServletContext().setAttribute("CPool", lc.getCP());
//					}
//					LogObject lo = new LogObject();
//					lo.setLog_action((short)1);
//					lo.setLog_date(Utilities_date.getDateProfiles());
//					lo.setLog_name(Utilities.encode(fullname1));
//					lo.setLog_position((short)1);
//					lo.setLog_user_name(user.getUser_name());
//					lo.setLog_user_permission(user.getUser_permission());
//					boolean checkLog = lc.addLog(lo);
//					lc.releaseConnection();
//					if(checkLog) {
//						response.sendRedirect("/btl/user/list");
//					}else {
//						response.sendRedirect("/btl/user/list?err=loadd");
//					}
					response.sendRedirect("/btl/gksk/list");
				} else {
					response.sendRedirect("/btl/gksk/list?err=uradd");
				}
			}else {
				response.sendRedirect("/btl/gksk/list?err=noidcus");
			}
		}else {
			response.sendRedirect("/btl/user/login?err=urnopremis");
		}
	}

}
