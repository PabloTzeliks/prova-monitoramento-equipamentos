package pablo.tzeliks.service;

import pablo.tzeliks.dto.MedicaoCreateDTO;
import pablo.tzeliks.dto.MedicaoDTO;
import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.model.domain.Codigo;

import java.util.List;

// Facade da camada Service

public class MonitoramentoService {

    private final SensorService sensorService;
    private final MedicaoService medicaoService;
    private final AlertaService alertaService;

    public MonitoramentoService() {
        this.sensorService = new SensorService();
        this.alertaService = new AlertaService();
        this.medicaoService = new MedicaoService(sensorService, alertaService);
    }

    public void cadastrarSensor(SensorDTO dto) {
        sensorService.cadastrarSensor(dto);
    }

    // Remoção por Código ou ID
    public void removerSensor(Codigo codigo) {
        sensorService.removerPorCodigo(codigo);
    }

    public void removerSensor(int id) {
        sensorService.removerPorId(id);
    }

    public List<SensorDTO> listarSensores() {
        return sensorService.listarSensores();
    }

    public MedicaoDTO adicionarMedicao(String codigoSensor, double valor) {
        Codigo codigo = new Codigo(codigoSensor);
        MedicaoCreateDTO createDTO = new MedicaoCreateDTO(valor, codigo);
        return medicaoService.adicionarMedicao(createDTO);
    }

    public void exibirHistoricoMedicoes(String codigo) {
        medicaoService.exibirHistoricoMedicoes(codigo);
    }

    public void verificarAlertas() {
        List<SensorDTO> sensores = sensorService.listarSensores();
        alertaService.verificarAlertas(sensores, sensorService);
    }

    public void listarSensoresCriticos() {
        alertaService.listarSensoresCriticos(sensorService);
    }

    public SensorDTO acharSensorPorCodigo(Codigo codigo) {
        return sensorService.acharPorCodigo(codigo);
    }

    public SensorDTO acharSensorPorId(int id) {
        return sensorService.acharPorId(id);
    }

}
