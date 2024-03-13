function bodau(str) {
	str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
	str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
	str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
	str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
	str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
	str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
	str = str.replace(/đ/g, "d");
	str = str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'|\"|\&|\#|\[|\]|~|\$|_|`|-|{|}|\||\\/g, " ");
	str = str.replace(/  +/g, ' ');
	return str;
}
function nhap(){
	var chuoi = document.getElementById("fullName").value;
	chuoi = chuoi.toLowerCase().trim();
	chuoi = bodau(chuoi);
	var arr = [];
	arr = chuoi.split(" ");
	var dau="";
	for(var i = 0; i<arr.length-1; i++){
		dau += arr[i].charAt(0);
	}
	
	var ketqua;
	if(dau!=null){
		ketqua = chuoi.slice(chuoi.lastIndexOf(" ")+1)+dau;
	}else{
		ketqua = chuoi.slice(chuoi.lastIndexOf(" ")+1);
	}
	
	document.getElementById("alias").value = ketqua;
	}