function auth(){
	var username=document.login.username.value;
//	var username=document.getElementsByName("username").value;
	var password=document.login.password.value;
	var errorMessage="";
	if(username=="" || username==null){
		errorMessage +="please enter username \n";
		document.login.username.focus();		
	}
	 if(password=="" || password==null){
		errorMessage +="please enter password";
	document.login.password.focus();	
}
if(errorMessage!="")
	{
		alert(errorMessage);
		return false;
	}
}
