<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Chat Application</title>
<link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>

<!-- 
Q1: Need to track session here in order to use the name of the user!

Ex: 
request.getParameter( "name" );
session.setAttribute("username", request.getParameter( "name" );
-->



<%!


public String loginForm()
{
	String loginform = "<div id=\"loginform\"><form name=\"enterName\" action=\"index.jsp\" method=\"post\" >"
	+ "<p>Enter your name to continue:</p>"
	+ "<label for=\"name\">Name:</label>"
	+ "<input type=\"text\" name=\"name\" id=\"name\" />"
	+ "<input type=\"submit\" name=\"enter\" id=\"enter\" value=\"Enter\" /></form></div>";
	return loginform;
}
%>
<%
		session.setAttribute("username", request.getParameter( "name" ));
%>

<% if(session.getAttribute( "username" ) == "" || session.getAttribute( "username" ) == null ){ %>
	<%= loginForm() %>
	
<%}


else { %>

 


<div id="wrapper">
    <div id="menu">
        <p class="welcome">Welcome, <b></b> <%=session.getAttribute( "username" )%> </p>
    </div>
     
    <!-- This div will contain the chatlog. --> 
    <div id="chatbox"></div>
    
    <form name="message" action="" method="post">
        <input name="usermsg" type="text" id="usermsg" size="63" />
        <input name="submitmsg" type="submit" id="submitmsg" value="Send" />
    </form>
</div>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script type="text/javascript">
// jQuery Document
$(document).ready(function(){
});


//Q2: Complete the following jQuery to pass the messages to servlet.
$("#submitmsg").click(function(){	
	var clientmsg = $("#usermsg").val();		// Add URL to your servlet		
	var sendData = {"text":clientmsg};
	  	//alert(sendData);
	  	$.ajax({
	  	    type: 'POST',
	  	    url: '\chatapp',
	  	    data: sendData,
	  	    success: function(resp) { }
	  	});
		document.getElementById('usermsg').value = "";
	return false;
});



//Q3: Reload file every 100 milliseconds.
window.setInterval(function(){
  loadLog();  //calling every 100 mili seconds
}, 100);


//Q4: Fill the following function to update the chat box with ajax query.
function loadLog(){		

	//Insert chat log into the #chatbox div on success.
	
	// Refer "http://www.w3schools.com/jquery/jquery_ajax_get_post.asp" OR
	// http://www.sitepoint.com/use-jquerys-ajax-function/ for more inf
		//alert(sendData);
	  	$.ajax({
	  	    type: 'GET',
	  	    url: '\chatapp',
	  	    success: function(a,b,c) { 
				document.getElementById("chatbox").innerHTML = a;
			}
	  	});
	
}

</script>

<% } %>

</body>
</html>