package com.example.testproject.controller;

import com.example.testproject.model.Emp;
import com.example.testproject.model.PageBean;
import com.example.testproject.model.Result;
import com.example.testproject.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping//page is the page we try to find
    public Result emps(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, String name, String gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询， {}，{} , {} ,{}, {}, {}", page, pageSize, name, gender, begin, end);

        PageBean pageBean = empService.findPageEmp(page, pageSize, name, gender, begin,end);
        return Result.success(pageBean);
    }

    @DeleteMapping("{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除， ids:{}", ids);
        empService.delete(ids);
        return Result.success();

    }

    @PostMapping
    public Result add(@RequestBody Emp emp) {
        empService.add(emp);
        return Result.success();
    }

    @GetMapping("{id}")
    public Result get(@PathVariable Integer id) {
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("update emp:{}", emp);
        empService.update(emp);
        return Result.success();
    }


}
