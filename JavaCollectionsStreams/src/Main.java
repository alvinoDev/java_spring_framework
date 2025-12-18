void main() {

    // AGREGAR ELEMENTOS A UNA LISTA
    List<String> empleados = new ArrayList<>();
    empleados.add("Juan");
    empleados.add("María");
    empleados.add("Carlos");
    empleados.add("Ana");

    IO.println("Lista de empleados INICIAL: " + empleados);

    // ELIMINAR UN ELEMENTO DE LA LISTA
    empleados.remove("Carlos");
    IO.println("Lista después de la eliminación: " + empleados);

}
