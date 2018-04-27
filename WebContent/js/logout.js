/**
 * 	For logout.jspx only.
 */

function varifyLogout(){
    	var xhttp = new XMLHttpRequest();
    	xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4) {
				alert(xhttp.responseText);
			}
	    		  
	    }
   	 xhttp.open("POST", "test", true);
   	 xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//only post
   	 xhttp.send("username=" + $("#login_username").val() +"&password=" + $("#login_password").val());
    }