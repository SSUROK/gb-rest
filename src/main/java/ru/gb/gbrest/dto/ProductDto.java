package ru.gb.gbrest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.gb.gbrest.entity.enums.Status;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @Digits(integer = 5, fraction = 2, message = "мое кастомное сообщение")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal cost;
    @PastOrPresent
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate manufactureDate;
    @NotNull
    private Status status;

    private String manufacturer;
}
