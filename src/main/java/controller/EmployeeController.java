package controller;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;
import service.EmployeeService;
import view.EmployeeView;

import java.sql.SQLException;
import java.util.List;

public class EmployeeController {

    private final EmployeeView view;
    private final EmployeeService service;

    public EmployeeController(EmployeeView view, EmployeeService service) {
        this.view = view;
        this.service = service;
    }

    public void create() {

        EmployeeCreateRequest request = view.createEmployee();

        EmployeeResponse response = service.createEmployee(request);

        view.displayEmployeeResponse(response, "===[Created Employee]===");
//        System.out.println(response);
    }

    public void update() {
        try {

            Long id = view.inputId();
            EmployeeResponse oldEmployee = service.getEmployeeById(id);

            view.displayEmployeeResponse( oldEmployee, "Current Employees");
            EmployeeCreateRequest request = view.updateEmployee(oldEmployee);

            EmployeeResponse updatedEmployee = service.updateEmployee(id, request);
            view.displayEmployeeResponse( updatedEmployee, "Updated Employee");

        } catch (EmployeeException e) {
            System.out.println("Not Found 404");
        }
    }

    public void getALl() {

        List<EmployeeResponse> responseList = service.getALLEmployees();

        view.displayTableEmployee(responseList);

//        view.displayTableEmployee(
//                service.getALLEmployees()
//        );
    }

    public void getById() {

        Long id = view.inputId();

        EmployeeResponse response = service.getEmployeeById(id);

        view.displayEmployeeResponse(response, "Employee Details");

    }

    public void delete() {
        try {
            Long id = view.inputId();

            EmployeeResponse response = service.getEmployeeById(id);

            view.displayEmployeeResponse(response, "Employee To Delete");

            System.out.print("Are you sure Delete? (y/n): ");
            String confirm = view.getScanner().nextLine();

            if (confirm.equalsIgnoreCase("y")) {

                service.deleteEmployeeById(id);

                System.out.println("Employee deleted successfully.");
            }

        } catch (EmployeeException | SQLException e) {
            System.out.println("Not Found");
        }
    }

    public void start() {
        while (true) {
            int option = view.showMenuAndGetOption();
            if (option == 0) {
                System.out.println("!Exiting.....");
                System.exit(0);
            }
            switch (option) {
                case 1 -> create();
                case 2 -> update();
                case 3 -> getALl();
                case 4 -> {
                    try {
                        getById();
                    } catch (EmployeeException e) {
                        System.out.println("Info " + e.getMessage());
                    }
                }
                case 5 -> delete();
//               case 0 -> {
//                   System.out.println("Exiting..........!");
//                   break;
//               }
                default -> System.out.println("[!] - Invalid option.");
            }
        }
    }

}
