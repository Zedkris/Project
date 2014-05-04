package test;

import java.io.*;
import java.net.*;
import java.util.*;

import test.Client;

public class Server {
	ServerSocket serversocket = null;
	Socket clientsocket = null;
	int port = 0;
	int clientnumber = 0;
	Scanner scanner = null ;
	String[] IPtable = new String[999];
	int IPindex;
	private static ArrayList<Socket> players=new ArrayList<Socket>();
	public Server(){
		try {
			// call pingipornmap and getARPtable
			PingIPorNmap p = new PingIPorNmap();
			p.ping();
			try {
			    Thread.sleep(15000); 
			} catch(InterruptedException ex) {
			 
			}
			GetARPtable ARP = new GetARPtable();
			ARP.get();
			IPtable = ARP.IP2table;
			IPindex = ARP.index;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
        	System.out.println("Input the port");
        	scanner = new Scanner(System.in);
        	port = scanner.nextInt();
        	serversocket = new ServerSocket(port);
        	//serversocket.setSoTimeout(5000);
        	
        	Thread connect = new Thread(ClientStartConnect);  // Connect to Another Server
    		connect.start();
    		
        	while(!serversocket.isClosed()){
        			clientsocket = serversocket.accept();
            		players.add(clientsocket);
            		clientnumber++;
            		if(clientnumber>0){
            			System.out.println(" " + clientnumber + ". Client " + clientsocket.getInetAddress() + " connect!!");
            			/*Thread connect = new Thread(ClientStartConnect);  // Connect to Another Server
                		connect.start();*/
        
            		}
        	}
        	/*
        	Thread connect = new Thread(ClientStartConnect);  // Connect to Another Server
    		connect.start();
    		*/
		}  catch (java.io.IOException e) {
            System.out.println("IP has been using!!!");
        }
	}

	public Runnable ClientStartConnect = new Runnable(){
		public void run(){
			for(int i=0;i<IPindex;i++){
				System.out.println("IP " + (i+1) + " : " + IPtable[i]);
				try {
					Client test = new Client(IPtable[i],port);
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					System.out.println("No connect!!");
					break;
				}
			}			
		}
	};
}
