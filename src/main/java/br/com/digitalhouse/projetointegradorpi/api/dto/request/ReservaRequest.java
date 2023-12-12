package br.com.digitalhouse.projetointegradorpi.api.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequest {
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Schema(example = "20/12/2023 08:00")
    private LocalDateTime dataInicial;
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Schema(example = "25/12/2023 08:00")
    private LocalDateTime dataFinal;
    @NotNull
    private UUID carroId;
    @NotNull
    private UUID usuarioId;

}
