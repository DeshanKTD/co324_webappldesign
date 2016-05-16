//package http;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HTTP server that accepts GET and POST requests.
 */
public class KVServer extends Thread {
	static final int PORT = 4321;
	static final Map<String, String> database = new HashMap<>();

	/** Used to serve an individual client */
	final Socket socket;

	public KVServer(Socket s) {
		this.socket = s;
	}

	public static void main(String[] args) throws IOException {
		try (ServerSocket ss = new ServerSocket(PORT)) {
			while (!Thread.interrupted())
				new KVServer(ss.accept())
						.run();
		}
	}
	/** Logic to handle a single client request */
	public void run() {
		try (DataInputStream sin = new DataInputStream(socket.getInputStream());
		     PrintStream sout = new PrintStream(socket.getOutputStream());) {
			// Deserialise HTTP request
			Message req = new Message(sin);
			/* TODO:
			Handle GET and POST requests and send appropriate response.
			NOTE: the code given below is just for testing!
			 */
			 
			if(req.getMethod().equals("GET")){
				if(database.containsKey(req.getEnterKey())){
					Status response=Status.OK_SEND_DATA;
							response.setContent(
						database.get(req.getEnterKey()));
						
						response.message.serialise(sout);
				}
				else{
					Status.NOTFOUND.message.serialise(sout);
				}
				
			}
			
			else if(req.getMethod().equals("POST")){
				String [] entr = new String(req.body).split("&");
				String key = entr[0].split("=")[1];
				String value = entr[1].split("=")[1];
				database.put(key,value);
				Status.OK.message.serialise(sout);
				
			}
			
			else{
				Status.BADREQ.message.serialise(sout);
			}
			 
			req.serialise(new PrintStream(System.out));
			Status.OK.message.serialise(sout);
		} catch (IOException e) {
			Logger.getGlobal().log(Level.WARNING, "Client error", e);
		} finally {
			try { socket.close(); } catch (IOException e) {}
		}
	}
}
