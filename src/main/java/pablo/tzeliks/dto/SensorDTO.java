package pablo.tzeliks.dto;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoSensor;

public record SensorDTO(int id, Codigo codigo, String nomeEquipamento, TipoSensor tipo) {
}
