//check  if the password has minimun 6 character with just letter and number,by a paramter
function checkPWD(){
		var reg = /^[a-zA-Z0-9]{6,}$/;
		var flag = reg.test(document.getElementById("pwd").value);
		if(!flag){
			alert("Minimum of 6 characters with only letters or/and number")
		}
}
//check if the confirm password is as the same as the previous password
function confirmPWD(){
		var pwd = document.getElementById("pwd").value;
		var cfmpws = document.getElementById("cfmpws").value;
		if(pwd != cfmpws)
			alert("Password is not the same!"); 
	}

/*
function checkregister(){
	var regphone = /\d{10}/;
	var flagphone = regphone.test(document.getElementById("phone").value);
	//shipping phone
	var regsphone = /\d{10}/;
	var flagsphone = regsphone.test(document.getElementById("sphone").value);
	//billing zip code
	var regzip =  /([a-zA-Z0-9]){6}$/;
	var flagzip = regzip.test(document.getElementById("zip").value);
	//shipping zip code
	var regszip =  /([a-zA-Z0-9]){6}$/;
	var flagszip = regszip.test(document.getElementById("szip").value);
	if(!flagphone||!flagsphone||!flagzip||!flagszip){
		alert("please check the input information!");
	}
}
*/