package com.uuhnaut69.cqengine.service.impl;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.index.hash.HashIndex;
import com.googlecode.cqengine.index.navigable.NavigableIndex;
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
import java.util.Set;
import java.util.stream.Collectors;

import static com.googlecode.cqengine.query.QueryFactory.equal;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    IndexedCollection<Employee> employees = new ConcurrentIndexedCollection<>();

    @Override
    public void generateData(String csvPath) throws IOException {
        employees.addIndex(NavigableIndex.onAttribute(Employee.ID));
        employees.addIndex(HashIndex.onAttribute(Employee.NAME));
        employees.addIndex(HashIndex.onAttribute(Employee.JOB_TITLE));
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
        Query<Employee> idQuery = equal(Employee.ID, 2);
        employees.retrieve(idQuery).forEach(System.out::println);
    }

    @Override
    public Set<Employee> findEmployeeById(int id) {
        Query<Employee> idQuery = equal(Employee.ID, id);
        return employees.retrieve(idQuery).stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> findEmployeeByJobTitle(String param) {
        Query<Employee> jobTitleQuery = equal(Employee.JOB_TITLE, param);
        return employees.retrieve(jobTitleQuery).stream().collect(Collectors.toSet());
    }
}
