package fb.common;

import java.rmi.RemoteException;

public interface FlightBookingService {
	
	 public String[] cities() throws RemoteException;
	 
	 public String[] flights(String fromCity, String toCity, String departTime) throws RemoteException;
	
	 public boolean regReq(String name, String phone,String mail,String creditcard) throws RemoteException;
	 
	 public boolean bookReq(String fid, String name) throws RemoteException;
}
