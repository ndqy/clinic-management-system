package jsoft.ads.gksk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.UIClientPropertyKey;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.library.Utilities;
import jsoft.objects.CustomerObject;
import jsoft.objects.GKSKObject;

public class GKSKModel {

	private GKSK gk;
	
	public GKSKModel(ConnectionPool cp) {
		this.gk = new GKSKImpl(cp);
	}
	protected void finalize() throws Throwable{
		this.gk=null;
	} 
	public ConnectionPool getCP() {
		return this.gk.getCP();
	}
	public void releaseConnection() {
		this.gk.releaseConnection();
	}
	//============================================================
	public boolean addGKSK(GKSKObject item) {
		return this.gk.addGKSK(item);
	}
	public boolean editGKSK(GKSKObject item) {
		return this.gk.editGKSK(item);
	}
	public boolean delGKSK(GKSKObject item) {
		return this.gk.delGKSK(item);
	}
	//============================================================

	
	public GKSKObject getGKSK(int id) {
		GKSKObject item = null;
		ResultSet rs = this.gk.getGKSK(id);
		if(rs!=null) {
			try {
				if(rs.next()) {
					item = new GKSKObject();
					item.setId(rs.getInt("id"));
					item.setTien_su_gia_dinh(rs.getBoolean("tien_su_gia_dinh"));
					item.setTien_su_gia_dinh_ten_benh(Utilities.decode(rs.getString("tien_su_gia_dinh_ten_benh")));
					item.setCo_benh_trong_5_nam_qua(rs.getBoolean("co_benh_trong_5_nam_qua"));
					item.setDai_thao_duong(rs.getBoolean("dai_thao_duong"));
					item.setCo_benh_than_kinh(rs.getBoolean("co_benh_than_kinh"));
					item.setBenh_tam_than(rs.getBoolean("benh_tam_than"));
					item.setBenh_mat_giam_thi_luc(rs.getBoolean("benh_mat_giam_thi_luc"));
					item.setMat_y_thuc_roi_loan_y_thuc(rs.getBoolean("mat_y_thuc_roi_loan_y_thuc"));
					item.setBenh_o_tai(rs.getBoolean("benh_o_tai"));
					item.setNgat_chong_mat(rs.getBoolean("ngat_chong_mat"));
					item.setBenh_o_tim(rs.getBoolean("benh_o_tim"));
					item.setBenh_tieu_hoa(rs.getBoolean("benh_tieu_hoa"));
					item.setPhau_thuat_can_thiep_tim_mach(rs.getBoolean("phau_thuat_can_thiep_tim_mach"));
					item.setRoi_loan_giac_ngu(rs.getBoolean("roi_loan_giac_ngu"));
					item.setTang_huyet_ap(rs.getBoolean("tang_huyet_ap"));
					item.setTai_bien_mach_mau_nao(rs.getBoolean("tai_bien_mach_mau_nao"));
					item.setKho_tho(rs.getBoolean("kho_tho"));
					item.setBenh_hoac_ton_thuong_cot_song(rs.getBoolean("benh_hoac_ton_thuong_cot_song"));
					item.setBenh_phoi_hen(rs.getBoolean("benh_phoi_hen"));
					item.setSu_dung_ruou(rs.getBoolean("su_dung_ruou"));
					item.setBenh_than_loc_mau(rs.getBoolean("benh_than_loc_mau"));
					item.setSu_dung_ma_tuy(rs.getBoolean("su_dung_ma_tuy"));
					item.setTien_su_benh_nhan_ten_benh(Utilities.decode(rs.getString("tien_su_benh_nhan_ten_benh")));
					item.setDang_dieu_tri_benh_ten_benh(Utilities.decode(rs.getString("dang_dieu_tri_benh_ten_benh")));
					item.setMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh(Utilities.decode(rs.getString("mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh")));

					item.setTam_than_noi_dung_kham(Utilities.decode(rs.getString("tam_than_noi_dung_kham")));
					item.setTam_than_ket_luan(Utilities.decode(rs.getString("tam_than_ket_luan")));
					item.setTam_than_bac_si(Utilities.decode(rs.getString("tam_than_bac_si")));
					item.setThan_kinh_noi_dung_kham(Utilities.decode(rs.getString("than_kinh_noi_dung_kham")));
					item.setThan_kinh_ket_luan(Utilities.decode(rs.getString("than_kinh_ket_luan")));
					item.setThan_kinh_bac_si(Utilities.decode(rs.getString("than_kinh_bac_si")));
					item.setMat_tung_mat_khong_kinh_mat_trai(rs.getString("mat_tung_mat_khong_kinh_mat_trai"));
					item.setMat_tung_mat_khong_kinh_mat_phai(rs.getString("mat_tung_mat_khong_kinh_mat_phai"));
					item.setMat_tung_mat_co_kinh_mat_trai(rs.getString("mat_tung_mat_co_kinh_mat_trai"));
					item.setMat_tung_mat_co_kinh_mat_phai(rs.getString("mat_tung_mat_co_kinh_mat_phai"));
					item.setMat_hai_mat_khong_kinh(rs.getString("mat_hai_mat_khong_kinh"));
					item.setMat_hai_mat_co_kinh(rs.getString("mat_hai_mat_co_kinh"));
					item.setMat_thi_truong_ngang(rs.getBoolean("mat_thi_truong_ngang"));
					item.setMat_thi_truong_dung(rs.getBoolean("mat_thi_truong_dung"));
					item.setMat_sac_giac_binh_thuong(rs.getBoolean("mat_sac_giac_binh_thuong"));
					item.setMat_sac_giac_mu_mau_toan_bo(rs.getBoolean("mat_sac_giac_mu_mau_toan_bo"));
					item.setMat_sac_giac_mu_mau_do(rs.getBoolean("mat_sac_giac_mu_mau_do"));
					item.setMat_sac_giac_mu_mau_xanh_la(rs.getBoolean("mat_sac_giac_mu_mau_xanh_la"));
					item.setMat_sac_giac_mu_mau_vang(rs.getBoolean("mat_sac_giac_mu_mau_vang"));
					item.setMat_ten_benh(Utilities.decode(rs.getString("mat_ten_benh")));
					item.setMat_ket_luan(Utilities.decode(rs.getString("mat_ket_luan")));
					item.setMat_bac_si(Utilities.decode(rs.getString("mat_bac_si")));
					item.setTai_mui_hong_tai_trai_noi_thuong(rs.getString("tai_mui_hong_tai_trai_noi_thuong"));
					item.setTai_mui_hong_tai_trai_noi_tham(rs.getString("tai_mui_hong_tai_trai_noi_tham"));
					item.setTai_mui_hong_tai_phai_noi_thuong(rs.getString("tai_mui_hong_tai_phai_noi_thuong"));
					item.setTai_mui_hong_tai_phai_noi_tham(rs.getString("tai_mui_hong_tai_phai_noi_tham"));
					item.setTai_mui_hong_ten_benh(rs.getString("tai_mui_hong_ten_benh"));
					item.setTai_mui_hong_ket_luan(Utilities.decode(rs.getString("tai_mui_hong_ket_luan")));
					item.setTai_mui_hong_bac_si(Utilities.decode(rs.getString("tai_mui_hong_bac_si")));
					item.setTim_mach_mach(Utilities.decode(rs.getString("tim_mach_mach")));
					item.setTim_mach_huyet_ap(Utilities.decode(rs.getString("tim_mach_huyet_ap")));
					item.setTim_mach_ten_benh(Utilities.decode(rs.getString("tim_mach_ten_benh")));
					item.setTim_mach_ket_luan(Utilities.decode(rs.getString("tim_mach_ket_luan")));
					item.setTim_mach_bac_si(Utilities.decode(rs.getString("tim_mach_bac_si")));
					item.setHo_hap_noi_dung_kham(Utilities.decode(rs.getString("ho_hap_noi_dung_kham")));
					item.setHo_hap_ket_luan(Utilities.decode(rs.getString("ho_hap_ket_luan")));
					item.setHo_hap_bac_si(Utilities.decode(rs.getString("ho_hap_bac_si")));
					item.setCo_xuong_khop_noi_dung_kham(Utilities.decode(rs.getString("co_xuong_khop_noi_dung_kham")));
					item.setCo_xuong_khop_ket_luan(Utilities.decode(rs.getString("co_xuong_khop_ket_luan")));
					item.setCo_xuong_khop_bac_si(Utilities.decode(rs.getString("co_xuong_khop_bac_si")));
					item.setNoi_tiet_noi_dung_kham(Utilities.decode(rs.getString("noi_tiet_noi_dung_kham")));
					item.setNoi_tiet_ket_luan(Utilities.decode(rs.getString("noi_tiet_ket_luan")));
					item.setNoi_tiet_bac_si(Utilities.decode(rs.getString("noi_tiet_bac_si")));
					item.setThai_san_noi_dung_kham(Utilities.decode(rs.getString("thai_san_noi_dung_kham")));
					item.setThai_san_ket_luan(Utilities.decode(rs.getString("thai_san_ket_luan")));
					item.setThai_san_bac_si(Utilities.decode(rs.getString("thai_san_bac_si")));
					item.setXet_nghiem_morphin_heroin(rs.getBoolean("xet_nghiem_morphin_heroin"));
					item.setXet_nghiem_amphetamin(rs.getBoolean("xet_nghiem_amphetamin"));
					item.setXet_nghiem_methamphetamin(rs.getBoolean("xet_nghiem_methamphetamin"));
					item.setXet_nghiem_marijuana(rs.getBoolean("xet_nghiem_marijuana"));
					item.setXet_nghiem_nong_do_con(rs.getString("xet_nghiem_nong_do_con"));
					item.setXet_nghiem_khac_ket_qua(Utilities.decode(rs.getString("xet_nghiem_khac_ket_qua")));
					item.setXet_nghiem_khac_ket_luan(Utilities.decode(rs.getString("xet_nghiem_khac_ket_luan")));
					item.setXet_nghiem_bac_si(Utilities.decode(rs.getString("xet_nghiem_bac_si")));
					item.setKet_luan(Utilities.decode(rs.getString("ket_luan")));
					item.setHang_xe(rs.getString("hang_xe"));
					item.setKet_luan_suc_khoe(rs.getInt("ket_luan_suc_khoe"));;
					item.setNgay_kham_lai(rs.getString("ngay_kham_lai"));
					item.setBenh_nhan_id(rs.getInt("benh_nhan_id"));
					item.setNgay_kham(rs.getString("ngay_kham"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	
	public Quartet<
				ArrayList<GKSKObject>, 
				Integer, 
				HashMap<Integer, CustomerObject>, 
				ArrayList<String> //Danh sách tên bác sĩ
				>  getGKSKs(Triplet<GKSKObject, Short, Byte> infors){
		ArrayList<GKSKObject> items = new ArrayList<GKSKObject>();
		
		GKSKObject item = null;
		ArrayList<ResultSet> res = this.gk.getGKSKs(infors);
		ResultSet rs = res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
					item = new GKSKObject();
					item.setId(rs.getInt("id"));
					item.setTien_su_gia_dinh(rs.getBoolean("tien_su_gia_dinh"));
					item.setTien_su_gia_dinh_ten_benh(rs.getString("tien_su_gia_dinh_ten_benh"));
					item.setCo_benh_trong_5_nam_qua(rs.getBoolean("co_benh_trong_5_nam_qua"));
					item.setDai_thao_duong(rs.getBoolean("dai_thao_duong"));
					item.setCo_benh_than_kinh(rs.getBoolean("co_benh_than_kinh"));
					item.setBenh_tam_than(rs.getBoolean("benh_tam_than"));
					item.setBenh_mat_giam_thi_luc(rs.getBoolean("benh_mat_giam_thi_luc"));
					item.setMat_y_thuc_roi_loan_y_thuc(rs.getBoolean("mat_y_thuc_roi_loan_y_thuc"));
					item.setBenh_o_tai(rs.getBoolean("benh_o_tai"));
					item.setNgat_chong_mat(rs.getBoolean("ngat_chong_mat"));
					item.setBenh_o_tim(rs.getBoolean("benh_o_tim"));
					item.setBenh_tieu_hoa(rs.getBoolean("benh_tieu_hoa"));
					item.setPhau_thuat_can_thiep_tim_mach(rs.getBoolean("phau_thuat_can_thiep_tim_mach"));
					item.setRoi_loan_giac_ngu(rs.getBoolean("roi_loan_giac_ngu"));
					item.setTang_huyet_ap(rs.getBoolean("tang_huyet_ap"));
					item.setTai_bien_mach_mau_nao(rs.getBoolean("tai_bien_mach_mau_nao"));
					item.setKho_tho(rs.getBoolean("kho_tho"));
					item.setBenh_hoac_ton_thuong_cot_song(rs.getBoolean("benh_hoac_ton_thuong_cot_song"));
					item.setBenh_phoi_hen(rs.getBoolean("benh_phoi_hen"));
					item.setSu_dung_ruou(rs.getBoolean("su_dung_ruou"));
					item.setBenh_than_loc_mau(rs.getBoolean("benh_than_loc_mau"));
					item.setSu_dung_ma_tuy(rs.getBoolean("su_dung_ma_tuy"));
					item.setTien_su_benh_nhan_ten_benh(Utilities.decode(rs.getString("tien_su_benh_nhan_ten_benh")));
					item.setDang_dieu_tri_benh_ten_benh(Utilities.decode(rs.getString("dang_dieu_tri_benh_ten_benh")));
					item.setMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh(Utilities.decode(rs.getString("mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh")));

					item.setTam_than_noi_dung_kham(Utilities.decode(rs.getString("tam_than_noi_dung_kham")));
					item.setTam_than_ket_luan(Utilities.decode(rs.getString("tam_than_ket_luan")));
					item.setTam_than_bac_si(Utilities.decode(rs.getString("tam_than_bac_si")));
					item.setThan_kinh_noi_dung_kham(Utilities.decode(rs.getString("than_kinh_noi_dung_kham")));
					item.setThan_kinh_ket_luan(Utilities.decode(rs.getString("than_kinh_ket_luan")));
					item.setThan_kinh_bac_si(Utilities.decode(rs.getString("than_kinh_bac_si")));
					item.setMat_tung_mat_khong_kinh_mat_trai(rs.getString("mat_tung_mat_khong_kinh_mat_trai"));
					item.setMat_tung_mat_khong_kinh_mat_phai(rs.getString("mat_tung_mat_khong_kinh_mat_phai"));
					item.setMat_tung_mat_co_kinh_mat_trai(rs.getString("mat_tung_mat_co_kinh_mat_trai"));
					item.setMat_tung_mat_co_kinh_mat_phai(rs.getString("mat_tung_mat_co_kinh_mat_phai"));
					item.setMat_hai_mat_khong_kinh(rs.getString("mat_hai_mat_khong_kinh"));
					item.setMat_hai_mat_co_kinh(rs.getString("mat_hai_mat_co_kinh"));
					item.setMat_thi_truong_ngang(rs.getBoolean("mat_thi_truong_ngang"));
					item.setMat_thi_truong_dung(rs.getBoolean("mat_thi_truong_dung"));
					item.setMat_sac_giac_binh_thuong(rs.getBoolean("mat_sac_giac_binh_thuong"));
					item.setMat_sac_giac_mu_mau_toan_bo(rs.getBoolean("mat_sac_giac_mu_mau_toan_bo"));
					item.setMat_sac_giac_mu_mau_do(rs.getBoolean("mat_sac_giac_mu_mau_do"));
					item.setMat_sac_giac_mu_mau_xanh_la(rs.getBoolean("mat_sac_giac_mu_mau_xanh_la"));
					item.setMat_sac_giac_mu_mau_vang(rs.getBoolean("mat_sac_giac_mu_mau_vang"));
					item.setMat_ten_benh(rs.getString("mat_ten_benh"));
					item.setMat_ket_luan(Utilities.decode(rs.getString("mat_ket_luan")));
					item.setMat_bac_si(Utilities.decode(rs.getString("mat_bac_si")));
					item.setTai_mui_hong_tai_trai_noi_thuong(Utilities.decode(rs.getString("tai_mui_hong_tai_trai_noi_thuong")));
					item.setTai_mui_hong_tai_trai_noi_tham(Utilities.decode(rs.getString("tai_mui_hong_tai_trai_noi_tham")));
					item.setTai_mui_hong_tai_phai_noi_thuong(Utilities.decode(rs.getString("tai_mui_hong_tai_phai_noi_thuong")));
					item.setTai_mui_hong_tai_phai_noi_tham(Utilities.decode(rs.getString("tai_mui_hong_tai_phai_noi_tham")));
					item.setTai_mui_hong_ten_benh(Utilities.decode(rs.getString("tai_mui_hong_ten_benh")));
					item.setTai_mui_hong_ket_luan(Utilities.decode(rs.getString("tai_mui_hong_ket_luan")));
					item.setTai_mui_hong_bac_si(Utilities.decode(rs.getString("tai_mui_hong_bac_si")));
					item.setTim_mach_mach(Utilities.decode(rs.getString("tim_mach_mach")));
					item.setTim_mach_huyet_ap(Utilities.decode(rs.getString("tim_mach_huyet_ap")));
					item.setTim_mach_ten_benh(Utilities.decode(rs.getString("tim_mach_ten_benh")));
					item.setTim_mach_ket_luan(Utilities.decode(rs.getString("tim_mach_ket_luan")));
					item.setTim_mach_bac_si(Utilities.decode(rs.getString("tim_mach_bac_si")));
					item.setHo_hap_noi_dung_kham(Utilities.decode(rs.getString("ho_hap_noi_dung_kham")));
					item.setHo_hap_ket_luan(Utilities.decode(rs.getString("ho_hap_ket_luan")));
					item.setHo_hap_bac_si(Utilities.decode(rs.getString("ho_hap_bac_si")));
					item.setCo_xuong_khop_noi_dung_kham(Utilities.decode(rs.getString("co_xuong_khop_noi_dung_kham")));
					item.setCo_xuong_khop_ket_luan(Utilities.decode(rs.getString("co_xuong_khop_ket_luan")));
					item.setCo_xuong_khop_bac_si(Utilities.decode(rs.getString("co_xuong_khop_bac_si")));
					item.setNoi_tiet_noi_dung_kham(Utilities.decode(rs.getString("noi_tiet_noi_dung_kham")));
					item.setNoi_tiet_ket_luan(Utilities.decode(rs.getString("noi_tiet_ket_luan")));
					item.setNoi_tiet_bac_si(Utilities.decode(rs.getString("noi_tiet_bac_si")));
					item.setThai_san_noi_dung_kham(Utilities.decode(rs.getString("thai_san_noi_dung_kham")));
					item.setThai_san_ket_luan(Utilities.decode(rs.getString("thai_san_ket_luan")));
					item.setThai_san_bac_si(Utilities.decode(rs.getString("thai_san_bac_si")));
					item.setXet_nghiem_morphin_heroin(rs.getBoolean("xet_nghiem_morphin_heroin"));
					item.setXet_nghiem_amphetamin(rs.getBoolean("xet_nghiem_amphetamin"));
					item.setXet_nghiem_methamphetamin(rs.getBoolean("xet_nghiem_methamphetamin"));
					item.setXet_nghiem_marijuana(rs.getBoolean("xet_nghiem_marijuana"));
					item.setXet_nghiem_nong_do_con(rs.getString("xet_nghiem_nong_do_con"));
					item.setXet_nghiem_khac_ket_qua(rs.getString("xet_nghiem_khac_ket_qua"));
					item.setXet_nghiem_khac_ket_luan(Utilities.decode(rs.getString("xet_nghiem_khac_ket_luan")));
					item.setXet_nghiem_bac_si(Utilities.decode(rs.getString("xet_nghiem_bac_si")));
					item.setKet_luan(Utilities.decode(rs.getString("ket_luan")));
					item.setHang_xe(rs.getString("hang_xe"));
					item.setKet_luan_suc_khoe(rs.getInt("ket_luan_suc_khoe"));;
					item.setNgay_kham_lai(rs.getString("ngay_kham_lai"));
					item.setBenh_nhan_id(rs.getInt("benh_nhan_id"));
					item.setNgay_kham(rs.getString("ngay_kham"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Lấy tổng số bản ghi
		rs = res.get(1);
		int total = 0;
		if(rs!=null) {
			try {
				if(rs.next()) {
					total = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Lấy thông tin khách hàng
		rs = res.get(2);
		HashMap<Integer, CustomerObject> cuss = new HashMap<Integer, CustomerObject>();
		CustomerObject cus = null;
		if(rs!=null) {
			try {
				while(rs.next()) {
					cus = new CustomerObject();
					cus.setCustomer_id(rs.getInt("customer_id"));
					cus.setCustomer_fullname(Utilities.decode(rs.getString("customer_fullname")));
					cus.setCustomer_address(Utilities.decode(rs.getString("customer_address")));
					cus.setCustomer_phone(rs.getString("customer_phone"));
					cus.setCustomer_notes(rs.getString("customer_identity_card"));
					cus.setCustomer_email(rs.getString("customer_email"));
					cus.setCustomer_modified_date(rs.getString("customer_identity_card_issue_date"));
					cus.setCustomer_modified_date(rs.getString("customer_gender"));
					cus.setCustomer_modified_date(rs.getString("customer_identity_card_issue_address"));
					//cus.setCustomer_modified_date(rs.getString("customer_birth"));
					cuss.put(rs.getInt("customer_id"), cus);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Lấy danh sách bác sĩ
		rs = res.get(3);
		ArrayList<String> users = new ArrayList<String>();
		if(rs!=null) {
			try {
				while(rs.next()) {
					users.add(Utilities.decode(rs.getString("user_fullname")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new Quartet<>(items, total, cuss, users);
	}
	
//	public Pair<ArrayList<GKSKObject>,Integer> getGKSKs(Triplet<GKSKObject, Short, Byte> infors){
//		ArrayList<GKSKObject> items = new ArrayList<GKSKObject>();
//		
//		GKSKObject item = null;
//		ArrayList<ResultSet> res = this.gk.getGKSKs(infors);
//		ResultSet rs = res.get(0);
//		if(rs!=null) {
//			try {
//				while(rs.next()) {
//					item = new GKSKObject();
//					item.setId(rs.getInt("id"));
//					item.setTien_su_gia_dinh(rs.getBoolean("tien_su_gia_dinh"));
//					item.setTien_su_gia_dinh_ten_benh(rs.getString("tien_su_gia_dinh_ten_benh"));
//					item.setCo_benh_trong_5_nam_qua(rs.getBoolean("co_benh_trong_5_nam_qua"));
//					item.setDai_thao_duong(rs.getBoolean("dai_thao_duong"));
//					item.setCo_benh_than_kinh(rs.getBoolean("co_benh_than_kinh"));
//					item.setBenh_tam_than(rs.getBoolean("benh_tam_than"));
//					item.setBenh_mat_giam_thi_luc(rs.getBoolean("benh_mat_giam_thi_luc"));
//					item.setMat_y_thuc_roi_loan_y_thuc(rs.getBoolean("mat_y_thuc_roi_loan_y_thuc"));
//					item.setBenh_o_tai(rs.getBoolean("benh_o_tai"));
//					item.setNgat_chong_mat(rs.getBoolean("ngat_chong_mat"));
//					item.setBenh_o_tim(rs.getBoolean("benh_o_tim"));
//					item.setBenh_tieu_hoa(rs.getBoolean("benh_tieu_hoa"));
//					item.setPhau_thuat_can_thiep_tim_mach(rs.getBoolean("phau_thuat_can_thiep_tim_mach"));
//					item.setRoi_loan_giac_ngu(rs.getBoolean("roi_loan_giac_ngu"));
//					item.setTang_huyet_ap(rs.getBoolean("tang_huyet_ap"));
//					item.setTai_bien_mach_mau_nao(rs.getBoolean("tai_bien_mach_mau_nao"));
//					item.setKho_tho(rs.getBoolean("kho_tho"));
//					item.setBenh_hoac_ton_thuong_cot_song(rs.getBoolean("benh_hoac_ton_thuong_cot_song"));
//					item.setBenh_phoi_hen(rs.getBoolean("benh_phoi_hen"));
//					item.setSu_dung_ruou(rs.getBoolean("su_dung_ruou"));
//					item.setBenh_than_loc_mau(rs.getBoolean("benh_than_loc_mau"));
//					item.setSu_dung_ma_tuy(rs.getBoolean("su_dung_ma_tuy"));
//					item.setTien_su_benh_nhan_ten_benh(Utilities.decode(rs.getString("tien_su_benh_nhan_ten_benh")));
//					item.setDang_dieu_tri_benh_ten_benh(Utilities.decode(rs.getString("dang_dieu_tri_benh_ten_benh")));
//					item.setMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh(Utilities.decode(rs.getString("mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh")));
//
//					item.setTam_than_noi_dung_kham(Utilities.decode(rs.getString("tam_than_noi_dung_kham")));
//					item.setTam_than_ket_luan(Utilities.decode(rs.getString("tam_than_ket_luan")));
//					item.setTam_than_bac_si(Utilities.decode(rs.getString("tam_than_bac_si")));
//					item.setThan_kinh_noi_dung_kham(Utilities.decode(rs.getString("than_kinh_noi_dung_kham")));
//					item.setThan_kinh_ket_luan(Utilities.decode(rs.getString("than_kinh_ket_luan")));
//					item.setThan_kinh_bac_si(Utilities.decode(rs.getString("than_kinh_bac_si")));
//					item.setMat_tung_mat_khong_kinh_mat_trai(rs.getString("mat_tung_mat_khong_kinh_mat_trai"));
//					item.setMat_tung_mat_khong_kinh_mat_phai(rs.getString("mat_tung_mat_khong_kinh_mat_phai"));
//					item.setMat_tung_mat_co_kinh_mat_trai(rs.getString("mat_tung_mat_co_kinh_mat_trai"));
//					item.setMat_tung_mat_co_kinh_mat_phai(rs.getString("mat_tung_mat_co_kinh_mat_phai"));
//					item.setMat_hai_mat_khong_kinh(rs.getString("mat_hai_mat_khong_kinh"));
//					item.setMat_hai_mat_co_kinh(rs.getString("mat_hai_mat_co_kinh"));
//					item.setMat_thi_truong_ngang(rs.getBoolean("mat_thi_truong_ngang"));
//					item.setMat_thi_truong_dung(rs.getBoolean("mat_thi_truong_dung"));
//					item.setMat_sac_giac_binh_thuong(rs.getBoolean("mat_sac_giac_binh_thuong"));
//					item.setMat_sac_giac_mu_mau_toan_bo(rs.getBoolean("mat_sac_giac_mu_mau_toan_bo"));
//					item.setMat_sac_giac_mu_mau_do(rs.getBoolean("mat_sac_giac_mu_mau_do"));
//					item.setMat_sac_giac_mu_mau_xanh_la(rs.getBoolean("mat_sac_giac_mu_mau_xanh_la"));
//					item.setMat_sac_giac_mu_mau_vang(rs.getBoolean("mat_sac_giac_mu_mau_vang"));
//					item.setMat_ten_benh(rs.getString("mat_ten_benh"));
//					item.setMat_ket_luan(Utilities.decode(rs.getString("mat_ket_luan")));
//					item.setMat_bac_si(Utilities.decode(rs.getString("mat_bac_si")));
//					item.setTai_mui_hong_tai_trai_noi_thuong(rs.getString("tai_mui_hong_tai_trai_noi_thuong"));
//					item.setTai_mui_hong_tai_trai_noi_tham(rs.getString("tai_mui_hong_tai_trai_noi_tham"));
//					item.setTai_mui_hong_tai_phai_noi_thuong(rs.getString("tai_mui_hong_tai_phai_noi_thuong"));
//					item.setTai_mui_hong_tai_phai_noi_tham(rs.getString("tai_mui_hong_tai_phai_noi_tham"));
//					item.setTai_mui_hong_ten_benh(rs.getString("tai_mui_hong_ten_benh"));
//					item.setTai_mui_hong_ket_luan(Utilities.decode(rs.getString("tai_mui_hong_ket_luan")));
//					item.setTai_mui_hong_bac_si(Utilities.decode(rs.getString("tai_mui_hong_bac_si")));
//					item.setTim_mach_mach(rs.getString("tim_mach_mach"));
//					item.setTim_mach_huyet_ap(rs.getString("tim_mach_huyet_ap"));
//					item.setTim_mach_ten_benh(rs.getString("tim_mach_ten_benh"));
//					item.setTim_mach_ket_luan(Utilities.decode(rs.getString("tim_mach_ket_luan")));
//					item.setTim_mach_bac_si(Utilities.decode(rs.getString("tim_mach_bac_si")));
//					item.setHo_hap_noi_dung_kham(Utilities.decode(rs.getString("ho_hap_noi_dung_kham")));
//					item.setHo_hap_ket_luan(Utilities.decode(rs.getString("ho_hap_ket_luan")));
//					item.setHo_hap_bac_si(Utilities.decode(rs.getString("ho_hap_bac_si")));
//					item.setCo_xuong_khop_noi_dung_kham(Utilities.decode(rs.getString("co_xuong_khop_noi_dung_kham")));
//					item.setCo_xuong_khop_ket_luan(Utilities.decode(rs.getString("co_xuong_khop_ket_luan")));
//					item.setCo_xuong_khop_bac_si(Utilities.decode(rs.getString("co_xuong_khop_bac_si")));
//					item.setNoi_tiet_noi_dung_kham(Utilities.decode(rs.getString("noi_tiet_noi_dung_kham")));
//					item.setNoi_tiet_ket_luan(Utilities.decode(rs.getString("noi_tiet_ket_luan")));
//					item.setNoi_tiet_bac_si(Utilities.decode(rs.getString("noi_tiet_bac_si")));
//					item.setThai_san_noi_dung_kham(Utilities.decode(rs.getString("thai_san_noi_dung_kham")));
//					item.setThai_san_ket_luan(Utilities.decode(rs.getString("thai_san_ket_luan")));
//					item.setThai_san_bac_si(Utilities.decode(rs.getString("thai_san_bac_si")));
//					item.setXet_nghiem_morphin_heroin(rs.getBoolean("xet_nghiem_morphin_heroin"));
//					item.setXet_nghiem_amphetamin(rs.getBoolean("xet_nghiem_amphetamin"));
//					item.setXet_nghiem_methamphetamin(rs.getBoolean("xet_nghiem_methamphetamin"));
//					item.setXet_nghiem_marijuana(rs.getBoolean("xet_nghiem_marijuana"));
//					item.setXet_nghiem_nong_do_con(rs.getBoolean("xet_nghiem_nong_do_con"));
//					item.setXet_nghiem_khac_ket_qua(rs.getString("xet_nghiem_khac_ket_qua"));
//					item.setXet_nghiem_khac_ket_luan(Utilities.decode(rs.getString("xet_nghiem_khac_ket_luan")));
//					item.setXet_nghiem_bac_si(Utilities.decode(rs.getString("xet_nghiem_bac_si")));
//					item.setKet_luan(Utilities.decode(rs.getString("ket_luan")));
//					item.setHang_xe(rs.getString("hang_xe"));
//					item.setKet_luan_suc_khoe(rs.getInt("ket_luan_suc_khoe"));;
//					item.setNgay_kham_lai(rs.getString("ngay_kham_lai"));
//					item.setBenh_nhan_id(rs.getInt("benh_nhan_id"));
//					item.setNgay_kham(rs.getString("ngay_kham"));
//					items.add(item);
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		//Lấy tổng số bản ghi
//		rs = res.get(1);
//		int total = 0;
//		if(rs!=null) {
//			try {
//				if(rs.next()) {
//					total = rs.getInt("total");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		return new Pair<>(items, total);
//	}
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		GKSKModel md = new GKSKModel(cp);
//		ArrayList<GKSKObject> list = new ArrayList<GKSKObject>();
//		Pair<ArrayList<GKSKObject>,Integer> tmp = md.getGKSKs(new Triplet<GKSKObject, Short, Byte>(null, (short)1, (byte)10));
//		list = tmp.getValue0();
//		list.forEach(item->{
//			System.out.println(item);
//		});
		GKSKObject item = md.getGKSK(1);
		if(item==null) {
			System.out.println("Khong thay");
		}else {
			System.out.println("Thay");
		}
		//System.out.println("Toong so: " + tmp.getValue1());
//		GKSKObject item = md.getGKSK(31);
//		String day = item.getNgay_kham().substring(8, 10);
//	    String month = item.getNgay_kham().substring(5, 7);
//	    String year = item.getNgay_kham().substring(0, 4);
//	    System.out.println(item.getNgay_kham());
//	    System.out.println(day+" - " + month+" - "+ year);
		
	}
	
}
