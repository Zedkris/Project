package adhoc.udp;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.naming.SizeLimitExceededException;

import adhoc.aodv.Constants;
import adhoc.aodv.exception.DataExceedsMaxSizeException;
import arp.NMAP;

public class UdpSender {
	private DatagramSocket datagramSocket;
	private int receiverPort = 8888;
	//private String subNet = "10.0.1.";
	public static String IPAddress = "read IP error!";
	private static int[] ipsrc = new int[999];
	private int srcindex = 0; // 10.0.1.86
								// ipsrc[0]=10
								// ipsrc[1]=0
	  							// ipsrc[2]=1
	
	public UdpSender() throws SocketException, UnknownHostException, BindException{
	    datagramSocket = new DatagramSocket(8881);
	}

	/**
	 * Sends data using the UDP protocol to a specific receiver
	 * @param destinationNodeID indicates the ID of the receiving node. Should be a positive integer.
	 * @param data is the message which is to be sent. 
	 * @throws IOException 
	 * @throws SizeLimitExceededException is thrown if the length of the data to be sent exceeds the limit
	 */
	public boolean sendPacket(int destinationNodeID, byte[] data) throws IOException, DataExceedsMaxSizeException{
		if(data.length <= Constants.MAX_PACKAGE_SIZE){
			int index = 0;    					// 38~87 讀取網域名稱
			String fullip = ""; 
			NMAP nmap = new NMAP();
			IPAddress=nmap.checkIPAddress();
			for(int i=0;i<IPAddress.length();i++)
			 { 
				 if(IPAddress.substring(i,i+1).equals(" "))
				 {
					 fullip=IPAddress.substring(0,i);
					 i=IPAddress.length();
				 }
			 }
			int test=0;
			for(int i=0;i<fullip.length();i++)
			 { 
				 if(fullip.substring(i,i+1).equals("."))
				 {
					 ipsrc[index]=Integer.parseInt(fullip.substring(test,i));
					 index++;
					 test=i+1;
				 }
				 if(index==3)
				 {
					 int temp = fullip.length()-test;
					 //ipsrc[index]=Integer.parseInt(fullip.substring(test,test+2));
					 if(temp==1){
						 if(fullip.substring(test,test+1).equals("1") || fullip.substring(test,test+1).equals("2") || fullip.substring(test,test+1).equals("3") || fullip.substring(test,test+1).equals("4") ||
								 fullip.substring(test,test+1).equals("5") || fullip.substring(test,test+1).equals("6") || fullip.substring(test,test+1).equals("7") ||
								 fullip.substring(test,test+1).equals("8") || fullip.substring(test,test+1).equals("9") || fullip.substring(test,test+1).equals("0")){
							 ipsrc[index]=Integer.parseInt(fullip.substring(test,test+1));
						 }
					 }
					 else if(temp==2){
						 if(fullip.substring(test+1,test+2).equals("1") || fullip.substring(test+1,test+2).equals("2") || fullip.substring(test+1,test+2).equals("3") || fullip.substring(test+1,test+2).equals("4") ||
								 fullip.substring(test+1,test+2).equals("5") || fullip.substring(test+1,test+2).equals("6") || fullip.substring(test+1,test+2).equals("7") ||
								 fullip.substring(test+1,test+2).equals("8") || fullip.substring(test+1,test+2).equals("9") || fullip.substring(test+1,test+2).equals("0")){
							 ipsrc[index]=Integer.parseInt(fullip.substring(test,test+2));
						 }
					 }
					 else if(temp==3){
						 if(fullip.substring(test+2,test+3).equals("1") || fullip.substring(test+2,test+3).equals("2") || fullip.substring(test+2,test+3).equals("3") || fullip.substring(test+2,test+3).equals("4") ||
								 fullip.substring(test+2,test+3).equals("5") || fullip.substring(test+2,test+3).equals("6") || fullip.substring(test+2,test+3).equals("7") ||
								 fullip.substring(test+2,test+3).equals("8") || fullip.substring(test+2,test+3).equals("9") || fullip.substring(test+2,test+3).equals("0")){
							 ipsrc[index]=Integer.parseInt(fullip.substring(test,test+3));
						 }
					 }		 
				 }
			 }
			
				InetAddress IPAddress = InetAddress.getByName(ipsrc[0]+"."+ipsrc[1]+"."+ipsrc[2]+"."+destinationNodeID);
				//do we have a packet to be broadcasted?
				DatagramPacket sendPacket;
				if(destinationNodeID == Constants.BROADCAST_ADDRESS){
					datagramSocket.setBroadcast(true);
					sendPacket = new DatagramPacket(data, data.length, IPAddress, receiverPort+1);
				}else {
					datagramSocket.setBroadcast(false);
					sendPacket = new DatagramPacket(data, data.length, IPAddress, receiverPort);
				}
				
				datagramSocket.send(sendPacket);
				return true;
			} else {
				throw new DataExceedsMaxSizeException();
			}
	}
	
	public void closeSoket(){
		datagramSocket.close();
	}

}
