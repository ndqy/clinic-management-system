package jsoft.ads.certificate;

import java.sql.*;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.CertificateObject;

public class CertificateImpl extends BasicImpl implements Certificate{

	public CertificateImpl(ConnectionPool cp) {
		super(cp, "Certificate");
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean addCertificate(CertificateObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblcertificate( ");
		sql.append("certificate_code, certificate_customer_id, certificate_created_date, ");
		sql.append("certificate_driving_license_type, certificate_created_author_id, ");
		sql.append("certificate_last_modified, certificate_1_1_yesno, certificate_1_1_notes, ");
		sql.append("certificate_1_2_yesno, certificate_1_2_notes, certificate_1_3_question_1, ");
		sql.append("certificate_1_3_question_2, certificate_2_mental_notes, ");
		sql.append("certificate_2_mental_conclusion, certificate_2_mental_user_id, ");
		sql.append("certificate_2_nervous_notes, certificate_2_nervous_conclusion, ");
		sql.append("certificate_2_nervous_user_id, certificate_2_eyes_each_eye_1, ");
		sql.append("certificate_2_eyes_each_eye_2, certificate_2_eyes_both_eye, ");
		sql.append("certificate_2_eyes_visual_fields, certificate_2_eyes_color_vision, ");
		sql.append("certificate_2_eyes_notes, certificate_2_eyes_conclusion, ");
		sql.append("certificate_2_eyes_user_id, certificate_2_ears_nose_throat_left, ");
		sql.append("certificate_2_ears_nose_throat_right, certificate_2_ears_nose_throat_notes, ");
		sql.append("certificate_2_ears_nose_throat_conclusion, certificate_2_ears_nose_throat_user_id, ");
		sql.append("certificate_2_circulatory_pulse, certificate_2_circulatory_blood_pressure, ");
		sql.append("certificate_2_circulatory_notes, certificate_2_circulatory_conclusion, ");
		sql.append("certificate_2_circulatory_user_id, certificate_2_respiratory_notes, ");
		sql.append("certificate_2_respiratory_conclusion, certificate_2_respiratory_user_id, ");
		sql.append("certificate_2_musculoskeletal_notes, certificate_2_musculoskeletal_conclusion, ");
		sql.append("certificate_2_musculoskeletal_user_id, certificate_2_endocrine_notes, ");
		sql.append("certificate_2_endocrine_conclusion, certificate_2_endocrine_user_id, ");
		sql.append("certificate_2_obstetrics_notes, certificate_2_obstetrics_conclusion, ");
		sql.append("certificate_2_obstetrics_user_id, certificate_3_1_drug_tests, ");
		sql.append("certificate_3_1_additional_tests, certificate_3_2_results, ");
		sql.append("certificate_3_2_conclusion, certificate_conclusion, ");
		sql.append("certificate_author_conclusion, certificate_conclusion_type, ");
		sql.append("certificate_conclusion_notes, certificate_delete, certificate_image) ");
		sql.append("VALUES (");
		sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,");
		sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		try {
			PreparedStatement pre =  this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getCertificate_code());
			pre.setInt(2, item.getCertificate_customer_id());
			pre.setString(3, item.getCertificate_created_date());
			pre.setString(4, item.getCertificate_driving_license_type());
			pre.setInt(5, item.getCertificate_created_author_id());
			pre.setString(6, item.getCertificate_last_modified());
			pre.setBoolean(7, item.isCertificate_1_1_yesno());
			pre.setString(8, item.getCertificate_1_1_notes());
			pre.setString(9, item.getCertificate_1_2_yesno());
			pre.setString(10, item.getCertificate_1_2_notes());
			pre.setString(11, item.getCertificate_1_3_question_1());
			pre.setString(12, item.getCertificate_1_3_question_2());
			pre.setString(13, item.getCertificate_2_mental_notes());
			pre.setShort(14, item.getCertificate_2_mental_conclusion());
			pre.setInt(15, item.getCertificate_2_mental_user_id());
			pre.setString(16, item.getCertificate_2_nervous_notes());
			pre.setShort(17, item.getCertificate_2_nervous_conclusion());
			pre.setInt(18, item.getCertificate_2_nervous_user_id());
			pre.setString(19, item.getCertificate_2_eyes_each_eye_1());
			pre.setString(20, item.getCertificate_2_eyes_each_eye_2());
			pre.setString(21, item.getCertificate_2_eyes_both_eye());
			pre.setString(22, item.getCertificate_2_eyes_visual_fields());
			pre.setString(23, item.getCertificate_2_eyes_color_vision());
			pre.setString(24, item.getCertificate_2_eyes_notes());
			pre.setShort(25, item.getCertificate_2_eyes_conclusion());
			pre.setInt(26, item.getCertificate_2_eyes_user_id());
			pre.setString(27, item.getCertificate_2_ears_nose_throat_left());
			pre.setString(28, item.getCertificate_2_ears_nose_throat_right());
			pre.setString(29, item.getCertificate_2_ears_nose_throat_notes());
			pre.setShort(30, item.getCertificate_2_ears_nose_throat_conclusion());
			pre.setInt(31, item.getCertificate_2_ears_nose_throat_user_id());
			pre.setString(32, item.getCertificate_2_circulatory_pulse());
			pre.setString(33, item.getCertificate_2_circulatory_blood_pressure());
			pre.setString(34, item.getCertificate_2_circulatory_notes());
			pre.setShort(35, item.getCertificate_2_circulatory_conclusion());
			pre.setInt(36, item.getCertificate_2_circulatory_user_id());
			pre.setString(37, item.getCertificate_2_respiratory_notes());
			pre.setShort(38, item.getCertificate_2_respiratory_conclusion());
			pre.setInt(39, item.getCertificate_2_respiratory_user_id());
			pre.setString(40, item.getCertificate_2_musculoskeletal_notes());
			pre.setShort(41, item.getCertificate_2_musculoskeletal_conclusion());
			pre.setInt(42, item.getCertificate_2_musculoskeletal_user_id());
			pre.setString(43, item.getCertificate_2_endocrine_notes());
			pre.setShort(44, item.getCertificate_2_endocrine_conclusion());
			pre.setInt(45, item.getCertificate_2_endocrine_user_id());
			pre.setString(46, item.getCertificate_2_obstetrics_notes());
			pre.setShort(47, item.getCertificate_2_obstetrics_conclusion());
			pre.setInt(48, item.getCertificate_2_obstetrics_user_id());
			pre.setString(49, item.getCertificate_3_1_drug_tests());
			pre.setString(50, item.getCertificate_3_1_additional_tests());
			pre.setString(51, item.getCertificate_3_2_results());
			pre.setString(52, item.getCertificate_3_2_conclusion());
			pre.setShort(53, item.getCertificate_conclusion());
			pre.setInt(54, item.getCertificate_author_conclusion());
			pre.setShort(55, item.getCertificate_conclusion_type());
			pre.setString(56, item.getCertificate_conclusion_notes());
			pre.setBoolean(57, item.isCertificate_delete());
			pre.setString(58, item.getCertificate_image());

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
	public boolean editCertificate(CertificateObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delCertificate(CertificateObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getCertificate(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblcertificate WHERE certificate_id = ?");
		return this.get(sql.toString(), id);
	}

	@Override
	public ArrayList<ResultSet> getCertificates(Triplet<CertificateObject, Short, Byte> infors) {
		// TODO Auto-generated method stub
		CertificateObject item = infors.getValue0();
		
		
		//Lấy danh sách
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblcertificate ;");
		
		return this.getReLists(sql.toString());
	}

}
