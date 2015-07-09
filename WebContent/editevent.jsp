<%@page import="com.model.Event"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Event</title>
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
         <h3><a href="Events">Events</a></h3>
         <h3><a href="DownloadFile">Downloads</a></h3>
         <h3><a href="Uploads">Uploads</a></h3>
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
    <h1>Add Event</h1>
    <%
    	String message=(String) request.getParameter("msg");
    	Event event=(Event) request.getAttribute("event");
    %>
    <div>
    	<form action="EditEvent" method="post">
    	<h2>Event Details</h2>
    	<table>
    		<tr><td>Name:</td><td><input type="text" name="name" id="name" value=<%=event.getName() %>></input></td></tr>
    		<tr><td>Description:</td><td><textarea  id="desciption"  name="desciption" rows="6" cols="45"><%=event.getDescription() %></textarea></td></tr>
    		<tr><td>Date:</td><td><input type="text" name="date" id="date" value=<%=event.getDate() %>></input></td></tr>
    	</table>
    	<h2>Address Details</h2>
    	<table>
    		<tr><td>PlotNo:</td><td><input type="text" name="plotNo" id="plotNo" value=<%=event.getPlotNo() %>></input></td></tr>
    		<tr><td>Area:</td><td><input type="text" name="area" id="area" value=<%=event.getArea() %>></input></td></tr>
    		<tr><td>City:</td><td><input type="text" name="city" id="city" value=<%=event.getCity() %>></input></td></tr>
    		<tr><td>State:</td><td><input type="text" name="state" id="state"/ value=<%=event.getState() %>></input></td></tr>
    		<tr><td>Country:</td><td><input type="text" name="country" id="country" value=<%=event.getCountry() %>></input></td></tr>
    		<tr><td>Pin Code:</td><td><input type="text" name="pincode" id="pincode" value=<%=event.getPincode() %>></input></td></tr>   
    		<tr><td><input type="hidden" name="eventid" id="eventid" value=<%=event.geteId() %>></input></td></tr>  
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