package test;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	Socket client = new Socket();
	Scanner scanner;
	SocketAddress sc_add = null;
	public Client(String ip,int port) throws SocketException{
		while(true){
			try {
				System.out.println("test...");
				//client.setSoTimeout(5000);
				/*sc_add= new InetSocketAddress(ip,2020);
				client.connect(sc_add, 2000);*/
				client.connect(new InetSocketAddress(ip, 2020), 5000);
				//client = new Socket(ip,port);
				
			} catch (SocketTimeoutException s) {
		        System.out.println("Socket timed out! ");
		        break;
		    } catch (IOException e) {
		    	System.out.println("Socket timed out! ");
		        break;
		    }
		}
		
	}
}
