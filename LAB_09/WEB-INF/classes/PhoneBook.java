import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;


public class PhoneBook extends HttpServlet{
	
	private HashMap<String,String> pBook = new HashMap<String,String>();
	private String username,number;
	
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException 
		{
					response.setContentType("text/plain");
					
					username = request.getParameter("userName"); //get username
		
					PrintWriter out = response.getWriter();
					
					if(pBook.containsKey(username)){	//check name contain in directory
						synchronized(pBook){
							number = pBook.get(username);	//get and send username
							out.println(username + " : " + number);
						}
					}
					else{
						out.println("Phone number entry not found");
					}
		}
		
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException 
		{
					response.setContentType("text/plain");
					
					username = request.getParameter("userName");	//get username and number
					number = request.getParameter("number");
					
					PrintWriter out = response.getWriter();
					
					synchronized(pBook){
						pBook.put(username,number);		//add number to phonebook
					}
					out.println(username + " added to the PhoneBook");
					
		}
		
}