package jsoft.ads.gksk;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.javatuples.Triplet;
import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.library.Utilities;
import jsoft.objects.GKSKObject;

public class GKSKImpl extends BasicImpl implements GKSK {

	public GKSKImpl(ConnectionPool cp) {
		super(cp, "GKSK");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addGKSK(GKSKObject item) {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tbl_gksk ( ");
		sql.append("`tam_than_noi_dung_kham`, `tam_than_ket_luan`, `tam_than_bac_si`, ");
		sql.append("`than_kinh_noi_dung_kham`, `than_kinh_ket_luan`, `than_kinh_bac_si`,  ");
		sql.append("`mat_tung_mat_khong_kinh_mat_trai`, `mat_tung_mat_khong_kinh_mat_phai`, ");
		sql.append("`mat_tung_mat_co_kinh_mat_trai`, `mat_tung_mat_co_kinh_mat_phai`,  ");
		sql.append("`mat_hai_mat_khong_kinh`, `mat_hai_mat_co_kinh`, `mat_thi_truong_ngang`,  ");
		sql.append("`mat_thi_truong_dung`, `mat_sac_giac_binh_thuong`, `mat_sac_giac_mu_mau_toan_bo`, ");
		sql.append("`mat_sac_giac_mu_mau_do`, `mat_sac_giac_mu_mau_xanh_la`, `mat_sac_giac_mu_mau_vang`,  ");
		sql.append("`mat_ten_benh`, `mat_ket_luan`, `mat_bac_si`,  ");
		sql.append("`tai_mui_hong_tai_trai_noi_thuong`, `tai_mui_hong_tai_trai_noi_tham`,  ");
		sql.append("`tai_mui_hong_tai_phai_noi_thuong`, `tai_mui_hong_tai_phai_noi_tham`,  ");
		sql.append("`tai_mui_hong_ten_benh`, `tai_mui_hong_ket_luan`, `tai_mui_hong_bac_si`,  ");
		sql.append("`tim_mach_mach`, `tim_mach_huyet_ap`, `tim_mach_ten_benh`, `tim_mach_ket_luan`,  ");
		sql.append("`tim_mach_bac_si`, `ho_hap_noi_dung_kham`, `ho_hap_ket_luan`, `ho_hap_bac_si`,  ");
		sql.append("`co_xuong_khop_noi_dung_kham`, `co_xuong_khop_ket_luan`, `co_xuong_khop_bac_si`,  ");
		sql.append("`noi_tiet_noi_dung_kham`, `noi_tiet_ket_luan`, `noi_tiet_bac_si`,  ");
		sql.append("`thai_san_noi_dung_kham`, `thai_san_ket_luan`, `thai_san_bac_si`,  ");
		sql.append("`xet_nghiem_morphin_heroin`, `xet_nghiem_amphetamin`, `xet_nghiem_methamphetamin`,  ");
		sql.append("`xet_nghiem_marijuana`, `xet_nghiem_nong_do_con`, `xet_nghiem_khac_ket_qua`, ");
		sql.append("`xet_nghiem_khac_ket_luan`, `xet_nghiem_bac_si`, `ket_luan`,  ");
		sql.append("`hang_xe`, `ngay_kham_lai`, `benh_nhan_id`, `co_benh_trong_5_nam_qua`,  ");
		sql.append("`dai_thao_duong`, `co_benh_than_kinh`, `benh_tam_than`, `benh_mat_giam_thi_luc`, ");
		sql.append("`mat_y_thuc_roi_loan_y_thuc`, `benh_o_tai`, `ngat_chong_mat`, `benh_o_tim`,  ");
		sql.append("`benh_tieu_hoa`, `phau_thuat_can_thiep_tim_mach`, `roi_loan_giac_ngu`,  ");
		sql.append("`tang_huyet_ap`, `tai_bien_mach_mau_nao`, `kho_tho`, `benh_hoac_ton_thuong_cot_song`,  ");
		sql.append("`benh_phoi_hen`, `su_dung_ruou`, `benh_than_loc_mau`, `su_dung_ma_tuy`,  ");
		sql.append("`tien_su_benh_nhan_ten_benh`,  ");
		sql.append("`dang_dieu_tri_benh_ten_benh`,  ");
		sql.append("`mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh`, `tien_su_gia_dinh`, ");
		sql.append("`tien_su_gia_dinh_ten_benh`, `ket_luan_suc_khoe`,`ngay_kham`) ");
		sql.append("VALUES ( ");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,  ?, ?, ");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?  ");
		sql.append("); ");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getTam_than_noi_dung_kham()));
			pre.setString(2,  Utilities.encode(item.getTam_than_ket_luan()));
			pre.setString(3,  Utilities.encode(item.getTam_than_bac_si()));
			pre.setString(4,  Utilities.encode(item.getThan_kinh_noi_dung_kham()));
			pre.setString(5,  Utilities.encode(item.getThan_kinh_ket_luan()));
			pre.setString(6,  Utilities.encode(item.getThan_kinh_bac_si()));
			pre.setString(7, item.getMat_tung_mat_khong_kinh_mat_trai());
			pre.setString(8, item.getMat_tung_mat_khong_kinh_mat_phai());
			pre.setString(9, item.getMat_tung_mat_co_kinh_mat_trai());
			pre.setString(10, item.getMat_tung_mat_co_kinh_mat_phai());
			pre.setString(11, item.getMat_hai_mat_khong_kinh());
			pre.setString(12, item.getMat_hai_mat_co_kinh());
			pre.setBoolean(13, item.isMat_thi_truong_ngang());
			pre.setBoolean(14, item.isMat_thi_truong_dung());
			pre.setBoolean(15, item.isMat_sac_giac_binh_thuong());
			pre.setBoolean(16, item.isMat_sac_giac_mu_mau_toan_bo());
			pre.setBoolean(17, item.isMat_sac_giac_mu_mau_do());
			pre.setBoolean(18, item.isMat_sac_giac_mu_mau_xanh_la());
			pre.setBoolean(19, item.isMat_sac_giac_mu_mau_vang());
			pre.setString(20, Utilities.encode(item.getMat_ten_benh()));
			pre.setString(21, Utilities.encode(item.getMat_ket_luan()));
			pre.setString(22, Utilities.encode(item.getMat_bac_si()));
			pre.setString(23, item.getTai_mui_hong_tai_trai_noi_thuong());
			pre.setString(24, item.getTai_mui_hong_tai_trai_noi_tham());
			pre.setString(25, item.getTai_mui_hong_tai_phai_noi_thuong());
			pre.setString(26, item.getTai_mui_hong_tai_phai_noi_tham());
			pre.setString(27, Utilities.encode(item.getTai_mui_hong_ten_benh()));
			pre.setString(28, Utilities.encode(item.getTai_mui_hong_ket_luan()));
			pre.setString(29, Utilities.encode(item.getTai_mui_hong_bac_si()));
			pre.setString(30, Utilities.encode(item.getTim_mach_mach()));
			pre.setString(31, Utilities.encode(item.getTim_mach_huyet_ap()));
			pre.setString(32, Utilities.encode(item.getTim_mach_ten_benh()));
			pre.setString(33, Utilities.encode(item.getTim_mach_ket_luan()));
			pre.setString(34, Utilities.encode(item.getTim_mach_bac_si()));
			pre.setString(35, Utilities.encode(item.getHo_hap_noi_dung_kham()));
			pre.setString(36, Utilities.encode(item.getHo_hap_ket_luan()));
			pre.setString(37, Utilities.encode(item.getHo_hap_bac_si()));
			pre.setString(38, Utilities.encode(item.getCo_xuong_khop_noi_dung_kham()));
			pre.setString(39, Utilities.encode(item.getCo_xuong_khop_ket_luan()));
			pre.setString(40, Utilities.encode(item.getCo_xuong_khop_bac_si()));
			pre.setString(41, Utilities.encode(item.getNoi_tiet_noi_dung_kham()));
			pre.setString(42, Utilities.encode(item.getNoi_tiet_ket_luan()));
			pre.setString(43, Utilities.encode(item.getNoi_tiet_bac_si()));
			pre.setString(44, Utilities.encode(item.getThai_san_noi_dung_kham()));
			pre.setString(45, Utilities.encode(item.getThai_san_ket_luan()));
			pre.setString(46, Utilities.encode(item.getThai_san_bac_si()));
			pre.setBoolean(47, item.isXet_nghiem_morphin_heroin());
			pre.setBoolean(48, item.isXet_nghiem_amphetamin());
			pre.setBoolean(49, item.isXet_nghiem_methamphetamin());
			pre.setBoolean(50, item.isXet_nghiem_marijuana());
			pre.setString(51, item.getXet_nghiem_nong_do_con());
			pre.setString(52, Utilities.encode(item.getXet_nghiem_khac_ket_qua()));
			pre.setString(53, Utilities.encode(item.getXet_nghiem_khac_ket_luan()));
			pre.setString(54, Utilities.encode(item.getXet_nghiem_bac_si()));
			pre.setString(55, Utilities.encode(item.getKet_luan()));
			pre.setString(56, item.getHang_xe());
			pre.setString(57, item.getNgay_kham_lai());
			pre.setInt(58, item.getBenh_nhan_id());
			pre.setBoolean(59, item.isCo_benh_trong_5_nam_qua());
			pre.setBoolean(60, item.isDai_thao_duong());
			pre.setBoolean(61, item.isCo_benh_than_kinh());
			pre.setBoolean(62, item.isBenh_tam_than());
			pre.setBoolean(63, item.isBenh_mat_giam_thi_luc());
			pre.setBoolean(64, item.isMat_y_thuc_roi_loan_y_thuc());
			pre.setBoolean(65, item.isBenh_o_tai());
			pre.setBoolean(66, item.isNgat_chong_mat());
			pre.setBoolean(67, item.isBenh_o_tim());
			pre.setBoolean(68, item.isBenh_tieu_hoa());
			pre.setBoolean(69, item.isPhau_thuat_can_thiep_tim_mach());
			pre.setBoolean(70, item.isRoi_loan_giac_ngu());
			pre.setBoolean(71, item.isTang_huyet_ap());
			pre.setBoolean(72, item.isTai_bien_mach_mau_nao());
			pre.setBoolean(73, item.isKho_tho());
			pre.setBoolean(74, item.isBenh_hoac_ton_thuong_cot_song());
			pre.setBoolean(75, item.isBenh_phoi_hen());
			pre.setBoolean(76, item.isSu_dung_ruou());
			pre.setBoolean(77, item.isBenh_than_loc_mau());
			pre.setBoolean(78, item.isSu_dung_ma_tuy());
			pre.setString(79, Utilities.encode(item.getTien_su_benh_nhan_ten_benh()));
			pre.setString(80, Utilities.encode(item.getDang_dieu_tri_benh_ten_benh()));
			pre.setString(81, Utilities.encode(item.getMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh()));
			pre.setBoolean(82, item.isTien_su_gia_dinh());
			pre.setString(83, Utilities.encode(item.getTien_su_gia_dinh_ten_benh()));
			pre.setInt(84, item.getKet_luan_suc_khoe());
			pre.setString(85, item.getNgay_kham());
			System.out.println(item.getNgay_kham()+"4456465");
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public boolean editGKSK(GKSKObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbl_gksk SET ");
		sql.append("tam_than_noi_dung_kham = ? , tam_than_ket_luan = ? , tam_than_bac_si = ? , ");
		sql.append("than_kinh_noi_dung_kham = ? , than_kinh_ket_luan = ? , than_kinh_bac_si = ? ,  ");
		sql.append("mat_tung_mat_khong_kinh_mat_trai = ? , mat_tung_mat_khong_kinh_mat_phai = ? , ");
		sql.append("mat_tung_mat_co_kinh_mat_trai = ? , mat_tung_mat_co_kinh_mat_phai = ? ,  ");
		sql.append("mat_hai_mat_khong_kinh = ? , mat_hai_mat_co_kinh = ? , mat_thi_truong_ngang = ? ,  ");
		sql.append("mat_thi_truong_dung = ? , mat_sac_giac_binh_thuong = ? , mat_sac_giac_mu_mau_toan_bo = ? , ");
		sql.append("mat_sac_giac_mu_mau_do = ? , mat_sac_giac_mu_mau_xanh_la = ? , mat_sac_giac_mu_mau_vang = ? ,  ");
		sql.append("mat_ten_benh = ? , mat_ket_luan = ? , mat_bac_si = ? ,  ");
		sql.append("tai_mui_hong_tai_trai_noi_thuong = ? , tai_mui_hong_tai_trai_noi_tham = ? ,  ");
		sql.append("tai_mui_hong_tai_phai_noi_thuong = ? , tai_mui_hong_tai_phai_noi_tham = ? ,  ");
		sql.append("tai_mui_hong_ten_benh = ? , tai_mui_hong_ket_luan = ? , tai_mui_hong_bac_si = ? ,  ");
		sql.append("tim_mach_mach = ? , tim_mach_huyet_ap = ? , tim_mach_ten_benh = ? , tim_mach_ket_luan = ? ,  ");
		sql.append("tim_mach_bac_si = ? , ho_hap_noi_dung_kham = ? , ho_hap_ket_luan = ? , ho_hap_bac_si = ? ,  ");
		sql.append("co_xuong_khop_noi_dung_kham = ? , co_xuong_khop_ket_luan = ? , co_xuong_khop_bac_si = ? ,  ");
		sql.append("noi_tiet_noi_dung_kham = ? , noi_tiet_ket_luan = ? , noi_tiet_bac_si = ? ,  ");
		sql.append("thai_san_noi_dung_kham = ? , thai_san_ket_luan = ? , thai_san_bac_si = ? ,  ");
		sql.append("xet_nghiem_morphin_heroin = ? , xet_nghiem_amphetamin = ? , xet_nghiem_methamphetamin = ? ,  ");
		sql.append("xet_nghiem_marijuana = ? , xet_nghiem_nong_do_con = ? , xet_nghiem_khac_ket_qua = ? , ");
		sql.append("xet_nghiem_khac_ket_luan = ? , xet_nghiem_bac_si = ? , ket_luan = ? ,  ");
		sql.append("hang_xe = ? , ngay_kham_lai = ? , benh_nhan_id = ? , co_benh_trong_5_nam_qua = ? ,  ");
		sql.append("dai_thao_duong = ? , co_benh_than_kinh = ? , benh_tam_than = ? , benh_mat_giam_thi_luc = ? , ");
		sql.append("mat_y_thuc_roi_loan_y_thuc = ? , benh_o_tai = ? , ngat_chong_mat = ? , benh_o_tim = ? ,  ");
		sql.append("benh_tieu_hoa = ? , phau_thuat_can_thiep_tim_mach = ? , roi_loan_giac_ngu = ? ,  ");
		sql.append(
				"tang_huyet_ap = ? , tai_bien_mach_mau_nao = ? , kho_tho = ? , benh_hoac_ton_thuong_cot_song = ? ,  ");
		sql.append("benh_phoi_hen = ? , su_dung_ruou = ? , benh_than_loc_mau = ? , su_dung_ma_tuy = ? ,  ");
		sql.append("tien_su_benh_nhan_ten_benh = ? ,  ");
		sql.append("dang_dieu_tri_benh_ten_benh = ? ,  ");
		sql.append("mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh = ?, ");
		sql.append("tien_su_gia_dinh = ?, ");
		sql.append("tien_su_gia_dinh_ten_benh = ?, ket_luan_suc_khoe = ? ");
		sql.append("WHERE id = ? ");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getTam_than_noi_dung_kham()));
			pre.setString(2,  Utilities.encode(item.getTam_than_ket_luan()));
			pre.setString(3,  Utilities.encode(item.getTam_than_bac_si()));
			pre.setString(4,  Utilities.encode(item.getThan_kinh_noi_dung_kham()));
			pre.setString(5,  Utilities.encode(item.getThan_kinh_ket_luan()));
			pre.setString(6,  Utilities.encode(item.getThan_kinh_bac_si()));
			pre.setString(7, item.getMat_tung_mat_khong_kinh_mat_trai());
			pre.setString(8, item.getMat_tung_mat_khong_kinh_mat_phai());
			pre.setString(9, item.getMat_tung_mat_co_kinh_mat_trai());
			pre.setString(10, item.getMat_tung_mat_co_kinh_mat_phai());
			pre.setString(11, item.getMat_hai_mat_khong_kinh());
			pre.setString(12, item.getMat_hai_mat_co_kinh());
			pre.setBoolean(13, item.isMat_thi_truong_ngang());
			pre.setBoolean(14, item.isMat_thi_truong_dung());
			pre.setBoolean(15, item.isMat_sac_giac_binh_thuong());
			pre.setBoolean(16, item.isMat_sac_giac_mu_mau_toan_bo());
			pre.setBoolean(17, item.isMat_sac_giac_mu_mau_do());
			pre.setBoolean(18, item.isMat_sac_giac_mu_mau_xanh_la());
			pre.setBoolean(19, item.isMat_sac_giac_mu_mau_vang());
			pre.setString(20, Utilities.encode(item.getMat_ten_benh()));
			pre.setString(21, Utilities.encode(item.getMat_ket_luan()));
			pre.setString(22, Utilities.encode(item.getMat_bac_si()));
			pre.setString(23, item.getTai_mui_hong_tai_trai_noi_thuong());
			pre.setString(24, item.getTai_mui_hong_tai_trai_noi_tham());
			pre.setString(25, item.getTai_mui_hong_tai_phai_noi_thuong());
			pre.setString(26, item.getTai_mui_hong_tai_phai_noi_tham());
			pre.setString(27, Utilities.encode(item.getTai_mui_hong_ten_benh()));
			pre.setString(28, Utilities.encode(item.getTai_mui_hong_ket_luan()));
			pre.setString(29, Utilities.encode(item.getTai_mui_hong_bac_si()));
			pre.setString(30, Utilities.encode(item.getTim_mach_mach()));
			pre.setString(31, Utilities.encode(item.getTim_mach_huyet_ap()));
			pre.setString(32, Utilities.encode(item.getTim_mach_ten_benh()));
			pre.setString(33, Utilities.encode(item.getTim_mach_ket_luan()));
			pre.setString(34, Utilities.encode(item.getTim_mach_bac_si()));
			pre.setString(35, Utilities.encode(item.getHo_hap_noi_dung_kham()));
			pre.setString(36, Utilities.encode(item.getHo_hap_ket_luan()));
			pre.setString(37, Utilities.encode(item.getHo_hap_bac_si()));
			pre.setString(38, Utilities.encode(item.getCo_xuong_khop_noi_dung_kham()));
			pre.setString(39, Utilities.encode(item.getCo_xuong_khop_ket_luan()));
			pre.setString(40, Utilities.encode(item.getCo_xuong_khop_bac_si()));
			pre.setString(41, Utilities.encode(item.getNoi_tiet_noi_dung_kham()));
			pre.setString(42, Utilities.encode(item.getNoi_tiet_ket_luan()));
			pre.setString(43, Utilities.encode(item.getNoi_tiet_bac_si()));
			pre.setString(44, Utilities.encode(item.getThai_san_noi_dung_kham()));
			pre.setString(45, Utilities.encode(item.getThai_san_ket_luan()));
			pre.setString(46, Utilities.encode(item.getThai_san_bac_si()));
			pre.setBoolean(47, item.isXet_nghiem_morphin_heroin());
			pre.setBoolean(48, item.isXet_nghiem_amphetamin());
			pre.setBoolean(49, item.isXet_nghiem_methamphetamin());
			pre.setBoolean(50, item.isXet_nghiem_marijuana());
			pre.setString(51, item.getXet_nghiem_nong_do_con());
			pre.setString(52, Utilities.encode(item.getXet_nghiem_khac_ket_qua()));
			pre.setString(53, Utilities.encode(item.getXet_nghiem_khac_ket_luan()));
			pre.setString(54, Utilities.encode(item.getXet_nghiem_bac_si()));
			pre.setString(55, Utilities.encode(item.getKet_luan()));
			pre.setString(56, item.getHang_xe());
			pre.setString(57, item.getNgay_kham_lai());
			pre.setInt(58, item.getBenh_nhan_id());
			pre.setBoolean(59, item.isCo_benh_trong_5_nam_qua());
			pre.setBoolean(60, item.isDai_thao_duong());
			pre.setBoolean(61, item.isCo_benh_than_kinh());
			pre.setBoolean(62, item.isBenh_tam_than());
			pre.setBoolean(63, item.isBenh_mat_giam_thi_luc());
			pre.setBoolean(64, item.isMat_y_thuc_roi_loan_y_thuc());
			pre.setBoolean(65, item.isBenh_o_tai());
			pre.setBoolean(66, item.isNgat_chong_mat());
			pre.setBoolean(67, item.isBenh_o_tim());
			pre.setBoolean(68, item.isBenh_tieu_hoa());
			pre.setBoolean(69, item.isPhau_thuat_can_thiep_tim_mach());
			pre.setBoolean(70, item.isRoi_loan_giac_ngu());
			pre.setBoolean(71, item.isTang_huyet_ap());
			pre.setBoolean(72, item.isTai_bien_mach_mau_nao());
			pre.setBoolean(73, item.isKho_tho());
			pre.setBoolean(74, item.isBenh_hoac_ton_thuong_cot_song());
			pre.setBoolean(75, item.isBenh_phoi_hen());
			pre.setBoolean(76, item.isSu_dung_ruou());
			pre.setBoolean(77, item.isBenh_than_loc_mau());
			pre.setBoolean(78, item.isSu_dung_ma_tuy());
			pre.setString(79, Utilities.encode(item.getTien_su_benh_nhan_ten_benh()));
			pre.setString(80, Utilities.encode(item.getDang_dieu_tri_benh_ten_benh()));
			pre.setString(81, Utilities.encode(item.getMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh()));
			pre.setBoolean(82, item.isTien_su_gia_dinh());
			pre.setString(83, Utilities.encode(item.getTien_su_gia_dinh_ten_benh()));
			pre.setInt(84, item.getKet_luan_suc_khoe());
			pre.setInt(85, item.getId());
			System.out.println(item.getNgay_kham()+"4456465");
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean delGKSK(GKSKObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tbl_gksk WHERE id = ?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, item.getId());
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet getGKSK(int id) {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbl_gksk ");
		sql.append("WHERE id = ?");
		return this.get(sql.toString(), id);
	}

	@Override
	public ArrayList<ResultSet> getGKSKs(Triplet<GKSKObject, Short, Byte> infors) {
		// TODO Auto-generated method stub
		GKSKObject similar = infors.getValue0();
		byte total = infors.getValue2(); // số bản ghi trong 1 trang
		int at = (infors.getValue1() - 1) * total;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbl_gksk ");
		sql.append("ORDER BY id DESC ");
		sql.append("LIMIT ").append(at).append(" , ").append(total).append(";");
		// Đếm tổng số bản ghi
		sql.append("SELECT COUNT(id) AS total FROM tbl_gksk; ");
		String phone = similar.getCo_xuong_khop_bac_si();
		String cccd = similar.getCo_xuong_khop_ket_luan();
		String add = similar.getCo_xuong_khop_noi_dung_kham();
		// Lấy thông tin khách hàng
		if(add!=null) {
			//if(!"".equalsIgnoreCase(phone) || !"".equalsIgnoreCase(cccd)) {
				sql.append("SELECT * FROM tblcustomer WHERE customer_phone = '"+phone+"' OR  customer_identity_card= '"+cccd+"'; ");
			//}
		}else {
			sql.append("SELECT * FROM tblcustomer; ");
		}
		
		// Lấy danh sách bác sĩ
		sql.append("SELECT user_fullname FROM tbluser WHERE user_permission=1; ");
		System.out.println(sql.toString());
		return this.getReLists(sql.toString());
	}

	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		GKSKImpl sm = new GKSKImpl(cp);
		// Khai báo đối tượng của lớp GKSKObject
		GKSKObject gksk = new GKSKObject();

		// Sử dụng các phương thức set để gán giá trị cho đối tượng
		gksk.setCo_benh_trong_5_nam_qua(true);
		gksk.setDai_thao_duong(false);
		gksk.setCo_benh_than_kinh(true);
		gksk.setBenh_tam_than(false);
		gksk.setMat_y_thuc_roi_loan_y_thuc(true);
		gksk.setBenh_o_tai(false);
		gksk.setNgat_chong_mat(true);
		gksk.setBenh_o_tim(false);
		gksk.setBenh_tieu_hoa(true);
		gksk.setPhau_thuat_can_thiep_tim_mach(false);
		gksk.setRoi_loan_giac_ngu(true);
		gksk.setTang_huyet_ap(false);
		gksk.setTai_bien_mach_mau_nao(true);
		gksk.setKho_tho(false);
		gksk.setBenh_hoac_ton_thuong_cot_song(true);
		gksk.setBenh_phoi_hen(false);
		gksk.setSu_dung_ruou(true);
		gksk.setBenh_than_loc_mau(false);
		gksk.setSu_dung_ma_tuy(true);
		gksk.setTien_su_benh_nhan_ten_benh("Bệnh tiểu đường");
		gksk.setDang_dieu_tri_benh_ten_benh("Bệnh cao huyết áp");
		gksk.setMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh("N/A");
		gksk.setTam_than_noi_dung_kham("Khám tâm thần");
		gksk.setTam_than_ket_luan("Không có dấu hiệu bất thường");
		gksk.setTam_than_bac_si("Bác sĩ A");
		gksk.setThan_kinh_noi_dung_kham("Khám thần kinh");
		gksk.setThan_kinh_ket_luan("Không có dấu hiệu bất thường");
		gksk.setThan_kinh_bac_si("Bác sĩ B");
		gksk.setMat_tung_mat_khong_kinh_mat_trai("10/10");
		gksk.setMat_tung_mat_khong_kinh_mat_phai("10/10");
		gksk.setMat_tung_mat_co_kinh_mat_trai("9/10");
		gksk.setMat_tung_mat_co_kinh_mat_phai("9/10");
		gksk.setMat_hai_mat_khong_kinh("20/20");
		gksk.setMat_hai_mat_co_kinh("18/20");
		gksk.setMat_thi_truong_ngang(true);
		gksk.setMat_thi_truong_dung(false);
		gksk.setMat_sac_giac_binh_thuong(true);
		gksk.setMat_sac_giac_mu_mau_toan_bo(false);
		gksk.setMat_sac_giac_mu_mau_do(false);
		gksk.setMat_sac_giac_mu_mau_xanh_la(false);
		gksk.setMat_sac_giac_mu_mau_vang(false);
		gksk.setMat_ten_benh("Viễn thị");
		gksk.setMat_ket_luan("Không cần điều trị");
		gksk.setMat_bac_si("Bác sĩ C");
		gksk.setTai_mui_hong_tai_trai_noi_thuong("Bình thường");
		gksk.setTai_mui_hong_tai_trai_noi_tham("Không có");
		gksk.setTai_mui_hong_tai_phai_noi_thuong("Bình thường");
		gksk.setTai_mui_hong_tai_phai_noi_tham("Không có");
		gksk.setTai_mui_hong_ten_benh("Viêm xoang");
		gksk.setTai_mui_hong_ket_luan("Cần điều trị");
		gksk.setTai_mui_hong_bac_si("Bác sĩ D");
		gksk.setTim_mach_mach("Bình thường");
		gksk.setTim_mach_huyet_ap("120/80");
		gksk.setTim_mach_ten_benh("N/A");
		gksk.setTim_mach_ket_luan("Không cần điều trị");
		gksk.setTim_mach_bac_si("Bác sĩ E");
		gksk.setHo_hap_noi_dung_kham("Khám hô hấp");
		gksk.setHo_hap_ket_luan("Không có dấu hiệu bất thường");
		gksk.setHo_hap_bac_si("Bác sĩ F");
		gksk.setCo_xuong_khop_noi_dung_kham("Khám cơ xương khớp");
		gksk.setCo_xuong_khop_ket_luan("Không có dấu hiệu bất thường");
		gksk.setCo_xuong_khop_bac_si("Bác sĩ G");
		gksk.setNoi_tiet_noi_dung_kham("Khám nội tiết");
		gksk.setNoi_tiet_ket_luan("Không có dấu hiệu bất thường");
		gksk.setNoi_tiet_bac_si("Bác sĩ H");
		gksk.setThai_san_noi_dung_kham("Khám thai sản");
		gksk.setThai_san_ket_luan("Không có dấu hiệu bất thường");
		gksk.setThai_san_bac_si("Bác sĩ I");
		gksk.setXet_nghiem_morphin_heroin(false);
		gksk.setXet_nghiem_amphetamin(false);
		gksk.setXet_nghiem_methamphetamin(false);
//		gksk.setXet_nghiem_marijuana(false);
//		gksk.getXet_nghiem_nong_do_con("");
		gksk.setXet_nghiem_khac_ket_qua("Âm tính");
		gksk.setXet_nghiem_khac_ket_luan("Không có chất gây nghiện");
		gksk.setXet_nghiem_bac_si("Bác sĩ J");
		gksk.setKet_luan("Đạt yêu cầu sức khỏe");
		gksk.setHang_xe("ABC");
		gksk.setNgay_kham_lai("2024-09-15");
		gksk.setBenh_nhan_id(12345);
		gksk.setBenh_mat_giam_thi_luc(true);
		gksk.setId(28);
		if(sm.editGKSK(gksk)) {
	        System.out.println("Đối tượng GKSKObject đã được set giá trị thành công.");
		}else {
			System.out.println("That bai");
		}

		// DELETE
//        GKSKObject item = new GKSKObject();
//        item.setId(18);
//        if(sm.delGKSK(item)) {
//	        System.out.println("Đối tượng GKSKObject đã được set giá trị thành công.");
//		}else {
//			System.out.println("That bai");
//		}
		
	}
}
