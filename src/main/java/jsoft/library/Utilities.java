package jsoft.library;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletRequest;
import net.htmlparser.jericho.*;


public class Utilities {
	public static byte getByteParam(ServletRequest request, String name) {
		byte value = -1;
		
		String str_value = request.getParameter(name);
		
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Byte.parseByte(str_value);
		}
		return value;
	}
	public static Short getShortParam(ServletRequest request, String name) {
		Short value = -1;
		
		String str_value = request.getParameter(name);
		
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Short.parseShort(str_value);
		}
		return value;
	}
	public static Float getFloatParam(ServletRequest request, String name) {
		float value = 0;
		
		String str_value = request.getParameter(name);
		
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Float.parseFloat(str_value);
		}
		return value;
	}
	public static int getIntParam(ServletRequest request, String name) {
		int value = -1;
		
		String str_value = request.getParameter(name);
		
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Integer.parseInt(str_value);
		}
		return value;
	}
	public static String encode(String str_unicode) {
		return CharacterReference.encode(str_unicode);
	}
	public static String decode(String str_html) {
		return CharacterReference.decode(str_html);
	}
	
	/**
	 * Phương thức dùng để mã hóa khi tìm kiếm
	 * @param str_html - từ khóa cần mã hóa
	 * @return
	 */
	public static String encodeURL(String str_html) {
		String tmp ="";
		try {
			 tmp =URLEncoder.encode(str_html,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
	public static String decodeURL(String str_html) {
		String tmp ="";
		try {
			 tmp =URLDecoder.decode(str_html,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
	public static String encodeUTF(String utf8String) {

		// Mã hóa chuỗi UTF-8 thành mã Unicode
		byte[] utf8Bytes = utf8String.getBytes(StandardCharsets.UTF_8);
		String unicodeString = new String(utf8Bytes, StandardCharsets.UTF_8);
		// In kết quả
		return unicodeString;
	}
	public static void main(String[] args) {
		String utf8String = "Tiếng việt";

		// Mã hóa chuỗi UTF-8 thành mã Unicode
		byte[] utf8Bytes = utf8String.getBytes(StandardCharsets.UTF_8);
		String unicodeString = new String(utf8Bytes, StandardCharsets.UTF_8);

		// In kết quả
		System.out.println(unicodeString);
		// In kết quả
		System.out.println(utf8Bytes);
	}
}
