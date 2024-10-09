package jsoft.ads.testpdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

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
import com.itextpdf.text.FontFactory;

/**
 * Servlet implementation class ItextPDF
 */
@WebServlet("/pdf")
public class ItextPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItextPDF() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.setContentType("application/pdf");
		  // Creating a PdfWriter       
	      // String dest = "D:/TestReport/sample1.pdf";       
	      PdfWriter writer = new PdfWriter(response.getOutputStream()); 
	   
	      // Creating a PdfDocument object      
	      PdfDocument pdf = new PdfDocument(writer);                  
	      
	      // Creating a Document object       
	      Document doc = new Document(pdf);      
	      String fontUrlBold = "http://localhost:8080/btl/font/Tinos/Tinos-Bold.ttf";
	      String fontUrlRegular = "http://localhost:8080/btl/font/Tinos/Tinos-Regular.ttf";
	        
	      File tempBoldFontFile = downloadFontFromURL(fontUrlBold);
          File tempRegularFontFile = downloadFontFromURL(fontUrlRegular);
          
	      // Setting font of the text       
	      PdfFont TinosBold = PdfFontFactory.createFont(tempBoldFontFile.getAbsolutePath(), PdfEncodings.IDENTITY_H, true);       
	      PdfFont TinosRegular = PdfFontFactory.createFont(tempRegularFontFile.getAbsolutePath(), PdfEncodings.IDENTITY_H, true);

	      doc.setFont(TinosRegular);  
	      // Creating a table       
	      float [] pointColumnWidths = {500F, 500F};   
	      Table tbheader = new Table(pointColumnWidths);    
	      
	      // Populating row 1 and adding it to the table               
	      Cell c1 = new Cell();                       
	      c1.add("BỘ Y TẾ");     
	      c1.add("BỆNH VIỆN QUỐC TẾ VIỆT ĐỨC");  
	      // Setting background color
	      c1.setFont(TinosBold); 
	      c1.setBorder(Border.NO_BORDER);              
	      c1.setTextAlignment(TextAlignment.CENTER);       
	      tbheader.addCell(c1);                           
	      
	      Cell c2 = new Cell();                               
	      c2.add("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");  
	      c2.add("Độc lập - Tự do - Hạnh phúc");  
	      c2.setFont(TinosBold); 
	      c2.add("______________");  
	      c2.setBorder(Border.NO_BORDER);       
	      c2.setTextAlignment(TextAlignment.CENTER);      
	      tbheader.addCell(c2);    
	      
	      // Creating Paragraph
	      Paragraph title = new Paragraph();
	      Text text2 = new Text("HÓA ĐƠN KHÁM BỆNH");
	      title.add(text2);
	      title.setFont(TinosBold); 
	      title.setTextAlignment(TextAlignment.CENTER);
	      title.setFontSize(18);
	      
	      // Creating a table       
	      float [] pointColumnWidths2 = {600F, 500F};   
	      Table inforCus = new Table(pointColumnWidths2);    
	      
	      // Populating row 1 and adding it to the table               
	      Cell c3 = new Cell();                       
	      c3.add("Tên khách hàng: Nguyễn Văn Test");     
	      c3.add("Ngày sinh: 01/01/2000" );  
	      // Setting background color
	      c3.setBorder(Border.NO_BORDER);              
	      c3.setTextAlignment(TextAlignment.LEFT);       
	      inforCus.addCell(c3);                           
	      
	      Cell c4 = new Cell();                               
	      c4.add("Giới tính: Nam");  
	      c4.add("Số điện thoại: 0987563245");  
	      c4.setBorder(Border.NO_BORDER);       
	      c4.setTextAlignment(TextAlignment.LEFT);      
	      inforCus.addCell(c4);   
	      
	      Cell c56 = new Cell(1,2);                               
	      c56.add("Địa chỉ: Thị trấn Chờ - Yên Phong - Bắc Ninh");  
	      c56.setBorder(Border.NO_BORDER);       
	      c56.setTextAlignment(TextAlignment.LEFT);   
	      inforCus.addCell(c56); 
	      
	      Cell c7 = new Cell();                               
	      c7.add("Hình thức thanh toán: Chuyển khoản");
	      c7.setBorder(Border.NO_BORDER);   
	      c7.setTextAlignment(TextAlignment.LEFT);      
	      inforCus.addCell(c7);  
	      
	      Cell c8 = new Cell();                          
	      c8.add("Số tài khoản: 0987563245");
	      c8.setBorder(Border.NO_BORDER);   
	      c8.setTextAlignment(TextAlignment.LEFT);      
	      inforCus.addCell(c8); 
	      
	      Cell c910 = new Cell(1,2);                           
	      c910.add("Ngân hàng: Ngân hàng Quân đội MB");  
	      c910.setBorder(Border.NO_BORDER);   
	      c910.setTextAlignment(TextAlignment.LEFT);      
	      inforCus.addCell(c910);  
	      
	      // Creating a table       
	      float [] pointColumnWidths3 = {50F, 500F, 100F, 150F, 150F, 200F};   
	      Table tblSer = new Table(pointColumnWidths3);  
	      
	      tblSer.addCell(new Cell().add("STT").setTextAlignment(TextAlignment.CENTER));
	      tblSer.addCell(new Cell().add("Tên dịch vụ").setTextAlignment(TextAlignment.LEFT));
	      tblSer.addCell(new Cell().add("Số lượng").setTextAlignment(TextAlignment.CENTER));
	      tblSer.addCell(new Cell().add("Đơn giá").setTextAlignment(TextAlignment.RIGHT));
	      tblSer.addCell(new Cell().add("Khuyến mại").setTextAlignment(TextAlignment.RIGHT));
	      tblSer.addCell(new Cell().add("Thành tiền").setTextAlignment(TextAlignment.RIGHT));    
	      
	      for(int i=0; i < 6; i++) {
		      tblSer.addCell(new Cell().add(i+"").setTextAlignment(TextAlignment.CENTER));
		      tblSer.addCell(new Cell().add("Khám răng miệng tổng quát").setTextAlignment(TextAlignment.LEFT));
		      tblSer.addCell(new Cell().add(i+"").setTextAlignment(TextAlignment.CENTER));
		      tblSer.addCell(new Cell().add("100.000").setTextAlignment(TextAlignment.RIGHT));
		      tblSer.addCell(new Cell().add("10%").setTextAlignment(TextAlignment.RIGHT));
		      tblSer.addCell(new Cell().add("600.000").setTextAlignment(TextAlignment.RIGHT)); 
	      }
	      
	      tblSer.addCell(new Cell(1,5).add("Tổng tiền").setTextAlignment(TextAlignment.RIGHT)); 
	      tblSer.addCell(new Cell().add("6.0000.000").setTextAlignment(TextAlignment.RIGHT));
	      
	      tblSer.addCell(new Cell(1,6).add("Bằng chữ: Một triệu không trăm ba mươi năm nghìn đồng").setTextAlignment(TextAlignment.LEFT)); 
	      
	      // Creating a table       
	      float [] colWitdh4 = {400F, 600F};   
	      Table tblFooter = new Table(colWitdh4);  
	      tblFooter.addCell(new Cell().add("Người thanh toán").add("(Ký, ghi rõ họ tên)").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
	      tblFooter.addCell(new Cell().add("Hà Nội, Ngày 01 tháng 01 năm 2024").add("Người lập hóa đơn").add("Quý").add("Nghiêm Đình Quý").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));  
	      
	      // Adding Table to document        
	      doc.add(tbheader);                  
	      doc.add(title);   
	      doc.add(inforCus); 
	      doc.add(tblSer);
	      doc.add(new Paragraph("\n"));
	      doc.add(tblFooter);
	      
	      // Closing the document       
	      doc.close();
	      tempRegularFontFile.delete();
	      tempBoldFontFile.delete();
	      System.out.println("Thành công");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
