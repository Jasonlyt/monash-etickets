package fb.entity;

public class Flight {
	private String fid;
	private String from_city;
	private String to_city;
	private String airplane;
	private int rate;
	private String depart_date;
	private int seats;
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFrom_city() {
		return from_city;
	}
	public void setFrom_city(String from_city) {
		this.from_city = from_city;
	}
	public String getTo_city() {
		return to_city;
	}
	public void setTo_city(String to_city) {
		this.to_city = to_city;
	}
	public String getAirplane() {
		return airplane;
	}
	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getDepart_date() {
		return depart_date;
	}
	public void setDepart_date(String depart_date) {
		this.depart_date = depart_date;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	
}
