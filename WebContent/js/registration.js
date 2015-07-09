function checkForValidation()
{
	var errorMessage=null;
	var username=document.registration.username.value;
	var password=document.registration.password.value;
	var name=document.registration.name.value;
	var gender=document.registration.gender.value;
	var city=document.registration.city.value;
	if(username=="" || username==null)
		{
			errorMessage +="please Enter User Name \n";
		}
	if(password=="" || password==null)
		{
		errorMessage +="please Enter password \n";
		}
	if(name=="" || name==null)
		{
		errorMessage +="please Enter name \n";
		}
	if(gender=="" || gender==null)
		{
		errorMessage +="please Select gender \n";
		}
	if(city=="" || city==null)
		{
		errorMessage +="please Select city \n";
		}
	if(errorMessage!=null)
		{
		alert(errorMessage);
		return false;
		}
	}