package com.example.testproject.service;

import com.example.testproject.model.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();
    void deleteById(Integer id);
    void addDept(Dept dept);
    void updateDept(Integer id);
}
