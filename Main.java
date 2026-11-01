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
                    Inicializar(lista);
                    System.out.println("La lista se Inicializo");
                    break;
                case 2:
                    //Mostrar lista descendente/ascendente
                    boolean OpcMostrar = false; 
                    System.out.println("Ver la lista de P a F: (si)");
                    OpcMostrar = sc.nextLine().equalsIgnoreCase("si");
                    if (OpcMostrar) {
                        MostrarP(lista.P);
                    }else{
                        MostrarF(lista.F);
                    }
                    break;
                case 3:
                    //Buscar
                    
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

    public static void Inicializar(Lista lista) {
        Nodo temp = lista.P;

    while (temp != null) {
        Nodo sig = temp.sig;
        
        temp.sig = null;
        temp.ant = null;
        temp = sig;
        }

        lista.P = null;
        lista.F = null;
    }
    
    //Mostrar lista de P a F
    public static void MostrarP(Nodo P) {
        Nodo temp = P;
        while (temp != null) {
            System.out.print(temp.info + " ");
            temp = temp.sig;
        }
        System.out.println();
    }
    //Mostrar lista de F a P
    public static void MostrarF(Nodo F) {
        Nodo temp = F;
        while (temp != null) {
            System.out.print(temp.info + " ");
            temp = temp.ant;
        }
        System.out.println();
    } 
    public Nodo Buscar(Lista lista, char letraBuscada, int prioridadBuscada) {
        //Prioridad 1
        if(prioridadBuscada==1){
            Nodo temp = lista.P;
            while (temp != null) {
                if (temp.prioridad == 2) {
                    return null;
                }
                if (temp.info == letraBuscada){
                    return temp;
                }
                temp = temp.sig;
            }
            return null;
        }
        if(prioridadBuscada==2){
            //Investigar una madera de ordenar letras AaBbCc..Zz jeje no se si hay funciones como con las fechas
        }
        return null;
    }
}
