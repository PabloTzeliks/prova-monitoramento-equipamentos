package pablo.tzeliks.service.contracts;

import pablo.tzeliks.exceptions.AlertaException;
import pablo.tzeliks.model.Alerta;
import pablo.tzeliks.model.Medicao;
import pablo.tzeliks.model.Sensor;

import java.util.List;

public interface AlertaInterface {

    List<Alerta> avaliar(Sensor sensor, Medicao medicao) throws AlertaException;

}
