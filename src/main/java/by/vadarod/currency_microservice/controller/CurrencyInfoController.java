package by.vadarod.currency_microservice.controller;

import by.vadarod.currency_microservice.dto.CurrencyInfoDto;
import by.vadarod.currency_microservice.dto.RequestParamsDto;
import by.vadarod.currency_microservice.service.CurrencyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

import static by.vadarod.currency_microservice.utils.RestPaths.CURRENCY_INFO_API;

/**
 * @author Aliaksandr_Hryharovich
 * @date 08/07/2024
 */

@RestController()
@RequestMapping(value = CURRENCY_INFO_API)
@RequiredArgsConstructor
public class CurrencyInfoController {

    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";

    private final CurrencyInfoService currencyInfoService;

    @GetMapping("/byDate")
    public ResponseEntity<Page<CurrencyInfoDto>> getCurrencyInfoByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_SIZE) int size) throws IOException {
        return new ResponseEntity<>(currencyInfoService.getCurrencyInfoByDate(date, page, size), HttpStatus.OK);
    }

    @PostMapping("/byDateAndAbbr")
    public ResponseEntity<CurrencyInfoDto> getCurrencyInfoByAbbreviationAndDate(
            @RequestBody RequestParamsDto requestParamsDto) {
        return new ResponseEntity<>(
                currencyInfoService.getCurrencyInfoByDateAndAbbr(requestParamsDto), HttpStatus.OK);
    }
}
