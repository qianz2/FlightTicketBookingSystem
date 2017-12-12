package trainTicket;
/* �ͻ���Ʊ��ϢDAO�ӿ�
 * ��ɾ�Ĳ�ͻ���Ʊ��Ϣ
 */
public interface Passenger_infoDAO {
	//����
	public boolean add_passenger_info(Passenger_info info);
	//ɾ��
	public boolean delete_passenger_info(String user_account,String flight);
	//��ѯ
	public Passenger_info[] find_passenger_byAccount(String user_account);
	//����
	public void update_passenger_name(String user_account, String name);
	public void update_passenger_id_number(String user_account, double id);
	public void update_passenger_flight(String user_account, String flight);
	public void update_passenger_start_station(String user_account, String start_station);
	public void update_passenger_arrived_station(String user_account, String arrived_station);
}
