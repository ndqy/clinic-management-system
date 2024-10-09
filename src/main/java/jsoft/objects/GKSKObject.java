package jsoft.objects;

public class GKSKObject {
	
	private int id;
	private boolean tien_su_gia_dinh;
	private String tien_su_gia_dinh_ten_benh;
	private boolean co_benh_trong_5_nam_qua;
	private boolean dai_thao_duong;
	private boolean co_benh_than_kinh;
	private boolean benh_tam_than;
	private boolean benh_mat_giam_thi_luc;
	private boolean mat_y_thuc_roi_loan_y_thuc;
	private boolean benh_o_tai;
	private boolean ngat_chong_mat;
	private boolean benh_o_tim;
	private boolean benh_tieu_hoa;
	private boolean phau_thuat_can_thiep_tim_mach;
	private boolean roi_loan_giac_ngu;
	private boolean tang_huyet_ap;
	private boolean tai_bien_mach_mau_nao;
	private boolean kho_tho;
	private boolean benh_hoac_ton_thuong_cot_song;
	private boolean benh_phoi_hen;
	private boolean su_dung_ruou;
	private boolean benh_than_loc_mau;
	private boolean su_dung_ma_tuy;
	private String tien_su_benh_nhan_ten_benh;
	private String dang_dieu_tri_benh_ten_benh;
	private String mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh;

	private String tam_than_noi_dung_kham;
	private String tam_than_ket_luan;
	private String tam_than_bac_si;
	private String than_kinh_noi_dung_kham;
	private String than_kinh_ket_luan;
	private String than_kinh_bac_si;
	private String mat_tung_mat_khong_kinh_mat_trai;
	private String mat_tung_mat_khong_kinh_mat_phai;
	private String mat_tung_mat_co_kinh_mat_trai;
	private String mat_tung_mat_co_kinh_mat_phai;
	private String mat_hai_mat_khong_kinh;
	private String mat_hai_mat_co_kinh;
	private boolean mat_thi_truong_ngang;
	private boolean mat_thi_truong_dung;
	private boolean mat_sac_giac_binh_thuong;
	private boolean mat_sac_giac_mu_mau_toan_bo;
	private boolean mat_sac_giac_mu_mau_do;
	private boolean mat_sac_giac_mu_mau_xanh_la;
	private boolean mat_sac_giac_mu_mau_vang;
	private String mat_ten_benh;
	private String mat_ket_luan;
	private String mat_bac_si;
	private String tai_mui_hong_tai_trai_noi_thuong;
	private String tai_mui_hong_tai_trai_noi_tham;
	private String tai_mui_hong_tai_phai_noi_thuong;
	private String tai_mui_hong_tai_phai_noi_tham;
	private String tai_mui_hong_ten_benh;
	private String tai_mui_hong_ket_luan;
	private String tai_mui_hong_bac_si;
	private String tim_mach_mach;
	private String tim_mach_huyet_ap;
	private String tim_mach_ten_benh;
	private String tim_mach_ket_luan;
	private String tim_mach_bac_si;
	private String ho_hap_noi_dung_kham;
	private String ho_hap_ket_luan;
	private String ho_hap_bac_si;
	private String co_xuong_khop_noi_dung_kham;
	private String co_xuong_khop_ket_luan;
	private String co_xuong_khop_bac_si;
	private String noi_tiet_noi_dung_kham;
	private String noi_tiet_ket_luan;
	private String noi_tiet_bac_si;
	private String thai_san_noi_dung_kham;
	private String thai_san_ket_luan;
	private String thai_san_bac_si;
	private boolean xet_nghiem_morphin_heroin;
	private boolean xet_nghiem_amphetamin;
	private boolean xet_nghiem_methamphetamin;
	private boolean xet_nghiem_marijuana;
	private String xet_nghiem_nong_do_con;
	private String xet_nghiem_khac_ket_qua;
	private String xet_nghiem_khac_ket_luan;
	private String xet_nghiem_bac_si;
	private String ket_luan;
	private String hang_xe;
	private String ngay_kham_lai;
	private int ket_luan_suc_khoe;
	private int benh_nhan_id;
	private String ngay_kham;
	
	public int getId() {
		return id;
	}
	public boolean isCo_benh_trong_5_nam_qua() {
		return co_benh_trong_5_nam_qua;
	}
	public boolean isDai_thao_duong() {
		return dai_thao_duong;
	}
	public boolean isCo_benh_than_kinh() {
		return co_benh_than_kinh;
	}
	public boolean isBenh_tam_than() {
		return benh_tam_than;
	}
	public boolean isMat_y_thuc_roi_loan_y_thuc() {
		return mat_y_thuc_roi_loan_y_thuc;
	}
	public boolean isBenh_o_tai() {
		return benh_o_tai;
	}
	public boolean isNgat_chong_mat() {
		return ngat_chong_mat;
	}
	public boolean isBenh_o_tim() {
		return benh_o_tim;
	}
	public boolean isBenh_tieu_hoa() {
		return benh_tieu_hoa;
	}
	public boolean isPhau_thuat_can_thiep_tim_mach() {
		return phau_thuat_can_thiep_tim_mach;
	}
	public boolean isRoi_loan_giac_ngu() {
		return roi_loan_giac_ngu;
	}
	public boolean isTang_huyet_ap() {
		return tang_huyet_ap;
	}
	public boolean isTai_bien_mach_mau_nao() {
		return tai_bien_mach_mau_nao;
	}
	public boolean isKho_tho() {
		return kho_tho;
	}
	public boolean isBenh_hoac_ton_thuong_cot_song() {
		return benh_hoac_ton_thuong_cot_song;
	}
	public boolean isBenh_phoi_hen() {
		return benh_phoi_hen;
	}
	public boolean isSu_dung_ruou() {
		return su_dung_ruou;
	}
	public boolean isBenh_than_loc_mau() {
		return benh_than_loc_mau;
	}
	public boolean isSu_dung_ma_tuy() {
		return su_dung_ma_tuy;
	}
	public String getTien_su_benh_nhan_ten_benh() {
		return tien_su_benh_nhan_ten_benh;
	}
	public String getDang_dieu_tri_benh_ten_benh() {
		return dang_dieu_tri_benh_ten_benh;
	}
	public String getMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh() {
		return mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh;
	}
	public String getTam_than_noi_dung_kham() {
		return tam_than_noi_dung_kham;
	}
	public String getTam_than_ket_luan() {
		return tam_than_ket_luan;
	}
	public String getTam_than_bac_si() {
		return tam_than_bac_si;
	}
	public String getThan_kinh_noi_dung_kham() {
		return than_kinh_noi_dung_kham;
	}
	public String getThan_kinh_ket_luan() {
		return than_kinh_ket_luan;
	}
	public String getThan_kinh_bac_si() {
		return than_kinh_bac_si;
	}
	public String getMat_tung_mat_khong_kinh_mat_trai() {
		return mat_tung_mat_khong_kinh_mat_trai;
	}
	public String getMat_tung_mat_khong_kinh_mat_phai() {
		return mat_tung_mat_khong_kinh_mat_phai;
	}
	public String getMat_tung_mat_co_kinh_mat_trai() {
		return mat_tung_mat_co_kinh_mat_trai;
	}
	public String getMat_tung_mat_co_kinh_mat_phai() {
		return mat_tung_mat_co_kinh_mat_phai;
	}
	public String getMat_hai_mat_khong_kinh() {
		return mat_hai_mat_khong_kinh;
	}
	public String getMat_hai_mat_co_kinh() {
		return mat_hai_mat_co_kinh;
	}
	public boolean isMat_thi_truong_ngang() {
		return mat_thi_truong_ngang;
	}
	public boolean isMat_thi_truong_dung() {
		return mat_thi_truong_dung;
	}
	public boolean isMat_sac_giac_binh_thuong() {
		return mat_sac_giac_binh_thuong;
	}
	public boolean isMat_sac_giac_mu_mau_toan_bo() {
		return mat_sac_giac_mu_mau_toan_bo;
	}
	public boolean isMat_sac_giac_mu_mau_do() {
		return mat_sac_giac_mu_mau_do;
	}
	public boolean isMat_sac_giac_mu_mau_xanh_la() {
		return mat_sac_giac_mu_mau_xanh_la;
	}
	public boolean isMat_sac_giac_mu_mau_vang() {
		return mat_sac_giac_mu_mau_vang;
	}
	public String getMat_ten_benh() {
		return mat_ten_benh;
	}
	public String getMat_ket_luan() {
		return mat_ket_luan;
	}
	public String getMat_bac_si() {
		return mat_bac_si;
	}
	public String getTai_mui_hong_tai_trai_noi_thuong() {
		return tai_mui_hong_tai_trai_noi_thuong;
	}
	public String getTai_mui_hong_tai_trai_noi_tham() {
		return tai_mui_hong_tai_trai_noi_tham;
	}
	public String getTai_mui_hong_tai_phai_noi_thuong() {
		return tai_mui_hong_tai_phai_noi_thuong;
	}
	public String getTai_mui_hong_tai_phai_noi_tham() {
		return tai_mui_hong_tai_phai_noi_tham;
	}
	public String getTai_mui_hong_ten_benh() {
		return tai_mui_hong_ten_benh;
	}
	public String getTai_mui_hong_ket_luan() {
		return tai_mui_hong_ket_luan;
	}
	public String getTai_mui_hong_bac_si() {
		return tai_mui_hong_bac_si;
	}
	public String getTim_mach_mach() {
		return tim_mach_mach;
	}
	public String getTim_mach_huyet_ap() {
		return tim_mach_huyet_ap;
	}
	public String getTim_mach_ten_benh() {
		return tim_mach_ten_benh;
	}
	public String getTim_mach_ket_luan() {
		return tim_mach_ket_luan;
	}
	public String getTim_mach_bac_si() {
		return tim_mach_bac_si;
	}
	public String getHo_hap_noi_dung_kham() {
		return ho_hap_noi_dung_kham;
	}
	public String getHo_hap_ket_luan() {
		return ho_hap_ket_luan;
	}
	public String getHo_hap_bac_si() {
		return ho_hap_bac_si;
	}
	public String getCo_xuong_khop_noi_dung_kham() {
		return co_xuong_khop_noi_dung_kham;
	}
	public String getCo_xuong_khop_ket_luan() {
		return co_xuong_khop_ket_luan;
	}
	public String getCo_xuong_khop_bac_si() {
		return co_xuong_khop_bac_si;
	}
	public String getNoi_tiet_noi_dung_kham() {
		return noi_tiet_noi_dung_kham;
	}
	public String getNoi_tiet_ket_luan() {
		return noi_tiet_ket_luan;
	}
	public String getNoi_tiet_bac_si() {
		return noi_tiet_bac_si;
	}
	public String getThai_san_noi_dung_kham() {
		return thai_san_noi_dung_kham;
	}
	public String getThai_san_ket_luan() {
		return thai_san_ket_luan;
	}
	public String getThai_san_bac_si() {
		return thai_san_bac_si;
	}
	public boolean isXet_nghiem_morphin_heroin() {
		return xet_nghiem_morphin_heroin;
	}
	public boolean isXet_nghiem_amphetamin() {
		return xet_nghiem_amphetamin;
	}
	public boolean isXet_nghiem_methamphetamin() {
		return xet_nghiem_methamphetamin;
	}
	public boolean isXet_nghiem_marijuana() {
		return xet_nghiem_marijuana;
	}
	public String getXet_nghiem_nong_do_con() {
		return xet_nghiem_nong_do_con;
	}
	public String getXet_nghiem_khac_ket_qua() {
		return xet_nghiem_khac_ket_qua;
	}
	public String getXet_nghiem_khac_ket_luan() {
		return xet_nghiem_khac_ket_luan;
	}
	public String getXet_nghiem_bac_si() {
		return xet_nghiem_bac_si;
	}
	public String getKet_luan() {
		return ket_luan;
	}
	public String getHang_xe() {
		return hang_xe;
	}
	public String getNgay_kham_lai() {
		return ngay_kham_lai;
	}
	public int getBenh_nhan_id() {
		return benh_nhan_id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCo_benh_trong_5_nam_qua(boolean co_benh_trong_5_nam_qua) {
		this.co_benh_trong_5_nam_qua = co_benh_trong_5_nam_qua;
	}
	public void setDai_thao_duong(boolean dai_thao_duong) {
		this.dai_thao_duong = dai_thao_duong;
	}
	public void setCo_benh_than_kinh(boolean co_benh_than_kinh) {
		this.co_benh_than_kinh = co_benh_than_kinh;
	}
	public void setBenh_tam_than(boolean benh_tam_than) {
		this.benh_tam_than = benh_tam_than;
	}
	public void setMat_y_thuc_roi_loan_y_thuc(boolean mat_y_thuc_roi_loan_y_thuc) {
		this.mat_y_thuc_roi_loan_y_thuc = mat_y_thuc_roi_loan_y_thuc;
	}
	public void setBenh_o_tai(boolean benh_o_tai) {
		this.benh_o_tai = benh_o_tai;
	}
	public void setNgat_chong_mat(boolean ngat_chong_mat) {
		this.ngat_chong_mat = ngat_chong_mat;
	}
	public void setBenh_o_tim(boolean benh_o_tim) {
		this.benh_o_tim = benh_o_tim;
	}
	public void setBenh_tieu_hoa(boolean benh_tieu_hoa) {
		this.benh_tieu_hoa = benh_tieu_hoa;
	}
	public void setPhau_thuat_can_thiep_tim_mach(boolean phau_thuat_can_thiep_tim_mach) {
		this.phau_thuat_can_thiep_tim_mach = phau_thuat_can_thiep_tim_mach;
	}
	public void setRoi_loan_giac_ngu(boolean roi_loan_giac_ngu) {
		this.roi_loan_giac_ngu = roi_loan_giac_ngu;
	}
	public void setTang_huyet_ap(boolean tang_huyet_ap) {
		this.tang_huyet_ap = tang_huyet_ap;
	}
	public void setTai_bien_mach_mau_nao(boolean tai_bien_mach_mau_nao) {
		this.tai_bien_mach_mau_nao = tai_bien_mach_mau_nao;
	}
	public void setKho_tho(boolean kho_tho) {
		this.kho_tho = kho_tho;
	}
	public void setBenh_hoac_ton_thuong_cot_song(boolean benh_hoac_ton_thuong_cot_song) {
		this.benh_hoac_ton_thuong_cot_song = benh_hoac_ton_thuong_cot_song;
	}
	public void setBenh_phoi_hen(boolean benh_phoi_hen) {
		this.benh_phoi_hen = benh_phoi_hen;
	}
	public void setSu_dung_ruou(boolean su_dung_ruou) {
		this.su_dung_ruou = su_dung_ruou;
	}
	public void setBenh_than_loc_mau(boolean benh_than_loc_mau) {
		this.benh_than_loc_mau = benh_than_loc_mau;
	}
	public void setSu_dung_ma_tuy(boolean su_dung_ma_tuy) {
		this.su_dung_ma_tuy = su_dung_ma_tuy;
	}
	public void setTien_su_benh_nhan_ten_benh(String tien_su_benh_nhan_ten_benh) {
		this.tien_su_benh_nhan_ten_benh = tien_su_benh_nhan_ten_benh;
	}
	public void setDang_dieu_tri_benh_ten_benh(String dang_dieu_tri_benh_ten_benh) {
		this.dang_dieu_tri_benh_ten_benh = dang_dieu_tri_benh_ten_benh;
	}
	public void setMang_thai_nuoi_con_nho_duoi_12_thang_ten_benh(String mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh) {
		this.mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh = mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh;
	}
	public void setTam_than_noi_dung_kham(String tam_than_noi_dung_kham) {
		this.tam_than_noi_dung_kham = tam_than_noi_dung_kham;
	}
	public void setTam_than_ket_luan(String tam_than_ket_luan) {
		this.tam_than_ket_luan = tam_than_ket_luan;
	}
	public void setTam_than_bac_si(String tam_than_bac_si) {
		this.tam_than_bac_si = tam_than_bac_si;
	}
	public void setThan_kinh_noi_dung_kham(String than_kinh_noi_dung_kham) {
		this.than_kinh_noi_dung_kham = than_kinh_noi_dung_kham;
	}
	public void setThan_kinh_ket_luan(String than_kinh_ket_luan) {
		this.than_kinh_ket_luan = than_kinh_ket_luan;
	}
	public void setThan_kinh_bac_si(String than_kinh_bac_si) {
		this.than_kinh_bac_si = than_kinh_bac_si;
	}
	public void setMat_tung_mat_khong_kinh_mat_trai(String mat_tung_mat_khong_kinh_mat_trai) {
		this.mat_tung_mat_khong_kinh_mat_trai = mat_tung_mat_khong_kinh_mat_trai;
	}
	public void setMat_tung_mat_khong_kinh_mat_phai(String mat_tung_mat_khong_kinh_mat_phai) {
		this.mat_tung_mat_khong_kinh_mat_phai = mat_tung_mat_khong_kinh_mat_phai;
	}
	public void setMat_tung_mat_co_kinh_mat_trai(String mat_tung_mat_co_kinh_mat_trai) {
		this.mat_tung_mat_co_kinh_mat_trai = mat_tung_mat_co_kinh_mat_trai;
	}
	public void setMat_tung_mat_co_kinh_mat_phai(String mat_tung_mat_co_kinh_mat_phai) {
		this.mat_tung_mat_co_kinh_mat_phai = mat_tung_mat_co_kinh_mat_phai;
	}
	public void setMat_hai_mat_khong_kinh(String mat_hai_mat_khong_kinh) {
		this.mat_hai_mat_khong_kinh = mat_hai_mat_khong_kinh;
	}
	public void setMat_hai_mat_co_kinh(String mat_hai_mat_co_kinh) {
		this.mat_hai_mat_co_kinh = mat_hai_mat_co_kinh;
	}
	public void setMat_thi_truong_ngang(boolean mat_thi_truong_ngang) {
		this.mat_thi_truong_ngang = mat_thi_truong_ngang;
	}
	public void setMat_thi_truong_dung(boolean mat_thi_truong_dung) {
		this.mat_thi_truong_dung = mat_thi_truong_dung;
	}
	public void setMat_sac_giac_binh_thuong(boolean mat_sac_giac_binh_thuong) {
		this.mat_sac_giac_binh_thuong = mat_sac_giac_binh_thuong;
	}
	public void setMat_sac_giac_mu_mau_toan_bo(boolean mat_sac_giac_mu_mau_toan_bo) {
		this.mat_sac_giac_mu_mau_toan_bo = mat_sac_giac_mu_mau_toan_bo;
	}
	public void setMat_sac_giac_mu_mau_do(boolean mat_sac_giac_mu_mau_do) {
		this.mat_sac_giac_mu_mau_do = mat_sac_giac_mu_mau_do;
	}
	public void setMat_sac_giac_mu_mau_xanh_la(boolean mat_sac_giac_mu_mau_xanh_la) {
		this.mat_sac_giac_mu_mau_xanh_la = mat_sac_giac_mu_mau_xanh_la;
	}
	public void setMat_sac_giac_mu_mau_vang(boolean mat_sac_giac_mu_mau_vang) {
		this.mat_sac_giac_mu_mau_vang = mat_sac_giac_mu_mau_vang;
	}
	public void setMat_ten_benh(String mat_ten_benh) {
		this.mat_ten_benh = mat_ten_benh;
	}
	public void setMat_ket_luan(String mat_ket_luan) {
		this.mat_ket_luan = mat_ket_luan;
	}
	public void setMat_bac_si(String mat_bac_si) {
		this.mat_bac_si = mat_bac_si;
	}
	public void setTai_mui_hong_tai_trai_noi_thuong(String tai_mui_hong_tai_trai_noi_thuong) {
		this.tai_mui_hong_tai_trai_noi_thuong = tai_mui_hong_tai_trai_noi_thuong;
	}
	public void setTai_mui_hong_tai_trai_noi_tham(String tai_mui_hong_tai_trai_noi_tham) {
		this.tai_mui_hong_tai_trai_noi_tham = tai_mui_hong_tai_trai_noi_tham;
	}
	public void setTai_mui_hong_tai_phai_noi_thuong(String tai_mui_hong_tai_phai_noi_thuong) {
		this.tai_mui_hong_tai_phai_noi_thuong = tai_mui_hong_tai_phai_noi_thuong;
	}
	public void setTai_mui_hong_tai_phai_noi_tham(String tai_mui_hong_tai_phai_noi_tham) {
		this.tai_mui_hong_tai_phai_noi_tham = tai_mui_hong_tai_phai_noi_tham;
	}
	public void setTai_mui_hong_ten_benh(String tai_mui_hong_ten_benh) {
		this.tai_mui_hong_ten_benh = tai_mui_hong_ten_benh;
	}
	public void setTai_mui_hong_ket_luan(String tai_mui_hong_ket_luan) {
		this.tai_mui_hong_ket_luan = tai_mui_hong_ket_luan;
	}
	public void setTai_mui_hong_bac_si(String tai_mui_hong_bac_si) {
		this.tai_mui_hong_bac_si = tai_mui_hong_bac_si;
	}
	public void setTim_mach_mach(String tim_mach_mach) {
		this.tim_mach_mach = tim_mach_mach;
	}
	public void setTim_mach_huyet_ap(String tim_mach_huyet_ap) {
		this.tim_mach_huyet_ap = tim_mach_huyet_ap;
	}
	public void setTim_mach_ten_benh(String tim_mach_ten_benh) {
		this.tim_mach_ten_benh = tim_mach_ten_benh;
	}
	public void setTim_mach_ket_luan(String tim_mach_ket_luan) {
		this.tim_mach_ket_luan = tim_mach_ket_luan;
	}
	public void setTim_mach_bac_si(String tim_mach_bac_si) {
		this.tim_mach_bac_si = tim_mach_bac_si;
	}
	public void setHo_hap_noi_dung_kham(String ho_hap_noi_dung_kham) {
		this.ho_hap_noi_dung_kham = ho_hap_noi_dung_kham;
	}
	public void setHo_hap_ket_luan(String ho_hap_ket_luan) {
		this.ho_hap_ket_luan = ho_hap_ket_luan;
	}
	public void setHo_hap_bac_si(String ho_hap_bac_si) {
		this.ho_hap_bac_si = ho_hap_bac_si;
	}
	public void setCo_xuong_khop_noi_dung_kham(String co_xuong_khop_noi_dung_kham) {
		this.co_xuong_khop_noi_dung_kham = co_xuong_khop_noi_dung_kham;
	}
	public void setCo_xuong_khop_ket_luan(String co_xuong_khop_ket_luan) {
		this.co_xuong_khop_ket_luan = co_xuong_khop_ket_luan;
	}
	public void setCo_xuong_khop_bac_si(String co_xuong_khop_bac_si) {
		this.co_xuong_khop_bac_si = co_xuong_khop_bac_si;
	}
	public void setNoi_tiet_noi_dung_kham(String noi_tiet_noi_dung_kham) {
		this.noi_tiet_noi_dung_kham = noi_tiet_noi_dung_kham;
	}
	public void setNoi_tiet_ket_luan(String noi_tiet_ket_luan) {
		this.noi_tiet_ket_luan = noi_tiet_ket_luan;
	}
	public void setNoi_tiet_bac_si(String noi_tiet_bac_si) {
		this.noi_tiet_bac_si = noi_tiet_bac_si;
	}
	public void setThai_san_noi_dung_kham(String thai_san_noi_dung_kham) {
		this.thai_san_noi_dung_kham = thai_san_noi_dung_kham;
	}
	public void setThai_san_ket_luan(String thai_san_ket_luan) {
		this.thai_san_ket_luan = thai_san_ket_luan;
	}
	public void setThai_san_bac_si(String thai_san_bac_si) {
		this.thai_san_bac_si = thai_san_bac_si;
	}
	public void setXet_nghiem_morphin_heroin(boolean xet_nghiem_morphin_heroin) {
		this.xet_nghiem_morphin_heroin = xet_nghiem_morphin_heroin;
	}
	public void setXet_nghiem_amphetamin(boolean xet_nghiem_amphetamin) {
		this.xet_nghiem_amphetamin = xet_nghiem_amphetamin;
	}
	public void setXet_nghiem_methamphetamin(boolean xet_nghiem_methamphetamin) {
		this.xet_nghiem_methamphetamin = xet_nghiem_methamphetamin;
	}
	public void setXet_nghiem_marijuana(boolean xet_nghiem_marijuana) {
		this.xet_nghiem_marijuana = xet_nghiem_marijuana;
	}
	public void setXet_nghiem_nong_do_con(String xet_nghiem_nong_do_con) {
		this.xet_nghiem_nong_do_con = xet_nghiem_nong_do_con;
	}
	public void setXet_nghiem_khac_ket_qua(String xet_nghiem_khac_ket_qua) {
		this.xet_nghiem_khac_ket_qua = xet_nghiem_khac_ket_qua;
	}
	public void setXet_nghiem_khac_ket_luan(String xet_nghiem_khac_ket_luan) {
		this.xet_nghiem_khac_ket_luan = xet_nghiem_khac_ket_luan;
	}
	public void setXet_nghiem_bac_si(String xet_nghiem_bac_si) {
		this.xet_nghiem_bac_si = xet_nghiem_bac_si;
	}
	public void setKet_luan(String ket_luan) {
		this.ket_luan = ket_luan;
	}
	public void setHang_xe(String hang_xe) {
		this.hang_xe = hang_xe;
	}
	public void setNgay_kham_lai(String ngay_kham_lai) {
		this.ngay_kham_lai = ngay_kham_lai;
	}
	public void setBenh_nhan_id(int benh_nhan_id) {
		this.benh_nhan_id = benh_nhan_id;
	}

	public boolean isBenh_mat_giam_thi_luc() {
		return benh_mat_giam_thi_luc;
	}
	public void setBenh_mat_giam_thi_luc(boolean benh_mat_giam_thi_luc) {
		this.benh_mat_giam_thi_luc = benh_mat_giam_thi_luc;
	}
	@Override
	public String toString() {
		return "GKSKObject [id=" + id + ", mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh="
				+ mang_thai_nuoi_con_nho_duoi_12_thang_ten_benh + ", tam_than_noi_dung_kham=" + tam_than_noi_dung_kham
				+ ", tam_than_ket_luan=" + tam_than_ket_luan + ", tam_than_bac_si=" + tam_than_bac_si
				+ ", than_kinh_noi_dung_kham=" + than_kinh_noi_dung_kham + ", co_xuong_khop_noi_dung_kham="
				+ co_xuong_khop_noi_dung_kham + ", co_xuong_khop_ket_luan=" + co_xuong_khop_ket_luan
				+ ", co_xuong_khop_bac_si=" + co_xuong_khop_bac_si + ", noi_tiet_noi_dung_kham="
				+ noi_tiet_noi_dung_kham + ", noi_tiet_ket_luan=" + noi_tiet_ket_luan + ", noi_tiet_bac_si="
				+ noi_tiet_bac_si + "]";
	}
	public boolean isTien_su_gia_dinh() {
		return tien_su_gia_dinh;
	}
	public String getTien_su_gia_dinh_ten_benh() {
		return tien_su_gia_dinh_ten_benh;
	}
	public void setTien_su_gia_dinh(boolean tien_su_gia_dinh) {
		this.tien_su_gia_dinh = tien_su_gia_dinh;
	}
	public void setTien_su_gia_dinh_ten_benh(String tien_su_gia_dinh_ten_benh) {
		this.tien_su_gia_dinh_ten_benh = tien_su_gia_dinh_ten_benh;
	}
	public int getKet_luan_suc_khoe() {
		return ket_luan_suc_khoe;
	}
	public void setKet_luan_suc_khoe(int ket_luan_suc_khoe) {
		this.ket_luan_suc_khoe = ket_luan_suc_khoe;
	}
	public String getNgay_kham() {
		return ngay_kham;
	}
	public void setNgay_kham(String ngay_kham) {
		this.ngay_kham = ngay_kham;
	}
	
	
	
}
