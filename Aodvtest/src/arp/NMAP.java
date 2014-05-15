package arp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NMAP {
 public static String physicalAddress = "read MAC error!";
 public static String physicalAddress1 = "read MAC error!";
 public static String IPAddress = "read IP error!";
 public static String IPAddress1 = "read IP error!";
 public NMAP() {
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
 public static String checkPhysicalAddress1() {
	 try {
		 String line;
		 Process process = Runtime.getRuntime().exec("ifconfig");
		 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		 while ((line = bufferedReader.readLine()) != null) {
			 if (line.indexOf("wlan1     Link encap:Ethernet  HWaddr") != -1) {
				 if (line.indexOf("HWaddr ") != -1) {
					  physicalAddress1 = line.substring(line.indexOf("r ") + 2);
					  physicalAddress1 = physicalAddress1.substring(0,physicalAddress1.length()-2);
				 }
			 } 
		 }
		 process.waitFor();
  		 } catch (Exception e) {
  			 e.printStackTrace();
  		 }
  	return physicalAddress1;
 }
 public static String checkIPAddress() {
	try {
		String line;
		Process process = Runtime.getRuntime().exec("ifconfig");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		while ((line = bufferedReader.readLine()) != null) {
			if (line.indexOf("wlan0     Link encap:Ethernet  HWaddr") != -1) {
				line = bufferedReader.readLine();
				if (line.indexOf("          inet addr:") != -1) {
				IPAddress = line.substring(20,34);		  
				}
			}
		} 
		process.waitFor();
	  	} catch (Exception e) {
	  		e.printStackTrace();
	  	}
	return IPAddress;
}
 
 public static String checkIPAddress1() {
		try {
			String line;
			Process process = Runtime.getRuntime().exec("ifconfig");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = bufferedReader.readLine()) != null) {
				if (line.indexOf("wlan1     Link encap:Ethernet  HWaddr") != -1) {
					line = bufferedReader.readLine();
					if (line.indexOf("          inet addr:") != -1) {
					IPAddress1 = line.substring(20,34);		  
					}
				}
			} 
			process.waitFor();
		  	} catch (Exception e) {
		  		e.printStackTrace();
		  	}
		return IPAddress1;
}
 
 public static void procNMAP(String ip){
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
 
 public static void nmap(){
	 int index = 0;
	 int index1 = 0;
	 String findip = "";  // find "10.0.1." in "10.0.1.X" 
	 String findip1 = ""; 
	 String fullip = "";
	 String fullip1 = "";
	 NMAP.checkIPAddress();
	 NMAP.checkIPAddress1();
	 checkPhysicalAddress();
	 checkPhysicalAddress1();

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
	 
	 for(int i=0;i<IPAddress1.length();i++)
	 {  
		 if(IPAddress1.substring(i,i+1).equals("."))
		 {
			 index1++;
		 }
		 if(index1==3)
		 {
			 findip1=IPAddress1.substring(0,i+1);
			 i=IPAddress1.length();
		 }
	 }
	 
	 for(int i=0;i<IPAddress1.length();i++)
	 { 
		 if(IPAddress1.substring(i,i+1).equals(" "))
		 {
			 fullip1=IPAddress1.substring(0,i+1);
			 i=IPAddress1.length();
		 }
	 }
	 
	 System.out.println("本機的IP地址是 : " +  fullip);
	 System.out.println("本機的IP1地址是 : " +  fullip1);
	 System.out.println("本機的MAC地址是 : " + physicalAddress);
	 System.out.println("本機的MAC1地址是 : " + physicalAddress1);
	 
	 System.out.println("Wlan0");
	 System.out.println(findip);
	 procNMAP(findip);
	 System.out.println("Wlan1");
	 System.out.println(findip1);
	 procNMAP(findip1);
	 
	 /*
	 // nmap -sP 140.115.204.0/24 means ping all IP 140.115.204.x x(0~255)
	 try {
		   String line;
		   Process process = Runtime.getRuntime().exec("nmap -sP " + findip + "0/24");
		   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		   while ((line = bufferedReader.readLine()) != null) {
			   System.out.println(line); 
		   } 
		   process.waitFor();
		   } catch (Exception e) {
		  		e.printStackTrace();
		   }*/
 }
 

 
}


