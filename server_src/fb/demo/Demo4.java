package fb.demo;

import fb.dao.FlightDao;
import fb.dao.impl.FlightDaoImpl;

public class Demo4 {
	public static void main(String[] args) {
		FlightDao dao = new FlightDaoImpl();
		String[] c = dao.listCities();
		for (String string : c) {
			System.out.println(string);
		}
	}
}
