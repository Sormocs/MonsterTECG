package listas;

public class NodoCircular {

    private Object elemento;
    private NodoCircular next;
    private NodoCircular prev;

    public NodoCircular(Object element, NodoCircular previo, NodoCircular siguiente){

        this.elemento = element;
        this.next = previo;
        this.prev = siguiente;

    }

    /**
     * Obtiene el elemento
     * @return Object
     */

    public Object getElemento() {
        return this.elemento;
    }

    /**
     * Modifica el siguiente
     * @param next NodoCircular
     */

    public void setNext(NodoCircular next) {
        this.next = next;
    }

    /**
     * Modifica el anterior
     * @param prev NodoCircular
     */

    public void setPrev(NodoCircular prev) {
        this.prev = prev;
    }

    /**
     * Retorna el anterior
     * @return NodoCircular
     */

    public NodoCircular getPrev(){
        return prev;
    }

    /**
     * Retorna el siguiente
     * @return NodoCircular
     */

    public NodoCircular getNext(){
        return next;
    }

    /**
     * Mofidica el elemento
     * @param elemento Object
     */

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}
