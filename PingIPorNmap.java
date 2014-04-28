package haha;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingIPorNmap {
 public static String physicalAddress = "read MAC error!";
 public static String IPAddress = "read IP error!";
 public PingIPorNmap() {
 }

 public static String checkPhysicalAddress() {
	 try {
		 String line;
		 Process process = Runtime.getRuntime().exec("ifconfig");
		 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		 while ((line = bufferedReader.readLine()) != null) {
			 if (line.indexOf("wlan0     Link encap:Ethernet  HWaddr") != -1) {
				 if (line.indexOf("HWaddr ") != -1) {
					  physicalAddress = line.substring(line.indexOf("r ") + 2);
					  physicalAddress = physicalAddress.substring(0,physicalAddress.length()-2);
				 }
			 } 
		 }
		 process.waitFor();
  		 } catch (Exception e) {
  			 e.printStackTrace();
  		 }
  	return physicalAddress;
 }
 public static String checkIPAddress() {
	try {
		String line;
		Process process = Runtime.getRuntime().exec("ifconfig");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		while ((line = bufferedReader.readLine()) != null) {
			if (line.indexOf("          inet addr:") != -1) {
				IPAddress = line.substring(20,34);		  
			}
		} 
		process.waitFor();
	  	} catch (Exception e) {
	  		e.printStackTrace();
	  	}
	return IPAddress;
}
 public static void nmap(String ip){
	 // nmap -sP 140.115.204.0/24 means ping all IP 140.115.204.x x(0~255)
	 try {
		   String line;
		   Process process = Runtime.getRuntime().exec("nmap -sP " + ip + "0/24");
		   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		   while ((line = bufferedReader.readLine()) != null) {
			   System.out.println(line); 
		   } 
		   process.waitFor();
		   } catch (Exception e) {
		  		e.printStackTrace();
		   }
 }
 
 public static void PingIP(String ip) {
	  try {
		  String line;
		  // 0~254
		  for(int i=0;i<255;i++){
			  String ip2=ip+i;
			  boolean reachable = (java.lang.Runtime.getRuntime().exec("ping -c 1 -w 1 "+ip2).waitFor()==0);
			  if(reachable){
				  System.out.println("IP is reachable:: "+ip2);
			  }
			  else{
				  System.out.println("IP is not reachable: "+ip2);
			  }
		  }
	   Process process = Runtime.getRuntime().exec("arp -n");
	   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	   while ((line = bufferedReader.readLine()) != null) {
		   System.out.println(line);
	   }
	 } catch (Exception e) {
	   e.printStackTrace();
	   }
}

 public static void ping() throws UnknownHostException {
	 int index = 0;
	 String findip = "";  // find "10.0.1." in "10.0.1.X" 
	 String fullip = "";
	 PingIPorNmap.checkIPAddress();
	 checkPhysicalAddress();

	 for(int i=0;i<IPAddress.length();i++)
	 {  
		 if(IPAddress.substring(i,i+1).equals("."))
		 {
			 index++;
		 }
		 if(index==3)
		 {
			 findip=IPAddress.substring(0,i+1);
			 i=IPAddress.length();
		 }
	 }
	 
	 for(int i=0;i<IPAddress.length();i++)
	 { 
		 if(IPAddress.substring(i,i+1).equals(" "))
		 {
			 fullip=IPAddress.substring(0,i+1);
			 i=IPAddress.length();
		 }
	 }
	 
	 System.out.println("本機的IP地址是 : " +  fullip);
	 System.out.println("本機的MAC地址是 : " + physicalAddress);
	 
	 
	 //PingIP(findip);  // ping ip and receive reply
	 nmap(findip);  // search ip in the subnet
  
 }
}
