package pablo.tzeliks.service.contracts;

import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.exceptions.AlertaException;
import pablo.tzeliks.model.Alerta;
import pablo.tzeliks.model.Medicao;
import pablo.tzeliks.model.Sensor;
import pablo.tzeliks.service.SensorService;

import java.util.List;

// Aqui eu iria possuir uma lógica de Avaliação de Medições, para basear caso diferentes tipos de Alertas poderiam ser lançados.
// Porém para se adequar melhor aos critérios e requisitos trazidos, resolvi deixar mais simples como versão inicial.

public interface AlertaInterface {

    void salvarAlerta(Alerta alerta) throws AlertaException;
    void verificarAlertas(List<SensorDTO> todosOsSensores, SensorService sensorService);
    void listarSensoresCriticos(SensorService sensorService);

}