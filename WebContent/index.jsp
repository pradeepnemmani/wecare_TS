<%@page import="com.model.Event"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/cssLayout.css" type="text/css"/>
<link rel="stylesheet" href="css/default.css" type="text/css"/>
</head>
<body>
  <div id="top" align="center">
 <div>
 	<img src="img/wecare.png" width=800px height=100px>
 	<img src="img/images.jpg" align="right" height=100px/>
 </div>
  
          
        </div>
        <div>
            <div id="left">
           <h3> <a href="index.jsp">Home</a></h3>
              <h3> <a href="login.jsp">Login</a></h3>
                <h3><a href="registration.jsp">Sign up</a></h3>
            </div>
            <div id="content" class="left_content">            
              <font style="color: gray;"> <p>Spread awareness about the various and most harmful cancers which can affect women. A platform
					for medical practitioners and social service organizations to engage in community awareness and
					education programs. Knowledge of measures that can be taken to prevent these life threatening
					diseases can lead to the enormous decrease in the number of victims. Early diagnosis and treatment
					can help cure the disease easily.   
				</p>	
				</font>		  
			<hr/>  
			  
			<div id="allevents" style="margin-left: auto; margin-right: auto;width:1150px;height:476px; overflow:scroll;">
    	<%
    		List<Event> events=(List)request.getAttribute("events");
    	
    	if(events!=null)
    	{
    			if(events.size()>0)
    			{
    				for(Event event:events)
    				{
    					%>
    					<div>  						
    						<h4><u><font style="color: green;"><%=event.getName()%></font></u></h4>
    						<p>Description:<%=event.getDescription() %></p>
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
    							</tr>
    						</table>
    						<hr/>
    					</div>
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