package fb.common;

import fb.corba.FlightBookingPOA;

public class FlightBookingImpl extends FlightBookingPOA {

	ProxyServerHOPP hopp = new ProxyServerHOPP();

	@Override
	public String[] cities() {
		return hopp.cities();
	}

	@Override
	public String[] flights(String fromCity, String toCity, String date) {
		return hopp.flights(fromCity, toCity, date);
	}

	@Override
	public boolean register(String name, String phone, String mail,
			String creditcard) {
		return hopp.regReq(name, phone, mail, creditcard);
	}

	@Override
	public boolean book(String fid, String username) {
		return hopp.bookReq(fid, username);
	}

}
