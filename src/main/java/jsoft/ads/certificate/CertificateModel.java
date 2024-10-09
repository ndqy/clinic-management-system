package jsoft.ads.certificate;

import java.sql.*;
import java.util.*;

import org.javatuples.*;
import jsoft.library.*;
import jsoft.*;
import jsoft.objects.*;

public class CertificateModel {

	private Certificate c;

	public CertificateModel(ConnectionPool cp) {
		this.c = new CertificateImpl(cp);
	}

	public void finalize() throws Throwable {
		this.c = null;
	}

	public ConnectionPool getCP() {
		return this.c.getCP();
	}

	public void releaseConnection() {
		this.c.releaseConnection();
	}

	// ===========================================================
	public boolean addCertificate(CertificateObject item) {
		return this.c.addCertificate(item);
	}

	public boolean editCertificate(CertificateObject item) {
		return this.c.editCertificate(item);
	}

	public boolean delCertificate(CertificateObject item) {
		return this.c.delCertificate(item);
	}

	// ===========================================================

	public CertificateObject getCertificate(int id) {
		CertificateObject item = null;
		ResultSet rs = this.c.getCertificate(id);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new CertificateObject();
					item.setCertificate_id(rs.getInt("certificate_id"));
					item.setCertificate_code(rs.getString("certificate_code"));
					item.setCertificate_customer_id(rs.getInt("certificate_customer_id"));
					item.setCertificate_created_date(rs.getString("certificate_created_date"));
					item.setCertificate_driving_license_type(rs.getString("certificate_driving_license_type"));
					item.setCertificate_created_author_id(rs.getInt("certificate_created_author_id"));
					item.setCertificate_last_modified(rs.getString("certificate_last_modified"));
					item.setCertificate_1_1_yesno(rs.getBoolean("certificate_1_1_yesno"));
					item.setCertificate_1_1_notes(rs.getString("certificate_1_1_notes"));
					item.setCertificate_1_2_yesno(rs.getString("certificate_1_2_yesno"));
					item.setCertificate_1_2_notes(rs.getString("certificate_1_2_notes"));
					item.setCertificate_1_3_question_1(rs.getString("certificate_1_3_question_1"));
					item.setCertificate_1_3_question_2(rs.getString("certificate_1_3_question_2"));
					item.setCertificate_2_mental_notes(rs.getString("certificate_2_mental_notes"));
					item.setCertificate_2_mental_conclusion(rs.getShort("certificate_2_mental_conclusion"));
					item.setCertificate_2_mental_user_id(rs.getInt("certificate_2_mental_user_id"));
					item.setCertificate_2_nervous_notes(rs.getString("certificate_2_nervous_notes"));
					item.setCertificate_2_nervous_conclusion(rs.getShort("certificate_2_nervous_conclusion"));
					item.setCertificate_2_nervous_user_id(rs.getInt("certificate_2_nervous_user_id"));
					item.setCertificate_2_eyes_each_eye_1(rs.getString("certificate_2_eyes_each_eye_1"));
					item.setCertificate_2_eyes_each_eye_2(rs.getString("certificate_2_eyes_each_eye_2"));
					item.setCertificate_2_eyes_both_eye(rs.getString("certificate_2_eyes_both_eye"));
					item.setCertificate_2_eyes_visual_fields(rs.getString("certificate_2_eyes_visual_fields"));
					item.setCertificate_2_eyes_color_vision(rs.getString("certificate_2_eyes_color_vision"));
					item.setCertificate_2_eyes_notes(rs.getString("certificate_2_eyes_notes"));
					item.setCertificate_2_eyes_conclusion(rs.getShort("certificate_2_eyes_conclusion"));
					item.setCertificate_2_eyes_user_id(rs.getInt("certificate_2_eyes_user_id"));
					item.setCertificate_2_ears_nose_throat_left(rs.getString("certificate_2_ears_nose_throat_left"));
					item.setCertificate_2_ears_nose_throat_right(rs.getString("certificate_2_ears_nose_throat_right"));
					item.setCertificate_2_ears_nose_throat_notes(rs.getString("certificate_2_ears_nose_throat_notes"));
					item.setCertificate_2_ears_nose_throat_conclusion(rs.getShort("certificate_2_ears_nose_throat_conclusion"));
					item.setCertificate_2_ears_nose_throat_user_id(rs.getInt("certificate_2_ears_nose_throat_user_id"));
					item.setCertificate_2_circulatory_pulse(rs.getString("certificate_2_circulatory_pulse"));
					item.setCertificate_2_circulatory_blood_pressure(rs.getString("certificate_2_circulatory_blood_pressure"));
					item.setCertificate_2_circulatory_notes(rs.getString("certificate_2_circulatory_notes"));
					item.setCertificate_2_circulatory_conclusion(rs.getShort("certificate_2_circulatory_conclusion"));
					item.setCertificate_2_circulatory_user_id(rs.getInt("certificate_2_circulatory_user_id"));
					item.setCertificate_2_respiratory_notes(rs.getString("certificate_2_respiratory_notes"));
					item.setCertificate_2_respiratory_conclusion(rs.getShort("certificate_2_respiratory_conclusion"));
					item.setCertificate_2_respiratory_user_id(rs.getInt("certificate_2_respiratory_user_id"));
					item.setCertificate_2_musculoskeletal_notes(rs.getString("certificate_2_musculoskeletal_notes"));
					item.setCertificate_2_musculoskeletal_conclusion(rs.getShort("certificate_2_musculoskeletal_conclusion"));
					item.setCertificate_2_musculoskeletal_user_id(rs.getInt("certificate_2_musculoskeletal_user_id"));
					item.setCertificate_2_endocrine_notes(rs.getString("certificate_2_endocrine_notes"));
					item.setCertificate_2_endocrine_conclusion(rs.getShort("certificate_2_endocrine_conclusion"));
					item.setCertificate_2_endocrine_user_id(rs.getInt("certificate_2_endocrine_user_id"));
					item.setCertificate_2_obstetrics_notes(rs.getString("certificate_2_obstetrics_notes"));
					item.setCertificate_2_obstetrics_conclusion(rs.getShort("certificate_2_obstetrics_conclusion"));
					item.setCertificate_2_obstetrics_user_id(rs.getInt("certificate_2_obstetrics_user_id"));
					item.setCertificate_3_1_drug_tests(rs.getString("certificate_3_1_drug_tests"));
					item.setCertificate_3_1_additional_tests(rs.getString("certificate_3_1_additional_tests"));
					item.setCertificate_3_2_results(rs.getString("certificate_3_2_results"));
					item.setCertificate_3_2_conclusion(rs.getString("certificate_3_2_conclusion"));
					item.setCertificate_conclusion(rs.getShort("certificate_conclusion"));
					item.setCertificate_author_conclusion(rs.getShort("certificate_author_conclusion"));
					item.setCertificate_conclusion_type(rs.getShort("certificate_conclusion_type"));
					item.setCertificate_conclusion_notes(rs.getString("certificate_conclusion_notes"));
					item.setCertificate_delete(rs.getBoolean("certificate_delete"));
					item.setCertificate_image(rs.getString("certificate_image"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}
	
	public ArrayList<CertificateObject> getCertificates(Triplet<CertificateObject, Short, Byte> infors) {
		ArrayList<CertificateObject> items = new ArrayList<>();
		CertificateObject item = null;
		ArrayList<ResultSet> res = this.c.getCertificates(infors);
		ResultSet rs = res.get(0);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new CertificateObject();
					item.setCertificate_id(rs.getInt("certificate_id"));
					item.setCertificate_code(rs.getString("certificate_code"));
					item.setCertificate_customer_id(rs.getInt("certificate_customer_id"));
					item.setCertificate_created_date(rs.getString("certificate_created_date"));
					item.setCertificate_driving_license_type(rs.getString("certificate_driving_license_type"));
					item.setCertificate_created_author_id(rs.getInt("certificate_created_author_id"));
					item.setCertificate_last_modified(rs.getString("certificate_last_modified"));
					item.setCertificate_1_1_yesno(rs.getBoolean("certificate_1_1_yesno"));
					item.setCertificate_1_1_notes(rs.getString("certificate_1_1_notes"));
					item.setCertificate_code(rs.getString("certificate_code"));
					item.setCertificate_1_2_yesno(rs.getString("certificate_1_2_yesno"));
					item.setCertificate_1_2_notes(rs.getString("certificate_1_2_notes"));
					item.setCertificate_1_3_question_1(rs.getString("certificate_1_3_question_1"));
					item.setCertificate_1_3_question_2(rs.getString("certificate_1_3_question_2"));
					item.setCertificate_2_mental_notes(rs.getString("certificate_2_mental_notes"));
					item.setCertificate_2_mental_conclusion(rs.getShort("certificate_2_mental_conclusion"));
					item.setCertificate_2_mental_user_id(rs.getInt("certificate_2_mental_user_id"));
					item.setCertificate_2_nervous_notes(rs.getString("certificate_2_nervous_notes"));
					item.setCertificate_2_nervous_conclusion(rs.getShort("certificate_2_nervous_conclusion"));
					item.setCertificate_2_nervous_user_id(rs.getInt("certificate_2_nervous_user_id"));
					item.setCertificate_2_eyes_each_eye_1(rs.getString("certificate_2_eyes_each_eye_1"));
					item.setCertificate_2_eyes_each_eye_2(rs.getString("certificate_2_eyes_each_eye_2"));
					item.setCertificate_2_eyes_both_eye(rs.getString("certificate_2_eyes_both_eye"));
					item.setCertificate_2_eyes_visual_fields(rs.getString("certificate_2_eyes_visual_fields"));
					item.setCertificate_2_eyes_color_vision(rs.getString("certificate_2_eyes_color_vision"));
					item.setCertificate_2_eyes_notes(rs.getString("certificate_2_eyes_notes"));
					item.setCertificate_2_eyes_conclusion(rs.getShort("certificate_2_eyes_conclusion"));
					item.setCertificate_2_eyes_user_id(rs.getInt("certificate_2_eyes_user_id"));
					item.setCertificate_2_ears_nose_throat_left(rs.getString("certificate_2_ears_nose_throat_left"));
					item.setCertificate_2_ears_nose_throat_right(rs.getString("certificate_2_ears_nose_throat_right"));
					item.setCertificate_2_ears_nose_throat_notes(rs.getString("certificate_2_ears_nose_throat_notes"));
					item.setCertificate_2_ears_nose_throat_conclusion(rs.getShort("certificate_2_ears_nose_throat_conclusion"));
					item.setCertificate_2_ears_nose_throat_user_id(rs.getInt("certificate_2_ears_nose_throat_user_id"));
					item.setCertificate_2_circulatory_pulse(rs.getString("certificate_2_circulatory_pulse"));
					item.setCertificate_2_circulatory_blood_pressure(rs.getString("certificate_2_circulatory_blood_pressure"));
					item.setCertificate_2_circulatory_notes(rs.getString("certificate_2_circulatory_notes"));
					item.setCertificate_2_circulatory_conclusion(rs.getShort("certificate_2_circulatory_conclusion"));
					item.setCertificate_2_circulatory_user_id(rs.getInt("certificate_2_circulatory_user_id"));
					item.setCertificate_2_respiratory_notes(rs.getString("certificate_2_respiratory_notes"));
					item.setCertificate_2_respiratory_conclusion(rs.getShort("certificate_2_respiratory_conclusion"));
					item.setCertificate_2_respiratory_user_id(rs.getInt("certificate_2_respiratory_user_id"));
					item.setCertificate_2_musculoskeletal_notes(rs.getString("certificate_2_musculoskeletal_notes"));
					item.setCertificate_2_musculoskeletal_conclusion(rs.getShort("certificate_2_musculoskeletal_conclusion"));
					item.setCertificate_2_musculoskeletal_user_id(rs.getInt("certificate_2_musculoskeletal_user_id"));
					item.setCertificate_2_endocrine_notes(rs.getString("certificate_2_endocrine_notes"));
					item.setCertificate_2_endocrine_conclusion(rs.getShort("certificate_2_endocrine_conclusion"));
					item.setCertificate_2_endocrine_user_id(rs.getInt("certificate_2_endocrine_user_id"));
					item.setCertificate_2_obstetrics_notes(rs.getString("certificate_2_obstetrics_notes"));
					item.setCertificate_2_obstetrics_conclusion(rs.getShort("certificate_2_obstetrics_conclusion"));
					item.setCertificate_2_obstetrics_user_id(rs.getInt("certificate_2_obstetrics_user_id"));
					item.setCertificate_3_1_drug_tests(rs.getString("certificate_3_1_drug_tests"));
					item.setCertificate_3_1_additional_tests(rs.getString("certificate_3_1_additional_tests"));
					item.setCertificate_3_2_results(rs.getString("certificate_3_2_results"));
					item.setCertificate_3_2_conclusion(rs.getString("certificate_3_2_conclusion"));
					item.setCertificate_conclusion(rs.getShort("certificate_conclusion"));
					item.setCertificate_author_conclusion(rs.getShort("certificate_author_conclusion"));
					item.setCertificate_conclusion_type(rs.getShort("certificate_conclusion_type"));
					item.setCertificate_conclusion_notes(rs.getString("certificate_conclusion_notes"));
					item.setCertificate_delete(rs.getBoolean("certificate_delete"));
					item.setCertificate_image(rs.getString("certificate_image"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return items;
	}

}
