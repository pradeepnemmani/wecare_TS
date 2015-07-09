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
            <div style="margin-left: auto; margin-right: auto;width:1100px;height:490px; overflow:scroll;">
           	       	      
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
		        			%>
		        				<div>		        					
		        			<%
		        			for(Entry<Map<User, Topic>, List<Map<User, Comment>>> entry:map.entrySet())
		    				{
		    					Map<User,Topic> topicMap=entry.getKey();
		    					for(Entry<User, Topic> topicEntry:topicMap.entrySet())
		    					{
		    						User user=topicEntry.getKey();
		    						 topic=topicEntry.getValue();
		    						%>
		    						<table>
		    							<tr>
		    								<td>
		    									<div id="topic">
		    										<h2> <u>Topic:<%=topic.getTname() %></u></h2>
		    									</div>
		    								</td>
		    							</tr>
		    							<tr>
		    								<td>
		    										<%
		    										
		    										if(user.getImage()!=null)
	    											{
	    												%>
	    												
	    													<h2 style="color: red;">
														<img style="width: 30%; height: 10%;" src=<%=user.getImage() %>></img> <%=user.getUserid() %>
													</h2>  												
	    												<%
	    											}
	    										else
	    										{
	    											%>	
													<h2 style="color: red;">
														<img alt="" src="images/user.jpg"/> <%=user.getUserid() %><br/><%=topic.getDate() %>
													</h2>    												
												<%
	    										}
		    										%>	  		    										 												    										    													    											
		    								</td>
		    							</tr>
		    							<tr>
		    								<td>
		    									<p>
		    										Description:<%=topic.getComment() %>
		    									</p>
		    								</td>
		    							</tr>
		    							
		    						</table>
		    							
		    						<%	    						
		    					}
		    					%>
		    						<hr/>
		    					<%
		    					List<Map<User,Comment>> commentsList=entry.getValue();
		    					for(Map<User,Comment> commentMap:commentsList)
		    					{
		    						for(Entry<User,Comment> commentEntry:commentMap.entrySet())
		    						{
		    							User user=commentEntry.getKey();
		    							Comment comment =commentEntry.getValue();
		    							%>
		    							<table>
		    								<tr>
		    									<td>
		    										<%
		    										if(user.getImage()!=null)
	    											{
	    												%>
	    												
	    													<h2 style="color: red;">
														<img style="width: 30%; height: 10%;" src=<%=user.getImage() %>></img><br/> <%=user.getUserid() %><br/>
														<%=comment.getTime() %>
													</h2>  												
	    												<%
	    											}
	    										else
	    										{
	    											%>	
													<h2 style="color: red;">
														<img alt="" src="images/user.jpg"/> <%=user.getUserid() %>
													</h2>    												
												<%
	    										}
		    										%>
		    							
		    									</td>
		    								</tr>
		    								<tr>
		    									<td>
		    										Comment:<%=comment.getComment() %>
		    									</td>
		    								</tr>
		    							</table>
		    							<hr/>	    								
		    						<%
		    						}
		    					}
		    					
		    				}
		        			
	        			}
		   			%>
		    		</div>
		    		</div>
		    			<form action="CommentTopic" method="post">
        									<label for="comment">Comment:</label>
        									<textarea name="comment" id="comment" rows="1" cols="110"></textarea>
        									<input type="hidden" name="tid" id="tid" value=<%=topic.getTid() %>></input>
        									<input type="submit" name="Comment" value="AddComment"/>
        								</form>
    							    		</div>
        <div id="bottom" align="center">
            copyrights Talentsprint 2014
        </div>

</body>
</html>