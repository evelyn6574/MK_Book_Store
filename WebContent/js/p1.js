//-----------------billing address-----------------------
var state=[
     ["Ontario","Quebec","Nova Scotia","New Brunswick","Manitoba","British Columbia","Prince Edward Island","Saskatchewan","Alberta","Newfoundland and Labrador" ],
     ["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","Nevada","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio,Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","Washington","Wisconsin","Wyoming","District of Columbia"],
     ];

     function getState(){
         
         var sltCountry=document.form1.country;   
         var sltState=document.form1.state;            
         var countryState=state[sltCountry.selectedIndex - 1];          
         sltState.length=1;         
         for(var i=0;i<countryState.length;i++){
             sltState[i+1]=new Option(countryState[i],countryState[i]);
         }
     }
    
//------------------shippinging address---------------------    

	var sstate=[
	           ["Ontario","Quebec","Nova Scotia","New Brunswick","Manitoba","British Columbia","Prince Edward Island","Saskatchewan","Alberta","Newfoundland and Labrador" ],
	           ["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","Nevada","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio,Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","Washington","Wisconsin","Wyoming","District of Columbia"],
	           ];

	function sgetState(){
					
	               var sltCountry=document.form1.scountry; 	         
	               var sltState=document.form1.sstate; 	               
	               var countryState=sstate[sltCountry.selectedIndex - 1]; 	               
	               sltState.length=1;         
	               for(var i=0;i<countryState.length;i++){
	                   sltState[i+1]=new Option(countryState[i],countryState[i]);
	               }
	           }
	

//-------------shipping addres is as the same as billing address----------

	function sameadrs(){
		if(document.getElementById("yes").checked == true){
			
			var cName = document.getElementById("cardHolder").value; // name form credit card
			var nameSplit = cName.split(" ");
			//first name for the shipping address
			var sfToString="";
			for(var i=0;i<nameSplit.length - 1;i++){
				sfToString=sfToString+nameSplit[i].toString() + " ";
			}			
			document.getElementById("sfname").value=sfToString;
			document.getElementById("sfname").readOnly=true; 
			
			
			//last name for the shipping address
			var slToString="";
			slToString=nameSplit[nameSplit.length - 1].toString();	
			document.getElementById("slname").value=slToString;
			document.getElementById("slname").readOnly = true;	
			
			//the same address
			var a = document.getElementById("adrs").value;
			document.getElementById("sadrs").value=a;
			document.getElementById("sadrs").readOnly=true;
			
			//the same phone number
			var p = document.getElementById("phone").value;
			document.getElementById("sphone").value=p;
			document.getElementById("sphone").readOnly=true;
			
			//the same zip
			var e = document.getElementById("zip").value;
			document.getElementById("szip").value=e;
			document.getElementById("szip").readOnly=true;
			//the same contry
			var c = document.getElementById("country").value;
			document.getElementById("scountry").value=c;
			document.getElementById("scountry").readOnly=true;
			sgetState();	
			//the same state
			var s = document.getElementById("state").value;
			document.getElementById("sstate").value=s;	
			document.getElementById("sstate").options[document.getElementById("sstate").selectedIndex].text=document.getElementById("state").options[document.getElementById("state").selectedIndex].text;
			document.getElementById("sstate").readOnly=true;
		}
		else{
			document.getElementById("sfname").readOnly=false;
			document.getElementById("slname").readOnly=false;
			document.getElementById("sadrs").readOnly=false;
			document.getElementById("sphone").readOnly=false;
			document.getElementById("szip").readOnly=false;
			document.getElementById("scountry").readOnly=false;
			document.getElementById("sstate").readOnly=false;	
		}	
	}
	
//check if the billing phone number is 10 digits
	function checkPhone(){
		//alert("this is a test");
		var reg = /^[0-9]{10,10}$/;
		var flag = reg.test(document.getElementById("phone").value);
		if(!flag){
			alert("Phone Number must be 10 digits!");
		}
	}
//check if the shipping phone number is 10digits
	function checkSPhone(){
		var reg = /^[0-9]{10,10}$/;
		var flag = reg.test(document.getElementById("sphone").value);
		if(!flag){
			alert("Phone Number must be 10 digits!");
		}
	}
//check if the zip code is 6 digits with letter	
	function checkZip(){
		var reg =  /^[a-zA-Z0-9]{6,6}$/;
		var flag = reg.test(document.getElementById("zip").value);
		if(!flag){
			alert("ZIP CODE must be 6 digits with numbers and letters!");
		}		
	}
//check if the shipping zip code is 6 digits with letter
	function checkSZip(){
		var reg =  /^[a-zA-Z0-9]{6,6}$/;
		var flag = reg.test(document.getElementById("szip").value);
		if(!flag){
			alert("ZIP CODE must be 6 digits with numbers and letters!");
		}		
	}
	
	//check if the cardholder is all letters
	function checkCardHolder(){			
		var reg = /^[a-zA-Z]+$/;
		var flag = reg.test(document.getElementById("cardHolder").value);
		if(!flag){
			alert("CardHolder must to be all letters!");
		}
	}

//check if the card number is digits
	function checkCardNumber(){
		var reg16 = /^[0-9]{16,16}$/;
		var reg15 = /^[0-9]{15,15}$/;		
		var type = document.getElementById("cardType");
		if(type.value=="1" || type.value=="2"){
			var flag16 = reg16.test(document.getElementById("cardNumber").value);
			if(!flag16 && type.value=="1"){
				alert("VISA Card Number must be 16 digits!");
			}
			else if(!flag16 && type.value=="2"){
				alert("MASTER Card Number must be 16 digits!");
			}
		}
		else{
			var flag15 = reg15.test(document.getElementById("cardNumber").value);
			if(!flag15 && type.value =="3"){
				alert("AMERICAN EXPRESS Card Number must be 15 digits!")
			}
		}
		
	}

//check if the cvc is all digits
	function checkCVC(){
		var reg3 = /^[0-9]{3,3}$/;
		var reg4 =/^[0-9]{4,4}$/;
		
		var type = document.getElementById("cardType"); 
		if(type.value == "1" || type.value == "2"){
			var digit3 = reg3.test(document.getElementById("cvc").value);
			if(!digit3){
				alert("cvc must be 3 digits!");
			}
		}
		else{
			var digit4 = reg3.test(document.getElementById("cvc").value);
			if(!digit4){
				alert("cvc must be 4 digits!")
			}
		}		
	}
	
	function checkAll(){
		//check the billling phone
		var regphone = /^[0-9]{10,10}$/;
		var flagphone = regphone.test(document.getElementById("phone").value);
		//shipping phone
		var regsphone = /^[0-9]{10,10}$/;
		var flagsphone = regsphone.test(document.getElementById("sphone").value);
		//billing zip code
		var regzip =  /^([a-zA-Z0-9]){6,6}$/;
		var flagzip = regzip.test(document.getElementById("zip").value);
		//shipping zip code
		var regszip =  /^([a-zA-Z0-9]){6,6}$/;
		var flagszip = regszip.test(document.getElementById("szip").value);
		//cardHolder
		var regholder = /^[a-zA-Z]+$/;
		var flagholder = regholder.test(document.getElementById("cardHolder").value);
		//card number
		var reg16 = /^[0-9]{16,16}$/;
		var reg15 = /^[0-9]{15,15}$/;		
		var type = document.getElementById("cardType");
		if(type.value=="1" || type.value=="2"){
			var flag16 = reg16.test(document.getElementById("cardNumber").value);
		}
		else{
			var flag15 = reg15.test(document.getElementById("cardNumber").value);
		}
		//check cvc
		var reg3 = /^[0-9]{3,3}$/;
		var reg4 =/^[0-9]{4,4}$/;
		if(type.value == "1" || type.value == "2"){
			var digit3 = reg3.test(document.getElementById("cvc").value);
		}
		else{
			var digit4 = reg3.test(document.getElementById("cvc").value);
		}	
		if(!flagphone||!flagsphone||!flagzip||!flagszip||!flagholder||(!flag16 && type.value=="1")||(!flag16 && type.value=="2")||(!flag15 && type.value =="3")||!digit3||!digit4){
			alert("please check the input information!");
		}		
		
		
	}
	
	
	/*
	function test(){
		alert("the shipping first name is:"+ document.getElementById("sfname").value);
	}
	*/
	
	function disShiping(){
		
		if(document.getElementById("previous").value != "0"){
			document.getElementById("sfname").disabled = true; 
			document.getElementById("slname").disabled = true;	
			document.getElementById("sadrs").disabled=true;
			document.getElementById("sphone").disabled=true;
			document.getElementById("szip").disabled=true;
			document.getElementById("scountry").disabled=true;
			document.getElementById("sstate").disabled=true;
		}
		else{
			document.getElementById("sfname").disabled = false;
			document.getElementById("slname").disabled = false;
			document.getElementById("sadrs").disabled = false;
			document.getElementById("sphone").disabled = false;
			document.getElementById("szip").disabled = false;
			document.getElementById("scountry").disabled = false;
			document.getElementById("sstate").disabled = false;	
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	