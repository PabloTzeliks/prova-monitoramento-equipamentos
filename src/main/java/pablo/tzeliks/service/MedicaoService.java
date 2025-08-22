package pablo.tzeliks.service;

import pablo.tzeliks.dto.MedicaoCreateDTO;
import pablo.tzeliks.dto.MedicaoDTO;
import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.mapper.MedicaoMapper;
import pablo.tzeliks.model.Medicao;
import pablo.tzeliks.model.Sensor;

public class MedicaoService {

    private final MedicaoMapper mapper;
    private CrudService service;

    public MedicaoService(MedicaoMapper mapper) {
        this.mapper = mapper;
    }

    public MedicaoDTO registrarMedicao(Sensor sensor, MedicaoCreateDTO dto) {

        Medicao medicao = mapper.toMedicao(dto, id, sensor);
        sensor.adicionarMedicao(medicao);
        return mapper.toDTO(medicao);
    }

}