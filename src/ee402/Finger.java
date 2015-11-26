package ee402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Finger {

	public static void main(String[] args){
		// Check command line arguments
	      if (args.length != 1) {
	           System.err.println ("Invalid number of parameters:");
	             System.err.println ("Usage: Finger username@host");
	             System.exit(1);
	      }
	      // Check for existence of @ in argument
	      else if (args[0].indexOf("@") == -1) {
	        System.err.println ("Invalid parameter : syntax user@host");
	        System.exit(1);
	      }
	      // Split command line argument at the @ character
	      String username = args[0].substring(0, args[0].indexOf("@") );
	      String hostname = args[0].substring(args[0].indexOf("@") +1, args[0].length());
	      
	      
	      try {
	    	System.out.println("Connecting to: " + hostname);
			Socket s = new Socket(hostname, 79);
			
			PrintStream out = new PrintStream(s.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));	
			
			//Write username to socket output
			out.println(username);
			
			//Read response from socket
			String line = in.readLine();
			
			while(line != null){
				System.out.println(line);
				line = in.readLine();
			}
			
			s.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
