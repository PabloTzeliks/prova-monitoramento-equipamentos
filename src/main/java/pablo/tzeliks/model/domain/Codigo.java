package pablo.tzeliks.model.domain;

import pablo.tzeliks.exceptions.CodigoProdutoException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Codigo {

    private static final Pattern PADRAO = Pattern.compile("^[A-Za-z]{3}-\\d{4}$");

    private final String codigo;

    public Codigo(String codigo) {
        Objects.requireNonNull(codigo, "Código não pode ser nulo");
        String trimmed = codigo.trim();
        if (!isValid(trimmed)) {
            throw new CodigoProdutoException("Código inválido: '" + codigo + "'. Formato esperado: AAA-0000 (ex: WEG-1234).");
        }
        this.codigo = trimmed.toUpperCase();
    }

    public static Codigo of(String codigo) {
        return new Codigo(codigo);
    }

    public static boolean isValid(String codigo) {
        if (codigo == null) return false;
        return PADRAO.matcher(codigo.trim()).matches();
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Codigo)) return false;
        Codigo other = (Codigo) o;
        return codigo.equals(other.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo;
    }
}