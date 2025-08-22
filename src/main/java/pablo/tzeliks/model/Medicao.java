package pablo.tzeliks.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static java.time.format.DateTimeFormatter.ofLocalizedDate;

public class Medicao {

    private int id;
    private Sensor sensor;
    private double valor;
    private LocalDateTime dataHora =  LocalDateTime.now();

    Medicao(int id, Sensor sensor, double valor, LocalDateTime dataHora) {
        this.id = id;
        this.sensor = sensor;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataHoraFormatada() {
        FormatStyle formatStyle = FormatStyle.SHORT;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(formatStyle);
        return dataHora.format(dateTimeFormatter);
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
