package pablo.tzeliks.model;

import pablo.tzeliks.exceptions.AlertaException;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoSensor;

import java.util.List;

public class SensorTemperatura extends Sensor {

    public SensorTemperatura(int id, Codigo codigo, String nomeEquipamento, TipoSensor tipoSensor, List<Medicao> historicoMedicoes) {
        super(id, codigo, nomeEquipamento, tipoSensor, historicoMedicoes);
    }

    @Override
    public String toString() {

        return String.format("ID: " + getId()
                + "\nNome: " + getNomeEquipamento()
                + "\nCódigo: " + getCodigo()
                + "\nTipo do Sensor: " + getTipo());
    }
}