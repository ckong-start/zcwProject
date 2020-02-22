package exer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.User;

public interface UserMapper {
	public User queryUserById(Integer id);
	public List<User> queryUserByNameOrSex(String name, Integer sex);
	//public List<User> queryUserByNameOrSex(@Param("name")String name, @Param("sex")Integer sex);
	public List<User> queryUserByMap(Map<String, Object> paramMap);
	public int updateUser(User user);
	public List<User> queryUserLikeName(@Param("name")String name);
}
