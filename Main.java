import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Lista lista = new Lista();
        
        int opcion;
        do {
            menu();
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    //Inicializar/Borrar lista
                    lista.Inicializar();
                    System.out.println("La lista se Inicializo");
                    break;
                case 2:
                    //Mostrar lista descendente/ascendente
                    boolean OpcMostrar = false; 
                    System.out.println("Ver la lista de P a F: (si)");
                    OpcMostrar = sc.nextLine().equalsIgnoreCase("si");
                    if (OpcMostrar) {
                        lista.MostrarP();
                    }else{
                        lista.MostrarF();
                    }
                    break;
                case 3:
                    //Buscar
                    System.out.println("Ingresa la letra que deseas buscar:");
                    char letra = sc.nextLine().charAt(0);
                    System.out.println("Ingresa la prioridad (1 o 2):");
                    int prioridad = sc.nextInt();
                    sc.nextLine();
                    Nodo encontrado = lista.Buscar(letra, prioridad);
                    if (encontrado != null) {
                        System.out.println("La letra SI se encuentra en la lista.");
                    } else {
                        System.out.println("La letra NO se encuentra en la lista.");
                    }
                    break;
                case 4:
                    //Insertar
                    System.out.println("Ingresa la letra que deseas insertar:");
                    char letraInsertar = sc.nextLine().charAt(0);
                    System.out.println("Ingresa la prioridad (1 o 2):");
                    int prioridadInsertar = sc.nextInt();
                    sc.nextLine();
                    lista.Insertar(letraInsertar, prioridadInsertar);
                    break;
                case 5:
                    //Eliminar
                    System.out.println("Ingresa la prioridad (1 o 2):");
                    int prioridadEliminar = sc.nextInt();
                    sc.nextLine(); // Consume el salto de línea

                    if (prioridadEliminar == 1) {
                        lista.EliminarPrimeroPrioridad1();
                    } else if (prioridadEliminar == 2) {
                        System.out.println("Ingresa la letra que deseas eliminar:");
                        char letraEliminar = sc.nextLine().charAt(0);

                        lista.Eliminar(letraEliminar, 2);
                    } else {
                        System.out.println("Prioridad no válida.");
                    }
                    break;
                case 6:
                    // Modificar
                    System.out.println("Ingresa la letra que deseas modificar:");
                    char letraModificar = sc.nextLine().charAt(0);

                    System.out.println("Ingresa la prioridad (1 o 2):");
                    int prioridadModificar = sc.nextInt();
                    sc.nextLine(); // Consume el salto de línea pendiente

                    System.out.println("Ingresa la nueva letra:");
                    char nuevaLetra = sc.nextLine().charAt(0);

                    lista.Modificar(letraModificar, prioridadModificar, nuevaLetra);
                    break;
                case 7:
                    //Creditos
                    creditos();
                    break;
                case 8:
                    //Salir
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 8);
    }


    public static void menu() {
        System.out.println();
        System.out.println("MENU PRINCIPAL");
        System.out.println("1. Inicializar/Borrar lista");
        System.out.println("2. Mostrar lista");
        System.out.println("3. Buscar");
        System.out.println("4. Insertar");
        System.out.println("5. Eliminar");
        System.out.println("6. Modificar");
        System.out.println("7. Creditos");
        System.out.println("8. Salir");
        System.out.println("Seleccione una opcion:");
    }

    public static void creditos() {
        System.out.println();
        System.out.println("Materia: Estructura de datos");
        System.out.println();
        System.out.println("Integrantes:");
        System.out.println("NOMBRE                              MATRICULA");    //Aqui agreguen su nombre y matricula
        System.out.println("Joshue Angel Regalado Martinez      25420019");
        System.out.println("Omar Emiliano Cuevas Peña           25420131");
        System.out.println("Zoe Valentina Morales García        25420058");
    }

}