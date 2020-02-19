package com.uuhnaut69.cqengine.service;

import com.googlecode.cqengine.resultset.ResultSet;
import com.uuhnaut69.cqengine.model.Employee;

import java.io.IOException;

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
     * @return ResultSet {@link Employee}
     */
    ResultSet<Employee> findEmployeeById(int id);

    /**
     * Find employee by job title
     *
     * @param param
     * @return ResultSet {@link Employee}
     */
    ResultSet<Employee> findEmployeeByJobTitle(String param);

}
