package fb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;

import fb.entity.Flight;


public class FlightBookingClient{
	
	protected BufferedReader console;
	protected FlightBookingClientHOPP hopp;
	
	public static void main(String[] args) {
		FlightBookingClient client = new FlightBookingClient();
		client.loop();
	}
	
	/**
	 * Constructor
	 */
	public FlightBookingClient(){
		hopp = new FlightBookingClientHOPP();
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	private void loop() {
		while(true){
			String line = null;
			try {
				System.out.println("==============================");
				System.out.println("Flight Booking request options: "
						+ "CITY | QUERY | BOOK | or enter QUIT to exit.");
				System.out.println("Enter request: ");
				line = console.readLine();
				System.out.println("Request was " + line);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			if(line.equalsIgnoreCase("CITY")){
				city();
			}else if(line.equalsIgnoreCase("QUERY")){
				query();
			}else if(line.equalsIgnoreCase("BOOK")){
				book();
			}else if(line.equalsIgnoreCase("QUIT")){
				quit();
			}
		}
	}

	private void city() {
		try {
			String[] cities = hopp.cities();
			System.out.println("The available cities:");
			for (String str : cities) {
				System.out.println(str);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void quit() {
		System.exit(0);
	}

	private void book() {
		System.out.println("Please input the flights you want to book:");
		System.out.println("Tip: <fid of flight out> <fid of flight back>");
		try {
			String line = console.readLine();
			String[] fids = line.split(" ");
			if(fids.length==2){
				if(hopp.checkFid(fids[0])&&hopp.checkFid(fids[1])){
					System.out.println("Please input your personal information:");
					System.out.println("Tip: <fullname> <phone> <email> <creditcard>");
					line = console.readLine();
					String[] info = line.split(" ");
					if(info.length==4){
						String name = info[0];
						String phone = info[1];
						String email = info[2];
						String creditcard = info [3];
						if(hopp.checkUser(name)&&hopp.checkPhone(phone)&&hopp.checkMail(email)&&hopp.checkCredit(creditcard)){
							if(hopp.regReq(name, phone, email, creditcard)){
								if(hopp.bookReq(fids[0].toUpperCase(), name)&&hopp.bookReq(fids[1].toUpperCase(), name)){
									System.out.println("You have successfully booked your tickets!");
								}else{
									System.out.println("Booking failed!");
								}
							}else{
								System.out.println("Register your infomation failed!");
							}
						}else{
							System.out.println("Something wrong in your personal information.");
						}
					}else{
						System.out.println("Invalid input!");
					}
				}else{
					System.out.println("Illegal FID number");
				}
			}else{
				System.out.println("You can only input two FIDs!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void query() {
		System.out.println("Please input the information like this one:");
		System.out.println("e.g <fromCity> <toCity> <departDate> <returnDate>");
		System.out.println("tip: the date should between 05/01/2014 ~ 05/30/2014");
		try {
			String line = console.readLine();
			String[] arr = line.split(" ");
			if(arr.length==4){
				String fromCity = arr[0];
				String toCity = arr[1];
				String departDate = arr[2];
				String returnDate = arr[3];
				if(hopp.checkCity(fromCity)&&hopp.checkCity(toCity)){
					if(hopp.checkDate(departDate)&&hopp.checkDate(returnDate)){
						ArrayList<Flight> outFlights = getFlights(fromCity, toCity, departDate);
						ArrayList<Flight> backFlights = getFlights(fromCity, toCity, returnDate);
						System.out.println("==Flight Out [from "+fromCity+" to "+toCity+"]========");
						System.out.println("fid\t airplane\t rate");
						printFlights(outFlights);
						System.out.println("==Flight Back [from "+toCity+" to "+fromCity+"]=======");
						System.out.println("fid\t airplane\t rate");
						printFlights(backFlights);
					}else{
						System.out.println("the date should between 05/01/2014 ~ 05/30/2014");
					}
				}else{
					System.out.println("Your city names may have Illegal characters!");
				}
			}else{
				System.out.println("Your input format is wrong!");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * covert the flight array to flight list
	 * 
	 * @param fromCity
	 * @param toCity
	 * @param date
	 * @return
	 */
	public  ArrayList<Flight> getFlights(String fromCity, String toCity, String date){
		try {
			String[] departFlights = hopp.flights(fromCity, toCity, date);
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
	
	public void printFlights(ArrayList<Flight> list){
		for (Flight f : list) {
			System.out.println(f.getFid()+"\t "+f.getAirplane()+"\t\t "+f.getRate());
		}
	}
}
