package pablo.tzeliks.model;

import pablo.tzeliks.model.enums.NivelAlerta;

public class Alerta {

    // Sem ID para diminuir a complexidade
    final Sensor sensor;
    final Medicao medicao;
    final NivelAlerta nivelAlerta;


    Alerta(Sensor sensor, Medicao medicao, NivelAlerta nivelAlerta) {
        this.sensor = sensor;
        this.medicao = medicao;
        this.nivelAlerta = nivelAlerta;
    }

    public static Alerta of(Sensor s, Medicao m, NivelAlerta nivel) {
        return new Alerta(s, m, nivel);
    }

    @Override
    public String toString() {
        return String.format("Alerta %d" +
                        "\nSensor: %s" +
                        "\nMedição: %d" +
                        "\nNivel do Alerta: %s",
                sensor.getCodigo(), medicao.getId(), nivelAlerta.getNome());
    }


}
