package trainTicket;

public interface AdminDAO {
		//ɾ������Ա��Ϣ
		public boolean delete_admin_info(String admin_account);
		//��ӹ���Ա��Ϣ
		public boolean add_admin_info(Admin_info info);
		//��ѯ����Ա��Ϣ (������ѯ)
		public Admin_info find_admin_info_byAccount(String admin_account);
		//��ѯ����Ա��Ϣ(�߼���ѯ)
		
		//���¹���Ա��Ϣ
		public boolean update_Admin_password(String admin_account, String password);
		public boolean update_Admin_name(String admin_account, String name);
}
