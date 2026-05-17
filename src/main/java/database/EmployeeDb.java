package database;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDb {

    private final List<Employee> employees;

    //when u  want constructor employee
    public EmployeeDb(){

        employees = new ArrayList<>();
    }

    // return all employee data
    public List<Employee> data(){
        return employees;
    }



}
