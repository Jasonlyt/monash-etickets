package fb.common;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;

public class ProxyServerHOPP {
	
	/**
	 * If have some other airline servers, named like hopp4AC, hopp4CEA
	 */
	protected ProxyClientHOPP hopp4AC;
	private static String acPort;

	static {
		Properties props = new Properties();
		// get the class loader
		InputStream is = ProxyServerHOPP.class.getClassLoader()
				.getResourceAsStream("fb/util/corbaConfig.properties");
		try {
			props.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		acPort = props.getProperty("acport");
	}

	public ProxyServerHOPP() {
		hopp4AC = new ProxyClientHOPP(acPort);
	}

	public String[] cities() {
		try {
			return hopp4AC.cities();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] flights(String fromCity, String toCity, String departTime) {
		
		try {
			return hopp4AC.flights(fromCity, toCity, departTime);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean regReq(String name, String phone, String mail,
			String creditcard) {
		try {
			return hopp4AC.regReq(name, phone, mail, creditcard);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean bookReq(String fid, String name) {
		try {
			return hopp4AC.bookReq(fid, name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

}
