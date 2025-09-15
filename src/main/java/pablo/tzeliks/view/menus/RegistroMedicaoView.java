package pablo.tzeliks.view.menus;

import pablo.tzeliks.dto.MedicaoCreateDTO;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.service.MonitoramentoService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.util.Scanner;

public class RegistroMedicaoView {

    public static void executar(Scanner scanner, MonitoramentoService service) {
        MenuHelper.imprimirMenuRegistroMedicao();

        try {
            // Recebe o código do sensor
            String codigoRaw = null;
            while (codigoRaw == null) {

                try {
                    codigoRaw = InputHelper.lerString(scanner, "Digite o código do sensor: ");
                } catch (Exception e) {
                    MessageHelper.erro(e.getMessage() + ". Tente novamente.");
                }
            }

            // Recebe o valor da medição
            Double valor = null;
            while (valor == null) {
                try {
                    valor = InputHelper.lerDouble(scanner, "Digite o valor da medição: ");
                } catch (Exception e) {
                    MessageHelper.erro("Valor inválido. Tente novamente.");
                }
            }

            service.adicionarMedicao(codigoRaw, valor);

        } catch (Exception e) {
            MessageHelper.erro("Erro ao registrar medição: " + e.getMessage());
        }

        System.out.println();
    }
}
