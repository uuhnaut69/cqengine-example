package com.uuhnaut69.cqengine.controller;

import com.uuhnaut69.cqengine.model.Employee;
import com.uuhnaut69.cqengine.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Generate dummy data for test
     *
     * @param csvPath
     * @throws IOException
     */
    @PostMapping
    public void generateDummyData(@RequestParam(name = "csvPath", defaultValue = "") String csvPath) throws IOException {
        employeeService.generateData(csvPath);
    }

    /**
     * Find by id using cq engine
     *
     * @param id
     * @return Set {@link Employee}
     */
    @GetMapping("/cq/{id}")
    public Set<Employee> cqFindById(@PathVariable int id) {
        return employeeService.findEmployeeById(id);
    }

    /**
     * Autocomplete using cq engine
     *
     * @param name
     * @return Set {@link Employee}
     */
    @GetMapping("/cq/autocomplete")
    public Set<Employee> cqAutocomplete(@RequestParam(value = "name", defaultValue = "") String name) {
        return employeeService.findEmployeeHasNameStartWith(name);
    }

    /**
     * Find employee by job title using cq engine
     *
     * @param param
     * @return Set {@link Employee}
     */
    @GetMapping("/cq/by-job-title")
    public Set<Employee> cqFindEmployeeByJobTitle(@RequestParam(name = "param") String param) {
        return employeeService.findEmployeeByJobTitle(param);
    }

    /**
     * Find employee by job title using normal java stream
     *
     * @param param
     * @return Set {@link Employee}
     */
    @GetMapping("/nm/by-job-title")
    public Set<Employee> normalFindEmployeeByJobTitle(@RequestParam(name = "param") String param) {
        return employeeService.normalFindEmployeeJobTitle(param);
    }
}
