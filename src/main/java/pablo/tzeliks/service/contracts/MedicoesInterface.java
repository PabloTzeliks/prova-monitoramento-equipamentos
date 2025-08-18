package pablo.tzeliks.service.contracts;

import pablo.tzeliks.model.domain.Codigo;

import java.util.List;

public interface MedicoesInterface<T> {

    List<T> listarHistoricoMedicoes(Codigo codigo);
}