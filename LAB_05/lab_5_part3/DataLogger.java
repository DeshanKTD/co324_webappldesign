

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

//import Reading;

public class DataLogger extends Thread {
	static final int PORT = 12345;
	Socket socket;
	static ArrayList<Reading> dataLog = new ArrayList<Reading>();
	static Object lock = new Object();

	public DataLogger(Socket soc){
		this.socket = soc;
	}

	@Override
	public void run(){
				try (DataInputStream sin = new DataInputStream(
							this.socket.getInputStream()); ) {
					//This should run in a separate thread

					while (true){ 
						synchronized (lock){
							Reading r = new Reading(sin);
							System.out.println(r);
							dataLog.add(r);
						}
					}					

					/*	3. Data races
					 *	b. The log data is not in order, some times same data might 
					 *	be add to the log. Some times when writing to the log, 
					 * 	the value of the real reading may changed with value that 
					 * 	that writing to the log.
					 */

				} catch (IOException e1) {
					e1.printStackTrace();

				} 

	}
	
	public static void main(String[] args) throws IOException {
		DataLogger t;
		
		ServerSocket ss =  new ServerSocket(PORT);
		
		while(true){		
			Socket socketN = ss.accept(); 
			try{
				t = new DataLogger(socketN);	
				t.start();

			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}	
}
