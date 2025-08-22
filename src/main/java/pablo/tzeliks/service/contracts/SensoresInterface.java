package pablo.tzeliks.service.contracts;

import pablo.tzeliks.model.domain.Codigo;

import java.util.List;

public interface SensoresInterface<T> {

    void cadastrarSensor(T t);
    List<T> listarSensores();
    List<T> verificarSensoresCriticos();
    List<T> listarSensoresCriticos();
    T acharPorCodigo(Codigo codigo);
    T acharPorId(int id);
    void removerPorCodigo(Codigo codigo);
    void removerPorId(int id);
}
