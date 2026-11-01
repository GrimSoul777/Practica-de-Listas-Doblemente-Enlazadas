public class Nodo {
    char info;
    int prioridad;

    Nodo sig;
    Nodo ant;

    public Nodo(char v, int prioridad) {
        this.info = v;
        this.prioridad = prioridad;
        this.sig = null;
        this.ant = null;
    }
    public Nodo(){

    }
}
