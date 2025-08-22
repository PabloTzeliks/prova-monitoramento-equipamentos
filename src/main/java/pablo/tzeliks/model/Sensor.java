package pablo.tzeliks.model;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoSensor;

import java.util.ArrayList;
import java.util.List;

public abstract class Sensor {

    private int id;
    private Codigo codigo;
    private String nomeEquipamento;
    private TipoSensor tipo;
    private List<Medicao> historicoMedicoes = new ArrayList<>();

    public Sensor(int id, Codigo codigo, String nomeEquipamento, TipoSensor tipo, List<Medicao> historicoMedicoes) {
        this.id = id;
        this.codigo = codigo;
        this.nomeEquipamento = nomeEquipamento;
        this.tipo = tipo;
        this.historicoMedicoes = historicoMedicoes;
    }

    public void adicionarMedicao(Medicao medicao) {

        if (medicao == null) {
            throw new RuntimeException("Medição não existente.");
        }

        historicoMedicoes.add(medicao);

    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Codigo getCodigo() {
        return codigo;
    }

    public void setCodigo(Codigo codigo) {
        this.codigo = codigo;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public TipoSensor getTipo() {
        return tipo;
    }

    public void setTipo(TipoSensor tipo) {
        this.tipo = tipo;
    }

    public abstract String toString();
}