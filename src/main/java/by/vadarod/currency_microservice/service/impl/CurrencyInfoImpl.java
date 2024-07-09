package by.vadarod.currency_microservice.service.impl;

import by.vadarod.currency_microservice.dto.CurrencyInfoDto;
import by.vadarod.currency_microservice.dto.RequestParamsDto;
import by.vadarod.currency_microservice.entity.CurrencyInfoEntity;
import by.vadarod.currency_microservice.entity.enums.AbbreviationEnum;
import by.vadarod.currency_microservice.mapper.CurrencyInfoMapper;
import by.vadarod.currency_microservice.repository.CurrencyInfoRepository;
import by.vadarod.currency_microservice.service.CurrencyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static by.vadarod.currency_microservice.utils.UtilsService.*;

/**
 * @author Aliaksandr_Hryharovich
 * @date 08/07/2024
 */

@Service
@RequiredArgsConstructor
public class CurrencyInfoImpl implements CurrencyInfoService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final CurrencyInfoRepository currencyInfoRepository;

    private final CurrencyInfoMapper currencyInfoMapper;

    @Override
    public Page<CurrencyInfoDto> getCurrencyInfoByDate(LocalDate date, int page, int size) {
        String url = BASE_URL_API_NBRB_BY + "/exrates/rates?ondate=" + date + "&periodicity=0";

        List<CurrencyInfoEntity> savedEntity = null;
        try {
            List<CurrencyInfoDto> responseDto = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CurrencyInfoDto>>() {
                    }).getBody();

            List<CurrencyInfoEntity> responseEntity = responseDto
                    .stream()
                    .map(currencyInfoDto -> currencyInfoMapper.toCurrencyInfoEntity(currencyInfoDto))
                    .collect(Collectors.toList());

            savedEntity = currencyInfoRepository.saveAll(responseEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Pageable pageRequest = PageRequest.of(page, size);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), savedEntity.size());

        List<CurrencyInfoDto> pageContent = savedEntity
                .stream()
                .map(currencyInfoMapper::toCurrencyInfoDto)
                .collect(Collectors.toList())
                .subList(start, end);

        return new PageImpl<>(pageContent, pageRequest, savedEntity.size());
    }

    @Override
    public CurrencyInfoDto getCurrencyInfoByDateAndAbbr(RequestParamsDto requestParamsDto) {
        AbbreviationEnum abbreviationEnum = getAbbreviation(requestParamsDto.getAbbreviation());

        return currencyInfoMapper.toCurrencyInfoDto(
                currencyInfoRepository
                        .findByAbbreviationAndDate(
                                abbreviationEnum,
                                requestParamsDto.getDate()));
    }

    private AbbreviationEnum getAbbreviation(String abbr) {
        AbbreviationEnum abbreviationEnum = null;

        switch (abbr.toUpperCase()) {
            case ("AUD"):
                abbreviationEnum = AbbreviationEnum.AUD;
                break;
            case ("AMD"):
                abbreviationEnum = AbbreviationEnum.AMD;
                break;
            case ("BGN"):
                abbreviationEnum = AbbreviationEnum.BGN;
                break;
            case ("BRL"):
                abbreviationEnum = AbbreviationEnum.BRL;
                break;
            case ("UAH"):
                abbreviationEnum = AbbreviationEnum.UAH;
                break;
            case ("DKK"):
                abbreviationEnum = AbbreviationEnum.DKK;
                break;
            case ("AED"):
                abbreviationEnum = AbbreviationEnum.AED;
                break;
            case ("USD"):
                abbreviationEnum = AbbreviationEnum.USD;
                break;
            case ("VND"):
                abbreviationEnum = AbbreviationEnum.VND;
                break;
            case ("EUR"):
                abbreviationEnum = AbbreviationEnum.EUR;
                break;
            case ("PLN"):
                abbreviationEnum = AbbreviationEnum.PLN;
                break;
            case ("JPY"):
                abbreviationEnum = AbbreviationEnum.JPY;
                break;
            case ("INR"):
                abbreviationEnum = AbbreviationEnum.INR;
                break;
            case ("IRR"):
                abbreviationEnum = AbbreviationEnum.IRR;
                break;
            case ("ISK"):
                abbreviationEnum = AbbreviationEnum.ISK;
                break;
            case ("CAD"):
                abbreviationEnum = AbbreviationEnum.CAD;
                break;
            case ("CNY"):
                abbreviationEnum = AbbreviationEnum.CNY;
                break;
            case ("KWD"):
                abbreviationEnum = AbbreviationEnum.KWD;
                break;
            case ("MDL"):
                abbreviationEnum = AbbreviationEnum.MDL;
                break;
            case ("NZD"):
                abbreviationEnum = AbbreviationEnum.NZD;
                break;
            case ("NOK"):
                abbreviationEnum = AbbreviationEnum.NOK;
                break;
            case ("RUB"):
                abbreviationEnum = AbbreviationEnum.RUB;
                break;
            case ("XDR"):
                abbreviationEnum = AbbreviationEnum.XDR;
                break;
            case ("SGD"):
                abbreviationEnum = AbbreviationEnum.SGD;
                break;
            case ("KGS"):
                abbreviationEnum = AbbreviationEnum.KGS;
                break;
            case ("KZT"):
                abbreviationEnum = AbbreviationEnum.KZT;
                break;
            case ("TRY"):
                abbreviationEnum = AbbreviationEnum.TRY;
                break;
            case ("GBP"):
                abbreviationEnum = AbbreviationEnum.GBP;
                break;
            case ("CZK"):
                abbreviationEnum = AbbreviationEnum.CZK;
                break;
            case ("SEK"):
                abbreviationEnum = AbbreviationEnum.SEK;
                break;
            case ("CHF"):
                abbreviationEnum = AbbreviationEnum.CHF;
        }
        return abbreviationEnum;
    }

}
