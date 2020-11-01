package listas;

/**
 * Clase para el manejo de un nodo doble
 */

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

    /**
     * metodo para obtener el anterior
     * @return NodoDoble
     */

    public NodoDoble getAnterior() {
        return anterior;
    }

    /**
     * Método para enlazar en alterior
     * @param anterior
     */

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    /**
     * Metodo para obtener el anterior
     * @return objecto
     */

    public Object getDato() {
        return dato;
    }

    /**
     * Metodo para dar el dato;
     * @param dato
     */

    public void setDato(Object dato) {
        this.dato = dato;
    }

    /**
     * Nodo para obtener el anterior
     * @return NodoDoble
     */

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    /**
     * Método para enlazar siguiente
     * @param siguiente
     */

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
}
