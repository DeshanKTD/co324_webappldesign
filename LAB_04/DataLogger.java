

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//import Reading;

public class DataLogger extends Thread {
	static final int PORT = 12345;
	Socket socket;

	public DataLogger(Socket soc){
		this.socket = soc;
	}

	@Override
	public void run(){
		while (true)
				try (DataInputStream sin = new DataInputStream(
							this.socket.getInputStream()); ) {
					//This should run in a separate thread
					while (true){ 
						Reading r = new Reading(sin);
						System.out.println(r);
					}					
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
				// finally{
				// 	this.socket.close();
				// }

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
