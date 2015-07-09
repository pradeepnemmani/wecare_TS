function topicValidater()
{
	var topicName=document.topic.name.value;
	var topicComment=document.topic.comment.value;
	var errorMessage="";
	if(topicName=="" || topicName.length==0)
		{
			errorMessage +="Please Enter the topic Name  \n";
		}
	else if(topicName.length>200)
		{
			errorMessage +="topic Name should not exceed more than 45 characters";
		}
	if(topicComment=="" || topicComment.length==0)
	{
		errorMessage +="Please Enter the topic comment  \n";
	}
	else if(topicComment.length>2000)
	{
		errorMessage +="Topic Comment should not exceed more than 45 characters";
	}
	if(errorMessage!="")
		{
			alert(errorMessage);
			return false;
		}
	}