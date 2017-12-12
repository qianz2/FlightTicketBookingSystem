package trainTicket;

public class Airplane {
	private String flight;
	private String start_station;
	private String arrive_station;
	private int flight_price;
	private String start_time;
	private String arrive_time;
	private int total_ticket;
	private int standby_ticket;
	
	public Airplane(String flight, String start_station, String arrive_station, int flight_price, String start_time, String arrive_time, int total_ticket, int standby_ticket) {
		this.flight = flight;
		this.start_station = start_station;
		this.arrive_station = arrive_station;
		this.flight_price = flight_price;
		this.start_time = start_time;
		this.arrive_time = arrive_time;
		this.total_ticket = total_ticket;
		this.standby_ticket = standby_ticket;
	}

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getStart_station() {
		return start_station;
	}

	public void setStart_station(String start_station) {
		this.start_station = start_station;
	}

	public String getArrive_station() {
		return arrive_station;
	}

	public void setArrive_station(String arrive_station) {
		this.arrive_station = arrive_station;
	}

	public int getFlight_price() {
		return flight_price;
	}

	public void setFlight_price(int flight_price) {
		this.flight_price = flight_price;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getArrive_time() {
		return arrive_time;
	}

	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}

	public int getTotal_ticket() {
		return total_ticket;
	}

	public void setTotal_ticket(int total_ticket) {
		this.total_ticket = total_ticket;
	}

	public int getStandby_ticket() {
		return standby_ticket;
	}

	public void setStandby_ticket(int standby_ticket) {
		this.standby_ticket = standby_ticket;
	}
	
}
