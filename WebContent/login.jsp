<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/cssLayout.css" type="text/css"/>
<link rel="stylesheet" href="css/default.css" type="text/css"/>
<script type="text/javascript" src="js/validation.js"></script>
</head>
<body>
   <div id="top" align="center">
 
  <img src="img/wecare.png" width=800px height=100px>
          
          <img src="img/images.jpg" height=100px/>
        </div>
        <div>
            <div id="left">
           <h3> <a href="index.jsp">Home</a></h3>
              <h3> <a href="login.jsp">Login</a></h3>
                <h3><a href="registration.jsp">Sign up</a></h3>
            </div>
            <div id="content" class="left_content">
            
            <h1>Login Form</h1>
              <%
             
            String msg=(String)request.getAttribute("msg");
            if(msg!=null){
            	%>
            	<font style="color: green;"><%=msg %></font>
            	<%
            }
            %>
            <form action="Login" name="login">
            <table>
     <tr><td> Username</td><td><input type="text" name="username"/></td></tr>
     <tr><td> Password</td><td> <input type="password" name="password" /></td></tr></table>
      <input type="submit" value="Login" onclick="return auth()">
           </form>
            </div>
        </div>
        <div id="bottom" align="center">
            copyrights Talentsprint 2014
        </div>

</body>
</html>