package exer2.mapper;

import pojo.Clazz;

public interface ClazzMapper {
	public Clazz queryClazzByIdForSimple(Integer id);
	public Clazz queryClazzByIdForTwoStep(Integer id);
}
