package trainTicket;

public class Passenger_info {
	private String user_account;
	private String name;
	private String flight;
	private String star_station;
	private String arrived_station;
	private Double id_number;
	
	public Passenger_info(String user_account, String name, String flight, String star_station, String arrived_station, Double id_number) {
		this.user_account = user_account;
		this.name = name;
		this.flight = flight;
		this.star_station = star_station;
		this.arrived_station = arrived_station;
		this.id_number = id_number;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getStar_station() {
		return star_station;
	}

	public void setStar_station(String star_station) {
		this.star_station = star_station;
	}

	public String getArrived_station() {
		return arrived_station;
	}

	public void setArrived_station(String arrived_station) {
		this.arrived_station = arrived_station;
	}

	public double getId_number() {
		return id_number;
	}

	public void setId_number(double id_number) {
		this.id_number = id_number;
	}


}
