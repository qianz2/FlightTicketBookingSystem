package trainTicket;
/* �ͻ���Ϣ��DAO�ӿ�
 * ��ɾ�Ĳ�ͻ���Ϣ����
 */
public interface User_infoDAO {
	//ɾ���ͻ���Ϣ
	public boolean delete_user_info(String user_account);
	//����¿ͻ���Ϣ
	public boolean add_user_info(User_info info);
	//��ѯ�ͻ���Ϣ (������ѯ)
	public User_info find_user_info_byAccount(String user_account);
	//��ѯ�ͻ���Ϣ(�߼���ѯ)
	
	//���¿ͻ���Ϣ
	public boolean update_user_password(String user_account, String password);
	public boolean update_user_name(String user_account, String user_name);
	public boolean update_user_phone_number(String user_account, double phone_number);
	
}
