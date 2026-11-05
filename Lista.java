import java.util.Scanner;

public class Lista {
    Nodo P;
    Nodo F;

    public Lista() {
        this.P = null;
        this.F = null;
    }

    //Metodos
    public void Inicializar(Lista lista) {
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
    public void MostrarP(Nodo P) {
        Nodo temp = P;
        while (temp != null) {
            System.out.print(temp.info + " ");
            temp = temp.sig;
        }
        System.out.println();
    }
    //Mostrar lista de F a P
    public void MostrarF(Nodo F) {
        Nodo temp = F;
        while (temp != null) {
            System.out.print(temp.info + " ");
            temp = temp.ant;
        }
        System.out.println();
    } 

    //Funcion para obtener la posicion de la letra en el orden AaBbCc..Zz(hecha por zoe)
    public static int posicionLetra(char letra) {
        if (letra >= 'A' && letra <= 'Z') {
            return (letra - 'A') * 2; //el espacio entre mayusculas
        }
        if (letra >= 'a' && letra <= 'z') {
            return (letra - 'a') * 2 + 1; //coloca la miniscula en la posicion siguiente a la mayuscula
        }
        return -1;
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
            //calculamos la posicion de la letra central, eleginos donde comenzar la busqueda, si es menor a la letra central comenzamos desde P hacia F, si es mayor comenzamos desde F hacia P
            int posicionBuscada = posicionLetra(letraBuscada);
            if (posicionBuscada == -1) {
                return null;
            }
            int posicionCentral = posicionLetra('M');
            //desde donde se va a comenzar la busqueda
            if (posicionBuscada <= posicionCentral) {
                Nodo temp = lista.P;
                while (temp != null && temp.prioridad == 1) {
                    temp = temp.sig;
                }
                while (temp != null) {
                    int posicionActual = posicionLetra(temp.info);
                    if (posicionActual == posicionBuscada) {
                        if (temp.prioridad == 2) {
                        return temp;
                        }
                    }
                    if (posicionActual > posicionBuscada) {
                        return null;
                    }
                    temp = temp.sig;
                }

            } else {
                Nodo temp = lista.F; //desde F hacia P
                while (temp != null && temp.prioridad == 1) {
                    temp = temp.ant;
                }

                while (temp != null && temp.prioridad == 2) {
                    int posicionActual = posicionLetra(temp.info);
                    if (posicionActual == posicionBuscada) {
                        return temp;
                    }
                    if (posicionActual < posicionBuscada) {
                        return null;
                    }
                    temp = temp.ant;
                }
            }
        }        
        return null;
    }

    public void Insertar(Lista lista, char letra, int prioridad) {
        if (prioridad != 1 && prioridad != 2) {
            System.out.println("Prioridad no valida");
            return;
        }
        if (posicionLetra(letra) == -1) {
            System.out.println("Solo se permiten letras");
            return;
        }

        Nodo nuevo = new Nodo(letra, prioridad); //nuevo nodo
        Nodo anterior = null;
        Nodo actual = lista.P;
        if (prioridad == 1) {
            while (actual != null && actual.prioridad == 1) {
                anterior = actual;
                actual = actual.sig;
            }
        } else {
            //saltar los nodos de prioridad 1
            while (actual != null && actual.prioridad == 1) {
                anterior = actual;
                actual = actual.sig;
            }
            int posicionNueva = posicionLetra(letra);
            //avanzar mientras las letras sean menores o iguales
            while (actual != null &&
                posicionLetra(actual.info) <= posicionNueva) {
                anterior = actual;
                actual = actual.sig;
            }
        }

        nuevo.ant = anterior; //enlazar el nuevo nodo con el anterior
        nuevo.sig = actual;
        if (anterior == null) {
            lista.P = nuevo;
        } else {
            anterior.sig = nuevo;
        }
        if (actual == null) {
            lista.F = nuevo;
        } else {
            actual.ant = nuevo;
        }

        System.out.println("Letra insertada correctamente");
    }
    
    public void Eliminar(Lista lista, char letra, int prioridad) {
        Nodo nodoAEliminar = Buscar(lista, letra, prioridad);
        if (nodoAEliminar == null) {
            System.out.println("La letra no se encuentra en la lista.");
            return;
        }

        if (nodoAEliminar.ant != null) {
            nodoAEliminar.ant.sig = nodoAEliminar.sig;// Actualizar el enlace
        } else {
            lista.P = nodoAEliminar.sig; // Actualizar P si es el primer nodo
        }

        if (nodoAEliminar.sig != null) {
            nodoAEliminar.sig.ant = nodoAEliminar.ant;// Actualizar el enlace
        } else {
            lista.F = nodoAEliminar.ant; // Actualizar F si es el último nodo
        }

        System.out.println("Letra eliminada correctamente");
    }

    public void Modificar(Lista lista, char letra, int prioridad, Scanner sc) {
        Nodo encontrado = Buscar(lista, letra, prioridad);

        if (encontrado == null) {
            System.out.println("La letra no se encuentra en la lista.");
            return;
        }
        System.out.println("Ingresa la nueva letra:");
        char nuevaLetra = sc.nextLine().charAt(0); //aca nose si se tanga que poner nueva prioridad o no lol
        if (posicionLetra(nuevaLetra) == -1) {
            System.out.println("Solo se permiten letras");
            return;
        }
        int prioridadOriginal = encontrado.prioridad;
        
        Eliminar(lista, letra, prioridadOriginal);
        Insertar(lista, nuevaLetra, prioridadOriginal);
        System.out.println("Letra modificada correctamente");
    }
}
