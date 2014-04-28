package fb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fb.dao.FlightDao;
import fb.entity.Flight;
import fb.util.DBUtil;

public class FlightDaoImpl implements FlightDao {

	@Override
	public ArrayList<Flight> listFlights(String fromCity, String toCity,
			String date) {
		
		String sql = "select flight.fid, depart_date, seats, from_city, to_city, airplane, rate " +
				"from flight left join flight_plan on flight.fid = flight_plan.fid " +
				"where from_city=? and to_city=? and depart_date=?";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fromCity);
			pstmt.setString(2, toCity);
			pstmt.setDate(3, toSqlDate(date));
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Flight> list = new ArrayList<Flight>();
			
			while(rs.next()){
				String fid = rs.getString("fid");
				String airplane = rs.getString("airplane");
				int rate = rs.getInt("rate");
				int seats = rs.getInt("seats");
				Flight f = new Flight();
				f.setFid(fid);
				f.setFrom_city(fromCity);
				f.setTo_city(toCity);
				f.setDepart_date(date);
				f.setAirplane(airplane);
				f.setRate(rate);
				f.setSeats(seats);
				list.add(f);
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}
	
	/**
	 * String --> java.util.date --> java.sql.date
	 * @param str
	 * @return
	 */
	private java.sql.Date toSqlDate(String str) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
		
		try {
			java.util.Date date = sdf.parse(str);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			return sqlDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
