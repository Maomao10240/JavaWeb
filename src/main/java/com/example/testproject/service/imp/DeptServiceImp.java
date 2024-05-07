package com.example.testproject.service.imp;

import com.example.testproject.mapper.DeptLogMapper;
import com.example.testproject.mapper.DeptMapper;
import com.example.testproject.mapper.EmpMapper;
import com.example.testproject.model.Dept;
import com.example.testproject.model.DeptLog;
import com.example.testproject.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
public class DeptServiceImp implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogMapper deptLogMapper;


    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)//事务管理 delete at the same time // 所有异常
    @Override
    public void deleteById(Integer id) {
        try{
            deptMapper.deleteById(id);
            //int i = 1/0;
            empMapper.deleteByDepId(id);
        }finally{
            //Always excuted. require_new will not rerolled
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("Deleted department: " + id);
            deptLogMapper.insert(deptLog);
        }


    }

    @Override
    public void addDept(Dept dept) {
        dept.setCreateTime(now());
        dept.setUpdateTime(now());
        deptMapper.addDept(dept);
    }

    @Override
    public void updateDept(Integer id) {
        deptMapper.updateDept(id);
    }
}
