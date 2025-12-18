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
        List<Integer> numeros = List.of(2, 3, 5, 7, 11);
        List<Integer> numCuadrado = numeros.stream()
                .map(n -> n*n)
                .collect(Collectors.toList());
        IO.println(numCuadrado);

    }
}
