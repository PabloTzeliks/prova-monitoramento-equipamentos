package pablo.tzeliks.dto;

import pablo.tzeliks.model.Sensor;
import pablo.tzeliks.model.domain.Codigo;

import java.time.LocalDateTime;

public record MedicaoDTO(int id, double valor, Codigo codigo, LocalDateTime dataHora) {
}
