package pablo.tzeliks.model.enums;

public enum NivelAlerta {

    ALERTA_ATENCAO("Atenção"),
    ALERTA_CRITICO("Critico");

    String nome;

    private NivelAlerta(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
