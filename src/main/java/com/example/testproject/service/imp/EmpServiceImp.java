package com.example.testproject.service.imp;

import com.example.testproject.aop.MyLog;
import com.example.testproject.mapper.EmpMapper;
import com.example.testproject.model.Emp;
import com.example.testproject.model.PageBean;
import com.example.testproject.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImp implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean findPageEmp(Integer page, Integer pageSize, String name, String gender, LocalDate begin, LocalDate end ){
        Long count = empMapper.count();
        Integer start = (page - 1) * pageSize;

        List<Emp> empList = empMapper.findPageEmp(start, pageSize, name, gender, begin, end);
        PageBean pageBean = new PageBean(count, empList);
        return pageBean;
    };
    @MyLog
    @Override
    public void delete(List<Integer> ids){
        empMapper.deleteEmps(ids);
    }
    @MyLog
    @Override
    public void add(Emp emp){
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }
    @MyLog
    @Override
    public void update(Emp emp){
        empMapper.update(emp);
    }

    @Override
    public Emp findById(Integer id){
        return empMapper.findById(id);
    }

    @Override
    public Emp login(Emp emp){
        return empMapper.login(emp);
    }
}
