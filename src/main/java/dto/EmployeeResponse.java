package dto;

import java.time.LocalDate;

public record EmployeeResponse(
        Long id,

        String fisrtName,

        String lastName,

        Double salary,

        LocalDate hireDate

) {
}
