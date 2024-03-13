package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Error
 */
@WebServlet("/error")
public class Error extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Error() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String err = request.getParameter("err");
		if (err != null) {
			String uc = err.substring(0, 2);
			String name = err.substring(2);
			String title;
			String content;
			switch(uc) {
				case "lg":
					title = "Có lỗi khi đăng nhập!";
					switch(name) {
						case "param":
							content = "Tên đăng nhập hoặc mật khẩu bị thiếu.";
							break;
						case "value":
							content = "Tên đăng nhập hoặc mật khẩu không được để trống!";
							break;
						case "notok":
							content = "Tên đăng nhập hoặc mật khẩu không chính xác!";
							break;
						default: content = "Có lỗi, vui lòng kiểm tra lại.";
					}
					
					break;
				case "ur":
					title = "Có lỗi tại Modul Người sử dụng!";
					switch(name) {
						case "add":
							content ="Thêm mới người sử dụng thất bại.";
							break;
						case "null":
							content ="Nhập thiếu thông tin.";
							break;
						case "edit":
							content ="Có lỗi khi cập nhật thông tin người sử dụng.";
							break;
						case "nopermis":
							content ="Tài khoản không có quyền thực hiện yêu cầu.";
							break;
						case "notoke":
							content ="Không tìm thấy form sửa.";
							break;
						case "permis":
							content ="Lỗi khi cập nhật quyền tài khoản.";
							break;	
						default: 
							content ="Có lỗi, vui lòng kiểm tra lại";
							
						}
					break;
				case "cl":
					title = "Có lỗi tại Modul Lịch khám!";
					switch(name) {
						case "add":
							content ="Thêm mới lịch khám thất bại.";
							break;
						case "addcus":
							content ="Thêm mới khách hàng thất bại.";
							break;
						case "edit":
							content ="Có lỗi khi cập nhật thông tin lịch khám.";
							break;
						case "nopermis":
							content ="Tài khoản không có quyền thực hiện yêu cầu.";
							break;
						case "null":
							content ="Chưa nhập đầy đủ thông tin lịch khám";
							break;
						case "acclogin":
							content ="Người dùng chưa đăng nhập.";
							break;	
						case "del":
							content ="Lỗi khi xóa người dùng.";
							break;
						default: 
							content ="Có lỗi, vui lòng kiểm tra lại";
						}
					break;
				case "cs":
					title = "Có lỗi tại Modul Khách hàng!";
					switch(name) {
						case "add":
							content ="Thêm mới khách hàng thất bại do trùng SỐ ĐIỆN THOẠI.";
							break;
						case "null":
							content ="Nhập thiếu thông tin.";
							break;
						case "edit":
							content ="Có lỗi khi cập nhật thông tin khách hàng.";
							break;
						case "del":
							content ="Có lỗi khi xóa khách hàng.";
							break;
						case "notlogin":
							content ="Tài khoản không có quyền thực hiện yêu cầu.";
							break;	
						default: 
							content ="Có lỗi, vui lòng kiểm tra lại";
							
						}
					break;
				case "sv":
					title = "Có lỗi tại Modul Dịch vụ!";
					switch(name) {
						case "add":
							content ="Thêm mới dịch vụ thất bại.";
							break;
						case "null":
							content ="Nhập thiếu thông tin.";
							break;
						case "edit":
							content ="Có lỗi khi cập nhật thông tin dịch vụ.";
							break;
						case "del":
							content ="Có lỗi khi xóa dịch vụ.";
							break;
						case "notlogin":
							content ="Tài khoản không có quyền thực hiện yêu cầu.";
							break;	
						default: 
							content ="Có lỗi, vui lòng kiểm tra lại";
							
						}
					break;
				case "lo":
					title = "Có lỗi khi ghi lại hành động!";
					switch(name) {
						case "add":
							content ="Ghi lịch sử làm việc thất bại.";
							break;
						default: 
							content ="Có lỗi, vui lòng kiểm tra lại";
							
						}
					break;
				default: 
					title = "Có lỗi ";
					content = "Có lỗi, vui lòng kiểm tra lại";	
			}
			
			out.append("<div class=\"toast-container position-fixed bottom-0 end-0 p-3 \">");
			  out.append("<div id=\"liveToast\" class=\"toast border-0\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">");
				out.append("<div class=\"toast-header text-danger border-1\" style=\"background: #f1b7c1; border-color: #b03d50\">");
				  out.append("<strong class=\"me-auto\"><i class=\"bi bi-exclamation-triangle-fill\"></i> "+title+"</strong>");
				  //out.append("<small>11 mins ago</small>");
				  out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" style=\"background-size: 50%\" aria-label=\"Close\"></button>");
				out.append("</div>");
				out.append("<div class=\"toast-body text-danger\" style=\"background: #f1b7c1\" >");
				out.append(content);
			
				out.append("</div>");
				out.append("</div>");
			out.append("</div>");
			
			out.append("<script language=\"javascript\"> ");
				out.append(" window.onload = (event) => { ");
				out.append(" var toastLiveExample = document.getElementById('liveToast'); ");
				out.append(" var toast = new bootstrap.Toast(toastLiveExample); ");
				out.append(" toast.show(); ");
				out.append("}");
			out.append("</script> ");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
