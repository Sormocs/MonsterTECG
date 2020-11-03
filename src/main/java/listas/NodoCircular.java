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

    public Object getElemento() {
        return this.elemento;
    }

    public void setNext(NodoCircular next) {
        this.next = next;
    }

    public void setPrev(NodoCircular prev) {
        this.prev = prev;
    }

    public NodoCircular getPrev(){
        return prev;
    }

    public NodoCircular getNext(){
        return next;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}
