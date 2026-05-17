package dto;

import java.time.LocalDate;

//data transfer object
public record EmployeeCreateRequest(
        String firstName,
        String lastName,
        Double salary,
        LocalDate hireDate
) {
}
