void main() {

    // AGREGAR ELEMENTOS A UNA LISTA
//    List<String> empleados = new ArrayList<>();
//    empleados.add("Juan");
//    empleados.add("María");
//    empleados.add("Carlos");
//    empleados.add("Ana");

//    IO.println("Lista de empleados INICIAL: " + empleados);

    // ELIMINAR UN ELEMENTO DE LA LISTA
//    empleados.remove("Carlos");
//    IO.println("Lista después de la eliminación: " + empleados);

    // ACCEDER A ELEMENTOS
//    empleados.add("Pedro");
//    empleados.add("Antonio");

//    IO.println("La segunda persona de la lista es: " + empleados.get(Integer.hashCode(1)));
//    IO.println("Total de empleados: " + empleados.size());

//    List<String> eventos = new ArrayList<>();
//    eventos.add("IA Conference");
//    eventos.add("AI Summit");
//    eventos.add("DevFest");
//    eventos.add("Cloud Expo");
//    eventos.add("IA Conference");
//    eventos.add("DevFest");
//
//    IO.println("Lista de eventos: " + eventos);
//
//    Set<String> eventosUnicos = new HashSet<>(eventos);
//    IO.println("Lista de eventos unicos: " + eventosUnicos);

    Map<Integer, String> clientes = new HashMap<>();

    clientes.put(1, "Maria");
    clientes.put(2, "Calos");
    clientes.put(3, "Marcos");
    clientes.put(4, "Renata");
    clientes.put(5, "Maria");

    IO.println("LISTA DE CLIENTES: " + clientes);
    IO.println("El nombre del cliente con ID 3 es: " + clientes.get(3));

}
