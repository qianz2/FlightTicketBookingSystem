package trainTicket;

public interface AdminDAO {
		//删除管理员信息
		public boolean delete_admin_info(String admin_account);
		//添加管理员信息
		public boolean add_admin_info(Admin_info info);
		//查询管理员信息 (基础查询)
		public Admin_info find_admin_info_byAccount(String admin_account);
		//查询管理员信息(高级查询)
		
		//更新管理员信息
		public boolean update_Admin_password(String admin_account, String password);
		public boolean update_Admin_name(String admin_account, String name);
}
