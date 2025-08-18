package pablo.tzeliks.service.contracts;

import java.util.List;

public interface CrudSensoresInterface<T> {

    void cadastrarSensor(T t);
    List<T> listarSensores();
    List<T> verificarSensoresCriticos();
    List<T> listarSensoresCriticos();
}
