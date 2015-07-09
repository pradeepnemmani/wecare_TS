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
<script type="text/javascript" src="js/searchtopic.js"></script>
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
                <h3><a href="DownloadFile">Downloads</a></h3>
                <h3><a href="Signout">Sign out</a></h3>
                </div>              
            </div >
            <div id="content" class="left_content">
            <div>
	           <form action="SearchTopic" name="searchtopic">
            <label for="topic">Topic:</label>
            <input type="text" id="topic" name="topic"/>
            <input type="submit" name="submit" value="SEARCH" onclick="return searchTopicValidator()"/>
            </form>  
            </div>
            <hr/>
            <div>
            	<h1>Topic List</h1>
            </div>
            <div>
            	<%
            		String message=(String) request.getAttribute("msg");
            		List<Topic> topics=(List)request.getAttribute("topics");
            		int index=1;
            		if(message==null)
            		{
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
								 </tr>
	            			<%
	            			for(Topic topic:topics)
	            			{
	            				%>
	            					 <tr>
     									<td>
 											 <%=index++ %> </td>
										<td><a href="CommentTopic"> <%=topic.getTname() %></a> </td>
 										<td><font style="color: green;"><%if(topic.getComment().length()>10)
 							{
 								%>
 									<%= topic.getComment().substring(0,10)%>
 								<%	
 							}
 						else{
 							%>
 								<%=topic.getComment() %>
 							<%
 						}
 							%></font></td>
 						<td><%=topic.getDate() %></td>
 						<td>
 							<form action="CommentTopic">							
 								<label for="topicid"></label>
 								<input type="hidden" name="topicid" id="topicid" value=<%=topic.getTid() %>></input>
 								<input type="submit" name="Comment" id="Comment" value="Comment"/>
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
	            			<p>No Topics more..!</p>
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