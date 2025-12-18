import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    void main() {

        // LISTA FILTRADA CON nombres que tengan menos de 5 caracteres
//        List<String> funcionarios = List.of("Ana", "Bruno", "Carlos", "Amanda", "Alice", "Daniel", "Caroline");
//        List<String> listaFiltrada = funcionarios.stream()
//                .filter(f -> f.length() <= 5)
//                .collect(Collectors.toList());
//        IO.println(listaFiltrada);

        // CALCULAR EL cuadrado de los números
//        List<Integer> numeros = List.of(2, 3, 5, 7, 11);
//        List<Integer> numCuadrado = numeros.stream()
//                .map(n -> n*n)
//                .collect(Collectors.toList());
//        IO.println(numCuadrado);

        // CALCULAR el total con impuesto
        List<Double> preciosProductos = List.of(29.99, 49.50, 15.75, 99.99);
        double totalGastado = preciosProductos.stream()
                .reduce(0.0, Double::sum);

        double impuesto = totalGastado * 0.08;
        double totalConImpuesto = totalGastado + impuesto;

        IO.println("Valor total antes del impuesto: " + String.format("%.2f", totalGastado));
        IO.println("Valor total con impuesto de 8%: " + String.format("%.2f", totalConImpuesto));

    }
}
