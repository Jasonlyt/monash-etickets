package fb.proxy;

import java.io.InputStream;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import fb.common.FlightBookingImpl;
import fb.corba.FlightBooking;
import fb.corba.FlightBookingHelper;

public class ProxyServer {
	public static void main(String[] args) {
		new ProxyServerHandler().start();
	}
}

class ProxyServerHandler extends Thread {
	private static String serverPort;

	@Override
	public void run() {
		CORBAInit();
	}

	/**
	 * class initialize
	 */
	static {
		Properties props = new Properties();
		// get the class loader
		InputStream is = ProxyServerHandler.class.getClassLoader()
				.getResourceAsStream("fb/util/corbaConfig.properties");
		try {
			props.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		serverPort = props.getProperty("clientport");
	}

	public void CORBAInit() {
		String[] arr = { "-ORBInitialPort", serverPort };
		try {
			ORB orb = ORB.init(arr, null);

			POA rootpoa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			FlightBookingImpl impl = new FlightBookingImpl();

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
			FlightBooking href = FlightBookingHelper.narrow(ref);

			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String name = "FlightBooking";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);

			System.out.println("Proxy ready and waiting...");

			orb.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
