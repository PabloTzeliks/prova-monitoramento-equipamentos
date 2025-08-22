package pablo.tzeliks.service.contracts;

import pablo.tzeliks.model.domain.Codigo;

import java.util.List;

public interface MedicoesInterface<T> {

    void adicionarMedicao(T t);
    List<T> listarHistoricoMedicoes(Codigo codigo);

}