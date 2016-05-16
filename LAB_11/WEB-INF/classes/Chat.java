import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

public class Chat extends HttpServlet{
	
	public static ArrayList<String> log= new ArrayList<String>();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
					
					response.setContentType("text/plain");
					String username = request.getParameter("username");
					String mesg = request.getParameter("text");
					System.out.println(mesg);
					log.add(username + " : "+mesg);
					PrintWriter out = response.getWriter();
					out.println("sent");
				}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
					//System.out.println("get");
					response.setContentType("text/plain");
					PrintWriter out = response.getWriter();
					//JSONObject json = new JSONObject();
					//json.put("chatlog",makeString());
					out.println(makeString());
				}
		
		
	public String makeString(){
		String sendData="";
		if(log.size()<=20){
			for(int i=0;i<log.size();i++){
				sendData = sendData + log.get(i)+"<br>";
			}
		}
		else{
			for(int i =log.size()-20;i<log.size();i++){
				sendData = sendData + log.get(i)+"<br>";
			}
		}
		return sendData;
	}
	
	
}