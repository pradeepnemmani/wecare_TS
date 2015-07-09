<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Topic</title>
<link rel="stylesheet" href="css/cssLayout.css" type="text/css"/>
<link rel="stylesheet" href="css/default.css" type="text/css"/>
<link rel="stylesheet" href="css/search&uploadbar.css" type="text/css"/>
<script type="text/javascript" src="js/topicvalidater.js">
</script>
<script type="text/javascript" src="js/searchtopic.js"></script>
<script type="text/javascript" src="js/filevalidate.js"></script>

</head>
<body>
	<div id="top" align="center">
	  	<img src="img/wecare.png" width=800px height=100px>      
	    <img src="img/images.jpg" height=100px/>
   </div>
	<div id="left">
        <h3><a href="MyTopics">My topic</a></h3>
         <h3><a href="DownloadFile">Downloads</a></h3>
         <% 	if(request.getSession().getAttribute("admin")!=null)
               	{
               		if(((Boolean)request.getSession().getAttribute("admin")).booleanValue())
               		{
	                	
	                		%>
	                			 <h3><a href="addevent.jsp">Add Event</a></h3>
	             			     <h3><a href="Events">Events</a></h3>
	             			     <h3><a href="Uploads">Uploads</a></h3>
        
	                		<%
	          		}
               	}
          %>
               <h3><a href="Signout">Sign Out</a></h3>
    </div>
    <div id="content" class="left_content"> 
    <div class="search-upload-container">
           <table>
            <tr>
            	<td>
            	
            		<form action="SearchTopic" name="searchtopic">
			            <label for="topic">Topic:</label>
			            <input type="text" id="topic" name="topic"/>
			            <button type="submit" name="submit" value="SEARCH" onclick="return searchTopicValidator()">SEARCH</button>
			        </form>   
                    <%
               	if(request.getSession().getAttribute("admin")!=null)
               	{
               		if(((Boolean)request.getSession().getAttribute("admin")).booleanValue())
               		{
	                	%>		
	                		 <form action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data" name="fileupload">
								Select File to Upload:<input type="file" name="fileName">
								<button type="submit" value="Upload" onclick=" return filevalidate()">UPLOAD</button>
							</form>	
							              		                			
	                	<%
	          		}
               	}
                	%>  
                </td>
           	 </tr>    
            </table>
            
           </div>	 
          <hr/>       
    <h1 style="text-align: center;">Add Topic</h1>
    <%
    	String message=(String) request.getParameter("msg");
    %>
    <div>
    	<form action="Addtopic" name="topic">
    	<h2>Topic Name</h2>
    	<table>
    		<tr><td>Name:</td><td><input type="text" name="name" id="name"/></td></tr>
    		<tr><td>Comment:</td><td><textarea  id="comment"  name="comment" rows="6" cols="45"></textarea></td></tr>
    	</table>  
    	 <input type="submit" value="Add" onclick="topicValidater()">
             <input type="reset" value="Clear">	 		
    	</form>
    </div>
    </div>
     <div id="bottom" align="center">
            copyrights Talentsprint 2014
        </div>
</body>
</html>