/*
 *  A datagram client to send and receive UDP packets in Java.
 *
 *  Instructions to run:
 *  Pass the port as command line arguments.
 *  Eg: >>javac UDPClient portNo
 */
 
 import java.net.* ;
public class UDPServer
{
   //Give a standard packet size
	private final static int packetsize = 100 ;

	
	public static void main( String args[] )
	{

	double time=0;
	double timeatt=0;
	double troughput=0;
      // Check the whether the arguments are given
	if( args.length != 1 )
	{
		System.out.println( "usage: DatagramServer port" ) ;
		return ;
	}

	long tStart = System.currentTimeMillis();

	try
	{
         // Convert the argument to ensure that is it valid
		int port = Integer.parseInt( args[0] ) ;

         // Construct the socket
	 /* If port is in use by another application, according to the
	    type of application, error get fatal or non fatal because
	    it never allows to construct the port
	    If application is running continuesly this is not recoverable
	    if not it can be recovered after restarting the application

	    SocketException - if the socket could not be opened, or the socket could not bind to the specified local port. - depends
	    SecurityException - if a security manager exists and its checkListen method doesn't allow the operation. - non recoverable
	*/ 
		DatagramSocket socket = new DatagramSocket( port ) ;

		System.out.println( "The server is ready..." ) ;
		
	// Create a packet
	/* If the server packet size is less than received packet size,
	   the part of the data coming get lost. 
	   This cannot be recover until size change. But this is not
	   an exception
	*/
		DatagramPacket packet = new DatagramPacket( new byte[packetsize], packetsize ) ;


		for(int i=1 ;;i++ )
		{
		try{
		    // Receive a packet (blocking)
			socket.receive( packet ) ;
		    /* IOExcetions - for IO errors - recoverable
		       SocketTimeoutException - if setSoTimeout was previously called and the timeout has expired. -recoverable
		       PortUnreachableException - socket is connected to a currently unreachable destination. - not recoverable
		       IllegalBlockingModeException - if this socket has an associated channel, and the channel is in non-blocking mode. - non recoverable
		    */
			

		    // Print the packet
			//System.out.println( packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()) ) ;

		    // Return the packet to the sender
			socket.send( packet ) ;
			/*
			 IOException - if an I/O error occurs.
			 SecurityException - if a security manager exists and its checkMulticast or checkConnect method doesn't allow the send. - non recoverable
			 PortUnreachableException - may be thrown if the socket is connected to a currently unreachable destination. - non recoverable
			 IllegalBlockingModeException - if this socket has an associated channel, and the channel is in non-blocking mode. - non recoverable
			*/
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println(elapsedSeconds + " Received : " + i + " pkts -> throughput: "+ troughput+ " KBps");

			//calculating throughput for each 100 pakts
			if(i%100==0){
			
				time=elapsedSeconds-timeatt;
				troughput = 100*100/(time*1024);
				timeatt=elapsedSeconds;
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		}  
	}
	catch( Exception e )
	{
		System.out.println( e ) ;
		
	}

     
  }
}
