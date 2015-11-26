package clientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private static int portNumber = 5050;
	private Socket socket  = null;
	private ObjectOutputStream os = null;
	private ObjectInputStream is = null;
	
	public Client(String serverIP){
		if(!connectToServer(serverIP)){
			System.out.println("Error");
		}
	}
	
	private boolean connectToServer(String serverIP){
		try {
			this.socket  = new Socket(serverIP, portNumber);
			this.os = new ObjectOutputStream(socket.getOutputStream());
			this.is = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("Connecting to server...");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void getDate(){
		String theDateCommand = "GetDate", theDateAndTime;
		System.out.println("Sending Command (" + theDateCommand + ") to the server...");
		this.send(theDateCommand);
		
		theDateAndTime = (String) receive();
		System.out.println("Server responded with " + theDateAndTime);
	}
	
	private void send(Object o){
		
		try {
			System.out.println("Sending object " + o);
			this.os.writeObject(o);
			this.os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Object receive(){
		Object o = null;
	
	
		try {
			System.out.println("About to receive object...");
			o = is.readObject();
			System.out.println("Object received...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
		
	}
	
	public static void main(String[] args){
		Client app = new Client(args[0]);
		app.getDate();
	}
}
