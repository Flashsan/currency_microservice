package by.vadarod.currency_microservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Aliaksandr_Hryharovich
 * @date 08/07/2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestParamsDto {
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private LocalDate date;

    @NotNull
    private String abbreviation;
}
