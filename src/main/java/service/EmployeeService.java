package service;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;

import java.util.List;

public interface EmployeeService {


    //CRUD
    //for wait to get database as object
    EmployeeResponse createEmployee(EmployeeCreateRequest request) throws EmployeeException;

    List<EmployeeResponse> getALLEmployees() throws EmployeeException;

    EmployeeResponse getEmployeeById(Long id) throws  EmployeeException;

}
