package com.uuhnaut69.cqengine.service.impl;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.resultset.ResultSet;
import com.uuhnaut69.cqengine.model.Employee;
import com.uuhnaut69.cqengine.service.EmployeeService;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;


@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    IndexedCollection<Employee> employees = new ConcurrentIndexedCollection<>();

    @Override
    public void generateData(String csvPath) throws IOException {
        CsvReader csvReader = new CsvReader();
        csvReader.setContainsHeader(true);
        CsvContainer csv = csvReader.read(Paths.get(csvPath), StandardCharsets.UTF_8);
        csv.getRows().forEach(csvRow ->
                employees.add(
                        Employee.builder()
                                .id(Integer.valueOf(csvRow.getField("Id")))
                                .employeeName(csvRow.getField("EmployeeName"))
                                .jobTitle(csvRow.getField("JobTitle"))
                                .year(csvRow.getField("Year"))
                                .agency(csvRow.getField("Agency"))
                                .build())
        );
    }

    @Override
    public ResultSet<Employee> findEmployeeById(int id) {
        return null;
    }

    @Override
    public ResultSet<Employee> findEmployeeByJobTitle(String param) {
        return null;
    }
}
