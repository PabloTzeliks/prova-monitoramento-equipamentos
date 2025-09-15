package pablo.tzeliks.view.menus;

import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.service.MonitoramentoService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.util.Scanner;

public class HistoricoMedicaoView {

    public static void executar(Scanner scanner, MonitoramentoService service) {
        MenuHelper.imprimirMenuHistoricoMedicoes();

        String codigoRaw = null;

        try {

            while (codigoRaw == null) {

                try {
                    codigoRaw = InputHelper.lerString(scanner, "Digite o código do sensor: ");
                } catch (Exception e) {
                    MessageHelper.erro(e.getMessage() + ". Tente novamente.");
                }
            }

            // Chama o método do service para exibir o histórico
            service.exibirHistoricoMedicoes(codigoRaw);

        } catch (Exception e) {
            MessageHelper.erro("Erro ao exibir histórico de medições: " + e.getMessage());
        }

        System.out.println();
    }
}
