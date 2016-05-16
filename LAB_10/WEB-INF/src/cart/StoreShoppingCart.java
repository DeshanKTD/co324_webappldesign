package cart;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class StoreShoppingCart extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
		Cookie ck = null;
		String item = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("cartItems"))	//checking whether the cookie exists. cartItems is the name we provide for the cookie.
				{
					ck = cookie;
					break;
				}
			}
		}
		
			
		//get the name of the user requested item
		//if the cookie exists, append the name of the requested item to the cookie. If not, add it to a new cookie.
		
		item = request.getParameter("item");
		if(ck == null){
			ck = new Cookie("cartItems",item);
		}
		else{
			String val = ck.getValue();
			ck.setValue(val + ","+item);
		}
		
	
    ck.setMaxAge(30*60);	//set cookie to expire in 30 mins.
    response.addCookie(ck);	//sending the cookie to the client
	response.sendRedirect("index.html");
		
	}
}	