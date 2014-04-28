package fb.client;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import fb.common.FlightBookingService;
import fb.corba.FlightBooking;
import fb.corba.FlightBookingHelper;

public class FlightBookingClientHOPP implements FlightBookingService {
	
	private FlightBooking impl;
	private static String clientPort;
    
	/**
	 * Initialize
	 */
	static{
        Properties props = new Properties();  
        //get the class loader
        InputStream is = FlightBookingClientHOPP.class.getClassLoader().getResourceAsStream("fb/util/corbaConfig.properties");  
        try {  
            props.load(is);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        clientPort = props.getProperty("clientport");  
    }
	
	
	/**
	 * constructor
	 */
	public FlightBookingClientHOPP(){
	    try {
	        // create and initialize the ORB
	    	String[] args = {"-ORBInitialPort",clientPort};
	        ORB orb = ORB.init(args, null);

	        // get the root naming context
	        org.omg.CORBA.Object objRef = orb
	            .resolve_initial_references("NameService");
	        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

	        // resolve the Object Reference in Naming
	        String name = "FlightBooking";
	        impl = FlightBookingHelper.narrow(ncRef.resolve_str(name));

	        System.out.println("Obtained a handle on server object: " + impl);

	      } catch (Exception e) {
	        System.out.println("ERROR : " + e);
	        e.printStackTrace(System.out);
	      }
	}
	
	@Override
	public String[] cities() throws RemoteException {
		return impl.cities();
	}

	@Override
	public String[] flights(String fromCity, String toCity, String departTime)
			throws RemoteException {
		return impl.flights(fromCity, toCity, departTime);
	}



	@Override
	public boolean bookReq(String fid,String name) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean regReq(String name, String phone, String mail,
			String creditcard) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}



}
