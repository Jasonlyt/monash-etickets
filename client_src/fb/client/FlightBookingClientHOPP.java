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
		return impl.book(fid, name);
	}

	@Override
	public boolean regReq(String name, String phone, String mail,
			String creditcard) throws RemoteException {
		return impl.register(name, phone, mail, creditcard);
	}

	public boolean checkFid(String fid){
		String fidRE = "(cea|ac|qan)[0-9]{4}";
		return fid.matches(fidRE);
	}
	
	public boolean checkCity(String city){
		String cityRE = "^[A-Za-z]+$";
		return city.matches(cityRE);
	}
	
	public boolean checkDate(String date){
		String dateRE = "05/[0-9]{2}/2014";
		return date.matches(dateRE); 
	}
	
	public boolean checkUser(String name){
		String nameRE = "^[A-Za-z]+$";
		return name.matches(nameRE);
	}
	
	public boolean checkPhone(String phone){
		String phoneRE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		return phone.matches(phoneRE);
	}
	
	public boolean checkMail(String mail){
		String mailRE = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return mail.matches(mailRE);
	}
	
	public boolean checkCredit(String creditcard){
		String creditcardRE = "[0-9]{16}";
		return creditcard.matches(creditcardRE);
	}

}
