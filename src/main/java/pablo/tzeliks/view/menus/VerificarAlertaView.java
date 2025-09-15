package pablo.tzeliks.view.menus;

import pablo.tzeliks.service.MonitoramentoService;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.util.Scanner;

public class VerificarAlertaView {

    public static void executar(Scanner scanner, MonitoramentoService service) {
        MenuHelper.imprimirMenuVerificarAlertas();

        try {
            service.verificarAlertas();
        } catch (Exception e) {
            MessageHelper.erro("Erro ao verificar alertas: " + e.getMessage());
        }

        System.out.println();
    }
}