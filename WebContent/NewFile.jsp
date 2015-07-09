<%@page import="java.util.Map.Entry"%>
<%@page import="javax.swing.text.html.parser.Entity"%>
<%@page import="com.model.User"%>
<%@page import="com.model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Topic"%>

<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comments</title>
<link rel="stylesheet" href="css/cssLayout.css" type="text/css"/>
<link rel="stylesheet" href="css/default.css" type="text/css"/>
<link rel="stylesheet" href="css/search&uploadbar.css" type="text/css"/>
<link rel="stylesheet" href="css/image.css" type="text/css"/>
<link rel="stylesheet" href="css/some.css" type="text/css"/>
<script type="text/javascript" src="js/searchtopic.js"></script>
</head>
<body>

  <div id="top" align="center">
 
  <img src="img/wecare.png" width=800px height=100px>
 	<img src="img/images.jpg" align="right" height=100px/>
          
        </div>
        <div>
            <div id="left">        
          <%
          if(request.getSession().getAttribute("admin")!=null)
          {
	          	if(((Boolean)request.getSession().getAttribute("admin")).booleanValue())
	                	{
	                		%>
	                			 <h3><a href="addevent.jsp">Add event</a></h3>
	                			   <h3><a href="Events">Events</a></h3>
	                			   <h3><a href="DownloadFile">Downloads</a></h3>
	                			   <h3><a href="Uploads">Uploads</a></h3>
        
	                		<%
	                	}
          }
                	%>
                <h3><a href="MyTopics">My topic</a></h3>
                <h3><a href="addtopic1.jsp">Add Topic</a></h3>
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
	                		<form action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data">
								Select File to Upload:<input type="file" name="fileName">
								<button type="submit" value="Upload">UPLOAD</button>
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
          
           	       	      
		        	<%
		        		String message=(String) request.getAttribute("msg");
		        	Map<Map<User,Topic>,List<Map<User,Comment>>> map=(Map) request.getAttribute("map");
		        	Topic topic=null;
		        		if(message!=null)
		        		{
		        		%>
		        			<font style="color: green;"><%=message %></font>
		        		<%
		        		}
		        		
		        		if(map!=null)
	        			{
		        			for(Entry<Map<User, Topic>, List<Map<User, Comment>>> entry:map.entrySet())
		    				{
		    					Map<User,Topic> topicMap=entry.getKey();
		    					for(Entry<User, Topic> topicEntry:topicMap.entrySet())
		    					{
		    						User user=topicEntry.getKey();
		    						 topic=topicEntry.getValue();
		    						 %>
		    						 	<div style=" background-color: #CC33CC;  color: white;padding: 5px; margin: 0px 0px 0px 0px; height: 50px;">
 											 <h2 style="text-align: center;"> <%=topic.getTname() %></h2> 
		    						 	</div>
		    						 	<hr/>
		    						 	<div>
		    						 	  <div style=" margin-right: auto;width:1430px;height:400px; overflow:scroll;">
		    						 	  	<div id="some" >
			    						 		<div>
			    						 		
		    						 			<%
		    						 			
		    						 				if(user.getImage()!=null)
		    						 				{
		    						 					%>		    						 						
			    						 					<img style="width: 75%;height: 50%;" src=<%=user.getImage() %>>    						 						
		    						 					<%
		    						 				}
		    						 			else{
		    						 				%>
		    						 					<img style="width: 75%;height: 50%;" src="images/user.jpg">
		    						 				<%
		    						 			}
		    						 			%>
		    						 				
		    						 				<u style="color: blue;">
		    						 					<%
		    						 						if(user.getName().length()>10)
		    						 						{
		    						 							%>
		    						 								<%=user.getName().substring(0,10) %>
		    						 							<%	
		    						 						}
		    						 					else
		    						 					{
		    						 						%>
		    						 							<%=user.getName() %>
		    						 						<%
		    						 					}
		    						 					%>	
		    						 				</u>
		    						 				</p>
		    						 				</div>
		    						 			</div>
		    						 			<div style=" margin-left: 10px;" >
		    						 							<p>
		    						 								<%=topic.getComment() %>
		    						 							</p>
		    						 						</div>
		    						 						
		    						 							<p style="float: right; color: maroon;"><%=topic.getDate() %></p>
		    						 			<br/>
		    						 			<br/>
		    						 	
		    						 	<hr/>
		    						 <%
		    						 
		    					}
		    					List<Map<User,Comment>> list=entry.getValue();
		    					if(list!=null)
		    					{
		    						if(list.size()>0)
		    						{
		    							for(Map<User,Comment> map2:list)
		    							{
		    								%>
		    									
		    								<%
		    								for(Entry<User,Comment> commentEntry:map2.entrySet())
		    								{
		    									User user=commentEntry.getKey();
		    									Comment comment=commentEntry.getValue();
		    									%>
		    										<div id="some" >
			    						 		<div>
		    						 			<%
		    						 			
		    						 				if(user.getImage()!=null)
		    						 				{
		    						 					%>		    						 						
			    						 					<img style="width: 75%;height: 50%" src=<%=user.getImage() %>>	    						 						
		    						 					<%
		    						 				}
		    						 			else{
		    						 				%>
		    						 					<img style="width: 75%;height: 50%" src="images/user.jpg">
		    						 				<%
		    						 			}
		    						 			%>
		    						 				</div>
		    						 				<u style="color: blue;">
		    						 					<%
		    						 						if(user.getName().length()>10)
		    						 						{
		    						 							%>
		    						 								<%=user.getName().substring(0,10) %>
		    						 							<%	
		    						 						}
		    						 					else
		    						 					{
		    						 						%>
		    						 							<%=user.getName() %>
		    						 						<%
		    						 					}
		    						 					%>	
		    						 				</u>
		    						 			</div>
		    						 			<div >
		    						 				<p>
		    						 					<%=comment.getComment() %>
		    						 				</p>
		    						 				</div>
		    						 						
		    						 				<p style="float: right; color: maroon;"><%=comment.getTime() %></p>
		    						 						
		    						 			<br/>
		    						 			<br/>
		    						 	
		    						 	<hr/>
		    									<%

											}
		    								
		    							}
		    						}
		    					}
		    				}
		        			
	        			}
		        		%>
		    		
		    		</div>
		    			<form action="CommentTopic" method="post">
        									<label for="comment">Comment:</label>
        									<textarea name="comment" id="comment" rows="1" cols="110"></textarea>
        									<input type="hidden" name="tid" id="tid" value=<%=topic.getTid() %>></input>
        									<input type="submit" name="Comment" value="AddComment"/>
        								</form>
        								</div>
    	</div>
        <div id="bottom" align="center">
            copyrights Talentsprint 2014
        </div>

</body>
</html>