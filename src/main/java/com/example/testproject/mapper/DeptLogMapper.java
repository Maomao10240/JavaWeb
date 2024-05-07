package com.example.testproject.mapper;

import com.example.testproject.model.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Mapper
public interface DeptLogMapper {
    @Insert("insert into deptlog(createTime, description) values (#{createTime}, #{description})")
    void insert(DeptLog deptLog);
}
