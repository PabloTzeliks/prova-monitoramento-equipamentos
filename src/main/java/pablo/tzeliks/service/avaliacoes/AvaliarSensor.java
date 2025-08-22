package pablo.tzeliks.service.avaliacoes;

import pablo.tzeliks.exceptions.AlertaException;
import pablo.tzeliks.model.Alerta;
import pablo.tzeliks.model.Medicao;
import pablo.tzeliks.model.Sensor;
import pablo.tzeliks.model.enums.NivelAlerta;
import pablo.tzeliks.service.contracts.AlertaInterface;

import java.util.ArrayList;
import java.util.List;

public class AvaliarSensor implements AlertaInterface {

    private final int ATENCAO_TEMPERATURA = 70;
    private final int CRITICO_TEMPERATURA = 80;

    private final int PADRAO_VIBRACAO = 60;
    private final double DIFERENCA_ATENCAO_VIBRACAO = 0.5;
    private final double DIFERENCA_CRITICO_VIBRACAO = 1.25;

    @Override
    public List<Alerta> avaliar(Sensor sensor, Medicao medicao) throws AlertaException {
        List<Alerta> resultado = new ArrayList<>();
        if (sensor == null || medicao == null) throw new AlertaException("Sensor ou medicao nulos");

        double valor = medicao.getValor();

        switch (sensor.getTipo()) {
            case SENSOR_TEMPERATURA:

                if (valor >= CRITICO_TEMPERATURA) {
                    resultado.add(Alerta.of(sensor, medicao, NivelAlerta.ALERTA_CRITICO));
                } else if (valor >= ATENCAO_TEMPERATURA) {
                    resultado.add(Alerta.of(sensor, medicao, NivelAlerta.ALERTA_ATENCAO));
                }
                break;

            case SENSOR_VIBRACAO:

                double diferenca = Math.abs(valor - PADRAO_VIBRACAO);
                if (diferenca >=  DIFERENCA_CRITICO_VIBRACAO) {
                    resultado.add(Alerta.of(sensor, medicao, NivelAlerta.ALERTA_CRITICO));
                } else if (diferenca >=  DIFERENCA_ATENCAO_VIBRACAO) {
                    resultado.add(Alerta.of(sensor, medicao, NivelAlerta.ALERTA_ATENCAO));
                }
                break;

            default:
        }

        return resultado;
    }
}