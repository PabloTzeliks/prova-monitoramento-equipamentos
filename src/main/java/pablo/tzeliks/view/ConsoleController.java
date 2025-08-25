package pablo.tzeliks.view;

import pablo.tzeliks.service.AlertaService;
import pablo.tzeliks.service.SensorService;
import pablo.tzeliks.view.helpers.InputHelper;
import pablo.tzeliks.view.helpers.MenuHelper;
import pablo.tzeliks.view.helpers.MessageHelper;
import pablo.tzeliks.view.menus.*;

import java.util.Scanner;

public class ConsoleController {

    private final SensorService serviceSensor;
    private final AlertaService serviceAlerta;
    private final Scanner scanner;

    public ConsoleController(SensorService service) {
        this.serviceSensor = service;
        this.scanner = new Scanner(System.in);
    }

    public ConsoleController(AlertaService service) {
        this.serviceAlerta = service;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            MenuHelper.imprimirMenuPrincipal();
            opcao = InputHelper.lerInteiro(scanner, "Digite a opção desejada: ");
            switch (opcao) {
                case 1:
                    CadastroSensorView.executar(scanner, serviceCrud);
                    break;
//                case 2:
//                    ListarView.executar(scanner, service);
//                    break;
//                case 3:
//                    PesquisaView.executar(scanner, service);
//                    break;
//                case 4:
//                    RemocaoView.executar(scanner, service);
//                    break;
//                case 5:
//                    MovimentacaoEstoqueView.executar(scanner, service);
//                    break;
//                case 6:
//                    RelatoriosView.executar(scanner, service);
//                    break;
//                case 7:
//                    BuscaAvancadaView.executar(scanner, service);
//                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    MessageHelper.erro("Opção inválida!");
            }
        } while (opcao != 0);
    }
}