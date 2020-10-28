package listas;

public class NodoDoble {
    private Object dato;
    private NodoDoble siguiente;
    private NodoDoble anterior;

    public NodoDoble(Object dato){
        this(dato,null,null);
        }

    public NodoDoble(Object dato, NodoDoble siguiente, NodoDoble anterior){
        this.dato = dato;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
}
