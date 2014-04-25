package adhoc;

public class AODVgrab {
	protected int destSquenceNumber;
	protected byte pduType;  // Type
	protected String DestinationAddr;
	protected String SourceAddr;
	protected String NextHopAddr;
	public AODVgrab(){
		
	}
	public AODVgrab(String DestAddr, String SourAddr,String nextHopaddr){ //get the information 
		DestinationAddr = DestAddr;
		SourceAddr = SourAddr;
		NextHopAddr = nextHopaddr;
	}
	
	public String GetSourceAddr(){  
		return SourceAddr;
	}
	
	public String GetDestinationAddr(){
		return DestinationAddr;
	}
	
	public String GetNextHopAddr(){
		return NextHopAddr;
	}
	
	
}
