/*
 *  A datagram client to send and receive UDP packets in Java.
 *
 *  Instructions to run:
 *  Pass the host address and the port as command line arguments.
 *  Eg: >>javac UDPClient.java
 *      >>java UDPClient hostaddress portNo
 */

 //import network and input output packages
 import java.net.* ;


public class UDPClient
{
	//Give a standard packet size. 
	private final static int packetsize = 100 ;
	public static DatagramSocket socket;
   
	public static void main( String args[] )
	{


       // Check the whether the arguments are given
	if( args.length != 2 )
	{
	         System.out.println( "usage: java DatagramClient host port" ) ;
	         return ;
	}

     	long tStart = System.currentTimeMillis();
	for(int i =1;i<=1000;i++){
	  
	try
	{
          // Convert the arguments to ensure that they are valid
		InetAddress host = InetAddress.getByName( args[0] ) ;
		int port         = Integer.parseInt( args[1] ) ;

         //Q1: Construct the socket
		socket = new DatagramSocket() ;

		byte [] data = "The message watnts to pass".getBytes() ;
		 
         //Q2: Construct the datagram packet
		DatagramPacket packet = new DatagramPacket(data, data.length, host, port);

         // Send the packet
		socket.send( packet ) ;
        

         // Prepare the packet for receive
		//packet.setData( new byte[packetsize] ) ;

         // Wait for a response from the server
		//socket.receive( packet ) ;

         // Print the response
	//	System.out.println( new String(packet.getData()) ) ;
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds + " Sent Packets: " + i ); 

		
	 
	}
	catch( Exception e )
	{
		System.out.println( e ) ;
	}

	finally
	{
		socket.close() ;
	}
     	}
	}
}


	/* Q5
	   If socket.close() place at end of the try  block,\
     		if exception occured before the that line,
		socket will not be close
	*/
