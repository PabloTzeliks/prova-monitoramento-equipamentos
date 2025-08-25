package pablo.tzeliks.service.contracts;

import pablo.tzeliks.dto.MedicaoDTO;
import pablo.tzeliks.model.domain.Codigo;

public interface MedicaoInterface<T> {

    MedicaoDTO adicionarMedicao(T t);
    void exibirHistoricoMedicoes(String codigo);

}