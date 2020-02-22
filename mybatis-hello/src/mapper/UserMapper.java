package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import pojo.User;

public interface UserMapper {
	/**
	 * 根据id查询User对象
	 * @param id
	 * @return
	 */
	public User queryUserById(Integer id);
	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int saveUser(User user);
	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserById(User user);
	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public int deleteUserById(Integer id);
	/**
	 * 查询全部
	 * 
	 * @return
	 */
	public List<User> queryUsers();
	/**
	 * 查询全部的用户，并保存成为一个Map集合<br/>
	 * key 是每个User对象的id值。<br/>
	 * value 是User对象<br/>
	 *@MapKey 是把查询的结果转换成为map返回<br/>
	 * 这里的key是指你使用哪个属性做为map集合中的key
	 */
	@MapKey("id")
	public Map<Integer, User> queryUserForMap();

}
