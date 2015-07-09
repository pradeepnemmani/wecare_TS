<%@page import="com.model.Topic"%>
<%@page import="com.model.Event"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Topic</title>
<link rel="stylesheet" href="css/cssLayout.css" type="text/css"/>
<link rel="stylesheet" href="css/default.css" type="text/css"/>
<link rel="stylesheet" href="css/search&uploadbar.css" type="text/css"/>
<script type="text/javascript" src="js/filevalidate.js"></script>
</head>
<body>
	<div id="top" align="center">
	  	<img src="img/wecare.png" width=800px height=100px>      
	    <img src="img/images.jpg" height=100px/>
   </div>
	<div id="left">
        <h3><a href="MyTopics">My topic</a></h3>
        <h3><a href="addtopic1.jsp">Add topic</a></h3>
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
         <h3><a href="DownloadFile">Downloads</a></h3>
        <h3><a href="Signout">Sign out</a></h3>
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
    <h1 style="text-align: center;">Edit Event</h1>
    <%
    	String message=(String) request.getParameter("msg");
    	Topic topic=(Topic) request.getAttribute("topic");
    %>
    <div>
    	<form action="EditTopic" method="post">
    	<h2>Event Details</h2>
    	<table>
    		<tr><td>Name:</td><td><input type="text" name="name" id="name" value=<%=topic.getTname() %>></input></td></tr>
    		<tr><td>Comment:</td><td><textarea  id="comment"  name="comment" rows="6" cols="45"><%=topic.getComment() %></textarea></td></tr>
    		<tr><td><input type="hidden" name="date" id="date" value=<%=topic.getDate() %>></input></td></tr>
    		<tr><td><input type="hidden" name="topicid" id="topicid" value=<%=topic.getTid() %>></input></td></tr>  
    	</table>
    	<input type="submit" name="submit" value="Edit Event"/> 		 		
    	</form>
    </div>
    </div>
     <div id="bottom" align="center">
            copyrights Talentsprint 2014
        </div>
</body>
</html>