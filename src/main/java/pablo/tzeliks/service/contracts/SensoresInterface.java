package pablo.tzeliks.service.contracts;

import pablo.tzeliks.model.Sensor;
import pablo.tzeliks.model.domain.Codigo;

import java.util.List;

public interface SensoresInterface<T> {

    void cadastrarSensor(T t);
    List<T> listarSensores();
    Sensor acharPorCodigo(Codigo codigo);
    Sensor acharPorId(int id);
    void removerPorCodigo(Codigo codigo);
    void removerPorId(int id);
}
