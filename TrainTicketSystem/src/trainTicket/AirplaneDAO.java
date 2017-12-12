package trainTicket;
/* ��Ʊ��DAO�ӿ�
 * ��ɾ�Ĳ��Ʊ��Ϣ�Ĺ���
 */
public interface AirplaneDAO {
	//���º�����Ϣ
	public boolean update_airplane_flight_price(String flight, String start_time, int price);
	public boolean update_airplane_flight_start_time(String flight, String start_time, String new_start_time);
	public boolean update_airplane_flight_arrived_time(String flight, String start_time, String arrived_time);
	public boolean update_airplane_flight_station(String flight, String start_time, String start_station, String arrived_station);
	public boolean update_airplane_flight_total_ticket(String flight, String start_time, int ticket);
	public boolean update_airplane_flight_standby_ticket(String flight, String start_time, int ticket);
	
	//�����º���
	public boolean add_airplane(Airplane airplane);
	//����ȡ��
	public boolean delete_airplane(String flight, String start_time);
	//��ѯ������Ϣ
	public Airplane[] find_airplane(String start_station, String arrive_station, String start_time);
}
