package by.vadarod.currency_microservice.entity;

import by.vadarod.currency_microservice.entity.enums.AbbreviationEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Aliaksandr_Hryharovich
 * @date 08/07/2024
 */

@Getter
@Setter
@Entity
@Table(name = "currency_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyInfoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "abbreviation")
    @Enumerated(EnumType.STRING)
    private AbbreviationEnum abbreviation;

    @Column(name = "scale")
    private int scale;

    @Column(name = "name")
    private String name;

    @Column(name = "official_rate")
    private float officialRate;
}
