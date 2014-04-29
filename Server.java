package haha;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server{		
	ServerSocket serversocket = null;
	Socket clientsocket = null;
	int port = 0;
	int clientnubmer = 0;
	BufferedReader getinput = null;//= new BufferedReader( new InputStreamReader(System.in));
	PrintWriter output = null;
	String message;
	Scanner scanner = null ;
	int once = 0;
	
	public Server() {                             // create socket 
		try {
			// call pingipornmap and getARPtable
			PingIPorNmap p = new PingIPorNmap();
			p.ping();
			try {
			    Thread.sleep(15000); 
			} catch(InterruptedException ex) {
			 
			}
			GetARPtable getARPtable = new GetARPtable();
			getARPtable.get();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
        try {
        	System.out.println("Input the port");
        	scanner = new Scanner(System.in);
        	port = scanner.nextInt();
        	serversocket = new ServerSocket(port);
        	//Thread chat = new Thread(talk);
        	//System.out.println("Input the IP address");
        	System.out.println("test message");
    		Scanner fff = new Scanner(System.in);
    		String testmess = fff.next();
    		
        	while(true){
        		clientsocket = serversocket.accept();
        		clientnubmer++;
        		if(clientnubmer>0){
        			System.out.println("the nubmer is "+clientnubmer);
        			//chat.start();
    
        		}
        		try{
        			 if(clientsocket.isConnected()){   //read the message from client
        				/*getinput = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        				message = getinput.readLine();
        				System.out.println("client"+ clientnubmer);
        				output = new PrintWriter(clientsocket.getOutputStream());
        				 BufferedReader wt=new BufferedReader(new InputStreamReader(System.in));*/
        				BufferedWriter bw;
        	            bw = new BufferedWriter( new OutputStreamWriter(clientsocket.getOutputStream()));
        	            bw.write(testmess + "\n");
        	            bw.flush();
        			}
        		}
        		catch(IOException e){System.out.println("輸入流之後的IOException");}
        	}
        	
        } 
        catch (java.io.IOException e) {
            System.out.println("Socket Using!");
            System.out.println("IOException :" + e.toString());
        }
    }
	
	
	public Runnable talk = new Runnable(){
	public void run(){
		System.out.println("server Thread is running ");
		
		Client test = new Client();
		System.out.println("server become the client");

		System.out.println("test message");
		Scanner fff = new Scanner(System.in);
		String testmess = fff.next();
		try{
			while(true){   //read the message from client
				/*getinput = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
				message = getinput.readLine();
				System.out.println("client"+ clientnubmer);
				output = new PrintWriter(clientsocket.getOutputStream());
				 BufferedReader wt=new BufferedReader(new InputStreamReader(System.in));*/
				BufferedWriter bw;
	            bw = new BufferedWriter( new OutputStreamWriter(clientsocket.getOutputStream()));
	            bw.write(testmess + "\n");
	            bw.flush();
	            bw.close();
			}
		}
		catch(IOException e){System.out.println(e);}
		
	}
	};
}
