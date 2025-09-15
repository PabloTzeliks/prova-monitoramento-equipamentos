package pablo.tzeliks.view;

import pablo.tzeliks.service.AlertaService;
import pablo.tzeliks.service.MonitoramentoService;
import pablo.tzeliks.service.SensorService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;
import pablo.tzeliks.view.menus.*;

import java.util.Scanner;

public class ConsoleController {

    private final MonitoramentoService service;
    private final Scanner scanner;

    public ConsoleController(MonitoramentoService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            MenuHelper.imprimirMenuPrincipal();
            opcao = InputHelper.lerInteiro(scanner, "Digite a opção: ");

            try {
                switch (opcao) {
                    case 1:
                        CadastroSensorView.executar(scanner, service);
                        break;
                    case 2:
                        RemocaoSensorView.executar(scanner, service);
                        break;
                    case 3:
                        ListarSensorView.executar(scanner, service);
                        break;
//                    case 4:
//                        RegistrarMedicaoView.executar(scanner, serviceFacade);
//                        break;
//                    case 5:
//                        HistoricoMedicoesView.executar(scanner, serviceFacade);
//                        break;
//                    case 6:
//                        VerificarAlertasView.executar(scanner, serviceFacade);
//                        break;
//                    case 7:
//                        SensoresCriticosView.executar(scanner, serviceFacade);
//                        break;
                    case 0:
                        System.out.println("Encerrando sistema... Obrigado por usar o Monitoramento WEG!");
                        break;
                    default:
                        MessageHelper.erro("Opção inválida!");
                }
            } catch (Exception e) {
                MessageHelper.erro("Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }
}