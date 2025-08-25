package pablo.tzeliks.service;

import pablo.tzeliks.exceptions.AlertaException;
import pablo.tzeliks.model.Alerta;
import pablo.tzeliks.model.Sensor;
import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.service.SensorService;
import pablo.tzeliks.service.contracts.AlertaInterface;

import java.util.*;
import java.util.stream.Collectors;

public class AlertaService implements AlertaInterface {

    private final List<Alerta> listaAlertas = new ArrayList<>();

    public void salvarAlerta(Alerta alerta) {
        if (alerta == null) {
            throw new AlertaException("Alerta não pode ser nulo.");
        };
        listaAlertas.add(alerta);
    }

    public void verificarAlertas(List<SensorDTO> todosOsSensores, SensorService sensorService) {
        System.out.println("Verificando sensores...");
        System.out.println();

        for (SensorDTO dto : todosOsSensores) {
            Sensor sensor = sensorService.acharPorCodigoEntidade(dto.codigo());
            long quantidadeAlertas = contarAlertasPorSensor(sensor);

            System.out.println("Sensor " + dto.codigo() + " (" + dto.tipo() + ") – " +
                    dto.nomeEquipamento() + ":");

            if (quantidadeAlertas > 0) {
                System.out.println("⚠️ " + quantidadeAlertas + " alerta(s) detectado(s)");
            } else {
                System.out.println("✅ Nenhum alerta");
            }
            System.out.println();
        }
    }

    public void listarSensoresCriticos(SensorService sensorService) {
        Map<Sensor, Long> contagemAlertas = listaAlertas.stream()
                .collect(Collectors.groupingBy(
                        Alerta::getSensor,
                        Collectors.counting()
                ));

        List<Map.Entry<Sensor, Long>> criticos = contagemAlertas.entrySet().stream()
                .filter(entry -> entry.getValue() > 3)
                .toList();

        if (criticos.isEmpty()) {
            System.out.println("Nenhum sensor crítico encontrado.");
            return;
        }

        System.out.println("Sensores com mais de 3 alertas:");
        System.out.println();

        for (Map.Entry<Sensor, Long> entry : criticos) {
            Sensor sensor = entry.getKey();
            long alertas = entry.getValue();

            System.out.println("Código: " + sensor.getCodigo() +
                    " | Tipo: " + sensor.getTipo() +
                    " | Equipamento: " + sensor.getNomeEquipamento() +
                    " | Alertas: " + alertas);
        }

        System.out.println();
        System.out.println("⚠️ ATENÇÃO: Inspeção imediata recomendada!");
    }

    private long contarAlertasPorSensor(Sensor sensor) {
        return listaAlertas.stream()
                .filter(a -> a.getSensor().equals(sensor))
                .count();
    }
}