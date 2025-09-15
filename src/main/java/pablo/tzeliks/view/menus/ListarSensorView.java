package pablo.tzeliks.view.menus;

import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.service.MonitoramentoService;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;
import pablo.tzeliks.view.helpers.SensorPrinter;

import java.util.List;
import java.util.Scanner;

public class ListarSensorView {
    public static void executar(Scanner scanner, MonitoramentoService service) {
        MenuHelper.imprimirMenuListagemSensores();
        try {
            List<SensorDTO> sensores = service.listarSensores();
            if (sensores.isEmpty()) {
                MessageHelper.info("Nenhum sensor cadastrado.");
            } else {
                SensorPrinter.imprimirLista(sensores);
            }
        } catch (Exception e) {
            MessageHelper.erro("Erro ao listar sensores: " + e.getMessage());
        }
        System.out.println();
    }
}
