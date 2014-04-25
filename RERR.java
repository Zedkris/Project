package adhoc;

import java.util.ArrayList;

public class RERR extends AODVgrab{
	private String unreachableNodeAddress;
    private int unreachableNodeSequenceNumber;
    private ArrayList<Integer> destAddresses = new ArrayList<Integer>();
    
    public RERR(){
        
    }
    
    public RERR(String unreachableNodeAddress ,int unreachableNodeSequenceNumber, ArrayList<Integer> destinationAddresses) {
        this.unreachableNodeAddress = unreachableNodeAddress;
        this.unreachableNodeSequenceNumber = unreachableNodeSequenceNumber;
        pduType = Constants.RERR_PDU;
        destAddresses = destinationAddresses;
       // destAddress = -1;
    }

    public String getUnreachableNodeAddress(){
        return unreachableNodeAddress;
    }

    public int getUnreachableNodeSequenceNumber(){
        return unreachableNodeSequenceNumber;
    }

    public ArrayList<Integer> getAllDestAddresses(){
        return destAddresses;
    }

}
