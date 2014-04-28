package fb.business;

import java.rmi.RemoteException;
import java.util.ArrayList;

import fb.client.FlightBookingClientHOPP;
import fb.entity.Flight;

public class FlightBookingJSP {
	public static ArrayList<?> getFlights(String fromCity, String toCity, String date){
		
		FlightBookingClientHOPP clientHOPP = new FlightBookingClientHOPP();
		try {
			String[] departFlights = clientHOPP.flights(fromCity, toCity, date);
			ArrayList<Flight> flist = new ArrayList<Flight>();
			for (String s : departFlights) {
				String[] arr = s.split(",");
				Flight f = new Flight();
				f.setFid(arr[0]);
				f.setFrom_city(arr[1]);
				f.setTo_city(arr[2]);
				f.setDepart_date(arr[3]);
				f.setAirplane(arr[4]);
				f.setRate(Integer.valueOf(arr[5]));
				f.setSeats(Integer.valueOf(arr[6]));
				flist.add(f);
			}
			return flist;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
