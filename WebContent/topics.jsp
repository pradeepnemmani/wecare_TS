<%@page import="com.model.Topic"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Topics</title>

<link rel="stylesheet" href="css/cssLayout.css" type="text/css"/>
<link rel="stylesheet" href="css/default.css" type="text/css"/>
<link rel="stylesheet" href="css/tabel.css" type="text/css"/>
<link rel="stylesheet" href="css/search&uploadbar.css" type="text/css"/>
<script type="text/javascript" src="js/validation.js"></script><base>
<script type="text/javascript" src="js/searchtopic.js"></script>
<script type="text/javascript" src="js/filevalidate.js"></script>
<style type="text/css"> {
div.panel panel-default.panel-heading.panel-title.move_right{
    position: fixed;
    top: 30px;
    right: 0px;
    color: red;	
}
}
</style>
</head>
<body>
   <div id="top" align="center">
  <img src="img/wecare.png" width=800px height=100px>       
          <img src="img/images.jpg" height=100px/>
        </div>
        <div>
            <div id="left">
                <h3><a href="addtopic1.jsp">Add Topic</a></h3>
                <h3><a href="DownloadFile">Downloads</a></h3>
                  <%
                  if(request.getSession().getAttribute("admin")!=null)
                  {
	                  if(((Boolean)request.getSession().getAttribute("admin")).booleanValue())
	                	{
	                		%>
	                			 <h3><a href="addevent.jsp">Add event</a></h3>
	                			   <h3><a href="Events">Events</a></h3>
	                			   <h3><a href="Uploads">Uploads</a></h3>
	                		<%
	                	}
                  }
                	%>
                <h3><a href="Signout">Sign out</a></h3>
                </div>
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
            <h1>My Topics</h1>
              <%
             
            String msg=(String)request.getAttribute("msg");
              List<Topic> topics=(List)request.getAttribute("topics");
              int index=1;
              if(msg!=null){
            	%>
            	<font style="color: green;"><%=msg %></font>
            	<%
            }
            %>
            <%
            
            if(topics!=null)
            {
			if(topics.size()>0)
			{
			%>
			<div id="box-table-a">			 
			
				<table >
				 <tr><th>S.No</th>
				 <th>Name</th>
				 <th>Comment</th>
				 <th>Date</th>
				 <th>Delete</th>
				 <th>Edit</th>
				 </tr>
			<%
				for(Topic topic:topics)
				{
					int topicId=topic.getTid();
					%>
					 <tr>
     					<td><font style="color: green;">
 									 <%=index++ %> </font></td>
						<td> <%=topic.getTname() %> </td>
 						<td><%if(topic.getComment().length()>10)
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
 							%></td>
 						<td><%=topic.getDate() %></td>
 						<td>
	 						<form action="DeleteTopic">
		 						<label for="topicid"></label>
		 						<input type="hidden" name="topicid" id="topicid" value=<%=topicId %>></input>
		 						<input type="submit" name="delete" id="delete" value="Delete"/>
	 						</form>
 						</td>
 						<td>
	 						<form action="EditTopic">
		 						<label for="topicid"></label>
		 						<input type="hidden" name="topicid" id="topicid" value=<%=topicId %>></input>
		 						<input type="submit" name="Edit" id="Edit" value="Edit"/>
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
			else
			{
				%>
				<font style="color: green;">
				<p>No Topics..!</p>
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