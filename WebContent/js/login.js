/**
 * 	For login.japx only.
 */

function varifyLogin(){
    	var xhttp = new XMLHttpRequest();
    	xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4) {
				alert(xhttp.responseText);
			}
	    		  
	    }
   	 xhttp.open("POST", "Login", true);
   	 xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   	 xhttp.send("username=" + $("#login_username").val() +"&password=" + $("#login_password").val());
    }

function updateModal(bid, title, price){
		var xhttp = new XMLHttpRequest();
    	xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4) {
				$("#dm_img").attr("src", "image/book_cover/" + bid + ".jpg");
				$("#dm_title").text(title);
				$("#submit_bid").val(bid);
				$("#addToCart").val(bid);
				$("#dm_price").text("$" + price + ".00");
				var size = xhttp.getResponseHeader("reviews_size");
				$("#reviewList").html("");
				if(size == 0)
					$("#reviewList").append("<div class=\"panel panel-default\">" +
							"<div class=\"panel-body\">" +
							"No comments yet. Please write a review!" +
							"</div>" +
							"<div class=\"panel-footer\">" +
							"</div>" +
						"</div>");
				else
				for(i = 0; i < size; i++){
					var review = xhttp.getResponseHeader("review" + i).split(",");
					$("#reviewList").append("<div class=\"panel panel-default\">" +
													"<div class=\"panel-body\">" +
													review[1] +
													"</div>" +
													"<div class=\"panel-footer\">" +
													review[2] +
													"</div>" +
												"</div>");
				}
			}	
    	}
    	xhttp.open("GET", "DetailModalCtrl?bid=" + bid, true); 
  	  xhttp.send();
}
function submitReview(user){
	if(user == ''){
		alert("Sorry, please sign in before write a review!");
		return false;
	}
	
	else if(!($("#star1").prop("checked") ||
			$("#star2").prop("checked") ||
			$("#star3").prop("checked") ||
			$("#star4").prop("checked") ||
			$("#star5").prop("checked"))){
		alert("Please give a rating before submit!");
		return false;
	}
	
	
	else if($("#customerreview").val().indexOf("<") >= 0){
		alert("Invalid review!");
		return false;
	}
	
	else if($("#customerreview").val() == ""){
		alert("Invalid review!");
		return false;
	}
	
	else if($("#customerreview").val() == "please write a review"){
		alert("Invalid review!");
		return false;
	}
	
	else{
		return true;
	}
	

}


function validateInput(){
	var someEmpty = $('.inputTextField').filter(function(){
	    return $.trim(this.value).length === 0;
	}).length > 0;
	
	if(someEmpty){
		alert("Please fill out all information before submit!");
		return false;
	}
	else
		return true;
}