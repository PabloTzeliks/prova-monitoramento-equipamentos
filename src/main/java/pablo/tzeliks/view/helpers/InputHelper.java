package pablo.tzeliks.view.helpers;

import pablo.tzeliks.model.enums.TipoSensor;

import java.util.Scanner;

public class InputHelper {

    public static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                MessageHelper.erro("Valor inválido, tente novamente.");
            }
        }
    }

    public static double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input.trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                MessageHelper.erro("Valor inválido, tente novamente.");
            }
        }
    }

    public static String lerString(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public static TipoSensor escolherEnum(Scanner scanner, String mensagem) {

        while (true) {

            System.out.println(mensagem);
            System.out.println();

            System.out.println("Tipos de Sensores:\n" +
                    "\n1- Sensor de Temperatura" +
                    "\n2- Sensor de Vibração\n");

            String input = scanner.nextLine();

            try {
                if (input.equals("1")) {
                    TipoSensor tipo = TipoSensor.SENSOR_TEMPERATURA;

                    return tipo;
                } else if (input.equals("2")) {
                    TipoSensor tipo = TipoSensor.SENSOR_VIBRACAO;

                    return tipo;
                } else {
                    MessageHelper.erro("Valor incorreto, tente novamente.");
                }

            } catch (NumberFormatException e) {
                MessageHelper.erro("Erro ao inserir o Tipo de Sensor, observe: " + e.getMessage());
            }
        }



    }
}