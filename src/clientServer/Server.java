package clientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int portNumber = 5050;
	
	public static void main(String[] args){
		
		boolean listening = true;
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Server listening on port number " + portNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(listening){
			Socket clientSocket = null;
			
			
			try {
				System.out.println("Awaiting a connection...");
				clientSocket = serverSocket.accept();
				System.out.println("Client connected");
				System.out.println("Client Address " + clientSocket.getInetAddress().toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ConnectionHandler con = new ConnectionHandler(clientSocket);
			con.init();
			System.out.println("02. -- Finished communicating with client:" 
                    + clientSocket.getInetAddress().toString());
		}
			 // Server is no longer listening for client connections - time to shut down.
	        try 
	        {
	            System.out.println("Closing down the server socket gracefully.");
	            serverSocket.close();
	        } 
	        catch (IOException e) 
	        {
	            System.err.println("Could not close server socket. " + e.getMessage());
	            
	        }
		
		
	}
}
