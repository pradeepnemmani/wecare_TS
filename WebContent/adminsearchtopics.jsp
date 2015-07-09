<%@page import="java.io.Console"%>
<%@page import="com.model.Topic"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SearchTopics</title>
<link rel="stylesheet" href="css/cssLayout.css" type="text/css"/>
<link rel="stylesheet" href="css/default.css" type="text/css"/>
<link rel="stylesheet" href="css/tabel.css" type="text/css"/>
<link rel="stylesheet" href="css/search&uploadbar.css" type="text/css"/>
<script type="text/javascript" src="js/searchtopic.js"></script>
<script type="text/javascript" src="js/filevalidate.js"></script>
</head>
<body>

  <div id="top" align="center">
 
  <img src="img/wecare.png" width=800px height=100px>
          
        </div>
        <div>
            <div id="left">                         
                <h3><a href="MyTopics">My topic</a></h3>
                <h3><a href="addtopic1.jsp">Add Topic</a></h3>
                <h3><a href="addevent.jsp">Add Event</a></h3>
	              <h3><a href="Events">Events</a></h3>
	              <h3><a href="Uploads">Uploads</a></h3> 
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
			       <form action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data" name="fileupload">
								Select File to Upload:<input type="file" name="fileName">
								<button type="submit" value="Upload" onclick=" return filevalidate()">UPLOAD</button>
							</form>	
					
                   </td>
           	 </tr>    
            </table>           
           </div>	
            <hr/>         
            	<h1>Topic List</h1>      
            <div>
            	<%
            		String message=(String) request.getAttribute("msg");
            		List<Topic> topics=(List)request.getAttribute("topics");
              		int index=1;
            		if(!(message==null))
            		{
            			%>
            				<font style="color: green"><%=message %></font>
            			<%	
            		}
            			if(topics!=null)
            			{
	            		if(topics.size()>0)
	            		{
	            			%>
	            			<div id="box-table-a">
	            			 <table>
								 <tr>
									 <th>S.No</th>
									 <th>Name</th>
									 <th>Comment</th>
									 <th>Date</th>
									 <th>Comment</th>
									<th>Delete</th>						
								 </tr>
	            			<%
	            			for(Topic topic:topics)
	            			{
	            				%>
	            				 <tr>
     								<td><%=index++ %> </td>
									<td><a href="CommentTopic"> <%=topic.getTname() %></a> </td>
 									<td>
 										<font style="color: green;">
		 									<%if(topic.getComment().length()>10)
			 									{
			 									%>
			 										<%= topic.getComment().substring(0,10)%>
			 									<%	
			 									}
			 								else
			 									{
			 									%>
			 										<%=topic.getComment() %>
			 									<%
			 									}
			 									%>
 										</font>
 									</td>
 									<td><%=topic.getDate() %></td>
 									<td>
 										<form action="CommentTopic">							
			 								<label for="topicid"></label>
			 								<input type="hidden" name="topicid" id="topicid" value=<%=topic.getTid() %>></input>
			 								<input type="submit" name="Comment" id="Comment" value="Comment"/>
 										</form>
 									</td>
	            					<td>
								 		<form action="DeleteTopicAdmin">
											<label for="topicid"></label>
					 						<input type="hidden" name="topicid" id="topicid" value=<%=topic.getTid() %>></input>
							 				<input type="hidden" name="topicName" id="topicName" value=<%=request.getSession().getAttribute("topicName") %> ></input>
											<input type="submit" name="delete" id="delete" value="Delete"/>
				 						</form>
			 						</td>			            										            						
				            	<%				            						                           	
	            				}
	            				%>
	            		</table>
	            		</div>
	            		<%	
	            		}
	            		else
	            		{
	            			%>
	            			<p>No Topics...!</p>
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