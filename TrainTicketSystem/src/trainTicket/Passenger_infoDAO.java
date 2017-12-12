package trainTicket;
/* 客户购票信息DAO接口
 * 增删改查客户购票信息
 */
public interface Passenger_infoDAO {
	//增添
	public boolean add_passenger_info(Passenger_info info);
	//删除
	public boolean delete_passenger_info(String user_account,String flight);
	//查询
	public Passenger_info[] find_passenger_byAccount(String user_account);
	//更新
	public void update_passenger_name(String user_account, String name);
	public void update_passenger_id_number(String user_account, double id);
	public void update_passenger_flight(String user_account, String flight);
	public void update_passenger_start_station(String user_account, String start_station);
	public void update_passenger_arrived_station(String user_account, String arrived_station);
}
