package ee402;

import java.net.*;

public class NetApplication1 {
	
	public static void main(String[] args){
		if(args.length < 1){
			System.out.println("Please enter an IP address");
			System.exit(0);
		}
		try {
			InetAddress i = InetAddress.getByName(args[0]);
			String hostName = i.getHostName();
			String address = i.getHostAddress();
			System.out.println("The host has name " + hostName
					+ " and Address " + address);
		} catch (UnknownHostException e) {
			System.out.println("Unknown Host");
			e.printStackTrace();
		}
		
	}
}
