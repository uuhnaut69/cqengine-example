package com.uuhnaut69.cqengine.controller;

import com.googlecode.cqengine.resultset.ResultSet;
import com.uuhnaut69.cqengine.model.Employee;
import com.uuhnaut69.cqengine.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public void generateDummyData(@RequestParam(name = "csvPath", defaultValue = "") String csvPath) throws IOException {
        employeeService.generateData(csvPath);
    }

    @GetMapping("/{id}")
    public ResultSet<Employee> findById(@PathVariable int id){
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/by-job-title")
    public ResultSet<Employee> findEmployeeByJobTitle(@RequestParam(name = "param") String param) {
        return employeeService.findEmployeeByJobTitle(param);
    }
}
