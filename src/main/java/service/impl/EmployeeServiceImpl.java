package service.impl;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;
import mapper.EmployeeMapper;
import model.Employee;
import repository.EmployeeRepository;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeCreateRequest request) throws EmployeeException {

        Employee employee = mapper.fromEmployeeCreateRequest(request);

        Employee emp = repository.save(employee);

        return mapper.toEmployeeResponse(emp);
    }

    @Override
    public List<EmployeeResponse> getALLEmployees() throws EmployeeException {
        try {
            if (repository.findAll().isEmpty()) {
                throw new EmployeeException("No data yet.");
            }
            return repository.findAll()
                    .stream()
                    //.map(emp -> mapper.toEmployeeResponse(emp))
                    .map(mapper::toEmployeeResponse)
                    .toList();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) throws EmployeeException {
        return repository.findAll().stream()
                .filter(emp -> emp.getId().equals(id))
                .map(mapper::toEmployeeResponse)
                .findFirst()
                .orElseThrow(
                        () -> new EmployeeException("Employee Not Found")
                );
    }

    @Override
    public void deleteEmployeeById(Long id) throws SQLException {
        repository.delete(id);
    }
}
