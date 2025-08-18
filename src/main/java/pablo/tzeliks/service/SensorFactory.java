package pablo.tzeliks.service;

import org.mapstruct.factory.Mappers;
import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.mapper.SensorMapper;
import pablo.tzeliks.model.Sensor;

public class SensorFactory {

    private static final SensorMapper mapper = Mappers.getMapper(SensorMapper.class);

    public static Sensor fromDTO(SensorDTO dto) {

        try {
            switch(dto.tipo()) {

                case SENSOR_TEMPERATURA: {
                    return mapper.toSensorTemperatura(dto);
                }

                case SENSOR_VIBRACAO: {
                    return mapper.toSensorVibracao(dto);
                }

            }

        } catch (Exception e) {
            System.out.println("Erro! Houve um erro ao Criar o Sensor. Observe: " + e.getMessage());
        }

        return null;
    }
}