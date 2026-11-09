import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);

    private static int LeerNumero(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);

            try {
                int numero = Integer.parseInt(entrada.nextLine());

                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

                System.out.println(
                        "Ingresa un número entre " + minimo + " y " + maximo + "."
                );
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número entero.");
            }
        }
    }

    private static String LeerPalabra(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String palabra = entrada.nextLine();

            if (!palabra.trim().isEmpty() && !palabra.matches(".*\\s.*")) {
                return palabra;
            }

            System.out.println("Ingresa una palabra sin espacios.");
        }
    }

    private static int LeerPrioridad() {
        return LeerNumero("Prioridad (1 o 2): ", 1, 2);
    }

    private static void Menu() {
        System.out.println("\nLISTA DOBLEMENTE ENLAZADA");
        System.out.println("1. Inicializar / Borrar lista");
        System.out.println("2. Mostrar lista");
        System.out.println("3. Buscar");
        System.out.println("4. Insertar");
        System.out.println("5. Eliminar");
        System.out.println("6. Modificar");
        System.out.println("7. Créditos");
        System.out.println("8. Salir");
    }

    private static void Buscar(Lista lista) {
        int prioridad = LeerPrioridad();
        String palabra = LeerPalabra("Palabra a buscar: ");

        if (lista.Buscar(palabra, prioridad) == null) {
            System.out.println("Palabra no encontrada.");
        } else {
            System.out.println("Palabra encontrada.");
        }
    }

    private static void Insertar(Lista lista) {
        int prioridad = LeerPrioridad();
        String palabra = LeerPalabra("Palabra a insertar: ");

        lista.Insertar(palabra, prioridad);
        System.out.println("Palabra insertada.");
    }

    private static void Eliminar(Lista lista) {
        int prioridad = LeerPrioridad();
        String palabra = null;

        if (prioridad == 2) {
            palabra = LeerPalabra("Palabra a eliminar: ");
        }

        String eliminada = lista.Eliminar(palabra, prioridad);

        if (eliminada != null) {
            System.out.println("Palabra eliminada: " + eliminada);
        } else if (prioridad == 1) {
            System.out.println("No hay palabras de prioridad 1.");
        } else {
            System.out.println("Palabra no encontrada.");
        }
    }

    private static void Modificar(Lista lista) {
        int prioridad = LeerPrioridad();
        String palabra = LeerPalabra("Palabra a modificar: ");

        if (lista.Buscar(palabra, prioridad) == null) {
            System.out.println("No se pudo localizar la palabra.");
            return;
        }

        String nueva = LeerPalabra("Nueva palabra: ");

        if (lista.Modificar(palabra, nueva, prioridad)) {
            System.out.println("Palabra modificada.");
        } else {
            System.out.println("No se pudo localizar la palabra.");
        }
    }

    private static void Creditos() {
        System.out.println("\nMateria: Estructuras de Datos");
        System.out.println("Práctica: Listas Doblemente Enlazadas");
        System.out.println("Integrante: Jonatan Perez Rosales");
        System.out.println("Matrícula: 25420015");
        System.out.println("Integrante: Jesus Emilio Castañeda Hernandez");
        System.out.println("Matrícula: 25420045");
    }

    public static void main(String[] args) {
        Lista lista = new Lista();
        int opcion;

        do {
            Menu();
            opcion = LeerNumero("Selecciona una opción: ", 1, 8);

            switch (opcion) {
                case 1:
                    lista.Liberar();
                    System.out.println("Lista vaciada.");
                    break;

                case 2:
                    System.out.println("1. De inicio a fin");
                    System.out.println("2. De fin a inicio");
                    int orden = LeerNumero("Selecciona el recorrido: ", 1, 2);
                    lista.Mostrar(orden == 1);
                    break;

                case 3:
                    Buscar(lista);
                    break;

                case 4:
                    Insertar(lista);
                    break;

                case 5:
                    Eliminar(lista);
                    break;

                case 6:
                    Modificar(lista);
                    break;

                case 7:
                    Creditos();
                    break;

                case 8:
                    lista.Liberar();
                    System.out.println("Programa terminado.");
                    break;
            }
        } while (opcion != 8);

        entrada.close();
    }
}