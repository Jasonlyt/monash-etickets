package fb.demo;

import java.util.Random;

import fb.dao.FlightDao;
import fb.dao.impl.FlightDaoImpl;

public class Demo2 {
	public static void main(String[] args) {
		
		FlightDao dao = new FlightDaoImpl();
		
		String fid;
		String[] city = {"Beijing","Shanghai","Melbourne","Sydney"};
		String[] airplane = {"777","747","767","757","340"};
		int rate = 100;//180 ~ 220
		String strDate;
		int seats = 100;
		
		for(int i = 1; i<31; i++){
			String departCity = city[0];
			String toCity = city[3];
			if(i<10){
				strDate = "05/0"+i+"/2014";
				fid = "CEA590" + i;
				
			}else{
				strDate = "05/"+i+"/2014";
				fid = "CEA59"+i;
			}
			
			Random r = new Random();
			int j = r.nextInt(5);//0-4
			String plane = airplane[j];
			if(plane.equals("777")){
				seats = 300;
				rate = 220;
			}else if(plane.equals("747")){
				seats = 350;
				rate = 200;
			}
			
			else if(plane.equals("767")){
				seats = 220;
				rate = 180;
			}else if(plane.equals("757")){
				seats = 180;
				rate = 170;
			}else if(plane.equals("340")){
				seats = 300;
				rate = 240;
			}
			
			if(dao.createFlightPlan(fid, departCity, toCity, plane, rate)){
				if(dao.createFlight(fid, strDate, seats))
					System.out.println(i + " success");
			}
			

		}
	}
	
}
