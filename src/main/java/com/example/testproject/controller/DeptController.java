package com.example.testproject.controller;

import com.example.testproject.model.Dept;
import com.example.testproject.model.Result;
import com.example.testproject.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//加注解
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
    //@RequestMapping("/depts", method= RequestMethod.GET)
    @GetMapping
    public Result depts() {
        log.info("search all dept information");
        //using service to search data
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        deptService.addDept(dept);
        return Result.success();
    }
    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id) {
        deptService.updateDept(id);
        return Result.success();
    }

}
