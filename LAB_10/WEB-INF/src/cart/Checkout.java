package cart;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
 
public class Checkout extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>Checkout</title></head>");
		out.println("<body>Your items:");
		
		Cookie ck = null;
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
		
		String cartItems = ck.getValue();
		
		//remove the cookie
		if(ck==null){
			ck = new Cookie("cartItems",null);
		}
		
		ck.setMaxAge(0);
		response.addCookie(ck);
		
		if (cartItems == null)
			out.println("No items were selected.");
		else 			
			out.println(cartItems);
		
		out.println("</body></html>");
	}
	
}
	
	