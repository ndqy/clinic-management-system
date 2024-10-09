package jsoft.library;

public class Utilities_text {

	public static boolean checkvalidPass(String pass1, String pass2) {
		if (pass1 != null && pass2 != null) {
			if (!pass1.equalsIgnoreCase("") && !pass2.equalsIgnoreCase("")) {
				if (pass1.length() >= 6 && pass1.equals(pass2)) {
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		for(int i=1; i<60; i++) {
			System.out.println("pre.setString("+i+", item.getCertificate_code());");
			
		}
	}
}
