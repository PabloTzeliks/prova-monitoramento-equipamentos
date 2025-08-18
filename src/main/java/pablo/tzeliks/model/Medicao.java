package pablo.tzeliks.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static java.time.format.DateTimeFormatter.ofLocalizedDate;

public class Medicao {

    private double valor;
    private LocalDateTime dataHora =  LocalDateTime.now();

    Medicao(double valor, LocalDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
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
