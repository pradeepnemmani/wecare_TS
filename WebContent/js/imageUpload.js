function saveImage() {
	var imageName=document.registration.fileName.value;
	 $.ajax({
     	type:"GET",
     	url:"SaveImage",
     	dataType:"json",
     	data:{imageurl:imageName},
     	success:function(data)
     	{
     		alert("success");
     	}
     });
}