package pablo.tzeliks.model.enums;

public enum TipoSensor {

    SENSOR_TEMPERATURA("Sensor de Temperatura"),
    SENSOR_VIBRACAO("Sensor de Vibração");

    String nome;

    private TipoSensor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
