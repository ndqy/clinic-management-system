/*Các xử lý kịch bản cho loginV3.html*/

function checkValidLogin(){
	// Tham chiếu lấy giá trị
	let name = document.getElementById('name').value;
	let pass = document.getElementById('pass').value;
	
	// Tham chiếu đối tượng hiển thị lỗi
	let viewErrName = document.getElementById('errName');
	let viewErrPass = document.getElementById('errPass');
	
	// Khai báo biến xác nhận hợp lệ
	var validName = true;
	var validPass = true;
	
	// Khai báo biến ghi nhận thông báo
	var message = "";
	
	// Kiểm tra name
	name = name.trim();
	if(name==""){
		validName = false;
		message = "Thiếu tên / hộp thư đăng nhập cho tài khoản"
	}else{
		if((name.length<5) || (name.length>50)){
			validName = false;
			message = "Tên đang nhập / hộp thư quá ngắn hoặc quá dài"
		}else{
			
			if(name.indexOf(" ")!=-1){
				validName = false;
				message = "Tên đăng nhập / hộp thư không có dấu cách.";
			}else{
				if(name.indexOf("@")!=-1){
					var parttern = /\w+@+\w+[.]+\w/;///\w+@+\w+[.]+\w/
					
					if(!name.match(parttern)){
						validName = false;
						message = "Không đúng cấu trúc hộp thư"
					}
				}
			}
		}
	}
	
	// Thông báo lỗi name
	viewErrName.style.padding = "8px";
	viewErrName.style.marginTop = "8px";
	if(validName){
		viewErrName.innerHTML = '<i class="fa-solid fa-check"></i>';
		viewErrName.style.backgroundColor = "transparent";//background-color
		viewErrName.style.color = "blue";//color
	}else{
		viewErrName.innerHTML = message;
		viewErrName.style.backgroundColor = "red";
		viewErrName.style.color = "yellow";
	}


	//Kiểm tra pass
	pass = pass.trim();
	if(pass==""){
		validPass = false;
		message = "Thiếu mật khẩu để đăng nhập";
	}else{
		if(pass.length<6){
			validPass = false;
			message = "Mật khẩu không hợp lệ";
		}else{
			var pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*]).{8,}$/;
			if(!pass.match(pattern)){
				validPass = false;
				message = "Mật khẩu cần có chữ hoa, chữ thường, số và ký tự đặc biệt";
			}
		}
	}
	
	//Thông báo lỗi pass
	viewErrPass.style.padding = "8px";
	viewErrPass.style.marginTop = "8px";
	if(validPass){
		viewErrPass.innerHTML = '<i class="fa-solid fa-check"></i>';
		viewErrPass.style.backgroundColor = "transparent";//background-color
		viewErrPass.style.color = "blue";//color
	}else{
		viewErrPass.innerHTML = message;
		viewErrPass.style.backgroundColor = "red";
		viewErrPass.style.color = "yellow";
	}
	return validName && validPass;

}
//Nhập đầy đủ thông tin thì nút đăng nhập mới hiện