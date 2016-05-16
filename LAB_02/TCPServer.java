/*
 *  A TCP server to send and receive data streams in Java.
 */

import java.io.*;
import java.net.*;
import java.util.*;


public class TCPServer
{
    private static int port;

    public static void main(String args[])
    {
        // Check whether the arguments are given
        if( args.length != 1)
        {
            System.out.println("usage: java TCPServer <port>");
            return;
        }
        
        // Convert the argument to ensure that is it valid
        port = Integer.parseInt( args[0] ) ;

        // Create the serversocket.
        // Note the use "try-with-resources" introduced in jdk 7.
        try(ServerSocket ss = new ServerSocket(port)){
        while(true){
            try(Socket socket = ss.accept(); // Accept connections from clients.
                
                // Binary data is read and written to the sockets via I/O streams.
                Scanner sin = new Scanner(socket.getInputStream() );
                PrintStream sout = new PrintStream( socket.getOutputStream());)
                {
					String line = sin.nextLine();
                    //sout.println("Server says hello world!");
                    System.out.println(line);
                    
                    // Q1: Server is receiving client messages and printing them back to the client
                    sout.println(line);

                }
            
            catch(Exception e){
                System.out.println(e);
            }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

}

	/*	Q2 - The advantage is, opend inputstreams and outputstreams get disconnect
	 * 	after the transmission is completed. i.e socket get automatically closed.
	 * 	If ss.close() put inside the 2nd try catch block, the program try accept
	 * 	transfer from closed socket in next iteration, which will provide exceptions
	 * 	for all other iterations
	 * /

