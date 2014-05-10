package fb.dao;

import java.util.ArrayList;

import fb.entity.Flight;

public interface FlightDao {
	
	public String[] listCities();//list all available departing cities
	
	public ArrayList<Flight> listFlights(String fromCity, String toCity, String date);
	
	public boolean isTicketExisted(String fid);
	
	public boolean updateTickets(String fid);
	
	public boolean createFlightPlan(String fid, String fromCity, String toCity, String airplane, int rate);
	
	public boolean createFlight(String fid, String date, int seats);
}
