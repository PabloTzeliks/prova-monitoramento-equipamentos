package pablo.tzeliks.model.enums;

// Lógica para melhor implementação futura, podendo assim o sistema ter mais de um tipo de Alerta.

public enum NivelAlerta {

//    ALERTA_ATENCAO("Atenção"),
    ALERTA_CRITICO("Critico");

    String nome;

    private NivelAlerta(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
