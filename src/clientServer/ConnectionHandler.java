package clientServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandler {

	private Socket clientSocket = null;
	private DateTimeService theDateService;
	private ObjectInputStream is = null;
	private ObjectOutputStream os = null;
	
	public ConnectionHandler(Socket clientSocket){
		this.clientSocket = clientSocket;
		this.theDateService = new DateTimeService();
	}
	
	public void init(){
		
		try {
			
			this.is = new ObjectInputStream(clientSocket.getInputStream());
			this.os = new ObjectOutputStream(clientSocket.getOutputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(this.readCommand()){};
		
	}
	
	private boolean readCommand(){
		
		String s = null;
		
		try {
			s = (String) is.readObject();
		} catch (Exception e) {
			this.closeSocket();
			System.out.println("Client Socket closed");
			return false;
		}
		
		System.out.println("Received command ( " + s + " )");
		
		if(s.equalsIgnoreCase("getDate")){
			this.getDate();
		} else{
			this.sendError("Invalid command: " + s);
		}
		
		return true;
	}
	
	private void getDate(){
		String currentDateTimeText = theDateService.getDateAndTime();
		this.send(currentDateTimeText);
	}
	
	private void send(Object o){
		try {
			System.out.println("Sending " + o + " to client");
			this.os.writeObject(o);
			this.os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void sendError(String message){
		this.send("Error:" + message); 	
	}
	
	private void closeSocket(){
		
		try {
			this.os.close();
			this.is.close();
			this.clientSocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
