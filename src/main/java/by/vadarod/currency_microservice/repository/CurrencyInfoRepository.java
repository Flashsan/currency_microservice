package by.vadarod.currency_microservice.repository;

import by.vadarod.currency_microservice.entity.CurrencyInfoEntity;
import by.vadarod.currency_microservice.entity.enums.AbbreviationEnum;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * @author Aliaksandr_Hryharovich
 * @date 08/07/2024
 */

@Repository
public interface CurrencyInfoRepository extends JpaRepository<CurrencyInfoEntity, Long> {
    CurrencyInfoEntity findByAbbreviationAndDate(final AbbreviationEnum abbreviation, final LocalDate date);
}
