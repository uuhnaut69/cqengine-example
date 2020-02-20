package com.uuhnaut69.cqengine.service.impl;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.index.hash.HashIndex;
import com.googlecode.cqengine.index.radix.RadixTreeIndex;
import com.googlecode.cqengine.index.unique.UniqueIndex;
import com.googlecode.cqengine.query.Query;
import com.uuhnaut69.cqengine.model.Employee;
import com.uuhnaut69.cqengine.service.EmployeeService;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.googlecode.cqengine.query.QueryFactory.*;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    IndexedCollection<Employee> employeesCq = new ConcurrentIndexedCollection<>();
    Set<Employee> employees = new HashSet<>();

    @Override
    public void generateData(String csvPath) throws IOException {
        employeesCq.addIndex(UniqueIndex.onAttribute(Employee.ID));
        employeesCq.addIndex(RadixTreeIndex.onAttribute(Employee.NAME));
        employeesCq.addIndex(HashIndex.onAttribute(Employee.JOB_TITLE));
        CsvReader csvReader = new CsvReader();
        csvReader.setContainsHeader(true);
        CsvContainer csv = csvReader.read(Paths.get(csvPath), StandardCharsets.UTF_8);
        csv.getRows().forEach(csvRow ->
                {
                    Employee employee = Employee.builder()
                            .id(Integer.parseInt(csvRow.getField("Id")))
                            .employeeName(csvRow.getField("EmployeeName"))
                            .jobTitle(csvRow.getField("JobTitle"))
                            .year(csvRow.getField("Year"))
                            .agency(csvRow.getField("Agency"))
                            .build();
                    employeesCq.add(employee);
                    employees.add(employee);
                }
        );
    }

    @Override
    public Set<Employee> findEmployeeById(int id) {
        Query<Employee> idQuery = equal(Employee.ID, id);
        return employeesCq.retrieve(idQuery, queryOptions(orderBy(descending(Employee.NAME)))).stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> findEmployeeHasNameStartWith(String param) {
        Query<Employee> startWithQuery = startsWith(Employee.NAME, param);
        return employeesCq.retrieve(startWithQuery).stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> findEmployeeByJobTitle(String param) {
        Query<Employee> jobTitleQuery = equal(Employee.JOB_TITLE, param);
        return employeesCq.retrieve(jobTitleQuery, queryOptions(orderBy(descending(Employee.NAME)))).stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> normalFindEmployeeJobTitle(String param) {
        return employees.stream().filter(employee -> employee.getJobTitle().equals(param)).collect(Collectors.toSet());
    }
}
