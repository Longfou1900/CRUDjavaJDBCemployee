package view;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class EmployeeView {

    private final Scanner scanner = new Scanner(System.in);

    //for user input and display
    public EmployeeCreateRequest createEmployee() {

        System.out.println("=== [[ Employees Creation ]] ===");

        String firstname = getStringInput(scanner, "[+] Input FisrtName : ");
//        String firstname;
//        do {
//            System.out.print("[+] Input FirstName: ");
//            firstname = scanner.nextLine();
//        }while (firstname.isBlank() || !firstname.matches("[A-Za-z]{2,}"));

//        System.out.print("[+] Input LastName: ");
//        String lastname = scanner.nextLine();
        String lastname = getStringInput(scanner, "[+] Input LastName : ");

//        System.out.print("[+] Input Salary: ");
//        Double salary = scanner.nextDouble();
        Double salary = getDoubleInput(scanner, "[+] Input Salary: ");

//        // clear buffer
//        scanner.nextLine();

//        System.out.print("[+] Input hireDate(yyyy-mm-dd): ");
//        String hireDate = scanner.nextLine();
        LocalDate hireDate = getDateTimeInput(scanner, "[+] Input hireDate(yyyy-MM-dd): ");

//        LocalDate.parse(hireDate);
        //or
//        String[] parts = hireDate.split("-");
//        int year = Integer.parseInt(parts[0]);
//        int month = Integer.parseInt(parts[1]);
//        int dayOfMonth = Integer.parseInt(parts[2]);

//        LocalDate hire = LocalDate.of(year,month, dayOfMonth);
//        DateTimeFormatter hireDatefmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return new EmployeeCreateRequest(
                firstname, lastname, salary, hireDate
        );

    }

    public Long inputId(){
        while (true){
            System.out.print("[+] Input ID: ");
            try {
//                long id = Long.parseLong(scanner.nextLine());
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Invalid ID format. Please input number");
            }

        }
    }

    private String getStringInput(Scanner sc, String context) {
        while (true) {
            System.out.print(context);
            String input = sc.nextLine();
            if (input.isBlank() || !input.matches("[A-Za-z]{2,}")) {
                System.out.println("Invalid Input. tey again.");
            } else {
                return input;
            }
        }
    }

    private Double getDoubleInput(Scanner sc, String context) {
        while (true) {
            System.out.print(context);
            try {
                Double salary = Double.parseDouble(sc.nextLine());
                if (salary < 0) {
                    System.out.println("Salary can't be negative");
                    continue;
                }
                return salary;
//                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invaid Input has to Number. Try again.");
            }
        }
    }

    private LocalDate getDateTimeInput(Scanner sc, String context) {
        while (true) {
            System.out.print(context);
            String input = sc.nextLine();

            if (!input.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
                System.out.println("Invalid Format for Date(yyyy-mm-dd). Try Again");
            } else {
//                return LocalDate.parse(input);
                LocalDate result = LocalDate.parse(input);
                if (result.isAfter(LocalDate.now())) {
                    System.out.println("Cannot hire employee from the future");
                } else {
                    return result;
                }
            }
        }
    }

    public void displayEmployeeResponse(EmployeeResponse response, String context) {
        Table table = new Table(
                3,
                BorderStyle.CLASSIC

        );

        table.addCell(context, 3);

        table.addCell("ID");
        table.addCell(response.id().toString(), 2);

        table.addCell("FirstName ");
        table.addCell(response.fisrtName(), 2);

        table.addCell("LastName");
        table.addCell(response.lastName(), 2);

        table.addCell("Salary");
        table.addCell(response.salary().toString(), 2);

        table.addCell("HireDate");
        table.addCell(response.hireDate().toString(), 2);

        System.out.println(
                table.render()
        );
    }

    public void displayTableEmployee(List<EmployeeResponse> responses) {
        Table table = new Table(
                5,
                BorderStyle.CLASSIC
        );

        String[] columns = {
                " ID "," Fistrname ", " Lastname ", " Salary ", " Hire Date ",
        };
        for (String column : columns) {
            table.addCell(column);
        }
        responses.forEach(
                user -> {
                    table.addCell(user.id().toString());
                    table.addCell(user.fisrtName());
                    table.addCell(user.lastName());
                    table.addCell(user.salary().toString());
                    table.addCell(user.hireDate().toString());
                }
        );
        System.out.println(table.render());
    }

    public int showMenuAndGetOption() {
        System.out.println("""
                \n  
                |==========[ [ Employee Management ] ]========|
                |   1. Create Employee                        |
                |   2. Update Employee                        |
                |   3. Get All Employee                       |
                |   4. Get Employee by id                     |
                |   5. Delete Employee by id                  |
                |   0. Exit                                   |
                |=============================================|""");
        System.out.print("Choose an option(1-5): ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Scanner getScanner() {
        return scanner;
    }
}
