/*
 *  A TCP client to send and receive data streams in Java.
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPClient{
    public static void main(String args[]){
        
        if( args.length != 2)
        {
            System.out.println("usage: java TCPClient <IP address> <port>");
            return;
        }

        int port = Integer.parseInt(args[1]);
        
        try{
            InetAddress address = InetAddress.getByName(args[0]);
            
            //Q1: Add codes here
      
			
			try(Socket socket = new Socket(address,port);
				Scanner sin = new Scanner(socket.getInputStream());
				PrintStream sout = new PrintStream(socket.getOutputStream())){
					sout.println("This is client, say hello to server" +  address);
					System.out.println(sin.nextLine());
				}
			catch(Exception e){
				System.out.println(e);
			}
        }
        
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}

