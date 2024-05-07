package com.example.testproject.mapper;

import com.example.testproject.model.OperLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Mapper
public interface OperLogMapper {
    @Insert("insert into operlog(operateUser, operateTime, classMethod, methodName, methodParams, returnValue, costTime) values (#{operateUser}, #{operateTime},#{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    void insert(OperLog operLog);
}
