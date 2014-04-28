package haha;

import java.io.*;
import java.net.*;
import java.util.*;
public class Client {
	Socket client=null;
	//int port=0;
	Scanner scanner;
	String ip;
	String message;
	DataOutputStream output = null;
	
	public Client(){
		try{  
			System.out.println("Input the port");
        	scanner = new Scanner(System.in);
        	int port = scanner.nextInt();
        	System.out.println("Input the IP address");
        	ip = scanner.next();
        	client = new Socket(ip,port);        	

        	Thread test = new Thread(talk);
        	test.start();
		}
		catch(IOException e){System.out.println(e);}
		
	}
	private Runnable talk = new Runnable (){
	public void run(){
		System.out.println("client thread is running");
		Server abc = new Server();
		System.out.println("client become the server");
		/*while(true){
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
				message = br.readLine();
				System.out.println(message);
			//	output = new DataOutputStream(client.getOutputStream());
			}
			catch(IOException e){System.out.println(e);}
    	}*/		
	}
	};
}

