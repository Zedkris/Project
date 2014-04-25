package adhoc;

import adhoc.Constants;
import adhoc.BadPduFormatException;

/*
	Reverse route entry consists of
	<Source IPaddress, Source seq. number, number of hops to source node,
	IP address of node from which RREQ was received>
*/
public class RREP extends AODVgrab {
	private int HopCount = 0;
    private int srcSeqNum;
 
    public RREP(){
    }
   
    public RREP(String sourceAddr, String destAddr, String nextHopAddr,
            int sourceSequenceNumber, int destinationSequenceNumber, int hopCount){  
    	super(destAddr, sourceAddr, nextHopAddr);  // return to AODVgrab 
        pduType = Constants.RREP_PDU;  // type
        srcSeqNum = sourceSequenceNumber;
        this.HopCount = hopCount;
    }
          
    public int getHopCount(){ 	 // number of Hop to source node
                return HopCount;
    }
       
    public void incrementHopCount(){  // hopCount + 1
                HopCount++;
    }
       
    public int getDestinationSequenceNumber(){
                return destSquenceNumber;
    }     
}
