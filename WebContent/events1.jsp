<%@page import="java.io.File"%>
<%@page import="java.util.Random"%>
<%@page import="com.model.Event"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events</title>
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
        <h3><a href="addtopic1.jsp">Add Topic</a></h3>
        <h3><a href="addevent.jsp">Add Event</a></h3>
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
            <h1>All Events</h1>
    	<div id="allevents" style=" margin-right: auto;width:1100px;height:465px;overflow-x: hidden; overflow:scroll;">
    	<%
    		String message=(String) request.getParameter("msg");
    		List<Event> events=(List)request.getAttribute("events");
    		Random random=new Random();
    		File eventImagesDir=new File((String)request.getServletContext().getAttribute("EVENTIMAGES_DIR"));
    		 String ss[]=eventImagesDir.list();
    		if(message!=null)
    		{
    			%>
					<font><h2><%=message %></h2></font>
				<%
    		}
    		if(events!=null)
    		{
    			if(events.size()>0)
    			{
    				for(Event event:events)
    				{
    					%>
    					
    						<h4><u><font style="color: green;"><%=event.getName()%></font></u></h4>
    						<p> <img src=<%=eventImagesDir+File.separator+ss[random.nextInt(15)]%>></img> <%=event.getDescription() %></p>
    						<div >
    						<table>
    							<tr><td>Address</td>
    							<td>
    							<select name="Address">
    								<option>Address</option>
								     <option><p>Plot No:-</p><%=event.getPlotNo() %></option>
								     <option><p>Area:-</p><%=event.getArea() %></option>
								     <option><p>City:-</p><%=event.getCity() %></option>
								     <option><p>State:-</p><%=event.getState() %></option>
								     <option><p>Country:-</p><%=event.getCountry() %></option>
								     <option><p>Pin Code:-</p><%=event.getPincode() %></option>								     
								 </select>
    							</td>
    							<td><form action="EditEvent">
    							<label for="eventid"></label>
    							<input type="hidden" name="eventid" id="eventid" value=<%=event.geteId() %>></input>
    							<input type="submit" name="submit" value="Edit"/>
    						</form></td>
    						<td><form action="DeleteEvent">
    							<label for="eventid"></label>
    							<input type="hidden" name="eventid" id="eventid" value=<%=event.geteId() %>></input>
    							<input type="submit" name="submit" value="Delete"/>
    						</form></td>
    							</tr>
    						</table>
    						</div>
    						<hr/>
    					<%
    				}
    			}
    			else{
    				%>
    					<font style="color: green;">
						<p>No Events Found..!</p>
						</font>
    				<%
    			}
    		}
    	%>
    	</div>
    </div>
     <div id="bottom" align="center">
            copyrights Talentsprint 2014
        </div>
</body>
</html>