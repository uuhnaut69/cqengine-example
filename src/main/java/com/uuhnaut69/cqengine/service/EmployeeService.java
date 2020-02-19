package com.uuhnaut69.cqengine.service;

import com.uuhnaut69.cqengine.model.Employee;

import java.io.IOException;
import java.util.Set;

public interface EmployeeService {

    /**
     * Generate Data
     *
     * @param csvPath
     */
    void generateData(String csvPath) throws IOException;

    /**
     * Find employee by id
     *
     * @param id
     * @return Set {@link Employee}
     */
    Set<Employee> findEmployeeById(int id);

    /**
     * Find employee by job title
     *
     * @param param
     * @return Set {@link Employee}
     */
    Set<Employee> findEmployeeByJobTitle(String param);

}
