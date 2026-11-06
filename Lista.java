public class Lista {
    private Nodo P;
    private Nodo F;

    public Lista() {
        this.P = null;
        this.F = null;
    }

    //Metodos
    public void Inicializar() {
        Nodo temp = P;

    while (temp != null) {
        Nodo sig = temp.sig;
        
        temp.sig = null;
        temp.ant = null;
        temp = sig;
        }

        P = null;
        F = null;
    }

    //Mostrar lista de P a F
    public void MostrarP() {
        Nodo temp = P;
        while (temp != null) {
            System.out.print(temp.info + " ");
            temp = temp.sig;
        }
        System.out.println();
    }
    //Mostrar lista de F a P
    public void MostrarF() {
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

    public Nodo Buscar(char letraBuscada, int prioridadBuscada) {
        if (prioridadBuscada != 1 && prioridadBuscada != 2) {
            return null;
        }

        //Prioridad 1
        if(prioridadBuscada==1){
            Nodo temp = P;

            while (temp != null && temp.prioridad == 1) {
                if (temp.info == letraBuscada) {
                    return temp;
                }
                temp = temp.sig;
            }
            return null;
        }

        int posicionBuscada = posicionLetra(letraBuscada);
        if (posicionBuscada == -1) {
            return null;
        }

        char letraMayuscula = Character.toUpperCase(letraBuscada);

        if (letraMayuscula < 'N') {
        Nodo temp = P;

            while (temp != null && temp.prioridad == 1) {
                temp = temp.sig;
            }

            while (temp != null) {
                int posicionActual = posicionLetra(temp.info);

                if (posicionActual == posicionBuscada) {
                    return temp;
                }

            // La lista está ordenada: ya pasamos la posición buscada.
                if (posicionActual > posicionBuscada) {
                    return null;
                }

                temp = temp.sig;
            }
        } else {
            Nodo temp = F;

            while (temp != null && temp.prioridad == 2) {
                int posicionActual = posicionLetra(temp.info);

                if (posicionActual == posicionBuscada) {
                    return temp;
                }

                // Al ir hacia atrás, ya estamos antes de la posición buscada.
                if (posicionActual < posicionBuscada) {
                    return null;
                }

                temp = temp.ant;
            }
        }

        return null;
    }

    public void Insertar(char letra, int prioridad) {
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
        Nodo actual = null;

        if (prioridad == 1) {
            actual = P;

            while (actual != null && actual.prioridad == 1) {
                anterior = actual;
                actual = actual.sig;
            }
        } else {
            int posicionNueva = posicionLetra(letra);
            char letraMayuscula = Character.toUpperCase(letra);

            if (letraMayuscula < 'N') {
                actual = P;
                //saltar los nodos de prioridad 1
                while (actual != null && actual.prioridad == 1) {
                    anterior = actual;
                    actual = actual.sig;
                }

                //avanzar mientras las letras sean menores o iguales
                while (actual != null && posicionLetra(actual.info) <= posicionNueva) {
                    anterior = actual;
                    actual = actual.sig;
                }
            } else {
                 // Recorrer desde F; insertar antes de los duplicados.
                anterior = F;

                while (anterior != null
                        && anterior.prioridad == 2
                        && posicionLetra(anterior.info) >= posicionNueva) {
                    anterior = anterior.ant;
                }

                actual = (anterior == null) ? P : anterior.sig;
            }
        }

        nuevo.ant = anterior; //enlazar el nuevo nodo con el anterior
        nuevo.sig = actual;

        if (anterior == null) {
            P = nuevo;
        } else {
            anterior.sig = nuevo;
        }

        if (actual == null) {
            F = nuevo;
        } else {
            actual.ant = nuevo;
        }

        System.out.println("Letra insertada correctamente");
    }
    
    public void Eliminar(char letra, int prioridad) {
        Nodo nodoAEliminar = Buscar(letra, prioridad);
        if (prioridad == 1) {
            EliminarPrimeroPrioridad1();
            return;
        }

        if (prioridad != 2) {
            System.out.println("Prioridad no válida.");
            return;
        }

        if (nodoAEliminar == null) {
            System.out.println("La letra no se encuentra en la lista.");
            return;
        }

        if (nodoAEliminar.ant != null) {
            nodoAEliminar.ant.sig = nodoAEliminar.sig;// Actualizar el enlace
        } else {
            P = nodoAEliminar.sig; // Actualizar P si es el primer nodo
        }

        if (nodoAEliminar.sig != null) {
            nodoAEliminar.sig.ant = nodoAEliminar.ant;// Actualizar el enlace
        } else {
            F = nodoAEliminar.ant; // Actualizar F si es el último nodo
        }

        System.out.println("Letra eliminada correctamente");
    }

    public void EliminarPrimeroPrioridad1() {
        if (P == null || P.prioridad != 1) {
            System.out.println("No hay nodos de prioridad 1.");
            return;
        }

        Nodo eliminado = P;
        P = eliminado.sig;

        if (P != null) {
            P.ant = null;
        } else {
            F = null;
        }

        eliminado.sig = null;
        eliminado.ant = null;

        System.out.println("Se eliminó el primer nodo de prioridad 1.");
    }

    public void Modificar(char letra, int prioridad, char nuevaLetra) {
        if (posicionLetra(nuevaLetra) == -1) {
            System.out.println("Solo se permiten letras");
            return;
        }

        Nodo encontrado = Buscar(letra, prioridad);

        if (encontrado == null) {
            System.out.println("La letra no se encuentra en la lista.");
            return;
        }

        if (prioridad == 1) {
            // Cambiar el dato conserva la posición en la cola.
            encontrado.info = nuevaLetra;
        } else {
            // Reinsertar conserva el orden alfabético de prioridad 2.
            Eliminar(letra, prioridad);
            Insertar(nuevaLetra, prioridad);
        }

        System.out.println("Letra modificada correctamente");
    }
}
