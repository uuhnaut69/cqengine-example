package com.uuhnaut69.cqengine.model;

import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private int id;

    private String employeeName;

    private String jobTitle;

    private String year;

    private String agency;

    public static final Attribute<Employee, Integer> ID = new SimpleAttribute<Employee, Integer>("id") {
        @Override
        public Integer getValue(Employee o, QueryOptions queryOptions) {
            return o.id;
        }
    };

    public static final Attribute<Employee, String> NAME = new SimpleAttribute<Employee, String>("name") {
        @Override
        public String getValue(Employee o, QueryOptions queryOptions) {
            return o.employeeName;
        }
    };

    public static final Attribute<Employee, String> JOB_TITLE = new SimpleAttribute<Employee, String>("job_title") {
        @Override
        public String getValue(Employee o, QueryOptions queryOptions) {
            return o.jobTitle;
        }
    };

    public static final Attribute<Employee, String> YEAR = new SimpleAttribute<Employee, String>("year") {
        @Override
        public String getValue(Employee o, QueryOptions queryOptions) {
            return o.year;
        }
    };

    public static final Attribute<Employee, String> AGENCY = new SimpleAttribute<Employee, String>("agency") {
        @Override
        public String getValue(Employee o, QueryOptions queryOptions) {
            return o.agency;
        }
    };

}
