package exer2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Student;

public interface StudentMapper {
	public List<Student> queryStudentByClazzId(Integer clazzId);
	public List<Student> queryStudentByClazzIdAndLikeName(@Param("clazzId")Integer clazzId, @Param("stuName")String stuName);
}
