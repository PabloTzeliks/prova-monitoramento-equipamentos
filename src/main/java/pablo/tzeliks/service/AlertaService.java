package pablo.tzeliks.service;

import pablo.tzeliks.model.Alerta;
import pablo.tzeliks.model.Sensor;

import java.util.*;
import java.util.stream.Collectors;

public class AlertaService {

    private final List<Alerta> listaAlertas = new ArrayList<>();

    public void salvarTodos(List<Alerta> alertas) {
        if (alertas == null || alertas.isEmpty()) return;
        listaAlertas.addAll(alertas);

        alertas.forEach(a -> System.out.println("Alerta salvo: " + a));
    }

    public List<Sensor> verificarCriticos(int valorMinimo) {
        return listaAlertas.stream()
                .collect(Collectors.groupingBy(Alerta::getSensor, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > valorMinimo)
                .map(Map.Entry::getKey) // aqui já é o objeto Sensor
                .toList();
    }

    public List<Alerta> buscarPorCodigoSensor(String codigoSensor) {
        return listaAlertas.stream()
                .filter(a -> a.getSensor().getCodigo().equals(codigoSensor))
                .collect(Collectors.toList());
    }

    public List<Alerta> listarTodos() {
        return Collections.unmodifiableList(listaAlertas);
    }

}