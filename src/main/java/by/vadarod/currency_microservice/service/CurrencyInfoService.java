package by.vadarod.currency_microservice.service;

import by.vadarod.currency_microservice.dto.CurrencyInfoDto;
import by.vadarod.currency_microservice.dto.RequestParamsDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author Aliaksandr_Hryharovich
 * @date 08/07/2024
 */

public interface CurrencyInfoService {

    Page<CurrencyInfoDto> getCurrencyInfoByDate(final @NotNull LocalDate date, final int page, final int size) throws IOException;
    CurrencyInfoDto getCurrencyInfoByDateAndAbbr(final @NotNull RequestParamsDto requestParamsDto);


}
