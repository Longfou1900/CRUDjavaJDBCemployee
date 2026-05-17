package repository;

import config.DbConnection;
//import database.EmployeeDb;
import exceptions.EmployeeException;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//repo for connect to db
public class EmployeeRepository {
    public List<Employee> findAll() {
        List<Employee> emps = new ArrayList<>();

        String qsl = "select * from employees";

        //connection instance
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(qsl);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Employee singleRecord = new Employee();
                singleRecord.setId(resultSet.getLong("id"));
                singleRecord.setFirstName(resultSet.getString("first_name"));
                singleRecord.setLastName(resultSet.getString("last_name"));
                singleRecord.setSalary(resultSet.getDouble("salary"));
                singleRecord.setHireDate(
                        resultSet.getDate("hire_date").toLocalDate()
                );
                emps.add(singleRecord);
            }

        } catch (SQLException e) {
            System.out.println("Error final all.");
        }

        return emps;
    }

    public Employee save(Employee employee) {
        String sql = """
                insert into employees (first_name, last_name, salary, hire_date)
                values(? ,? ,? ,? ) returning *;
                """;
        Employee singleRecord = new Employee();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pts = conn.prepareStatement(sql)) {
            pts.setString(1, employee.getFirstName());
            pts.setString(2, employee.getLastName());
            pts.setDouble(3, employee.getSalary());
            pts.setDate(4, Date.valueOf(employee.getHireDate()));

            try (ResultSet rs = pts.executeQuery()) {
                if (rs.next()){
                    singleRecord.setId(rs.getLong("id"));
                    singleRecord.setFirstName(rs.getString("first_name"));
                    singleRecord.setLastName(rs.getString("last_name"));
                    singleRecord.setSalary(rs.getDouble("salary"));
                    singleRecord.setHireDate(
                            rs.getDate("hire_date").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error insert data");
        }
        return singleRecord;
    }

    public Employee delete(Long id) throws SQLException {
        String sql = """
            DELETE FROM employees
            WHERE id = ?
            """;

        Connection connection = DbConnection.getConnection();
        try (
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new EmployeeException(
                        "Employee ID not found."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
//    private final EmployeeDb employeeDb;
//
//    public EmployeeRepository(EmployeeDb employeeDb){
//        this.employeeDb = employeeDb;
//    }
//
//    public void save(Employee employee){
//        employeeDb.data().add(employee);
//    }
//
//    //for find all data employees
//    public List<Employee> findAll(){
//        return employeeDb.data();
//    }
//
//    //for check data employees has or not
//    public boolean existById(Long id){
//        return employeeDb.data().stream()
//                .anyMatch(emp -> emp.getId().equals(id));
//    }

//}
