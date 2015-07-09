function validimage() {
	var imageAddress=document.imageupload.fileName.value;
	var errorMessage=null;
	if(imageAddress!=null && imageAddress!="")
		{
			var arr= imageAddress.toString().split(".");
			if(arr!=null )
				{
					var imageFormat=arr[1].toLowerCase(); 
					if(imageFormat=="jpg" ||imageFormat=="jpeg" || imageFormat=="bmp" || imageFormat=="gif" || imageFormat=="png")
						{
							return true;
						}
						else {
							errorMessage="Unsuppoted file format..!";
							}
						
				}	
		}
	
	else {
		errorMessage="Please browse the imagefile..!";
	}
	
	if(errorMessage!=null)
	{
	alert(errorMessage);
	}return false;
}