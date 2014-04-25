package adhoc;

public class RREQ extends AODVgrab{
	private int SourceSeqNum;
	private int HopCount;
	private int Broadcast;
	
	
	public RREQ(){
		
	}
	public RREQ(String sourceAddr, String destAddr, String nextHopAddr,
			 int sourceSequenceNumber, int destinationSequenceNumber, int broadcastId) {
		 super(destAddr, sourceAddr, nextHopAddr);  // return to AODVgrab 
		 pduType = Constants.RREQ_PDU;
		 SourceSeqNum = sourceSequenceNumber;
		 this.Broadcast = broadcastId;  // ??
	}
	
	public int getSourceSeqNum(){
		return SourceSeqNum;
	}
	
	public void setDestSeqNum(int destinationSequenceNumber){
		destSquenceNumber = destinationSequenceNumber;
	}

	public int getHopCount(){
		return HopCount;
	}
	
	public void incrementHopCount(){  // HopCount + 1
        HopCount++;
	}

	public int getBroadcastId(){
         return Broadcast;
	}
	
	
	
}