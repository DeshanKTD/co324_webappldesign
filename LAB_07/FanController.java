
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 *	Prob 2
 *	Fanstate is set to static and method to
 *	change state is synchronizd 
 *	so this mutiple users can change the state
 *	without causing problem
 *
 */

enum FanState {
	OFF {
		FanState eval(String command) { 
			if(command.equals("inc"))
				return MED; 
			return OFF;
		}
	} ,
	MED {
		FanState eval(String command) { 
			if(command.equals("inc"))
				return HI;
			else if(command.equals("dec"))
				return OFF;
			return MED; 
		}
	} ,
	HI {
		FanState eval(String command) { 
			if(command.equals("inc"))
				return HI;
			else if(command.equals("dec"))
				return MED;
			return HI; 

		}
	} ;
	
	abstract FanState eval(String command);

}




public class FanController extends Thread {
	public static FanState fs = FanState.OFF;
	public static final int PORT = 4321;

	final Socket socket;
	
	public FanController(Socket s) {
		this.socket = s;
	}

	public void run()  {
		try (Scanner sin = new Scanner(socket.getInputStream() ); 
				PrintStream sout = new PrintStream (socket.getOutputStream() ); ) {
			
			while (!interrupted() && sin.hasNext()) {
				synchronized(fs){
					fs = fs.eval( sin.nextLine() );
					sout.println(fs);
				}
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) throws IOException {
		try(ServerSocket ss =  new ServerSocket(PORT) ) {
			while (true)
				new FanController(
						ss.accept()).start();
		} 	
	}
}
