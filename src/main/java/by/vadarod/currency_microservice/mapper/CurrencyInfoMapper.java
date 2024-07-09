package by.vadarod.currency_microservice.mapper;

import by.vadarod.currency_microservice.dto.CurrencyInfoDto;
import by.vadarod.currency_microservice.entity.CurrencyInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Aliaksandr_Hryharovich
 * @date 08/07/2024
 */

@Mapper(componentModel = "spring")
public interface CurrencyInfoMapper {
    @Mappings({
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "curAbbreviation", source = "abbreviation"),
            @Mapping(target = "curScale", source = "scale"),
            @Mapping(target = "curName", source = "name"),
            @Mapping(target = "curOfficialRate", source = "officialRate")
    })
    CurrencyInfoDto toCurrencyInfoDto(CurrencyInfoEntity entity);

    @Mappings({
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "abbreviation", source = "curAbbreviation"),
            @Mapping(target = "scale", source = "curScale"),
            @Mapping(target = "name", source = "curName"),
            @Mapping(target = "officialRate", source = "curOfficialRate")
    })
    CurrencyInfoEntity toCurrencyInfoEntity(CurrencyInfoDto dto);
}

