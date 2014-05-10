package fb.common;

import java.rmi.RemoteException;
import java.util.ArrayList;

import fb.dao.FlightDao;
import fb.dao.OrderDao;
import fb.dao.UserDao;
import fb.dao.impl.FlightDaoImpl;
import fb.dao.impl.OrderDaoImpl;
import fb.dao.impl.UserDaoImpl;
import fb.entity.Flight;
import fb.entity.Order;
import fb.entity.User;

public class AirlineServerHOPP implements FlightBookingService {

	@Override
	public String[] cities() throws RemoteException {
		FlightDao dao = new FlightDaoImpl();
		String[] cities = dao.listCities();
		return cities;
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
		
		boolean flag = false;
		User user = new User();
		user.setUsername(name);
		user.setPhone(phone);
		user.setMail(mail);
		user.setCreditcard(creditcard);
		UserDao dao = new UserDaoImpl();
		if(!dao.isUserExisted(user.getUsername())){
			flag = dao.insertNewUser(user);
		}else{
			System.out.println("User existed!");
		}
		
		return flag;
	}

	@Override
	public boolean bookReq(String fid, String name) throws RemoteException {
		boolean flag = false;
		
		Order order = new Order();
		order.setFid(fid);
		order.setUsername(name);
		OrderDao dao = new OrderDaoImpl();
		FlightDao fdao = new FlightDaoImpl();
		if(fdao.isTicketExisted(fid)){
			if(!dao.isOrderExisted(order.getFid(), order.getUsername())){
				if(fdao.updateTickets(fid)){
					flag = dao.insertNewOrder(order);
					System.out.println("Insert successfully!");
				}else{
					System.out.println("Update tickets failed!");
				}
			}else{
				System.out.println("Order existed!");
			}
		}else{
			System.out.println("No remaining tickets!");
		}
		return flag;
	}

}
