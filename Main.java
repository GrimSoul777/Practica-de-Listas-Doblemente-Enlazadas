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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
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
                    sc.nextLine();
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
