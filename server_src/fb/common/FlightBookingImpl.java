package fb.common;

import java.rmi.RemoteException;

import fb.corba.FlightBookingPOA;

public class FlightBookingImpl extends FlightBookingPOA {
	
	AirlineServerHOPP hopp = new AirlineServerHOPP();
	
	@Override
	public String[] cities() {
		
		try {
			return hopp.cities();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public String[] flights(String fromCity, String toCity, String date) {
		
		try {
			return hopp.flights(fromCity, toCity, date);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean register(String name, String phone, String mail,
			String creditcard) {
		
		try {
			return hopp.regReq(name, phone, mail, creditcard);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean book(String fid, String username) {
		try {
			return hopp.bookReq(fid, username);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}


}
