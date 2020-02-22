package exer1.mapper;

import pojo.Key;

public interface KeyMapper {
	public Key queryKeyById(Integer id);
	public Key queryKeyByIdForTwoStep(Integer id);
}
