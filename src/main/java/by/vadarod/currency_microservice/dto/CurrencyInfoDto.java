package by.vadarod.currency_microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Aliaksandr_Hryharovich
 * @date 08/07/2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyInfoDto implements Serializable {
    @JsonProperty("Date")
    private LocalDate date;
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;
    @JsonProperty("Cur_Scale")
    private String curScale;
    @JsonProperty("Cur_Name")
    private String curName;
    @JsonProperty("Cur_OfficialRate")
    private String curOfficialRate;
}
