//package pablo.tzeliks.view.helpers;
//
//import pablo.tzeliks.dto.SensorDTO;
//
//import java.util.List;
//
//public final class SensorPrinter {
//
//    private SensorPrinter() {}
//
//    public static void imprimirLista(List<SensorDTO> lista) {
//        if (lista == null || lista.isEmpty()) {
//            System.out.println("Nenhum equipamento encontrado.");
//            return;
//        }
//        System.out.println();
//        System.out.println("---- Sensores ----");
//        for (SensorDTO dto : lista) {
//            imprimirSensores(dto);
//            System.out.println("----------------------");
//        }
//    }
//
//    public static void imprimirSensores(SensorDTO dto) {
//        if (dto == null) {
//            System.out.println("Sensor nulo.");
//            return;
//        }
//
//        System.out.println("ID: " + dto.id());
//        System.out.println("Nome: " + safeString(dto.nomeEquipamento());
//        System.out.println("Código: " + safeString(dto.codigo()));
//        System.out.println("Tipo: " + (dto.tipo() != null ? dto.tipo().name() : "N/A"));
//    }
//
//    private static void imprimirListaResumo(List<SensorDTO > lista) {
//        System.out.printf("%-4s %-30s %-12s %-15s%n", "ID", "NOME", "CÓDIGO", "TIPO");
//        System.out.println("----------------------------------------------------------------------------------------");
//        for (SensorDTO dto : lista) {
//            String codigoStr = dto.codigo() != null ? dto.codigo().toString() : "-";
//            System.out.printf("%-4d %-30s %-12s %-10d %-10.2f %-15s%n",
//                    dto.id(),
//                    abreviar(dto.nomeEquipamento(), 30),
//                    codigoStr,
//                    dto.tipo() != null ? dto.tipo().name() : "-"
//            );
//        }
//    }
//
//    private static String abreviar(String s, int tamanho) {
//        if (s == null) return "-";
//        if (s.length() <= tamanho) return s;
//        return s.substring(0, tamanho - 3) + "...";
//    }
//
//    private static String safeString(Object o) {
//        return o == null ? "-" : o.toString();
//    }
//}