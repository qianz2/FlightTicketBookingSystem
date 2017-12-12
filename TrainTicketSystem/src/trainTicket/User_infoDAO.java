package trainTicket;
/* 客户信息的DAO接口
 * 增删改查客户信息功能
 */
public interface User_infoDAO {
	//删除客户信息
	public boolean delete_user_info(String user_account);
	//添加新客户信息
	public boolean add_user_info(User_info info);
	//查询客户信息 (基础查询)
	public User_info find_user_info_byAccount(String user_account);
	//查询客户信息(高级查询)
	
	//更新客户信息
	public boolean update_user_password(String user_account, String password);
	public boolean update_user_name(String user_account, String user_name);
	public boolean update_user_phone_number(String user_account, double phone_number);
	
}
