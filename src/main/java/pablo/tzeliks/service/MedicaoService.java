package pablo.tzeliks.service;

import pablo.tzeliks.dto.MedicaoCreateDTO;
import pablo.tzeliks.dto.MedicaoDTO;
import pablo.tzeliks.exceptions.ServiceException;
import pablo.tzeliks.model.Alerta;
import pablo.tzeliks.model.Medicao;
import pablo.tzeliks.model.Sensor;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.NivelAlerta;
import pablo.tzeliks.service.contracts.MedicaoInterface;

import java.time.LocalDateTime;
import java.util.List;

public class MedicaoService implements MedicaoInterface<MedicaoCreateDTO> {

    private SensorService sensorService;
    private AlertaService alertaService;
    private int novoId = 1;

    public MedicaoService(SensorService sensorService, AlertaService alertaService) {
        this.sensorService = sensorService;
        this.alertaService = alertaService;
    }

    public MedicaoDTO adicionarMedicao(MedicaoCreateDTO createDTO) {

        if (createDTO == null) {
            throw new ServiceException("Dados da Medição inválidos.");
        }

        Sensor sensor = sensorService.acharPorCodigoEntidade(createDTO.codigo());
        if (sensor == null) {
            throw new ServiceException("Sensor não encontrado: " + createDTO.codigo());
        }

        LocalDateTime agora = LocalDateTime.now();
        Medicao medicao = new Medicao(novoId++, sensor, createDTO.valor(), agora);

        boolean temAlerta = sensor.verificarSensor(medicao);

        if (temAlerta) {

            Alerta alerta = Alerta.of(sensor, medicao, NivelAlerta.ALERTA_CRITICO);
            alertaService.salvarAlerta(alerta);

            System.out.println("✅ Medição registrada com sucesso!");
            System.out.printf("\n⚠️ ALERTA: Medição de %.2f fora do limite técnico!\n", createDTO.valor());
        } else {
            System.out.println("✅ Medição registrada com sucesso!");
        }

        // 8. Retornar DTO completo com dados gerados pelo sistema
        return new MedicaoDTO(medicao.getId(), createDTO.valor(), createDTO.codigo(), agora);

    }


    public void exibirHistoricoMedicoes(String codigoString) {

        Codigo codigo = new Codigo(codigoString);
        Sensor sensor = sensorService.acharPorCodigoEntidade(codigo);

        if (sensor == null) {
            throw new ServiceException("Sensor não encontrado: " + codigo);
        }

        List<Medicao> historicoMedicoes = sensor.getHistoricoMedicoes();

        if (historicoMedicoes.isEmpty()) {
            System.out.println("Nenhuma medição encontrada para o sensor " + codigo);
            return;
        }

        System.out.println("Histórico de Medições do Sensor " + codigo + ":");
        System.out.println();

        for (int i = 0; i < historicoMedicoes.size(); i++) {
            Medicao m = historicoMedicoes.get(i);
            boolean temAlerta = sensor.verificarSensor(m);
            String alertaStr = temAlerta ? " ⚠️ ALERTA" : "";

            System.out.println((i + 1) + ". Valor: " + m.getValor() +
                    " | Data: " + m.getDataHoraFormatada() + alertaStr);
        }
    }
}