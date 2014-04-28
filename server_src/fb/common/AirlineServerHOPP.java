package fb.common;

import java.rmi.RemoteException;
import java.util.ArrayList;

import fb.dao.FlightDao;
import fb.dao.impl.FlightDaoImpl;
import fb.entity.Flight;

public class AirlineServerHOPP implements FlightBookingService {

	@Override
	public String[] cities() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] flights(String fromCity, String toCity, String departTime)
			throws RemoteException {
		FlightDao dao = new FlightDaoImpl();
		ArrayList<Flight> flightList = dao.listFlights(fromCity, toCity, departTime);
		String arr[] = new String[flightList.size()];
		
		int i = 0;
		for (Flight f : flightList) {
			arr[i] = f.getFid()+","+f.getFrom_city()+","+f.getTo_city()+","+f.getDepart_date()+","+f.getAirplane()
					+","+f.getRate()+","+f.getSeats();
			i++;
		}
		return arr;
	}

	@Override
	public boolean regReq(String name, String phone, String mail,
			String creditcard) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bookReq(String fid, String name) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
