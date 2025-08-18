package pablo.tzeliks.mapper;

import org.mapstruct.Mapper;
import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.model.SensorTemperatura;
import pablo.tzeliks.model.SensorVibracao;

@Mapper
public interface SensorMapper {

    SensorTemperatura toSensorTemperatura(SensorDTO dto);
    SensorDTO toDTO(SensorTemperatura entity);

    SensorVibracao toSensorVibracao(SensorDTO dto);
    SensorDTO toDTO(SensorVibracao entity);
}