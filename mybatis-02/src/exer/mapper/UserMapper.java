package exer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.User;

public interface UserMapper {
	public User queryUserById(Integer id);
	public List<User> queryUsersByNameOrSex(@Param("name")String name, @Param("sex")Integer sex);
	public List<User> queryUsersByMap(Map<String, Object> paramMap);
	public List<User> queryUsersLikeName(@Param("name")String name);
}
