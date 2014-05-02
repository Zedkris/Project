package test;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	Socket client=null;
	Scanner scanner;
	
	public Client(String ip,int port){
		try {
			client = new Socket(ip,port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
