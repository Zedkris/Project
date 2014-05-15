package arp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Object;

public class ARP {
	static String[] IPtable = new String[999];
	static String[] IP2table = new String[999];
	static String[] MACtable = new String[999];	
	// IPtable save IP address
	// IP2table save IP address without"(" ")"
	// MACtable save MAC address
	static int index=0;  // IP 
	static int index2=0;  // MAC
	public static void GetARPtable() {
		try {
			Process process = Runtime.getRuntime().exec("arp -a");
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
		
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			    //System.out.println(ip.getHostName());
				if (line.indexOf("(" ) > -1) {
					StringTokenizer st = new StringTokenizer(line);
					st.nextToken();
					IPtable[index]=st.nextToken();
					index++;
					st.nextToken();
					MACtable[index2]=st.nextToken();
					index2++;
					if(MACtable[index2-1].equals("<incomplete>"))
					{				
						index--;
						index2--;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ARP() {
		NMAP.nmap();
		
		System.out.println("-----------------------------------------------------------------");
		
		TimerTask task = new TimerTask(){  
		public void run(){  
			GetARPtable();  
			System.out.println("-----------------------------------------------------------------");
			for(int i=0;i<index;i++)
			{
				IP2table[i] = IPtable[i].substring(1,IPtable[i].length()-1);
			}
			for(int i=0;i<index;i++)
			{
				if(MACtable[i].equals("<incomplete>"))
					System.out.println("no data");
				else
					System.out.println("IP : "+IP2table[i]+"\tMAC : "+MACtable[i]);
					//System.out.println("IP : "+IPtable[i].substring(1,IPtable[i].length()-1)+"\tMAC : "+MACtable[i]);
			
			}
			System.out.println(index + " hosts online");
			
			
				
		}  
		};  
		Timer timer = new Timer();
	    timer.schedule(task, 15000);
		//GetARPtable();
		
		
		/*for(int i=0;i<index;i++)   // check ip is or isn't reachable
		{
			try {
				if(InetAddress.getByName("10.0.1.86").isReachable(1000))
					System.out.println(IPtable[i] + " is reachable");
				else
					System.out.println(IPtable[i] + " isn't reachable");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}
