package pablo.tzeliks.view.menus;

import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.service.MonitoramentoService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.util.Scanner;

public class RemocaoSensorView {

    public static void executar(Scanner scanner, MonitoramentoService service) {

        MenuHelper.imprimirMenuRemocaoSensor();

        String codigoRaw = InputHelper.lerString(scanner, "Informe o código do Sensor (ex: AAA-0001): ");

        Codigo codigo;
        try {
            codigo = new Codigo(codigoRaw);
        } catch (Exception e) {
            MessageHelper.erro("Código inválido: " + e.getMessage());
            return;
        }

        try {
            SensorDTO dto = service.acharSensorPorCodigo(codigo);
            if (dto == null) {
                MessageHelper.info("Sensor não encontrado.");
            } else {
                boolean confirmacaoExclusao = InputHelper.confirmarExclusao(scanner, dto);

                if (confirmacaoExclusao) {
                    service.removerSensor(codigo);
                }
            }
        } catch (Exception e) {
            MessageHelper.erro("Falha ao pesquisar Sensor: " + e.getMessage());
        }
    }

}
