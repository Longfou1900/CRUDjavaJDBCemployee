package model;

import lombok.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

//    private static DateTimeFormatter hireDatefmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//    private static Long nextId = 1L;
    @NonNull
    private Long id;
    private String firstName;
    private String lastName;
    private Double salary;
    private LocalDate hireDate;

    public Employee(String firstName, String lastName, Double salary, LocalDate hireDate){
//        this.id = nextId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
//        this.hireDate = LocalDate.parse(hireDate.format(hireDatefmt));
        this.hireDate = hireDate;
    }
    //test format
//    static void main() {
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("E-dd-MM-yyyy");
//
//        System.out.println(
//                LocalDate.now().format(fmt)
//        );
//    }

}
