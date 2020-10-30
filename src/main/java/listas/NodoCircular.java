package listas;

public class NodoCircular {

    private int elemento;
    private NodoCircular next;
    private NodoCircular prev;

    public NodoCircular(int element, NodoCircular previo, NodoCircular siguiente){

        this.elemento = element;
        this.next = previo;
        this.prev = siguiente;

    }

    public int getElemento() {
        return elemento;
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
}
