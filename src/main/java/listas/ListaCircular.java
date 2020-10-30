package listas;

public class ListaCircular {

    private int size;
    private NodoCircular inicio;
    private NodoCircular end;

    public void InsertEnd(int valor){

        if (inicio == null){

            NodoCircular nuevoNodo = new NodoCircular(valor, null, null);
            nuevoNodo.setNext(nuevoNodo);
            nuevoNodo.setPrev(nuevoNodo);
            inicio = nuevoNodo;
            end = nuevoNodo;

        } else{

            NodoCircular ultimo = getEnd();
            NodoCircular nuevoNodo = new NodoCircular(valor, ultimo, inicio);

            inicio.setPrev(nuevoNodo);
            end.setNext(nuevoNodo);
            end = nuevoNodo;

        }

    }

    public void Delete(int valor){



    }

    public NodoCircular getEnd() {
        return end;
    }

    public NodoCircular getInicio(){
        return inicio;
    }

    public void Show(){

        NodoCircular actual = inicio;
        while(actual != end){

            System.out.println(actual.getElemento());
            actual = actual.getNext();

        }
        System.out.println(actual.getElemento());

    }
}
