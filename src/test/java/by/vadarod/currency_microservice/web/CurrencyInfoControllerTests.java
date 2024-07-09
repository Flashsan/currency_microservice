package by.vadarod.currency_microservice.web;

import by.vadarod.currency_microservice.dto.CurrencyInfoDto;
import by.vadarod.currency_microservice.dto.RequestParamsDto;
import by.vadarod.currency_microservice.service.CurrencyInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.query.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static by.vadarod.currency_microservice.utils.RestPaths.CURRENCY_INFO_API;
import static org.h2.value.ValueToObjectConverter.readValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CurrencyInfoControllerTests {

    public static final String DATE = "2024-07-08";
    public static final LocalDate DATE_ABR = LocalDate.of(2024, 07, 8);
    public static final String USD = "USD";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private CurrencyInfoService currencyInfoService;

    public <T> T convertJSONStringToObject(String json, Class<T> objectclass) throws Exception {
        return objectMapper.readValue(json, objectclass);
    }

    @Test
    @DisplayName("Get currency info by date.")
    void getCurrencyInfoByDate() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/" + CURRENCY_INFO_API + "/byDate?date=" + DATE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get currency info by date and abbreviation.")
    void getCurrencyInfoByDateAndAbr() throws Exception {

        RequestParamsDto requestParamsDto =
                RequestParamsDto.builder()
                        .date(LocalDate.of(2024, 07,8))
                        .abbreviation(USD)
                        .build();

        CurrencyInfoDto currencyInfoDto = currencyInfoService.getCurrencyInfoByDateAndAbbr(requestParamsDto);
        Assertions.assertEquals(USD,currencyInfoDto.getCurAbbreviation());

    }
}
