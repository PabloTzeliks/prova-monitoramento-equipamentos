package pablo.tzeliks.view.menus;

import pablo.tzeliks.dto.SensorDTO;
import pablo.tzeliks.model.domain.Codigo;
import pablo.tzeliks.model.enums.TipoSensor;
import pablo.tzeliks.service.MonitoramentoService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;

import java.util.Scanner;

public class CadastroSensorView {

    public static void executar(Scanner scanner, MonitoramentoService service) {

        MenuHelper.imprimirMenuCadastroSensor();

        try {
            Codigo codigo = null;
            while (codigo == null) {
                String codigoRaw = InputHelper.lerString(scanner, "Código do Sensor ( ex: AAA-0001 ): ");
                try {
                    codigo = new Codigo(codigoRaw);
                } catch (Exception e) {
                    MessageHelper.erro(e.getMessage() + ". Tente novamente.");
                }
            }

            String nomeEquipamento = InputHelper.lerString(scanner, "\nDigite o nome do Sensor: ");

            TipoSensor tipo = InputHelper.escolherEnum(scanner, "\nEscolha o tipo de sensor: ");
            if (tipo == null) {
                MessageHelper.info("O tipo do Sensor deve ser informado.");
                return;
            }

            SensorDTO dto = new SensorDTO(0, codigo, nomeEquipamento, tipo);
            service.cadastrarSensor(dto);

            MessageHelper.sucesso("Sensor cadastrado com sucesso!");

            if (tipo == TipoSensor.SENSOR_TEMPERATURA) {

                System.out.println("\nTipo: " + tipo.getNome() + " | Limite de alerta: 80 Cº");
            } else if (tipo == TipoSensor.SENSOR_VIBRACAO){

                System.out.println("\nTipo: " + tipo.getNome() + " | Limite de alerta: 60 Hz");
            }

        } catch (Exception e) {
            MessageHelper.erro("Erro ao cadastrar sensor, observe: " + e.getMessage());
        }

        System.out.println();
    }

}
