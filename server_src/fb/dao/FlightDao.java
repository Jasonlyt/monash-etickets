package fb.dao;

import java.util.ArrayList;

import fb.entity.Flight;

public interface FlightDao {
	public ArrayList<Flight> listFlights(String fromCity, String toCity, String date);
}
