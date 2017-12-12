package trainTicket;
/* 机票的DAO接口
 * 增删改查机票信息的功能
 */
public interface AirplaneDAO {
	//更新航班信息
	public boolean update_airplane_flight_price(String flight, String start_time, int price);
	public boolean update_airplane_flight_start_time(String flight, String start_time, String new_start_time);
	public boolean update_airplane_flight_arrived_time(String flight, String start_time, String arrived_time);
	public boolean update_airplane_flight_station(String flight, String start_time, String start_station, String arrived_station);
	public boolean update_airplane_flight_total_ticket(String flight, String start_time, int ticket);
	public boolean update_airplane_flight_standby_ticket(String flight, String start_time, int ticket);
	
	//增添新航班
	public boolean add_airplane(Airplane airplane);
	//航班取消
	public boolean delete_airplane(String flight, String start_time);
	//查询航班信息
	public Airplane[] find_airplane(String start_station, String arrive_station, String start_time);
}
