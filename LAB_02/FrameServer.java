import java.io.*;
import java.net.*;
import java.util.*;

public class FrameServer
{
	public static void main(String args[])
	{
		final String delim =  "\u0003";
		final int portNumber = 20100;
		try
		{ 
			ServerSocket serverSocket = new ServerSocket(portNumber);
			while(true)
			{
				try
				(
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				Scanner in = new Scanner ( clientSocket.getInputStream() );
				)
				{
					String message;
					try
					{
						while((message = in.next()) != null)
						{
							System.out.println("message: "+message);
						}
					}
					catch(Exception ex)
					{
						System.err.println("Sorry something wrong happened reading from client");
					}
				}		
			}
		}
		catch(Exception ex)
		{
			System.err.println("Sorry something wrong happened. I need to die");
			ex.printStackTrace();
		}
	}
}

	/*
	 * Q3 - If delimeter is not append to end of the message, the sending message is not
	 * break in to chunks. i.e the stream is not closing until total message send to the
	 * the server. 
	 * If delimeter is added between message, the message will receive to the
	 * server as separate blocks braked by the delimeter entered.
	 */
	 
	 
	 
	 
	 /* 
	  * Q4 - If delimeter is not defined,  java uses white space as the default delimeter
	  */
