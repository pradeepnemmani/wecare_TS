<%@page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uploaded Files</title>
<link rel="stylesheet" href="css/cssLayout.css" type="text/css"/>
<link rel="stylesheet" href="css/default.css" type="text/css"/>
<link rel="stylesheet" href="css/tabel.css" type="text/css"/>
<link rel="stylesheet" href="css/heading.css" type="text/css"/>
<link rel="stylesheet" href="css/search&uploadbar.css" type="text/css"/>
<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript" src="js/searchtopic.js"></script>
<script type="text/javascript" src="js/filevalidate.js"></script>
</head>
<body>

  <div id="top" align="center">
 
  <img src="img/wecare.png" width=800px height=100px>
 	<img src="img/images.jpg" align="right" height=100px/>
          
        </div>
        <div>
            <div id="left">
           
                <h3><a href="MyTopics">My topic</a></h3>
                <h3><a href="addtopic1.jsp">Add Topic</a></h3>
               <%
               	if(request.getSession().getAttribute("admin")!=null)
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
                  <h3><a href="Signout">Sign out</a></h3>
                </div>              
            </div >
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
       				 <h1>Files</h1>
                <%
                	String message=(String) request.getAttribute("msg");
                	String[] files=(String[]) request.getAttribute("files");
                	if(message!=null)
                	{
                		%>
            				<p> <font style="color:green;"><%=message %></font> </p>
            			<%
                	}
                		if(files!=null)
                		{
                			
                		if(files.length>0)
                		{
                			int index=0;
                			%>
                				<div id="box-table-a" style="border: 5px;">
                						<table>
                							<tr>
                								<th>NO</th>
                								<th>File Name</th>
                								<th>  Delete</th>
                								
                							</tr>
                			<%
                			for(String file:files)
                			{
                				%>
                					<tr>
                						<td><%=++index %></td>               						
                						<td><a href="UploadDownloadFileServlet?fileName=<%=file%>"><%=file%></a></td>  
                						<td>
                							<form action="Uploads" method="post">
                								<input type="hidden" name="filename" id="filename" value=<%=file %>></input>
                								<input type="submit" name="submit" value="delete"/>
                							</form>
                						</td>                  					               					                						
                					</tr>
                				<%
                			}
                			%>
                				</table>
                				</div>
                			<%
                		}
                		else{
                			%>
                		<%
                		}
                	}
                	
                	
                %>
        </div>
        <div id="bottom" align="center">
            copyrights Talentsprint 2014
        </div>

</body>
</html>