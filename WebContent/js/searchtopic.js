function searchTopicValidator()
{
	var message=null;
	var topicName=document.searchtopic.topic.value;
	if( topicName=="" || topicName==null)
		{
			message="Please Enter the Topic Name to be search...!";
		}
	if(message!=null)
		{
			alert(message);
			return false;
		}
			
	}