package com.example.testproject.mapper;

import com.example.testproject.model.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("select count(*) from emp")
    public Long count();

  // @Select("Select * from emp limit #{start}, #{pageSize}")/
    public List<Emp> findPageEmp(Integer start, Integer pageSize, String name, String gender, LocalDate begin, LocalDate end);

  // @Delete("delete from emp where id in (1,2)")
   public void deleteEmps(List<Integer> ids);

   @Insert("insert into emp(username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time)" + "values(#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{depId}, #{createTime}, #{updateTime})")
   public void add(Emp emp);

   @Select("select * from emp where id = #{id}")
    public Emp findById(Integer id);


   public void update(Emp emp);

   @Select("select * from emp where name = #{username} and password = #{password}")
   public Emp login(Emp emp);

    @Delete("delete from emp where dept_Id = #{id}")
    void deleteByDepId(Integer id);
}
