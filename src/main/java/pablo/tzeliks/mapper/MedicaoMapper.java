package pablo.tzeliks.mapper;

import org.mapstruct.Mapper;
import pablo.tzeliks.dto.MedicaoDTO;
import pablo.tzeliks.model.Medicao;

@Mapper
public interface MedicaoMapper {
    Medicao toMedicao(MedicaoDTO dto);
    MedicaoDTO toDTO(Medicao entity);
}
