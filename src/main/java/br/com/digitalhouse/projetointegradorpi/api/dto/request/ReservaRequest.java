package br.com.digitalhouse.projetointegradorpi.api.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequest {
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Schema(example = "27/04/2023 08:00")
    private LocalDateTime dataInicial;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Schema(example = "22/04/2023 08:00")
    private LocalDateTime dataFinal;
    @NotNull
    private UUID carroId;
    @NotNull
    private UUID usuarioId;

}
