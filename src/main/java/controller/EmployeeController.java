package controller;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;
import service.EmployeeService;
import view.EmployeeView;

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
    }

    public void start() {
        while (true) {
            int option = view.showMenuAndGetOption();
            if (option == 0) {
                System.out.println("!Existing.....");
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
//                   System.out.println("Existing..........!");
//                   break;
//               }
                default -> System.out.println("[!] - Invalid option.");
            }
        }
    }

}
