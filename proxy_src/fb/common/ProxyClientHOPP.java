package fb.common;

import java.rmi.RemoteException;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import fb.corba.FlightBooking;
import fb.corba.FlightBookingHelper;

public class ProxyClientHOPP implements FlightBookingService {
	
	private FlightBooking impl;
	
	/**
	 * init as a client-side
	 * 
	 * @param port
	 */
	public ProxyClientHOPP(String port) {
		try {
			// create and initialize the ORB
			String[] args = { "-ORBInitialPort", port };
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// resolve the Object Reference in Naming
			String name = "FlightBookingProxy";
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
	public boolean regReq(String name, String phone, String mail,
			String creditcard) throws RemoteException {
		return impl.register(name, phone, mail, creditcard);
	}

	@Override
	public boolean bookReq(String fid, String name) throws RemoteException {
		return impl.book(fid, name);
	}

}
