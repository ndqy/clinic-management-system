package jsoft.ads.testpdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import jsoft.ConnectionPool;
import jsoft.ads.calendar.CalendarControl;
import jsoft.ads.customer.CustomerControl;
import jsoft.objects.CustomerObject;

/**
 * Servlet implementation class GKSK
 */
@WebServlet("/gksk")
public class GKSK extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GKSK() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static File downloadFontFromURL(String fontUrl) throws IOException {
		URL url = new URL(fontUrl);
		InputStream in = url.openStream();
		File tempFile = Files.createTempFile("font", ".ttf").toFile();
		try (FileOutputStream out = new FileOutputStream(tempFile)) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
		return tempFile;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/pdf");
		ArrayList<Question> list = new ArrayList<Question>();
		list.add(new Question("Có bệnh hay bị thương trong 5 năm qua", true));
		list.add(new Question("Có bệnh thần kinh hay bị thương ở đầu", false));
		list.add(new Question("Bệnh mắt hoặc giảm thị lực (trừ trường hợp đeo kính thuốc)", true));
		list.add(new Question("Bệnh ở tai, giảm sức nghe hoặc thăng bằng", false));
		list.add(new Question("Bệnh ở tim, hoặc nhồi máu cơ tim, các bệnh tim mạch khác", true));
		list.add(new Question(
				"Phẫu thuật can thiệp tim - mạch (thay van, bắc cầu nối, tạo hình mạch, máy tạo nhịp, đặt slent mạch, ghép tim)",
				false));
		list.add(new Question("Tăng huyết áp", true));
		list.add(new Question("Khó thở", true));
		list.add(new Question("Bệnh phổi, hen, khí phế thũng, viêm phế quản mạn tính", false));
		list.add(new Question("Bệnh thận, lọc máu", false));
		list.add(new Question("Đái tháo đường hoặc kiểm soát tăng đường huyết", true));
		list.add(new Question("Bệnh tâm thần", false));
		list.add(new Question("Mất ý thức, rối loạn ý thức", true));
		list.add(new Question("Ngất, chóng mặt", false));
		list.add(new Question("Bệnh tiêu hóa", true));
		list.add(new Question("Rối loạn giấc ngủ, ngừng thở khi ngủ, ngủ rũ ban ngày, ngáy to", false));
		list.add(new Question("Tai biến mạch máu não hoặc liệt", true));
		list.add(new Question("Bệnh hoặc tổn thương cột sống", true));
		list.add(new Question("Sử dụng rượu thường xuyên, liên tục", false));
		list.add(new Question("Sử dụng ma túy và chất gây nghiện", true));


		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

		CustomerControl cc = new CustomerControl(cp);
		if(cp == null) {
			getServletContext().setAttribute("CPool", cc.getCP());
		}
		String id = request.getParameter("id");
		CustomerObject cus = cc.getCustomer(Integer.parseInt(id));
		try {
			PdfWriter writer = new PdfWriter(response.getOutputStream());

			// Creating a PdfDocument object
			PdfDocument pdf = new PdfDocument(writer);
			
			// Creating a Document object
			Document doc = new Document(pdf);

			String urlTinosBold = "http://localhost:8080/btl/font/Tinos/Tinos-Bold.ttf";
			String urlTinosRegular = "http://localhost:8080/btl/font/Tinos/Tinos-Regular.ttf";
			String urlTinosItalic = "http://localhost:8080/btl/font/Tinos/Tinos-Italic.ttf";
			String urlNoto = "http://localhost:8080/btl/font/NotoSans/NotoSansSymbols2-Regular.ttf";

			File fileTinosBold = downloadFontFromURL(urlTinosBold);
			File fileTinosRegular = downloadFontFromURL(urlTinosRegular);
			File fileTinosItalic = downloadFontFromURL(urlTinosItalic);
			File fileNoto = downloadFontFromURL(urlNoto);

			// Setting font of the text
			PdfFont TinosBold = PdfFontFactory.createFont(fileTinosBold.getAbsolutePath(), PdfEncodings.IDENTITY_H,
					true);
			PdfFont TinosRegular = PdfFontFactory.createFont(fileTinosRegular.getAbsolutePath(),
					PdfEncodings.IDENTITY_H, true);
			PdfFont TinosItalic = PdfFontFactory.createFont(fileTinosItalic.getAbsolutePath(), PdfEncodings.IDENTITY_H,
					true);
			PdfFont Noto = PdfFontFactory.createFont(fileNoto.getAbsolutePath(), PdfEncodings.IDENTITY_H, true);

			doc.setFont(TinosRegular);
			// Creating a table
			float [] pointColumnWidths = {300F, 100F, 500F};   
		      Table tbheader = new Table(pointColumnWidths);    
		      
		      // Populating row 1 and adding it to the table               
		      Cell c1 = new Cell();                       
		      c1.add("BỘ Y TẾ");     
		      c1.add("BỆNH VIỆN QUỐC TẾ VIỆT ĐỨC");  
		      c1.add("Số: 653289/GKSKLX");  
		      // Setting background color
		      c1.setFont(TinosBold).setFontSize(9); 
		      c1.setBorder(Border.NO_BORDER);              
		      c1.setTextAlignment(TextAlignment.CENTER);       
		      tbheader.addCell(c1);   
		      
		      Cell c = new Cell();          
		      c.setBorder(Border.NO_BORDER);                 
		      tbheader.addCell(c);   
		      
		      Cell c2 = new Cell();                               
		      c2.add("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");  
		      c2.add("Độc lập - Tự do - Hạnh phúc");  
		      c2.setFont(TinosBold).setFontSize(9); 
		      c2.add("______________");  
		      c2.setBorder(Border.NO_BORDER);       
		      c2.setTextAlignment(TextAlignment.CENTER);      
		      tbheader.addCell(c2);    
		      
		      // Creating Paragraph
		      Paragraph title = new Paragraph();
		      Text text2 = new Text("GIẤY KHÁM SỨC KHỎE CỦA NGƯỜI LÁI XE");
		      title.add(text2);
		      title.setFont(TinosBold); 
		      title.setTextAlignment(TextAlignment.CENTER);
		      title.setFontSize(12);
		      
		      // Creating a table       
		      float [] width2 = {200F, 30F, 700F, 300F};   
		      Table inforCus = new Table(width2);    
		                 
		      Cell c3 = new Cell(6,1);              
		      inforCus.addCell(c3);   
		      
		      Cell padding = new Cell(6,1).setBorder(Border.NO_BORDER);
		      inforCus.addCell(padding);     
		      
		      Cell c4 = new Cell(1,2);  
		      c4.add("Họ và tên: NGUYỄN ĐỖ TIẾN LÊN").setFontSize(9);     
		      c4.setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER);
		      inforCus.addCell(c4); 
		      
		      //Ký tự ô vuông có dấu tích
			  String square= "\u2610";// ☐
			  String checkmark = "\u2611";// ☑
			  String tick = "\u2713"; // ✓
			  Text tickIcon = new Text(tick).setFont(Noto);
		      Cell c5 = new Cell();  
		      Text t0 = new Text("Giới tính: ");
		      Text checkmarkIcon = new Text(checkmark).setFont(Noto);
		      Text t2 = new Text(" Nam ");
		      Text squareIcon = new Text(square).setFont(Noto);
		      Text t4 = new Text(" Nữ");
		      Paragraph title1 = new Paragraph();
		      title1.add(t0).add(checkmarkIcon).add(t2).add(squareIcon).add(t4).setFontSize(9);
		      title1.setPaddings(-5, 0, -5, 0).setBorder(Border.NO_BORDER);
		      //title1.setMargins(0, 0, 0, 0);
		      c5.add(title1);  
		      c5.setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER);
		      c5.setFontSize(9);  
		      inforCus.addCell(c5);   
		      
		      Cell c6 = new Cell();            
		      c6.add("Ngày sinh: 01/01/2000").setBorder(Border.NO_BORDER);  
		      c6.setTextAlignment(TextAlignment.LEFT);   
		      c6.setFontSize(9);  
		      inforCus.addCell(c6); 
		      
		      Cell c7 = new Cell();            
		      c7.add("Số CCCD hoặc hộ chiếu: 037203001955");
		      c7.setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER);   
		      c7.setFontSize(9);  
		      inforCus.addCell(c7); 
		      
		      Cell c8 = new Cell();             
		      c8.add("Cấp ngày: 01/01/2023");  
		      c8.setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER);   
		      c8.setFontSize(9);  
		      inforCus.addCell(c8); 
		      
		      Cell c9 = new Cell(1,2);  
		      c9.add("Tại Công an tỉnh Bắc Ninh").setBorder(Border.NO_BORDER);     
		      c9.setTextAlignment(TextAlignment.LEFT).setFontSize(9);
		      inforCus.addCell(c9); 
		     
		      Cell c10 = new Cell(1,2);  
		      c10.add("Chỗ ở hiện tại: Nghiêm Xá, Thị trấn Chờ, Yên Phong, Bắc Ninh");     
		      c10.setTextAlignment(TextAlignment.LEFT).setFontSize(9).setBorder(Border.NO_BORDER);
		      inforCus.addCell(c10); 
		      
		      Cell c11 = new Cell(1,2);  
		      c11.add("Đề nghị khám sức khỏe để lái xe hạng: B2");     
		      c11.setTextAlignment(TextAlignment.LEFT).setFontSize(9).setBorder(Border.NO_BORDER);
		      inforCus.addCell(c11); 
		      
		      // Creating Paragraph
		      Paragraph para1 = new Paragraph();
		      para1.add(new Text("I. TIỀN SỬ BỆNH CỦA ĐỐI TƯỢNG KHÁM SỨC KHỎE").setFont(TinosBold).setFontSize(10));
		      para1.add(new Text("\n 1. Tiền sử gia đình").setFont(TinosBold));
		      para1.add(new Text("\n Có ai trong gia đình ông (bà) mắc một trong các bệnh: truyền nhiễm, tim mạch, đái tháo đường, lao, hen phế quản, ung thư, động kinh, rối loạn tâm thần, bệnh khác: \n "));
		      para1.setTextAlignment(TextAlignment.LEFT).setFontSize(9);
		      
		      Text t5 = new Text(" a) Có ");
		      Text t6 = new Text(" b) Không ");
		      
		      Paragraph yesno = new Paragraph();
		      yesno.add(t5).add(squareIcon).add("\t").add(t6).add(checkmarkIcon).setFontSize(9);
		      yesno.setPaddings(-5, 0, -5, 0);
		      yesno.setMargins(-5, 0, -5, 0);
		      
		      Paragraph para2 = new Paragraph();
		      para2.add(new Text("Nếu \"có\", đề nghị ghi cụ thể tên bệnh: ")).setFontSize(9);
		      
		      Paragraph para3 = new Paragraph();
		      para3.add(new Text("2. Tiền sử, bệnh sử bản thân").setFont(TinosBold));
		      para3.add(new Text(": Ông (bà) đã/đang mắc bệnh, tình trạng bệnh nào sau đây không "));
		      para3.add(new Text("(Bác sỹ hỏi bệnh và đánh dấu X vào ô tương ứng)").setFont(TinosItalic));
		      para3.setMargins(-5, 0, 0, 0).setFontSize(9);
		      
		      float [] witdh3 = {350F, 30F, 30F, 30F, 350F, 30F, 30F,};   
		      Table tblQs = new Table(witdh3);  
	    	  Cell c31 = new Cell();       
		      tblQs.addCell(c31); 
		      Cell c32 = new Cell(1,2);
		      c32.add("Có/Không").setFontSize(9);
		      tblQs.addCell(c32);
		      Cell c33 = new Cell().setBorder(Border.NO_BORDER);       
		      tblQs.addCell(c33);
		      Cell c34 = new Cell();       
		      tblQs.addCell(c34); 
		      Cell c35 = new Cell(1,2);
		      c35.add("Có/Không").setFontSize(9);
		      tblQs.addCell(c35);
		      for(int i=0; i<10; i++) {
		    	  Cell q1 = new Cell(); 
		    	  q1.add(list.get(i).getName()).setFontSize(9);
			      tblQs.addCell(q1); 
			      if(list.get(i).isYesno()) {
			    	  Cell q2 = new Cell();
			    	  q2.add(new Paragraph().add(tickIcon).setMargins(0, 0, -3, 0).setTextAlignment(TextAlignment.CENTER)).setFontSize(9);
				      tblQs.addCell(q2);
				      Cell q3 = new Cell();       
				      tblQs.addCell(q3);
			      }else {
			    	  Cell q2 = new Cell();
				      tblQs.addCell(q2);
				      Cell q3 = new Cell();  
				      q3.add(new Paragraph().add(tickIcon).setMargins(0, 0, -3, 0).setTextAlignment(TextAlignment.CENTER)).setFontSize(9);
				      tblQs.addCell(q3);
			      }
			      Cell aaa = new Cell().setBorder(Border.NO_BORDER);
			      tblQs.addCell(aaa).setFontSize(9);
			      Cell q11 = new Cell(); 
		    	  q11.add(list.get(i+10).getName());
			      tblQs.addCell(q11); 
			      if(list.get(i+10).isYesno()) {
			    	  Cell q21 = new Cell();
			    	  q21.add(new Paragraph().add(tickIcon).setMargins(0, 0, -3, 0).setTextAlignment(TextAlignment.CENTER)).setFontSize(9);
				      tblQs.addCell(q21);
				      Cell q31 = new Cell();       
				      tblQs.addCell(q31);
			      }else {
			    	  Cell q22 = new Cell();
				      tblQs.addCell(q22);
				      Cell q32 = new Cell();  
				      q32.add(new Paragraph().add(tickIcon).setMargins(0, 0, -3, 0).setTextAlignment(TextAlignment.CENTER)).setFontSize(9);
				      tblQs.addCell(q32);
			      }
		      }
		      
		      Paragraph para4 = new Paragraph();
		      para4.add(new Text("Nếu \"có\", đề nghị ghi cụ thể tên bệnh: "));
		      para4.add(new Text("\n 3. Câu hỏi khác (nếu có)").setFont(TinosBold));
		      para4.add(new Text("\n a) Ông (bà) có đang điều trị bệnh gì không? Nếu có, xin hãy liệt kê các thuốc đang dùng và liều lượng:"));
		      para4.add(new Text("\n"));
		      para4.add(new Text("\n b) Hiện tại đang có thai hoặc nuôi con nhỏ dưới 12 tháng hay không? (Đối với phụ nữ):"));
		      para4.add(new Text("\n"));
		      para4.add(new Text("\n Tôi xin cam đoan những điều khai trên đây hoàn toàn đúng với sự thật. Nếu sai tôi xin chịu trách nhiệm trước pháp luật."));
		      para4.setTextAlignment(TextAlignment.LEFT).setFontSize(9);
		      
		      // Creating a table       
		      float [] colWitdh4 = {600F, 300F};   
		      Table tblFooter = new Table(colWitdh4);  
		      tblFooter.addCell(new Cell().setBorder(Border.NO_BORDER));
		      tblFooter.addCell(new Cell().add("Hà Nội, ngày 01 tháng 01 năm 2024")
		    		  .add(new Paragraph().add("Người đề nghị khám sức khỏe").setFont(TinosBold)).add("Quý").add("Nghiêm Đình Quý")
		    		  .setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(9));  
		      
		     
		      // Mục II
		      float [] colWitdh5 = {800F, 150F};   
		      Table tblPart2 = new Table(colWitdh5);  
		      
		      tblPart2.addCell(new Cell().add("Nội dung khám").setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFont(TinosBold).setFontSize(10));
		      tblPart2.addCell(new Cell().add("Họ tên, chữ ký của bác sỹ").setTextAlignment(TextAlignment.CENTER).setFont(TinosBold).setFontSize(10));
		      
		      Cell a2 = new Cell().setFontSize(10);
		      a2.add(new Paragraph("1. Tâm thần").setFont(TinosBold));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("Kết luận: "));
		      a2.add(new Paragraph("2. Thần kinh").setFont(TinosBold));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("Kết luận: "));
		      a2.add(new Paragraph("3. Mắt").setFont(TinosBold));
		      a2.add(new Paragraph("- Thị lực nhìn xa từng mắt:"));
		      a2.add(new Paragraph("+ Không kính:       Mắt trái:.......Mắt phải:......."));
		      a2.add(new Paragraph("+ Có kính:             Mắt trái:.......Mắt phải:......."));
		      a2.add(new Paragraph("- Thị lực nhìn xa hai mắt: Không kính:.......Có kính:......."));
		      a2.add(new Paragraph("- Thị trường: "));
		      float [] colEyes = {150F, 150F, 150F, 150F};   
		      Table eyes = new Table(colEyes).setTextAlignment(TextAlignment.CENTER).setMarginLeft(10).setMarginRight(10);
		      Cell eyel = new Cell(1,2).add(new Paragraph().add("Thị trường ngang hai mắt").add(("\n (chiều mũi - thái dương)")));
		      Cell eyer = new Cell(1,2).add(new Paragraph().add("Thị trường đứng").add(("\n (chiều trên - chiều dưới)")));
		      eyes.addCell(eyel).addCell(eyer);
		      eyes.addCell("Bình thường");
		      eyes.addCell("Hạn chế");
		      eyes.addCell("Bình thường");
		      eyes.addCell("Hạn chế");
		      eyes.addCell(new Paragraph().add(tickIcon).setMargins(-5, 0, -5, 0).setTextAlignment(TextAlignment.CENTER));
		      eyes.addCell("");
		      eyes.addCell(new Paragraph().add(tickIcon).setMargins(-5, 0, -5, 0).setTextAlignment(TextAlignment.CENTER));
		      eyes.addCell("");
		      a2.add(eyes);
		      a2.add(new Paragraph("- Sắc giác:"));
		      a2.add(new Paragraph("+ Bình thường   ").add(checkmarkIcon).setMargins(-5, 0, -5, 0));
		      a2.add(new Paragraph("+ Mù màu toàn bộ   \t\t").add(checkmarkIcon).add("\t Mù màu:  Đỏ   ").add(squareIcon).add(" \t Xanh lá cây   ").add(squareIcon).add(" \t  Vàng   ").add(squareIcon).setMargins(-5, 0, -5, 0));
		      a2.add(new Paragraph("- Các bệnh về mắt(nếu có):"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("Kết luận: "));
		      a2.add(new Paragraph("4. Tai - Mũi - Họng").setFont(TinosBold));
		      a2.add(new Paragraph("- Kết quả khám có trợ thính(có thể sử dụng máy trợ tính)"));
		      a2.add(new Paragraph("\t\t\t + Tai trái: \t\t Nói thường:.......m; \t\t\tNói thầm:.......m;"));
		      a2.add(new Paragraph("\t\t\t + Tai phải: \t\t Nói thường:.......m; \t\t\tNói thầm:.......m;"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("Các bệnh về tai mũi họng(nếu có):"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("Kết luận: "));
		      a2.add(new Paragraph("5. Tim mạch").setFont(TinosBold));
		      a2.add(new Paragraph("+ Mạch:....................lần/phút"));
		      a2.add(new Paragraph("+ Huyết áp:....................mmHg"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("Kết luận: "));
		      a2.add(new Paragraph("6. Hô hấp").setFont(TinosBold));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("\n"));
		      a2.add(new Paragraph("Kết luận: "));
		      Cell a3 = new Cell().setFontSize(10);
		      a3.add("\n\n\n.......................");
		      a3.add("\n\n\n..........................");
		      a3.add(new Paragraph().add(" \t").setMarginTop(8));
		      a3.add("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n..........................");
		      a3.add("\n\n\n\n\n\n\n\n\n..........................");
		      a3.add("\n\n\n\n\n..........................");
		      a3.add("\n\n\n..........................");
		      tblPart2.addCell(a2).addCell(a3);
		      
		      //Mục II trang 3
		      float [] colWitdh6 = {800F, 140F};   
		      Table tblPart3 = new Table(colWitdh6);  
		      
		      Cell b1 = new Cell().setFontSize(10);
		      b1.add(new Paragraph("7. Cơ xương khớp").setFont(TinosBold));
		      b1.add(new Paragraph("\n"));
		      b1.add(new Paragraph("\n"));
		      b1.add(new Paragraph("Kết luận: "));
		      b1.add(new Paragraph("8. Nội tiết").setFont(TinosBold));
		      b1.add(new Paragraph("\n"));
		      b1.add(new Paragraph("\n"));
		      b1.add(new Paragraph("Kết luận: "));
		      b1.add(new Paragraph("8. Thai sản").setFont(TinosBold));
		      b1.add(new Paragraph("\n"));
		      b1.add(new Paragraph("\n"));
		      b1.add(new Paragraph("Kết luận: "));
		      Cell b2 = new Cell().setFontSize(10);
		      b2.add(new Paragraph("\n\n\n............................."));
		      b2.add(new Paragraph("\n\n\n............................."));
		      b2.add(new Paragraph("\n\n\n............................."));
		      tblPart3.addCell(b1).addCell(b2);
		      
		      
		      //Mục 4
		      float [] colWitdh7 = {800F, 140F};   
		      Table tblPart4 = new Table(colWitdh7);  
		      
		      tblPart4.addCell(new Cell().add("Nội dung khám").setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFont(TinosBold).setFontSize(10));
		      tblPart4.addCell(new Cell().add("Họ tên, chữ ký của bác sỹ").setTextAlignment(TextAlignment.CENTER).setFont(TinosBold).setFontSize(10));
		      
		      Cell d1 = new Cell().setFontSize(10);
		      d1.add(new Paragraph("1. Các xét nghiệm bắt buộc").setFont(TinosBold));
		      d1.add(new Paragraph("a) Xét nghiệm ma túy"));
		      d1.add(new Paragraph("- Test Morphin/Heroin:"));
		      d1.add(new Paragraph("- Test Amphetamin:"));
		      d1.add(new Paragraph("- Test Methamphetamin:"));
		      d1.add(new Paragraph("- Test Marijuana (cần sa):"));
		      d1.add(new Paragraph("b) Xét nghiệm nồng độ cồn trong máu hoặc hơi thở:"));
		      d1.add(new Paragraph("2. Các xét nghiệm chỉ thực hiện khi có chỉ định của bác sỹ khám sức khỏe: Huyết học/sinh hóa/X.quang và các xét nghiệm khác.").setFont(TinosBold));
		      d1.add(new Paragraph("a) Kết quả:"));
		      d1.add(new Paragraph("b) Kết luận: "));
		      tblPart4.addCell(d1);
		      Cell d2 = new Cell().setFontSize(10);
		      d2.add(new Paragraph("\n\n\n\n................................."));
		      tblPart4.addCell(d2);
		      
		      Paragraph sumary = new Paragraph().add(new Text("\n KẾT LUẬN \n").setTextAlignment(TextAlignment.CENTER)).setFontSize(10);
		      sumary.add(new Text("(Giấy khám sức khỏe này có giá trị sử dụng trong vòng 06 tháng kể từ ngày ký kết luận).").setTextAlignment(TextAlignment.LEFT));
		        
		      Table tblFooter2 = new Table(colWitdh4);  
		      tblFooter2.addCell(new Cell().setBorder(Border.NO_BORDER));
		      tblFooter2.addCell(new Cell().add("Hà Nội, ngày 01 tháng 01 năm 2024")
		    		  .add(new Paragraph().add("NGƯỜI KẾT LUẬN").setFont(TinosBold)).add("Quý").add("Nghiêm Đình Quý")
		    		  .setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(10));  
		      
		      Paragraph end = new Paragraph().setFontSize(10);
		      end.add("\n 5. Kết luận sức khỏe: Ghi rõ một trong 3 tình trạng sau đây: ");
		      end.add("\n 5.1. Đủ điều kiện sức khỏe lái xe hạng");
		      end.add("\n 5.2. Không đủ điều kiện sức khỏe lái xe hạng");
		      end.add("\n 5.3. Đạt tiêu chuẩn sức khỏe lái xe hạng ..... nhưng yêu cầu khám lại (ghi cụ thể thời gian khám lại)…………………………………………");
		      end.add("\n Những trường hợp khó kết luận, đề nghị hội chẩn chuyên khoa hoặc gửi đối tượng xin khám sức khỏe lái xe ở Hội đồng GĐYK các cấp");

		      // Thêm các mục vào danh sách
		      doc.add(tbheader);                  
		      doc.add(title);   
		      doc.add(inforCus); 
		      doc.add(para1).add(yesno).add(para2);
		      doc.add(para3).add(tblQs);
		      doc.add(para4);
		      doc.add(tblFooter);
		      
		      doc.add(new Paragraph().add("II. KHÁM LÂM SÀNG").setFont(TinosBold).setFontSize(11));
		      doc.add(tblPart2);
		      
		      doc.add(tblPart3);	
		      doc.add(new Paragraph().add("III. KHÁM CẬN LÂM SÀNG").setFont(TinosBold).setFontSize(11));
		      doc.add(tblPart4);
		      doc.add(new Paragraph().add("IV. KẾT LUẬN").setFont(TinosBold).setFontSize(11));
		      doc.add(sumary);
		      doc.add(tblFooter2);
		      doc.add(end);

			// Closing the document
			doc.close();
			System.out.println("PDF created successfully..");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
