public class Nodo {
    String Info;
    int Prioridad;
    Nodo Ant;
    Nodo Sig;

    public Nodo(String palabra, int prioridad) {
        Info = palabra;
        Prioridad = prioridad;
        Ant = null;
        Sig = null;
    }
}