package com.example.testproject.service;

import com.example.testproject.model.Emp;
import com.example.testproject.model.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    public PageBean findPageEmp(Integer page, Integer pageSize, String name, String gender, LocalDate begin, LocalDate end );
    public void delete(List<Integer> ids);
    public void add(Emp emp);
    public Emp findById(Integer id);
    public void update(Emp emp);
    public Emp login(Emp emp);
}

